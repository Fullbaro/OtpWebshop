package otpwebshop;

public abstract class BaseClass {

	// GLOBAL VARIABLES
	
	protected String wSID, cID;
	
	// END OF GLOBAL VARIABLES
	
	public BaseClass(String wSID, String cID) {
		this.wSID = wSID;
		this.cID = cID;
	}
	
}
