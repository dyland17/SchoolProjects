package cps162.assignments.commas;
import java.util.Scanner;
/**
 *  This class contains a method that will take any long value from the user and 
 *  provide a comma formatted value back.
 * @author Dylan Dewald
 */
public class Commas {
		
		public static void main(String []args){
			long number;
			String numberFormatted;
			Scanner scan = new Scanner(System.in);
			System.out.println("Write a number to be formatted with commas?");
			while(scan.hasNextLong()){
				number = scan.nextLong();
				numberFormatted = formatLongWithCommas(number);
				System.out.println(numberFormatted);
			}
		}
	
		/**
		 * 
		 * @param number long value to be formatted with commas.
		 * @return Will return a String representing the long value with commas.
		 */
		public static String formatLongWithCommas(long number){
			String formattedNumber = null;
			// Returns anything that is under a thousand.
			if(number > 0){
				if(number < 1000){
					formattedNumber = String.valueOf(number); 
					return formattedNumber;
				}
				else{
					long shortenedNumber = number / 1000;
					long modNumber = number % 1000;
					if(modNumber >= 100){
						if(modNumber == 0){
							formattedNumber = formatLongWithCommas(shortenedNumber) + ",000";
						}
						else{
							formattedNumber = formatLongWithCommas(shortenedNumber) + "," + modNumber;
						}
					}
					else{
						if(modNumber == 0){
							formattedNumber = formatLongWithCommas(shortenedNumber) + ",000";
						}
						else{
							if(modNumber < 10){
								formattedNumber = formatLongWithCommas(shortenedNumber) + ",00" + modNumber;
							}
							else{
								formattedNumber = formatLongWithCommas(shortenedNumber) + ",0" + modNumber;
							}
						}
					}
				}
			}
			else{
				if(number > -1000){
					formattedNumber = String.valueOf(number); 
					return formattedNumber;
				}
				else{
					long shortenedNumber = number / 1000;
					long modNumber = number % 1000;
					if(modNumber <= -100){
						if(modNumber == 0){
							formattedNumber = formatLongWithCommas(-shortenedNumber) + ",000";
						}
						else{
							formattedNumber = formatLongWithCommas(shortenedNumber) + "," + -modNumber;
						}
					}
					else{
						if(modNumber == 0){
							formattedNumber = formatLongWithCommas(-shortenedNumber) + ",000";
						}
						else{
							if(modNumber > -10){
								formattedNumber = formatLongWithCommas(shortenedNumber) + ",00" + -modNumber;
							}
							else{
								formattedNumber = formatLongWithCommas(shortenedNumber) + ",0" + -modNumber;
							}
						}
					}
				}
			}
			return formattedNumber;
		}
}