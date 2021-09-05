package frc.robot;

//Imports
import edu.wpi.first.wpilibj.Spark;
import edu.wpi.first.wpilibj.DoubleSolenoid.Value;
import edu.wpi.first.wpilibj.DoubleSolenoid;

//Subsystem class is general - specific methods for each subsystem
public class Subsystem {
    
    //Wheel Spin Variables
    private Spark wheelSpin = new Spark(0); //need pwm channel
    private DoubleSolenoid wheelSpinLift = new DoubleSolenoid(/*the forward pcm id*/1, /*the reverse pcm id*/2);

    //Constructor
    public Subsystem(){}

    //Wheel Spin Method
    public void wheelSpin(boolean wheelLift, boolean wheelLower, boolean spinRight, boolean spinLeft){
        //Controls pneumatic lifting mechanism
        //May need to nest the spinning control inside the if statement for wheelLift
        if(wheelLift){
            wheelSpinLift.set(Value.kForward);
        }
        else if(wheelLower){
            wheelSpinLift.set(Value.kReverse);
        }
        //Controls motor that spins wheel
        if (spinRight){
            wheelSpin.set(0.5);
        }
        else if (spinLeft){
            wheelSpin.set(-0.5);
        }
    }


}
