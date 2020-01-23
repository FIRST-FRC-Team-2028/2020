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
 * Rotates to aim for the ports
 * Angles with the hood
 * Uses rollers to shoot power cells
 */
public class Turret extends SubsystemBase {
  //FIXME do I need to set these as private?
  CANSparkMax yaw;
  CANSparkMax pitch;
  CANSparkMax shooter;
  AutoMode mode;
  Timer timer;

  public Turret() {
    yaw = new CANSparkMax(Parameters.CANIDs.TURRET_YAW.getid(), MotorType.kBrushless);
    pitch = new CANSparkMax(Parameters.CANIDs.TURRET_PITCH.getid(), MotorType.kBrushless);
    shooter = new CANSparkMax(Parameters.CANIDs.TURRET_SHOOT.getid(), MotorType.kBrushless);
    //mode = new AutoMode();
    timer = new Timer();
  }

  // public void setYaw(AngleInDegrees) {
  //   if (Parameters.TURRET_AVAILABLE) {
  //     yaw.set();
  //     //FIXME AngleInDegrees
  //   }
  // }

  // public void setPitch(AngleInDegrees) {
  //   if (Parameters.TURRET_AVAILABLE) {
  //     pitch.set();
  //     //FIXME AngleInDegrees
  //   }
  // }

  public void setYawPower(double voltage) {
    if (Parameters.TURRET_AVAILABLE) {
      yaw.setVoltage(voltage);
    }
  }

  public void setPitchPower(double voltage) {
    if (Parameters.TURRET_AVAILABLE) {
      pitch.setVoltage(voltage);
    }
  }

  public void setShooterMode(AutoMode switchMode) {
    if (Parameters.TURRET_AVAILABLE) {
      mode = switchMode;
    }
  }

  public void setShooterPower(int outputVolts) {
    if (Parameters.TURRET_AVAILABLE) {
      shooter.setVoltage(outputVolts);
    }
  }

  public void setShooterSpeed(double speed) {
    if (Parameters.TURRET_AVAILABLE) {
      shooter.set(speed);
    }
  }
  public void getShooterSpeed() {
    //returns RPM
    if (Parameters.TURRET_AVAILABLE) {
      shooter.get();
    }
  }

  public boolean isShooterReady() {
    if (Parameters.TURRET_AVAILABLE) {
      if (timer.get() >= Parameters.SHOOTER_TIME) {
        return true;
      }
    }
    return false;
  }

  // public CANSparkMax getYaw() {
  //   if (Parameters.TURRET_AVAILABLE) {
  //     return yaw;
  //   }
  // }

  // public CANSparkMax getPitch() {
  //   if (Parameters.TURRET_AVAILABLE) {
  //     return pitch;
  //   }
  // }
  //FIXME is using the CANSparkmax good??


  @Override
  public void periodic() {
    // This method will be called once per scheduler run
  }
}
