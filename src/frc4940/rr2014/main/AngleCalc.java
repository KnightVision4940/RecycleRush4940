package frc4940.rr2014.main;

public class AngleCalc {
	public float oppAngle(float _angle){
		float oppAngle;
		float angle = _angle;
		
		if (angle < 180) 	oppAngle = 360 + (angle - 180);
		else 				oppAngle = angle - 180;
		
		return oppAngle;
	}
}
