package frc4940.rr2014.main;
import edu.wpi.first.wpilibj.IterativeRobot;
import edu.wpi.first.wpilibj.RobotDrive;

public class Robot extends IterativeRobot {
	//Declares the Subsystems and other global variables
	XtremePro joystick = new XtremePro();
	Drivetrain drivetrain = new Drivetrain();
	RobotDrive drive = new RobotDrive(drivetrain.Bae4, drivetrain.Bae3, drivetrain.Bae1, drivetrain.Bae2);
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
    	drivetrain.setBae1(0.5);
    	drivetrain.setBae2(0.5);
    	drivetrain.setBae3(0.5);
    	drivetrain.setBae4(0.5);
    }
    /**
     * This function is called periodically during operator control
     */
    public void teleopInit(){
    	//Disables Safety for the Robots
    	drivetrain.Bae1.setSafetyEnabled(false);
    	drivetrain.Bae2.setSafetyEnabled(false);
    	drivetrain.Bae3.setSafetyEnabled(false);
    	drivetrain.Bae4.setSafetyEnabled(false);
    }
    public void teleopPeriodic() {
    	
    	if(xbox.getAButton()){
    		drivetrain.setBae1(0.5);
    	} else {
    		drivetrain.setBae1(0);
    	}
    	
    	drivetrain.setBae1(joystick.getYAxis());
    	drivetrain.setBae1(xbox.getLeftY());
    	
    	//FRONT ELEVATOR
    	if(xbox.getAButton()){
    		if(xbox.getRTrig() >= 0.1){
    			//Lowers the Front elevator
    		}
    	} else {
    		if(xbox.getRTrig() >= 0.1){
    			//Raises the Front elevator
    		}
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
