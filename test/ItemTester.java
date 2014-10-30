import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;


public class ItemTester {
	Item cereals, bambiMeat;
	Discount expectedD, actualD, specOf, priceD;
	
	Money expectedM, actualM;
	String expectedUID, actualUID; // unitId
	
	@Before
	public void setUp(){
		cereals = new PricedPerPiece("Cer69MAN008", 25.15);
		bambiMeat = new PricedPerWeight("Cha12HUN005", 208.66, "kg");
		specOf = new SpecialOffer("2for1", 2015, 5, 10, 2015, 5, 15);
		priceD = new PriceDiscount(25, 2015, 5, 10, 2015, 5, 15);
	}
	
	@After
	public void tearDown(){
		cereals = null;
		bambiMeat = null;
		expectedD = null;
		actualD = null;
		expectedM = null;
		actualM = null;
		expectedUID = null;
		actualUID = null;
		specOf = null;
		priceD = null;
	}
	
	@Test
	public void testItemConstructor() {

		assertNotNull(cereals);
	}

	@Test
	public void testGetID() {
		expectedUID = "Cer69MAN008";
		actualUID = cereals.getID();
		
		
		assertEquals(expectedUID, actualUID);
	}

	@Test
	public void testSetUnitPrice() {
		cereals.setUnitPrice(21.50);
		expectedM = new Money(21.50, "SEK");
		actualM = cereals.getUnitPrice();
		
		assertEquals(expectedM, actualM);
	}

	@Test
	public void testGetDiscount() {
		cereals.setDiscount(specOf);
		expectedD = specOf;
		actualD = cereals.getDiscount();
		
		assertEquals(expectedD, actualD);
	}

	@Test
	public void testGetUnitPrice() {
		expectedM = new Money(25.15, "SEK");
		actualM = cereals.getUnitPrice();
		
		assertEquals(expectedM, actualM);
	}

	@Test
	public void testSetDiscount() {
		cereals.setDiscount(specOf);
		expectedD = specOf;
		actualD = cereals.getDiscount();
		
		assertEquals(expectedD, actualD);
	}
	
	@Test
	public void testSetDiscount2() {
		cereals.setPriceDiscount(25, 2015, 5, 10, 2015, 5, 15);
		expectedD = priceD;
		actualD = cereals.getDiscount();

		assertEquals(expectedD, actualD);
	}
	
	@Test (expected = IllegalArgumentException.class)
	public void testSetDiscountSpecialOfferForPricedPerWeightTypeOfProduct() {
		bambiMeat.setDiscount(specOf);
		actualD = bambiMeat.getDiscount();
		
		assertNull(actualD);
	}

	@Test
	public void testSetPriceDiscount() {
		bambiMeat.setPriceDiscount(25, 2015, 5, 10, 2015, 5, 15);
		actualD = bambiMeat.getDiscount();
		expectedD = priceD;

		assertEquals(expectedD, actualD);
	}

	@Test
	public void testToString() {
		cereals.setPriceDiscount(25, 2015, 5, 10, 2015, 5, 15);
		String expected = "Item ID: Cer69MAN008\nDiscount: Price Reduction\nValid through:\n";
		expected += "Start date: 10/5/2015\nEnd date: 15/5/2015\nPercentage: 25.0";
		expected += "\nUnit Price: 25.15 SEK\nReduced Price: 18.86 SEK";
		String actual = cereals.toString();
		
		assertEquals(expected, actual);
	}
	
	@Test
	public void testGetReducedPriceWithSpecialOffer(){
		((PricedPerPiece) cereals).setSpecialOfferDiscount("2for1", 2015, 5, 10, 2015, 5, 15);
		cereals.activateSpecialOffer(1, 2);
		
		actualM = cereals.getReducedPrice();
		
		assertNull(actualM);
	}
	
	@Test
	public void testChangeADiscountType(){
		cereals.setPriceDiscount(10, 2015, 5, 10, 2015, 5, 15);
		cereals.setUnitPrice(100);

		((PricedPerPiece)cereals).setSpecialOfferDiscount("2for1", 2015, 5, 10, 2015, 5, 15);
		cereals.activateSpecialOffer(1, 1);

		actualM = cereals.getReducedPrice();
		
		assertNull(actualM);

	}

