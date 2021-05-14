package otherclasses;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Tester {
	
	// GLOBAL VARIABLES
		
	Pattern pattern;
	Matcher matcher;
	
	// GLOBAL VARIABLES
	
	public boolean tesPayment(String[] data) {
		if(isWSID(data[1]) && isCID(data[2]) && isType(data[3]) && isAmount(data[4]) && (isCardOrBankNumber(data[5]) || isCardOrBankNumber(data[6])) && isDate(data[7]))
			return true;
		else 
			return false;
	}
	
	// TESTS
	
	private boolean isWSID(String wSID) {
		pattern = Pattern.compile("^[WS]{2}\\d+$");
		matcher = pattern.matcher(wSID);
		if(matcher.matches())
			return true;
		else return false;
	}
	
	private boolean isCID(String cID) {
		pattern = Pattern.compile("^[A]{1}\\d+$");
		matcher = pattern.matcher(cID);
		if(matcher.matches())
			return true;
		else return false;
	}
	
	// PAYMENT TESTS
	
	private boolean isType(String type) {
		if(type.equals("card") || type.equals("transfer"))
			return true;
		else return false;
	}
	
	private boolean isAmount(String amount) {
		pattern = Pattern.compile("^[1-9][0-9]*$");
		matcher = pattern.matcher(amount);
		if(matcher.matches())
			return true;
		else return false;
	}
	
	private boolean isCardOrBankNumber(String cardNumber) {
		pattern = Pattern.compile("^[1-9]+,[0-9]+E\\+15$");
		matcher = pattern.matcher(cardNumber);		
		if(matcher.matches())
			return true;
		else return false;
	}
	
	private boolean isDate(String date) {
		try {
			Date d = new SimpleDateFormat("yyyy.MM.dd").parse(date);
			return true;
		}catch(Exception ex) {
			return false;
		}
	}
	
	// CUSTOMER TESTS
	
	private boolean isName(String name) {
		return true;
	}
	
	private boolean isAddress(String address) {
		return true;
	}	
}
