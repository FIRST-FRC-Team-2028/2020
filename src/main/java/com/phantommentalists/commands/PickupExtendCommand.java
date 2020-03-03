/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package com.phantommentalists.commands;

import edu.wpi.first.wpilibj.Timer;

import com.phantommentalists.OI;
import com.phantommentalists.Parameters;
import com.phantommentalists.subsystems.Pickup;

import edu.wpi.first.wpilibj2.command.CommandBase;

public class PickupExtendCommand extends CommandBase {
  private Pickup pickup;
  private Timer timer;
  private OI oi;

  /**
   * Creates a new PickupExtendCommand.
   */
  public PickupExtendCommand(OI o, Pickup p) {
    // Use addRequirements() here to declare subsystem dependencies.
    pickup = p;
    oi = o;
    //timer = new Timer();
    addRequirements(pickup);
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
    //timer.reset();
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    //timer.start();
    pickup.extend();

  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
    //timer.stop();
    // pickup.position = Parameters.PickupPos.EXTEND;
    pickup.retract();
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    // if (Parameters.PICKUP_AVAILABLE) {
    //   return pickup.isPickUpExtended();
    // } else {
    //   return false;
    // }
    if (!oi.isPickupButton()) {
      return true;
    } else {
      return false;
    }
  }
}