	@Test
	public void testCompareTo(){
		Item something = new PricedPerPiece("Cer69MAN008", 25.15);
		boolean equalItems = something.equals(cereals);
		int compared = something.compareTo(cereals);
		
		assertTrue(equalItems && compared == 0);
		
	}
	
	@Test
	public void testHasDiscount(){
		cereals.setDiscount(priceD);
		assertTrue(cereals.hasDiscount());
	}
	
	@Test
	public void testHasDiscount2(){
		SpecialOffer sO = new SpecialOffer("Two for One", 2015, 3, 15, 2015, 3, 20);
		cereals.setDiscount(sO);
		assertTrue(cereals.hasDiscount());
	}
	
	@Test
	public void testHasDiscount3(){
		assertFalse(cereals.hasDiscount());
	}
	
	@Test(expected = NullPointerException.class)
	public void testGetDiscountAmountWithoutAnySetDiscount(){
		assertNull(cereals.getDiscountAmount());
		
	}
	
	@Test
	public void testGetDiscountAmount(){
		cereals.setDiscount(priceD);
		Money expected = new Money(6.29, "SEK");
		Money actual = cereals.getDiscountAmount();
		
		assertEquals(expected, actual);
	}

	@Test
	public void testHashCode() {
		PricedPerPiece cereals2 = new PricedPerPiece("Cer69MAN008", 25.15);
		assertTrue(cereals.hashCode() == cereals2.hashCode());
	}
	
	@Test
	public void testEquals() {
		assertFalse(cereals.equals(null));
	}
	
	@Test
	public void testEquals2() {
		assertFalse(cereals.equals(new Money(10.00, "SEK")));
	}
	
	@Test
	public void testEquals3() {
		PricedPerPiece cereals2 = new PricedPerPiece("Cer69MAN008", 25.15);
		cereals.setDiscount(priceD);
		assertFalse(cereals.equals(cereals2));
	}
	
	@Test
	public void testEquals4() {
		PricedPerPiece cereals2 = new PricedPerPiece("Cer69MAN008", 25.15);
		cereals2.setDiscount(priceD);
		assertFalse(cereals.equals(cereals2));
	}
	
	@Test
	public void testEquals5() {
		PricedPerPiece cereals2 = new PricedPerPiece("Cer69MAN008", 25.15);
		cereals2.setDiscount(priceD);
		PriceDiscount priceD2 = new PriceDiscount(10, 2015, 5, 10, 2015, 5, 15);
		cereals.setDiscount(priceD2);
		assertFalse(cereals.equals(cereals2));
	}
	
	@Test
	public void testEquals6() {
		PricedPerPiece cereals2 = new PricedPerPiece("Cer69MAN008", 25.15);
		cereals.setDiscount(priceD);
		assertFalse(cereals2.equals(cereals));
	}
	
	@Test
	public void testGetDiscountResult() {
		cereals.setDiscount(specOf);
		assertTrue(cereals.getDiscountResult(1).toString().contains("0.00"));
	}
	
	@Test
	public void testGetDiscountResult2() {
		cereals.setDiscount(specOf);
		SpecialOffer special = (SpecialOffer) cereals.getDiscount();
		special.setBuyQuantity(1);
		special.setGetFreeQuantity(1);
		assertFalse(cereals.getDiscountResult(1).toString().contains("0.00"));
	}
	
	
	@Test
	public void testGetDiscountResult3() {
		cereals.setDiscount(specOf);
		SpecialOffer special = (SpecialOffer) cereals.getDiscount();
		special.setBuyQuantity(2);
		special.setGetFreeQuantity(1);
		assertFalse(cereals.getDiscountResult(1).toString().contains("0.00"));
	}
	
	@Test(expected = NullPointerException.class)
	public void testGetDiscountAmount2() {
		cereals.setDiscount(specOf);
		cereals.getDiscountAmount();
	}
	
	@Test
	public void testActivateSpecialOffer() {
		cereals.setDiscount(priceD);
		cereals.activateSpecialOffer(3, 2);
	}
	
}
