package program.skateboards;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.DecimalFormat;

import javax.swing.JFrame;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.AbstractListModel;
import javax.swing.JButton;

public class SkateboardDesigner implements ActionListener{
	//GUI fields needed for program
	private JButton butCalculate;
	private JFrame winDesigner;
	private JLabel labDecks;
	private JComboBox comBoxDecks;
	private JLabel labTrucks;
	private JComboBox comBoxTrucks;
	private JLabel labWheels;
	private JComboBox comBoxWheels;
	private JLabel labMisc;
	private JList listMisc;
	private JLabel labTotal;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
					SkateboardDesigner window = new SkateboardDesigner();
					window.winDesigner.setVisible(true);
	}

	/**
	 * Create the application.
	 */
	public SkateboardDesigner() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		//Gui setup
		
		//window setup
		winDesigner = new JFrame();
		winDesigner.setTitle("Skateboard Designer");
		winDesigner.setBounds(100, 100, 450, 300);
		winDesigner.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		winDesigner.getContentPane().setLayout(null);
		
		labDecks = new JLabel("Choose a Deck:");
		labDecks.setBounds(10, 11, 83, 30);
		winDesigner.getContentPane().add(labDecks);
		
		comBoxDecks = new JComboBox();
		comBoxDecks.setBounds(236, 11, 159, 30);
		comBoxDecks.setModel(new DefaultComboBoxModel(new String[] {"The MasterThrasher", "The Dictator ", "The Street King"}));
		winDesigner.getContentPane().add(comBoxDecks);
		
		labTrucks = new JLabel("Choose a truck:");
		labTrucks.setBounds(10, 52, 97, 30);
		winDesigner.getContentPane().add(labTrucks);
		
		comBoxTrucks = new JComboBox();
		comBoxTrucks.setBounds(236, 52, 159, 30);
		comBoxTrucks.setModel(new DefaultComboBoxModel(new String[] {"Stock trucks", "Thunder trucks", "King pin trucks"}));
		winDesigner.getContentPane().add(comBoxTrucks);
		
		labWheels = new JLabel("Choose wheel size:");
		labWheels.setBounds(10, 95, 116, 30);
		winDesigner.getContentPane().add(labWheels);
		
		comBoxWheels = new JComboBox();
		comBoxWheels.setBounds(236, 95, 159, 30);
		comBoxWheels.setModel(new DefaultComboBoxModel(new String[] {"51 mm", "55 mm", "58 mm", "61 mm"}));
		winDesigner.getContentPane().add(comBoxWheels);
		
		labMisc = new JLabel("Misc Products:");
		labMisc.setBounds(10, 180, 116, 30);
		winDesigner.getContentPane().add(labMisc);
		
		listMisc = new JList();
		listMisc.setBounds(110, 169, 116, 81);
		//Creating a list to put in listMisc.
		listMisc.setModel(new AbstractListModel() {
			String[] values = new String[] {"Riser pads $2", "Nuts and Bolts $3", "Grip Tape $10", "Bearings $30"};
			public int getSize() {
				return values.length;
			}
			public Object getElementAt(int index) {
				return values[index];
			}
		});
		winDesigner.getContentPane().add(listMisc);
		
		labTotal = new JLabel("subTotal:   Tax:    Total: ");
		labTotal.setBounds(61, 136, 270, 14);
		winDesigner.getContentPane().add(labTotal);
		
		butCalculate = new JButton("Calculate");
		butCalculate.setBounds(236, 166, 159, 44);
		butCalculate.addActionListener(this);
		winDesigner.getContentPane().add(butCalculate);
		
	}

	@Override
	public void actionPerformed(ActionEvent e) {
			calculate();
		
	}

	private void calculate() {
			//Input
		double total = 0.0;
		//Seeing which deck was picked.
		switch(comBoxDecks.getSelectedIndex()){
		case 0:
				total += 60.0;
				break;
		case 1:
				total += 45.0;
				break;
		case 2:
				total += 50.0;
		}
		//Seeing which truck was picked.
		switch(comBoxTrucks.getSelectedIndex()){
			case 0:
				total += 20.0;
				break;
			case 1:
				total += 65.0;
				break;
			case 2:
				total += 80.0;
		}
		//Seeing which wheel was picked.
		switch(comBoxWheels.getSelectedIndex()){
			case 0:
				total += 20;
				break;
			case 1:
				total += 22;
				break;
			case 2:
				total += 24;
				break;
			case 3:
				total += 28;
				break;
		}
		//Misc objects
		//Using the getSelectedIndices() method to see which
				//indexes were picked.
		int[] choice = listMisc.getSelectedIndices();
		for(int i = 0; i < choice.length; i++){
			if(choice[i] == 0){
				total += 2.0;
			}
			else if(choice[i] == 1){
				total += 3.0;
			}
			else if(choice[i] == 2){
				total += 10.0;
			}
			else if(choice[i] == 3){
				total += 30.0;
			}
		}
		setTotal(total);
	}
	//Setting labTotal to display the subtotal, tax, and final total of the purchase.
	private void setTotal(double total) {
		DecimalFormat dollar = new DecimalFormat("#.00");
		double tax = total * 0.06;
		labTotal.setText("SubTotal: " + dollar.format(total) + " Tax: " +
									dollar.format(tax) + " Total: " + dollar.format((total + tax)) );
	}
}
