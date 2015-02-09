package frc4940.rr2014.main;
import edu.wpi.first.wpilibj.Victor;

public class Elevator {
	Victor shortElevator = new Victor(5);
	
	void setShortElev(double speed){
		shortElevator.set(speed);
	}
}
