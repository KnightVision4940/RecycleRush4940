package frc4940.rr2014.main;

import edu.wpi.first.wpilibj.Victor;

public class RearElevator 
{
	
	Victor LongElevator = new Victor(4);

	void setLongElev(double speed)
	{
	LongElevator.set(speed);
	}
}
