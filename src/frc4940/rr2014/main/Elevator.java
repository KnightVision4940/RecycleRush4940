package frc4940.rr2014.main;
import edu.wpi.first.wpilibj.Victor;
import edu.wpi.first.wpilibj.DigitalInput;

public class Elevator {
	
	Victor shortElevator = new Victor(4);
	
	void set(double speed){
		shortElevator.set(speed);
	}
	
	//Limit Switches
	private DigitalInput upperLimit = new DigitalInput(0);
	private DigitalInput lowerLimit = new DigitalInput(1);
	private DigitalInput midTote = new DigitalInput(6);
	
	public boolean getUpperLimit(){
		return upperLimit.get();
	}
	public boolean getLowerLimit(){
		return lowerLimit.get();
	}
	public boolean getMidTote(){
		return midTote.get();
	}
}