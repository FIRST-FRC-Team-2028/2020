/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package com.phantommentalists.commands;

import com.phantommentalists.subsystems.Climber;

import edu.wpi.first.wpilibj2.command.CommandBase;

public class ClimbCommand extends CommandBase {
  private Climber climber;
  /**
   * Creates a new ClimbCommand.
   */
  public ClimbCommand(Climber c) {
    climber = c;
    // Use addRequirements() here to declare subsystem dependencies.
    addRequirements(climber);
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
    climber.releaseArm();
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    climber.climbUp();
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
    climber.stopClimbing();
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return false;
  }
}