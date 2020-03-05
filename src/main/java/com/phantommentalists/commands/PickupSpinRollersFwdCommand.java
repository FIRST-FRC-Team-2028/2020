/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package com.phantommentalists.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;

import com.phantommentalists.OI;
import com.phantommentalists.Parameters;
import com.phantommentalists.subsystems.Pickup;

/**
 * Spins the pickup wheels forward when the magazine up button is pressed and
 * pickup is extended
 */
public class PickupSpinRollersFwdCommand extends CommandBase {
  private OI oi;
  private Pickup pickup;

  public PickupSpinRollersFwdCommand(OI o, Pickup p) {
    // Use addRequirements() here to declare subsystem dependencies.
    oi = o;
    pickup = p;
    // addRequirements(pickup);
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    if (Parameters.PICKUP_AVAILABLE) {
      pickup.turnOnRollersFwd();
    }
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
    if (Parameters.PICKUP_AVAILABLE) {
      pickup.turnOffRollers();
    }
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return !oi.isRollerButtonFwd();
  }
}