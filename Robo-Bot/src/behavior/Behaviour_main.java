package behavior;

import lejos.hardware.BrickFinder;
import lejos.hardware.Keys;
import lejos.hardware.ev3.EV3;
import lejos.robotics.subsumption.Arbitrator;
import lejos.robotics.subsumption.Behavior;
import perception.RangeSensor;
import motors.RegulatedMotor;

public class Behaviour_main {

	public static void main(String[] args) {
		EV3 ev3brick = (EV3) BrickFinder.getLocal();
		Keys buttons = ev3brick.getKeys();
		
		RangeSensor rangeSensor = new RangeSensor(ev3brick);
		
		Behavior b1 = new Move_Forward(rangeSensor);
		Behavior b3 = new Rotate_To_Bypass(rangeSensor);

		Behavior[] bArray = { b1, b3};		
		Arbitrator arby = new Arbitrator(bArray);
		
		arby.go();
	}

}
