/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package com.phantommentalists.commands;

import com.phantommentalists.Parameters;
import com.phantommentalists.TurretPixyPacket;
import com.phantommentalists.subsystems.Turret;
import com.revrobotics.CANPIDController;
import com.revrobotics.ControlType;

import edu.wpi.first.wpilibj2.command.CommandBase;

public class AutonomousTurretAimCommand extends CommandBase {
  private Turret turret;
  private TurretPixyPacket target;
  private double input;
  private CANPIDController pidController;

  /**
   * Creates a new AutonomousTurretAimCommand.
   */
  public AutonomousTurretAimCommand(Turret t) {
    // Use addRequirements() here to declare subsystem dependencies.
    turret = t;
    pidController = turret.getDirectionController();
    addRequirements(turret);
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    target = turret.getTurretTarget();
    input = target.X;
    double currentPos = turret.getDirection();
    double setPoint = (Parameters.TURRET_DIRECTION_SETPOINT - input) * 10.5 + currentPos;
    if (turret.mode == Parameters.AutoMode.AUTO) {
      pidController.setReference(setPoint, ControlType.kPosition);
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