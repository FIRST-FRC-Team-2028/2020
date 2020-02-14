/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018-2019 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package com.phantommentalists.commands;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.CommandBase;
import com.phantommentalists.OI;
import com.phantommentalists.Parameters;
import com.phantommentalists.Parameters.AutoMode;
import com.phantommentalists.subsystems.Turret;
import com.revrobotics.ControlType;

/**
 * This command manual moves the Turret Direction based on monitoring the
 * Co-Pilot buttons. It only moves the turret at a constant speed (%V-bus) It
 * does use the Pixy Camera or input from the Gyro. This is the default command
 * for the Turret
 */
public class TurretDefaultCommand extends CommandBase {
  private OI oi;
  private Turret turret;
  private double speed;

  public TurretDefaultCommand(OI o, Turret t) {
    oi = o;
    turret = t;
    addRequirements(turret);
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {

    if (oi.isTurretAutoButton()) {
      turret.setAimMode(Parameters.AutoMode.AUTO);
    }

    

    
    // Turret Direction controls   *****************************************/
    speed = Parameters.TURRET_MANUAL_SPEED_FAST;

    if (oi.isTurretDirectionFineMoveButton())
      speed = Parameters.TURRET_MANUAL_SPEED_SLOW;

    if (oi.isTurretDirectionMoveRightButton()) {
      turret.setDirectionPower(speed);
    } else if (oi.isTurretDirectionMoveLeftButton()) {
      turret.setDirectionPower(-speed);
    } else {
      turret.setDirectionPower(0.0);
    }

    // Turret Hood controls   *****************************************/
    // FIXME insert manual button methods for hood

    if (oi.isTurretHoodClose()) {
      turret.setHoodPosition(Parameters.TURRET_HOOD_CLOSE);
    } else if (oi.isTurretHoodMedium()) {
      turret.setHoodPosition(Parameters.TURRET_HOOD_MEDIUM);
    } else if (oi.isTurretHoodFar()) {
      turret.setHoodPosition(Parameters.TURRET_HOOD_FAR);
    }

    // Turret Shooter controls   *****************************************/
    if (oi.isShooterButtonPressed()) {
      // turret.getShooterController().setReference(3000, ControlType.kVelocity);
      turret.setShooterSpeed(Parameters.TURRET_SHOOTER_SPEED);
    } else {
      turret.setShooterPower(0.0);
      // Turret_Shooter_Controller.setReference(0, ControlType.kVelocity);
    }

    if (oi.isTestButton()) {
      turret.setDirectionHome();
    }
  }


  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {

  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return false;
  }

}
