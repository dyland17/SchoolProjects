package program.exceptions;

public class HoursException extends Exception {
	String message;
	//Constructor creates a different message depending on
			//hours entered.
	public HoursException(int hours) {
			if(hours < 0){
				message = "Your hours cannot be negative.";
			}
			else{
				message = "Your hours are to high.";
			}
	}
	@Override
	public String getMessage() {
		return message;
	}
	
}
