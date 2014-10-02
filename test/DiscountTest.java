import static org.junit.Assert.*;

import org.junit.Test;


public class DiscountTest {

	@Test
	public void testGetPercentage() {
		Discount discount = new Discount();
		assertTrue(discount.getPercentage() == 12.5);
	}

}
