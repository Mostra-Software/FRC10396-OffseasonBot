// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems.intake;

import edu.wpi.first.math.system.plant.DCMotor;
import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.simulation.DCMotorSim;
import edu.wpi.first.wpilibj.simulation.DIOSim;
import frc.robot.subsystems.intake.IntakeIO.IntakeIOInputs;

/** Add your docs here. */
public class IntakeIOSim implements IntakeIO {
  private DCMotor intakeMotor = DCMotor.getCIM(1);
  private DCMotorSim sim = new DCMotorSim(intakeMotor, 24. / 35., 0.000151);
  private double appliedPercent = 0.0;
  private boolean isGPPresent = false;
  private DigitalInput ls = new DigitalInput(0);
  private DIOSim limitSwitch = new DIOSim(ls);

  @Override
  public void updateInputs(IntakeIOInputs inputs) {
    sim.update(0.02);
    inputs.appliedPercent = appliedPercent;
    inputs.isGPPresent = limitSwitch.getValue();
    // inputs.gyroYaw = sim.getHeading();
  }

  @Override
  public void runIntake(double percent) {
    appliedPercent = percent;
    sim.setInputVoltage(percent);
  }

  @Override
  public void stopIntake() {
    appliedPercent = 0;
    sim.setInputVoltage(0);
  }
}
