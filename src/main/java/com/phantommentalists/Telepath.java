/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2018 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/
package com.phantommentalists;

import java.lang.reflect.Parameter;

import com.phantommentalists.commands.AutonomousCenterPickupPowerCells;
import com.phantommentalists.subsystems.Drive;
import com.phantommentalists.subsystems.Magazine;
import com.phantommentalists.subsystems.Turret;
import com.phantommentalists.subsystems.Pickup;
import com.phantommentalists.OI;

import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.I2C;
import edu.wpi.first.wpilibj.PowerDistributionPanel;
import edu.wpi.first.wpilibj.TimedRobot;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj.util.Color;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.CommandScheduler;
import edu.wpi.first.cameraserver.CameraServer;
import edu.wpi.first.wpilibj.Compressor;
import edu.wpi.first.wpilibj.Joystick;

import com.revrobotics.ColorMatch;
import com.revrobotics.ColorMatchResult;
import com.revrobotics.ColorSensorV3;

/**
 * The VM is configured to automatically run this class, and to call the
 * functions corresponding to each mode, as described in the TimedRobot
 * documentation. If you change the name of this class or the package after
 * creating this project, you must also update the build.gradle file in the
 * project.
 */
public class Telepath extends TimedRobot {
  private Command m_autonomousCommand;
  private OI m_oi;
  private Drive drive;
  private Turret turret;
  private Magazine magazine;
  private Pickup pickup;

  private Compressor compressor;
  // private DoubleSolenoid shifter
  private PowerDistributionPanel pdp;

  public Pickup getPickup() {
    return pickup;
  }

  public Magazine getMagazine() {
    return magazine;
  }

  /**
   * This function is run when the robot is first started up and should be used
   * for any initialization code.
   */
  @Override
  public void robotInit() {
    m_oi = new OI(this);
    // Instantiate our RobotContainer. This will perform all our button bindings,
    // and put our
    // autonomous chooser on the dashboard.
    if (Parameters.TURRET_AVAILABLE) {
      turret = new Turret();
    }
    if (Parameters.MAGAZINE_AVAILABLE) {
      magazine = new Magazine(m_oi);
    }
    if (Parameters.PICKUP_AVAILABLE) {
      pickup = new Pickup();
      // pickup = m_oi.getPickup();
    }
    // controlWord = FRCNetworkCommunicationsLibrary.HALGetControlWord();

    drive = m_oi.getDrive();

    if (Parameters.COMPRESSOR_AVAILABLE) {
      compressor = new Compressor(0);
      compressor.setClosedLoopControl(true);
      compressor.start();
    }

    pdp = new PowerDistributionPanel();


    CameraServer.getInstance().startAutomaticCapture();

  }

  /**
   * This function is called every robot packet, no matter the mode. Use this for
   * items like diagnostics that you want ran during disabled, autonomous,
   * teleoperated and test.
   * <p>
   * This runs after the mode specific periodic functions, but before LiveWindow
   * and SmartDashboard integrated updating.
   */
  @Override
  public void robotPeriodic() {
    // Runs the Scheduler. This is responsible for polling buttons, adding
    // newly-scheduled
    // commands, running already-scheduled commands, removing finished or
    // interrupted commands,
    // and running subsystem periodic() methods. This must be called from the
    // robot's periodic
    // block in order for anything in the Command-based framework to work.
    CommandScheduler.getInstance().run();
    if (Parameters.TURRET_AVAILABLE) {
      turret.periodic();
    }
    if (Parameters.DRIVE_AVAILABLE) {
      drive.periodic();
    }
    if (Parameters.MAGAZINE_AVAILABLE) {
      magazine.periodic();
    }
    SmartDashboard.putNumber("PDP #0", pdp.getCurrent(0));
  }

  /**
   * This function is called once each time the robot enters Disabled mode.
   */
  @Override
  public void disabledInit() {
    if (Parameters.MAGAZINE_AVAILABLE) {
      magazine.setBallHeldCount(0);
    }
  }

  @Override
  public void disabledPeriodic() {
  }

  /**
   * This autonomous runs the autonomous command selected by your
   * {@link RobotContainer} class.
   */
  @Override
  public void autonomousInit() {
    if (Parameters.MAGAZINE_AVAILABLE) {
      magazine.setBallHeldCount(3);
    }
    m_autonomousCommand = AutonomousCenterPickupPowerCells.createCommand(drive);
    if (Parameters.DRIVE_AVAILABLE) {
      drive.resetGyro();
    }
    // schedule the autonomous command (example)
    if (m_autonomousCommand != null) {
      m_autonomousCommand.schedule();
    }
  }

  /**
   * This function is called periodically during autonomous.
   */
  @Override
  public void autonomousPeriodic() {
    // Runs the Scheduler. This is responsible for polling buttons, adding
    // newly-scheduled
    // commands, running already-scheduled commands, removing finished or
    // interrupted commands,
    // and running subsystem periodic() methods. This must be called from the
    // robot's periodic
    // block in order for anything in the Command-based framework to work.
    CommandScheduler.getInstance().run();
  }

  @Override
  public void teleopInit() {
    // This makes sure that the autonomous stops running when
    // teleop starts running. If you want the autonomous to
    // continue until interrupted by another command, remove
    // this line or comment it out.
    if (m_autonomousCommand != null) {
      m_autonomousCommand.cancel();
    }
  }

  /**
   * This function is called periodically during operator control.
   */
  @Override
  public void teleopPeriodic() {

    if (Parameters.PICKUP_AVAILABLE) {
      Joystick stick = m_oi.getPilotStick();
      if (stick.getRawButton(5)) {
        pickup.turnOnRollers();
      }
      else {
        pickup.turnOffRollers();
      }
    }
    String gameData;
    gameData = DriverStation.getInstance().getGameSpecificMessage();
    if (gameData.length() > 0) {
      switch (gameData.charAt(0)) {
      case 'B':
        // Blue case code
        break;
      case 'G':
        // Green case code
        break;
      case 'R':
        // Red case code
        break;
      case 'Y':
        // Yellow case code
        break;
      default:
        // This is corrupt data
        break;
      }
    } else {
      // Code for no data received yet
    }

    // Runs the Scheduler. This is responsible for polling buttons, adding
    // newly-scheduled
    // commands, running already-scheduled commands, removing finished or
    // interrupted commands,
    // and running subsystem periodic() methods. This must be called from the
    // robot's periodic
    // block in order for anything in the Command-based framework to work.
    CommandScheduler.getInstance().run();
  }

  @Override
  public void testInit() {
    // Cancels all running commands at the start of test mode.
    CommandScheduler.getInstance().cancelAll();
  }

  /**
   * This function is called periodically during test mode.
   */
  @Override
  public void testPeriodic() {
  }
}