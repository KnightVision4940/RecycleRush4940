package frc4940.rr2014.main;
import edu.wpi.first.wpilibj.IterativeRobot;
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
	final int NULL = 0;
	final double DEADZONE = 0.1;
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
    public void autonomousPeriodic() {}
    
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
    		drive.mecanumDrive_Cartesian(NULL, xbox.getLeftY_movePad(), NULL, NULL);
    	//} else drive.mecanumDrive_Cartesian(NULL,NULL,NULL,NULL);
    	//Right bumper, rotates right
    	if(xbox.getRBButton_movePad()){
    		drive.mecanumDrive_Cartesian(NULL, NULL, 0.4, NULL);
    	}
    	//Left bumper, rotates left
    	if(xbox.getLBButton_movePad()){
    		drive.mecanumDrive_Cartesian(NULL, NULL, -0.4, NULL);
    	}
    	
    	/**
    	 * ElEVATOR PAD CONTROLS
    	 */
    	//Controls the tall elevator
    	if(xbox.getAButton()) tallElev.set(0.3);
    	else if (xbox.getBButton()) tallElev.set(-0.5);
    	else tallElev.set(-OFFSET);
    	//Controls the short elevator
    	if(xbox.getRightY() > DEADZONE || xbox.getRightY() < -DEADZONE){
    		shortElev.set(xbox.getRightY());
    	} else {
    		shortElev.set(0);
    	}
    	//Closes the gripper, L Bumper
    	if(xbox.getLBButton()){
    		gripper.set(-1);
    	} else {
    		gripper.set(0);
    	}
    	//Opens the gripper, R Bumper
    	if(xbox.getRBButton()){
    		gripper.set(1);
    	} else {
    		gripper.set(0);
    	}
    	
    	//INSERT CODE FOR WHEELS
    	
    	/**
    	 * SCRAP CODE
    	 * EXPERIMENTATION
    	 * ALL COMMENTED OUT
    	 */
    	/*
    	if(xbox.getAButton()){
    		elev.set(0.75);
    	} 
    	
    	if(xbox.getXButton()){
    		elev.set(-0.75);
    	}
    	
    	if(!xbox.getXButton() && !xbox.getAButton()){
        		elev.set(0.0);  //lower limit
    	}
    	
    	
    	if(xbox.getBButton()){
    		rearelev.setLongElev(0.2);
    	}
    	if(xbox.getYButton()){
    		rearelev.setLongElev(-0.5);
    	}
    	if(!xbox.getBButton() && !xbox.getYButton()) {
    		rearelev.setLongElev(REAR_ELEV_HOLDING_VALUE);
	}
    	/*
    	if(xbox.getXButton()){
    		drivetrain.LBack.set(0.75);
    	} else {
    		drivetrain.LBack.set(0.0);
	}
    	if(xbox.getYButton()){
    		drivetrain.LFront.set(0.75);
    	} else {
    		drivetrain.LFront.set(0.0);
	}*/
    	/*
    	if(xbox.getLBButton()){
    		RearGripper.setGripper(1);
    	}
    	if(xbox.getRBButton()){
    		RearGripper.setGripper(-1);
    	}
    	if(!xbox.getLBButton() && !xbox.getRBButton()){
    		RearGripper.setGripper(0.0);
    	}
    	//checks if the joystick is in the dead zone. If so, the drive are stopped. Else,
    	//the drive work bases on the input
    	/*
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
    	*/
    	//drivetrain.setBae1(xbox.getLeftX());
    }
    
    /**
     * This function is called periodically during test mode
     */
    public void testPeriodic() {
    	
    	if(shortElev.getUpperLimit()) shortElev.set(0.5);
    	else if (shortElev.getLowerLimit()) shortElev.set(-0.5);
    	else shortElev.set(0);
    	
    	if(tallElev.getUpperLimit()) tallElev.set(-0.4);
    	else if (tallElev.getLowerLimit()) tallElev.set(0.2);
    	else tallElev.set(OFFSET);
    	
    	if(gripper.getClosedLimit()) gripper.set(1);
    	else if (gripper.getOpenLimit()) gripper.set(-1);
    	else gripper.set(0);
    	//drive.mecanumDrive_Cartesian(0.5 * xbox.getLeftX_movePad(),  0.5 * xbox.getLeftY_movePad(), 0.5 * xbox.getRightX_movePad(), NULL);
    	/*
    	if(xbox.getAButton_movePad()){
    		drivetrain.RFront.set(1);
    		drivetrain.RBack.set(1);
    		drivetrain.LFront.set(1);
    		drivetrain.LBack.set(1);
    	}
    	else if(xbox.getBButton_movePad()){
    		drivetrain.RFront.set(-1);
    		drivetrain.RBack.set(-1);
    		drivetrain.LFront.set(-1);
    		drivetrain.LBack.set(-1);
    	}
    	else{
    		drivetrain.RFront.set(0);
    		drivetrain.RBack.set(0);
    		drivetrain.LFront.set(0);
    		drivetrain.LBack.set(0);
    	}*/
    	/*
    	drivetrain.RFront.set(joystick.getXAxis());
    	drivetrain.RBack.set(joystick.getXAxis());
    	drivetrain.LFront.set(joystick.getXAxis());
    	drivetrain.LBack.set(joystick.getXAxis());
    	*/
    	
    	/*
    	if(xbox.getAButton_movePad()){
    		drivetrain.RBack.set(-1);
    	} 
    	else if
    		{
        		drivetrain.RBack.set(1);
        	} 
    		else {
        		drivetrain.RBack.set(0);
        	}
    	}
    	if(xbox.getBButton_movePad()){
    		drivetrain.RFront.set(-1);
    	} else {
    		drivetrain.RFront.set(0);
    	}
    	if(xbox.getXButton_movePad()){
    		drivetrain.LBack.set(1);
    	} else {
    		drivetrain.LBack.set(0);
    	}
    	if(xbox.getYButton_movePad()){
    		drivetrain.LFront.set(1);
    	} else {
    		drivetrain.LFront.set(0);
    	}
    	
    	
    	if(xbox.getBButton()){
    		drivetrain.RFront.set(1);
    	} else {
    		drivetrain.RFront.set(0);
    	}
    	if(xbox.getXButton()){
    		drivetrain.LBack.set(-1);
    	} else {
    		drivetrain.LBack.set(0);
    	}
    	if(xbox.getYButton()){
    		drivetrain.LFront.set(-1);
    	} else {
    		drivetrain.LFront.set(0);
    	}
    	
    	//JOYSTICKS
    	if(xbox.getRightX_movePad() > DEADZONE){
    		drivetrain.RBack.set(-1);
    	} else {
    		drivetrain.RBack.set(0);
    	}
    	if(xbox.getBButton_movePad()){
    		drivetrain.RFront.set(-1);
    	} else {
    		drivetrain.RFront.set(0);
    	}
    	if(xbox.getXButton_movePad()){
    		drivetrain.LBack.set(1);
    	} else {
    		drivetrain.LBack.set(0);
    	}
    	if(xbox.getYButton_movePad()){
    		drivetrain.LFront.set(1);
    	} else {
    		drivetrain.LFront.set(0);
    	}
    	
    	if(xbox.getAButton()) drivetrain.RBack.set(-1);
    	else if(xbox.getAButton_movePad()) drivetrain.RBack.set(1);
    	else drivetrain.RBack.set(0);
    	
    	if(xbox.getBButton()) drivetrain.RFront.set(-1);
    	else if(xbox.getBButton_movePad()) drivetrain.RFront.set(1);
    	else drivetrain.RFront.set(0);
    	
    	if(xbox.getXButton()) drivetrain.LBack.set(1);
    	else if(xbox.getXButton_movePad()) drivetrain.LBack.set(-1);
    	else drivetrain.LBack.set(0);
    	
    	if(xbox.getYButton()) drivetrain.LFront.set(1);
    	else if(xbox.getYButton_movePad()) drivetrain.LFront.set(-1);
    	else drivetrain.LFront.set(0);
    	
    	
    	if(xbox.getRightY() < DEADZONE) drivetrain.RBack.set(-1);
    	else if(xbox.getAButton_movePad()) drivetrain.RBack.set(1);
    	else drivetrain.RBack.set(0);
    	
    	if(xbox.getBButton()) drivetrain.RFront.set(-1);
    	else if(xbox.getBButton_movePad()) drivetrain.RFront.set(1);
    	else drivetrain.RFront.set(0);
    	
    	if(xbox.getXButton()) drivetrain.LBack.set(1);
    	else if(xbox.getXButton_movePad()) drivetrain.LBack.set(-1);
    	else drivetrain.LBack.set(0);
    	
    	if(xbox.getYButton()) drivetrain.LFront.set(1);
    	else if(xbox.getYButton_movePad()) drivetrain.LFront.set(-1);
    	else drivetrain.LFront.set(0);
    	*/
    }
    
}
