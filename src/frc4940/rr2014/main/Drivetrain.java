package frc4940.rr2014.main;

import edu.wpi.first.wpilibj.Talon;
import edu.wpi.first.wpilibj.Victor;

public class Drivetrain {
	Talon Bae1 = new Talon(0);
	Victor Bae2 = new Victor(1);
	Talon Bae3 = new Talon(2);
	Talon Bae4 = new Talon(3);
	void setBae1(double speed){
		Bae1.set(speed);
	}
	void setBae2(double speed){
		Bae2.set(speed);
	}
	void setBae3(double speed){
		Bae3.set(speed);
	}
	void setBae4(double speed){
		Bae4.set(speed);
	}			
}
