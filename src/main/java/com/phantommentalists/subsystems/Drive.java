/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package com.phantommentalists.subsystems;

import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

import com.phantommentalists.Parameters;
import com.phantommentalists.Telepath;
import com.phantommentalists.Parameters.Gear;
import com.phantommentalists.commands.DriveDefaultCommand;

/**
 * Drives robot using 3 wheel drive?
 * Can spin
 */
public class Drive extends SubsystemBase { 
  private CANSparkMax leftLeader;
  private CANSparkMax leftFollower;
  private CANSparkMax rightLeader;
  private CANSparkMax rightFollower;
  Gear gear;

  public Drive() {
    leftLeader = new CANSparkMax(Parameters.CANIDs.DRIVE_LEFT_LEADER.getid(), MotorType.kBrushless);
    rightLeader = new CANSparkMax(Parameters.CANIDs.DRIVE_RIGHT_LEADER.getid(), MotorType.kBrushless);
    leftFollower = new CANSparkMax(Parameters.CANIDs.DRIVE_LEFT_FOLLOWER.getid(), MotorType.kBrushless);
    rightFollower = new CANSparkMax(Parameters.CANIDs.DRIVE_RIGHT_FOLLOWER.getid(), MotorType.kBrushless);

    leftLeader.restoreFactoryDefaults();
    leftFollower.restoreFactoryDefaults();
    rightLeader.restoreFactoryDefaults();
    rightFollower.restoreFactoryDefaults();

    rightLeader.setInverted(Parameters.CANIDs.DRIVE_RIGHT_LEADER.isInverted());
    leftLeader.setInverted(Parameters.CANIDs.DRIVE_LEFT_LEADER.isInverted());

    if (Parameters.CANIDs.DRIVE_LEFT_FOLLOWER.isFollower()) leftFollower.follow(leftLeader);
    if (Parameters.CANIDs.DRIVE_RIGHT_FOLLOWER.isFollower()) rightFollower.follow(rightLeader);
  }

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
    if (Parameters.DRIVE_AVAILABLE) {
      SmartDashboard.putNumber("Right Output", rightLeader.getAppliedOutput());
      SmartDashboard.putNumber("Right Current",rightLeader.getOutputCurrent());
      SmartDashboard.putNumber("Left Output", leftLeader.getAppliedOutput());
      SmartDashboard.putNumber("Left Current",leftLeader.getOutputCurrent());
    }
  }

  public void tankDrive(double left, double right) {
    if (Parameters.DRIVE_AVAILABLE) {
      leftLeader.set(left);
      rightLeader.set(right);
    }
  }

  public void spin(double rate) {
    //Positive rate is clockwise 
    if (Parameters.DRIVE_AVAILABLE) {
      leftLeader.set(rate);
      rightLeader.set(-rate);
    }
  }

  public void stop(){
    if (Parameters.DRIVE_AVAILABLE) {
      leftLeader.set(0.);
      rightLeader.set(0.);
    }
  }
  
  public double getAllMotorCurrent() {
    if (Parameters.DRIVE_AVAILABLE) {
      return leftLeader.getOutputCurrent() + rightLeader.getOutputCurrent();
    }
    return 0.0;
    //FIXME what should be returned if Drive is not available
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

  public void pidWrite() {
    if (Parameters.DRIVE_AVAILABLE) {

    }
    //FIXME: what does this mean?
  }

  public void setGear(Gear gear2) {
    if (Parameters.DRIVE_AVAILABLE) {
      gear = gear2;
    }
  }
}
