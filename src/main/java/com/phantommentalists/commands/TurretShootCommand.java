/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package com.phantommentalists.commands;

import com.phantommentalists.Parameters;
import com.phantommentalists.subsystems.Magazine;
import com.phantommentalists.subsystems.Turret;

import edu.wpi.first.wpilibj2.command.CommandBase;

/**
 * switch name to ShootCommand?
 */
public class TurretShootCommand extends CommandBase {
  private Turret turret;
  private Magazine magazine;

  public TurretShootCommand() {
    // Use addRequirements() here to declare subsystem dependencies.
    addRequirements(turret);
    addRequirements(magazine);
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
  }

  // Called every time the scheduler runs while the command is scheduled. FIXME: is this right?
  @Override
  public void execute() {
    if (turret.isShooterReady()) {
    magazine.shootBall();
    turret.setPitchPower(Parameters.PITCH_POWER);
    turret.setYawPower(Parameters.YAWPOWER);
    turret.setShooterPower(Parameters.SHOOTERPOWER);
    turret.setShooterSpeed(Parameters.SHOOTER_SPEED);
    }
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
    //stop everything and possibly compare ball counts to ensure that the method ran
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return false;
  }
}
