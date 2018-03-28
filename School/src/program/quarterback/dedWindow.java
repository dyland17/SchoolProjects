package program.quarterback;
/*Name: Dylan Dewald
 * Date: 02/10/16
 * Purpose: This class is JFrame layout class. It sets up the
 * 				look of the window and has JTextFields to receive
 * 				user input.
*/

import java.awt.GridLayout;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextArea;
import javax.swing.JTextField;


public class dedWindow extends JFrame{
	//To get user input...
	private JTextField qbNameField, completePassField,
					   attemptedPassField, receivingYardField,
					   passingTDField,interceptionField;
	//Descriptions for fields.
	private JLabel qbNameLab,completePassLab,attemptedPassLab,
				   receivingYardLab,passingTDLab, interceptionLab;
	//Display information from input.
	private JButton doneBut;
	//Constructor.
	public dedWindow(ActionListener al){
		super("QB Statistics");
		//setup of JFrame
		setLayout(new GridLayout(7,2));
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		setupGUI(al);
		pack();
		setLocationRelativeTo(null);
		setVisible(true);
	}
	private void setupGUI(ActionListener al) {
		//Labels
		qbNameLab = new JLabel("Please enter the quarterback's name: ");
		completePassLab = new JLabel("Please enter the number of completed passes: ");
		attemptedPassLab = new JLabel("Please enter the number of attempted passes: ");
	    receivingYardLab = new JLabel("Please enter the number of receiving yards: ");
	    passingTDLab = new JLabel("Please enter the number of passing TDs: ");
	    interceptionLab = new JLabel("Please enter the number of intercepted passes: ");
		//JTextFields
	    int length = 10;
	    qbNameField = new JTextField(length); 
	    completePassField = new JTextField(length); 
		attemptedPassField = new JTextField(length); 
		receivingYardField = new JTextField(length); 
		passingTDField = new JTextField(length); 
		interceptionField = new JTextField(length);
		//Display info component.
		doneBut = new JButton("Done");
		doneBut.addActionListener(al);
		//addingComponents to JFrame
		add(qbNameLab);
		add(qbNameField);
		
		add(completePassLab);
		add(completePassField);
		
		add(attemptedPassLab);
		add(attemptedPassField);
		
		add(receivingYardLab);
		add(receivingYardField);
		
		add(passingTDLab);
		add(passingTDField);
		
		add(interceptionLab);
		add(interceptionField);
		
		add(doneBut);
	}
	public JTextField getQbNameField() {
		return qbNameField;
	}
	public JTextField getCompletePassField() {
		return completePassField;
	}
	public JTextField getAttemptedPassField() {
		return attemptedPassField;
	}
	public JTextField getReceivingYardField() {
		return receivingYardField;
	}
	public JTextField getPassingTDField() {
		return passingTDField;
	}
	public JTextField getInterceptionField() {
		return interceptionField;
	}
	public JButton getDoneBut() {
		return doneBut;
	}
	public void clear() {
		this.qbNameField.setText("");
		this.completePassField.setText("");
		this.attemptedPassField.setText("");
		this.receivingYardField.setText("");
		this.passingTDField.setText("");
		this.interceptionField.setText("");
		
	}
	
}
