package otpwebshop;

import java.text.SimpleDateFormat;
import java.util.Date;

public class Payment extends BaseClass{

	// GLOBAL VARAIABLES
	
	private String type, bankAccountNumber, cardNumber;
	private int iD, amount;
	private Date date;
		
	// END OF GLOBAL VARIABLES
	
	/**
	 * Constructor
	 * @param data - All parameters of a payment
	 * @throws Exception
	 */
	public Payment(String[] data) throws Exception{
		super(data[1], data[2]);
		
		this.iD = Integer.parseInt(data[0]);
		this.type = data[3];
		this.amount = Integer.parseInt(data[4]);
		this.bankAccountNumber = data[5].length() > 0 ? data[5] : null;
		this.cardNumber = data[6].length() > 0 ? data[6] : null;
		this.date = new SimpleDateFormat("yyyy.MM.dd").parse(data[7]);
	}	
}
