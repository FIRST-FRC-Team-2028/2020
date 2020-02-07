/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018-2019 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package com.phantommentalists;

import edu.wpi.first.wpilibj.GenericHID;
import edu.wpi.first.wpilibj.Joystick;
// //import edu.wpi.first.wpilibj.XboxController;
// import com.phantommentalists.commands.DriveDefaultCommand;
// import com.phantommentalists.commands.DriveSpinCommand;
import com.phantommentalists.commands.PixyFollowPowerCellCommand;
import com.phantommentalists.subsystems.Drive;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.button.JoystickButton;

/**
 * This class is where the bulk of the robot should be declared. Since
 * Command-based is a "declarative" paradigm, very little robot logic should
 * actually be handled in the {@link Robot} periodic methods (other than the
 * scheduler calls). Instead, the structure of the robot (including subsystems,
 * commands, and button mappings) should be declared here.
 */
public class OI {
  // The robot's subsystems and commands are defined here...
  private final Drive drive;
  // private PixyAnalog frontPixy = new PixyAnalog(Parameters.PIXY_CHANNEL);

  private final Command m_autoCommand = null;  
  // DriveDefaultCommand();

  // private XboxController xboxController;
  private Joystick pilotJoystick, copilotJoystick1, copilotJoystick2;

  /**
   * The container for the robot. Contains subsystems, OI devices, and commands.
   */
  public OI() {
    // Configure the button bindings
    if (Parameters.DRIVE_AVAILABLE) {
      drive = new Drive(this);
      drive.initDefaultCommand();
    }
    pilotJoystick = new Joystick(Parameters.USB_STICK_PILOT);
    copilotJoystick1 = new Joystick(Parameters.USB_STICK_COPILOT1);
    copilotJoystick2 = new Joystick(Parameters.USB_STICK_COPILOT2);

    //FIXME How is fuelCellCam going to be used

    configureButtonBindings();

    // drive.setDefaultCommand(driveDefaultCommand);
    // FIXME why is this set in the Oi?
  }

  /**
   * Use this method to define your button->command mappings. Buttons can be
   * created by instantiating a {@link GenericHID} or one of its subclasses
   * ({@link edu.wpi.first.wpilibj.Joystick} or {@link XboxController}), and then
   * passing it to a {@link edu.wpi.first.wpilibj2.command.button.JoystickButton}.
   */
  private void configureButtonBindings() {

    // xboxController = new XboxController(1);
     JoystickButton pilotStickPowerCellFollowButton = new JoystickButton(pilotJoystick, Parameters.PILOT_JOYSTICK_FOLLOW_POWER_CELL_BUTTON);
     pilotStickPowerCellFollowButton.whenPressed(new PixyFollowPowerCellCommand(drive, drive.getPixyAnalog(), this));
     JoystickButton copilotStickPowerCellFollowButton = new JoystickButton(copilotJoystick1, Parameters.COPILOT1_JOYSTICK_FOLLOW_POWER_CELL_BUTTON);
     copilotStickPowerCellFollowButton.whenPressed(new PixyFollowPowerCellCommand(drive, drive.getPixyAnalog(), this));
  
    }

  /**
   * Use this to pass the autonomous command to the main {@link Robot} class.
   *
   * @return the command to run in autonomous
   */
  public Command getAutonomousCommand() {
    // An ExampleCommand will run in autonomous
    return m_autoCommand;
  }

  public Joystick getPilotStick() {
    return pilotJoystick;
  }

  public Joystick getCoPilotStick1() {
    return copilotJoystick1;
  }

  public Joystick getCoPilotStick2() {
    return copilotJoystick2;
  }

  /*----------------------------------------------------------------------------*/
  /*  Allows both Pilot and CoPilot to select "Follow Fuel Cell"           */
  /*----------------------------------------------------------------------------*/
  public boolean isFollowFuelCellButton() {
    boolean Temp = false;
    if (copilotJoystick1.getRawButton(Parameters.COPILOT1_JOYSTICK_FOLLOW_POWER_CELL_BUTTON)) {
      Temp = true;
    }
    if (pilotJoystick.getRawButton(Parameters.PILOT_JOYSTICK_FOLLOW_POWER_CELL_BUTTON)) {
      Temp = true;
    }
    return Temp;
  }


  public boolean isTurretMoveRightButton() {
    boolean Temp = false;
    if (copilotJoystick1.getRawButton(Parameters.COPILOT1_JOYSTICK_TURRET_RIGHT)) {
      Temp = true;
    }
    return Temp;
  }

  public boolean isTurretMoveLeftButton() {
    boolean Temp = false;
    if (copilotJoystick1.getRawButton(Parameters.COPILOT1_JOYSTICK_TURRET_LEFT)) {
      Temp = true;
    }
    return Temp;
  }


  public boolean isHighGearButton() {
    return pilotJoystick.getRawButton(Parameters.Pilot_Button_1);   ///FIXME   will need to be changed from but 3
  }

  public boolean isTestButton() {
    return pilotJoystick.getRawButton(Parameters.Pilot_Button_3);   ///FIXME   will need to be changed from but 3
  }

  public Drive getDrive() {
    return drive;
  }

  // public XboxController getXboxController() {
  // return xboxController;
  // }
}
