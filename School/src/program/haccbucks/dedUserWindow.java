package program.haccbucks;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

/*
 * Name: Dylan Dewald
 * Date: 02/20/16
 * Input: User inputs data for lottery numbers and
 * 				user chosen numbers.
 * Output: Shows the winnings of the user, with the
 * 				numbers chosen.
 */
public class dedUserWindow extends JFrame implements ActionListener{
	//GUI for the user.
		//Labels for lottery numbers.
	JLabel []labLot;
	//Labels for user picked numbers
	JLabel []labUserNum;
	//JTextFields for lottery numbers.
	JTextField []fieldLot;
	//JTextFields for user numbers.
	JTextField []fieldUser;
	//Button to enter Data.
	JButton butEnter;
	
	public dedUserWindow(){
		//setup of JFrame
		super("Lottery, Lucky 40.");
		setPreferredSize(new Dimension(450,260));
		setLayout(new FlowLayout());
		getContentPane().setBackground(Color.YELLOW);
		
		setupGui();
		//setup of JFrame
		pack();
		setLocationRelativeTo(null);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setVisible(true);
	}

	private void setupGui() {
		setupTextFields();
		setupLabels();
		//JButton
		butEnter = new JButton("Enter");
		butEnter.addActionListener(this);
		butEnter.setPreferredSize(new Dimension(65,25));
		
		//Adding components to window.
		for(int x = 0; x < 6; x++){
			add(labLot[x]);
			add(fieldLot[x]);
			add(labUserNum[x]);
			add(fieldUser[x]);
		}
		add(butEnter);
	}

	private void setupLabels() {
		//Initializing labels for Lottery Numbers.
		labLot = new JLabel[6];
		
		for(int x = 0; x < 6; x++){
			labLot[x] = new JLabel("Enter Lottery Number " + (x+1) + ": ");
			labLot[x].setForeground(Color.red);
		}
		//Initializing labels for user Numbers.
		labUserNum = new JLabel[6];
		
		for(int y = 0; y < 6; y++){
			labUserNum[y] = new JLabel("Pick a number for " + (y+1) + ": ");
			labUserNum[y].setForeground(Color.red);
		}
	}

	private void setupTextFields() {
		//Column length for text fields.
		final int len = 5;
		//Initializing lottery textFields.
		fieldLot = new JTextField[6];
		
		for(int x = 0; x < 6; x++){
			fieldLot[x] = new JTextField(len);
		}
		//Initializing user Number textFields.
		fieldUser = new JTextField[6];
		
		for(int y = 0; y < 6; y++){
			fieldUser[y] = new JTextField(len);
		}
		
		
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		calculateWinnings();
		
	}

	private void calculateWinnings() {
		//keeps track of matches and consecutive matches.
		int matches = 0, consecutives = 0;
		//Used to capture data from fields.
		int []lotNum = new int[6];
		int []userNum = new int[6];
		//Keeps track of the indexes of the matches to calculate
			//the consecutives at the end.
		dedMatches matchCounter = new dedMatches();
		//Retreiving numbers
		for(int num = 0; num < 6; num++){
			lotNum[num] = Integer.parseInt(fieldLot[num].getText());
			userNum[num] = Integer.parseInt(fieldUser[num].getText());
			
		}
		//Finding matches and consecutive numbers.
		for(int x = 0; x < 6; x++){
			for(int y = 0; y < 6; y++){
				if(lotNum[x] == userNum[y]){
					matches++;
					matchCounter.enterMatch(y);
				}
			}
		}
		//Finding the consecutive matches.
		consecutives = matchCounter.calculateConsecutives();
		//Calculate Winnings
		if(matches == 1){
			dedLotteryGenerator.winnings = 10;
		}
		else if(matches == 2){
			dedLotteryGenerator.winnings = 25;
		}
		else if(matches >= 3 && matches <= 5){
			if(consecutives > 1){
				dedLotteryGenerator.winnings = (matches * 50);
			}
			else{
				dedLotteryGenerator.winnings = (matches * 25);
			}
		}
		else if(matches == 6){
			if(consecutives == 5){
				dedLotteryGenerator.winnings = 10000;
			}
			else{
				dedLotteryGenerator.winnings = 1000;
			}
			
		}
		//display winnings
		JOptionPane.showMessageDialog(null, "Winnings: " + dedLotteryGenerator.winnings + 
									 "Cons: " + consecutives);
		//resetWinnings
		dedLotteryGenerator.winnings = 0;
	}
	
}
