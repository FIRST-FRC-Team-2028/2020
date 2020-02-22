package com.phantommentalists.commands;

import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.Filesystem;
import edu.wpi.first.wpilibj.controller.RamseteController;
import edu.wpi.first.wpilibj.trajectory.Trajectory;
import edu.wpi.first.wpilibj.trajectory.TrajectoryUtil;
import edu.wpi.first.wpilibj2.command.CommandBase;
import edu.wpi.first.wpilibj2.command.RamseteCommand;
import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;

import java.io.IOException;
import java.nio.file.Path;

import com.phantommentalists.OI;
import com.phantommentalists.subsystems.Drive;

/**
 * This builds a command that assumes the robot starts in the center of the field
 * stratling the initiation line. It drives towards the shield generator, turning to
 * pickup fuel cells and returns facing the outer port.
 */
public class AutonomousCenterPickupPowerCells {

    private final static String trajectoryJsonFile = "paths/QuestionMark.wpilib.json";

    public static SequentialCommandGroup createCommand(Drive drive)
    {
        Trajectory trajectory = null;
        try {
            Path trajectoryPath = Filesystem.getDeployDirectory().toPath().resolve(trajectoryJsonFile);
            trajectory = TrajectoryUtil.fromPathweaverJson(trajectoryPath);
        } catch (final IOException exception) {
            DriverStation.reportError("Unable to open trajectory file " + trajectoryJsonFile, exception.getStackTrace());
        }
        final RamseteCommand command = new RamseteCommand(
            trajectory,
            drive::getPose, 
            new RamseteController(2.0, 0.7),
            drive.getFeedForward(), 
            drive.getKinematics(), 
            drive::getWheelSpeeds, 
            drive.getLeftMotorController(),
            drive.getRightMotorController(), 
            drive::tankDrive, 
            drive);
        return command.andThen(() -> drive.stop());
    }
}