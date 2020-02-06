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
    public static final boolean PICKUP_AVAILABLE        = false;
    public static final boolean TURRET_AVAILABLE        = false;
    public static final boolean MAGAZINE_AVAILABLE      = false;
    public static final boolean CLIMBER_AVAILABLE       = false;
    public static final boolean CONTROLPANEL_AVAILABLE  = false;
    public static final boolean AIM_AVAILABLE           = false;     // FIXME use camera_available?
    public static final boolean COMPRESSOR_AVAILABLE    = false;
    public static final boolean GYRO_AVAILABLE          = false;
    public static final boolean BUTTONBOX_AVAILABLE     = false;

    /** Enum to hold all information about pneumatic solenoids */
    public enum PneumaticChannel {
        DRIVE_LOW_GEAR(4),
        DRIVE_HIGH_GEAR(5),
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
        DRIVE_LEFT_LEADER    (10, false, true), 
        DRIVE_LEFT_FOLLOWER  (11, false, false),
        DRIVE_RIGHT_LEADER   (20, true, true), 
        DRIVE_RIGHT_FOLLOWER (21, true, false), 

        TURRET_DIRECTION     (30, false, false), 
        TURRET_HOOD          (31, false, false),
        TURRET_SHOOTER       (32, false, false),

        ROLLER               (40, false, false), 
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
        AUTO, MANUAL;
    }

    /**
     * Switches the gears of the drive
     */
    public enum Gear {
        LOW, HIGH;
    }

    /**
     * Used in Aim to setNoTargetMode
     */
    public enum Mode {
        DONT_MOVE, SCAN, FACE_FORWARD;
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

    //Speeds for subsystems
    public static final double PICKUP_ROLLER_SPEED = 0.7;
    public static final double MAGAZINE_LOAD_SPEED = 0.5;
    public static final double MAGAZINE_SHOOT_SPEED = 1;

    //used for climber currently not being used
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
    public static final double DRIVE_LEFT_GEAR_RATIO = 1.0/8.333;    // 8.333 Low     3.667 High for 2 CIM Ball Shifter
    public static final double DRIVE_RIGHT_GEAR_RATIO = 1.0/8.333;
    public static final double DRIVE_WHEEL_DIAMETER = 4.25;
    // FIXME fill in/ fix all of the values

    //used in Turret sets port for gyro
    public static final SPI.Port CHASSIS_GYRO_PORT = SPI.Port.kOnboardCS0;

    //subsystem PIDs
    public static final double kP_Turret_Camera             =  0.005;    // was 0.005
    public static final double kI_Turret_Camera             =  0.0008;   // was 0.0008
    public static final double kD_Turret_Camera             =  0.0;      //

    public static final double kP_Turret_Position           =  0.005;    //
    public static final double kI_Turret_Position           =  1.1e-5;   //
    public static final double kD_Turret_Position           =  0.0;      //
    public static final double klz_Turret_Position          =  0.0;      //
    public static final double kFF_Turret_Position          =  0.0;      //
    public static final double kMaxOutput_Turret_Position   =  1.0;      //
    public static final double kMinOutput_Turret_Position   = -1.0;      //
 
    public static final double kP_Shooter                   =  5e-5;     //
    public static final double kI_Shooter                   =  1e-6;     //
    public static final double kD_Shooter                   =  0.0;      //
    public static final double klz_Shooter                  =  0.0;      //
    public static final double kFF_Shooter                  =  0.0;      //
    public static final double kMaxOutput_Shooter           =  1.0;      //
    public static final double kMinOutput_Shooter           = -1.0;      //

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
    public static final int Pilot             =  0;
    public static final int Pilot_Button_1    =  1;
    public static final int Pilot_Button_2    =  2;
    public static final int PILOT_JOYSTICK_FOLLOW_POWER_CELL_BUTTON    =  2;
    public static final int Pilot_Button_3    =  3;
    public static final int Pilot_Button_4    =  4;
    public static final int Pilot_Button_5    =  5;
    public static final int Pilot_Button_6    =  6;
    public static final int Pilot_Button_7    =  7;
    public static final int Pilot_Button_8    =  8;
    public static final int Pilot_Button_9    =  9;
    public static final int Pilot_Button_10   = 10;
    public static final int Pilot_Button_11   = 11;

    public static final int CoPilot1                     =  3;
    public static final int COPILOT1_JOYSTICK_FOLLOW_POWER_CELL_BUTTON    =  1;
    public static final int CoPilot1_Pick_Up             =  2;
    public static final int CoPilot1_Magazine_Down       =  3;
    public static final int CoPilot1_Hood_Close          =  4;
    public static final int COPILOT1_JOYSTICK_TURRET_LEFT                 =  5;
    public static final int CoPilot1_Turret_Fine_Adjust  =  6;
    public static final int COPILOT1_JOYSTICK_TURRET_RIGHT                =  7;
    public static final int CoPilot1_Shoot               =  8;
    public static final int CoPilot1_Find_Target         =  9;
    public static final int CoPilot1_Hood_Far            = 10;

    public static final int CoPilot2                     =  4;
    public static final int CoPilot2_Hood_Medium         =  1;      // Button 11
    public static final int CoPilot2_Magazine_Up         =  2;      // Button 12
    public static final int CoPilot2_Climb               =  3;      // Button 13
    public static final int CoPilot2_Camera              =  4;      // Button 14
    public static final int CoPilot2_Button_5    =  5;
    public static final int CoPilot2_Button_6    =  6;
    public static final int CoPilot2_Button_7    =  7;
    public static final int CoPilot2_Button_8    =  8;
    public static final int CoPilot2_Button_9    =  9;
    public static final int CoPilot2_Button_10   = 10;







    /*----------------------------------------------------------------------------*/ 
    /*  Used with the PCM controlled VEX Blinkin (5V LED Strips)                  */
    /*----------------------------------------------------------------------------*/ 
    public static final double LED_Rainbow_Palette         = -0.99;   //   1	-0.99	Fixed Palette Pattern	Rainbow, Rainbow Palette
    public static final double LED_Party_Palette           = -0.97;   //   2 	-0.97	Fixed Palette Pattern	Rainbow, Party Palette
    public static final double LED_Ocean_Palette           = -0.95;   //   3	-0.95	Fixed Palette Pattern	Rainbow, Ocean Palette
    public static final double LED_Lava_Palette            = -0.93;   //   4	-0.93	Fixed Palette Pattern	Rainbow, Lava Palette
    public static final double LED_Forest_Palette          = -0.91;   //   5	-0.91	Fixed Palette Pattern	Rainbow, Forest Palette
    public static final double LED_Rainbow_Glitter         = -0.89;   //   6	-0.89	Fixed Palette Pattern	Rainbow with Glitter
    public static final double LED_Confetti                = -0.87;   //   7	-0.87	Fixed Palette Pattern	Confetti
    public static final double LED_Red_Shot                = -0.85;   //   8	-0.85	Fixed Palette Pattern	Shot, Red
    public static final double LED_Blue_Shot               = -0.83;   //   9	-0.83	Fixed Palette Pattern	Shot, Blue
    public static final double LED_White_Shot              = -0.81;   //  10	-0.81	Fixed Palette Pattern	Shot, White
    public static final double LED_Rainbow_Sinelon         = -0.79;   //  11	-0.79	Fixed Palette Pattern	Sinelon, Rainbow Palette
    public static final double LED_Party_Sinelon           = -0.77;   //  12	-0.77	Fixed Palette Pattern	Sinelon, Party Palette
    public static final double LED_Ocean_Sinelon           = -0.75;   //  13	-0.75	Fixed Palette Pattern	Sinelon, Ocean Palette
    public static final double LED_Lava_Sinelon            = -0.73;   //  14	-0.73	Fixed Palette Pattern	Sinelon, Lava Palette
    public static final double LED_Forest_Sinelon          = -0.71;   //  15	-0.71	Fixed Palette Pattern	Sinelon, Forest Palette
    public static final double LED_Rainbow_BPM             = -0.69;   //  16	-0.69	Fixed Palette Pattern	Beats per Minute, Rainbow Palette
    public static final double LED_Party_BPM               = -0.67;   //  17	-0.67	Fixed Palette Pattern	Beats per Minute, Party Palette
    public static final double LED_Ocean_BPM               = -0.65;   //  18	-0.65	Fixed Palette Pattern	Beats per Minute, Ocean Palette
    public static final double LED_Lava_BPM                = -0.63;   //  19	-0.63	Fixed Palette Pattern	Beats per Minute, Lava Palette
    public static final double LED_Forest_BPM              = -0.61;   //  20	-0.61	Fixed Palette Pattern	Beats per Minute, Forest Palette
    public static final double LED_Medium_Fire             = -0.59;   //  21	-0.59	Fixed Palette Pattern	Fire, Medium
    public static final double LED_Large_Fire              = -0.57;   //  22	-0.57	Fixed Palette Pattern	Fire, Large
    public static final double LED_Rainbow_Twinkles        = -0.55;   //  23	-0.55	Fixed Palette Pattern	Twinkles, Rainbow Palette
    public static final double LED_Party_Twinkles          = -0.53;   //  24	-0.53	Fixed Palette Pattern	Twinkles, Party Palette
    public static final double LED_Ocean_Twinkles          = -0.51;   //  25	-0.51	Fixed Palette Pattern	Twinkles, Ocean Palette
    public static final double LED_Lava_Twinkles           = -0.49;   //  26	-0.49	Fixed Palette Pattern	Twinkles, Lava Palette
    public static final double LED_Forest_Twinkles         = -0.47;   //  27	-0.47	Fixed Palette Pattern	Twinkles, Forest Palette
    public static final double LED_Rainbow_Waves           = -0.45;   //  28	-0.45	Fixed Palette Pattern	Color Waves, Rainbow Palette
    public static final double LED_Party_Waves             = -0.43;   //  29	-0.43	Fixed Palette Pattern	Color Waves, Party Palette
    public static final double LED_Ocean_Waves             = -0.41;   //  30	-0.41	Fixed Palette Pattern	Color Waves, Ocean Palette
    public static final double LED_Lava_Waves              = -0.39;   //  31	-0.39	Fixed Palette Pattern	Color Waves, Lava Palette
    public static final double LED_Forest_Waves            = -0.37;   //  32	-0.37	Fixed Palette Pattern	Color Waves, Forest Palette
    public static final double LED_Red_Larson              = -0.35;   //  33	-0.35	Fixed Palette Pattern	Larson Scanner, Red
    public static final double LED_Gray_Larson             = -0.33;   //  34	-0.33	Fixed Palette Pattern	Larson Scanner, Gray
    public static final double LED_Red_Chase               = -0.31;   //  35	-0.31	Fixed Palette Pattern	Light Chase, Red
    public static final double LED_Blue_Chase              = -0.29;   //  36	-0.29	Fixed Palette Pattern	Light Chase, Blue
    public static final double LED_Gray_Chase              = -0.27;   //  37	-0.27	Fixed Palette Pattern	Light Chase, Gray
    public static final double LED_Red_Heartbeat           = -0.25;   //  38	-0.25	Fixed Palette Pattern	Heartbeat, Red
    public static final double LED_Blue_Heartbeat          = -0.23;   //  39	-0.23	Fixed Palette Pattern	Heartbeat, Blue
    public static final double LED_White_Heartbeat         = -0.21;   //  40	-0.21	Fixed Palette Pattern	Heartbeat, White
    public static final double LED_Gray_Heartbeat          = -0.19;   //  41	-0.19	Fixed Palette Pattern	Heartbeat, Gray
    public static final double LED_Red_Breath              = -0.17;   //  42	-0.17	Fixed Palette Pattern	Breath, Red
    public static final double LED_Blue_Breath             = -0.15;   //  43	-0.15	Fixed Palette Pattern	Breath, Blue
    public static final double LED_Gray_Breath             = -0.13;   //  44	-0.13	Fixed Palette Pattern	Breath, Gray
    public static final double LED_Red_Strobe              = -0.11;   //  45	-0.11	Fixed Palette Pattern	Strobe, Red
    public static final double LED_Blue_Strobe             = -0.09;   //  46	-0.09	Fixed Palette Pattern	Strobe, Blue
    public static final double LED_Gold_Strobe             = -0.07;   //  47	-0.07	Fixed Palette Pattern	Strobe, Gold
    public static final double LED_White_Strobe            = -0.05;   //  48	-0.05	Fixed Palette Pattern	Strobe, White
    public static final double LED_End_to_End_Blend_Black1 = -0.03;   //  49	-0.03	Color 1 Pattern	        End to End Blend to Black
    public static final double LED_Larson_Scan1            = -0.01;   //  50	-0.01	Color 1 Pattern         Larson Scanner
    public static final double LED_Light_Chase1            =  0.01;   //  51	0.01	Color 1 Pattern	        Light Chase
    public static final double LED_Slow_Heartbeat1         =  0.03;   //  52	0.03	Color 1 Pattern	        Heartbeat Slow
    public static final double LED_Medium_Heartbeat1       =  0.05;   //  53	0.05	Color 1 Pattern	        Heartbeat Medium
    public static final double LED_Fast_Heartbeat1         =  0.07;   //  54	0.07	Color 1 Pattern	        Heartbeat Fast
    public static final double LED_Slow_Breath1            =  0.09;   //  55	0.09	Color 1 Pattern	        Breath Slow
    public static final double LED_Fast_Breath1            =  0.11;   //  56	0.11	Color 1 Pattern	        Breath Fast
    public static final double LED_Shot1                   =  0.13;   //  57	0.13	Color 1 Pattern	        Shot
    public static final double LED_Strobe1                 =  0.15;   //  58	0.15	Color 1 Pattern	        Strobe
    public static final double LED_End_to_End_Blend_Black2 =  0.17;   //  59	0.17	Color 2 Pattern	        End to End Blend to Black
    public static final double LED_Larson_Scan2            =  0.19;   //  60	0.19	Color 2 Pattern	        Larson Scanner
    public static final double LED_Light_Chase2            =  0.21;   //  61	0.21	Color 2 Pattern	        Light Chase
    public static final double LED_Slow_Heartbeat2         =  0.23;   //  62	0.23	Color 2 Pattern	        Heartbeat Slow
    public static final double LED_Medium_Heartbeat2       =  0.25;   //  63	0.25	Color 2 Pattern	        Heartbeat Medium
    public static final double LED_Fast_Heartbeat2         =  0.27;   //  64	0.27	Color 2 Pattern	        Heartbeat Fast
    public static final double LED_Slow_Breath2            =  0.29;   //  65	0.29	Color 2 Pattern	        Breath Slow
    public static final double LED_Fast_Breath2            =  0.31;   //  66	0.31	Color 2 Pattern	        Breath Fast
    public static final double LED_Shot2                   =  0.33;   //  67	0.33	Color 2 Pattern	        Shot
    public static final double LED_Strobe2                 =  0.35;   //  68	0.35	Color 2 Pattern	        Strobe
    public static final double LED_Sparkle_1_on_2          =  0.37;   //  69	0.37	Color 1 and 2 Pattern	Sparkle, Color 1 on Color 2
    public static final double LED_Sparkle_2_on_1          =  0.39;   //  70	0.39	Color 1 and 2 Pattern	Sparkle, Color 2 on Color 1
    public static final double LED_Gradient_1_2            =  0.41;   //  71	0.41	Color 1 and 2 Pattern	Color Gradient, Color 1 and 2
    public static final double LED_BPM_1_2                 =  0.43;   //  72	0.43	Color 1 and 2 Pattern	Beats per Minute, Color 1 and 2
    public static final double LED_End_to_End_Blend_1_2    =  0.45;   //  73	0.45	Color 1 and 2 Pattern	End to End Blend, Color 1 to 2
    public static final double LED_End_to_End_Blend        =  0.47;   //  74	0.47	Color 1 and 2 Pattern	End to End Blend
    public static final double LED_No_Blend_1_2            =  0.49;   //  75	0.49	Color 1 and 2 Pattern	Color 1 and Color 2 no blending (Setup Pattern)
    public static final double LED_Twinkle_1_2             =  0.51;   //  76	0.51	Color 1 and 2 Pattern	Twinkles, Color 1 and 2
    public static final double LED_Waves_1_2               =  0.53;   //  77	0.53	Color 1 and 2 Pattern	Color Waves, Color 1 and 2
    public static final double LED_Sinelon_1_2             =  0.55;   //  78	0.55	Color 1 and 2 Pattern	Sinelon, Color 1 and 2
    public static final double LED_Solid_Hot_Pink          =  0.57;   //  79	0.57	Solid Colors	        Hot Pink
    public static final double LED_Solid_Dark_Red          =  0.59;   //  80	0.59	Solid Colors	        Dark red
    public static final double LED_Solid_Red               =  0.61;   //  81	0.61	Solid Colors	        Red
    public static final double LED_Solid_Red_Orange        =  0.63;   //  82	0.63	Solid Colors	        Red Orange
    public static final double LED_Solid_Orange            =  0.65;   //  83	0.65	Solid Colors	        Orange
    public static final double LED_Solid_Gold              =  0.67;   //  84	0.67	Solid Colors	        Gold
    public static final double LED_Solid_Yellow            =  0.69;   //  85	0.69	Solid Colors	        Yellow
    public static final double LED_Solid_Lawn_Green        =  0.71;   //  86	0.71	Solid Colors	        Lawn Green
    public static final double LED_Solid_Lime              =  0.73;   //  87	0.73	Solid Colors	        Lime
    public static final double LED_Solid_Dark_Green        =  0.75;   //  88	0.75	Solid Colors	        Dark Green
    public static final double LED_Solid_Green             =  0.77;   //  89	0.77	Solid Colors	        Green
    public static final double LED_Solid_Blue_Green        =  0.79;   //  90	0.79	Solid Colors	        Blue Green
    public static final double LED_Solid_Aqua              =  0.81;   //  91	0.81	Solid Colors	        Aqua
    public static final double LED_Solid_Sky_Blue          =  0.83;   //  92	0.83	Solid Colors	        Sky Blue
    public static final double LED_Solid_Dark_Blue         =  0.85;   //  93	0.85	Solid Colors	        Dark Blue
    public static final double LED_Solid_Blue              =  0.87;   //  94	0.87	Solid Colors	        Blue
    public static final double LED_Solid_Blue_Violet       =  0.89;   //  95	0.89	Solid Colors	        Blue Violet
    public static final double LED_Solid_Violet            =  0.91;   //  96	0.91	Solid Colors	        Violet
    public static final double LED_Solid_White             =  0.93;   //  97	0.93	Solid Colors	        White
    public static final double LED_Solid_Gray              =  0.95;   //  98	0.95	Solid Colors	        Gray
    public static final double LED_Solid_Dark_Gray         =  0.97;   //  99	0.97	Solid Colors	        Dark Gray
    public static final double LED_Solid_Black             =  0.99;   // 100	0.99	Solid Colors	        Black
}