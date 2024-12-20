// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems.intake;

import com.ctre.phoenix.motorcontrol.VictorSPXControlMode;
import com.ctre.phoenix.motorcontrol.can.VictorSPX;
import edu.wpi.first.wpilibj.DigitalInput;
import frc.robot.Constants.IntakeConstants;

/** Add your docs here. */
public class IntakeIOVictorSPX implements IntakeIO {

  public static final VictorSPX intake_motor = new VictorSPX(IntakeConstants.motorID);
  public static final DigitalInput intake_sensor = new DigitalInput(IntakeConstants.sensorID);
  private double appliedPercent;

  @Override
  public void updateInputs(IntakeIOInputs inputs) {
    inputs.appliedPercent = appliedPercent;
    inputs.isGPPresent = intake_sensor.get();
    // inputs.gyroYaw = sim.getHeading();
  }

  @Override
  public void runIntake(double percent) {
    appliedPercent = percent;
    intake_motor.set(VictorSPXControlMode.PercentOutput, percent);
  }

  @Override
  public void stopIntake() {
    appliedPercent = 0;
    intake_motor.set(VictorSPXControlMode.PercentOutput, 0);
  }
}
