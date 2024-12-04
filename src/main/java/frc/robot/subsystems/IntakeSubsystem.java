// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;


import com.ctre.phoenix.motorcontrol.can.VictorSPX;

import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;
import frc.robot.Constants.IntakeConstants;

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
      SmartDashboard.putBoolean("Intake Sensor", get_intake_sensor());
    }

  }

  public static boolean get_intake_sensor(){
    return intake_sensor.get();
  }

}
