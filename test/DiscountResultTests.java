import static org.junit.Assert.*;

import org.junit.Test;


public class DiscountResultTests {

	@Test
	public void getDiscountDescriptionTest() {
		DiscountResult dr = new DiscountResult("Rabatt: 2 för 25:-", new Money(0, "SEK"));
		assertEquals("Rabatt: 2 för 25:-", dr.getDiscountDescription());
	}
	
	@Test
	public void getDiscountAmountTest() {
		DiscountResult dr = new DiscountResult(null, new Money(2900, "SEK"));
		assertEquals(new Money(2900, "SEK"), dr.getDiscountAmount());
	}

}
