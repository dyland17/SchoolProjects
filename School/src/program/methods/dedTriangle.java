package program.methods;
import javax.swing.JOptionPane;

/*Name: Dylan Dewald
 * Date: 1/22/16
 * Program Purpose: Create's a triangle with all leg measurements.
 * 						Then calculates the trig functions of one
 * 						angle in that particular triangle.
 */
public class dedTriangle {
	private double leg1,leg2,hypot;
	//Constructor
	public dedTriangle(){
		setupLegs();
		setupHypotenuse();
	}
	//Getting the lengths of the two legs
	public void setupLegs(){
		//Dialog Box to get the first leg length code
		String s;
		boolean gotLength = false;
		while(gotLength == false){
			//checking to make sure the leg length is a positive number
			if(leg1 <= 0.0){
				s = JOptionPane.showInputDialog(null, "Enter the first leg length", "First Leg");
				//try/catch block to catch a not parsable string
				try{
					leg1 = Double.parseDouble(s);
				}catch(NumberFormatException e){
					e.printStackTrace();
				}
			}
			else{
				gotLength = true;
			}
		}
		//getting length of second leg code
			//resetting the boolean variable
		gotLength = false;
		while(gotLength == false){
			//checking to make sure the leg length is a positive number
			if(leg2 <= 0.0){
				s = JOptionPane.showInputDialog(null, "Enter the second leg length", "Second Leg");
				//try/catch block to catch a not parsable string
				try{
					leg2 = Double.parseDouble(s);
				}catch(NumberFormatException e){
					e.printStackTrace();
				}
			}
			else{
				gotLength = true;
			}
		}
	}
	//Setting up hypotenuse
	public void setupHypotenuse(){
		hypot = Math.sqrt((Math.pow(leg1, 2) + Math.pow(leg2, 2)));
	}
	//calculating trig functions
	public void calcTrigFunctions(){
		String message;
		double angle = Math.atan((leg2/leg1));
		System.out.println(angle);
		double sin,cos,tan,sec,csc,cot;
		sin = Math.sin(angle);
		cos = Math.cos(angle);
		tan = Math.tan(angle);
		sec = 1 / Math.cos(angle);
		csc = 1 / Math.sin(angle);
		cot = 1 / Math.tan(angle);
		
		message = "Leg1: " + leg1 + " Leg2: " + leg2 + " Hypotenuse: " + hypot + 
						"\nSin: " + sin + "\nCos: " + cos + " \nTan: " + tan + 
						"\nSec: " + sec + "\nCsc: " + csc + "\nCot: " + cot;
		JOptionPane.showMessageDialog(null, message);
		
	}
	public static void main(String []args){
		dedTriangle program = new dedTriangle();
		program.calcTrigFunctions();
	}
}
