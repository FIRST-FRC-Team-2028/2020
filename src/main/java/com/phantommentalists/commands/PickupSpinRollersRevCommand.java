/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package com.phantommentalists.commands;

// import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj2.command.CommandBase;

import com.phantommentalists.OI;
import com.phantommentalists.Parameters;
import com.phantommentalists.subsystems.Pickup;

/**
 * Spins the pickup wheels in reverse when the magazine down button is pressed
 * and pickup is extended
 */
public class PickupSpinRollersRevCommand extends CommandBase {
  Pickup pickup;
  //Timer timer;
  OI oi;

  public PickupSpinRollersRevCommand(OI o, Pickup p) {
    // Use addRequirements() here to declare subsystem dependencies.
    oi = o;
    pickup = p;
    //timer = new Timer();
    //addRequirements(pickup);
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
    // timer.reset();
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    if (Parameters.PICKUP_AVAILABLE) {
      //timer.start();
      //pickup.extend();
      //pickup.turnOnRollers();

      //THIS WORKS
      pickup.turnOnRollersRev();

      // This code is set up like this is a default command
      // if (oi.isPickupButton()) {
      //   pickup.turnOnRollers();
      // } 
      // else {
      //   pickup.turnOffRollers();
      // }
    }
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
    //timer.stop();
    pickup.turnOffRollers();
    //pickup.stopArm();
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    //THIS WORKS
    // if (Parameters.PICKUP_AVAILABLE) {
    //   return pickup.isPickUpExtended();
    // } else {
    //   return false;
    // }

    return !oi.isRollerButtonRev();

    // if (!oi.isPickupButton()){
    //   return true;
    // } else {
    //   return false;
    // }
  }
}