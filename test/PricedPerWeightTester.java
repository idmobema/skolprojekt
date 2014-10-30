import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Test;


public class PricedPerWeightTester {

	Item pPW;
	
	@After
	public void tearDown(){
		pPW = null;
	}
	
	@Test
	public void testPricedPerWeightConstructor() {
		 pPW = new PricedPerWeight("App45TIR004", 134.5, "grams");
		assertNotNull(pPW);
	}
	
	@Test
	public void testPricedPerWeightConstWithIllegalArgs(){
		try{
			pPW = new PricedPerWeight("JAs85KRO014", 45, "");
		}
		catch(IllegalArgumentException iAE){
			assertNull(pPW);
		}
	}

	@Test
	public void testPricedPerWeightConstWithIllegalArgs2(){
		try{
			pPW = new PricedPerWeight("JAs85KRO014", 45, "_=");
		}
		catch(IllegalArgumentException iAE){
			assertNull(pPW);
		}
	}
	
	@Test
	public void testGetMeasureUnit(){
		pPW = new PricedPerWeight("App45TIR004", 134.5, "kilo");
		String expected = "kilo";
		String actual = ((PricedPerWeight) pPW).getMeasureUnit();
		
		assertEquals(expected, actual);
	}
	
	@Test
	public void testSetMeasureUnit(){
		pPW = new PricedPerWeight("App45TIR004", 134.5, "kilo");
		((PricedPerWeight) pPW).setMeasureUnit("hk");
		
		String expected = "hk";
		String actual = ((PricedPerWeight) pPW).getMeasureUnit();
		
		assertEquals(expected, actual);
	}

	@Test
	public void testSetPriceDiscount(){
		pPW = new PricedPerWeight("App45TIR004", 21, "kilo");
		pPW.setPriceDiscount(10, 2015, 2, 15, 2015, 2, 20);
		
		Money expected = new Money(18.90, "SEK");
		Money actual = pPW.getReducedPrice();
		
		assertEquals(expected, actual);
	}
	
	@Test
	public void testGetDiscount(){
		pPW = new PricedPerWeight("App45TIR004", 21, "kilo");
		pPW.setPriceDiscount(10, 2015, 2, 15, 2015, 2, 20);
		
		Discount expected = new PriceDiscount(10, 2015, 2, 15, 2015, 2, 20);
		Discount actual = pPW.getDiscount();
		
		assertEquals(expected, actual);
	}

	@Test
	public void testSetDiscount() {
		pPW = new PricedPerWeight("App45TIR004", 21, "kilo");
		Discount discount = new PriceDiscount(10, 2015, 2, 15, 2015, 2, 20);
		pPW.setDiscount(discount);
		assertTrue(pPW.hasDiscount());
	}
	
	@Test (expected = IllegalArgumentException.class)
	public void testSetDiscountWithSpecialOfferType(){

		pPW = new PricedPerWeight("App45TIR004", 21, "kilo");
		Discount discount = new SpecialOffer("Buy3Get5", 2015, 2, 15, 2015, 2, 20);
		pPW.setDiscount(discount);

		assertNull(pPW.getDiscount());
	}
	
	@Test
	public void testToString(){
		pPW = new PricedPerWeight("App45TIR004", 21, "kilo");
		String expected = "Item ID: App45TIR004" + "\nDiscount: None\n";
		expected += "Unit Price: 21.00 SEK\nReduced Price: none";
		String actual = pPW.toString();
		
		assertEquals(expected, actual);
	}

}
