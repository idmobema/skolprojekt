import static org.junit.Assert.*;

import org.junit.Test;
import org.junit.Before;
import org.junit.After;

public class DiscountTest {

	Discount d1, d2, d3, d4, d5;
	@Before
	public void setUpDiscountObjects(){
		//d1 = new Discount(-1, new TimeValidity(2013, 8, 5, 2013, 8, 10));
		//d2 = new Discount(0, new TimeValidity(2013, 8, 5, 2013, 8, 10));
		d3 = new Discount(12.34, new TimeValidity(2013, 8, 5, 2013, 8, 10));
		d4 = new Discount(75, new TimeValidity(2013, 8, 5, 2013, 8, 10));
		//d5 = new Discount(76, new TimeValidity(2013, 8, 5, 2013, 8, 10));
	}
	
	@After
	public void tearDown(){
		d1 = null;
		d2 = null;
		d3 = null;
		d4 = null;
		d5 = null;
	}
	
	@Test
	public void testGetPercentage() {
		assertTrue("should return 12.34", d3.getPercentage() == 12.34);
	}
	
	@Test (expected = IllegalArgumentException.class)
	public void testCheckPercentage(){
		d1 = new Discount(-1, new TimeValidity(2013, 8, 5, 2013, 8, 10));
		
		assertNull("this obj should not have been created", d1);
	}
	
	@Test
	public void testSetPercentage(){
		double expected = 20.40;
		d3.setPercentage(20.40);
		
		assertTrue(expected == d3.getPercentage());
	}
	
	@Test (expected = IllegalArgumentException.class)
	public void testSetPercentageWithIllegalValues(){
		d3.setPercentage(0);
		d4.setPercentage(75.01);
		d3.setPercentage(.99);
	}
	
//	@Test
//	public void testGetTimeValidity(){
//		TimeValidity expected = new TimeValidity(2013, 8, 5, 2013, 8, 10);
//		TimeValidity result = d3.getTimeValidity();
//		
//		assertTrue("should return the dates the timevalidity is in effect", expected.equals(result));
//	}

}
