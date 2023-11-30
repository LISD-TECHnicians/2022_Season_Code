package frc.robot;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.motorcontrol.PWMVictorSPX;
import edu.wpi.first.wpilibj.TimedRobot;
import edu.wpi.first.wpilibj.drive.MecanumDrive;
import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.cameraserver.CameraServer;
import edu.wpi.first.wpilibj.DoubleSolenoid;
//import edu.wpi.first.wpilibj.Solenoid;

public class Robot extends TimedRobot {
  private static final int FrontLeftChannel = 0;
  private static final int RearLeftChannel = 1;
  private static final int FrontRightChannel = 8;
  private static final int RearRightChannel = 3;
  private static final int JoystickChannel = 0;
  private static final int JoystickChannel2 = 1;
  private static final int IntakeChannel = 5;
  private static final int ClimberChannel = 6;
  private final PWMVictorSPX intake = new PWMVictorSPX(IntakeChannel);
  private final PWMVictorSPX climberWinch = new PWMVictorSPX(ClimberChannel);
  private final DoubleSolenoid intakeFlipper = new DoubleSolenoid(null, 6, 4);
  private final DoubleSolenoid catapult = new DoubleSolenoid(null, 1,3);
  private final PWMVictorSPX frontLeft = new PWMVictorSPX(FrontLeftChannel);
  private final  PWMVictorSPX rearLeft = new PWMVictorSPX(RearLeftChannel);
  private final  PWMVictorSPX frontRight = new PWMVictorSPX(FrontRightChannel);
  private final  PWMVictorSPX rearRight = new PWMVictorSPX(RearRightChannel);
  private final MecanumDrive robotDrive = new MecanumDrive(frontLeft, rearLeft, frontRight, rearRight);
  private final Joystick joystick = new Joystick(JoystickChannel);
  private final Joystick joystick2 = new Joystick(JoystickChannel2);
  private final Timer timer = new Timer();
  
  @Override
  public void robotInit() {
    CameraServer.startAutomaticCapture();
    CameraServer.startAutomaticCapture(1);
  }

  @Override
  public void autonomousInit(){
    timer.reset();
    timer.start();
  }

  @Override
  public void autonomousPeriodic(){                            

   if(timer.get() > .5 && timer.get() < 1.5) {
    intakeFlipper.set(DoubleSolenoid.Value.kReverse);
   }
   if(timer.get() > 1.6 && timer.get() < 2.3){
    intakeFlipper.set(DoubleSolenoid.Value.kForward);
   }
   if(timer.get() > 2.5 && timer.get() < 2.7){
     catapult.set(DoubleSolenoid.Value.kForward);
   }
   if(timer.get() > 2.8 && timer.get() < 3){
     catapult.set(DoubleSolenoid.Value.kReverse);
   }
   if(timer.get() > 3.1 && timer.get() < 6.5){
    frontLeft.set(-.205);
    rearLeft.set(-.205);
    frontRight.set(.205);
    rearRight.set(.205);
    intake.set(0.5);
    }
  if(timer.get() > 6.6 && timer.get() < 6.9){
    intake.set(.5);
  }
  if(timer.get() > 7 && timer.get() < 7.5){
    frontLeft.set(.205);
    rearLeft.set(.205);
    rearLeft.set(-.205);
    rearRight.set(-.205);
  }
  if(timer.get() > 7.6 && timer.get() < 7.8){
    catapult.set(DoubleSolenoid.Value.kForward);
  }
  if(timer.get() > 7.9 && timer.get() < 8){
    catapult.set(DoubleSolenoid.Value.kReverse);
  }
 }

  @Override
  public void teleopPeriodic() {
    robotDrive.driveCartesian(joystick.getX(), -joystick.getY(), joystick.getZ(), 0.0);
    if(joystick.getRawButton(1)){
      catapult.set(DoubleSolenoid.Value.kForward);
    }
    else{
      catapult.set(DoubleSolenoid.Value.kReverse);
    }
   if(joystick.getRawButton(5)){
      intake.set(.5);
    }
    else if(joystick.getRawButton(3)){
      intake.set(-.5);
    }
    else{
      intake.set(0);
    }
    if(joystick2.getRawButton(4)){
      climberWinch.set(1);
    }
    else if(joystick2.getRawButton(6)){
      climberWinch.set(-1);
    }
    else{
      climberWinch.set(0);
    }
    if(joystick.getRawButton(11)){
      intakeFlipper.set(DoubleSolenoid.Value.kReverse);
    }
    else{
      intakeFlipper.set(DoubleSolenoid.Value.kForward);
    }
  }
}




/*    BACKWARDS
  frontLeft.set(-.205);
  rearLeft.set(-.205);
  frontRight.set(.205);
  rearRight.set(.205);
*/

/*    FORWARDS
  frontLeft.set(.205);
  rearLeft.set(.205);
  frontRight.set(-.205);
  rearRight.set(-.205);
*/