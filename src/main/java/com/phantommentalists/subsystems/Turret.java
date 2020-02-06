/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package com.phantommentalists.subsystems;

import com.phantommentalists.Parameters;
import com.phantommentalists.Parameters.AutoMode;
import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;

import edu.wpi.first.wpilibj.ADXRS450_Gyro;
import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

/**
 * Rotates to aim for the ports Angles with the hood Uses rollers to shoot power
 * cells
 */
public class Turret extends SubsystemBase {
  private CANSparkMax turret;
  private CANSparkMax hood;
  private CANSparkMax shooter;
  private AutoMode mode;
  private Timer timer;
  private ADXRS450_Gyro yawGyro;
  //FIXME Find out what gyro would be best and what we have. Do we need 2 for pitch and yaw or can we use one?

  public Turret() {
    if (Parameters.TURRET_AVAILABLE) {
      turret = new CANSparkMax(Parameters.CANIDs.TURRET_DIRECTION.getid(), MotorType.kBrushless);
      hood = new CANSparkMax(Parameters.CANIDs.TURRET_HOOD.getid(), MotorType.kBrushless);
      shooter = new CANSparkMax(Parameters.CANIDs.TURRET_SHOOTER.getid(), MotorType.kBrushless);
      timer = new Timer();
      yawGyro = new ADXRS450_Gyro();
    }
  }

  /**
   * Sets the amount of rotation in the turret
   * <- ->
   */
  public void setYaw(double AngleInDegrees) {
    if (Parameters.TURRET_AVAILABLE) {
      
    }
  }

  /**
   * Sets the amount of up down in the turret
   * ^ v
   */
  public void setPitch(double AngleInDegrees) {
    if (Parameters.TURRET_AVAILABLE) {
      
    }
  }

  /**
   * Sets the amount of power for Yaw
   * @param voltage
   */
  public void setYawPower(double voltage) {
    if (Parameters.TURRET_AVAILABLE) {
      turret.setVoltage(voltage);
    }
  }

  /**
   * Sets the amount of power used for pitch
   * @param voltage
   */
  public void setPitchPower(double voltage) {
    if (Parameters.TURRET_AVAILABLE) {
      hood.setVoltage(voltage);
    }
  }

  /**
   * Sets shooter to either Manual or Auto
   * @param switchMode - sets to mode in enum AutoMode
   */
  public void setShooterMode(AutoMode switchMode) {
    if (Parameters.TURRET_AVAILABLE) {
      mode = switchMode;
    }
  }

  /**
   * Sets the power to the shooter, the wheels
   * @param outputVolts - amount of power you want to use
   */
  public void setShooterPower(int outputVolts) {
    if (Parameters.TURRET_AVAILABLE) {
      shooter.setVoltage(outputVolts);
    }
  }

  /**
   * Sets the speed for the shooter, wheels
   * @param speed - speed you want to set the shooter to
   */
  public void setShooterSpeed(double speed) {
    if (Parameters.TURRET_AVAILABLE) {
      shooter.set(speed);
    }
  }

  /**
   * Retrieves the speed of the shooter
   */
  public double getShooterSpeed() {
    // returns RPM
    if (Parameters.TURRET_AVAILABLE) {
      return shooter.get();
    }
    return 0.0;
  }

  /**
   * Determines if the shooter is ready to shoot
   * another ball, by using a timer, to restore 
   * the necessary speed
   * @return
   */
  public boolean isShooterReady() {
    if (Parameters.TURRET_AVAILABLE) {
      if (timer.get() >= Parameters.SHOOTER_TIME) {
        return true;
      }
    }
    return false;
  }

  // public double getYaw() {
  //   //return yawGyro.getAngle();
  // }

  // public void getPitch() {
  //   if (Parameters.TURRET_AVAILABLE) {
  //     pitch.get();
  //   }
  // }
  // FIXME is using the CANSparkmax good for getting the Pitch and Yaw?

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
  }
}