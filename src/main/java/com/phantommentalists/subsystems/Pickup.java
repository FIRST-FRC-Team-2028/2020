/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package com.phantommentalists.subsystems;

import com.phantommentalists.Parameters;

import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;

import edu.wpi.first.wpilibj2.command.SubsystemBase;

/**
   * Extends/Retracts the power cell pickup arm
   */
public class Pickup extends SubsystemBase {
  
  private CANSparkMax pickupRollerWheels;

  public Pickup() {
    pickupRollerWheels = new CANSparkMax(Parameters.CANIDs.ROLLER.getid(), MotorType.kBrushless);
  }

  public void extend() {
    if (Parameters.PICKUP_AVAILABLE) {
      //TODO Add stuff
    }
  }

  public void retract() {
    if (Parameters.PICKUP_AVAILABLE) {
      //TODO Add stuff
    }
  }

  public void turnOnRollers(double direction) {
    if (Parameters.PICKUP_AVAILABLE) {
      if (direction > 0) {
        pickupRollerWheels.setVoltage(Parameters.PICKUP_ROLLER_SPEED);
      }
      else if (direction < 0) {
        pickupRollerWheels.setVoltage(-Parameters.PICKUP_ROLLER_SPEED);
      }
      else {
        pickupRollerWheels.setVoltage(0.0);
      }
    
    }
  }

  public void turnOffRollers() {
    pickupRollerWheels.setVoltage(0.0);
  }

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
  }
}
