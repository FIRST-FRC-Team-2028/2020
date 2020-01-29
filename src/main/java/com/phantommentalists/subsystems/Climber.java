/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package com.phantommentalists.subsystems;

import com.phantommentalists.Parameters;

import edu.wpi.first.wpilibj2.command.SubsystemBase;

/**
 * Climbs the Power Generator
 */
public class Climber extends SubsystemBase {

  public Climber() {

  }

  /**
   * releases climbing arm
   */
  public void releaseArm() {
    if (Parameters.CLIMBER_AVAILABLE) {

    }
  }

  /**
   * climbs up
   */
  public void climbUp() {
    if (Parameters.CLIMBER_AVAILABLE) {

    }
  }

  /**
   * stops climbing
   */
  public void stopClimbing() {
    if (Parameters.CLIMBER_AVAILABLE) {

    }
  }

  /**
   * releases winch
   */
  public void releaseWinch() {
    if (Parameters.CLIMBER_AVAILABLE) {

    }
  }

  /**
   * returns whether the climber has been deployed or not
   * @return
   */
  public boolean isClimberDeployed() {
    if (Parameters.CLIMBER_AVAILABLE) {

    }
    return false;
  }

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
  }
}
