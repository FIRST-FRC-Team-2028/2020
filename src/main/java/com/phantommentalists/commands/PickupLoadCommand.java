/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package com.phantommentalists.commands;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj2.command.CommandBase;

import com.phantommentalists.OI;
import com.phantommentalists.Parameters;
import com.phantommentalists.subsystems.Pickup;

/**
 * Extends PickUp and turns on rollers to get the power cell off the floor
 * FIXME hold button till you think its done for manual mode, timer for auto. use selector/swtich for that
 */
public class PickupLoadCommand extends CommandBase {
  Pickup pickup;
  Timer timer;
  OI oi;

  public PickupLoadCommand(OI o, Pickup p) {
    // Use addRequirements() here to declare subsystem dependencies.
    oi = o;
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
      pickup.extend();
      pickup.turnOnRollers();
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
     return pickup.isPickUpExtended();
    } else {
      return false;
    }
  }
}