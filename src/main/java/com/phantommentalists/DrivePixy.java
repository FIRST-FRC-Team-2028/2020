/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/
package com.phantommentalists;

import edu.wpi.first.wpilibj.AnalogInput;

import edu.wpi.first.wpilibj.controller.PIDController;

/**
 * Interfaces with a PixyCam attached to an Analog port The pixy field of view
 * is 75 degrees horizontally. The analog signal from the pixy cam varied from 0
 * to 3.3 V as the target moves from left to right
 */
public class DrivePixy {
  private AnalogInput pixyPowerCell;
  public PIDController follow_Ball_Controller;

  // follow_Ball_Controller = new PIDController(Parameters.kP_Drive_Pixy,
  // Parameters.kI_Drive_Pixy,
  // Parameters.kD_Drive_Pixy); // Yellow Ball

  public DrivePixy(int channel) {
    pixyPowerCell = new AnalogInput(channel);
  }

  // map pixy output to -1 to +1 for angle range [-37, 37] degrees
  // public double getX() {
  // double val = pixyAnalog.getVoltage(); //FIXME use this.? actually where is
  // the getVoltage() at?
  // SmartDashboard.putNumber("PixyCam Input", val);
  // val = (val / 1.65) - 1.;
  // SmartDashboard.putNumber("PixyCam Output", val);
  // return val;
  // }

  public double getAverageVoltage() {
    return pixyPowerCell.getAverageVoltage();
  }

  public double getDriveFollowAdjust() {
    return follow_Ball_Controller.calculate(pixyPowerCell.getAverageVoltage(), (3.3 / 2.0));
  }

  public boolean isAcquired() {
    return false;
  }
}