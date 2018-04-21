package cps162.assignments.pattern;

public class Pattern {
	
	
	
	public static void main(String []args){
		String pattern = getPattern(7);
		pattern = makePrettyPattern(pattern, 1);
		System.out.println(pattern);
	}
	
	//vvvvvvvvv Not implemented yet vvvvvvvvvvvvv
	public static void printPattern(int n){
		if(n <= 5){
			System.out.println(getPattern(n));
		}
		else{
			String prevPattern = getPattern(n-1);
			System.out.println(prevPattern);
			System.out.println(getPattern(n));
			System.out.println(prevPattern);
		}
	}
	
	//Constructs pattern perfectly
	private static String getPattern(int caseNum){
		String pattern = null;
		if(caseNum == 1){
			return "1";
		}
		else if(caseNum > 1){
			String prevPattern = getPattern((caseNum - 1));
			pattern = prevPattern + " " + caseNum + " " + prevPattern; 
		}
		return pattern;
	}
	// vvvvvvvvvvvvvvvvv does not work exactly right vvvvvvvvvvvvvvvvv
	private static String makePrettyPattern(String pattern, int returnIndex){
		int length = pattern.length();
		int nextLineNum = returnIndex * 64;
		if(nextLineNum > length){
			return pattern;
		}
		else{
			pattern = makePrettyPattern(pattern, (returnIndex + 1));
			char[] sequence = pattern.toCharArray() ;
			sequence[nextLineNum] = '\n';
			return String.valueOf(sequence);
		}
	}
}
