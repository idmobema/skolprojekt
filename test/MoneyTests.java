import static org.junit.Assert.*;

import org.junit.Test;
import org.junit.Before;

import java.math.BigDecimal;

public class MoneyTests {
	private Money five, ten;
	private final String SEK = "SEK";
	
	@Before
	public void setUp() {
		five = new Money(500, SEK);
		ten = new Money(1000, SEK);
	}
	
	@Test
	public void testIfMoneyHoldsRightAmount() {
		assertEquals(new BigDecimal("5.00"), five.getAmount());
		assertEquals(new BigDecimal("10.00"), ten.getAmount());
	}

	@Test
	public void testAdditionOfMoneyObjects() {
		Money fifteen = five.plus(ten);
		assertEquals(new BigDecimal("15.00"), fifteen.getAmount());
		assertEquals(new BigDecimal("5.00"), five.getAmount());
		assertEquals(new BigDecimal("10.00"), ten.getAmount());
	}
	
	@Test 
	public void testSubtractionOfMoneyObjects() {
		Money otherFive = ten.minus(five);
		assertEquals(otherFive.getAmount(), five.getAmount());
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void testSubtractionWithInvalidSubtrahend() {
		five.minus(ten);
	}
	
	@Test
	public void testEquality() {
		Money otherFive = new Money(500, SEK);
		assertTrue(five.equals(otherFive));
		assertEquals(five.hashCode(), otherFive.hashCode());
		assertFalse(five.equals(ten));
		assertNotEquals(five.hashCode(), ten.hashCode());
		Money tenDollar = new Money(1000, "USD");
		assertFalse(ten.equals(tenDollar));
	}
		
	@Test
	public void testZeroAmount() {
		Money zero = new Money(0, SEK);
		assertEquals(zero.getAmount(), new BigDecimal("0.00"));
	}
	
	@Test
	public void testGetCurrency() {
		assertEquals(SEK, ten.getCurrency().getCurrencyCode());
		Money dollar = new Money(1, "USD");
		assertEquals("USD", dollar.getCurrency().getCurrencyCode());
	}
	
	@Test
	public void testIfToStringReturnsAmountAndCurrency() {
		assertTrue(five.toString().contains("5"));
		assertTrue(five.toString().contains(SEK));
	}

	@Test(expected = IllegalArgumentException.class)
	public void testInvalidCurrencyName() {
		new Money(200, "SE");
	}
}
