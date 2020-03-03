/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package com.phantommentalists.commands;

import edu.wpi.first.wpilibj.Timer;

import com.phantommentalists.Parameters;
import com.phantommentalists.Parameters.PickupPos;
import com.phantommentalists.subsystems.Pickup;

import edu.wpi.first.wpilibj2.command.CommandBase;

public class PickupDefaultCommand extends CommandBase {
  private Pickup pickup;
  private Timer timer;

  /**
   * Creates a new PickupDefaultCommand.
   */
<<<<<<< HEAD:src/main/java/com/phantommentalists/commands/PickupExtendCommand.java
  public PickupExtendCommand(Pickup p) {
    // Use addRequirements() here to declare subsystem dependencies.
    pickup = p;
    timer = new Timer();
=======
  public PickupDefaultCommand(OI o, Pickup p) {
    // Use addRequirements() here to declare subsystem dependencies.
    pickup = p;
    oi = o;
    //timer = new Timer();
>>>>>>> 8aa860fc3a674ceb10c080d23f4537102fc37f3d:src/main/java/com/phantommentalists/commands/PickupDefaultCommand.java
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
<<<<<<< HEAD:src/main/java/com/phantommentalists/commands/PickupExtendCommand.java
    timer.start();
    if (timer.get() > Parameters.EXTEND_TIME) {
      pickup.extend();
=======
    // timer.start();
    if (oi.isPickupButton()) {
      if (pickup.position == Parameters.PickupPos.RETRACT) {
        pickup.extend();
        pickup.position = Parameters.PickupPos.EXTEND;
      } else if (pickup.position == Parameters.PickupPos.EXTEND) {
        pickup.retract();
        pickup.position = Parameters.PickupPos.RETRACT;
      }
>>>>>>> 8aa860fc3a674ceb10c080d23f4537102fc37f3d:src/main/java/com/phantommentalists/commands/PickupDefaultCommand.java
    }
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
<<<<<<< HEAD:src/main/java/com/phantommentalists/commands/PickupExtendCommand.java
    timer.stop();
=======
    //timer.stop();
    // pickup.position = Parameters.PickupPos.EXTEND;
    //pickup.retract();
>>>>>>> 8aa860fc3a674ceb10c080d23f4537102fc37f3d:src/main/java/com/phantommentalists/commands/PickupDefaultCommand.java
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
<<<<<<< HEAD:src/main/java/com/phantommentalists/commands/PickupExtendCommand.java
    if (Parameters.PICKUP_AVAILABLE) {
      return pickup.isPickUpExtended();
    } 
    else {
=======
    // if (Parameters.PICKUP_AVAILABLE) {
    //   return pickup.isPickUpExtended();
    // } else {
    //   return false;
    // }
    // if (!oi.isPickupButton()) {
    //   return true;
    // } else {
>>>>>>> 8aa860fc3a674ceb10c080d23f4537102fc37f3d:src/main/java/com/phantommentalists/commands/PickupDefaultCommand.java
      return false;
    // }
  }
}
