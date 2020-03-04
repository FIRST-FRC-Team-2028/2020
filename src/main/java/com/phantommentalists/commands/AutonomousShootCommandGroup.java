/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package com.phantommentalists.commands;

import com.phantommentalists.OI;
import com.phantommentalists.subsystems.Magazine;
import com.phantommentalists.subsystems.Turret;

import edu.wpi.first.wpilibj2.command.ParallelCommandGroup;
import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;

// NOTE:  Consider using this command inline, rather than writing a subclass.  For more
// information, see:
// https://docs.wpilib.org/en/latest/docs/software/commandbased/convenience-features.html
public class AutonomousShootCommandGroup extends SequentialCommandGroup {
  /**
   * Creates a new AutonomousShootCommandGroup.
   */
  public AutonomousShootCommandGroup(Turret t, OI o, Magazine m) {
    // Add your commands in the super() call, e.g.
    // super(new FooCommand(), new BarCommand());super();
    super(new AutonomousTurretAimCommand(t), new ParallelCommandGroup(new AutonomousTurretShootCommand(t), new MagazineShootCommand(o, m)));
  }
}
