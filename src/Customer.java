
public class Customer extends Person {
	// Håller koll på antalet kunder, används för att ta fram kundnummer
	private static int numberOfCustomers = 0;
	
	private int customerNo;
	
	public Customer(String firstName, String lastName, Address adress, PhoneNumber phoneNo) {
		super(firstName, lastName, adress, phoneNo);
		numberOfCustomers++;
		customerNo = numberOfCustomers;
	}

	public int getCustomerNo() {
		return customerNo;
	}
}
