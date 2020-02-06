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
import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.kinematics.DifferentialDriveKinematics;
import edu.wpi.first.wpilibj.kinematics.DifferentialDriveOdometry;
import edu.wpi.first.wpilibj.kinematics.DifferentialDriveWheelSpeeds;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj.util.Units;
import edu.wpi.first.wpilibj.controller.PIDController;
import edu.wpi.first.wpilibj.controller.SimpleMotorFeedforward;
import edu.wpi.first.wpilibj.geometry.Pose2d;
import edu.wpi.first.wpilibj.geometry.Rotation2d;
import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.DoubleSolenoid.Value;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

import java.lang.reflect.Parameter;

import com.phantommentalists.OI;
import com.phantommentalists.Parameters;
import com.phantommentalists.Parameters.Gear;
import com.phantommentalists.Parameters.PneumaticChannel;
import com.phantommentalists.commands.DriveDefaultCommand;
import com.phantommentalists.PixyAnalog;
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
  public PIDController follow_Ball_Controller;

  /** Returns location of robot on the field */
  private Pose2d pose;
  private Timer timer;

  /** Converts motor controller voltage to speed */
  private SimpleMotorFeedforward feedForward;
  private DoubleSolenoid shifter;
  private OI oi;
  private PixyAnalog pixyAnalog;

  public Drive(OI o) {
    oi = o;
    timer = new Timer();
    leftLeader = new CANSparkMax(Parameters.CANIDs.DRIVE_LEFT_LEADER.getid(), MotorType.kBrushless);
    rightLeader = new CANSparkMax(Parameters.CANIDs.DRIVE_RIGHT_LEADER.getid(), MotorType.kBrushless);
    leftFollower = new CANSparkMax(Parameters.CANIDs.DRIVE_LEFT_FOLLOWER.getid(), MotorType.kBrushless);
    rightFollower = new CANSparkMax(Parameters.CANIDs.DRIVE_RIGHT_FOLLOWER.getid(), MotorType.kBrushless);
    shifter = new DoubleSolenoid(PneumaticChannel.DRIVE_LOW_GEAR.getChannel(),
        PneumaticChannel.DRIVE_HIGH_GEAR.getChannel());

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

    // gyro = new ADXRS450_Gyro(Parameters.CHASSIS_GYRO_PORT);
    kinematics = new DifferentialDriveKinematics(Units.inchesToMeters(Parameters.DRIVE_TRACK_WIDTH));
    odometry = new DifferentialDriveOdometry( /* kinematics, */ getChassisAngle());
    feedForward = new SimpleMotorFeedforward(Parameters.DRIVE_KS, Parameters.DRIVE_KV);
    leftMotorController = new PIDController(Parameters.DRIVE_KP, Parameters.DRIVE_KI, Parameters.DRIVE_KD);
    rightMotorController = new PIDController(Parameters.DRIVE_KP, Parameters.DRIVE_KI, Parameters.DRIVE_KD);
    pixyAnalog = new PixyAnalog(Parameters.PIXY_ANALOG_CHANNEL);    
    follow_Ball_Controller = new PIDController(Parameters.kP_Drive_Pixy, Parameters.kI_Drive_Pixy,
        Parameters.kD_Drive_Pixy);
  }

  /**
   * wheel distance is motor encoder counts/ counts per revolution * gear ratio *
   * wheel circumference
   * 
   * @return
   */
  public DifferentialDriveWheelSpeeds getWheelSpeeds() {
    double leftDistanceMeters = leftLeader.getEncoder().getVelocity() / Parameters.DRIVE_LEFT_GEAR_RATIO * 2 * Math.PI
        * Units.inchesToMeters(Parameters.DRIVE_WHEEL_DIAMETER / 2) / 60;
    double rightDistanceMeters = rightLeader.getEncoder().getVelocity() / Parameters.DRIVE_RIGHT_GEAR_RATIO * 2
        * Math.PI * Units.inchesToMeters(Parameters.DRIVE_WHEEL_DIAMETER / 2) / 60;
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
      // SmartDashboard.putNumber("Right Output", rightLeader.getAppliedOutput());
      // SmartDashboard.putNumber("Right Current",rightLeader.getOutputCurrent());
      // SmartDashboard.putNumber("Left Output", leftLeader.getAppliedOutput());
      // SmartDashboard.putNumber("Left Current",leftLeader.getOutputCurrent());

      // pose = odometry.update(getChassisAngle(),
      // getWheelSpeeds().leftMetersPerSecond, getWheelSpeeds().rightMetersPerSecond);
    }
  }

  /**
   * @param left  - Requires motor voltage for the left side of the robot's drive
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

  /**
   * stops the robot from moving
   */
  public void stop() {
    if (Parameters.DRIVE_AVAILABLE) {
      leftLeader.set(0.0);
      rightLeader.set(0.0);
    }
  }

  /**
   * returns the current of both motors combined
   * 
   * @return
   */
  public double getAllMotorCurrent() {
    if (Parameters.DRIVE_AVAILABLE) {
      return leftLeader.getOutputCurrent() + rightLeader.getOutputCurrent();
    } else {
      return 0.0;
    }
  }

  /**
   * sets the default command
   */
  public void initDefaultCommand() {
    if (Parameters.DRIVE_AVAILABLE) {
      setDefaultCommand(new DriveDefaultCommand(this, oi));
    }
  }

  /**
   * sets the drive power
   * 
   * @param power
   */
  public void drivePower(double power) {
    if (Parameters.DRIVE_AVAILABLE) {
    }
    // FIXME: what to do here?
  }

  /**
   * sets which gear we are in
   * 
   * @param gear2
   */
  public void setGear(Gear newGear) {
    if (Parameters.DRIVE_AVAILABLE) {
      gear = newGear;
      if (gear == Gear.LOW) {
        shifter.set(Value.kForward);
      } else {
        shifter.set(Value.kReverse);
      }

    }
  }

  /**
   * Convinience method to return the chassis angle from the gyro sensor in a
   * Rotation2d object. In WPILIBJ, gyro angles increase clockwise, but in the
   * Algebra unit circle, angles increase counterclockwise, so the reading from
   * the gyro must be inverted.
   * 
   * @return - Rotation2d - The angle of the robot on the field.
   */
  public Rotation2d getChassisAngle() {
    // return Rotation2d.fromDegrees(-1.0 * gyro.getAngle());
    // FIXME
    return Rotation2d.fromDegrees(0.0);
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

  public void Test() {
    {
      timer.start();
      if (timer.get() < 5) {
        rightLeader.set(0.5);
      } else if (timer.get() < 10) {
        rightLeader.set(0.0);
        rightFollower.set(0.5);
      } else if (timer.get() < 15) {
        rightFollower.set(0.0);
        leftLeader.set(0.5);
      } else if (timer.get() < 20) {
        leftLeader.set(0.0);
        leftLeader.set(0.5);
      } else {
        rightLeader.set(0.0);
        rightFollower.set(0.0);
        leftLeader.set(0.0);
        rightFollower.set(0.0);
      }
    }

  }

  public void executeDrive(double driveAdjust) {
    // XboxController xboxController = oi.getXboxController();
    // double left = xboxController.getRawAxis(Parameters.LEFT_STICK);
    // double right = xboxController.getRawAxis(Parameters.RIGHT_STICK);
    // if (Math.abs(left) < Parameters.DRIVE_DEAD_BAND) {
    //   left = 0;
    // }
    // if (Math.abs(right) < Parameters.DRIVE_DEAD_BAND) {
    //   right = 0;
    // }
    // drive.tankDrive(left, right);
    double Y = oi.getPilotStick().getY();
    double X = oi.getPilotStick().getX();

    // "Exponential" drive, where the movements are more sensitive during slow
    // movement, permitting easier fine control
    // X = Math.pow(X, 3); /// FIXME take a look here does it make a
    // Y = Math.pow(Y, 3); /// difference

    if (Math.abs(Y) <= Parameters.DRIVE_DEAD_BAND) {
      Y = 0;
    }
    if (Math.abs(X) <= Parameters.DRIVE_DEAD_BAND) {
      X = 0;
    }
    double V = ((1.0 - Math.abs(X)) * Y) + Y;
    double W = ((1.0 - Math.abs(Y)) * X) + X;
    double R = (V + W) / 4; ///// Should be divide by 2 for full power
    double L = (V - W) / 4; ///// FIXME

    /*----------------------------------------------------------------------------*/
    /* Shifts to High Gear on Pilot Trigger */
    /*----------------------------------------------------------------------------*/
    if (oi.GetHighGearButton()) {
      setGear(Gear.HIGH);
    } else {
      setGear(Gear.LOW);
    }

    tankDrive((R - driveAdjust) * 12.0, (L + driveAdjust) * 12.0);    
  }

  public PixyAnalog getPixyAnalog() {
    return pixyAnalog;
  }
}