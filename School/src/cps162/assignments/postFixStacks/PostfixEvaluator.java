
import java.util.Stack;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.EmptyStackException;
import java.util.NoSuchElementException;

public class PostfixEvaluator {
	
	public static String evaluate(String item) {
		
			Stack<Double> numbers = new Stack<Double>();
			TokenScanner scanner = new TokenScanner(item);
			try{
				if(item == null || (item.trim()).isEmpty()){
					throw new NoSuchElementException();
				}	
				
				do{
					Token currentToken =  scanner.nextToken();
					if(currentToken.isNumber()){
						numbers.push(currentToken.numberValue());
					}
					else if(currentToken.isOperator()){
						if(numbers.size() < 2){
							throw new EmptyStackException();
						}
						else{
							completeOperation(numbers, currentToken);
						}
					}
					else if(currentToken.isLeftParen() || currentToken.isRightParen()){
						throw new ParanException();
					}
					else{
						throw new NoSuchElementException();
					}
				}while(scanner.hasNextToken());
			}
			catch (NoSuchElementException e){
				return "No input";
			}
			catch(EmptyStackException e){
				return "nderflow";
			}
			catch(ArithmeticException e){
				return "Infinity";
			} 
			catch (ParanException e) {
				return "has no meaning here";
			}
			
			try{
				if(basicInputCheck(item) == false){
					throw new NotAllInputUsedException();
				}
				if(numbers.size() > 1){
					throw new ValuesLeftOnStackException();
				}
				else{
					return String.valueOf(numbers.pop());
				}
			}
			catch(NotAllInputUsedException e){
				return "not all input used";
			}
			catch(ValuesLeftOnStackException e){
				return "values remain on stack";
			}
	}
	
	private static void completeOperation(Stack<Double> numbers, Token operatorToken) throws ArithmeticException{
		Double topNumber = numbers.pop();
		Double nextNumber = numbers.pop();
		Double finalNumber = null;
		Character c = operatorToken.operatorCharValue();
		switch(c){
			case '*':
				finalNumber = nextNumber * topNumber;
				break;
			case '/':
				if(topNumber == 0.00){
					throw new ArithmeticException();
				}
				finalNumber = nextNumber / topNumber;
				break;
			case '+':
				finalNumber = nextNumber + topNumber;
				break;
			case '-':
				finalNumber = nextNumber - topNumber;
				break;
		}
		numbers.push(finalNumber);
	}
	private static boolean basicInputCheck(String item){
		int i = 0;
		int periodCount = 0;
		while(i < item.length()){
			Character ch = item.charAt(i);
			
			if(Character.isLetter(ch)){
				return false;
			}
			else if(ch.equals('.')){
				periodCount++;
			}
			else if(ch.equals('?')){
				return false;
			}
			else if(ch.equals('#')){
				return false;
			}
			else if(Character.isWhitespace(ch)){
				periodCount = 0;
			}
			if(periodCount > 1){
				return false;
			}
			i++;
		}
		return true;
	}
	static class ParanException extends Exception{}
	static class NotAllInputUsedException extends Exception{}
	static class ValuesLeftOnStackException extends Exception{}
	
}
