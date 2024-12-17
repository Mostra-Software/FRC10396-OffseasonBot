// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;


import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.VictorSPX;

import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants.IntakeConstants;

public class IntakeSubsystem extends SubsystemBase {

  public static final VictorSPX intake_motor = new VictorSPX(IntakeConstants.motorID);
  public static final DigitalInput intake_sensor = new DigitalInput(IntakeConstants.sensorID);
  
  public IntakeSubsystem() {

    intake_motor.setInverted(IntakeConstants.reversed);
    intake_motor.setNeutralMode(IntakeConstants.neutralMode);
    
  }


  @Override
  public void periodic() {
    SmartDashboard.putBoolean("Intake Sensor", is_gp_present());
  }

  public boolean is_gp_present(){
    return intake_sensor.get();
  }

  public void intake(){
      intake_motor.set(ControlMode.PercentOutput, IntakeConstants.intake_speed*-1);
  }

  public void shoot(){
    intake_motor.set(ControlMode.PercentOutput, IntakeConstants.shoot_speed*1);
  }

  public void stop(){
    intake_motor.set(ControlMode.PercentOutput, 0);
  }

}
