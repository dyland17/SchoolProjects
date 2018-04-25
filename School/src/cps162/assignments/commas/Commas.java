package cps162.assignments.commas;

public class Commas {
		
		public static void main(String []args){
			int number = 185700125;
			String output = formatLongWithCommas(number);
			System.out.println(output);
		}
	
	
		public static String formatLongWithCommas(long number){
			String formattedNumber = null;
			// Returns anything that is under a thousand.
			if(number < 1000){
				formattedNumber = String.valueOf(number); 
				return formattedNumber;
			}
			else{
				long shortenedNumber = number / 1000;
				long modNumber = number % 1000;
				if(modNumber == 0){
					formattedNumber = formatLongWithCommas(shortenedNumber) + ",000";
				}
				else{
					formattedNumber = formatLongWithCommas(shortenedNumber) + "," + modNumber;
				}
			}
			return formattedNumber;
			//formatLongWithCommas(dividedNumber);
		}
		private static int findHowManyCommasNeeded(long number){
			return findHowManyCommasNeeded(number, 1000, 0);
		}
		private static int findHowManyCommasNeeded(long number, long checkNumber,int commasNeeded){
			
			if(checkNumber <= number){
				commasNeeded++;
				return findHowManyCommasNeeded(number, (checkNumber*1000), commasNeeded);
			}
			else{
				return commasNeeded;
			}
		}
}
