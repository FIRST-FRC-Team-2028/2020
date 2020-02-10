/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package com.phantommentalists.subsystems;

import com.phantommentalists.OI;
import com.phantommentalists.Parameters;
import com.phantommentalists.Parameters.AutoMode;
import com.phantommentalists.commands.TurretDefaultCommand;
import com.revrobotics.CANAnalog;
import com.revrobotics.CANEncoder;
import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMax.IdleMode;
import com.revrobotics.CANSparkMax.SoftLimitDirection;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;
import com.revrobotics.ControlType;
import com.revrobotics.CANError;
import com.revrobotics.CANPIDController;

import edu.wpi.first.wpilibj.controller.PIDController;
import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

/**
 * Rotates to aim for the ports Angles with the hood Uses rollers to shoot power
 * cells
 */
public class Turret extends SubsystemBase {
  private CANSparkMax direction;
  private CANSparkMax hood;
  private CANSparkMax shooter;
  private AutoMode mode;
  private Timer timer;

  
  private CANEncoder directionEncoder;
  private double directionInput;
  private PIDController directionController;

  private CANEncoder hoodEncoder; //FIXME: Are we using an encoder
  private double hoodInput; //FIXME: are we using an input

  private CANEncoder shooterEncoder;
  private CANPIDController shooterController;


  public Turret() {
    if (Parameters.TURRET_AVAILABLE) {
      direction = new CANSparkMax(Parameters.CANIDs.TURRET_DIRECTION.getid(), MotorType.kBrushless);
      direction.setIdleMode(IdleMode.kBrake);
      directionController = new PIDController(Parameters.PID.TURRET_DIRECTION.getP(), Parameters.PID.TURRET_DIRECTION.getI(), Parameters.PID.TURRET_DIRECTION.getD());

      directionEncoder = direction.getEncoder();
      //FIXME: what does "setPositionConversionFactor"
      directionEncoder.setPositionConversionFactor(Parameters.TURRET_DIRECTION_POS_CONVERSION_FACTOR);
      direction.setSoftLimit(SoftLimitDirection.kForward, Parameters.TURRET_DIRECTION_FWD_LIMIT);
      direction.setSoftLimit(SoftLimitDirection.kReverse, Parameters.TURRET_DIRECTION_REV_LIMIT);
      direction.enableSoftLimit(SoftLimitDirection.kForward, true);
      direction.enableSoftLimit(SoftLimitDirection.kReverse, true);

      // hood = new CANSparkMax(Parameters.CANIDs.TURRET_HOOD.getid(), MotorType.kBrushless);
      
      shooter = new CANSparkMax(Parameters.CANIDs.TURRET_SHOOTER.getid(), MotorType.kBrushless);
      
      shooter.restoreFactoryDefaults();
      shooter.setIdleMode(IdleMode.kCoast);

      shooterController = shooter.getPIDController();
      shooterController.setP(Parameters.PID.TURRET_SHOOTER_SPEED.getP());
      shooterController.setI(Parameters.PID.TURRET_SHOOTER_SPEED.getI());
      shooterController.setD(Parameters.PID.TURRET_SHOOTER_SPEED.getD());

      shooterController.setIZone(Parameters.kLZ_SHOOTER);
      shooterController.setFF(Parameters.kFF_SHOOTER);
      shooterController.setOutputRange(Parameters.kMINOUTPUT_SHOOTER, Parameters.kMAXOUTPUT_SHOOTER);

      shooterEncoder = shooter.getEncoder();
    
      mode = AutoMode.MANUAL;
    }
  }

  // Direction methods *****************************************/
  /**
   * Sets the direction of the turret. It is aimed with the I2C pixy cam
   * the pixy cam has a resolution of 320 x 240 pixels. A target to the right
   * of center will have positive value 1 - 160. A target to the left of center
   * will have a value of -1 - -159.
   * 
   * @param pixels - Number of pixels from the center of the camera's view
   * 
   */

