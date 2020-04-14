package perception;

import lejos.hardware.ev3.EV3;
import lejos.hardware.sensor.EV3UltrasonicSensor;
import lejos.robotics.RangeFinderAdapter;

public class RangeSensor {
	private EV3UltrasonicSensor sonicSensor; 	
	private RangeFinderAdapter sonar;

	public RangeSensor(EV3 ev3brick) {
		super();
		this.sonicSensor = new EV3UltrasonicSensor(ev3brick.getPort("S2"));
		this.sonar = new RangeFinderAdapter(this.sonicSensor);
	}
	
	public int getRangeToNearestObject() {
		return (int) sonar.getRange();
	}
}
