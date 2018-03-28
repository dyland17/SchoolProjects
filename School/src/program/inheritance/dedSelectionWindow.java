package program.inheritance;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ButtonGroup;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
/*
 * Input: The user will click one of three
 * 				radio buttons. Each labeled 
 * 				point, circle, and cylinder
 * 				respectively.
 * Output: Will continue onto the calculation
 * 					window with his selection.
 */
public class dedSelectionWindow extends JFrame implements ActionListener{
		private JRadioButton[] button = new JRadioButton[3];
		private ButtonGroup objectGroup = new ButtonGroup();
		public dedSelectionWindow(){
				setupRadioButtons();
				JLabel info = new JLabel("Pick an object to create.");
				//Setup Window
				setLayout(new GridLayout(4,0));
				setTitle("Selection Window");
				setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
				setPreferredSize(new Dimension(200,250));
				//Adding components
				add(info);
				add(button[0]);
				add(button[1]);
				add(button[2]);
				//Still setting up JFrame
				pack();
				setLocationRelativeTo(null);
				setVisible(true);
		}
		private void setupRadioButtons() {
				//Setup of radio buttons.
				button[0] = new JRadioButton("Point");
				button[0].addActionListener(this);
				objectGroup.add(button[0]);
				
				button[1] = new JRadioButton("Circle");
				button[1].addActionListener(this);
				objectGroup.add(button[1]);
				
				button[2] = new JRadioButton("Cylinder");
				button[2].addActionListener(this);
				objectGroup.add(button[2]);
		}
		@Override
		public void actionPerformed(ActionEvent e) {
				//Gets the radio button selected.
				JRadioButton source = (JRadioButton) e.getSource();
				//Passes the radio button's string to the new window.
				new dedCalculationWindow(source.getText());
				//Releases the resources of this window.
				this.dispose();
		}
}
