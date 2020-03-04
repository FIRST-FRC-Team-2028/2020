/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package com.phantommentalists.commands;

import com.phantommentalists.subsystems.Drive;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj2.command.CommandBase;

public class AutonomousDriveCommand extends CommandBase {
  private Drive drive;
  private double driveTimeSec;
  private Timer timer;
  /**
   * Drives backwards for 2 seconds then stops.
   * Mainly for getting off the initiation line.
   */
  public AutonomousDriveCommand(Drive d) {
    drive = d;
    driveTimeSec = 2.0;
    timer = new Timer();
    addRequirements(d);
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
    timer.reset();
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    timer.start();
    // Should reverse it?
    drive.tankDrive(-2.5, 2.5);
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
    drive.tankDrive(0.0, 0.0);
    timer.stop();
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    if (timer.get() > driveTimeSec) {
      return true;
    }
    else {
      return false;
    }
  }
}
