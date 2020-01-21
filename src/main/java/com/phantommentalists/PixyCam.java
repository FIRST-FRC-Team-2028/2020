/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/
package com.phantommentalists;

import edu.wpi.first.wpilibj.AnalogInput;
import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 * Interfaces with a PixyCam attached to an Analog port
 * The pixy field of view is 75 degrees horizontally.
 * The analog signal from the pixy cam varied from 0 to 3.3 V
 *   as the target moves from left to right
 */
public class PixyCam {
  private AnalogInput cam;
  private DigitalInput isBall;
  public PixyCam(int channel) {
    cam = new AnalogInput(channel);
    isBall = new DigitalInput(1);
  }

  // map pixy output to -1 to +1 for angle range [-37, 37] degrees
  public double getX() {
    double val = cam.getVoltage();
    SmartDashboard.putNumber("PixyCam Input", val);
    val = (val/1.65) - 1.;
    SmartDashboard.putNumber("PixyCam Output", val);
    return val;
  }

  public boolean isAquired(){
    boolean detected = isBall.get();
    SmartDashboard.putBoolean("Is there a ball?", detected);
    return false;
  }
}
