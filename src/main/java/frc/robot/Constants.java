package frc.robot;

import com.ctre.phoenix.motorcontrol.NeutralMode;

public final class Constants {

  public static boolean sorun_cozucu = false;
  
   public static class ClimbConstants {

    public static final int climb_motorID = 0;
    public static final NeutralMode climbNeutralMode = NeutralMode.Brake;
    public static final boolean climbReversed = false;
    
    public static final double climb_speed = 1;
  }

   public static class DriveConstants {

    public static final int left_front_motorID = 0;
    public static final int left_rear_motorID = 1;
    public static final int right_front_motorID = 2;
    public static final int right_rear_motorID = 3;

    public static final boolean left_front_motor_reversed = false;
    public static final boolean left_rear_motor_reversed = false;
    public static final boolean right_front_motor_reversed = false;
    public static final boolean right_rear_motor_reversed = false;
    
    public static final NeutralMode driveNeutralMode = NeutralMode.Brake;

    


  }

   public static class IntakeConstants {

    public static final int intake_motorID = 0;
    public static final int intake_sensorID = 0;
    public static final NeutralMode intakeNeutralMode = NeutralMode.Brake;
    public static final boolean intakeReversed = false;

    public static final double intake_speed = 1;
  
  }

  public static class OperatorConstants {

    public static final int driver_controller_port = 0;
    public static final int operator_controller_port = 0;
  
  }
}