/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018-2019 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package com.phantommentalists.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import com.phantommentalists.OI;
import com.phantommentalists.Parameters;
import com.phantommentalists.subsystems.Turret;

/**
 * This command manual moves the Turret Direction based on monitoring the
 * Co-Pilot buttons. It only moves the turret at a constant speed (%V-bus) It
 * does use the Pixy Camera or input from the Gyro. This is the default command
 * for the Turret
 */
public class ManualTurretMoveCommand extends CommandBase {
  private OI oi;
  private Turret turret;
  private double speed;

  public ManualTurretMoveCommand(OI o, Turret t) {
    oi = o;
    turret = t;
    addRequirements(turret);
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {

    speed = Parameters.TURRET_MANUAL_SPEED_FAST;

    if (oi.isTurretFineMoveButton())
      speed = Parameters.TURRET_MANUAL_SPEED_SLOW;

    if (oi.isTurretMoveRightButton()) {
      turret.setYawPower(speed);
    } else if (oi.isTurretMoveLeftButton()) {
      turret.setYawPower(-speed);
    } else {
      turret.setYawPower(0.0);
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
