package frc4940.rr2014.main;

import edu.wpi.first.wpilibj.Victor;
import edu.wpi.first.wpilibj.DigitalInput;

public class RearElevator 
{
	
	Victor LongElevator = new Victor(5);

	void set(double speed){
		LongElevator.set(speed);
	}
	
	//LIMIT SWITCHES
	private DigitalInput upperLimit = new DigitalInput(2);
	private DigitalInput lowerLimit = new DigitalInput(3);
	
	public boolean getUpperLimit(){
		return upperLimit.get();
	}
	public boolean getLowerLimit(){
		return lowerLimit.get();
	}
}