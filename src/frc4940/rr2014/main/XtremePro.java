package frc4940.rr2014.main;

import edu.wpi.first.wpilibj.Joystick;

public class XtremePro {
	Joystick controller = new Joystick (1);
	double getXAxis(){
		return controller.getX();
	}
	double getyAxis(){ 
       return controller.getY();	
	}
	
	
	
	
	
}
