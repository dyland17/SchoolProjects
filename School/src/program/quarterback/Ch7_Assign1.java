package program.quarterback;
/************************************************
*
* Student: Dylan Dewald
* Date: 3/20/07
* Assignment Ch 7 #1
* Professor: Fahringer
*
*	This program will use JTextFields to get
*	a quarterback's name, completed passes,
*	attempts, receiving yards, touchdowns, and
*	interceptions.  Using that data, I will
*	calculate statistics regarding the QB,
*	culminating in the QB rating.  This data,
*	along with the input, will be displayed in
*	a final dialog box.
*
**************************************************/


import java.util.*;

import javax.swing.*;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.DecimalFormat;

public class Ch7_Assign1 implements ActionListener
{
	dedWindow win = new dedWindow(this);
    public static void main(String[] args){
			new Ch7_Assign1();
				
	 }
			
	//When the button is clicked this method runs.	
	@Override
	public void actionPerformed(ActionEvent ae) {
			//variables used in calculations for the QB.
			int completed,attempted,yard,touchdown,interception;
			/*****************************************************************/ 		
			   //------------------Input the Data---------------------------
				
						/*****************************************
						*
						* Using separate, subsequent JTextFields,
						* the user will be prompted to enter data
						* regarding the quarterback.
						*
						******************************************/
			String name = win.getQbNameField().getText();
			completed = Integer.parseInt(win.getCompletePassField().getText());
			attempted = Integer.parseInt(win.getAttemptedPassField().getText());
			yard = Integer.parseInt(win.getReceivingYardField().getText());
			touchdown = Integer.parseInt(win.getPassingTDField().getText());
			interception = Integer.parseInt(win.getInterceptionField().getText());
			
			double compResult = calcCompletions(completed, attempted);
			double ygaResult = calcYGA(yard, attempted);
			double tdResult = calcTDResult(touchdown, attempted);
			double interResult = calcInter(interception, attempted);
			double rating = qbRating(compResult, ygaResult, 
											tdResult, interResult);
			//----------------Display the output--------------------------
			
			/********************************************
			*
			* Using the data computed from my methods
			* below and called/stored above, I use
			* a dialog box to display all of the
			* data at once.
			* I decided to use the DecimalFormat class
			* to display the output to three decimal
			* places, since it is much cleaner than printf
			*
			*********************************************/
			DecimalFormat threeDec = new DecimalFormat("0.000");
			String output = name + "'s quarterback rating is: " + 
							threeDec.format(rating) + "." + 
							"\n" + "This is based upon the data you inputted:" +
							"\n" + "Completed Passes: " + completed + "." +
							"\n" + "Attempted Passes: " + attempted + "." +
							"\n" + "Receiving Yards: " + yard + "." +
							"\n" + "Touchdowns: " + touchdown + "." +
							"\n" + "Interceptions: " + interception + "." + "\n" +
							"\n" + "Percentage of Completions: " 
							+ threeDec.format(compResult) + "." +
							"\n" + "Average Yards Gained Per Attempt: " 
							+ threeDec.format(ygaResult) + "." +
							"\n" + "Percentage of Touchdowns: " 
							+ threeDec.format(tdResult) + "." +
							"\n" + "Percentage of Interceptions: " 
							+ threeDec.format(interResult) + ".";

					JOptionPane.showMessageDialog(null, output,
								"Quarterback Rating", JOptionPane.INFORMATION_MESSAGE);
					win.clear();
		
	}

				/********************************************
				*
				* Here, I have created methods that receive
				* their input data from the dialog boxes
				* above and return double values to be
				* stored in the variables above, when the
				* methods are called.
				*
				* The method for the QB rating requires
				* several 'if' statements nested within
				* the method, for proper calculation
				* of the rating.  By using non-void methods,
				* I was able to more easily manipulate the
				* data in terms of calculating the QB rating,
				* as well as the output.
				*
				*********************************************/
					
			public static double calcCompletions(int comp, int attempt)
			{
				double comps = comp;
				double attempts = attempt;
				double compPerc = (comps/attempts)*100;
				return compPerc;				
			}
			
			public static double calcYGA(int yard, int attempt)
			{
				double yards = yard;
				double attempts = attempt;
				double YGA = (yards/attempts);
				return YGA;
			}	
				
			public static double calcTDResult(int td, int attempt)
			{
				double tds = td;
				double attempts = attempt;
				double tdPerc = (tds/attempts)*100;
				return tdPerc;			
			}
			
			public static double calcInter(int inter, int attempt)
			{
				double inters = inter;
				double attempts = attempt;
				double interPerc = (inters/attempts)*100;
				return interPerc;	
			}
			
			public static double qbRating(double comp, double yga, 
													double td, double inter)
			{	
				double compRating = (comp - 30.0)*.05;
				if (compRating < 0.0)
					compRating = 0.0;
				if (compRating > 2.375)
					compRating = 2.375;
					
				double ygaRating = (yga - 3.0)*0.25;
				if (ygaRating < 0.0)
					ygaRating = 0.0;
				if (ygaRating > 2.375)
					ygaRating = 2.375;
					
				double tdRating = (td*0.2);
				if (tdRating > 2.375)
					tdRating = 2.375;
					
				double intRating = 2.375 - (inter*0.25);
				if (intRating < 0.0)
					intRating = 0.0;	
				
				double rating = (compRating + ygaRating + 
										tdRating + intRating)/6 * 100;
				return rating;
			}					
		
	
	/*****************************************************************/	
}