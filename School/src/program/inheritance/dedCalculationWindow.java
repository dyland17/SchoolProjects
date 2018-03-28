package program.inheritance;
import java.awt.Dimension;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
/*Input:	The user would enter values for
 * 				the x, y, radius, and height
 * 				respectively.
 * Output: Will display results of radius
 * 					circumference, volume, and surface
 * 					area respectively.
 */
public class dedCalculationWindow extends JFrame implements ActionListener{
	//Arrays to hold JTextFields and JLabels.
	private JTextField[] field;
	private JLabel[] label;
	//Button to push to do the calculations
		//and display results.
	private JButton calculate;
	private int rows = 0;
	//Constructor
	public dedCalculationWindow(String rbutTitle) {
		//Setup JFrame
		setTitle("Calculation Window");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setPreferredSize(new Dimension(200,250));
		//SetupGui.
		setupGui(rbutTitle);
		//Finishing setup of JFrame.
		pack();
		setLocationRelativeTo(null);
		setVisible(true);
	}
	//Sets up the GUI for the user.
	private void setupGui(String rbutTitle) {
			int xCord = 20;
			int yCord = 20;
			//Determining how many rows are needed by a string
					// parameter.
			if(rbutTitle.equals("Point")){
					rows = 3;
			}
			else if(rbutTitle.equals("Circle")){
					rows = 4;			
			}
			else if(rbutTitle.equals("Cylinder")){
					rows = 5;
			}
			//Setting up how many elements in each array.
			field = new JTextField[rows];
			label = new JLabel[rows];
			//Making button.
			calculate = new JButton("Calculate");
			calculate.addActionListener(this);
			//Absolute Positioning = null
			setLayout(null);
			//Setting up rows
			for(int x = 0; x < (rows - 1); x++){
					field[x] = new JTextField();
					//Setting each labels text field.
					switch (x){
							case 0:
									label[x] = new JLabel("X: ");
									break;
							case 1:
									label[x] = new JLabel("Y: ");
									break;
							case 2: 
									label[x] = new JLabel("Radius: ");
									break;
							case 3:
									label[x] = new JLabel("Height: ");
					}
					//setting locations
					label[x].setBounds(xCord, (yCord*(x+1)), 45, 20);
					add(label[x]);
					field[x].setBounds((xCord*4),(yCord*(x+1)),80,20);
					add(field[x]);
					
			}
			//Setting location of calculation button
				//then adding it.
			calculate.setBounds(xCord, (yCord*(rows+1)), 88, 20);
			add(calculate);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
			//Text fields are as followed:
					//0 is x coordinate.
					//1 is y coordinate.
					//2 is radius
					//3 is height
			//This depends on which object the user wanted to create.
			if(e.getSource() == calculate){
					//Done ahead of time because every object uses x and y.
					int x = Integer.parseInt(field[0].getText());
					int y = Integer.parseInt(field[1].getText());
					double radius, height;
					//Rows determines which object was chosen.
					switch(rows){
							case 3:
									dedPoint po = new dedPoint(x,y);
									JOptionPane.showMessageDialog(null, po);
									break;
							case 4:
									radius = Double.parseDouble(field[2].getText());
									dedCircle circ = new dedCircle(x,y,radius);
									JOptionPane.showMessageDialog(null, circ);
									break;
							case 5:
								radius = Double.parseDouble(field[2].getText());
								height = Double.parseDouble(field[3].getText());
								dedCylinder cylind = new dedCylinder(x,y,radius,height);
								JOptionPane.showMessageDialog(null, cylind);
					}
					//Returns resources to the system.
					this.dispose();
			}	
	}
}
