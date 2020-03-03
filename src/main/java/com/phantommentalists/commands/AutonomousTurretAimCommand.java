/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package com.phantommentalists.commands;

import com.phantommentalists.OI;
import com.phantommentalists.subsystems.Turret;

import edu.wpi.first.wpilibj2.command.CommandBase;

public class AutonomousTurretAimCommand extends CommandBase {
  private Turret turret;
  private OI oi;
  /**
   * Creates a new AutonomousTurretAimCommand.
   */
  public AutonomousTurretAimCommand(OI o, Turret t) {
    // Use addRequirements() here to declare subsystem dependencies.
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
    turret.getTurretTarget();
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
