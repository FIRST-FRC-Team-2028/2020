/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package com.phantommentalists.commands;

import com.phantommentalists.OI;
import com.phantommentalists.Parameters;
import com.phantommentalists.subsystems.Magazine;

import edu.wpi.first.wpilibj2.command.CommandBase;

public class MagazineDefaultCommand extends CommandBase {
  private OI oi;
  private Magazine magazine;
  /**
   * Moves the power cells through the magazine
   */
  public MagazineDefaultCommand(OI o, Magazine m) {
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
    if (Parameters.MAGAZINE_AVAILABLE) {
     if (oi.isMagazineLoadUpButton()) {
        magazine.setLoader(-Parameters.MAGAZINE_LOAD_SPEED);
      } 
      else if (oi.isMagazineLoadDownButton()) {
        magazine.setLoader(Parameters.MAGAZINE_LOAD_SPEED);
      } 
      else {
        magazine.setLoader(0.0);
      } 

      if (oi.isShooterButtonPressed()) {
        magazine.shootBall();
      } else {
        magazine.stopShoot();
      }
    }
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return false;
  }
}
