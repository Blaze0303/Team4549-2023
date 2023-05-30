// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

import frc.robot.Constants.OperatorConstants;
import frc.robot.subsystems.Drive;
import frc.robot.subsystems.Shooter;
import edu.wpi.first.wpilibj.PowerDistribution;
import edu.wpi.first.wpilibj.PowerDistribution.ModuleType;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.CommandBase;
import edu.wpi.first.wpilibj2.command.InstantCommand;
import edu.wpi.first.wpilibj2.command.button.CommandXboxController;
import edu.wpi.first.wpilibj2.command.button.Trigger;

/**
 * This class is where the bulk of the robot should be declared. Since Command-based is a
 * "declarative" paradigm, very little robot logic should actually be handled in the {@link Robot}
 * periodic methods (other than the scheduler calls). Instead, the structure of the robot (including
 * subsystems, commands, and trigger mappings) should be declared here.
 */
public class RobotContainer {
  // The robot's subsystems and commands are defined here...
  private final Drive m_drive = new Drive();
  private final Shooter m_shooter = new Shooter();
  private final SendableChooser<Command> m_autochooser = new SendableChooser<>();
  PowerDistribution m_pdp = new PowerDistribution(1, ModuleType.kRev);
  // Replace with CommandPS4Controller or CommandJoystick if needed
  private final CommandXboxController m_driverController =
      new CommandXboxController(OperatorConstants.kDriverControllerPort);
  /** The container for the robot. Contains subsystems, OI devices, and commands. */
  public RobotContainer() {
    m_drive.setDefaultCommand(
      m_drive.arcadeDriveCommand(()-> -0.75 * m_driverController.getLeftY(), ()-> -0.75 * m_driverController.getLeftX())
    );
      m_autochooser.setDefaultOption("Right Red", RightRed());
      m_autochooser.addOption("Middle Red", MiddleRed());
      m_autochooser.addOption("Left Red", LeftRed());
      m_autochooser.addOption("Right Blue", RightBlue());
      m_autochooser.addOption("Middle Blue", MiddleBlue());
      m_autochooser.addOption("Left Blue", LeftBlue());

      SmartDashboard.putData("Auto choices", m_autochooser);

    // Configure the trigger bindings
    configureBindings();

    double current14 = m_pdp.getCurrent(14);
    SmartDashboard.putNumber("Current Channel 14 ", current14);

    double current13 = m_pdp.getCurrent(13);
    SmartDashboard.putNumber("Current Channel 13 ", current13);

    double current12 = m_pdp.getCurrent(12);
    SmartDashboard.putNumber("Current Channel 12 ", current12);

    double current5 = m_pdp.getCurrent(5);
    SmartDashboard.putNumber("Current Channel 5 ", current5);

    double current6 = m_pdp.getCurrent(6);
    SmartDashboard.putNumber("Current Channel 6 ", current6);

    double current7 = m_pdp.getCurrent(7);
    SmartDashboard.putNumber("Current Channel 7 ", current7);
  }

  /**
   * Use this method to define your trigger->command mappings. Triggers can be created via the
   * {@link Trigger#Trigger(java.util.function.BooleanSupplier)} constructor with an arbitrary
   * predicate, or via the named factories in {@link
   * edu.wpi.first.wpilibj2.command.button.CommandGenericHID}'s subclasses for {@link
   * CommandXboxController Xbox}/{@link edu.wpi.first.wpilibj2.command.button.CommandPS4Controller
   * PS4} controllers or {@link edu.wpi.first.wpilibj2.command.button.CommandJoystick Flight
   * joysticks}.
   */
  
