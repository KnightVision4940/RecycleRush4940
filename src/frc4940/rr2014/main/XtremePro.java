package frc4940.rr2014.main;

import edu.wpi.first.wpilibj.Joystick;

public class XtremePro {
	Joystick controller = new Joystick (0);
	double getXAxis(){
		return controller.getRawAxis(1);
	}
	double getYAxis(){ 
		return controller.getRawAxis(2);
    }
	double getZAxis(){
    	return controller.getZ();
	}
	double getTwist(){
		return controller.getTwist();
	}
	boolean getTrigger(){
		return controller.getRawButton(1);
	}
	boolean getThumb(){
		return controller.getRawButton(2);
	}
	
}
