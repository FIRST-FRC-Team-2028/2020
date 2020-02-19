/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package com.phantommentalists.commands;

import com.phantommentalists.Parameters;
import com.phantommentalists.subsystems.Pickup;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj2.command.CommandBase;

/**
 * Turns off rollers and retracts arm
 */
public class PickUpRetractCommand extends CommandBase {
  private Pickup pickup;
  private Timer timer;

  public PickUpRetractCommand() {
    // Use addRequirements() here to declare subsystem dependencies.
    pickup = new Pickup();
    timer = new Timer();
    addRequirements(pickup);
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
      pickup.turnOffRollers();
      pickup.retract();
    }
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
    timer.stop();
    pickup.turnOffRollers();
    pickup.stopArm();
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    if (Parameters.PICKUP_AVAILABLE) {
      return pickup.isPickUpRetracted();
    } else {
      return false;
    }
  }
}