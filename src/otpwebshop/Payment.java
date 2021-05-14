package otpwebshop;

public class Payment extends BaseClass{

	// GLOBAL VARAIABLES
	
	private String wSID, cID, type, date;
	private int iD, amount, bankAccountNumber, cardNumber;
		
	// END OF GLOBAL VARIABLES
	
	public Payment(int iD, String wSID, String cID, String type, int amount, int bankAccountNumber, int cardNumber, String date) {
		super(wSID, cID);
		
		this.iD = iD;
		this.type = type;
		this.amount = amount;
		this.bankAccountNumber = bankAccountNumber;
		this.cardNumber = cardNumber;
		this.date = date;
	}
	
}
