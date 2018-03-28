package program.exceptions;

public class NameException extends Exception{
		//Message shown when this exception is thrown.
		public NameException(){
				super("ERROR:: Name given is not valid. ");
		}
}
