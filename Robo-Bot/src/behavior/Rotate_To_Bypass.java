package behavior;

import perception.RangeSensor;
import lejos.robotics.subsumption.Behavior;
import motors.RegulatedMotor;

public class Rotate_To_Bypass implements Behavior {
	RangeSensor rangeSensor;
	boolean suppressed;
	
	public Rotate_To_Bypass(RangeSensor rs) {
		super();
		rangeSensor = rs;
	}

	@Override
	public boolean takeControl() {
		return rangeSensor.getRangeToNearestObject() < 25;
	}

	@Override
	public void action() {
		suppressed = false;
		RegulatedMotor.LEFT_MOTOR.rotate(-360, true);
		RegulatedMotor.RIGHT_MOTOR.rotate(-180, true);
		
		while (RegulatedMotor.LEFT_MOTOR.isMoving() && !suppressed) {
			Thread.yield();
		}
		
		RegulatedMotor.LEFT_MOTOR.stop();
		RegulatedMotor.RIGHT_MOTOR.stop();
	}

	@Override
	public void suppress() {
		suppressed = true;		
	}
	
}
