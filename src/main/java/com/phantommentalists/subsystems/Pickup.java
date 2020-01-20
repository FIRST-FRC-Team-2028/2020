/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package com.phantommentalists.subsystems;

import com.phantommentalists.Constants;
import edu.wpi.first.wpilibj.DoubleSolenoid;

import edu.wpi.first.wpilibj2.command.SubsystemBase;

import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;

public class Pickup extends SubsystemBase {

 /**
   * Will extend arm and roll the wheels to pick up power cells from the floor
   * Then retract arm
   */

  CANSparkMax roller;
  DoubleSolenoid arm;

  public Pickup() {
    roller = new CANSparkMax(0, MotorType.kBrushless);
    arm = new DoubleSolenoid(Constants.SOLENOID_EXTEND, Constants.SOLENOID_RETRACT);
  }

  /**
   * Turn on rollers
   */
  public void turnOnRollers() {
    roller.set(Constants.PICKUP_ROLLER_SPEED);
    //roller.set(ControlMode.PercentOutput, Constants.PICKUP_ROLLER_SPEED)
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
    arm.set(Constants.PICKUP_RETRACT);
  }

  /**
   * Retract arm
   */
  public void retract() {
    arm.set(Constants.PICKUP_RETRACT);
  }

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
  }
}
