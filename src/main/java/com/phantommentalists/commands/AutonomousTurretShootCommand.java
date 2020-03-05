/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package com.phantommentalists.commands;

import com.phantommentalists.Parameters;
import com.phantommentalists.subsystems.Turret;

import edu.wpi.first.wpilibj2.command.CommandBase;

public class AutonomousTurretShootCommand extends CommandBase {
  private Turret turret;

  /**
   * Creates a new AutonomousTurretCommand.
   */
  public AutonomousTurretShootCommand(Turret t) {
    // Use addRequirements() here to declare subsystem dependencies.
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
    // FIXME: Place holder value, may need to find one that is better suited
    turret.setShooterSpeed(Parameters.TURRET_SHOOTER_SPEED);
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