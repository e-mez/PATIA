package behavior;

import lejos.robotics.subsumption.Behavior;
import motors.RegulatedMotor;
import perception.RangeSensor;

public class Move_Forward implements Behavior {
	RangeSensor rangeSensor;
	boolean suppressed;
	
	public Move_Forward(RangeSensor rangeSensor) {
		super();
		this.rangeSensor = rangeSensor;
	}

	@Override
	public boolean takeControl() {
		return true;
	}

	@Override
	public void action() {
		suppressed = false;
		
		RegulatedMotor.LEFT_MOTOR.forward();
		RegulatedMotor.RIGHT_MOTOR.forward();
		
		while (!suppressed) {
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
