import static org.junit.Assert.*;

import org.junit.Test;


public class DiscountResultTests {

	@Test
	public void toStringTest() {
		DiscountResult dr = new DiscountResult("Rabatt: 2 för 25:-", new Money(0, "SEK"));
		assertTrue(dr.toString().contains("Rabatt: 2 för 25:-"));
	}
	
	@Test
	public void getDiscountAmountTest() {
		DiscountResult dr = new DiscountResult(null, new Money(2900, "SEK"));
		assertEquals(new Money(2900, "SEK"), dr.getDiscountAmount());
	}

}
