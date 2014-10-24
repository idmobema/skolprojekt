import static org.junit.Assert.*;

import org.junit.Test;

import java.math.BigDecimal;
import java.util.Currency;

// Testfallen i denna testklass härrör från vår ekvivalensklassuppdelning
public class MoreMoneyTests {

	@Test
	public void tf7() {
		Money test = new Money(5000L, "USD");
		assertEquals(new BigDecimal("50.00"), test.getAmount());
	}
	
	@Test
	public void tf8() {
		Money test = new Money(10.20, Currency.getInstance("SEK"));
		assertEquals(new BigDecimal("10.20"), test.getAmount());
	}

	@Test
	public void tf9() {
		Money test = new Money(3014.19576, Currency.getInstance("SEK"));
		assertEquals(new BigDecimal("3014.20"), test.getAmount());
	}
	
	@Test
	public void tf10() {
		Money test = new Money(0L, Currency.getInstance("SEK"));
		assertEquals(new BigDecimal("0.00"), test.getAmount());
	}

	@Test
	public void tf11() {
		Money test = new Money(Long.MAX_VALUE, Currency.getInstance("SEK"));
		assertTrue(test.toString().contains("92233720368547758.07"));
	}

	// Obs att Double.MAX_VALUE i Money konverteras till Long.MAX_VALUE
	@Test
	public void tf12() {
		Money test = new Money(Double.MAX_VALUE, Currency.getInstance("SEK"));
		assertTrue(test.toString().contains("92233720368547758.07"));
	}
	
	@Test
	public void tf13() {
		Money test = new Money(0.0, Currency.getInstance("SEK"));
		assertEquals(new BigDecimal("0.00"), test.getAmount());
	}	
}
