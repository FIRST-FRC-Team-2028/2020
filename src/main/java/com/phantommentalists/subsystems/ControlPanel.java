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
import com.revrobotics.ColorSensorV3;

import edu.wpi.first.wpilibj.I2C;
import edu.wpi.first.wpilibj.util.Color;

import edu.wpi.first.wpilibj2.command.SubsystemBase;

/**
 * Turns to designated color and rotates wheel to given amount of times
 */
public class ControlPanel extends SubsystemBase {
  private CANSparkMax panelMotor;
  private ColorSensorV3 ColorSensor;
  private I2C.Port i2cPort;

  public ControlPanel() {
    panelMotor = new CANSparkMax(Parameters.CANIDs.CONTROL_PANEL.getid(), MotorType.kBrushless);
    i2cPort = I2C.Port.kOnboard;
    ColorSensor = new ColorSensorV3(i2cPort);
  }

  /**
   * FIXME: Can this be used in a command + checking the color when it ends and if true(matches)
   */
  public void turnToColor(Color) {
    if (Parameters.CONTROLPANEL_AVAILABLE) {
      //how much shouldwe turn? how many encoder counts?
    }
  }

  /**
   * 
   */
  public void rotate(count) {
    if (Parameters.CONTROLPANEL_AVAILABLE) {
      //count number of rotations or encoder counts with timer?
      
    }
  }

  /**
   * sets the power for the motor when spinning the wheel TODO: Add a constant for this method in parameters
   * @param voltage
   */
  public void setPower(double voltage) {
    if (Parameters.CONTROLPANEL_AVAILABLE) {
      panelMotor.setVoltage(voltage);
    }
  }

  //Use the Color Sensors code to getColor()
  public Color getReadColor() {
    return ColorSensor.getColor();
  }

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
  }
}
