import static org.junit.Assert.*;

import org.junit.Test;


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
	}
	
	@Test
	public void testHighestNumberOfDigitsInAreaCode(){
		PhoneNumber nbr = new PhoneNumber("0700", "1122334");
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void testNotNumericAreaCode(){
		PhoneNumber nbr = new PhoneNumber("0r6", "1122334");
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void testNoLeadingZeroInAreaCode(){
		PhoneNumber nbr = new PhoneNumber("170", "1122334");
	}

}
