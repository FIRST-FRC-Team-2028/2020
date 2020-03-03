/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package com.phantommentalists.subsystems;

import edu.wpi.first.wpilibj.Timer;

import com.phantommentalists.OI;
import com.phantommentalists.Parameters;
import com.phantommentalists.Parameters.PickupPos;
import com.phantommentalists.commands.PickupDefaultCommand;

import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.DoubleSolenoid.Value;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;

/**
 * Will extend arm and roll the wheels to pick up power cells from the floor
 * Then retract arm
 */
public class Pickup extends SubsystemBase {
  private CANSparkMax pickup;
  private DoubleSolenoid arm;
  private Timer timer;
  public PickupPos position;
  private OI oi;

  public Pickup() {
    pickup = new CANSparkMax(Parameters.CANIDs.PICKUP.getid(), MotorType.kBrushless);
    pickup.setInverted(Parameters.CANIDs.PICKUP.isInverted());
    arm = new DoubleSolenoid(Parameters.PneumaticChannel.PICKUP_EXTEND.getChannel(), Parameters.PneumaticChannel.PICKUP_RETRACT.getChannel());
    timer = new Timer();
    position = Parameters.PickupPos.RETRACT;
    
  }

  /**
   * Turn on rollers
   */
  public void turnOnRollersFwd() {
    if (Parameters.PICKUP_AVAILABLE) {
      pickup.set(-Parameters.PICKUP_ROLLER_SPEED);
    }
  }

  public void turnOnRollersRev() {
    if (Parameters.PICKUP_AVAILABLE) {
      pickup.set(Parameters.PICKUP_ROLLER_SPEED);
    }
  }

  /**
   * Turn off rollers
   */
  public void turnOffRollers() {
    if (Parameters.PICKUP_AVAILABLE) {
      pickup.set(0.0);
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
  public void stopArm() {
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

  public void setPosition(PickupPos switchPosition) {
    if (Parameters.TURRET_AVAILABLE) {
      position = switchPosition;
    }
  }

  public void initDefaultCommand() {
    setDefaultCommand(new PickupDefaultCommand(oi, this));
  }

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
  }
}
