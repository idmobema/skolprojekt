import static org.junit.Assert.*;

import org.junit.Test;


//de riktnummer som inte finns?
//mobilnummer alltid sju siffror i number?
//Får number börja med 0?

public class PhoneNumberTest {

	@Test
	public void testThatAreaCodeIsSaved() {
		PhoneNumber nbr = new PhoneNumber("070", "1122334");
		assertEquals("070", nbr.getAreaCode());
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void testTooShortAreaCode(){
		PhoneNumber nbr = new PhoneNumber("0", "1122334");
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void testTooLongAreaCode(){
		PhoneNumber nbr = new PhoneNumber("07000", "1122334");
	}
	
	@Test
	public void testLowestNumberOfDigitsInAreaCode(){
		PhoneNumber nbr = new PhoneNumber("07", "1122334");
		assertEquals("07", nbr.getAreaCode());
	}
	
	@Test
	public void testHighestNumberOfDigitsInAreaCode(){
		PhoneNumber nbr = new PhoneNumber("0700", "1122334");
		assertEquals("0700", nbr.getAreaCode());
	}
	
	@Test(expected = NumberFormatException.class)
	public void testNotNumericAreaCode(){
		PhoneNumber nbr = new PhoneNumber("0r6", "1122334");
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void testNoLeadingZeroInAreaCode(){
		PhoneNumber nbr = new PhoneNumber("170", "1122334");
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void testTwoLeadingZerosInAreaCode(){
		PhoneNumber nbr = new PhoneNumber("008", "123456");
	}
	
	@Test
	public void testThatNbrIsSaved(){
		PhoneNumber nbr = new PhoneNumber("08", "9988776");
		assertEquals("9988776", nbr.getNbr());
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void testTooShortNbr(){
		PhoneNumber nbr = new PhoneNumber("08", "1122");
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void testTooLongNbr(){
		PhoneNumber nbr = new PhoneNumber("08", "112233445");
	}
	
	@Test
	public void testLowestNumberOfDigitsInNbr(){
		PhoneNumber nbr = new PhoneNumber("08", "11223");
		assertEquals("11223", nbr.getNbr());
	}
	
	@Test
	public void testHighestNumberOfDigits(){
		PhoneNumber nbr = new PhoneNumber("08", "11223344");
		assertEquals("11223344", nbr.getNbr());
	}
	
	@Test(expected = NumberFormatException.class)
	public void testNotNumericalNbr(){
		PhoneNumber nbr = new PhoneNumber("08", "1a2233");
	}
	
	@Test(expected = NumberFormatException.class)
	public void testNegativeNbr(){
		PhoneNumber nbr = new PhoneNumber("0911", "-12344");
	}
	
	@Test
	public void testFormattingInToString(){
		PhoneNumber nbr = new PhoneNumber("08", "1122334");
		assertEquals("08-1122334", nbr.toString());
	}

}
