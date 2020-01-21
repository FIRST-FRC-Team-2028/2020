/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018-2019 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package com.phantommentalists;

/**
 * The Constants class provides a convenient place for teams to hold robot-wide
 * numerical or boolean constants. This class should not be used for any other
 * purpose. All constants should be declared globally (i.e. public static). Do
 * not put anything functional in this class.
 *
 * <p>
 * It is advised to statically import this class (or one of its inner classes)
 * wherever the constants are needed, to reduce verbosity. new parameters
 */
public final class Parameters {

    /**
     * Flag that tells the code systems exist
     */
    public static final boolean DRIVE_AVAILABLE = true;
    public static final boolean CAMERA_AVAILABLE = true;
    public static final boolean PICKUP_AVAILABLE = true;
    public static final boolean COMPRESSOR_AVAILABLE = true;
    public static final boolean GYRO_AVAILABLE = false;
    public static final boolean BUTTONBOX_AVAILABLE = true;

    /** Enum to hold all information about pneumatic solenoids */
    public enum PneumaticChannel {
        PICKUP_EXTEND(6), 
        PICKUP_RETRACT(7);

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
        DRIVE_LEFT_LEADER(10, false, true), 
        DRIVE_RIGHT_LEADER(20, true, true), 
        DRIVE_LEFT_FOLLOWER(11, false, false),
        DRIVE_RIGHT_FOLLOWER(21, true, false),
        ROLLER(40, false, false);

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

    public static final int PIXY_CHANNEL = 2;
    public static final int POWER_FOLLOWER_BUTTON = 2;
    public static final int LEFT_STICK = 5;
    public static final int RIGHT_STICK = 1;
    public static final double DRIVE_DEAD_BAND = 0.1;

    public static final double PICKUP_ROLLER_SPEED = 0.7;
    public static final int SOLENOID_EXTEND = 1;
    public static final int SOLENOID_RETRACT = 2;

}
