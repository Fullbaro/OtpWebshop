package otherclasses;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Tester implements Formatter{
	
	// GLOBAL VARIABLES
		
	Pattern pattern;
	Matcher matcher;
	
	// GLOBAL VARIABLES
	
	/**
	 * Checks if there are errors in a customer's data
	 * @param data - All parameters of a customer
	 * @return - boolean
	 */
	public boolean testCustomer(String[] data) throws Exception {
		String msg = "";
		msg += isWSID(data[0]);
		msg += isCID(data[1]);
		msg += isName(data[2]);
		msg += isAddress(data[3]);
		
		if(msg.length() > 0) {			
			log("A hibás rekord(ok): "+msg+" Ebben a sorban: "+arrayFormat(data));		
			return false;
		}else return true;
	}
	
	/**
	 * Checks if there are errors in a payment's data
	 * @param data - All parameters of a payment
	 * @return - boolean
	 */
	public boolean testPayment(String[] data) throws Exception {
		String msg = "";
		msg+= isWSID(data[1]);
		msg+= isCID(data[2]);
		msg+= isType(data[3]);
		msg+= isAmount(data[4]);	
		String bank = isCardOrBankNumber(data[5]);
		String card = isCardOrBankNumber(data[6]);
		if(card.length() > 0 || bank.length() > 0)
			msg += bank.length() > 0 ? "BankAccountNumber: "+bank+" " : "CardNumber: "+card+" ";		
		msg+= isDate(data[7]);
		
		if(msg.length() > 0) {			
			log("A hibás rekord(ok): "+msg+" Ebben a sorban: "+arrayFormat(data));			
			return false;
		}else return true;
	}
	
	// TESTS
	
	private String isWSID(String wSID) {
		pattern = Pattern.compile("^[WS]{2}\\d+$");
		matcher = pattern.matcher(wSID);
		if(matcher.matches()) return "";
		else return "WSID: "+wSID+" ";
	}
	
	private String isCID(String cID) {
		pattern = Pattern.compile("^[A]{1}\\d+$");
		matcher = pattern.matcher(cID);
		if(matcher.matches()) return "";
		else return "CID: "+cID+" ";
	}
	
	// CUSTOMER TESTS
	
	private String isName(String name) {
		pattern = Pattern.compile("^[AÁEÉIÍOÓÖÕUÚÜÛA-z][aáeéiíoóöõuúüûa-z]+ [AÁEÉIÍOÓÖÕUÚÜÛA-z][aáeéiíoóöõuúüûa-z]+$");
		matcher = pattern.matcher(name);		
		if(matcher.matches()) return "";
		else return "Name: "+name+" ";
	}
		
	private String isAddress(String address) {
		pattern = Pattern.compile("^[A-z]+.?[A-z ]+[1-9][0-9]{3} .+ [0-9]+$");
		matcher = pattern.matcher(address);		
		if(matcher.matches()) return "";
		else return "Address: "+address+" ";
	}
	
	// PAYMENT TESTS
	
	private String isType(String type) {
		if(type.equals("card") || type.equals("transfer")) return "";
		else return "Type: "+type+" ";
	}
	
	private String isAmount(String amount) {
		pattern = Pattern.compile("^[1-9][0-9]*$");
		matcher = pattern.matcher(amount);
		if(matcher.matches()) return "";
		else return "Amount: "+amount+" ";
	}
	
	private String isCardOrBankNumber(String number) {
		pattern = Pattern.compile("^[1-9]+,?[0-9]{0,15}+E\\+15$");
		matcher = pattern.matcher(number);		
		if(matcher.matches()) return "";
		else return number;
	}
	
	private String isDate(String date) {
		try {
			Date d = new SimpleDateFormat("yyyy.MM.dd").parse(date);
			return "";
		}catch(Exception ex) {
			return "Date: "+date+" ";
		}
	}
	
	// OTHER METHODS
	
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
			else re += ", "+s;
		return re;
	}
	
	/**
	 * Console and file log.
	 */
	private void log(String msg) throws Exception{
		FileWriter fw = new FileWriter("src/Assets/application.log", true);
        BufferedWriter bw = new BufferedWriter(fw); 
        msg = new Date()+": "+msg;        
        bw.write(msg);            
        bw.newLine();
        bw.close();
		System.out.println(msg);
	}
}
