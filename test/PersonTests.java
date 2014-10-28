import static org.junit.Assert.*;

import org.junit.Test;
import org.junit.Before;


public class PersonTests {
	private Person p;
	private String firstName = "Mats";
	private String lastName = "Rörbecker";
	private Address address = new Address("Tellusborgsvägen", "1", "126 32", "Hägersten");
	private PhoneNumber phoneNo = new PhoneNumber("070", "2072867");
	
	@Before
	public void setUp() {
		p = new Person(firstName, lastName, address, phoneNo);
	}
	
	@Test
	public void testIfPersonHasFirstName() {
		assertEquals(firstName, p.getFirstName());
	}

	@Test
	public void testIfPersonHasLastName() {
		assertEquals(lastName, p.getLastName());
	}
	
	@Test
	public void testIfPersonHasFullName() {
		assertEquals(firstName + " " + lastName, p.getName());
	}
	
	@Test(expected = IllegalArgumentException.class) 
	public void testTooShortFirstName() {
		new Person("", lastName, address, phoneNo);
	}

	@Test(expected = IllegalArgumentException.class) 
	public void testTooShortLastName() {
		new Person(firstName, "", address, phoneNo);
	}
	
	@Test(expected = IllegalArgumentException.class) 
	public void testTooLongName() {
		new Person("Mats Rörbecker Tellusborgsvägen 1 126 32 Hägersten", lastName, address, phoneNo);
	}
	
	@Test
	public void testIfToStringReturnsNames() {
		assertTrue(p.toString().contains(firstName));
		assertTrue(p.toString().contains(lastName));
	}
	
	@Test
	public void testIfFirstCustomerIsNumberOne() {
		Customer c = new Customer(firstName, lastName, address, phoneNo);
		assertEquals(1, c.getCustomerNo());
	}

	@Test
	public void testIfCustomerNumberIncreasesByOne() {
		Customer c1 = new Customer(firstName, lastName, address, phoneNo);
		Customer c2 = new Customer(firstName, lastName, address, phoneNo);
		assertTrue(c2.getCustomerNo() - c1.getCustomerNo() == 1);
	}

	@Test
	public void testIfPersonHasAddress() {
		assertEquals(address, p.getAddress());
	}
	
	@Test
	public void testIfToStringReturnsAddress() {
		assertTrue(p.toString().contains(address.getStreet()));
	}
	
	@Test
	public void testIfPersonHasPhoneNumber() {
		assertEquals(phoneNo, p.getPhoneNumber());
	}
	
	@Test
	public void testIfToStringReturnsPhoneNumber() {
		assertTrue(p.toString().contains(phoneNo.getAreaCode()));
	}
	
	@Test
	public void testCashier() {
		Cashier c = new Cashier(firstName, lastName, address, phoneNo);
		assertTrue(c.toString().contains(firstName + " " + lastName));
		assertTrue(c.toString().contains(address.toString()));
		assertTrue(c.toString().contains(phoneNo.toString()));
	}
}
