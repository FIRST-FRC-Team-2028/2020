/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package com.phantommentalists.commands;

//import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj2.command.CommandBase;
import com.phantommentalists.Parameters;
import com.phantommentalists.Parameters.Gear;
import com.phantommentalists.OI;
import com.phantommentalists.subsystems.Drive;

/**
 * Gets input from xbox controller to drive the robot.
 */
public class DriveDefaultCommand extends CommandBase {
  private Drive drive;
  private OI oi;

  public DriveDefaultCommand(Drive d, OI o) {
    // Use addRequirements() here to declare subsystem dependencies.
    drive = d;
    oi = o;
    addRequirements(drive);
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    // XboxController xboxController = oi.getXboxController();
    // double left = xboxController.getRawAxis(Parameters.LEFT_STICK);
    // double right = xboxController.getRawAxis(Parameters.RIGHT_STICK);
    // if (Math.abs(left) < Parameters.DRIVE_DEAD_BAND) {
    //   left = 0;
    // }
    // if (Math.abs(right) < Parameters.DRIVE_DEAD_BAND) {
    //   right = 0;
    // }
    // drive.tankDrive(left, right);
    double Y = oi.getPilotStick().getY();
    double X = oi.getPilotStick().getX();

    // "Exponential" drive, where the movements are more sensitive during slow
    // movement, permitting easier fine control
    // X = Math.pow(X, 3); /// FIXME take a look here does it make a
    // Y = Math.pow(Y, 3); /// difference

    if (Math.abs(Y) <= Parameters.DRIVE_DEAD_BAND) {
      Y = 0;
    }
    if (Math.abs(X) <= Parameters.DRIVE_DEAD_BAND) {
      X = 0;
    }
    double V = ((1.0 - Math.abs(X)) * Y) + Y;
    double W = ((1.0 - Math.abs(Y)) * X) + X;
    double R = (V + W) / 4; ///// Should be divide by 2 for full power
    double L = (V - W) / 4; ///// FIXME

    /*----------------------------------------------------------------------------*/
    /* Shifts to High Gear on Pilot Trigger */
    /*----------------------------------------------------------------------------*/
    if (oi.GetHighGearButton()) {
      drive.setGear(Gear.HIGH);
    } else {
      drive.setGear(Gear.LOW);
    }

    /*----------------------------------------------------------------------------*/
    /* DriveAdjust steers the robot toward a Power Cell */
    /*----------------------------------------------------------------------------*/
    // if (oi.GetFollowFuelCellButton()) {
    //   DriveAdjust = follow_Ball_Controller.calculate(m_Pixy_Analog.getAverageVoltage(), (3.3 / 2.0));
    // } else {
    //   DriveAdjust = 0.0;
    // }

    drive.tankDrive(R * 12.0, L * 12.0);
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
