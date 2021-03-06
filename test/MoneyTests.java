import static org.junit.Assert.*;

import org.junit.Test;
import org.junit.Before;

import java.math.BigDecimal;
import java.util.Currency;

public class MoneyTests {
	private Money five, ten, zero, dollar;
	private final String SEK = "SEK";
	
	@Before
	public void setUp() {
		five = new Money(500, SEK);
		ten = new Money(1000, SEK);
		zero = new Money(0, SEK);
		dollar = new Money(1, "USD");
	}
	
	@Test
	public void testIfMoneyHoldsRightAmount() {
		assertEquals(new BigDecimal("5.00"), five.getAmount());
		assertEquals(new BigDecimal("10.00"), ten.getAmount());
	}
	
	@Test
	public void testConstructorThatTakesDouble() {
		Money fifteen = new Money(15.0, SEK);
		assertEquals(new BigDecimal("15.00"), fifteen.getAmount());
	}

	@Test
	public void testConstructorThatTakesCurrency() {
		Money twenty = new Money(20.0, Currency.getInstance("SEK"));
		assertEquals(new BigDecimal("20.00"), twenty.getAmount());
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
		assertEquals(zero.getAmount(), new BigDecimal("0.00"));
	}
	
	@Test
	public void testGetCurrency() {
		assertEquals(SEK, ten.getCurrency().getCurrencyCode());
		assertEquals("USD", dollar.getCurrency().getCurrencyCode());
	}
	
	@Test
	public void testIfToStringReturnsAmountAndCurrency() {
		assertTrue(five.toString().contains("5"));
		assertTrue(five.toString().contains(SEK));
	}

	@Test(expected = IllegalArgumentException.class)
	public void testInvalidCurrencyCode() {
		new Money(200, "SE");
	}
	
	@Test(expected = NullPointerException.class)
	public void testCurrencyCodeSetToNull() {
		String currencyCode = null;
		new Money(200, currencyCode);
	}

	@Test
	public void testMultiplicationOfMoneyObjects() {
		Money fifteen = new Money(15.0, SEK);
		assertEquals(fifteen, five.times(3));
		assertEquals(zero, five.times(0));
	}

	@Test
	public void testMultiplicationWithDouble() {
		Money result = ten.times(0.7894);
		assertEquals(new BigDecimal("7.89"), result.getAmount());
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void testInvalidMultiplier() {
		five.times(-1);
	}
	
	@Test
	public void testRoundingOfDoubleAmount() {
		Money testMoney = new Money(10.815, SEK);
		assertEquals(new BigDecimal("10.82"), testMoney.getAmount());
		testMoney = new Money(10.8149, SEK);
		assertEquals(new BigDecimal("10.81"), testMoney.getAmount());
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void testAdditionWithDifferentCurrencies() {
		five.plus(dollar);
	}

	@Test(expected = IllegalArgumentException.class)
	public void testSubtractionWithDifferentCurrencies() {
		five.minus(dollar);
	}
	
	@Test
	public void testHelperConstructorForCrowns() {
		Money crown = Money.getCrowns(1.0);
		assertEquals(SEK, crown.getCurrency().getCurrencyCode());
	}
	
	@Test
	public void testComparison() {
		assertTrue(ten.compareTo(five) > 0);
		assertTrue(five.compareTo(ten) < 0);
		Money otherFive = new Money(5.0, SEK);
		assertTrue(five.compareTo(otherFive) == 0);
	}

	@Test(expected = IllegalArgumentException.class)
	public void testComparisonWithDifferentCurrencies() {
		five.compareTo(dollar);
	}
	
	@Test
	public void testRoundingAfterMultiplication() {
		Money result = ten.times(1.4956);
		assertEquals(new BigDecimal("14.96"), result.getAmount());
		result = ten.times(1.494492);
		assertEquals(new BigDecimal("14.94"), result.getAmount());
	}

	@Test(expected = IllegalArgumentException.class)
	public void testConstructorThatTakesLongAndString() {
		new Money(-1L, "SEK");
	}

	@Test
	public void testConstructorThatTakesLongAndCurrency() {
		Money testMoney = new Money(100, Currency.getInstance("SEK"));
		assertEquals(new BigDecimal("1.00"), testMoney.getAmount());
	}
	
	@Test
	public void testEqualityWithOtherClassAndNull() {
		assertFalse(ten.equals(new Object()));
		assertFalse(ten.equals(null));
	}
	
	// TestCase id T1
	@Test(expected = IllegalArgumentException.class)
	public void testNegativeLongAmountParam(){
		Money money = new Money(-2317L, "SEK");
		assertNull(money);
	}
	
	// TestCase id T2
	@Test(expected = IllegalArgumentException.class)
	public void testNegativeDoubleAmountParam(){
		new Money(-8.34, "SEK");
	}
	
	// TestCase id T3
	@Test(expected = NullPointerException.class)
	public void testNullCurrencyInConstructor(){
		Currency currency = null;
		new Money(10.20, currency);
	}
	
	// TestCase id T4
	@Test(expected = NullPointerException.class)
	public void testNullStringInConstructor(){
		String currency = null;
		new Money(10.20, currency);
	}
	
	// TestCase id T5
	@Test(expected = IllegalArgumentException.class)
	public void testNonExistentCurrencyCodeInConstructor(){
		new Money(10.20, "UÄK");
	}
	
	// TestCase id T6
	@Test(expected = IllegalArgumentException.class)
	public void testEmptyStringForCurrencyCode(){
		new Money(10.20, "");
	}
}
