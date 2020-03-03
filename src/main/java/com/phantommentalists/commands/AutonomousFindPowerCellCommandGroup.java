/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package com.phantommentalists.commands;

import com.phantommentalists.DrivePixy;
import com.phantommentalists.OI;
import com.phantommentalists.subsystems.Drive;
import com.phantommentalists.subsystems.Pickup;

import edu.wpi.first.wpilibj2.command.ParallelCommandGroup;

// NOTE:  Consider using this command inline, rather than writing a subclass.  For more
// information, see:
// https://docs.wpilib.org/en/latest/docs/software/commandbased/convenience-features.html
public class AutonomousFindPowerCellCommandGroup extends ParallelCommandGroup {
  /**
   * Command Group for finding and then loading a powercell.
   */
  public AutonomousFindPowerCellCommandGroup(Drive drive, DrivePixy pixyCam, OI o, Pickup p) {
    // Add your commands in the super() call, e.g.
    super(new DrivePixyFollowPowerCellCommand(drive, pixyCam, o), new LoadPowercellCommandGroup(p, o));
  }
}
