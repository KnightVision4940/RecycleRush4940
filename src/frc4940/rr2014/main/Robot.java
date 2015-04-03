package frc4940.rr2014.main;
import edu.wpi.first.wpilibj.IterativeRobot;
import edu.wpi.first.wpilibj.Timer;

public class Robot extends IterativeRobot {
	/**
	 * AUTONOMUS MODE SWITCH
	 * 
	 * Change the value of the below variable in order to select the desired auto code
	 * Please refer to the Autonomous mode directory
	 */
	final int AUTONOMOUS_MODE = 13;
	
	
	//SUBSYSTEMS / CLASSES
	XtremePro joystick = new XtremePro();
	Elevator shortElev = new Elevator();
	RearElevator tallElev = new RearElevator();
	Wheels wheels = new Wheels();
    XboxController xbox = new XboxController();
    Mecanum mecanum = new Mecanum();
    AngleCalc angles = new AngleCalc();
    
    //CONSTANTS
    final double OFFSET = 0.1;
	final double NULL = 0.0;
	final double DEADZONE = 0.1;
	final float FNULL = (float) 0.0;
	
	//OTHER GLOBAL VARIABLES
	boolean isStewGreat = true;
	/**
     * This function is run when the robot is first started up and should be
     * used for any initialization code.
     */
    public void robotInit() {
    	//inverts right-hand side motors
    	//drive.setInvertedMotor(RobotDrive.MotorType.kFrontRight, true);
        //drive.setInvertedMotor(RobotDrive.MotorType.kFrontLeft, true);
        mecanum.init(1, 0);
    }
    
