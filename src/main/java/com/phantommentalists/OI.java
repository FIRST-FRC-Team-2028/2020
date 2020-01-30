/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018-2019 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package com.phantommentalists;

import edu.wpi.first.wpilibj.GenericHID;
import edu.wpi.first.wpilibj.Joystick;
//import edu.wpi.first.wpilibj.XboxController;
import com.phantommentalists.commands.DriveDefaultCommand;
import com.phantommentalists.commands.DriveSpinCommand;
import com.phantommentalists.subsystems.Drive;
//import com.phantommentalists.PixyCam;
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
  private final Drive drive = new Drive();
  // private PixyCam frontPixy = new PixyCam(Parameters.PIXY_CHANNEL);

  private final DriveSpinCommand m_autoCommand = new DriveSpinCommand(drive);
 // private final DriveDefaultCommand driveDefaultCommand = new DriveDefaultCommand();

  //private XboxController xboxController;
  private Joystick pilotJoystick;

  /**
   * The container for the robot. Contains subsystems, OI devices, and commands.
   */
  public OI() {
    // Configure the button bindings

    pilotJoystick = new Joystick(Parameters.USB_STICK_PILOT);

    configureButtonBindings();
 //   drive.setDefaultCommand(driveDefaultCommand);
    // FIXME why is this set in the Oi?
  }

  /**
   * Use this method to define your button->command mappings. Buttons can be
   * created by instantiating a {@link GenericHID} or one of its subclasses
   * ({@link edu.wpi.first.wpilibj.Joystick} or {@link XboxController}), and then
   * passing it to a {@link edu.wpi.first.wpilibj2.command.button.JoystickButton}.
   */
  private void configureButtonBindings() {

  //  xboxController = new XboxController(1);
  //  JoystickButton exampleButton = new JoystickButton(xboxController, 1);
  //  exampleButton.whenHeld(new DriveSpinCommand(drive));
    // JoystickButton powerFollowButton = new JoystickButton(xboxController,
    // Parameters.POWER_FOLLOWER_BUTTON);
    // powerFollowButton.whenpressed(new PixyFollowPowerCellCommand(drive,
    // frontPixy));

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

   public Joystick getPilotStick()
   {
    return pilotJoystick;
   }

  // public XboxController getXboxController() {
  //   return xboxController;
  // }
}
