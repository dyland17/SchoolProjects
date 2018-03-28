package program.inheritance;

import java.text.DecimalFormat;
//Derived class of dedPoint.
public class dedCircle extends dedPoint {
	//protected field among derived classes.
	protected double radius;
	//constructor.
	public dedCircle(int x, int y, double rad){
			super(x,y);
			radius = rad;
	}
	//Calculates the area of a circle and
			//returns that value.
	public double getArea(){
			return (Math.PI * Math.pow(radius,2));
	}
	//Calculates the circumference of a circle
			// and returns the value.
	public double getCirumference(){
			return (2*Math.PI *radius);
	}
	//Can set a radius.
	public void setRadius(double r){
			radius = r;
	}
	//To create unique string for the class.
	public String toString(){
		//The super call to the toString method
			// is to stop repetitive code.
			String s =	super.toString();
			DecimalFormat df =new DecimalFormat("#0.00");
			
			s +=	"Radius: " + df.format(radius) + "\n" + 
						"Area: " + df.format(getArea()) + "\n" +
						"Circumference: " + df.format(getCirumference()) + "\n";
			return s;
		
	}
}