    public void autonomousInit(){
    	/**
    	 * AUTO MODE 1
    	 * 
    	 * Drives forwards into the auto zone
    	 * RAMP
    	 */
    	if(AUTONOMOUS_MODE == 1){ 
    		mecanum.drive((float)0.25, 180, FNULL, false);
    		Timer.delay(2.5);
    		mecanum.drive(FNULL, FNULL, FNULL, false);
    	}
    	/**
    	 * AUTO MODE 2
    	 * 
    	 * Starts between two stations, with arms in the air
    	 * Picks up bin and tote from adjacent stations
    	 * rotates, and drives into auto zone
    	 * lowers elevators and drops the tote and bin
    	 */
    	else if(AUTONOMOUS_MODE == 2){ 
    		//lowers the elevator
    		tallElev.set(0.4);
    		Timer.delay(1);
    		tallElev.set(-OFFSET);
    		//begins closing //gripper
    		//gripper.set(-1);
    		Timer.delay(1.6);
    		//begins raising the tall elevator
        	tallElev.set(-0.6);
        	Timer.delay(0.6);
    		//drives to the tote
    		mecanum.drive((float)0.5, (float)180, FNULL, false);
    		Timer.delay(0.3);
    		//stopes the tall elevator
    		tallElev.set(-OFFSET);
    		//stops driving
    		mecanum.breakDrive();
    		//lowers the short elevator below the toes
    		shortElev.set(-1);
    		Timer.delay(1.7);
    		shortElev.set(NULL);
    		//raises the elevator
    		shortElev.set(1);
    		Timer.delay(1);
    		//stops the ////grippers
    		////gripper.set(NULL);
    		Timer.delay(0.7);
    		//stops the elevator
    		shortElev.set(NULL);
    		//rotates 90 degrees to the right
    		mecanum.turn((float) 0.5);
    		Timer.delay(0.5);
    		//stops turning
    		mecanum.turn(0);
    		Timer.delay(0.2);
    		//drives forwards
    		mecanum.drive((float)0.5, (float)180, FNULL, false);
    		Timer.delay(1);
    		//begins opening //grippers while driving 
    		//gripper.set(1);
    		Timer.delay(0.7);
    		//stops driving
    		mecanum.breakDrive();
    		Timer.delay(0.2);
    		///turns 90 degrees to the left; original orintation
    		mecanum.turn((float) -0.5);
    		Timer.delay(0.56);
    		//stops turning
    		mecanum.turn(FNULL);
    		Timer.delay(0.2);
    		//stops //grippers
    		//gripper.set(NULL);
    		//lowers both elevators until they reach the lower limits
    		do{
    			if(!shortElev.getLowerLimit()) shortElev.set(-0.6);
    			else shortElev.set(NULL);
        		
    			if (!tallElev.getLowerLimit()) tallElev.set(0.3);
    			else tallElev.set(-OFFSET);
    		} while(shortElev.getLowerLimit() || tallElev.getLowerLimit());
    	}
    	/**
		 * AUTO MODE 3
		 * 
		 * Picks up the Recycling bin
		 * ANd places it in the auto zone
		 * RAMP
		 */
    	else if(AUTONOMOUS_MODE == 3){
    		//drives forwards
    		mecanum.drive((float)0.5, 0, FNULL, false);
    		Timer.delay(0.55);
    		//breaks
    		mecanum.breakDrive();
    		//raises the tall elevator 
    		tallElev.set(-0.6);
    		Timer.delay(0.32);
    		//stops the tall elevator
    		tallElev.set(-OFFSET);
    		//raises the tall elevator to pick up the recyling bin
    		tallElev.set(-0.8);
    		Timer.delay(0.3);
    		//stops the tall elevator
    		tallElev.set(-OFFSET);
    		//drives forwards
    		mecanum.drive((float)0.5, (float)180, FNULL, false);
    		Timer.delay(2.5);
    		//stops driving
    		mecanum.breakDrive();
    	}
    	/**
		 * AUTO MODE 4
		 * 
		 * Picks up the Tote
		 * ANd places it in the auto zone
		 */
    	else if(AUTONOMOUS_MODE == 4){
    		//drives forwards
    		mecanum.drive((float)0.5, 0, FNULL, false);
    		Timer.delay(0.55);
    		//breaks
    		mecanum.breakDrive();
    		//turns
    		mecanum.turn(0.5);
    		Timer.delay(0.5);
    		mecanum.breakDrive();
    		//drives into tote
    		mecanum.drive((float)0.5, 180, FNULL, false);
    		Timer.delay(0.65);
    		mecanum.breakDrive();
    		//lifts totes
    		shortElev.set(1);
    		Timer.delay(3);
    		shortElev.set(0);
    		//turns
    		mecanum.turn(-0.5);
    		Timer.delay(0.5);
    		mecanum.breakDrive();
    		//drives forwards
    		mecanum.drive((float)0.5, (float)180, FNULL, false);
    		Timer.delay(0.8);
    		//stops driving
    		mecanum.breakDrive();
    		Timer.delay(0.2);
    		///turns 90 degrees to the left; original orintation
    		mecanum.turn((float) -0.5);
    		Timer.delay(0.56);
    		//stops turning
    		mecanum.turn(FNULL);
    	}
    	/**
		 * AUTO MODE 5
		 * 
		 * Picks up the Tote
		 */
    	else if(AUTONOMOUS_MODE == 5){
    		//drives into tote
    		mecanum.drive((float)0.5, 180, FNULL, false);
    		Timer.delay(0.65);
    		mecanum.breakDrive();
    		//lifts totes
    		shortElev.set(-1);
    		Timer.delay(2);
    		shortElev.set(1);
    		Timer.delay(3);
    		shortElev.set(0);
    	}
    	/**
    	 * lol idk what this does
    	 */
    	else if (AUTONOMOUS_MODE == 6){
    		//tightens the //grippers
    		//gripper.set(-1);
    		//raises the tall elevator 
    		tallElev.set(-0.6);
    		Timer.delay(0.32);
    		//stops the tall elevator
    		tallElev.set(-OFFSET);
    		//drives forwards
    		mecanum.drive((float)0.5, 0, FNULL, false);
    		Timer.delay(0.55);
    		//breaks
    		mecanum.breakDrive();
    		Timer.delay(4.0);
    		//raises the tall elevator to pick up the recyling bin
    		tallElev.set(-0.8);
    		Timer.delay(0.3);
    		//stops the tall elevator
    		tallElev.set(-OFFSET);
    		//stops //grippers
    		Timer.delay(1.2);
    		//gripper.set(0);
    		//drives backwards
    		mecanum.drive((float)0.5, 180, FNULL, false);
    		Timer.delay(0.55);
    		//breaks
    		mecanum.breakDrive();
    		//turns
    		mecanum.turn(-0.5);
    		Timer.delay(0.55);
    		mecanum.breakDrive();
    		//drives forwards
    		mecanum.drive((float)0.5, 0, FNULL, false);
    		Timer.delay(0.35);
    		//breaks
    		mecanum.breakDrive();
    		//strafes
    		mecanum.drive((float) 0.5, (float) 90, FNULL, false);
    		Timer.delay(0.7);
    		mecanum.breakDrive();
    		//drives into totes
    		mecanum.drive((float)0.5, (float)180, FNULL, false);
    		Timer.delay(0.6);
    		mecanum.breakDrive();
    		//lifts totes
    		shortElev.set(1);
    		Timer.delay(2);
    		shortElev.set(0);
    		//turns
    		mecanum.turn(-0.5);
    		Timer.delay(0.5);
    		mecanum.breakDrive();
    		//drives forwards( into Autozone)
    		mecanum.drive((float)0.5, (float)180, FNULL, false);
    		Timer.delay(0.8);
    		//stops driving
    		mecanum.breakDrive();
    		//begins to open //grippers
    		//gripper.set(1);
    		Timer.delay(2.2);
    		///turns 90 degrees to the left; original orintation
    		mecanum.turn((float) -0.5);
    		Timer.delay(0.56);
    		//stops turning
    		mecanum.turn(FNULL);
    		Timer.delay(0.2);
    		//stops //grippers
    		//gripper.set(NULL);
    		//lowers both elevators until they reach the lower limits
    		do{
    			if(!shortElev.getLowerLimit()) shortElev.set(-0.6);
    			else shortElev.set(NULL);
        		
    			if (!tallElev.getLowerLimit()) tallElev.set(0.3);
    			else tallElev.set(-OFFSET);
    		} while(shortElev.getLowerLimit() || tallElev.getLowerLimit());
    	}
    	/**
    	 * AUTO MODE 7
    	 * 
    	 * Drives forwards into the auto zone
    	 */
    	if(AUTONOMOUS_MODE == 7){ 
    		mecanum.drive((float)0.25, 180, FNULL, false);
    		Timer.delay(3.2);
    		mecanum.drive(FNULL, FNULL, FNULL, false);
    	}
    	/**
		 * AUTO MODE 8
		 * 
		 * Literally does nothing
		 */
    	else if(AUTONOMOUS_MODE == 8){
    		//thank you mr. skeltal
    	}
    	/**
		 * AUTO MODE 9
		 * 
		 * Picks up the Recycling bin
		 * ANd places it in the auto zone
		 * NO RAMP
		 */
    	else if(AUTONOMOUS_MODE == 9){
    		//drives forwards
    		mecanum.drive((float)0.5, 0, FNULL, false);
    		Timer.delay(0.55);
    		//breaks
    		mecanum.breakDrive();
    		//raises the tall elevator 
    		tallElev.set(-0.6);
    		Timer.delay(0.32);
    		//stops the tall elevator
    		tallElev.set(-OFFSET);
    		//raises the tall elevator to pick up the recyling bin
    		tallElev.set(-0.8);
    		Timer.delay(0.3);
    		//stops the tall elevator
    		tallElev.set(-OFFSET);
    		//drives forwards
    		mecanum.drive((float)0.5, (float)180, FNULL, false);
    		Timer.delay(1.5);
    		//stops driving
    		mecanum.breakDrive();
    	}
    	/**
		 * AUTO MODE 10
		 * 
		 * Picks up the Recycling bin
		 * ANd places it in the auto zone
		 * STATIC
		 */
    	else if(AUTONOMOUS_MODE == 10){
    		//drives forwards
    		mecanum.drive((float)0.5, 0, FNULL, false);
    		Timer.delay(0.55);
    		//breaks
    		mecanum.breakDrive();
    		//raises the tall elevator 
    		tallElev.set(-0.6);
    		Timer.delay(0.32);
    		//stops the tall elevator
    		tallElev.set(-OFFSET);
    		//raises the tall elevator to pick up the recyling bin
    		tallElev.set(-0.8);
    		Timer.delay(0.3);
    		//stops the tall elevator
    		tallElev.set(-OFFSET);
    	}
    	/**
		 * AUTO MODE 11
		 * 
		 * Picks up the Recycling bin
		 * ANd places it in the auto zone
		 * HIGH ELEV FIX
		 */
    	else if(AUTONOMOUS_MODE == 11){
    		//tightens the //grippers
    		//gripper.set(-1);
    		//raises the tall elevator 
    		tallElev.set(-0.6);
    		Timer.delay( 0.35 );
    		//stops the tall elevator
    		tallElev.set(-OFFSET);
    		//drives forwards
    		mecanum.drive((float)0.5, 0, FNULL, false);
    		Timer.delay(0.55);
    		//breaks
    		mecanum.breakDrive();
    		Timer.delay(4.5);
    		//raises the tall elevator to pick up the recyling bin
    		tallElev.set(-0.8);
    		Timer.delay(0.45);
    		//stops the tall elevator
    		tallElev.set(-OFFSET);
    		Timer.delay(1.2);
    		//drives forwards
    		mecanum.drive((float)0.5, (float)180, FNULL, false);
    		Timer.delay(1.5);
    		//gripper.set(0);
    		Timer.delay(1);
    		//stops driving
    		mecanum.breakDrive();
    	}
    	/**
		 * AUTO MODE 12
		 * 
		 * Picks up the Recycling bin
		 * DO NOT USE
		 * ARCHAIC
		 */
    	else if(AUTONOMOUS_MODE == 12){
    		//drives forwards
    		mecanum.drive((float)0.5, 0, FNULL, false);
    		Timer.delay(0.7);
    		//breaks
    		mecanum.breakDrive();
    		//raises the tall elevator to pick up the recyling bin
    		tallElev.set(-0.8);
    		Timer.delay(1.1);
    		//stops the tall elevator
    		tallElev.set(-OFFSET);
    	}
    	/**
    	 * AUTO MODE 13
    	 * 
    	 * Picks up Recyling Bin
    	 * STATIONARY
    	 */
    	else if(AUTONOMOUS_MODE == 13){
    		//raises the tall elevator to pick up the recyling bin
    		tallElev.set(-0.8);
    		Timer.delay(1.1);
    		//stops the tall elevator
    		tallElev.set(-OFFSET);
    	}
    	/**
    	 * AUTO MODE 14
    	 * 
    	 * Picks up Recyling Bin
    	 * MOVES INTO ZONE (RAMP)
    	 */
    	else if(AUTONOMOUS_MODE == 14){
    		//raises the tall elevator to pick up the recyling bin
    		tallElev.set(-0.8);
    		Timer.delay(1.1);
    		//stops the tall elevator
    		tallElev.set(-OFFSET);
    		//drives forwards
    		mecanum.drive((float)0.5, 180, FNULL, false);
    		Timer.delay(2.5);
    		//breaks
    		mecanum.breakDrive();
    	}
    	/**
    	 * AUTO MODE 15
    	 * 
    	 * Picks up Recyling Bin
    	 * MOVES INTO ZONE (NO ramp))
    	 */
    	else if(AUTONOMOUS_MODE == 15){
    		//raises the tall elevator to pick up the recyling bin
    		tallElev.set(-0.8);
    		Timer.delay(1.1);
    		//stops the tall elevator
    		tallElev.set(-OFFSET);
    		//drives forwards
    		mecanum.drive((float)0.5, 180, FNULL, false);
    		Timer.delay(1.5);
    		//breaks
    		mecanum.breakDrive();
    	}
    	
    }

