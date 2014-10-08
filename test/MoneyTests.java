import static org.junit.Assert.*;

import org.junit.Test;
import org.junit.Before;

public class MoneyTests {
	private Money five, ten;
	
	@Before
	public void setUp() {
		five = new Money(5);
		ten = new Money(10);
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
		Money otherFive = new Money(5);
		assertTrue(five.equals(otherFive));
		assertEquals(five.hashCode(), otherFive.hashCode());
		assertFalse(five.equals(ten));
		assertNotEquals(five.hashCode(), ten.hashCode());
	}
}
