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
import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

/**
 * Rotates to aim for the ports Angles with the hood Uses rollers to shoot power
 * cells
 */
public class Turret extends SubsystemBase {
  // FIXME do I need to set the emun as private
  private CANSparkMax yaw;
  private CANSparkMax pitch;
  private CANSparkMax shooter;
  AutoMode mode;
  private Timer timer;
  //FIXME Find out what gyro would be best

  public Turret() {
    if (Parameters.TURRET_AVAILABLE) {
      yaw = new CANSparkMax(Parameters.CANIDs.TURRET_DIRECTION.getid(), MotorType.kBrushless);
      pitch = new CANSparkMax(Parameters.CANIDs.TURRET_HOOD.getid(), MotorType.kBrushless);
      shooter = new CANSparkMax(Parameters.CANIDs.TURRET_SHOOTER.getid(), MotorType.kBrushless);
      timer = new Timer();
    }
  }

  /**
   * Sets the amount of rotation in the turret
   * <- ->
   */
  public void setYaw(double AngleInDegrees) {
    if (Parameters.TURRET_AVAILABLE) {
      yaw.set(AngleInDegrees);
    }
  }

  /**
   * Sets the amount of up down in the turret
   * ^ v
   */
  public void setPitch(double AngleInDegrees) {
    if (Parameters.TURRET_AVAILABLE) {
      pitch.set(AngleInDegrees);
    }
  }

  /**
   * Sets the amount of power for Yaw
   * @param voltage
   */
  public void setYawPower(double voltage) {
    if (Parameters.TURRET_AVAILABLE) {
      yaw.setVoltage(voltage);
    }
  }

  /**
   * Sets the amount of power used for pitch
   * @param voltage
   */
  public void setPitchPower(double voltage) {
    if (Parameters.TURRET_AVAILABLE) {
      pitch.setVoltage(voltage);
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
  //FIXME void for get do I need to put return
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



  // public void getYaw() {
  //   if (Parameters.TURRET_AVAILABLE) {
  //     yaw.get();
  //   }
  // }

  // public void getPitch() {
  //   if (Parameters.TURRET_AVAILABLE) {
  //     pitch.get();
  //   }
  // }
  // FIXME is using the CANSparkmax good??

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
  }
}
