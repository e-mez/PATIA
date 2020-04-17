import lejos.hardware.motor.EV3MediumRegulatedMotor;
import lejos.hardware.port.MotorPort;
import lejos.hardware.sensor.EV3TouchSensor;
import lejos.hardware.sensor.EV3UltrasonicSensor;
import lejos.hardware.sensor.SensorMode;
import lejos.robotics.SampleProvider;
import lejos.robotics.subsumption.Behavior;

public class OpenClaws implements Behavior {
	private boolean suppressed;
	private int target[];
	private int current[];
	private EV3MediumRegulatedMotor claws;
	private static final float SPEED = 400;
	
	public OpenClaws(int target[], int current[]) {
		suppressed = false;
		claws = new EV3MediumRegulatedMotor(MotorPort.D);
		claws.setSpeed(SPEED);
		this.target = target;
	}
	
	@Override
	public boolean takeControl() {
		return Math.sqrt(Math.pow((target[0]-current[0]), 2) + Math.pow((target[1]-current[1]), 2)) < 18; // open if within 18cm (1 second) of target
			
	}
	
	@Override
	public void action() {
		suppressed = false;
		claws.rotateTo(400, true);
	}
	
	@Override
	public void suppress() {
		claws.stop();
		suppressed=true;
	}
	
	
}
