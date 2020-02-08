/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package com.phantommentalists.commands;

import com.phantommentalists.OI;
import com.phantommentalists.subsystems.Magazine;

import edu.wpi.first.wpilibj2.command.CommandBase;

public class MagazineShootCommand extends CommandBase {
  private OI oi;
  private Magazine magazine;
  /**
   * Runs the magazine accelerator to send the power cells to the shooter
   * Assumes shooter is running up to speed and turret is in position
   */

  public MagazineShootCommand(OI o, Magazine m) {
    // Use addRequirements() here to declare subsystem dependencies.
    oi = o;
    magazine = m;
    addRequirements(magazine);
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    magazine.shootBall();
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    if (!oi.isShoot()) {
      return true;
    }
    else {
      return false;
    }
  }
}
