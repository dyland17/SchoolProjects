package program.filereview;
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import javax.swing.*;
/*
 * Name: Dylan Dewald
 * Date: 02/03/16
 * Program Purpose: This program grabs a sonnet from the file Poem.txt and
 * 						formats it with out punctuation and capitalization.
 * 						Then in the end of the program it displays the 
 * 						original poem and the number of occurrences of e,t,o,i
 * 						n,s,h,r,d,l, and u.
*/
public class dedFileAnalyzer {
	//Shakespear's poem.
	private String sonnet = "";
	//A Class in a 11 element array to keep track of the 
		//numbers and associated letters.
	NumberLetter[] nletters = {new NumberLetter(0,'e'),new NumberLetter(0,'t'),
	new NumberLetter(0,'o'),new NumberLetter(0,'i'),new NumberLetter(0,'n'),
	new NumberLetter(0,'s'),new NumberLetter(0,'h'),new NumberLetter(0,'r'),
	new NumberLetter(0,'d'),new NumberLetter(0,'l'),new NumberLetter(0,'u')};
	//GUI.
	JFrame window;
	JPanel poem, letterInfo;
	
	public dedFileAnalyzer(){
		//opening the file that contains the sonnet written by Shakespear.
		try {
			BufferedReader fin	 = new BufferedReader(new FileReader("src/Poem.txt"));
			//After the file is open we need to read the file.
			readFile(fin);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		
		
	}
	
	//Reads the file and stores it in the variable sonnet.
	public void readFile(BufferedReader fin) {
		String tempS;
		try {
			//Lopping the file to get each line into the
				//string variable.
			while((tempS = fin.readLine()) != null){
				sonnet += tempS + "\n";
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		try {
			//Closing the file for now.
			fin.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		formatPoem();
	}

	//This removes most grammer necessities.
	private void formatPoem() {
		//Getting rid of punctuation.
		sonnet = sonnet.replaceAll("\\p{Punct}|\\d","");
		//Setting letters to lower case.
		sonnet = sonnet.toLowerCase();
		startCounting();
	}

	//Counts the occurrences of letters in the poem.
	private void startCounting() {
		//Declaring variables.
		int x = 0;
		char currentC;
		//testing if it is at the end of the string of characters.
		while(x < (sonnet.length() - 1)){
			//updating the currentC variable
			currentC = sonnet.charAt(x);
				for(int o = 0; o < nletters.length; o++){
					//Testing to see if the current char in the poem
						//is matching any of the letters in nletters
						//array.
					if(currentC == nletters[o].getLetter()){
						nletters[o].increaseOccurence();
					}
				}
			//updating the x variable, to get the next char in the string.
			x++;
		}
		compareValuesOfCharacters();
	}

	//This method for the most part sorts the array of NumberLetter elements.
	private void compareValuesOfCharacters() {
		//Using Selective Sort with my Class NumberLetter.
		
		//The NumberLetter class keeps track of the
			//occurences of the letter it is associated with
			//and simplifies the process.
		int currentIndex;
		int iteratedIndex;
		NumberLetter cINumLetter, iINumLetter;
		for(currentIndex = 0; currentIndex < nletters.length; currentIndex++){
			for(iteratedIndex = currentIndex + 1; iteratedIndex < nletters.length; iteratedIndex++){
				cINumLetter = nletters[currentIndex];
				iINumLetter = nletters[iteratedIndex];
				if(iINumLetter.getOccurences() > cINumLetter.getOccurences()){
					nletters[currentIndex] = iINumLetter;
					nletters[iteratedIndex] = cINumLetter;
				}
			}
		}
		displayInformation();
	}

	//This method displays the window.
	private void displayInformation() {
		
		window = setupWindow();
		window.setVisible(true);
	}

	//Setting up all necessary GUI components.
	private JFrame setupWindow() {
		window = new JFrame("Poem Statistics");
		//Initializing panels.
		poem = new JPanel();
		letterInfo = new JPanel();
		//TextArea to display original poem with no
			//modifications.
		JTextArea poemArea = new JTextArea();
		poemArea.setText(getOriginalPoem());
		poem.add(poemArea);
		window.add(poem,BorderLayout.WEST);
		//Displaying the result of occurences
			//for each letter.
		JTextArea letterStats = new JTextArea(11,3);
		String tString = "";
		for(int x = 0; x < nletters.length; x++){
			tString += (String) (nletters[x].getLetter() + ": " + nletters[x].getOccurences()
							   + "\n");
		}
		letterStats.append(tString);
		letterInfo.add(letterStats);
		window.add(letterInfo,BorderLayout.EAST);
		//Making every component fit snug in the window.
		window.pack();
		window.setLocationRelativeTo(null);
		window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		return window;
	}
	//Retrieves the original poem from the file for reference.
	private String getOriginalPoem(){
		BufferedReader freader;
		String poem = "";
		try {
			freader = new BufferedReader(new FileReader("src/Poem.txt")); 
		
			String tempString;
			while((tempString = freader.readLine()) != null){
				poem += tempString + "\n";
			}
			freader.close();
		} 
		catch (IOException e) {
			e.printStackTrace();
		}
		return poem;
	}


	public static void main(String[] args) {
		new dedFileAnalyzer();

	}
}
