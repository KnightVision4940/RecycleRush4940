
package frc4940.rr2014.main;

import edu.wpi.first.wpilibj.IterativeRobot;

public class Robot extends IterativeRobot {
	XtremePro joystick = new XtremePro();
	Talons motors = new Talons();
    /**
     * This function is run when the robot is first started up and should be
     * used for any initialization code.
     */
    public void robotInit() {
    	
    }

    /**
     * This function is called periodically during autonomous
     */
    public void autonomousPeriodic() {
    	motors.setBae1(0.4);
    }

    /**
     * This function is called periodically during operator control
     */
    public void teleopPeriodic() {
    	if(joystick.getTwist()>= 0.1 || joystick.getTwist()<= -0.1){
    		motors.setBae1(joystick.getTwist());
    	}
    	if(joystick.getYAxis()>= 0.1 || joystick.getYAxis()<= -0.1){
    		motors.setBae1(joystick.getYAxis());
    	}
    	if((joystick.getYAxis()<= 0.1 || joystick.getYAxis()>= -0.1)||(joystick.getTwist()<= 0.1 || joystick.getTwist()>= -0.1)){
    		motors.setBae1(0);
    	}
    	if(joystick.getTrigger()){
    		motors.setBae1(0.5);
    	}
    	
    }
    
    /**
     * This function is called periodically during test mode
     */
    public void testPeriodic() {
    	
    }
    
}
