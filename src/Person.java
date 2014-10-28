
public class Person {
	private String firstName, lastName;
	private Address address;
	private PhoneNumber phoneNo;
	
	public Person(String firstName, String lastName, Address address, PhoneNumber phoneNo) {
		checkName(firstName);
		checkName(lastName);
		this.firstName = firstName;
		this.lastName = lastName;
		this.address = address;
		this.phoneNo = phoneNo;
	}
	
	private void checkName(String name) {
		if (name.trim().length() < 1)
			throw new IllegalArgumentException("Name is too short - must consist of at least one character.");		
		if (name.trim().length() > 40)
			throw new IllegalArgumentException("Name is too long - must consist of maximum 40 characters.");
	}
	
	public String getFirstName() {
		return firstName;
	}
	
	public String getLastName() {
		return lastName;
	}
	
	public String getName() {
		return firstName + " " + lastName;
	}
	
	public Address getAddress() {
		return address;
	}
	
	public PhoneNumber getPhoneNumber() {
		return phoneNo;
	}
	
	@Override
	public String toString() {
		return firstName + " " + lastName + "\n" + address + "\n" + phoneNo;
	}
}