  private void configureBindings() {
    // Schedule `ExampleCommand` when `exampleCondition` changes to `true`
    
    // Schedule `exampleMethodCommand` when the Xbox controller's B button is pressed,
    // cancelling on release.
    m_driverController.y().whileTrue(m_shooter.FwdShoot(0.65));
    m_driverController.leftBumper().whileTrue(m_shooter.RevShoot(-1));
    m_driverController.a().whileTrue(m_shooter.FwdShoot(0.45));
    m_driverController.b().whileTrue(m_shooter.FwdShoot(0.48));
    m_driverController.x().whileTrue(m_shooter.FwdShoot(1));
    m_driverController.rightBumper().whileTrue(m_drive.arcadeDriveCommand(()-> -0.90 * m_driverController.getLeftY(), ()-> -0.90 * m_driverController.getLeftX()));
    m_driverController.rightTrigger().whileTrue(m_drive.arcadeDriveCommand(()-> 0.75 * m_driverController.getLeftY(), ()-> 0.75 * m_driverController.getLeftX()));
  }
  /**
   * Use this to pass the autonomous command to the main {@link Robot} class.
   *
   * @return the command to run in autonomous
   */
  public Command getAutonomousCommand() {
    // An example command will be run in autonomous
    return m_autochooser.getSelected();
    }
     /**  Auto Blue Alliance Station 1 */
  public CommandBase LeftBlue(){
    return  new InstantCommand(()->{}).withName("autoStarted")
    .andThen( m_shooter.FwdShoot(.48).withTimeout(0.5))

    .andThen(
      m_drive.run(() -> m_drive.drive.arcadeDrive(-0.5, 0)).withTimeout(2.75)
    )
    .andThen(
      m_drive.run(() -> m_drive.drive.arcadeDrive(0, 0.34)).withTimeout(.76)
    )
    .andThen(
      m_drive.run(() -> m_drive.drive.arcadeDrive(0, 0.34)).withTimeout(.65)
    ).withName("autoEnd");
  }
    /**  Auto Blue Alliance Station 2 */
    public CommandBase MiddleBlue(){
      return new InstantCommand(()->{}).withName("autoStarted")
      .andThen(
        m_shooter.FwdShoot(.48).withTimeout(1)
      )
      .andThen(
        m_drive.run(() -> m_drive.drive.arcadeDrive(-0.6, 0)).withTimeout(2.5)
        ) 
        .andThen(
          m_drive.run(() -> m_drive.drive.arcadeDrive(0, 0.34)).withTimeout(.76)
        ).withName("autoEnd");
    }
  /** Auto Blue Alliance Station 3  */
  public CommandBase RightBlue(){
    return new InstantCommand(()->{}).withName("autoStarted")
    .andThen(
      m_shooter.FwdShoot(.48).withTimeout(0.5)
    )
    .andThen(
      m_drive.run(() -> m_drive.drive.arcadeDrive(-0.5, 0)).withTimeout(2.75)
    )
    .andThen(
      m_drive.run(() -> m_drive.drive.arcadeDrive(0, 0.34)).withTimeout(.76)
    )
    .andThen(
      m_drive.run(() -> m_drive.drive.arcadeDrive(0, 0.34)).withTimeout(.65)
    ).withName("autoEnd");
    
  }
  /** Auto Red Alliance Station 1 */
public CommandBase LeftRed(){
  return new InstantCommand(()->{}).withName("autoStarted")
    .andThen(
      m_shooter.FwdShoot(.48).withTimeout(0.5)
    )
    .andThen(
      m_drive.run(() -> m_drive.drive.arcadeDrive(-0.5, 0)).withTimeout(2.75)
    )
    .andThen(
      m_drive.run(() -> m_drive.drive.arcadeDrive(0, 0.34)).withTimeout(.76)
    )
    .andThen(
      m_drive.run(() -> m_drive.drive.arcadeDrive(0, 0.34)).withTimeout(.65)
    ).withName("autoEnd");
}
  /**  Auto Red Alliance Station 2 */
  public CommandBase MiddleRed(){
    return new InstantCommand(()->{}).withName("autoStarted")
    .andThen(
       m_shooter.FwdShoot(.48).withTimeout(1)
      )
      .andThen(
        m_drive.run(() -> m_drive.drive.arcadeDrive(-0.6, 0)).withTimeout(2.5)
      )
      .andThen(
        m_drive.run(() -> m_drive.drive.arcadeDrive(0, 0.34)).withTimeout(.76)
      ).withName("autoEnd");
  }
/**  Auto Red Alliance Station 3 */
public CommandBase RightRed(){
  return new InstantCommand(()->{}).withName("autoStarted")
    .andThen(
      m_shooter.FwdShoot(.48).withTimeout(0.5)
    )
    .andThen(
      m_drive.run(() -> m_drive.drive.arcadeDrive(-0.5, 0)).withTimeout(2.75)
    )
    .andThen(
      m_drive.run(() -> m_drive.drive.arcadeDrive(0, 0.34)).withTimeout(.76)
    )
    .andThen(
      m_drive.run(() -> m_drive.drive.arcadeDrive(0, 0.34)).withTimeout(.65)
    ).withName("autoEnd");
}
}
