import static org.junit.Assert.*;

import org.junit.Test;


//Mellanslag i postnummer
//Postnummer f�r ej b�rja p� 0
//Postnummer 3+2 siffror
//Allt med Postort samt koppling mellan postnummer och postort

public class AddressTester {

	@Test
	public void testStreetIsSaved() {
		Address addr = new Address("V�gen", "1", "11223", "Orten");
		assertEquals("V�gen", addr.getStreet());
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
	
	@Test(expected = NumberFormatException.class)
	public void testNoNumberInStreetNumber(){
		Address addr = new Address("Stigen", "ett", "44556", "Staden");
	}
	
	@Test
	public void testStreetNumberWithLetter(){
		Address addr = new Address("All�n", "1a", "55667", "Byn");
		assertEquals("1a", addr.getStreetNumber());
	}
	
	@Test
	public void testStreetNumberWithSpaceBetweenNumberAndLetter(){
		Address addr = new Address("Kroken", "11 b", "66778", "Samh�llet");
		assertEquals("11 b", addr.getStreetNumber());
	}
	
	@Test (expected = IllegalArgumentException.class)
	public void testStreetNumberIsZero(){
		Address addr = new Address("Sv�ngen", "0", "88990", "Storstaden");
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void testNegativeStreetNumber(){
		Address addr = new Address("Min v�g", "-4", "99001", "Sm�staden");
	}
	
	
	
	
	
	@Test
	public void testPostalCodeIsSaved(){
		Address addr = new Address("�n", "3", "77889", "St�llet");
		assertEquals("77889", addr.getPostalCode());
	}
	
	@Test (expected = NumberFormatException.class)
	public void testPostalCodeNotANumber(){
		Address addr = new Address("�n", "4", "123c4", "Hemorten");
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void testPostalCodeStartingWithZero(){
		Address addr = new Address("Slingan", "45", "02344", "Stockholm");
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void testTooShortPostalCode(){
		Address addr = new Address("Din v�g", "12", "1122", "Kista");
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void testTooLongPostalCode(){
		Address addr = new Address("M�rtgatan", "15", "112233", "Fisks�tra");
	}
	
	
	
	
	
	

}
