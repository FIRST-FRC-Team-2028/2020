/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package com.phantommentalists.commands;

import com.phantommentalists.Parameters;
import com.phantommentalists.subsystems.PickUp;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj2.command.CommandBase;

public class PickUpRetractCommand extends CommandBase {
  /**
   * Creates a new PickUpRetractCommand.
   */

   PickUp pickUp;
   Timer timer;
  
  public PickUpRetractCommand() {
    // Use addRequirements() here to declare subsystem dependencies.
    pickUp = new PickUp();
    timer = new Timer();
    addRequirements(pickUp);
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    if (Parameters.PICKUP_AVAILABLE) {
      pickUp.turnOffRollers();
      pickUp.retract();
    }
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {

  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    //if (Parameters.PICKUP_AVAILABLE) {
    //  return true;
    //}
    //else return false;
    return false;
    //FIXME how do you return true??
    //use timer
  }
}
