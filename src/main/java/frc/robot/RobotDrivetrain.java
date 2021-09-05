package frc.robot;

//Imports
import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import edu.wpi.first.wpilibj.SpeedControllerGroup;
//Must install rev robotics library. Follow directions on site - EG
//https://docs.revrobotics.com/sparkmax/software-resources/spark-max-api-information#java-api
//Help docs https://www.revrobotics.com/content/sw/max/sw-docs/java/com/revrobotics/package-summary.html
import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;


//The goal of this class is to create methods that can be used with drivetrain
//This will clean up the code in Robot.java and will allow for easier future fixes - EG
public class RobotDrivetrain {
  
  //Initializes all motors
  //Assumes killswitch is at front of robot
  //The first parameter refers to the CAN ID
  //MotorType.kBrushless MUST be used when using NEO brushless motors
  private CANSparkMax leftFront = new CANSparkMax(1, MotorType.kBrushless);
  private CANSparkMax leftRear = new CANSparkMax(4, MotorType.kBrushless);
  private CANSparkMax rightFront = new CANSparkMax(2, MotorType.kBrushless);
  private CANSparkMax rightRear = new CANSparkMax(3, MotorType.kBrushless);
  
  //Groups left side speed controllers together and right side speed controllers together
  //This is cleaner than setting up two different differential drives
  private SpeedControllerGroup driveLeft = new SpeedControllerGroup(leftFront, leftRear);
  private SpeedControllerGroup  driveRight = new SpeedControllerGroup(rightFront, rightRear);
  private DifferentialDrive robotDrive = new DifferentialDrive(driveLeft, driveRight);

  public RobotDrivetrain(){} //constructor

  public void arcadeDrive(double xSpeed, double zRotation){
    robotDrive.arcadeDrive(xSpeed, zRotation);
  }

  public void tankDrive(double leftSpeed, double rightSpeed){
    robotDrive.tankDrive(leftSpeed, rightSpeed);
  }

  public void curvatureDrive(double xSpeed, double zRotation, boolean isQuickTurn){
    robotDrive.curvatureDrive(xSpeed, zRotation, isQuickTurn);
  }

    /*
    * D-Pad setup - these are used for small movements
    * 0 = up arrow (forward)
    * 90 = right arrow (right)
    * 180 = down arrow (backward)
    * 270 = left arrow (left)
    * speeds can be adjusted
    * first parameter is the forward/backward speed
    * Setting equal to 0 for left/right ensures it stays in place
    */
  public void dPadGetter(int dPad){
    if (dPad==0){
      robotDrive.arcadeDrive(0.5, 0); //forward
    }
    if (dPad==90){
      robotDrive.arcadeDrive(0, 0.5); //right
    }
    if (dPad==180){
      robotDrive.arcadeDrive(-0.5, 0); //reverse
    }
    if (dPad==270){
      robotDrive.arcadeDrive(0, -0.5); //left
    }
  }

  public void resetEncoders(){
    leftFront.getEncoder().setPosition(0);
    leftRear.getEncoder().setPosition(0);
    rightFront.getEncoder().setPosition(0);
    rightRear.getEncoder().setPosition(0); 
  }

}