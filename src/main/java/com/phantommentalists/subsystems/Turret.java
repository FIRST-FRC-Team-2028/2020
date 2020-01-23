/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package com.phantommentalists.subsystems;

import com.revrobotics.CANSparkMax;

import edu.wpi.first.wpilibj2.command.SubsystemBase;

/**
 * Rotates to aim for the ports
 * Angles with the hood
 * Uses rollers to shoot power cells
 */
public class Turret extends SubsystemBase {
  //FIXME do I need to set these as private?
  CANSparkMax yaw;
  CANSparkMax pitch;
  CANSparkMax shooter;
  public Turret() {

  }

  public void setYawPower(double voltage) {
    yaw.setVoltage(voltage);
  }

  public void setPitchPower(double voltage) {
    pitch.setVoltage(voltage);
  }

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
  }
}
