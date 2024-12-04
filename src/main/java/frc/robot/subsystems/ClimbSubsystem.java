// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;


import com.ctre.phoenix.motorcontrol.can.VictorSPX;

import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants.ClimbConstants;


public class ClimbSubsystem extends SubsystemBase {

  public static final VictorSPX climb_motor = new VictorSPX(ClimbConstants.climb_motorID);
  
  public ClimbSubsystem() {

    climb_motor.setInverted(ClimbConstants.climbReversed);
    climb_motor.setNeutralMode(ClimbConstants.climbNeutralMode);
    
  }


  @Override
  public void periodic() {
  
  }


}
