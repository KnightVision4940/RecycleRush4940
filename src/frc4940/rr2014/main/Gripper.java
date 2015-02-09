package frc4940.rr2014.main;

import edu.wpi.first.wpilibj.Victor;

public class Gripper {
	Victor Gripper = new Victor(6);
	
	void setGripper(double speed){
		Gripper.set(speed);
	}
}