package cps162.assignments.postFixStacks;
import java.util.Stack;
import java.util.EmptyStackException;
import java.util.NoSuchElementException;

public class PostfixEvaluator {
	
	public static String evaluate(String item) {
			Stack<Double> numbers = new Stack<Double>();
			TokenScanner scanner = new TokenScanner(item);
			try{
				if(item == null || (item.trim()).isEmpty()){
					throw new EmptyStringException();
				}
				while(scanner.hasNextToken()){
					//if(scanner.nextToken())
				}
			}
			catch(ArithmeticException e){
				return "Infinity";
			}
			catch(EmptyStringException e){
				return "No input";
			}
			
		return null;
	}
	
	public static class EmptyStringException extends Exception{
		
	}
}