    /**
     * 
     * This function is called periodically during autonomous
     */
    public void autonomousPeriodic() {}
    
    /**
     * This function is called periodically during operator control
     */
    public void teleopInit(){
    }
    
    public void teleopPeriodic() {
    	/**
    	 * JOYSTICK CONTROLS
    	 */
    	
    	//if trigger is held, rotates. Otherwise, drives/strafes
    	if(!joystick.getTrigger()){
    		//REVERSED motion
    		if (!joystick.getThumb()) mecanum.drive(mecanum.getJoystickMagnitude(), angles.oppAngle(mecanum.getJoystickDirection()), 0, false);
    		//REGULAR motion
    		else mecanum.drive(mecanum.getJoystickMagnitude(), mecanum.getJoystickDirection(), 0, false);
    		
    		if(joystick.getLeftT()) mecanum.turn(-0.3); 		//Turn L
    		else if (joystick.getRightT()) mecanum.turn(0.3);	//Turn R
    	}
    	//Rotation-Only motion
    	else if(joystick.getTrigger()) mecanum.turn(joystick.getYAxis() * 0.5);
    	
    	/**
    	 * ElEVATOR PAD CONTROLS
    	 */
    	//Controls the tall elevator, X and Y buttons
    	if(xbox.getXButton()){ //X Button
    		if(!tallElev.getLowerLimit()) 
    			tallElev.set(-OFFSET);
    		else 
    			tallElev.set(0.3); //Goes Down
    	}
    	else if(xbox.getYButton()){ //Y Button
    		if(!tallElev.getUpperLimit()) 
    			tallElev.set(-OFFSET);
    		else 
    			tallElev.set(-0.5); //Goes Up
    	}
    	else 
    		tallElev.set(-OFFSET);
    	
    	//Controls the short elevator, A and B Buttons
    	if(xbox.getAButton()){ //A Button
    		if(!shortElev.getLowerLimit())
    			shortElev.set(0);
    		else 
    			shortElev.set(-1.0); //Goes Down
    	}
    	else if(xbox.getBButton()){ //B Button
    		if(!shortElev.getUpperLimit()) 
    			shortElev.set(0);
    		else 
    			shortElev.set(1.0); //Goes Up
    	}
    	else 
    		shortElev.set(0);
    	
    	//Uses the intake wheel, Bumpers
    	//SUCK
		if(xbox.getRBButton()) //Right Bumper
			wheels.suck();
		//BLOW
    	else if(xbox.getLBButton()){ //Left Bumper
    		wheels.blow();
    	}

    	else wheels.set(0);
    	//Stew is God
    }
    	
    	
    
