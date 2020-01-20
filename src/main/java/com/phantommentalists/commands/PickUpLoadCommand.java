/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package com.phantommentalists.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import com.phantommentalists.subsystems.PickUp;

public class PickUpLoadCommand extends CommandBase {
  /**
   * Creates a new PickUpLoadCommand.
   */
  PickUp pickUp;

  public PickUpLoadCommand() {
    // Use addRequirements() here to declare subsystem dependencies.
    pickUp = new PickUp();
    addRequirements(pickUp);
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    pickUp.turnOnRollers();
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
    pickUp.turnOffRollers();
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return false;
    //TODO what do I do with the isFinished?^^
  }
}