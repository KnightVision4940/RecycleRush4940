package frc4940.rr2014.main;
import edu.wpi.first.wpilibj.Joystick;
public class XboxController {
Joystick xbox = new Joystick(1);
	
	double getLeftX(){
		return xbox.getRawAxis(1);
	}
	double getLeftY(){
		return xbox.getRawAxis(2);
	}
	double getLTrig(){
		return xbox.getRawAxis(3);
	}
	double getRTrig(){
		return xbox.getRawAxis(4);
	}
	double getRightX(){
		return xbox.getRawAxis(5);
	}
	double getRightY(){
		return xbox.getRawAxis(6);
	}
	
	
	boolean getAButton(){
    	return xbox.getRawButton(1);
	}
	boolean getBButton(){
    	return xbox.getRawButton(2);
	}
	boolean getXButton(){
    	return xbox.getRawButton(3);
	}
	boolean getYButton(){
    	return xbox.getRawButton(4);
	}
	boolean getRBButton(){
    	return xbox.getRawButton(6);
	}
	boolean getLBButton(){
    	return xbox.getRawButton(5);
	}
}