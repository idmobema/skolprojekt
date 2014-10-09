import static org.junit.Assert.*;

import org.junit.Test;
import org.junit.Before;

public class MoneyTests {
	private Money five, ten;
	private final String SEK = "SEK";
	
	@Before
	public void setUp() {
		five = new Money(5, SEK);
		ten = new Money(10, SEK);
	}
	
	@Test
	public void testIfMoneyHoldsRightAmount() {
		assertEquals(5, five.getAmount());
		assertEquals(10, ten.getAmount());
	}

	@Test
	public void testAdditionOfMoneyObjects() {
		Money fifteen = five.plus(ten);
		assertEquals(15, fifteen.getAmount());
		assertEquals(5, five.getAmount());
		assertEquals(10, ten.getAmount());
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
		Money otherFive = new Money(5, SEK);
		assertTrue(five.equals(otherFive));
		assertEquals(five.hashCode(), otherFive.hashCode());
		assertFalse(five.equals(ten));
		assertNotEquals(five.hashCode(), ten.hashCode());
		Money tenDollar = new Money(10, "USD");
		assertFalse(ten.equals(tenDollar));
	}
		
	@Test
	public void testZeroAmount() {
		Money zero = new Money(0, SEK);
		assertEquals(zero.getAmount(), 0);
	}
	
	@Test
	public void testGetCurrency() {
		assertEquals(SEK, ten.getCurrency());
		Money dollar = new Money(1, "USD");
		assertEquals("USD", dollar.getCurrency());
	}
	
	@Test
	public void testIfToStringReturnsAmountAndCurrency() {
		assertTrue(five.toString().contains("5"));
		assertTrue(five.toString().contains(SEK));
	}

	@Test(expected = IllegalArgumentException.class)
	public void testInvalidCurrencyName() {
		new Money(20, "SE");
	}
}
