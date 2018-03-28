package program.inheritance;

import java.text.DecimalFormat;

public class dedCylinder extends dedCircle{
	//Height of the cylinder.
	private double height;
	//Constructor
	public dedCylinder(int x, int y, double rad, double hi) {
			super(x, y, rad);
			height = hi;
	}
	//Calculates the volume of the cylinder
			//and returns the value.
	public double getVolume(){
			return (Math.PI*(Math.pow(radius, 2)) * height);
	}
	//Calculates the surface area of the cylinder
			//and returns the value.
	public double getSurfaceArea(){
			return ((2*Math.PI*radius*height) + (2*Math.PI*(Math.pow(radius, 2))));
	}
	//To create unique string for the class.
	public String toString(){
			//The super call to the toString method
				// is to stop repetitive code.
			String s = super.toString();
			DecimalFormat df = new DecimalFormat("#0.00");
			s += 	"Height: " + height + "\n"	+
						"Volume: " + df.format(getVolume()) + "\n" +
						  "Surface Area: " + df.format(getSurfaceArea());
			return s;
	}
}