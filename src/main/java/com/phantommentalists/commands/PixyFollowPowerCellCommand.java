/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package com.phantommentalists.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import com.phantommentalists.subsystems.Drive;
import com.phantommentalists.OI;
import com.phantommentalists.PixyAnalog;

/**
 * Use data from pixycam to point the robot at a power cell.
 */
public class PixyFollowPowerCellCommand extends CommandBase {
  private double pixyValue;
  private double speed;
  private Drive drive;
  private double pixyModifier = .5;
  private double window = 0.1;
  private double inWindowCount;
  private double windowCountVariable = 5.;
  private PixyAnalog pixyCam;
  private OI oi;

  public PixyFollowPowerCellCommand(Drive drive, PixyAnalog pixyCam, OI o) {
    // do I see a pixy cam
    this.drive = drive;
    this.pixyCam = pixyCam;
    oi = o;
    addRequirements(drive);
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
    // TODO can I see a ball
    // if (pixyCam.isAquired() == false)
    // {
    // inWindowCount = 5.0;
    // }
    // inWindowCount = 0.0;
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    double driveAdjust = 0.0;
    /*----------------------------------------------------------------------------*/
    /* DriveAdjust steers the robot toward a Power Cell */
    /*----------------------------------------------------------------------------*/

    driveAdjust = drive.follow_Ball_Controller.calculate(drive.getPixyAnalog().getAverageVoltage(), (3.3 / 2.0));
    drive.executeDrive(driveAdjust);
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {

  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    // value reaches 0, return true
    if (!oi.GetFollowFuelCellButton()) {
      return true;
    }
    return false;
  }
}