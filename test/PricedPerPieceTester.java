import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Test;


public class PricedPerPieceTester {

	Item item;
	@After
	public void tearDown(){
		item = null;
	}
	@Test
	public void testPricedPerPieceConstructorWithValidArgs() {
		Item item = new PricedPerPiece("Name", "Mej12ARL051", 10.50);
		assertNotNull(item);
	}
	
	@Test
	public void testPricedPerPieceConstructorWithInvalidItemID(){
		try{
			item = new PricedPerPiece("Name", "", 11.25);
		}
		catch(IllegalArgumentException iAE){
			assertNull("object should not 've been created", item);
		}
	}
	
	@Test (expected = IllegalArgumentException.class)
	public void testPricedPerPieceConstructorWithInvalidItemID2(){
		item = new PricedPerPiece("Name", "  trew12345", 11.25);
	}
	
	@Test (expected = IllegalArgumentException.class)
	public void testPricedPerPieceConstructorWithInvalidUnitPrice1(){
		item = new PricedPerPiece("Name", "Fis33ABB107", -1.3);
	}
	
	@Test (expected = IllegalArgumentException.class)
	public void testPricedPerPieceConstructorWithInvalidUnitPrice2(){
		item = new PricedPerPiece("Name", "Fis33ABB107", 0);
	}
	
	@Test
	public void testGetID(){
		item = new PricedPerPiece("Name", "12345678909", 21.45);
		String expected = "12345678909";
		String actual = item.getID();
		
		assertEquals(expected, actual);
	}
	
	@Test
	public void testGetUnitPriceWithoutSpecialOfferSet(){
		item = new PricedPerPiece("Name", "Kaf08NES002", 58.60);
		double expected = 58.60;
		double actual = item.getUnitPrice();
		
		assertTrue(expected == actual);
	}
	
	@Test
	public void testSetUnitPriceWithValidArgs(){
		item = new PricedPerPiece("Name", "Ket50FEL021", 22.40);
		item.setUnitPrice(21.05);
		
		double expected = 21.05;
		double actual = item.getUnitPrice();
		
		assertTrue(expected == actual);
	}
	
	@Test (expected = IllegalArgumentException.class)
	public void testSetUnitPriceWithInvalidArgs(){
		item = new PricedPerPiece("Name", "Ket50FEL021", 22.40);
		item.setUnitPrice(-6);
	}
	
	@Test
	public void testGetDiscount(){
		item = new PricedPerPiece("Name", "Tob28PRI006", 54.00);

		assertNull(item.getDiscount());
	}
	
	@Test
	public void testSetSpecialOfferDiscountWithValidArgs(){
		item = new PricedPerPiece("Name", "God66MAR014", 19.75);
		item.setSpecialOfferDiscount("2ForThePriceOf1", 2015, 2, 15, 2015, 2, 20);
		
		Discount expected = new SpecialOffer("2ForThePriceOf1", 2015, 2, 15, 2015, 2, 20);
		Discount actual = item.getDiscount();
		
		assertEquals(expected, actual);
	}
	
	@Test
	public void testGetSpecialOfferDiscount(){
		item = new PricedPerPiece("Name", "12345678901", 59.49);
		item.setSpecialOfferDiscount("2ForThePriceOf1", 2015, 2, 15, 2015, 2, 20);
		
		Discount expected = new SpecialOffer("2ForThePriceOf1", 2015, 2, 15, 2015, 2, 20);
		Discount actual = item.getDiscount();
		
		assertEquals(expected, actual);
	}
	
	@Test
	public void testGetBuyQuantity(){
		item = new PricedPerPiece("Name", "Gla10GBA065", 35.30);
		item.setSpecialOfferDiscount("2ForThePriceOf1", 2015, 2, 15, 2015, 2, 20);
		((SpecialOffer) item.getDiscount()).setBuyQuantity(3);
		int expected = 3;
		int actual = ((SpecialOffer) item.getDiscount()).getBuyQuantity();
		
		assertTrue(expected == actual);
	}
	
	@Test
	public void testGetGetFreeQuantity(){
		item = new PricedPerPiece("Name", "Gla10GBA065", 35.30);
		item.setSpecialOfferDiscount("5ForThePriceOf3", 2015, 2, 15, 2015, 2, 20);
		((SpecialOffer) item.getDiscount()).setBuyQuantity(3);
		((SpecialOffer) item.getDiscount()).setGetFreeQuantity(5);
		
		int buyQuantityExpected = 3;
		int getFreeQuantityExpected = 5;
		int buyQuantityActual = ((SpecialOffer) item.getDiscount()).getBuyQuantity();
		int getFreeQuantityActual = ((SpecialOffer) item.getDiscount()).getGetFreeQuantity();
		
		assertTrue(buyQuantityExpected == buyQuantityActual && getFreeQuantityExpected == getFreeQuantityActual);
	}
	
	@Test
	public void testGetUnitPriceWithSpecialOfferSet(){
		item = new PricedPerPiece("Name", "Gla10GBA065", 35.30);
		item.setSpecialOfferDiscount("2ForThePriceOf1", 2015, 2, 15, 2015, 2, 20);
		((SpecialOffer) item.getDiscount()).setBuyQuantity(1);
		((SpecialOffer) item.getDiscount()).setGetFreeQuantity(1);
		
		double finalPriceExpected = 17.65;
		double finalPriceActual = item.getUnitPrice();
		
		assertTrue(finalPriceExpected == finalPriceActual);
	}
	
	@Test
	public void testGetUnitPriceWithPriceDiscountSet(){
		item = new PricedPerPiece("Name", "12345678901", 50.0);
		item.setPriceDiscount(10.0, 2015, 2, 15, 2015, 2, 20);

		double expected = 45.0;
		double actual = item.getUnitPrice();
		
		assertTrue(expected == actual);
	}
	
	@Test
	public void testEquals(){
		Item item1 = new PricedPerPiece("Name", "12345678901", 59.49);
		item1.setSpecialOfferDiscount("2ForThePriceOf1", 2015, 2, 15, 2015, 2, 20);
		Item item2 = new PricedPerPiece("Name", "12345678901", 59.49);
		item2.setSpecialOfferDiscount("2ForThePriceOf1", 2015, 2, 15, 2015, 2, 20);
		
		assertEquals(item1, item2);
	}
	
	@Test
	public void testToString(){
		item = new PricedPerPiece("Name", "12345678901", 50.0);
		item.setPriceDiscount(15.0, 2015, 2, 15, 2015, 2, 20);
		
		String expected = "Item ID: " + item.getID();
		expected += "\nDiscount: " + item.getDiscount();
		expected += "\nUnit Price: " + item.getUnitPrice();
		
		String actual = item.toString();
		
		assertEquals(expected, actual);
	}

}
