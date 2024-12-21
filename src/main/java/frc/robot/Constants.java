package frc.robot;

import com.ctre.phoenix.motorcontrol.NeutralMode;

public final class Constants {

  public static boolean debugger = false;
  
   public static class ClimbConstants {

    public static final int motorID = 2;
    public static final NeutralMode neutralMode = NeutralMode.Brake;
    public static final boolean reversed = true;
    public static final double speed = 0.3;
  }

   public static class DriveConstants {

    public static final int left_front_motorID = 1;
    public static final int left_rear_motorID = 3;
    public static final int right_front_motorID = 4;
    public static final int right_rear_motorID = 5;

    public static final boolean left_front_motor_reversed = true;
    public static final boolean left_rear_motor_reversed = true;
    public static final boolean right_front_motor_reversed = false;
    public static final boolean right_rear_motor_reversed = false;
    
    public static final NeutralMode neutralMode = NeutralMode.Coast;
    public static double kSpeedRateLimit = 6;
    public static double kRotRateLimit = 6;
  }

   public static class IntakeConstants {

    public static final int motorID = 0;
    public static final int sensorID = 0;
    public static final NeutralMode neutralMode = NeutralMode.Brake;
    public static final boolean reversed = true;

    public static final double intake_speed = 0.5;
    public static final double shoot_speed = 1;
    public static final double ampSequenceTimeout = 0.6;
    public static final double rumbleStrength = 0.3;  
  }

  public static class JoystickConstants {

    public static final int driver = 1;
    public static final int operator = 0;
  
  }
}
