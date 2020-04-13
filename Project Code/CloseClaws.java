import lejos.hardware.motor.EV3MediumRegulatedMotor;
import lejos.hardware.port.MotorPort;
import lejos.hardware.sensor.EV3TouchSensor;
import lejos.hardware.sensor.SensorMode;
import lejos.robotics.subsumption.Behavior;

public class CloseClaws implements Behavior {
	private boolean suppressed;
	private SensorMode touch;
	private float[] sample;
	private EV3MediumRegulatedMotor claws;
	private static final float SPEED = 400;
	
	public CloseClaws(EV3TouchSensor tSensor) {
		suppressed = false;
		claws = new EV3MediumRegulatedMotor(MotorPort.D);
		claws.setSpeed(SPEED);
		touch = tSensor.getTouchMode();
		sample = new float[touch.sampleSize()];
	}
	
	@Override
	public boolean takeControl() {
		touch.fetchSample(sample,0);
		if (sample[0]==1) {
			return true;
		}
		return false;
	}
	
	@Override
	public void action() {
		suppressed = false;
		claws.rotateTo(-400, true);
	}
	
	@Override
	public void suppress() {
		claws.stop();
		suppressed=true;
	}
	
	
}
