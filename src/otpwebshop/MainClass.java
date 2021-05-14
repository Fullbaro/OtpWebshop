package otpwebshop;

import java.util.Vector;
import otherclasses.Database;
import otherclasses.Tester;

public class MainClass{

	// GLOBAL VARIABLES
	
	private Vector<Payment> payments = new Vector<Payment>();
	private Vector<Customer> customers = new Vector<Customer>();
	
	// END OF GLOBAL VARIABLES
	
	/**
	 * Constructor
	 */
	public MainClass() {
		try {
			readData();			
		}catch(Exception ex) {			
			ex.printStackTrace();
		}
	}
	
	/**
	 * 
	 * @throws Exception
	 */
	private void readData() throws Exception {		
		Database databse = new Database();		
		Tester tester = new Tester();
		databse.connect("src/Assets/Database.db");
		
		// Read customers				
		for(String[] data : databse.select("SELECT * FROM customers"))
			if(tester.testCustomer(data))
				customers.add(new Customer(data));
		
		// Read payments		
		for(String[] data : databse.select("SELECT * FROM payments"))
			if(tester.testPayment(data))
				payments.add(new Payment(data));					
	}	
	
	/**
	 * Main
	 * @param args
	 */
	public static void main(String[] args) {
		new MainClass();
	}

}