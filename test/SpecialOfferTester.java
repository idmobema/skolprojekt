/**#BC141007#*/

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Test;


public class SpecialOfferTester {

	SpecialOffer sO;
	
	@After
	public void tearDown(){
		sO = null;
	}
	@Test
	public void testSpecialOfferConstructorWithLegalArguments() {
		Discount sO = new SpecialOffer("BuyOneGetTwo", 2015, 4, 20, 2015, 4, 25);
		boolean expected = true;
		boolean actual = sO instanceof Discount;
		
		assertTrue(expected && actual);
	}


	@Test (expected = IllegalArgumentException.class)
	public void testSpecialOfferConstructorWithIllegalArguments() {
		sO = new SpecialOffer(null, 2015, 4, 20, 2015, 4, 25);
		
		assertNull("obj should not have been created", sO);
	}
	
	@Test (expected = IllegalArgumentException.class)
	public void testSpecialOfferConstructorWithEmptyString() {
		sO = new SpecialOffer("", 2015, 4, 20, 2015, 4, 25);
		assertNull("obj should not have been created", sO);
	}
	
	@Test (expected = IllegalArgumentException.class)
	public void testSpecialOfferConstructorWithLessThan3Chars() {
		sO = new SpecialOffer("Ab", 2015, 4, 20, 2015, 4, 25);
		assertNull("obj should not have been created", sO);
	}
	
	@Test 
	public void testSpecialOfferConstructorWith2FirstCharsNonAlphaNum() {
		try{
			sO = new SpecialOffer("+*d", 2015, 4, 20, 2015, 4, 25);
		}
		catch(IllegalArgumentException iAE){
			assertNull("obj should not have been created", sO);
		}
		
	}
	
	@Test 
	public void testSpecialOfferConstructorWithMoreThan40Chars() {
		try{
			sO = new SpecialOffer("abcdefghijklmnopqrstuvwyxz1234567891011121314151617181920", 2015, 4, 20, 2015, 4, 25);
		}
		catch(IllegalArgumentException iAE){
			assertNull("obj should not have been created", sO);
		}
		
	}
	
	@Test
	public void testGetOfferName(){
		Discount sO = new SpecialOffer("TwoForThePriceOfOne", 2015, 4, 20, 2015, 4, 25);
		String actual =sO.getOfferName();
		String expected = "TwoForThePriceOfOne";
		
		assertEquals(expected, actual);
	}

	@Test
	public void testGetBuyQuantity(){
		sO = new SpecialOffer("TwoForThePriceOfOne", 2015, 4, 20, 2015, 4, 25);
		int expected = 0;
		int actual = sO.getBuyQuantity();
		
		assertTrue(expected == actual);
	}
	
	@Test
	public void testSetBuyQuantity(){
		sO = new SpecialOffer("TwoForThePriceOfOne", 2015, 4, 20, 2015, 4, 25);
		int expected = 2;
		sO.setBuyQuantity(2);
		int actual = sO.getBuyQuantity();
		
		assertTrue(expected == actual);
	}
	
	@Test
	public void testSetGetFreeQuantity(){
		sO = new SpecialOffer("TwoForThePriceOfOne", 2015, 4, 20, 2015, 4, 25);
		sO.setBuyQuantity(1);
		sO.setGetFreeQuantity(2);
		int expected = 2;
		int actual = sO.getGetFreeQuantity();
		
		assertTrue(expected == actual);
	}
	
	@Test
	public void testSetGetFreeQuantityWithoutSettingBuyQuantity(){
		sO = new SpecialOffer("TwoForThePriceOfOne", 2015, 4, 20, 2015, 4, 25);
		try{
			sO.setGetFreeQuantity(3);
		}
		catch(IllegalStateException iSE){
			assertFalse(sO.isOfferActive());
		}
	}
	
	@Test (expected = IllegalStateException.class)
	public void testSetBuyQuantityWithIllegalParameter(){
		sO = new SpecialOffer("TwoForThePriceOfOne", 2015, 4, 20, 2015, 4, 25);
		sO.setBuyQuantity(-1);
		sO.setGetFreeQuantity(3);
	}
	
	@Test
	public void testToString(){
		sO = new SpecialOffer("TwoForThePriceOfOne", 2015, 4, 20, 2015, 4, 25);
		sO.setBuyQuantity(1);
		sO.setGetFreeQuantity(2);
		String expected = "Identifier TwoForThePriceOfOne\nValid through:\n";
		expected += "Start date: 20/4/2015\nEnd date: 25/4/2015";
		expected += "\nStatus: active\nBuy 1 Get 2";
		String actual = sO.toString();
		
		assertEquals(expected, actual);
	}
	
	@Test
	public void testIsOfferActive(){
		sO = new SpecialOffer("TwoForThePriceOfOne", 2015, 4, 20, 2015, 4, 25);
		boolean expected = false;
		boolean actual = sO.isOfferActive();
		
		assertFalse(expected || actual);
	}
	
	@Test
	public void testIsOfferActive2(){
		sO = new SpecialOffer("TwoForThePriceOfOne", 2015, 4, 20, 2015, 4, 25);
		sO.setBuyQuantity(1);
		boolean expected = false;
		boolean actual = sO.isOfferActive();
		
		assertFalse(expected || actual);
	}
	
	@Test
	public void testIsOfferActive3(){
		sO = new SpecialOffer("TwoForThePriceOfOne", 2015, 4, 20, 2015, 4, 25);
		sO.setBuyQuantity(1);
		sO.setGetFreeQuantity(2);
		boolean expected = true;
		boolean actual = sO.isOfferActive();
		
		assertTrue(expected && actual);
	}
	
	@Test
	public void testEquals(){
		Discount sO1 = new SpecialOffer("TwoForThePriceOfOne", 2015, 4, 20, 2015, 4, 25);
		Discount sO2 = new SpecialOffer("TwoForThePriceOfOne", 2015, 4, 20, 2015, 4, 25);
		
		assertEquals(sO1, sO2);
	}
	
	
}
