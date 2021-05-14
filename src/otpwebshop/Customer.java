package otpwebshop;

public class Customer extends BaseClass{

	// GLOBAL VARAIABLES
	
	private String name, address;
	
	// END OF GLOBAL VARIABLES
	
	/**
	 * Constructor
	 * @param data - All parameters of a customer
	 */
	public Customer(String[] data) {
		super(data[0], data[1]);
		
		this.name = data[2];
		this.address = data[3];		
	}
	
}
