/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package com.phantommentalists.subsystems;

import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;

import edu.wpi.first.wpilibj.ADXRS450_Gyro;
import edu.wpi.first.wpilibj.kinematics.DifferentialDriveKinematics;
import edu.wpi.first.wpilibj.kinematics.DifferentialDriveOdometry;
import edu.wpi.first.wpilibj.kinematics.DifferentialDriveWheelSpeeds;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj.util.Units;
import edu.wpi.first.wpilibj.controller.PIDController;
import edu.wpi.first.wpilibj.controller.SimpleMotorFeedforward;
import edu.wpi.first.wpilibj.geometry.Pose2d;
import edu.wpi.first.wpilibj.geometry.Rotation2d;

import edu.wpi.first.wpilibj2.command.SubsystemBase;

import com.phantommentalists.Parameters;
import com.phantommentalists.Parameters.Gear;
import com.phantommentalists.commands.DriveDefaultCommand;

/**
 * Drives robot using 3 wheel drive? Can spin
 */
public class Drive extends SubsystemBase {
  private CANSparkMax leftLeader;
  private CANSparkMax leftFollower;
  private CANSparkMax rightLeader;
  private CANSparkMax rightFollower;
  private Gear gear;
  private DifferentialDriveKinematics kinematics;
  private DifferentialDriveOdometry odometry;
  private ADXRS450_Gyro gyro;
  private PIDController leftMotorController;
  private PIDController rightMotorController;

  /** Returns location of robot on the field */
  private Pose2d pose;

  /** Converts motor controller voltage to speed */
  private SimpleMotorFeedforward feedForward;
  

  public Drive() {
    leftLeader = new CANSparkMax(Parameters.CANIDs.DRIVE_LEFT_LEADER.getid(), MotorType.kBrushless);
    rightLeader = new CANSparkMax(Parameters.CANIDs.DRIVE_RIGHT_LEADER.getid(), MotorType.kBrushless);
    leftFollower = new CANSparkMax(Parameters.CANIDs.DRIVE_LEFT_FOLLOWER.getid(), MotorType.kBrushless);
    rightFollower = new CANSparkMax(Parameters.CANIDs.DRIVE_RIGHT_FOLLOWER.getid(), MotorType.kBrushless);

    // leftLeader.restoreFactoryDefaults();
    // leftFollower.restoreFactoryDefaults();
    // rightLeader.restoreFactoryDefaults();
    // rightFollower.restoreFactoryDefaults();

    // No need to set inverted on followers, per Rev Robotics documentation for 2020
    rightLeader.setInverted(Parameters.CANIDs.DRIVE_RIGHT_LEADER.isInverted());
    leftLeader.setInverted(Parameters.CANIDs.DRIVE_LEFT_LEADER.isInverted());


    if (Parameters.CANIDs.DRIVE_LEFT_FOLLOWER.isFollower()) {
      leftFollower.follow(leftLeader);
    }
    if (Parameters.CANIDs.DRIVE_RIGHT_FOLLOWER.isFollower()) {
      rightFollower.follow(rightLeader);
    }

    gyro = new ADXRS450_Gyro( /* SPI.kOnbardCS0 */);
    kinematics = new DifferentialDriveKinematics(Units.inchesToMeters(Parameters.DRIVE_TRACK_WIDTH));
    odometry = new DifferentialDriveOdometry( /*kinematics,*/ getChassisAngle());
    feedForward = new SimpleMotorFeedforward(Parameters.DRIVE_KS, Parameters.DRIVE_KV);
    leftMotorController = new PIDController(Parameters.DRIVE_KP, Parameters.DRIVE_KI, Parameters.DRIVE_KD);
    rightMotorController = new PIDController(Parameters.DRIVE_KP, Parameters.DRIVE_KI, Parameters.DRIVE_KD);
  }

  /**
   * wheel distance is motor encoder counts/ counts per revolution * gear ratio * wheel circumference 
   * @return
   */
   public DifferentialDriveWheelSpeeds getWheelSpeeds() {
      double leftDistanceMeters = leftLeader.getEncoder().getVelocity() / Parameters.DRIVE_LEFT_GEAR_RATIO * 2 * Math.PI * Units.inchesToMeters(Parameters.DRIVE_WHEEL_DIAMETER / 2) / 60;
      double rightDistanceMeters = rightLeader.getEncoder().getVelocity() / Parameters.DRIVE_RIGHT_GEAR_RATIO * 2 * Math.PI * Units.inchesToMeters(Parameters.DRIVE_WHEEL_DIAMETER / 2) / 60;
      return new DifferentialDriveWheelSpeeds(leftDistanceMeters, rightDistanceMeters);
  }

  /**
   * FIXME
   * 
   * @return SimpleMotorFeedForward - FIXME
   */
  public SimpleMotorFeedforward getFeedForward() {
    return feedForward;
  }

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
    if (Parameters.DRIVE_AVAILABLE) {
      SmartDashboard.putNumber("Right Output", rightLeader.getAppliedOutput());
      SmartDashboard.putNumber("Right Current",rightLeader.getOutputCurrent());
      SmartDashboard.putNumber("Left Output", leftLeader.getAppliedOutput());
      SmartDashboard.putNumber("Left Current",leftLeader.getOutputCurrent());

      pose = odometry.update(getChassisAngle(), getWheelSpeeds().leftMetersPerSecond, getWheelSpeeds().rightMetersPerSecond);
    }
  }

  /**
   * 
   * @param left - Requires motor voltage for the left side of the robot's drive
   * @param right - Requires motor voltage for the right side of the robot
   */
  public void tankDrive(double left, double right) {
    if (Parameters.DRIVE_AVAILABLE) {
      leftLeader.set(left / 12.0);
      rightLeader.set(right / 12.0);
    }
  }

  /**
   * Tells the robot to spin in place.
   * 
   * @param voltage - Motor power in voltage. Positive voltage is clockwise
   */
  public void spin(double voltage) {
    if (Parameters.DRIVE_AVAILABLE) {
      leftLeader.set(voltage / 12);
      rightLeader.set(-voltage / 12);
    }
  }

  public void stop(){
    if (Parameters.DRIVE_AVAILABLE) {
      leftLeader.set(0.0);
      rightLeader.set(0.0);
    }
  }
  
  public double getAllMotorCurrent() {
    if (Parameters.DRIVE_AVAILABLE) {
      return leftLeader.getOutputCurrent() + rightLeader.getOutputCurrent();
    }
    else {
      return 0.0;
    }
  }

  public void initDefaultCommand() {
    if (Parameters.DRIVE_AVAILABLE) {
      setDefaultCommand(new DriveDefaultCommand());
    }
  }

  public void drivePower(double power) {
    if (Parameters.DRIVE_AVAILABLE) {

    }
    //FIXME: what to do here?
  }

  public void setGear(Gear gear2) {
    if (Parameters.DRIVE_AVAILABLE) {
      gear = gear2;
    }
  }

  /**
   * Convinience method to return the chassis angle from the gyro sensor in a Rotation2d object.
   * In WPILIBJ, gyro angles increase clockwise, but in the Algebra unit circle, angles increase
   * counterclockwise, so the reading from the gyro must be inverted.
   * 
   * @return - Rotation2d - The angle of the robot on the field.
   */
  public Rotation2d getChassisAngle() {
    return Rotation2d.fromDegrees(-1.0 * gyro.getAngle());
  }

  /**
   * 
   * @return
   */
  public PIDController getLeftMotorController() {
    return leftMotorController;
  }

  /**
   * 
   * @return
   */
  public PIDController getRightMotorController() {
    return rightMotorController;
  }

}