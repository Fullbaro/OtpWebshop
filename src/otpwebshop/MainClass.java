package otpwebshop;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.util.Date;
import java.util.Vector;
import otherclasses.Database;
import otherclasses.Formatter;
import otherclasses.Tester;

public class MainClass implements Formatter{

	// GLOBAL VARIABLES
	
	private Database database;
	
	private Vector<Customer> customers = new Vector<Customer>();
	private Vector<Payment> payments = new Vector<Payment>();	
	
	// END OF GLOBAL VARIABLES
	
	/**
	 * Constructor
	 */
	public MainClass() {
		try {
			database = new Database();
			readData();		
			System.out.println("Read successful.");
			generateReport();
			System.out.print("Report generated.");
		}catch(Exception ex) {			
			ex.printStackTrace();
		}
	}
	
	/**
	 * Reads all the data from the database. (Clears if needed)
	 * @throws Exception
	 */
	private void readData() throws Exception {				 	
		Tester tester = new Tester();
		database.connect("src/Assets/Database.db");
		
		// Read customers				
		for(String[] data : database.select("SELECT * FROM customers"))
			if(tester.testCustomer(data))
				customers.add(new Customer(data));
			else
				// Delete from database
				database.exec("DELETE FROM customers WHERE WSID LIKE "+data[1]+" AND CID LIKE "+data[2]);			
		
		// Read payments		
		for(String[] data : database.select("SELECT * FROM payments"))
			if(tester.testPayment(data))
				payments.add(new Payment(data));
			else
				// Delete from database
				database.exec("DELETE FROM payments WHERE ID LIKE "+data[0]);
		database.disconnect();		
	}
	
	/**
	 * Collect data
	 */
	private void generateReport() throws Exception{
		database.connect("src/Assets/Database.db");
		
		// Report one
		saveReport(database.select("SELECT Name, Address, sum(Amount) as Spent FROM customers\r\n"
				+ "INNER JOIN payments on payments.WSID = customers.WSID AND payments.CID = customers.CID\r\n"
				+ "GROUP BY customers.WSID, customers.CID"),
				"src/Assets/report01.csv");
		
		// Report two
		Vector<String[]> temp = new Vector<String[]>();
		for(String[] s : database.select("SELECT distinct WSID from payments"))			
			temp.add(database.select("select distinct WSID, (select sum(amount) from payments where type like 'card' and WSID like '"+s[0]+"') as card, ifnull((select sum(amount) from payments where type like 'transfer' and WSID like '"+s[0]+"'), 0) as transfer from payments where WSID like '"+s[0]+"'").get(0));		
		saveReport(temp, "src/Assets/report02.csv");
		
		database.disconnect();
	}
	
	/**
	 * Save data to report file
	 */
	private void saveReport(Vector<String[]> data, String file) throws Exception{	
		FileWriter fw = new FileWriter(file, false);
        BufferedWriter bw = new BufferedWriter(fw);
        for(String [] row : data) {
        	bw.write(arrayFormat(row));
        	bw.newLine();
        }
        bw.close();
	}
	
	/**
	 * ToString for an array
	 * @return - String
	 */
	@Override
	public String arrayFormat(String[] array) {
		String re = "";
		for(String s : array)
			if(s.equals(array[0]))
				re+=s;
			else re += ";"+s;
		return re;
	}
	
	/**
	 * Main
	 * @param args
	 */
	public static void main(String[] args) {
		new MainClass();
	}
}