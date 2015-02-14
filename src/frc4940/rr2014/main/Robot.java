package frc4940.rr2014.main;
import edu.wpi.first.wpilibj.IterativeRobot;
import edu.wpi.first.wpilibj.Timer;

public class Robot extends IterativeRobot {
	//SUBSYSTEMS / CLASSES
	XtremePro joystick = new XtremePro();
	Drivetrain drivetrain = new Drivetrain();
	Elevator shortElev = new Elevator();
	RearElevator tallElev = new RearElevator();
	Gripper gripper = new Gripper();
    XboxController xbox = new XboxController();
    Mecanum mecanum = new Mecanum();
    AngleCalc angles = new AngleCalc();
    
    //CONSTANTS
    final double OFFSET = 0.1;
	final double NULL = 0.0;
	final double DEADZONE = 0.1;
	final int AUTONOMOUS_MODE = 1;
	
	//OTHER GLOBAL VARIABLES
	int gripperState = 0; //0 is Bumper Control, 1 is closing to tote, 2 is opening to maximum
	/**
     * This function is run when the robot is first started up and should be
     * used for any initialization code.
     */
    public void robotInit() {
    	//inverts right-hand side motors
    	//drive.setInvertedMotor(RobotDrive.MotorType.kFrontRight, true);
        //drive.setInvertedMotor(RobotDrive.MotorType.kFrontLeft, true);
        mecanum.init(0, 0);
    }

    /**
     * This function is called periodically during autonomous
     */
    public void autonomousPeriodic() {
    	/*if(AUTONOMOUS_MODE == 1){ //Drives Forwards
    		drive.mecanumDrive_Cartesian(0.25, NULL, NULL, NULL);
    		Timer.delay(5);
    		drive.mecanumDrive_Cartesian(NULL, NULL, NULL, NULL);
    	}*/
    }
    
    /**
     * This function is called periodically during operator control
     */
    public void teleopInit(){}
    
    public void teleopPeriodic() {
    	/**
    	 * MOVEMENT PAD CONTROLS
    	 * Joystick
    	 */
    	
    	//if trigger is held, rotates. Otherwise, drives/strafes
    	if(!joystick.getTrigger()){
    		//Standard motion
    		if (!joystick.getThumb()) mecanum.drive(mecanum.getJoystickMagnitude(), mecanum.getJoystickDirection(), 0, false);
    		//Reversed motion
    		else mecanum.drive(mecanum.getJoystickMagnitude(), angles.oppAngle(mecanum.getJoystickDirection()), 0, false);
    	}
    	//Rotation-Only motion
    	else mecanum.drive(0, 0, (float)joystick.getTwist(), false);
    	
    	
    	/**
    	 * ElEVATOR PAD CONTROLS
    	 */
    	//Controls the tall elevator, A and B buttons
    	if(xbox.getAButton()){ //A Button
    		if(!tallElev.getLowerLimit()){
    			tallElev.set(-OFFSET);
    		}
    		else {
    			tallElev.set(0.3); //Goes Down
    		}
    	}
    	else if(xbox.getBButton()){ //B Button
    		if(!tallElev.getUpperLimit()){
    			tallElev.set(-OFFSET);
    		}
    		else {
    			tallElev.set(-0.5); //Goes Up
    		}
    	}
    	else{
    		tallElev.set(-OFFSET);
    	}
    	
    	//Controls the short elevator, X and Y Buttons
    	if(xbox.getXButton()){ //X Button
    		if(!shortElev.getLowerLimit()){
    			shortElev.set(0);
    		}
    		else {
    			shortElev.set(-1.0); //Goes Down
    		}
    	}
    	else if(xbox.getYButton()){ //Y Button
    		if(!shortElev.getUpperLimit()){
    			shortElev.set(0);
    		}
    		else {
    			shortElev.set(1.0); //Goes Up
    		}
    	}
    	else{
    		shortElev.set(0);
    	}
    	
    	//Uses the gripper, Bumpers
    	if(gripperState == 0){ //Code for the Bumper buttons
    		if(xbox.getLBButton()){ //Left Bumper
        		if(!gripper.getClosedLimit()) gripper.set(0);
        		else gripper.set(-1);
        	}
        	else if(xbox.getRBButton()){ //Right Bumper
        		if(!gripper.getOpenLimit()) gripper.set(0);
        		else gripper.set(1);
        	}
        	else gripper.set(0);
    	} 
    	/*
    	else if(gripperState == 1){ //Code for the start button
    		if(!gripper.getToteLimit()) gripper.set(0); gripperState = 0;
        	else gripper.set(-1); 
    	}
    	else if(gripperState == 2){ //Code for the select button
    		if(!gripper.getOpenLimit()) gripper.set(0);	gripperState = 0;
        	else gripper.set(1); 
    	}
    	*/
    	
    	
    	//INSERT CODE FOR WHEELS
    	//Stew is God
    }
    	
    	
    
    /**
     * This function is called periodically during test mode
     */
    public void testPeriodic() {}
    
}


//God Bless Kamen
//Hashtag Swag