// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;


import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.VictorSPX;

import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;
import frc.robot.RobotContainer;
import frc.robot.Constants.IntakeConstants;
import frc.robot.RobotState;

public class IntakeSubsystem extends SubsystemBase {

  public static final VictorSPX intake_motor = new VictorSPX(IntakeConstants.intake_motorID);
  public static final DigitalInput intake_sensor = new DigitalInput(IntakeConstants.intake_sensorID);
  
  public IntakeSubsystem() {

    intake_motor.setInverted(IntakeConstants.intakeReversed);
    intake_motor.setNeutralMode(IntakeConstants.intakeNeutralMode);
    
  }


  @Override
  public void periodic() {
  
    if(Constants.sorun_cozucu){
      SmartDashboard.putBoolean("Intake Sensor", is_gp_present());
      
      SmartDashboard.putString("ROBOT STATE", RobotContainer.m_RobotState.name());
    }

    if(!(RobotContainer.m_RobotState == RobotState.SHOOTING)){

      if(is_gp_present() && !(RobotContainer.m_RobotState == RobotState.INTAKING)){
    
        RobotContainer.m_RobotState = RobotState.READY;
        idle_Intake();

      }else if(!is_gp_present() && !(RobotContainer.m_RobotState == RobotState.INTAKING)){

        RobotContainer.m_RobotState = RobotState.IDLE;
        stop_intake();
      
    }


  
  }



  }

  public static boolean is_gp_present(){
    return intake_sensor.get();
  }

  public static void start_Intake(){
      intake_motor.set(ControlMode.PercentOutput, IntakeConstants.intake_speed*-1);
  }

  public static void idle_Intake(){
    intake_motor.set(ControlMode.PercentOutput, IntakeConstants.idle_speed*-1);
  }

  public static void start_Shooter(){
    intake_motor.set(ControlMode.PercentOutput, IntakeConstants.shoot_speed*1);
  }

  public static void stop_intake(){
    intake_motor.set(ControlMode.PercentOutput, 0);
  }

}
