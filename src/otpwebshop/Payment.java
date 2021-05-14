package otpwebshop;

public class Payment extends BaseClass{

	// GLOBAL VARAIABLES
	
	private String type, date;
	private int iD, amount, bankAccountNumber, cardNumber;
		
	// END OF GLOBAL VARIABLES
	
	public Payment(String[] data) throws Exception{
		super(data[1], data[2]);
		
		this.iD = Integer.parseInt(data[3]);
		this.type = data[4];
		this.amount = Integer.parseInt(data[5]);
		this.bankAccountNumber = Integer.parseInt(data[6]);
		this.cardNumber = Integer.parseInt(data[7]);
		this.date = data[8];
	}
	
}
