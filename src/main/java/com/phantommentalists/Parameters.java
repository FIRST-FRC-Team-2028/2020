/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018-2019 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package com.phantommentalists;

import edu.wpi.first.wpilibj.SPI;

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
    public static final boolean DRIVE_AVAILABLE         = true;
    public static final boolean CAMERA_AVAILABLE        = false;
    public static final boolean AIM_AVAILABLE           = false;
    public static final boolean TURRET_AVAILABLE        = true;
    public static final boolean MAGAZINE_AVAILABLE      = true;
    public static final boolean PICKUP_AVAILABLE        = true;
    public static final boolean KICKER_AVAILABLE        = false;
    public static final boolean CLIMBER_AVAILABLE       = false;
    public static final boolean CONTROLPANEL_AVAILABLE  = false;
    public static final boolean COMPRESSOR_AVAILABLE    = true;
    public static final boolean GYRO_AVAILABLE          = false;
    public static final boolean BUTTONBOX_AVAILABLE     = false;

    /** Enum to hold all information about pneumatic solenoids */
    public enum PneumaticChannel {
        DRIVE_LOW_GEAR(0),
        DRIVE_HIGH_GEAR(1),
        PICKUP_EXTEND(3),
        PICKUP_RETRACT(2),
        OPEN_ACCELERATOR_FLAPS(4),
        CLOSE_ACCELERATOR_FLAPS(5);

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
        DRIVE_LEFT_LEADER    (20, true, true), 
        DRIVE_LEFT_FOLLOWER  (21, true, false),
        DRIVE_RIGHT_LEADER   (10, false, true), 
        DRIVE_RIGHT_FOLLOWER (11, false, false), 

        TURRET_DIRECTION     (30, false, false), //30
        TURRET_HOOD          (31, false, false),
        TURRET_SHOOTER       (32, false, false),

        PICKUP               (40, true, false), //40
        KICKER               (43, false, false),
        MAGAZINE             (41, false, false),
        ACCELERATOR          (42, false, false), 

        CLIMB_RIGHT          (50, true, false),
        CLIMB_LEFT           (51, false, false),

        CONTROL_PANEL        (60, false, false), 

        SPARE                (28, false, false);

      
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

    /**
     * Used to set the mode of the turret
     */
    public enum AutoMode {
        AUTO, 
        MANUAL;
    }

    /**
     * Switches the gears of the drive
     */
    public enum Gear {
        LOW, 
        HIGH;
    }

    /**
     * Used in Aim to setNoTargetMode
     */
    public enum Mode {
        DONT_MOVE, 
        SCAN, 
        FACE_FORWARD;
    }

    public enum SmartPID {
        TURRET_DIRECTION(0.005, 0.9e-5, 0.0, 0.0, 0.0, -2.0, 2.0, 0.0, 0.0, 0.0, 0.0),
        TURRET_HOOD(0.005, 1.1e-5, 0.0, 0.0, 0.0, -1.0, 1.0, 0.0, 0.0, 0.0, 0.0);

        private double p, i, d, iz, ff, minOut, maxOut, maxVel, minVel, maxAcc, allowedErr;

        private SmartPID(double newP, double newI, double newD, double newIz, double newFF, double newMinOut, double newMaxOut, double newMaxVel, double newMinVel, double newMaxAcc, double newAllowedErr) {
            p = newP;
            i = newI;
            d = newD;
            iz = newIz;
            ff = newFF;
            minOut = newMinOut;
            maxOut = newMaxOut;
            maxVel = newMaxVel;
            minVel = newMinVel;
            maxAcc = newMaxAcc;
            allowedErr = newAllowedErr;
        }

        public double getP() {
            return p;
        }
        
        public double getI() {
            return i;
        }

        public double getD() {
            return d;
        }
        
        public double getIz() {
            return iz;
        }

        public double getFF() {
            return ff;
        }

        public double getMinOut() {
            return minOut;
        }

        public double getMaxOut() {
            return maxOut;
        }

        public double getMaxVel() {
            return maxVel;
        }

        public double getMinVel() {
            return minVel;
        }

        public double getMaxAcc() {
            return maxAcc;
        }

        public double getAllowedErr() {
            return allowedErr;
        }
    }

    public enum PID {
        //TURRET_DIRECTION(0, 0, 0),
        TURRET_SHOOTER_SPEED(5e-5, 1e-6, 5e-5),
        TURRET_HOOD(0.005, 1.1e-5, 0.0),
        DRIVE_LEFT(0.259, 0, 0),
        DRIVE_RIGHT(0.259, 0, 0);

        private double p, i, d;

        private PID(double newP, double newI, double newD) {
            p = newP;
            i = newI;
            d = newD;
        }

        public double getP() {
            return p;
        }
        
        public double getI() {
            return i;
        }

        public double getD() {
            return d;
        }
    }
    /*----------------------------------------------------------------------------*/ 
    /*  Pixy Camera on front of used to find Power Cells                          */
    /*----------------------------------------------------------------------------*/ 
    public static final int PIXY_ANALOG_CHANNEL = 0;

    //Drive
    public static final int USB_STICK_PILOT = 0;
    public static final int USB_STICK_COPILOT1 = 2;
    public static final int USB_STICK_COPILOT2 = 3;

    public static final double DRIVE_DEAD_BAND = 0.1;

    //Speed/Voltage for subsystems
    public static final double PICKUP_ROLLER_SPEED = 0.4;
    public static final double KICKER_SPEED = 0.5;
    public static final double MAGAZINE_LOAD_SPEED = 0.3;
    public static final double MAGAZINE_SHOOT_SPEED = 0.3;

     //used for climber currently not being used
     public static final int SOLENOID_EXTEND = 1;
     public static final int SOLENOID_RETRACT = 2;
     public static final double CLIMBER_REEL_SPEED = 1.0;

     // public static final double PICKUP_TIME = 0.5;
     public static final double EXTEND_TIME = 0.5;
     public static final double RETRACT_TIME = 0.5;
     public static final double SHOOTER_TIME = 0.7;

     //Speeds for Turret Direction
     public static final double TURRET_MANUAL_SPEED_FAST = 3.5;
     public static final double TURRET_MANUAL_SPEED_SLOW = 1.5;

     public static final double TURRET_DIRECTION_SETPOINT = 160.0;

     // direction encoder = 42 counts per rev, 64:1 gearbox ratio, 92 teeth on turret, 18 teeth on motor
     // 92 / 18 * 64 * 42 = 13739 counts per turret rev, / 360 deg = 38.2 counts / deg
     // camera in X = 320 pixels, 75 deg
     // 320 / 75 = 4.27 pixels / deg
     // 38.2 (counts / deg) / 4.27 (pixels / deg) = 8.9 counts / pixel (direction)
     public static final double TURRET_DIRECTION_COUNTS_PER_PIXEL = 8.9;

     public static final float TURRET_DIRECTION_FWD_LIMIT = 125.0f;
     public static final float TURRET_DIRECTION_REV_LIMIT = -125.0f;
     public static final double TURRET_DIRECTION_POS_CONVERSION_FACTOR = 1.0;

     //Hood positions
     public static final double TURRET_HOOD_CLOSE = 200.0;
     public static final double TURRET_HOOD_MEDIUM = 100.0;
     public static final double TURRET_HOOD_FAR = 50.0;
     public static final double TURRET_HOOD_VOLTAGE = 0.4;

     public static final double TURRET_HOOD_MIDPOINT = 100.0;

     // hood encoder = 42 counts per rev, 64:1 (subject to change) gearbox ratio, teeth ratio is 1-to-1
     // (42 / 42 = 1) * 64 * 42 = 2688 counts per timing belt rev (hood) / 360 deg = 7.47 counts / deg
     // camera in Y = 200 pixels, 47 deg
     // 200 / 47 = 4.26 pixels / deg
     // 7.47 (counts / deg) / 4.26 (pixels / deg) = 1.8 counts / pixel
     public static final double TURRET_HOOD_COUNTS_PER_PIXEL = 1.8;

     public static final double TURRET_HOOD_INIT_POWER = 1.0;
     public static final double TURRET_HOOD_CURRENT_LIMIT = 1.0;

     public static final float TURRET_HOOD_FWD_LIMIT = 1000.0f;
     public static final float TURRET_HOOD_REV_LIMIT = -1000.0f;

     //Speed for Shooter
     public static final double TURRET_SHOOTER_SPEED = 2000.0;
     public static final double TURRET_SHOOTER_SPEED_CLOSE = 4700.0;
     public static final double TURRET_SHOOTER_SPEED_MEDIUM = 5200.0;
     public static final double TURRET_SHOOTER_SPEED_FAR = 5700.0;
     

     /** Distance between wheels measured inside to inside in inches  */
    public static final double DRIVE_TRACK_WIDTH = 24.50;
    public static final double DRIVE_KS = 0.142;
    public static final double DRIVE_KV = 0.0589;
    public static final double DRIVE_KA = 0.00575;
    public static final double DRIVE_LEFT_GEAR_RATIO = 1.0/6.2;    // 6.2:1 Low  14.09:1 High for competition chassis
    public static final double DRIVE_RIGHT_GEAR_RATIO = 1.0/6.2;   // 6.2:1 Low  14.09:1 High for competition chassis
    //public static final double DRIVE_LEFT_GEAR_RATIO = 1.0/8.333;    // 8.333 Low     3.667 High for 2 CIM Ball Shifter
    //public static final double DRIVE_RIGHT_GEAR_RATIO = 1.0/8.333;
    public static final double DRIVE_WHEEL_DIAMETER = 6.0;
    // FIXME fill in/ fix all of the values

    //used in Turret sets port for gyro
    public static final SPI.Port CHASSIS_GYRO_PORT = SPI.Port.kOnboardCS0;

    //subsystem PIDs
    public static final double kP_Turret_Camera             =  0.005;    // was 0.005
    public static final double kI_Turret_Camera             =  0.0008;   // was 0.0008
    public static final double kD_Turret_Camera             =  0.0;      //

    public static final double klz_Turret_Position          =  0.0;      //
    public static final double kFF_Turret_Position          =  0.0;      //
    public static final double kMaxOutput_Turret_Position   =  1.0;      //
    public static final double kMinOutput_Turret_Position   = -1.0;      //
 
    public static final double kLZ_SHOOTER                  =  0.0;      //
    public static final double kFF_SHOOTER                  =  0.0;      //
    public static final double kMAXOUTPUT_SHOOTER           =  1.0;      //
    public static final double kMINOUTPUT_SHOOTER           = -1.0;      //

    //Drive PIDs
    public static final double kP_Drive_Gyro                =  0.008;    //
    public static final double kI_Drive_Gyro                =  0.0;      //
    public static final double kD_Drive_Gyro                =  0.0;      //

    public static final double kP_Drive_Pixy                =  0.17;     //
    public static final double kI_Drive_Pixy                =  0.0;      //
    public static final double kD_Drive_Pixy                =  0.0;      //

    /*----------------------------------------------------------------------------*/ 
    /*                                                                            */
    /*----------------------------------------------------------------------------*/ 
    public static final int PILOT             =  0;
    public static final int PILOT_BUTTON_1    =  1;
    public static final int PILOT_BUTTON_2    =  2;
    public static final int PILOT_JOYSTICK_FOLLOW_POWER_CELL_BUTTON    =  2;
    public static final int PILOT_BUTTON_3    =  3;
    public static final int PILOT_BUTTON_4    =  4;
    public static final int PILOT_BUTTON_5    =  5;
    public static final int Pilot_Button_6    =  6;
    public static final int PILOT_BUTTON_7    =  7;
    public static final int PILOT_BUTTON_8    =  8;
    public static final int PILOT_BUTTON_9    =  9;
    public static final int PILOT_BUTTON_10   = 10;
    public static final int PILOT_BUTTON_11   = 11;

    public static final int COPILOT1                     =  3;
    public static final int COPILOT1_JOYSTICK_FOLLOW_POWER_CELL_BUTTON =  1;
    public static final int COPILOT1_PICKUP              =  2;
    public static final int COPILOT1_MAGAZINE_DOWN       =  3;
    public static final int COPILOT1_HOOD_CLOSE          =  4;
    public static final int COPILOT1_JOYSTICK_TURRET_LEFT =  5;
    public static final int COPILOT1_TURRET_FINE_ADJUST  =  6;
    public static final int COPILOT1_JOYSTICK_TURRET_RIGHT =  7;
    public static final int COPILOT1_SHOOT               =  8;
    public static final int COPILOT1_FIND_TARGET         =  9;
    public static final int COPILOT1_HOOD_FAR            = 10;

    public static final int COPILOT2             =  4;
    public static final int COPILOT2_HOOD_MEDIUM =  1;      // Button 11
    public static final int COPILOT2_MAGAZINE_UP =  2;      // Button 12
    public static final int COPILOT2_CLIMB       =  3;      // Button 13
    public static final int COPILOT2_CAMERA      =  4;      // Button 14
    public static final int COPILOT2_BUTTON_5    =  5;
    public static final int COPILOT2_BUTTON_6    =  6;
    public static final int COPILOT2_BUTTON_7    =  7;
    public static final int COPILOT2_BUTTON_8    =  8;
    public static final int COPILOT2_BUTTON_9    =  9;
    public static final int COPILOT2_BUTTON_10   = 10;

    public static final int COPILOT1_PICKUP_ROLLERS = 11; //FIXME: Needs a physical button and button ID, this was made purely to remove errors







    /*----------------------------------------------------------------------------*/ 
    /*  Used with the PCM controlled VEX Blinkin (5V LED Strips)                  */
    /*----------------------------------------------------------------------------*/ 
    public static final double LED_RAINBOW_PALETTE         = -0.99;   //   1	-0.99	Fixed Palette Pattern	Rainbow, Rainbow Palette
    public static final double LED_PARTY_PALETTE           = -0.97;   //   2 	-0.97	Fixed Palette Pattern	Rainbow, Party Palette
    public static final double LED_OCEAN_PALETTE           = -0.95;   //   3	-0.95	Fixed Palette Pattern	Rainbow, Ocean Palette
    public static final double LED_LAVA_PALETTE            = -0.93;   //   4	-0.93	Fixed Palette Pattern	Rainbow, Lava Palette
    public static final double LED_FOREST_PALLETE          = -0.91;   //   5	-0.91	Fixed Palette Pattern	Rainbow, Forest Palette
    public static final double LED_RAINBOW_GLITTER         = -0.89;   //   6	-0.89	Fixed Palette Pattern	Rainbow with Glitter
    public static final double LED_CONFETTI                = -0.87;   //   7	-0.87	Fixed Palette Pattern	Confetti
    public static final double LED_RED_SHOT                = -0.85;   //   8	-0.85	Fixed Palette Pattern	Shot, Red
    public static final double LED_BLUE_SHOT               = -0.83;   //   9	-0.83	Fixed Palette Pattern	Shot, Blue
    public static final double LED_WHITE_SHOT              = -0.81;   //  10	-0.81	Fixed Palette Pattern	Shot, White
    public static final double LED_RAINBOW_SINELON         = -0.79;   //  11	-0.79	Fixed Palette Pattern	Sinelon, Rainbow Palette
    public static final double LED_PARTY_SINELON           = -0.77;   //  12	-0.77	Fixed Palette Pattern	Sinelon, Party Palette
    public static final double LED_OCEAN_SINELON           = -0.75;   //  13	-0.75	Fixed Palette Pattern	Sinelon, Ocean Palette
    public static final double LED_LAVA_SINELON            = -0.73;   //  14	-0.73	Fixed Palette Pattern	Sinelon, Lava Palette
    public static final double LED_FOREST_SINELON          = -0.71;   //  15	-0.71	Fixed Palette Pattern	Sinelon, Forest Palette
    public static final double LED_RAINBOW_BPM             = -0.69;   //  16	-0.69	Fixed Palette Pattern	Beats per Minute, Rainbow Palette
    public static final double LED_PARTY_BPM               = -0.67;   //  17	-0.67	Fixed Palette Pattern	Beats per Minute, Party Palette
    public static final double LED_OCEAN_BPM               = -0.65;   //  18	-0.65	Fixed Palette Pattern	Beats per Minute, Ocean Palette
    public static final double LED_LAVA_BPM                = -0.63;   //  19	-0.63	Fixed Palette Pattern	Beats per Minute, Lava Palette
    public static final double LED_FOREST_BPM              = -0.61;   //  20	-0.61	Fixed Palette Pattern	Beats per Minute, Forest Palette
    public static final double LED_MEDIUM_FIRE             = -0.59;   //  21	-0.59	Fixed Palette Pattern	Fire, Medium
    public static final double LED_LARGE_FIRE              = -0.57;   //  22	-0.57	Fixed Palette Pattern	Fire, Large
    public static final double LED_RAINBOW_TWINKLES        = -0.55;   //  23	-0.55	Fixed Palette Pattern	Twinkles, Rainbow Palette
    public static final double LED_PARTY_TWINKLES          = -0.53;   //  24	-0.53	Fixed Palette Pattern	Twinkles, Party Palette
    public static final double LED_OCEAN_TWINKLES          = -0.51;   //  25	-0.51	Fixed Palette Pattern	Twinkles, Ocean Palette
    public static final double LED_LAVA_TWINKLES           = -0.49;   //  26	-0.49	Fixed Palette Pattern	Twinkles, Lava Palette
    public static final double LED_FOREST_TWINKLES         = -0.47;   //  27	-0.47	Fixed Palette Pattern	Twinkles, Forest Palette
    public static final double LED_RAINBOW_WAVES           = -0.45;   //  28	-0.45	Fixed Palette Pattern	Color Waves, Rainbow Palette
    public static final double LED_PARTY_WAVES             = -0.43;   //  29	-0.43	Fixed Palette Pattern	Color Waves, Party Palette
    public static final double LED_OCEAN_WAVES             = -0.41;   //  30	-0.41	Fixed Palette Pattern	Color Waves, Ocean Palette
    public static final double LED_LAVA_WAVES              = -0.39;   //  31	-0.39	Fixed Palette Pattern	Color Waves, Lava Palette
    public static final double LED_FOREST_WAVES            = -0.37;   //  32	-0.37	Fixed Palette Pattern	Color Waves, Forest Palette
    public static final double LED_RED_LARSON              = -0.35;   //  33	-0.35	Fixed Palette Pattern	Larson Scanner, Red
    public static final double LED_GRAY_LARSON             = -0.33;   //  34	-0.33	Fixed Palette Pattern	Larson Scanner, Gray
    public static final double LED_RED_CHASE               = -0.31;   //  35	-0.31	Fixed Palette Pattern	Light Chase, Red
    public static final double LED_BLUE_CHASE              = -0.29;   //  36	-0.29	Fixed Palette Pattern	Light Chase, Blue
    public static final double LED_GRAY_CHASE              = -0.27;   //  37	-0.27	Fixed Palette Pattern	Light Chase, Gray
    public static final double LED_RED_HEARTBEAT           = -0.25;   //  38	-0.25	Fixed Palette Pattern	Heartbeat, Red
    public static final double LED_BLUE_HEARTBEAT          = -0.23;   //  39	-0.23	Fixed Palette Pattern	Heartbeat, Blue
    public static final double LED_WHITE_HEARTBEAT         = -0.21;   //  40	-0.21	Fixed Palette Pattern	Heartbeat, White
    public static final double LED_GRAY_HEARTBEAT          = -0.19;   //  41	-0.19	Fixed Palette Pattern	Heartbeat, Gray
    public static final double LED_RED_BREATH              = -0.17;   //  42	-0.17	Fixed Palette Pattern	Breath, Red
    public static final double LED_BLUE_BREATH             = -0.15;   //  43	-0.15	Fixed Palette Pattern	Breath, Blue
    public static final double LED_GRAY_BREATH             = -0.13;   //  44	-0.13	Fixed Palette Pattern	Breath, Gray
    public static final double LED_RED_STROBE              = -0.11;   //  45	-0.11	Fixed Palette Pattern	Strobe, Red
    public static final double LED_BLUE_STROBE             = -0.09;   //  46	-0.09	Fixed Palette Pattern	Strobe, Blue
    public static final double LED_GOLD_STROBE             = -0.07;   //  47	-0.07	Fixed Palette Pattern	Strobe, Gold
    public static final double LED_WHITE_STROBE            = -0.05;   //  48	-0.05	Fixed Palette Pattern	Strobe, White
    public static final double LED_END_TO_END_BLEND_BLACK1 = -0.03;   //  49	-0.03	Color 1 Pattern	        End to End Blend to Black
    public static final double LED_LARSON_SCAN1            = -0.01;   //  50	-0.01	Color 1 Pattern         Larson Scanner
    public static final double LED_LIGHT_CHASE1            =  0.01;   //  51	0.01	Color 1 Pattern	        Light Chase
    public static final double LED_SLOW_HEARTBEAT1         =  0.03;   //  52	0.03	Color 1 Pattern	        Heartbeat Slow
    public static final double LED_MEDIUM_HEARTBEAT1       =  0.05;   //  53	0.05	Color 1 Pattern	        Heartbeat Medium
    public static final double LED_FAST_HEARTBEAT1         =  0.07;   //  54	0.07	Color 1 Pattern	        Heartbeat Fast
    public static final double LED_SLOW_BREATH1            =  0.09;   //  55	0.09	Color 1 Pattern	        Breath Slow
    public static final double LED_FAST_BREATH1            =  0.11;   //  56	0.11	Color 1 Pattern	        Breath Fast
    public static final double LED_SHOT1                   =  0.13;   //  57	0.13	Color 1 Pattern	        Shot
    public static final double LED_STROBE1                 =  0.15;   //  58	0.15	Color 1 Pattern	        Strobe
    public static final double LED_END_TO_END_BLEND_BLACK2 =  0.17;   //  59	0.17	Color 2 Pattern	        End to End Blend to Black
    public static final double LED_LARSON_SCAN2            =  0.19;   //  60	0.19	Color 2 Pattern	        Larson Scanner
    public static final double LED_LIGHT_CHASE2            =  0.21;   //  61	0.21	Color 2 Pattern	        Light Chase
    public static final double LED_SLOW_HEARTBEAT2         =  0.23;   //  62	0.23	Color 2 Pattern	        Heartbeat Slow
    public static final double LED_MEDIUM_HEARTBEAT2       =  0.25;   //  63	0.25	Color 2 Pattern	        Heartbeat Medium
    public static final double LED_FAST_HEARTBEAT2         =  0.27;   //  64	0.27	Color 2 Pattern	        Heartbeat Fast
    public static final double LED_SLOW_BREATH2            =  0.29;   //  65	0.29	Color 2 Pattern	        Breath Slow
    public static final double LED_FAST_BREATH2            =  0.31;   //  66	0.31	Color 2 Pattern	        Breath Fast
    public static final double LED_SHOT2                   =  0.33;   //  67	0.33	Color 2 Pattern	        Shot
    public static final double LED_STROBE2                 =  0.35;   //  68	0.35	Color 2 Pattern	        Strobe
    public static final double LED_SPARKLE_1_ON_2          =  0.37;   //  69	0.37	Color 1 and 2 Pattern	Sparkle, Color 1 on Color 2
    public static final double LED_SPARKLE_2_ON_1          =  0.39;   //  70	0.39	Color 1 and 2 Pattern	Sparkle, Color 2 on Color 1
    public static final double LED_GRADIENT_1_2            =  0.41;   //  71	0.41	Color 1 and 2 Pattern	Color Gradient, Color 1 and 2
    public static final double LED_BPM_1_2                 =  0.43;   //  72	0.43	Color 1 and 2 Pattern	Beats per Minute, Color 1 and 2
    public static final double LED_END_TO_END_BLEND_1_2    =  0.45;   //  73	0.45	Color 1 and 2 Pattern	End to End Blend, Color 1 to 2
    public static final double LED_End_TO_END_BLEND        =  0.47;   //  74	0.47	Color 1 and 2 Pattern	End to End Blend
    public static final double LED_NO_BLEND_1_2            =  0.49;   //  75	0.49	Color 1 and 2 Pattern	Color 1 and Color 2 no blending (Setup Pattern)
    public static final double LED_TWINKLE_1_2             =  0.51;   //  76	0.51	Color 1 and 2 Pattern	Twinkles, Color 1 and 2
    public static final double LED_WAVES_1_2               =  0.53;   //  77	0.53	Color 1 and 2 Pattern	Color Waves, Color 1 and 2
    public static final double LED_SINELON_1_2             =  0.55;   //  78	0.55	Color 1 and 2 Pattern	Sinelon, Color 1 and 2
    public static final double LED_SOLID_HOT_PINK          =  0.57;   //  79	0.57	Solid Colors	        Hot Pink
    public static final double LED_SOLID_DARK_RED          =  0.59;   //  80	0.59	Solid Colors	        Dark red
    public static final double LED_SOLID_RED               =  0.61;   //  81	0.61	Solid Colors	        Red
    public static final double LED_SOLID_RED_ORANGE        =  0.63;   //  82	0.63	Solid Colors	        Red Orange
    public static final double LED_SOLID_ORANGE            =  0.65;   //  83	0.65	Solid Colors	        Orange
    public static final double LED_SOLID_GOLD              =  0.67;   //  84	0.67	Solid Colors	        Gold
    public static final double LED_SOLID_YELLOW            =  0.69;   //  85	0.69	Solid Colors	        Yellow
    public static final double LED_SOLID_LAWN_GREEN        =  0.71;   //  86	0.71	Solid Colors	        Lawn Green
    public static final double LED_SOLID_LIME              =  0.73;   //  87	0.73	Solid Colors	        Lime
    public static final double LED_SOLID_DARK_GREEN        =  0.75;   //  88	0.75	Solid Colors	        Dark Green
    public static final double LED_SOLID_GREEN             =  0.77;   //  89	0.77	Solid Colors	        Green
    public static final double LED_SOLID_BLUE_GREEN        =  0.79;   //  90	0.79	Solid Colors	        Blue Green
    public static final double LED_SOLID_AQUA              =  0.81;   //  91	0.81	Solid Colors	        Aqua
    public static final double LED_SOLID_SKY_BLUE          =  0.83;   //  92	0.83	Solid Colors	        Sky Blue
    public static final double LED_SOLID_DARK_BLUE         =  0.85;   //  93	0.85	Solid Colors	        Dark Blue
    public static final double LED_SOLID_BLUE              =  0.87;   //  94	0.87	Solid Colors	        Blue
    public static final double LED_SOLID_BLUE_VIOLET       =  0.89;   //  95	0.89	Solid Colors	        Blue Violet
    public static final double LED_SOLID_VIOLET            =  0.91;   //  96	0.91	Solid Colors	        Violet
    public static final double LED_SOLID_WHITE             =  0.93;   //  97	0.93	Solid Colors	        White
    public static final double LED_SOLID_GRAY              =  0.95;   //  98	0.95	Solid Colors	        Gray
    public static final double LED_SOLID_DARK_GRAY         =  0.97;   //  99	0.97	Solid Colors	        Dark Gray
    public static final double LED_SOLID_BLACK             =  0.99;   // 100	0.99	Solid Colors	        Black
}