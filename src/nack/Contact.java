package nack;

public class Contact {
	private int id;
	private String phoneNumber;
	
	public Contact( int id, String phoneNumber ) {
		this.id = id;
		this.phoneNumber = phoneNumber;
	}
	
	public int getId() {
		return id;
	}
	
	public String getPhoneNumber() {
		return phoneNumber;
	}
}
