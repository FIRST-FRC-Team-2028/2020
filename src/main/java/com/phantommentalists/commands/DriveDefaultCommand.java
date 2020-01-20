/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package com.phantommentalists.commands;

import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj2.command.CommandBase;
import com.phantommentalists.Constants;
import com.phantommentalists.RobotContainer;
import com.phantommentalists.subsystems.Drive;

public class DriveDefaultCommand extends CommandBase {
  /**
   * Gets input from xbox controller to drive the robot.
   */
  private final Drive drive;
  private RobotContainer robotContainer;

  public DriveDefaultCommand(Drive drive, RobotContainer robotContainer) {
    // Use addRequirements() here to declare subsystem dependencies.
    this.drive = drive;
    this.robotContainer = robotContainer;
    addRequirements(drive);
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    XboxController xboxController = robotContainer.getXboxController();
    double left = xboxController.getRawAxis(Constants.LEFT_STICK);
    double right = xboxController.getRawAxis(Constants.RIGHT_STICK);
    if(Math.abs(left)< Constants.DRIVE_DEAD_BAND){
      left = 0;
    }
    if(Math.abs(right)< Constants.DRIVE_DEAD_BAND){
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
