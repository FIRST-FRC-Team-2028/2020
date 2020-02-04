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

/**
 * Turns off rollers Retracts arm
 */
public class PickUpRetractCommand extends CommandBase {
  private PickUp pickUp;
  private Timer timer;

  public PickUpRetractCommand() {
    // Use addRequirements() here to declare subsystem dependencies.
    pickUp = new PickUp();
    timer = new Timer();
    addRequirements(pickUp);
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
    timer.reset();
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    if (Parameters.PICKUP_AVAILABLE) {
      timer.start();
      pickUp.turnOffRollers();
      pickUp.retract();
    }
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
    timer.stop();
    pickUp.turnOffRollers();
    pickUp.turnArmoff();
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    if (Parameters.PICKUP_AVAILABLE) {
      return pickUp.isPickUpRetracted();
    } else
      return false;
  }
}