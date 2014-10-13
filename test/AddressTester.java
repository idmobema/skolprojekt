import static org.junit.Assert.*;

import org.junit.Test;


public class AddressTester {

	@Test
	public void testStreetIsSaved() {
		Address addr = new Address("Vägen", "1", "11223", "Orten");
		assertEquals("Vägen", addr.getStreet());
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void testStreetIsNotEmpty(){
		Address addr = new Address("", "1", "22334", "Platsen");
	}
	
	@Test
	public void testStreetNumberIsSaved(){
		Address addr = new Address("Gatan", "2", "33445", "Platsen");
		assertEquals("2", addr.getStreetNumber());
	}
	
	@Test
	public void testStreetNumberIsIntegerApartFrom(){
		
	}

}
