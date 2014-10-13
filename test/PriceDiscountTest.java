/** 
 * IMPORTANT!!!
 * Most, if not all, of the tests are time related (/ bounded) to the current system's time.
 * Tests are constrained to FAIL if the time validity is SET / CREATED for a date that PRECEDES the current date.
 * Read more about the rules on assigning a time validity to a Discount in TimeValidity.java and 
 * TimeValidityDatesChecker.java 
 * 
 * #BC141004# */

import static org.junit.Assert.*;

import org.junit.Test;
import org.junit.Before;
import org.junit.After;

public class PriceDiscountTest {

	Discount d1, d3;
	TimeValidity tV;
	@Before
	public void setUp(){
		tV = new TimeValidity(2015, 2, 15, 2015, 2, 20);
	}

	
	@After
	public void tearDown(){
		d1 = null;
		d3 = null;
	}
	
	@Test
	public void testGetPercentage() {
		d3 = new PriceDiscount(12.34, 2015, 2, 15, 2015, 2, 20);
		assertTrue("should return 12.34", ((PriceDiscount) d3).getPercentage() == 12.34);
	}
	
	@Test (expected = IllegalArgumentException.class)
	public void testCheckPercentage(){
		d1 = new PriceDiscount(-1, 2013, 8, 5, 2013, 8, 10);
		
		assertNull("this obj should not have been created", d1);
	}
	
	@Test
	public void testSetPercentage(){
		double expected = 20.40;
		d3 = new PriceDiscount(20.40, 2015, 2, 15, 2015, 2, 20);
		((PriceDiscount) d3).setPercentage(20.40);
		
		assertTrue(expected == ((PriceDiscount) d3).getPercentage());
	}
	
	@Test (expected = IllegalArgumentException.class)
	public void testSetPercentageWithIllegalValues(){
		d3 = new PriceDiscount(1, 2015, 2, 15, 2015, 2, 20);

		((PriceDiscount) d3).setPercentage(.99);
	}
	
	@Test
	public void testGetTimeValidity(){
		d3 = new PriceDiscount(1, 2015, 2, 15, 2015, 2, 20);
		TimeValidity expected = tV;
		TimeValidity actual = d3.getTimeValidity();
		
		assertTrue(expected.compareTo(actual) == 0);
		
	}
	
	@Test
	public void testToString(){
		d3 = new PriceDiscount(1, 2015, 2, 15, 2015, 2, 20);
		String expected = d3.getOfferName() + "\nValid through:\n" + tV + "\nPercentage: 1.0";
		String actual = d3.toString();
		
		assertEquals(expected, actual);
	}
	
	@Test
	public void testSetTimeValidity(){
		d3 = new PriceDiscount(1, 2015, 2, 15, 2015, 2, 20);
		d3.setTimeValidity(2015, 2, 15, 2015, 2, 20);
		TimeValidity expected = new TimeValidity(2015, 2, 15, 2015, 2, 20);
		TimeValidity actual = d3.getTimeValidity();
		
		assertEquals(expected, actual);
		
	}
	
	@Test
	public void testEquals(){
		d3 = new PriceDiscount(15, 2015, 2, 15, 2015, 2, 20);
		d1 = new PriceDiscount(15, 2015, 2, 15, 2015, 2, 20);
		
		assertEquals(d3, d1);
	}

}
