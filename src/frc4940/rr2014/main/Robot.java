package frc4940.rr2014.main;

import edu.wpi.first.wpilibj.IterativeRobot;
import edu.wpi.first.wpilibj.RobotDrive;

public class Robot extends IterativeRobot {
	//Declares the Subsystems and other global variables
	XtremePro joystick = new XtremePro();
	Drivetrain drivetrain = new Drivetrain();
	RobotDrive drive = new RobotDrive(drivetrain.LFront, drivetrain.LBack, drivetrain.RFront, drivetrain.RBack);
	final int NULL = 0;
    XboxController xbox=new XboxController();
	/**
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
    	drive.setSafetyEnabled(false);

    	drive.drive(1, 0);
    }

    /**
     * This function is called periodically during operator control
     */
    public void teleopInit(){
    	drive.setSafetyEnabled(false);
    	/*
    	//Disables Safety for the Robots
    	drivetrain.RFront.setSafetyEnabled(false);
    	drivetrain.RBack.setSafetyEnabled(false);
    	drivetrain.LFront.setSafetyEnabled(false);
    	drivetrain.LBack.setSafetyEnabled(false);
    	*/
    }
    public void teleopPeriodic() {
    	/*
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
    	*/
    	
    	//checks if the joystick is in the dead zone. If so, the drive are stopped. Else, the drive work bases on the input
    	if(((-0.1 <= joystick.getXAxis()) && (joystick.getXAxis() <= 0.1)) || ((-0.1 <= joystick.getYAxis()) && (joystick.getYAxis() <= 0.1))){
    		drive.mecanumDrive_Cartesian(0, 0, 0, 0);
    	} else {
    		//checks if trigger is held, and uses twist rotation if true
    		if(joystick.getTrigger()){
    			drive.mecanumDrive_Cartesian(joystick.getXAxis() , joystick.getYAxis(), joystick.getTwist(), NULL); //drives and rotates
    		} else {
    			drive.mecanumDrive_Cartesian(joystick.getXAxis() , joystick.getYAxis(), NULL, NULL); //drives only, no rotation
    		}
    	}
    	//drivetrain.setBae1(xbox.getLeftX());
    }
    
    /**
     * This function is called periodically during test mode
     */
    public void testPeriodic() {
    	
    }
    
}
