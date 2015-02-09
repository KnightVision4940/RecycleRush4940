package frc4940.rr2014.main;
import edu.wpi.first.wpilibj.IterativeRobot;
import edu.wpi.first.wpilibj.RobotDrive;

public class Robot extends IterativeRobot {
	//Declares the Subsystems and other global variables
	XtremePro joystick = new XtremePro();
	Drivetrain drivetrain = new Drivetrain();
	Elevator elev = new Elevator();
	RobotDrive drive = new RobotDrive(drivetrain.LFront, drivetrain.LBack, drivetrain.RFront, drivetrain.RBack);
	final int NULL = 0;
    XboxController xbox=new XboxController();
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
    	drivetrain.setRFront(0.5);
    	drivetrain.setRBack(0.5);
    	drivetrain.setLFront(0.5);
    	drivetrain.setLBack(0.5);
    }
    /**
     * This function is called periodically during operator control
     */
    public void teleopInit(){
    	//Disables Safety for the Robots
    	//drivetrain.RFront.setSafetyEnabled(true);
    	//drivetrain.RBack.setSafetyEnabled(true);
    	//drivetrain.LFront.setSafetyEnabled(true);
    	//drivetrain.LBack.setSafetyEnabled(true);
    }
    public void teleopPeriodic() {
    	
    	//FRONT ELEVATOR
    	if(xbox.getAButton()){
    		if(xbox.getRTrig() >= 0.1){
    			//lowers front elevator
    			elev.setShortElev(-1 * xbox.getRTrig());
    		}
    	} else {
    		if(xbox.getRTrig() >= 0.1){
    			//raises front elevator
    			elev.setShortElev(xbox.getRTrig());
    		}
    	} 
    	
    	if(xbox.getRTrig() < 0.1)
    	{
			//raises front elevator
			elev.setShortElev(0.0);
    	}							
    	
    	
    	//BACK ELEVATOR
    	if(xbox.getXButton()){
    		if(xbox.getLTrig() >= 0.1){
    			//Lowers the back elevator
    		}
    	} else {
    		if(xbox.getLTrig() >= 0.1){
    			//Raises the Back elevator
    		}
    	} 
    	
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
