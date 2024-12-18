package frc.robot.subsystems.drive;

import com.ctre.phoenix.motorcontrol.can.WPI_VictorSPX;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import frc.robot.Constants.DriveConstants;

public class DriveIOVictorSPX implements DriveIO {

  private final WPI_VictorSPX leftMaster = new WPI_VictorSPX(0);
  private final WPI_VictorSPX leftSlave = new WPI_VictorSPX(0);
  private final WPI_VictorSPX rightMaster = new WPI_VictorSPX(0);
  private final WPI_VictorSPX rightSlave = new WPI_VictorSPX(0);
  private boolean isTurnInPlaceAllowed = false;

  public final DifferentialDrive drive = new DifferentialDrive(leftMaster, rightMaster);

  public DriveIOVictorSPX() {
    leftMaster.setInverted(DriveConstants.leftMasterReversed);
    leftMaster.setNeutralMode(DriveConstants.neutralMode);

    leftSlave.setInverted(DriveConstants.leftSlaveReversed);
    leftSlave.setNeutralMode(DriveConstants.neutralMode);

    rightMaster.setInverted(DriveConstants.rightMasterReversed);
    rightMaster.setNeutralMode(DriveConstants.neutralMode);

    rightSlave.setInverted(DriveConstants.rightSlaveReversed);
    rightSlave.setNeutralMode(DriveConstants.neutralMode);

    drive.setDeadband(0.05);

    rightSlave.follow(rightMaster);
    leftSlave.follow(leftMaster);
  }

  @Override
  public void updateInputs(DriveIOInputs inputs) {
    inputs.leftAppliedVolts = leftMaster.getMotorOutputVoltage();
    inputs.rightAppliedVolts = rightMaster.getMotorOutputVoltage();
    inputs.isTurnInPlaceAllowed = isTurnInPlaceAllowed;
  }

  @Override
  public void setVoltage(double leftVolts, double rightVolts) {
    leftMaster.setVoltage(leftVolts);
    rightMaster.setVoltage(rightVolts);
  }

  @Override
  public void setCurvatureTurnInPlace(boolean isAllowed) {
    isTurnInPlaceAllowed = isAllowed;
  }
}
