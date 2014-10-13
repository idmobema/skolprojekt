import static org.junit.Assert.*;

import org.junit.Test;


public class DiscountTester {

	@Test
	public void testEquals() {
		Discount d1 = new SpecialOffer("2for1", 2015, 2, 15, 2015, 2, 20);
		Discount d2 = new SpecialOffer("2for1", 2015, 2, 15, 2015, 2, 20);
		
		assertEquals(d1, d2);
	}

}
