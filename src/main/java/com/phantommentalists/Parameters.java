/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018-2019 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package com.phantommentalists;

import edu.wpi.first.wpilibj.SPI.Port;

/**
 * The Constants class provides a convenient place for teams to hold robot-wide
 * numerical or boolean constants. This class should not be used for any other
 * purpose. All constants should be declared globally (i.e. public static). Do
 * not put anything functional in this class.
 *
 * <p>
 * It is advised to statically import this class (or one of its inner classes)
 * wherever the constants are needed, to reduce verbosity.
 */
public final class Parameters {

    /**
     * Flag that tells the code systems exist
     */
    public static final boolean DRIVE_AVAILABLE = true;
    public static final boolean CAMERA_AVAILABLE = true;
    public static final boolean PICKUP_AVAILABLE = false;
    public static final boolean TURRET_AVAILABLE = true;
    public static final boolean MAGAZINE_AVAILABLE = false;
    public static final boolean CLIMBER_AVAILABLE = false;
    public static final boolean CONTROLPANEL_AVAILABLE = false;
    public static final boolean AIM_AVAILABLE = false; // FIXME use camera_available?
    public static final boolean COMPRESSOR_AVAILABLE = false;
    public static final boolean GYRO_AVAILABLE = false;
    public static final boolean BUTTONBOX_AVAILABLE = false;

    /** Enum to hold all information about pneumatic solenoids */
    public enum PneumaticChannel {
        PICKUP_EXTEND(6), PICKUP_RETRACT(7);

        private final int channel;

        private PneumaticChannel(final int ch) {
            channel = ch;
        }

        public int getChannel() {
            return channel;
        }
    }

    /**
     * Enum to hold all information about devices on the CAN bus
     */
    public enum CANIDs {
        DRIVE_LEFT_LEADER(10, false, true), DRIVE_RIGHT_LEADER(20, true, true), DRIVE_LEFT_FOLLOWER(11, false, false),
        DRIVE_RIGHT_FOLLOWER(21, true, false), TURRET_YAW(30, false, false), TURRET_PITCH(31, false, false),
        TURRET_SHOOT(32, false, false), ROLLER(40, false, false), MAGAZINE(41, false, false),
        ACCELERATOR(42, false, false), CLIMB_RIGHT(50, false, false), CLIMB_LEFT(51, false, false),
        CONTROL_PANEL(60, false, false), SPARE(28, false, false);

        private final int canid;
        private final boolean inverted;
        private final boolean leader;

        CANIDs(final int id, final boolean inverted, final boolean leader) {
            this.canid = id;
            this.inverted = inverted;
            this.leader = leader;
        }

        public int getid() {
            return canid;
        }

        public boolean isFollower() {
            return !leader;
        }

        public boolean isInverted() {
            return inverted;
        }
    }

    public enum AutoMode {
        AUTO, MANUAL;
    }

    public enum Gear {
        LOW, HIGH;
    }

    public enum Mode {
        DONT_MOVE, SCAN, FACE_FORWARD;
    }

    public static final int PIXY_CHANNEL = 2;
    public static final int POWER_FOLLOWER_BUTTON = 2;
    public static final int LEFT_STICK = 5;
    public static final int RIGHT_STICK = 1;
    public static final double DRIVE_DEAD_BAND = 0.1;

    public static final double PICKUP_ROLLER_SPEED = 0.7;
    public static final double MAGAZINE_LOAD_SPEED = 0.5;
    public static final double MAGAZINE_SHOOT_SPEED = 1;
    public static final int SOLENOID_EXTEND = 1;
    public static final int SOLENOID_RETRACT = 2;

    // public static final double PICKUP_TIME = 0.5;
    public static final double EXTEND_TIME = 0.5;
    public static final double RETRACT_TIME = 0.5;
    public static final double SHOOTER_TIME = 0.7;

    /**
     * Distance between wheels measured inside to inside in inches
     */
    public static final double DRIVE_TRACK_WIDTH = 28.0;
    public static final double DRIVE_KS = 0.0;
    public static final double DRIVE_KV = 0.0;
    public static final double DRIVE_KP = 0.0;
    public static final double DRIVE_KI = 0.0;
    public static final double DRIVE_KD = 0.0;
    public static final double DRIVE_LEFT_GEAR_RATIO = 0.0;
    public static final double DRIVE_RIGHT_GEAR_RATIO = 0.0;
    public static final double DRIVE_WHEEL_DIAMETER = 0.0;
    // FIXME fill in/ fix all of the values

    public static final Port TURRET_GYRO_PORT = Port.kMXP;
}