/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package com.phantommentalists.commands;

import com.phantommentalists.OI;
import com.phantommentalists.subsystems.Drive;
import com.phantommentalists.subsystems.Magazine;
import com.phantommentalists.subsystems.Turret;

import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;

/**
 * Main autonomous command for shooting our loaded powercells 
 * and then driving off the initiation line.
 */
public class AutonomousCommandGroup extends SequentialCommandGroup {
  /**
   * Creates a new Autonomous.
   */
  public AutonomousCommandGroup(Turret t, OI o, Magazine m, Drive d) {
    // Add your commands in the super() call, e.g.
    // super(new FooCommand(), new BarCommand());
    super(new AutonomousShootCommandGroup(t, o, m), new AutonomousDriveCommand(d));
  }
}
