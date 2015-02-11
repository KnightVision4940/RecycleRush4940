package frc4940.rr2014.main;

import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.Victor;

public class Gripper {
	Victor Gripper = new Victor(6);
	
	void set(double speed){
		Gripper.set(speed);
	}
	
	//LIMIT SWITCHES
	private DigitalInput openLimit = new DigitalInput(4);
	private DigitalInput closedLimit = new DigitalInput(5);
	
	public boolean getOpenLimit(){
		return openLimit.get();
	}
	public boolean getClosedLimit(){
		return closedLimit.get();
	}
}