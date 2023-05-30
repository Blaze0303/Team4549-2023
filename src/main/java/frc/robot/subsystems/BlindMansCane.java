// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.NeutralMode;
import com.ctre.phoenix.motorcontrol.can.VictorSPX;

import edu.wpi.first.wpilibj2.command.CommandBase;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
public class BlindMansCane extends SubsystemBase {
  VictorSPX blindmanscaneA = new VictorSPX(8);
  VictorSPX blindmanscaneB = new VictorSPX(15);
  /** Creates a new BlindMansCane. */
  public BlindMansCane() {
    blindmanscaneA.setInverted(false);
    blindmanscaneA.setNeutralMode(NeutralMode.Brake);
   /*  setDefaultCommand(runOnce(()->{
      blindmanscaneA.set(ControlMode.PercentOutput,0);
      blindmanscaneB.set(ControlMode.PercentOutput,0);
      
    }).andThen(run(()->{}))); */
    blindmanscaneB.setInverted(true);
    blindmanscaneB.setNeutralMode(NeutralMode.Brake);
   
      
 
  }
  /** Blind mans cane Forward
   * @param fwd Brins pole in front of robot
   * @return fwdblindmanscane
   */
  public CommandBase fwdblindmanscaneA(Double fwd){
    return runOnce(()->blindmanscaneA.set(ControlMode.PercentOutput,fwd));
  }
  /** Blind mans cane reverse
   * @param rev reteruns pole to up right posishing 
   * @return revblindmanscane
    */
  public CommandBase revblindmanscaneA(Double rev){
    return runOnce(()->blindmanscaneA.set(ControlMode.PercentOutput,rev * -1));
  }
  /** Blind mans cane Forward
   * @param fwd Brins pole in front of robot
   * @return fwdblindmanscane
   */
  public CommandBase fwdblindmanscaneB(Double fwd){
    return runOnce(()->blindmanscaneB.set(ControlMode.PercentOutput,fwd));
  }
  /** Blind mans cane reverse
   * @param rev reteruns pole to up right posishing 
   * @return revblindmanscane
    */
  public CommandBase revblindmanscaneB(Double rev){
    return runOnce(()->blindmanscaneB.set(ControlMode.PercentOutput,rev * -1));
  }

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
  }
}
