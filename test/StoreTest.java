
import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class StoreTest {
	
	private Address addr1, addr2;
	private PhoneNumber nbr1, nbr2;
	
	@Before
	public void setUp(){
		addr1 = new Address("Vägen", "2", "122 23", "Staden");
		addr2 = new Address("Stigen", "3", "33212", "Platsen");
		nbr1 = new PhoneNumber("070", "1231231");
		nbr2 = new PhoneNumber("08", "12312323");
	}

	@Test
	public void testStoreNameIsSaved() {
		Store theStore = new Store("Butik1", addr1, nbr1);
		assertEquals("Butik1", theStore.getName());
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void testNullName(){
		Store store = new Store(null, addr1, nbr1);
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void testEmptyName(){
		Store store = new Store("", addr1, nbr1);
	}

	@Test
	public void testAddressIsSaved() {
		Address addr = new Address("Vägen", "2", "22343", "Platsen");
		Store theStore = new Store("Butik2", addr, nbr1);
		assertEquals(addr, theStore.getAddress());
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void testNullAddress(){
		Store store = new Store ("Affären0", null, nbr1);
	}
	
	@Test
	public void testSetAddress(){
		Store store = new Store("Butik", addr1, nbr1);
		store.setAddress(addr2);
		assertEquals(addr2, store.getAddress());
	}

	@Test
	public void testPhoneNumberIsSaved() {
		PhoneNumber nbr = new PhoneNumber("090", "2345623");
		Store store = new Store("Butik3", addr1, nbr);
		assertEquals(nbr, store.getPhoneNumbers().get(0));
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void testNullPhoneNumber(){
		Store store = new Store ("Affären0", addr1, null);
	}

	@Test
	public void testSecondPhoneNumberAdded() {
		Store store = new Store("Butik4", addr1, nbr1);
		store.addPhoneNumber(nbr2);
		assertEquals(nbr2, store.getPhoneNumbers().get(1));
	}
	
	@Test
	public void testAddedPhoneNumberAlreadyExists(){
		Store store = new Store("Affären2", addr1, nbr1);
		store.addPhoneNumber(nbr1);
		assertEquals(1, store.getPhoneNumbers().size());
		
	}
	
	@Test
	public void testRemovePhoneNumber(){
		Store store = new Store("Affär", addr1, nbr1);
		store.addPhoneNumber(nbr2);
		PhoneNumber nbr = store.removePhoneNumber(0);
		assertEquals(nbr1, nbr);
		assertEquals(1, store.getPhoneNumbers().size());
	}

	@Test
	public void testFormatInToStringWithOnePhoneNumber() {
		Store store = new Store("Butik5", addr1, nbr1);
		assertEquals("Butik5\n\nAdress:\n" + addr1 + "\n\nTelefon:\n" + nbr1,
				store.toString());
	}
	
	@Test
	public void testFormatInToStringWithTwoNumbers(){
		Store store = new Store("Butik6", addr1, nbr1);
		store.addPhoneNumber(nbr2);
		assertEquals("Butik6\n\nAdress:\n" + addr1 + "\n\nTelefon:\n" + nbr1 + "\n" + nbr2, store.toString());
	}

}
