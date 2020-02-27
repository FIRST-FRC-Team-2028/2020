/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018-2019 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package com.phantommentalists;

import edu.wpi.first.wpilibj.GenericHID;
import edu.wpi.first.wpilibj.Joystick;

// import edu.wpi.first.wpilibj.XboxController;
// import com.phantommentalists.commands.DriveDefaultCommand;
// import com.phantommentalists.commands.DriveSpinCommand;
import com.phantommentalists.commands.DrivePixyFollowPowerCellCommand;
import com.phantommentalists.commands.MagazineShootCommand;
import com.phantommentalists.commands.PickupLoadCommand;
import com.phantommentalists.subsystems.Drive;
import com.phantommentalists.subsystems.Magazine;
import com.phantommentalists.subsystems.Pickup;

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
  private final Magazine magazine;
  private final Pickup pickup;
  // private PixyAnalog frontPixy = new PixyAnalog(Parameters.PIXY_CHANNEL);
  private final Command m_autoCommand = null;  
  // DriveDefaultCommand();
  private final Telepath robot;

  // private XboxController xboxController;
  private Joystick pilotJoystick, copilotJoystick1, copilotJoystick2;

  /**
   * The container for the robot. Contains subsystems, OI devices, and commands.
   */
  public OI(Telepath r) {
    // Configure the button bindings
    robot = r;
    if (Parameters.DRIVE_AVAILABLE) {
      drive = new Drive(this);
      drive.initDefaultCommand();
    }

    if (Parameters.MAGAZINE_AVAILABLE) {
      magazine = new Magazine(this);
      //magazine = robot.getMagazine();
      magazine.initDefaultCommand();    // FIXME    ***** uncomment the magazine available stuff when it works
    }

    pickup = new Pickup(); //FIXME: Why does this work but robot.getPickup() doesn't?
    //pickup = robot.getPickup(); 

    pilotJoystick = new Joystick(Parameters.USB_STICK_PILOT);
    copilotJoystick1 = new Joystick(Parameters.USB_STICK_COPILOT1);
    copilotJoystick2 = new Joystick(Parameters.USB_STICK_COPILOT2);

    //FIXME How is powerCellCam going to be used

    configureButtonBindings();
  }

  /**
   * Use this method to define your button->command mappings. Buttons can be
   * created by instantiating a {@link GenericHID} or one of its subclasses
   * ({@link edu.wpi.first.wpilibj.Joystick} or {@link XboxController}), and then
   * passing it to a {@link edu.wpi.first.wpilibj2.command.button.JoystickButton}.
   */
  private void configureButtonBindings() {

    // xboxController = new XboxController(1);

    //PixyFollowPowerCell
    JoystickButton pilotStickPowerCellFollowButton = new JoystickButton(pilotJoystick, Parameters.PILOT_JOYSTICK_FOLLOW_POWER_CELL_BUTTON);
    pilotStickPowerCellFollowButton.whenPressed(new DrivePixyFollowPowerCellCommand(drive, drive.getDrivePixy(), this));
    JoystickButton copilotStickPowerCellFollowButton = new JoystickButton(copilotJoystick1, Parameters.COPILOT1_JOYSTICK_FOLLOW_POWER_CELL_BUTTON);
    copilotStickPowerCellFollowButton.whenPressed(new DrivePixyFollowPowerCellCommand(drive, drive.getDrivePixy(), this));
    
    //Shoot
    JoystickButton copilotStickShoot = new JoystickButton(copilotJoystick1, Parameters.COPILOT1_SHOOT);
    copilotStickShoot.whenPressed(new MagazineShootCommand(this, magazine));

    //Pickup
    JoystickButton copilotStickPickupExtend = new JoystickButton(copilotJoystick1, Parameters.COPILOT1_PICKUP);
    copilotStickPickupExtend.whileHeld(new PickupLoadCommand(this, pickup));

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
  /*  Allows both Pilot and CoPilot to select "Follow Power Cell"               */
  /*----------------------------------------------------------------------------*/
  
  public boolean isFollowPowerCellButton() {
    return pilotJoystick.getRawButton(Parameters.PILOT_JOYSTICK_FOLLOW_POWER_CELL_BUTTON);
  }

  public boolean isTurretAutoButton() {
    return copilotJoystick1.getRawButtonPressed(Parameters.COPILOT1_FIND_TARGET);
  }

  public boolean isTurretDirectionMoveRightButton() {
    return copilotJoystick1.getRawButton(Parameters.COPILOT1_JOYSTICK_TURRET_RIGHT);
  }

  public boolean isTurretDirectionMoveLeftButton() {
    return copilotJoystick1.getRawButton(Parameters.COPILOT1_JOYSTICK_TURRET_LEFT);
  }

  public boolean isTurretDirectionFineMoveButton() {
    return copilotJoystick1.getRawButton(Parameters.COPILOT1_TURRET_FINE_ADJUST);
  }

  public boolean isTurretHoodClose() {
    return copilotJoystick1.getRawButton(Parameters.COPILOT1_HOOD_CLOSE);
  }

  public boolean isTurretHoodMedium() {
    return copilotJoystick2.getRawButton(Parameters.COPILOT2_HOOD_MEDIUM);
  }
  
  public boolean isTurretHoodFar() {
    return copilotJoystick1.getRawButton(Parameters.COPILOT1_HOOD_FAR);
  }

  public boolean isShooterButtonPressed() {
    return copilotJoystick1.getRawButton(Parameters.COPILOT1_SHOOT);
  }

  public boolean isMagazineLoadUpButton() {
    return copilotJoystick2.getRawButton(Parameters.COPILOT2_MAGAZINE_UP);
  }

  public boolean isMagazineLoadDownButton() {
    return copilotJoystick1.getRawButton(Parameters.COPILOT1_MAGAZINE_DOWN);
  }

  public boolean isShoot() {
    return copilotJoystick1.getRawButton(Parameters.COPILOT1_SHOOT); 
  }


  public boolean isHighGearButton() {
    return pilotJoystick.getRawButton(Parameters.PILOT_BUTTON_1);
  }

  public boolean isTestButton() {
    return pilotJoystick.getRawButton(Parameters.PILOT_BUTTON_3);
  }

  public Drive getDrive() {
    return drive;
  }

  public boolean isPickupButton() {
    return copilotJoystick1.getRawButton(Parameters.COPILOT1_PICKUP);
  }

  public boolean isClimb() {
    return copilotJoystick2.getRawButton(Parameters.COPILOT2_CLIMB);
  }

  /*
  // FIXME: Remove after testing is done
  public Pickup getPickup() {
    return pickup;
  }
  */
  

  // public XboxController getXboxController() {
  // return xboxController;
  // }
}