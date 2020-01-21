/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package com.phantommentalists.subsystems;

import com.phantommentalists.Parameters;

import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.DoubleSolenoid.Value;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;

/**
 * Will extend arm and roll the wheels to pick up power cells from the floor
 * Then retract arm
 */
public class PickUp extends SubsystemBase {

  CANSparkMax roller;
  DoubleSolenoid arm;

  public PickUp() {
    roller = new CANSparkMax(Parameters.CANIDs.ROLLER.getid(), MotorType.kBrushless);
    roller.setInverted(Parameters.CANIDs.ROLLER.isInverted());
    // if (Parameters.CANIDs.ROLLER.isFollower())
    // {
    //   roller.follow(leader CAN ID);
    // }
    arm = new DoubleSolenoid(Parameters.PneumaticChannel.PICKUP_EXTEND.getChannel(), Parameters.PneumaticChannel.PICKUP_RETRACT.getChannel());
  }

  /**
   * Turn on rollers
   */
  public void turnOnRollers() {
    roller.set(Parameters.PICKUP_ROLLER_SPEED);
    // roller.set(ControlMode.PercentOutput, Parameters.PICKUP_ROLLER_SPEED)
  }

  /**
   * Turn off rollers
   */
  public void turnOffRollers() {
    roller.set(0.0);
    // roller.set(ControlMode.PercentOutput, 0.0);
  }

  /**
   * Extend arm
   */
  public void extend() {
    arm.set(Value.kForward);
  }

  /**
   * Retract arm
   */
  public void retract() {
    arm.set(Value.kReverse);
  }

  public boolean isPickUpExtended() {
    // FIXME:  Implement correct logic
    return false;
  }

  public boolean isPickUpRetracted() {
    // FIXME: Implement correct logic
    return false;
  }

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
  }
}
