package program.exceptions;

public class IdNumberException extends Exception {
	//Message shown when this exception is thrown.
	public IdNumberException(int idNumber) {
			super("ERROR:: This id number: " + idNumber + " is not valid.");
			
	}

}
