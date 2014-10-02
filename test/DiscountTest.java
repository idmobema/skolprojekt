import static org.junit.Assert.*;

import org.junit.Test;
import org.junit.Before;

public class DiscountTest {

	Discount d1, d2, d3, d4, d5;
	@Before
	public void setUpDiscountObjects(){
		d1 = new Discount(-1);
		d2 = new Discount(0);
		d3 = new Discount(12.34);
		d4 = new Discount(75);
		d5 = new Discount(76);
	}
	
	@Test
	public void testGetPercentage() {
		assertTrue("should return 12.34", d3.getPercentage() == 12.34);
	}
	
	@Test //(expected = IllegalArgumentException.class)
	public void testCheckPercentage(){
		assertFalse(d1.getPercentage() == -1);
		assertFalse(d5.getPercentage() == 76);
	}
	
	@Test
	public void testSetProcentage(){
		d3.setPercentage(20.40);
	}

}
