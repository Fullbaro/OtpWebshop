package otpwebshop;

public class Customer extends BaseClass{

	// GLOBAL VARAIABLES
	
	private String wSID, cID, name, address;
	
	// END OF GLOBAL VARIABLES
	
	public Customer(String wSID, String cID, String name, String address) {
		super(wSID, cID);
		
		this.name = name;
		this.address = address;
	}
	
}
