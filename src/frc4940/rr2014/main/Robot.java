package frc4940.rr2014.main;
import edu.wpi.first.wpilibj.IterativeRobot;
import edu.wpi.first.wpilibj.RobotDrive;

public class Robot extends IterativeRobot {
	//Declares the Subsystems and other global variables
	XtremePro joystick = new XtremePro();
	Talons motors = new Talons();
	RobotDrive chassis = new RobotDrive(motors.Bae4, motors.Bae3, motors.Bae1, motors.Bae2);
	final int NULL = 0;
    
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
    	
    	//moves motor bases on twist
    	/*if(joystick.getTwist()>= 0.1 || joystick.getTwist()<= -0.1){
    		//motors.setBae1(joystick.getTwist());
    	}
    	//moves 
    	if(joystick.getYAxis() >= 0.1 || joystick.getYAxis()<= -0.1){
    		//motors.setBae1(joystick.getYAxis());
    	}
    	if((joystick.getYAxis()<= 0.1 && joystick.getYAxis()>= -0.1)&&(joystick.getTwist()<= 0.1 && joystick.getTwist()>= -0.1)){
    		motors.setBae1(0);
    	}
    	if(joystick.getTrigger()){
    		motors.setBae1(0.5);
    	}*/
    	
    	//checks if the joystick is in the dead zone. If so, the motors are stopped. Else, the motors work bases on the input
    	if(((-0.1 <= joystick.getXAxis()) && (joystick.getXAxis() <= 0.1)) || ((-0.1 <= joystick.getYAxis()) && (joystick.getYAxis() <= 0.1))){
    		chassis.mecanumDrive_Cartesian(NULL, NULL, NULL, NULL);
    	} else {
    		//checks if trigger is held, and uses twist rotation if true
    		if(joystick.getTrigger()){
    			chassis.mecanumDrive_Cartesian(joystick.getXAxis() , joystick.getYAxis(), joystick.getTwist(), NULL); //drives and rotates
    		} else {
    			chassis.mecanumDrive_Cartesian(joystick.getXAxis() , joystick.getYAxis(), NULL, NULL); //drives only, no rotation
    		}
    	}
    	
    }
    
    /**
     * This function is called periodically during test mode
     */
    public void testPeriodic() {
    	
    }
    
}
