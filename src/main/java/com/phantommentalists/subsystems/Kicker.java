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
 * Will give a kick to the powercell when it comes through the pickup
 */
public class Kicker extends SubsystemBase {
  private CANSparkMax kicker;

  public Kicker() {
    kicker = new CANSparkMax(Parameters.CANIDs.KICKER.getid(), MotorType.kBrushless);
  }

  /**
   * Turn on rollers
   */
  public void turnOnKicker() {
    if (Parameters.KICKER_AVAILABLE) {
      kicker.set(Parameters.KICKER_SPEED);
      // roller.set(ControlMode.PercentOutput, Parameters.KICKER_SPEED)
    }
  }

  /**
   * Turn off rollers
   */
  public void turnOffKicker() {
    if (Parameters.KICKER_AVAILABLE) {
      kicker.set(0.0);
      // roller.set(ControlMode.PercentOutput, 0.0);
    }
  }

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
  }
}