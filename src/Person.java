
public class Person {
	private String firstName, lastName;
	
	public Person(String firstName, String lastName, String address, String phoneNo) {
		checkName(firstName);
		checkName(lastName);
		this.firstName = firstName;
		this.lastName = lastName;
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
	
	@Override
	public String toString() {
		return firstName + " " + lastName;
	}
}
