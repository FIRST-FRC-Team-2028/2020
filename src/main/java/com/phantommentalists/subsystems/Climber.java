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
 * Climbs the Power Generator
 */
public class Climber extends SubsystemBase {

  private CANSparkMax leftClimberMotor; 
  private CANSparkMax rightClimberMotor;
  private double reelSpeed = 1.; //FIXME: set in Parameters

  public Climber() {
    leftClimberMotor = new CANSparkMax(Parameters.CANIDs.CLIMB_LEFT.getid(), MotorType.kBrushless);
    rightClimberMotor = new CANSparkMax(Parameters.CANIDs.CLIMB_RIGHT.getid(), MotorType.kBrushless);
  }

  /**
   * releases climbing arm
   */
  public void releaseArm() {
    if (Parameters.CLIMBER_AVAILABLE) {
      //utilizes pneumatics (pancake cylinder)
    }
  }

  /**
   * climbs up
   */
  public void climbUp() {
    if (Parameters.CLIMBER_AVAILABLE) {
      leftClimberMotor.set(reelSpeed); //may need to be negative
      rightClimberMotor.set(reelSpeed); //may need to be negative
    }
  }

  /**
   * stops climbing
   */
  public void stopClimbing() {
    if (Parameters.CLIMBER_AVAILABLE) {
      leftClimberMotor.stopMotor();
      rightClimberMotor.stopMotor();
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