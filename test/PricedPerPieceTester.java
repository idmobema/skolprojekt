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
		Item item = new PricedPerPiece("Mej12ARL051", 10.50);
		assertNotNull(item);
	}
	
	@Test
	public void testPricedPerPieceConstructorWithInvalidItemID(){
		try{
			item = new PricedPerPiece("", 11.25);
		}
		catch(IllegalArgumentException iAE){
			assertNull("object should not 've been created", item);
		}
	}
	
	@Test (expected = IllegalArgumentException.class)
	public void testPricedPerPieceConstructorWithInvalidItemID2(){
		item = new PricedPerPiece("  trew12345", 11.25);
	}
	
	@Test (expected = IllegalArgumentException.class)
	public void testPricedPerPieceConstructorWithInvalidUnitPrice1(){
		item = new PricedPerPiece("Fis33ABB107", -1.3);
	}
	
	@Test (expected = IllegalArgumentException.class)
	public void testPricedPerPieceConstructorWithInvalidUnitPrice2(){
		item = new PricedPerPiece("Fis33ABB107", 0);
	}
	
	@Test
	public void testGetID(){
		item = new PricedPerPiece("Br�10P�G014", 21.45);
		String expected = "Br�10P�G014";
		String actual = item.getID();
		
		assertEquals(expected, actual);
	}
	
	@Test
	public void testGetUnitPrice(){
		item = new PricedPerPiece("Kaf08NES002", 58.60);
		double expected = 58.60;
		double actual = item.getUnitPrice();
		
		assertTrue(expected == actual);
	}
	
	@Test
	public void testSetUnitPriceWithValidArgs(){
		item = new PricedPerPiece("Ket50FEL021", 22.40);
		item.setUnitPrice(21.05);
		
		double expected = 21.05;
		double actual = item.getUnitPrice();
		
		assertTrue(expected == actual);
	}
	
	@Test (expected = IllegalArgumentException.class)
	public void testSetUnitPriceWithInvalidArgs(){
		item = new PricedPerPiece("Ket50FEL021", 22.40);
		item.setUnitPrice(-6);
	}
	
	@Test
	public void testGetDiscount(){
		item = new PricedPerPiece("Tob28PRI006", 54.00);

		assertNull(item.getDiscount());
	}
	
	@Test
	public void testSetSpecialOfferDiscountWithValidArgs(){
		item = new PricedPerPiece("God66MAR014", 19.75);
		item.setSpecialOfferDiscount("2ForThePriceOf1", 2015, 2, 15, 2015, 2, 20);
		
		Discount expected = new SpecialOffer("2ForThePriceOf1", 2015, 2, 15, 2015, 2, 20);
		Discount actual = item.getDiscount();
		
		assertEquals(expected, actual);
	}
	
	@Test
	public void testGetSpecialOfferDiscount(){
		item = new PricedPerPiece("Kor20G�V058", 59.49);
		item.setSpecialOfferDiscount("2ForThePriceOf1", 2015, 2, 15, 2015, 2, 20);
		
		Discount expected = new SpecialOffer("2ForThePriceOf1", 2015, 2, 15, 2015, 2, 20);
		Discount actual = item.getDiscount();
		
		assertEquals(expected, actual);
	}
	
	@Test
	public void testGetBuyQuantity(){
		item = new PricedPerPiece("Gla10GBA065", 35.30);
		item.setSpecialOfferDiscount("2ForThePriceOf1", 2015, 2, 15, 2015, 2, 20);
		((SpecialOffer) item.getDiscount()).setBuyQuantity(3);
		int expected = 3;
		int actual = ((SpecialOffer) item.getDiscount()).getBuyQuantity();
		
		assertTrue(expected == actual);
	}
	
	@Test
	public void testGetGetFreeQuantity(){
		item = new PricedPerPiece("Gla10GBA065", 35.30);
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
	public void testGetUnitPriceWith(){
		item = new PricedPerPiece("Gla10GBA065", 35.30);
		item.setSpecialOfferDiscount("2ForThePriceOf1", 2015, 2, 15, 2015, 2, 20);
		((SpecialOffer) item.getDiscount()).setBuyQuantity(1);
		((SpecialOffer) item.getDiscount()).setGetFreeQuantity(1);
		
		double finalPriceExpected = 17.65;
		double finalPriceActual = item.getUnitPrice();
		
		assertTrue(finalPriceExpected == finalPriceActual);
	}

}
