package program.exceptions;

import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

public class Window extends JFrame implements ActionListener{
		private final int size= 200;
		private JTextField[] field;
		private JLabel[] lab;
		private JButton submitBut;
		private String name;
		private int id;
		private int hours;
		private double payRate;
		private Payroll payroll;
		private boolean enteredRight = true;
		
		public Window(){
				super("New Payroll ");
				setLayout(new GridLayout(5,2));
				setPreferredSize(new Dimension((size + 60),size));
				setupGUI();
				
				pack();
				setLocationRelativeTo(null);
				setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
				setVisible(true);
		}
		private void setupGUI() {
				//Instantiate arrays.
				field = new JTextField[4];
				lab = new JLabel[4];
				for(int x = 0; x < 4; x++){
					//switch is for determining each label's text.
						switch(x){
								case 0:
									lab[x] = new JLabel("Name: ");
									break;
								case 1:
									lab[x] = new JLabel("ID: ");
									break;
								case 2:
									lab[x] = new JLabel("Hours Worked: ");
									break;
								case 3:
									lab[x] = new JLabel("Pay Rate: ");
						}
						//Setting up the corresponding text field that is next to the
								//label.
						field[x] = new JTextField(10);
						//Adding them to the screen.
						add(lab[x]);
						add(field[x]);
				}
				//Button
				submitBut = new JButton("Submit");
				submitBut.addActionListener(this);
				add(submitBut);
		}
		private void checkForErrors() throws NumberFormatException{
				enteredRight = true;
				try {
				//Grabbing information from Text fields.
				
					name = field[0].getText();
					id = Integer.parseInt(field[1].getText());
					hours =  Integer.parseInt(field[2].getText());
					payRate =  Double.parseDouble(field[3].getText());
					//Checking for those errors and throwing most appropriate exception.
					if(name.equals("")){
							throw new NameException();
					}
					if(id <= 0){
							throw new IdNumberException(id);
					}
					if(hours < 0 || hours > 84){
							throw new HoursException(hours);
					}
					if(payRate < 0.0 || payRate > 25.0){
							throw new PayRateException(payRate);
					}
				//Catches bring up a jOptionPane to show
						//a message for the error to be fixed.
				}catch (NameException e) {
						JOptionPane.showMessageDialog(null, "Enter another name.");
						enteredRight = false;
				} catch(IdNumberException e){
						JOptionPane.showMessageDialog(null, "Enter another id.");
						enteredRight = false;
				} catch (HoursException e) {
						JOptionPane.showMessageDialog(null, e.getMessage());
						enteredRight = false;
				} catch (PayRateException e) {
						JOptionPane.showMessageDialog(null, e.getMessage());
						enteredRight = false;
				}catch(NumberFormatException e){
						JOptionPane.showMessageDialog(null, "Somthing went wrong");
						enteredRight = false;
				}
		}
		//Method called when the submit button is clicked.
		@Override
		public void actionPerformed(ActionEvent ae) {
				checkForErrors();
				//Clearing fields for more information to be entered.
				for(int i = 0; i < 4; i++){
					field[i].setText("");
				}
				//Setup a pay roll
				if(enteredRight == true){
					Payroll tempPayroll = new Payroll(name, id);
					tempPayroll.setHoursWorked(hours);
					tempPayroll.setPayRate(payRate);
				
					payroll = tempPayroll;
					//Showing the new payroll created.
					JOptionPane.showMessageDialog(null, "A new payroll has been created:\n" +
																				"Name: " + payroll.getName() + "\n" +
																				"ID: " +  payroll.getIdNumber() + "\n" +
																				"Hours worked: " + payroll.getHoursWorked() + "\n" +
																				"Pay rate: $" + payroll.getPayRate() + "\n" +
																				"Gross Pay: $" + payroll.getGrossPay());
				}
		}
}