package frc4940.rr2014.main;

import edu.wpi.first.wpilibj.Joystick;

public class XtremePro {
	Joystick controller = new Joystick (0);
	double getXAxis(){
		return controller.getX();
	}
	double getyAxis(){ 
       return controller.getY();
    
    }
	double getzAxis(){
    	return controller.getZ();
    	
	}
	boolean getTrigger(){
		return controller.getRawButton(1);
		
	}
	boolean getThumb(){
		return controller.getRawButton(2);
	}
	
}
