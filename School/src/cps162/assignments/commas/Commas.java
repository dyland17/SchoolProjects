package cps162.assignments.commas;

public class Commas {
		
		public static void main(String []args){
			int number = 1000000000;
			formatLongWithCommas(number);
			System.out.println(number);
		}
	
	
		public static String formatLongWithCommas(long number){
			String formattedNumber;
			if(number < 1000){
				return String.valueOf(number); 
			}
			else{
				//formatLongWithCommas(dividedNumber);
			}
			return null;
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
