package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.can.WPI_VictorSPX;

import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants.DriveConstants;



public class DriveSubsystem extends SubsystemBase {

  public static WPI_VictorSPX left_front_motor = new WPI_VictorSPX(DriveConstants.left_front_motorID);
  public static WPI_VictorSPX left_rear_motor = new WPI_VictorSPX(DriveConstants.left_rear_motorID);

  public static WPI_VictorSPX right_front_motor = new WPI_VictorSPX(DriveConstants.right_front_motorID);
  public static WPI_VictorSPX right_rear_motor = new WPI_VictorSPX(DriveConstants.right_rear_motorID);

  public static DifferentialDrive drive_ = new DifferentialDrive(left_front_motor, right_front_motor);

  public DriveSubsystem() {

    left_front_motor.setInverted(DriveConstants.left_front_motor_reversed);
    left_front_motor.setNeutralMode(DriveConstants.driveNeutralMode);

    left_rear_motor.setInverted(DriveConstants.left_rear_motor_reversed);
    left_rear_motor.setNeutralMode(DriveConstants.driveNeutralMode);

    right_front_motor.setInverted(DriveConstants.right_front_motor_reversed);
    right_front_motor.setNeutralMode(DriveConstants.driveNeutralMode);

    right_rear_motor.setInverted(DriveConstants.right_rear_motor_reversed);
    right_rear_motor.setNeutralMode(DriveConstants.driveNeutralMode);

  }



  @Override
  public void periodic() {
    
  }



}
