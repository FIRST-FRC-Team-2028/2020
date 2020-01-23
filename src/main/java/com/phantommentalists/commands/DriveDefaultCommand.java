/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package com.phantommentalists.commands;

import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj2.command.CommandBase;
import com.phantommentalists.Parameters;
import com.phantommentalists.OI;
import com.phantommentalists.subsystems.Drive;

/**
 * Gets input from xbox controller to drive the robot.
 */
public class DriveDefaultCommand extends CommandBase {
  private final Drive drive;
  private OI oi;

  public DriveDefaultCommand(Drive drive, OI oi) {
    // Use addRequirements() here to declare subsystem dependencies.\
    //FIXME why do we need to take drive and oi
    this.drive = drive;
    this.oi = oi;
    addRequirements(drive);
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    XboxController xboxController = oi.getXboxController();
    double left = xboxController.getRawAxis(Parameters.LEFT_STICK);
    double right = xboxController.getRawAxis(Parameters.RIGHT_STICK);
    if(Math.abs(left)< Parameters.DRIVE_DEAD_BAND){
      left = 0;
    }
    if(Math.abs(right)< Parameters.DRIVE_DEAD_BAND){
      right = 0;
    }
    drive.tankDrive(left, right);
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
    System.out.println("I'm stopped");
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return false;
  }
}
