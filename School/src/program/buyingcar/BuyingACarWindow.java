package program.buyingcar;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import java.awt.GridLayout;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.math.RoundingMode;
import java.text.DecimalFormat;
/*
 * Name: Dylan Dewald
 * Date: 06/16/16
 * Input: Enter the information on your loan such as
 * 				principle, annual rate, how long is the loan,
 * 				and payments per year.
 * Output: After the information is entered it will calculate the
 * 					amortized payment amount. This will be used in
 * 					a couple calculations and will display 24 payments
 * 					each JOptionPane that pops up.
 */
public class BuyingACarWindow implements ActionListener{
	//private variable GUI fields.
	private JFrame window;
	private JTextField fieldPrincipal;
	private JTextField fieldInRate;
	private JTextField fieldLoanTime;
	private JTextField fieldPayPerYear;
	//To round numbers to dollar amount.
	private DecimalFormat dollar;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
				BuyingACarWindow window = new BuyingACarWindow();
				window.window.setVisible(true);
	}

	/**
	 * Create the application.
	 */
	public BuyingACarWindow() {
		dollar = new DecimalFormat("#.00");
		dollar.setRoundingMode(RoundingMode.HALF_UP);
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		//Setting up GUI.
		//JFrame setup.
		window = new JFrame();
		window.setTitle("Buying a Car");
		window.setBounds(100, 100, 340, 300);
		window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		window.getContentPane().setLayout(new GridLayout(0, 2, 0, 0));
		//JLabel setup.
		JLabel labPrincipal = new JLabel("Principal:");
		window.getContentPane().add(labPrincipal);
		//JTextField for principal setup.
		fieldPrincipal = new JTextField();
		window.getContentPane().add(fieldPrincipal);
		fieldPrincipal.setColumns(10);
		//JLabel for annual Rate setup.
		JLabel labInRate = new JLabel("Annual Rate:");
		window.getContentPane().add(labInRate);
		//JTextField for annual rate setup.
		fieldInRate = new JTextField();
		window.getContentPane().add(fieldInRate);
		fieldInRate.setColumns(10);
		//JLabel for years on the loan setup.
		JLabel labLoanTime = new JLabel("How long is the loan?");
		window.getContentPane().add(labLoanTime);
		//JTextField for years on the loan setup.
		fieldLoanTime = new JTextField();
		window.getContentPane().add(fieldLoanTime);
		fieldLoanTime.setColumns(10);
		//JLabel for how many payments per year setup.
		JLabel labPayPerYear = new JLabel("Payments per year?");
		window.getContentPane().add(labPayPerYear);
		//JTextField for how many payments per year setup.
		fieldPayPerYear = new JTextField();
		window.getContentPane().add(fieldPayPerYear);
		fieldPayPerYear.setColumns(10);
		//JButton for calculation announcement setup.
		JButton butCalculate = new JButton("Calculate");
		butCalculate.addActionListener(this);
		window.getContentPane().add(butCalculate);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		//After Button is Pushed
		/*A; amortized payment amount.
		 * p: principal
		 * i:  annual rate/ # of payments per year
		 */
		double p, i;
		double annualRate;
		int yearOnLoan, payments,paymentsPerYear;
		//Getting principal
		p = Double.parseDouble(fieldPrincipal.getText());
		//Getting Annual rate
		annualRate = .01 * Double.parseDouble(fieldInRate.getText());
		//Loan length
		yearOnLoan = Integer.parseInt(fieldLoanTime.getText());
		//Payments per year.
		paymentsPerYear = Integer.parseInt(fieldPayPerYear.getText());
		//Getting monthly interest rate.
		i = annualRate / paymentsPerYear;
		//Payments on loan.
		payments = paymentsPerYear * yearOnLoan;
		//Calling method to get A.
		calculateCarPayment(p,i,yearOnLoan,payments, paymentsPerYear);
	}
	//Just calculates the amortized payment.
	private void calculateCarPayment(double p, double i, int yearOnLoan, int payments, int paymentsPerYear) {
		//Calculating payment of car using formula.	
		double A = (p * i *(Math.pow((1.0 + i), payments))) / 
						((Math.pow((1.0 + i), payments)) - 1);
			//Rounding principle and payment to two decimals.
			p = Double.parseDouble(dollar.format(p));
			A = Double.parseDouble(dollar.format(A));
			String header ="Principal: " +  p + "  Annual Rate: %" + (i*paymentsPerYear/.01) + 
										 "  Total Payments: " + payments + "  Payment Amount: " + A +"\n";
			/*s.n. enter test data from worksheet	
			 *  and you should get 533.35 as a payment per month.
			 */
			printPayments(header,p,i,payments,A);
		}
	//Calculates different variables and adds them to a JOptionPane to show later.
	private void printPayments(String header, double p, double i, int payments,double A) {
			//Variables for calculations and logic.
			String lineInfo = "";
			boolean done = false;
			int line = 1;
			double totalInterest = 0.0;
			double payment;
			//Showing the header.
			String subHead = "Payment number      Interest due      Payment      Remaining Balence\n";
			lineInfo += header + "\n" + subHead;
			while(done == false){
				payment = A;
				//Payment number
				lineInfo += line + "      ";
				//Interest Due
				totalInterest += (p*i);
				lineInfo += dollar.format((p * i)) + "      ";
				//Calculating payment
				payment = (payment -(p * i));
				payment = Double.parseDouble(dollar.format(payment));
				
				//Remaining balance
				if(p > payment){
					//Final balance subtraction.
					if(line == payments){
						payment = p;
						p -= p;
					}
					else{
						p = p - payment;
						p = Double.parseDouble(dollar.format(p));
						
					}
					//Payment towards principal
					lineInfo += payment + "      ";
					lineInfo += p + "\n";
				}
				//To print out a total of 24 payments
				if((line % 24)  == 0){
					//To end the payment process.
					if (line == payments){
						//Show final results.
						JOptionPane.showMessageDialog(null, lineInfo);
						JOptionPane.showMessageDialog(null, "Also your total interest rate is: " + dollar.format(totalInterest));
						done = true;
					}
					else{
						JOptionPane.showMessageDialog(null, lineInfo);
					}	
					//To add a sub head for the top of the payment window
					lineInfo = subHead;
				}
				
				line++;
			}
			//Empties the text fields.
			clearBoxes();
	}
	//Clears text fields.
	private void clearBoxes() {
			fieldPrincipal.setText("");
			fieldInRate.setText("");
			fieldLoanTime.setText("");
			fieldPayPerYear.setText("");
	}
}
