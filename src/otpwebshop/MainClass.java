package otpwebshop;

import java.util.Vector;
import otherclasses.Database;
import otherclasses.Tester;

public class MainClass{

	// GLOBAL VARIABLES
	
	private Vector<Payment> payments = new Vector<Payment>();
	
	// END OF GLOBAL VARIABLES
	
	public MainClass() {
		try {
			readData();
			System.out.println("done");
		}catch(Exception ex) {			
			ex.printStackTrace();
		}
	}
	
	private void readData() throws Exception {		
		Database databse = new Database();		
		Tester tester = new Tester();
		
		databse.connect("src/Assets/Database.db");		
		for(String[] data : databse.select("SELECT * FROM payments"))
			if(tester.tesPayment(data)) {
				payments.add(new Payment(data));
			}else
				System.out.println("EZ bukta.");						
	}	
	
	public static void main(String[] args) {
		new MainClass();
	}

}