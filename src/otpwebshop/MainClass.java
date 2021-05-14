package otpwebshop;

import java.util.Vector;
import otherclasses.Database;
import otherclasses.Tester;

public class MainClass{

	// GLOBAL VARIABLES
	
	
	
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
		for(String[] data : databse.select("SELECT * FROM payments")) {
			if(tester.tesPayment(data)) {
				
			}
		}					
	}	
	
	public static void main(String[] args) {
		new MainClass();
	}

}