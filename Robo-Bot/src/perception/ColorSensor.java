package perception;

import lejos.hardware.ev3.LocalEV3;
import lejos.hardware.port.Port;
import lejos.hardware.sensor.EV3ColorSensor;
import lejos.robotics.Color;
import lejos.robotics.SampleProvider;
import lejos.robotics.filter.MeanFilter;

public class ColorSensor {
	public static final String COLOR_SENSOR = "S1";
	
	private float[][] colors;
	private Port port;
	private EV3ColorSensor colorSensor;

	public ColorSensor() {
		port = LocalEV3.get().getPort(COLOR_SENSOR);
		colorSensor = new EV3ColorSensor(port);
		colors = new float[16][0];
	}
	
	public void calibrateColor(int color){
		SampleProvider average = new MeanFilter(colorSensor.getRGBMode(), 1);
		colors[color] = new float[average.sampleSize()];
		average.fetchSample(colors[color], 0);
	}
	
	public int getCurrentColor(){
		SampleProvider average = new MeanFilter(colorSensor.getRGBMode(), 1);
		float[] sample  = new float[average.sampleSize()];
		double minscal = Double.MAX_VALUE;
		int color   = -1;

		average.fetchSample(sample, 0);

		for(int i= 0; i< 16; i++){
			if(colors[i].length > 0){
				double scalaire = scalaire(sample, colors[i]);
				if (scalaire < minscal) {
					minscal = scalaire;
					color = i;
				}
			}
		}
		return color;
	}

	public float[][] getCalibration() {
		return colors;
	}
	
	public void setCalibration(float[][] colors){
		this.colors = colors;
	}
	
	public void lightOn(){
		colorSensor.setFloodlight(Color.WHITE);
	}
	
	public void lightOff(){
		colorSensor.setFloodlight(false);
	}
	
	private double scalaire(float[] v1, float[] v2) {
		return Math.sqrt (Math.pow(v1[0] - v2[0], 2.0) +
				Math.pow(v1[1] - v2[1], 2.0) +
				Math.pow(v1[2] - v2[2], 2.0));
	}
	
}
