import lejos.hardware.motor.EV3LargeRegulatedMotor;
import lejos.hardware.port.MotorPort;
import lejos.robotics.chassis.Chassis;
import lejos.robotics.chassis.Wheel;
import lejos.robotics.chassis.WheeledChassis;
import lejos.robotics.navigation.MovePilot;
import lejos.robotics.navigation.Navigator;
import lejos.robotics.subsumption.Behavior;

public class Travel implements Behavior {

	private boolean suppressed;
	private int target[];
	private static final double SPEED = 360;
	private Navigator navSystem;

	public Travel(int target[]) {
		suppressed = false;
		this.target = target;
		
		EV3LargeRegulatedMotor leftMotor = new EV3LargeRegulatedMotor(MotorPort.A); // adjust
		EV3LargeRegulatedMotor rightMotor = new EV3LargeRegulatedMotor(MotorPort.C); // adjust
		Wheel leftWheel = WheeledChassis.modelWheel(leftMotor, 2.8).offset(-9); // adjust
		Wheel rightWheel = WheeledChassis.modelWheel(rightMotor, 2.8).offset(9); // adjust
		Chassis chassis = new WheeledChassis(new Wheel[] { leftWheel, rightWheel }, WheeledChassis.TYPE_DIFFERENTIAL);
		MovePilot pilot = new MovePilot(chassis);
		pilot.setLinearSpeed(SPEED);
		navSystem = new Navigator(pilot);
		
	}

	@Override
	public boolean takeControl() {
		return target!=null;
	}

	@Override
	public void action() {
		suppressed = false;
		navSystem.goTo(target[0], target[1]);
		
	}

	@Override
	public void suppress() {
		navSystem.stop();
		suppressed = true;
	}

}