  public void setDirectionHome() {
    directionEncoder.setPosition(0);
  }
  
  public void setDirection(double pixels) {
    if (Parameters.TURRET_AVAILABLE) {
      directionInput = pixels;
      mode = AutoMode.AUTO;
    }
  }

  /**
   * Sets the amount of power for direction
   * @param voltage
   */
  public void setDirectionPower(double voltage) {
    if (Parameters.TURRET_AVAILABLE) {
      direction.setVoltage(voltage);
      mode = AutoMode.MANUAL;
    }
  }

  public double getDirection() {
    return directionEncoder.getPosition();
  }

  // Hood methods *****************************************/
  
  //FIXME: Methods are probably doing the wrong thing currently, need to be coordinated with the buttons on controller
  /**
   * Sets the hood of the turret
   * ^ v
   */
  public void setHood(double angleInDegrees) {
    if (Parameters.TURRET_AVAILABLE) {
      hoodInput = angleInDegrees;
      mode = AutoMode.AUTO;
    }
  }

  /**
   * Sets the amount of power used for hood
   * @param voltage
   */
  public void setHoodPower(double voltage) {
    if (Parameters.TURRET_AVAILABLE) {
      hood.setVoltage(voltage);
      mode = AutoMode.MANUAL;
    }
  }

  public double getHood() {
    return hoodEncoder.getPosition();
  }

  // Aim Enum  *****************************************/
  /**
   * Sets aim mode of hood and direction to either Manual or Auto
   * @param switchMode - sets to mode in enum AutoMode
   */
  public void setAimMode(AutoMode switchMode) {
    if (Parameters.TURRET_AVAILABLE) {
      mode = switchMode;
    }
  }

  // Shooter methods  *****************************************/
  /**
   * Sets the power to the shooter, the wheels
   * @param outputVolts - amount of power you want to use
   */
  public void setShooterPower(double outputVolts) {
    if (Parameters.TURRET_AVAILABLE) {
      shooter.setVoltage(outputVolts);
    }
  }

  /**
   * Sets the speed for the shooter, wheels
   * @param speed - speed you want to set the shooter to
   */
  public void setShooterSpeed(double rpm) {
    if (Parameters.TURRET_AVAILABLE) {
      shooterController.setReference(rpm, ControlType.kVelocity);
    }
  }

  /**
   * Retrieves the speed of the shooter
   * 
   * @return double - speed of shooter in RPM
   */
  public double getShooterSpeed() {
    if (Parameters.TURRET_AVAILABLE) {
      return shooterEncoder.getVelocity();
    }
    else{
      return 0.0;
    }
  }

  public CANPIDController getShooterController() {
    return shooterController;
  }

  /**
   * Determines if the shooter is ready to shoot
   * another ball, by using a timer, to restore 
   * the necessary speed
   * @return
   */
  public boolean isShooterReady() {
    // if (Parameters.TURRET_AVAILABLE) {
    //   if (timer.get() >= Parameters.SHOOTER_TIME) {
    //     return true;
    //   }
    // }
    return false;
  }

 

  // public void getHood() {
  //   if (Parameters.TURRET_AVAILABLE) {
  //     hood.get();
  //   }
  // }
  // FIXME is using the CANSparkmax good for getting the Hood and Direction?

  /**
   * This method will be called once per scheduler run
   */
  @Override
  public void periodic() {
    if (mode == Parameters.AutoMode.AUTO) {
      double directionPower = directionController.calculate(directionInput, Parameters.TURRET_POSITION_SETPOINT);
      direction.set(directionPower);
    }
    SmartDashboard.putNumber("Shooter RPM", getShooterSpeed());
    SmartDashboard.putNumber("Direction Pos:", getDirection());
  }  
  
  /**
  * sets the default command
  */
 public void initDefaultCommand(OI oi) {
   if (Parameters.TURRET_AVAILABLE) {
     setDefaultCommand(new TurretDefaultCommand(oi, this));
   }
 }
}