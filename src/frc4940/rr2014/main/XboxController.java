package frc4940.rr2014.main;
import edu.wpi.first.wpilibj.Joystick;
public class XboxController {
	
	
	Joystick xbox = new Joystick(2);         //Wireless Controller
	Joystick xbox_movePad = new Joystick(1); //Wired Controller
	
	/**
	 * WIRED CONTROLS
	 */
	//Joysticks
	double getLeftX_movePad(){
		return xbox_movePad.getRawAxis(1);
	}
	double getLeftY_movePad(){
		return -1 * xbox_movePad.getRawAxis(2);
	}
	double getTrig_movePad(){
		return -1 * xbox_movePad.getRawAxis(3);
	}
	double getRightX_movePad(){
		return xbox_movePad.getRawAxis(4);
	}
	double getRightY_movePad(){
		return -1 * xbox_movePad.getRawAxis(5);
	}
	//Buttons
	boolean getAButton_movePad(){
    	return xbox_movePad.getRawButton(1);
	}
	boolean getBButton_movePad(){
    	return xbox_movePad.getRawButton(2);
	}
	boolean getXButton_movePad(){
    	return xbox_movePad.getRawButton(3);
	}
	boolean getYButton_movePad(){
    	return xbox_movePad.getRawButton(4);
	}
	boolean getRBButton_movePad(){
    	return xbox_movePad.getRawButton(6);
	}
	boolean getLBButton_movePad(){
    	return xbox_movePad.getRawButton(5);
	}
	
	/**
	 * WIRELESS CONTROLS
	 */
	//Joysticks
	double getLeftX(){
		return xbox.getRawAxis(1);
	}
	double getLeftY(){
		return -1 * xbox.getRawAxis(2);
	}
	double getTrig(){
		return -1 * xbox.getRawAxis(3);
	}
	double getRightX(){
		return xbox.getRawAxis(4);
	}
	double getRightY(){
		return -1 * xbox.getRawAxis(5);
	}
	//Buttons
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