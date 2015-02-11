package frc4940.rr2014.main;

import edu.wpi.first.wpilibj.Talon;


public class Drivetrain {
	Talon RFront = new Talon(0);
	Talon RBack = new Talon(1); 
	Talon LBack = new Talon(2);
	Talon LFront = new Talon(3);
	
	void setRFront(double speed){
		RFront.set(speed);
	}
	void setRBack(double speed){
		RBack.set(speed);
	}
	void setLBack(double speed){
		LBack.set(speed);
	}
	void setLFront(double speed){
		LFront.set(speed);
	}			
}
