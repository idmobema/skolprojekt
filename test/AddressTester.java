import static org.junit.Assert.*;

import org.junit.Test;


public class AddressTester {

	@Test
	public void testStreetIsSaved() {
		Address addr = new Address("V�gen", "1", "11223", "Orten");
		assertEquals("V�gen", addr.getStreet());
	}
	
	@Test(expected = NullPointerException.class)
	public void testNullStreet(){
		Address addr = new Address(null, "2", "22345", "Hemma");
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
	
	@Test(expected = NullPointerException.class)
	public void testNullStreetNumber(){
		Address addr = new Address("V�g", null, "22345", "Hemma");
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
	
	@Test(expected = IllegalArgumentException.class)
	public void testStreetNumberWithSymbolAtEnd(){
		Address addr = new Address("Vitalisgatan", "33?", "44143", "Alings�s");
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
		assertEquals("778 89", addr.getPostalCode());
	}
	
	@Test(expected = NullPointerException.class)
	public void testNullPostalCode(){
		Address addr = new Address("V�gen", "2", null, "Hemma");
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
	
	@Test
	public void testPostalCodeWithSpace(){
		Address addr = new Address("Flintv�gen", "7", "441 41", "Alings�s");
		assertEquals("441 41", addr.getPostalCode());
	}
	

	
	
	
	@Test
	public void testThatPostTownIsSaved(){
		Address addr = new Address("Munins v�g", "10", "221 33", "V�ster�s");
		assertEquals("V�ster�s", addr.getPostTown());
	}
	
	@Test(expected = NullPointerException.class)
	public void testNullPostTown(){
		Address addr = new Address("V�gen", "2", "22345", null);
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void testEmptyPostTown(){
		Address addr = new Address("Tallstigen", "23", "923 44", "");
	}
	
	
	
	
	@Test
	public void testFormatInToString(){
		Address addr = new Address("Bl�v�gen", "123", "94432", "Storuman");
		assertEquals("Bl�v�gen 123\n944 32 Storuman", addr.toString());
	}
	
	

}
