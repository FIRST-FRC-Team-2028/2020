/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package com.phantommentalists.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import com.phantommentalists.subsystems.Drive;
import com.phantommentalists.PixyCam;

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
  private PixyCam pixyCam;

  public PixyFollowPowerCellCommand(Drive drive, PixyCam pixyCam) {
    //do I see a pixy cam
    this.drive = drive;
    this.pixyCam = pixyCam;
    addRequirements(drive);
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
    //can I see a ball
    if (pixyCam.isAquired()==false) inWindowCount = 5.;
    inWindowCount = 0.;
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    //take input from pixy cam
    pixyValue = pixyCam.getX();
    //determine direction of rotation
    //spin the robot; set speed
    if (java.lang.Math.abs(pixyValue) <= window){
      speed = 0.;
      inWindowCount+=1;
    } else {
      speed = pixyValue*pixyModifier;
      inWindowCount = 0.;
    }
    drive.spin(speed);
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
    
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    //value reaches 0, return true
    if (inWindowCount >= windowCountVariable) return true;
    return false;
  }
}