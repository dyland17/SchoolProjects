package cps162.assignments.commas;

public class Commas {
		
		public static void main(String []args){
			int number = 50125;
			String output = formatLongWithCommas(number);
			System.out.println(output);
		}
	
	
		public static String formatLongWithCommas(long number){
			String formattedNumber = null;
			long shortenedNumber;
			long modNumber = number % 1000;
			// Returns anything that is under a thousand.
			if(number < 1000){
				formattedNumber = String.valueOf(number); 
				return formattedNumber;
			}
			else{
					if(modNumber == 0){
					shortenedNumber = number /1000;
					formattedNumber = shortenedNumber + "," + "000";
				}
				else{
					long differenceNumber = number - modNumber;
					shortenedNumber = differenceNumber /1000;
					formattedNumber = shortenedNumber + "," + modNumber;
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
