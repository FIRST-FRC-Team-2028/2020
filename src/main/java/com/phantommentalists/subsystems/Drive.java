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
import com.phantommentalists.Constants;

public class Drive extends SubsystemBase {
  
private CANSparkMax leftLeader;
private CANSparkMax leftFollower;
private CANSparkMax rightLeader;
private CANSparkMax rightFollower;
  /**
   * Creates a new ExampleSubsystem.
   */
  public Drive() {
    leftLeader = new CANSparkMax(Constants.CANIDs.DRIVE_LEFT_LEADER.getid(), MotorType.kBrushless);
    rightLeader = new CANSparkMax(Constants.CANIDs.DRIVE_RIGHT_LEADER.getid(), MotorType.kBrushless);
    leftFollower = new CANSparkMax(Constants.CANIDs.DRIVE_LEFT_FOLLOWER.getid(), MotorType.kBrushless);
    rightFollower = new CANSparkMax(Constants.CANIDs.DRIVE_RIGHT_FOLLOWER.getid(), MotorType.kBrushless);

    leftLeader.restoreFactoryDefaults();
    leftFollower.restoreFactoryDefaults();
    rightLeader.restoreFactoryDefaults();
    rightFollower.restoreFactoryDefaults();

    rightLeader.setInverted(Constants.CANIDs.DRIVE_RIGHT_LEADER.isInverted());
    leftLeader.setInverted(Constants.CANIDs.DRIVE_LEFT_LEADER.isInverted());

    if (Constants.CANIDs.DRIVE_LEFT_FOLLOWER.isfollower()) leftFollower.follow(leftLeader);
    if (Constants.CANIDs.DRIVE_RIGHT_FOLLOWER.isfollower()) rightFollower.follow(rightLeader);
  }

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
    SmartDashboard.putNumber("Right Output", rightLeader.getAppliedOutput());
    SmartDashboard.putNumber("Right Current",rightLeader.getOutputCurrent());
    SmartDashboard.putNumber("Left Output", leftLeader.getAppliedOutput());
    SmartDashboard.putNumber("Left Current",leftLeader.getOutputCurrent());
  }

  public void tankDrive(double left, double right) {
    leftLeader.set(left);
    rightLeader.set(right);
  }

  public void spin(double rate) {
    //Positive rate is clockwise 
    leftLeader.set(rate);
    rightLeader.set(-rate);
  }

  public void stop(){
    leftLeader.set(0.);
    rightLeader.set(0.);
  }
}