    /**
     * This function is called periodically during test mode
     */
    public void testPeriodic() {
    	
    	/*
		//tightens the //grippers
		//gripper.set(-1);
		//raises the tall elevator 
		tallElev.set(-0.6);
		Timer.delay(0.42);
		//stops the tall elevator
		tallElev.set(-OFFSET);
		//drives forwards
		mecanum.drive((float)0.5, 0, FNULL, false);
		Timer.delay(0.55);
		//breaks
		mecanum.drive(FNULL, FNULL, FNULL, false);
		Timer.delay(2);
		//raises the tall elevator to pick up the recyling bin
		tallElev.set(-0.8);
		Timer.delay(0.25);
		//stops the tall elevator
		tallElev.set(-OFFSET);
		//drives forwards
		mecanum.drive((float)0.5, 180, FNULL, false);
		Timer.delay(0.55);
		//stops, and stafes to the right
		mecanum.drive(FNULL, FNULL, FNULL, false);
		mecanum.drive((float)0.5, 90, FNULL, false);
		Timer.delay(1.2);
		//breaks, stops the //grippers
		mecanum.drive(FNULL, FNULL, FNULL, false);
		//gripper.set(NULL);
		//raises the short elevator, then breaks it
		shortElev.set(-1);
		Timer.delay(1.5);
		shortElev.set(1);
		Timer.delay(1.5);
		shortElev.set(NULL);
		//turns to the right
		mecanum.turn(0.5);
		Timer.delay(0.5);
		//stops rotating
		mecanum.drive(FNULL, FNULL, FNULL, false);
		//drives into the auto zone
		/*
		mecanum.drive((float)0.6, (float)180, FNULL, false);
		Timer.delay(3.5);
		//stops driving
		mecanum.drive(FNULL, FNULL, FNULL, false);
		
		//rotates to the left, 90 degrees
		mecanum.turn(-0.5);
		Timer.delay(0.5);
		mecanum.drive(FNULL, FNULL, FNULL, false);
		//lowers both elevators until they reach the lower limits
		do{
			if(!shortElev.getLowerLimit()) shortElev.set(-0.6);
			else shortElev.set(NULL);
    		
			if (!tallElev.getLowerLimit()) tallElev.set(0.3);
			else tallElev.set(-OFFSET);
		} while(shortElev.getLowerLimit() || tallElev.getLowerLimit());
		//opens //gripper for the rest of autonomous
		do{
			//gripper.set(1);
		} while (//gripper.getOpenLimit());
		*/
    }
    
}


//God Bless Kamen
//Hashtag Swag
//#YOLOSWAGMASTER69