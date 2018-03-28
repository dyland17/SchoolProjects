package program.filereview;
//Keeps track of letters who are linked to how many times they occur in a string.
public class NumberLetter {
	private char letter;
	private int occurences;
	
	public NumberLetter(int o, char l){
		occurences = o;
		letter = l;
	}

	public char getLetter() {
		return letter;
	}

	public void setLetter(char letter) {
		this.letter = letter;
	}

	public int getOccurences() {
		return occurences;
	}

	public void setOccurences(int occurences) {
		this.occurences = occurences;
	}
	public void increaseOccurence(){
		occurences++;
	}
	
}
