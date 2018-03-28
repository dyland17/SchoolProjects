package program.exceptions;

public class PayRateException extends Exception {
	//Message shown when this exception is thrown.
	public PayRateException(double payRate) {
			super("This pay rate is invalid: " + payRate);
	}

}
