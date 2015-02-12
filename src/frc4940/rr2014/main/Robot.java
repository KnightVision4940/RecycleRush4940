package frc4940.rr2014.main;
import edu.wpi.first.wpilibj.IterativeRobot;
import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.RobotDrive;

public class Robot extends IterativeRobot {
	//Declares the Subsystems and other global variables
	XtremePro joystick = new XtremePro();
	Drivetrain drivetrain = new Drivetrain();
	Elevator shortElev = new Elevator();
	RearElevator tallElev = new RearElevator();
	Gripper gripper = new Gripper();
	RobotDrive drive = new RobotDrive(drivetrain.LFront, drivetrain.LBack, drivetrain.RFront, drivetrain.RBack);
    XboxController xbox = new XboxController();
    
    //CONSTANTS
    final double OFFSET = 0.1;
	final double NULL = 0.0;
	final double DEADZONE = 0.1;
	final int AUTONOMOUS_MODE = 1;
	
	/**
     * This function is run when the robot is first started up and should be
     * used for any initialization code.
     */
    public void robotInit() {
    	//inverts right-hand side motors
    	drive.setInvertedMotor(RobotDrive.MotorType.kFrontRight, true);
        drive.setInvertedMotor(RobotDrive.MotorType.kFrontLeft, true);
    }

    /**
     * This function is called periodically during autonomous
     */
    public void autonomousPeriodic() {
    	if(AUTONOMOUS_MODE == 1){ //Drives Forwards
    		drive.mecanumDrive_Cartesian(0.25, NULL, NULL, NULL);
    		Timer.delay(5);
    		drive.mecanumDrive_Cartesian(NULL, NULL, NULL, NULL);
    	}
    	
    }
    
    /**
     * This function is called periodically during operator control
     */
    public void teleopInit(){
    	//Disables Safety for the Robots
    	drivetrain.RFront.setSafetyEnabled(false);
    	drivetrain.RBack.setSafetyEnabled(false);
    	drivetrain.LFront.setSafetyEnabled(false);
    	drivetrain.LBack.setSafetyEnabled(false);
    	
    }
    public void teleopPeriodic() 
    {
    	/**
    	 * MOVEMENT PAD CONTROLS
    	 */
    	//drives the robot using mecanum wheels and joysticks
    	//if((xbox.getLeftX_movePad() > DEADZONE || xbox.getLeftX_movePad() < -DEADZONE) || (xbox.getLeftY_movePad() > DEADZONE || xbox.getLeftY_movePad() < -DEADZONE)){
    	drive.mecanumDrive_Cartesian(0.5 * joystick.getXAxis(), 0.5 * joystick.getYAxis(), NULL, NULL);
    	//} else drive.mecanumDrive_Cartesian(NULL,NULL,NULL,NULL);
    	//Right bumper, rotates right
    	if(xbox.getRBButton_movePad()){
    		drive.mecanumDrive_Cartesian(NULL, NULL, 1.0, NULL);
    	}
    	//Left bumper, rotates left
    	if(xbox.getLBButton_movePad()){
    		drive.mecanumDrive_Cartesian(NULL, NULL, -1.0, NULL);
    	}
    	
    	/**
    	 * ElEVATOR PAD CONTROLS
    	 */
    	//Controls the tall elevator
    	if(xbox.getAButton()) tallElev.set(0.3);
    	else if (xbox.getBButton()) tallElev.set(-0.5);
    	else tallElev.set(-OFFSET);
    	
    	
    	if(xbox.getAButton()){
    		if(!tallElev.getLowerLimit()){
    			tallElev.set(-OFFSET);
    		}
    		else {
    			tallElev.set(0.3); //Goes Down
    		}
    	}
    	else if(xbox.getYButton()){
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
    	if(xbox.getXButton()){
    		if(!shortElev.getLowerLimit()){
    			shortElev.set(0);
    		}
    		else {
    			shortElev.set(-0.5); //Goes Down
    		}
    	}
    	else if(xbox.getYButton()){
    		if(!shortElev.getUpperLimit()){
    			shortElev.set(0);
    		}
    		else {
    			shortElev.set(0.5); //Goes Up
    		}
    	}
    	else{
    		shortElev.set(0);
    	}
    	
    	//Closes the gripper, Bumpers
    	if(xbox.getLBButton()){
    		if(!gripper.getClosedLimit()){
    			gripper.set(0);
    		}
    		else {
    			gripper.set(-1);
    		}
    	}
    	else if(xbox.getRBButton()){
    		if(!gripper.getOpenLimit()){
    			gripper.set(0);
    		}
    		else {
    			gripper.set(1);
    		}
    	}
    	else{
    		gripper.set(0);
    	}
    	
    	//INSERT CODE FOR WHEELS
    	
    }
    
    /**
     * This function is called periodically during test mode
     */
    public void testPeriodic() {
    	/**
    	 * DIFFERENT DRIVE METHODS. 
    	 * UNCOMMENT ONE COMMENT BLOCK AT A TIME
    	 * TO TEST THEM OUT
    	 * REMEMBER TO COMMENT IT BACK AGAIN WHEN CHANGING METHODS
    	 */
    	
    	//drive.mecanumDrive_Cartesian(joystick.getXAxis(), joystick.getYAxis(), joystick.getTwist(), NULL);
    	
    	//drive.mecanumDrive_Cartesian(joystick.getYAxis(), joystick.getXAxis(), joystick.getTwist(), NULL);
    	
    	//drive.mecanumDrive_Cartesian(xbox.getLeftX(), xbox.getLeftY(), xbox.getRightX, NULL);
    	
    	//drive.mecanumDrive_Cartesian(xbox.getLeftY(), xbox.getLeftX(), xbox.getRightX, NULL);
    	
    	//drive.mecanumDrive_Cartesian(joystick.getZAxis(), joystick.getYAxis(), joystick.getTwist(), NULL);
    	
    	//drive.mecanumDrive_Cartesian(joystick.getYAxis(), joystick.getZAxis(), joystick.getTwist(), NULL);
    	
    }
    
}
