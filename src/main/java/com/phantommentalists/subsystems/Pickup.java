/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package com.phantommentalists.subsystems;

import edu.wpi.first.wpilibj.Timer;

import com.phantommentalists.Parameters;

import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.DoubleSolenoid.Value;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;
//FIXME what type of MotorType?

/**
 * Will extend arm and roll the wheels to pick up power cells from the floor
 * Then retract arm
 */
public class PickUp extends SubsystemBase {
  //FIXME do I need to set these to private?
  CANSparkMax roller;
  DoubleSolenoid arm;
  Timer timer;

  public PickUp() {
    roller = new CANSparkMax(Parameters.CANIDs.ROLLER.getid(), MotorType.kBrushless);
    roller.setInverted(Parameters.CANIDs.ROLLER.isInverted());
    // if (Parameters.CANIDs.ROLLER.isFollower())
    // {
    //   roller.follow(leader CAN ID);
    // }
    arm = new DoubleSolenoid(Parameters.PneumaticChannel.PICKUP_EXTEND.getChannel(), Parameters.PneumaticChannel.PICKUP_RETRACT.getChannel());
    timer = new Timer();
  }

  /**
   * Turn on rollers
   */
  public void turnOnRollers() {
    if (Parameters.PICKUP_AVAILABLE) {
      roller.set(Parameters.PICKUP_ROLLER_SPEED);
      // roller.set(ControlMode.PercentOutput, Parameters.PICKUP_ROLLER_SPEED)
    }
  }

  /**
   * Turn off rollers
   */
  public void turnOffRollers() {
    if (Parameters.PICKUP_AVAILABLE) {
      roller.set(0.0);
      // roller.set(ControlMode.PercentOutput, 0.0);
    }
  }

  /**
   * Extend arm
   */
  public void extend() {
    if (Parameters.PICKUP_AVAILABLE) {
      arm.set(Value.kForward);
    }
  }

  /**
   * Retract arm
   */
  public void retract() {
    if (Parameters.PICKUP_AVAILABLE) {
      arm.set(Value.kReverse);
    }
  }

  /**
   * Turn off DoubleSolenoid
   * @return
   */
  public void turnArmoff() {
    if (Parameters.PICKUP_AVAILABLE) {
      arm.set(Value.kOff);
    }
  }

  public boolean isPickUpExtended() {
    if (Parameters.PICKUP_AVAILABLE) {
      if (timer.get() >= Parameters.EXTEND_TIME && arm.get() == Value.kForward) {
        return true;
      }
    }
    return false;
  }

  public boolean isPickUpRetracted() {
    if (Parameters.PICKUP_AVAILABLE) {
      if (timer.get() >= Parameters.RETRACT_TIME && arm.get() == Value.kReverse) {
        return true;
      }
    }
    return false;
  }

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
  }
}
