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

import edu.wpi.first.hal.ControlWord;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

/**
 * Contains the Power Cells Moving them to the Turret to shoot
 */
public class Magazine extends SubsystemBase {
  // FIXME does accelerator need to be private?
  CANSparkMax accelerator;
  private CANSparkMax magazine;
  private int ballCount;
  private ControlWord controlWord;

  public Magazine() {
    accelerator = new CANSparkMax(Parameters.CANIDs.ACCELERATOR.getid(), MotorType.kBrushless);
    magazine = new CANSparkMax(Parameters.CANIDs.MAGAZINE.getid(), MotorType.kBrushless);
    controlWord = new ControlWord();
    if (controlWord.getAutonomous()) {
      ballCount = 3;
    }
    else {
      ballCount = 0;
    }
  }

  public int getBallHeldCount() {
    if (Parameters.MAGAZINE_AVAILABLE) {
      // number of times PickUp is used - how many times shooter is used?
      return ballCount;
    }
    return 0;
  }

  public void loadBall() {
    if (Parameters.MAGAZINE_AVAILABLE) {
      magazine.set(Parameters.MAGAZINE_LOAD_SPEED);
      ++ballCount;
    }
  }

  public void shootBall() {
    if (Parameters.MAGAZINE_AVAILABLE) {
      magazine.set(Parameters.MAGAZINE_SHOOT_SPEED);
      --ballCount;
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
  // FIXME what is the difference between LoaderPower and FeedPower? is it the
  // motors?

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
  }
}
