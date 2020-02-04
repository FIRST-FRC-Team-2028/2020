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
 * Turns to designated color and rotates wheel to given amount of times
 */
public class ControlPanel extends SubsystemBase {
  private CANSparkMax panelMotor;

  public ControlPanel() {
    panelMotor = new CANSparkMax(Parameters.CANIDs.CONTROL_PANEL.getid(), MotorType.kBrushless);
  }

  /**
   * 
   */
  // public void turnToColor(Color) {
  //   if (Parameters.CONTROLPANEL_AVAILABLE) {
  //     //is color going to be a string
  //   }
  // }

  // /**
  //  * 
  //  */
  // public void rotate(count) {
  //   if (Parameters.CONTROLPANEL_AVAILABLE) {
  //     //count number of rotations or encoder counts with timer?
  //   }
  // }

  /**
   * sets the power for the motor when spinning the wheel
   * @param voltage
   */
  public void setPower(double voltage) {
    if (Parameters.CONTROLPANEL_AVAILABLE) {
      panelMotor.setVoltage(voltage);
    }
  }

  /**
   * returns the color that the sensor sees FIXME: is this correct? is it for the sensor, or for what color we need to spin to?
   */
  public void getColor() {
    if (Parameters.CONTROLPANEL_AVAILABLE) {
      // Color
    }
  }

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
  }
}
