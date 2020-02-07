/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package com.phantommentalists.commands;

import com.phantommentalists.OI;
import com.phantommentalists.Parameters;
import com.phantommentalists.subsystems.Magazine;
import com.phantommentalists.subsystems.Turret;

import edu.wpi.first.wpilibj2.command.CommandBase;

public class ShootCommand extends CommandBase {
  private OI oi;
  private Turret turret;
  private Magazine magazine;
  /**
   * Creates a new Shoot.
   */
  public ShootCommand(OI o, Turret t, Magazine m) {
    // Use addRequirements() here to declare subsystem dependencies.
    oi = o;
    turret = t;
    magazine = m;
    addRequirements(turret);
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    turret.setShooterPower(Parameters.SHOOTER_VOLTAGE);
    magazine.shootBall();
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    if (!oi.isShoot()) {
      return true;
    }
    else {
      return false;
    }
  }
}
