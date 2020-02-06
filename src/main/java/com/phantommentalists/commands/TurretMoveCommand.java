/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018-2019 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package com.phantommentalists.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import com.phantommentalists.subsystems.Drive;
import com.phantommentalists.OI;
import com.phantommentalists.subsystems.Turret;

public class TurretMoveCommand extends CommandBase {
    private OI oi;
    private Turret turret;


    public TurretMoveCommand(OI o) {
        
    
    
    }


 // Called when the command is initially scheduled.
 @Override
 public void initialize() {
   // TODO can I see a ball
   // if (pixyCam.isAquired() == false)
   // {
   // inWindowCount = 5.0;
   // }
   // inWindowCount = 0.0;
 }

 // Called every time the scheduler runs while the command is scheduled.
 @Override
 public void execute() {
   turret.setTurretPower(6.0);
   /*----------------------------------------------------------------------------*/
   /* DriveAdjust steers the robot toward a Power Cell */
  
 }

 // Called once the command ends or is interrupted.
 @Override
 public void end(boolean interrupted) {

 }

 // Returns true when the command should end.
 @Override
 public boolean isFinished() {
   // value reaches 0, return true
   if (!oi.GetTurretMoveRight()) {
     return true;
   }
   return false;
 }




}



