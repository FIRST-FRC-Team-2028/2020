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
 * Contains the Power Cells
 * Moving them to the Turret to shoot
 */
public class Magazine extends SubsystemBase {
  CANSparkMax accelerator;
  CANSparkMax magazine;


  public Magazine() {
    accelerator = new CANSparkMax(Parameters.CANIDs.ACCELERATOR.getid(), MotorType.kBrushless);
    magazine = new CANSparkMax(Parameters.CANIDs.MAGAZINE.getid(), MotorType.kBrushless);
  }

  public void getBallHeldCount() {
    if (Parameters.MAGAZINE_AVAILABLE) {
      //number of times PickUp is used - how many times shooter is used?
      //return an integer
      
    }
  }

  public void loadBall() {
    if (Parameters.MAGAZINE_AVAILABLE) {
      magazine.set(Parameters.MAGAZINE_LOAD_SPEED);
    }
  }

  public void shootBall() {
    if (Parameters.MAGAZINE_AVAILABLE) {
      magazine.set(Parameters.MAGAZINE_SHOOT_SPEED);
    }
  }

  public void setLoaderPower(double voltage) {
    if (Parameters.MAGAZINE_AVAILABLE) {
      magazine.setVoltage(voltage);
    }
  }

  public void setFeedPower(double voltage) {
    if (Parameters.MAGAZINE_AVAILABLE) {
      magazine.setVoltage(voltage);
    }
  }
  //FIXME what is the difference between LoaderPower and FeedPower? is it the motors?

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
  }
}
