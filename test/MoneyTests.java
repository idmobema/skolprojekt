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
		Money fifteen = five.add(ten);
		assertEquals(15, fifteen.getAmount());
		assertEquals(5, five.getAmount());
		assertEquals(10, ten.getAmount());
	}
}
