/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package com.phantommentalists.subsystems;

import com.phantommentalists.Parameters;
import com.phantommentalists.Parameters.Mode;

import edu.wpi.first.wpilibj2.command.SubsystemBase;

/**
 * Aims the Turret to the Power Cell and Power Port
 */
public class Aim extends SubsystemBase {
  Mode mode;

  public Aim() {

  }

  public void getAngleToPowerCell() {
    if (Parameters.AIM_AVAILABLE) {
      
    }
  }

  public void getChassisAngle() {
    if (Parameters.AIM_AVAILABLE) {
      
    }
  }

  public void getDistance() {
    if (Parameters.AIM_AVAILABLE) {
      
    }
  }

  public void getAngleToPowerPort() {
    if (Parameters.AIM_AVAILABLE) {
      
    }
  }

  public void isTargetSeen() {
    if (Parameters.AIM_AVAILABLE) {
      
    }
  }

  public void enable() {
    if (Parameters.AIM_AVAILABLE) {
      
    }
  }

  public void disable() {
    if (Parameters.AIM_AVAILABLE) {

    }
  }

  public void setNoTargetMode(Mode mode2) {
    if (Parameters.AIM_AVAILABLE) {
      mode = mode2;
    }
  }

  public void getNoTargetMode() {
  //Mode
  }
  @Override
  public void periodic() {
    // This method will be called once per scheduler run
  }
}
