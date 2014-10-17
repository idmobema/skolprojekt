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
		item = new PricedPerPiece("Brö10PÅG014", 21.45);
		String expected = "Brö10PÅG014";
		String actual = item.getID();
		
		assertEquals(expected, actual);
	}
	
	@Test
	public void testGetUnitPriceWithoutSpecialOfferSet(){
		item = new PricedPerPiece("Kaf08NES002", 58.60);
		Money expected = new Money (58.60, "SEK");
		Money actual = item.getUnitPrice();
		
		assertEquals(expected, actual);
	}
	
	@Test
	public void testSetUnitPriceWithValidArgs(){
		item = new PricedPerPiece("Ket50FEL021", 22.40);
		item.setUnitPrice(21.05);
		
		Money expected = new Money(21.05, "SEK");
		Money actual = item.getUnitPrice();
		
		assertEquals(expected, actual);
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
		((PricedPerPiece) item).setSpecialOfferDiscount("2ForThePriceOf1", 2015, 2, 15, 2015, 2, 20);
		
		Discount expected = new SpecialOffer("2ForThePriceOf1", 2015, 2, 15, 2015, 2, 20);
		Discount actual = item.getDiscount();
		
		assertEquals(expected, actual);
	}
	
	@Test
	public void testGetSpecialOfferDiscount(){
		item = new PricedPerPiece("Kor20GÄV058", 59.49);
		((PricedPerPiece) item).setSpecialOfferDiscount("2ForThePriceOf1", 2015, 2, 15, 2015, 2, 20);
		
		Discount expected = new SpecialOffer("2ForThePriceOf1", 2015, 2, 15, 2015, 2, 20);
		Discount actual = item.getDiscount();
		
		assertEquals(expected, actual);
	}
	
	@Test
	public void testGetBuyQuantity(){
		item = new PricedPerPiece("Gla10GBA065", 35.30);
		((PricedPerPiece) item).setSpecialOfferDiscount("2ForThePriceOf1", 2015, 2, 15, 2015, 2, 20);
		((SpecialOffer) item.getDiscount()).setBuyQuantity(3);
		int expected = 3;
		int actual = ((SpecialOffer) item.getDiscount()).getBuyQuantity();
		
		assertTrue(expected == actual);
	}
	
	@Test
	public void testGetGetFreeQuantity(){
		item = new PricedPerPiece("Gla10GBA065", 35.30);
		((PricedPerPiece) item).setSpecialOfferDiscount("5ForThePriceOf3", 2015, 2, 15, 2015, 2, 20);
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
		item = new PricedPerPiece("Gla10GBA065", 35.30);
		((PricedPerPiece) item).setSpecialOfferDiscount("2ForThePriceOf1", 2015, 2, 15, 2015, 2, 20);

		item.activateSpecialOffer(2, 1);

		Money actualFinalPrice = item.getUnitPrice();
		
		assertNull(item.getReducedPrice());
		assertEquals(new Money(35.30, "SEK"), actualFinalPrice);
	}
	
	@Test
	public void testGetUnitPriceWithPriceDiscountSet(){
		item = new PricedPerPiece("Äpp21DRI014", 50.0);
		((PricedPerPiece) item).setPriceDiscount(10.0, 2015, 2, 15, 2015, 2, 20);

		Money expected = new Money(45.00, "SEK");
		Money actual = item.getReducedPrice();
		
		assertEquals(expected.getAmount(),actual.getAmount());
	}
	
	
	@Test
	public void testEquals(){
		Item item1 = new PricedPerPiece("Kor20GÄV058", 59.49);
		((PricedPerPiece) item1).setSpecialOfferDiscount("2ForThePriceOf1", 2015, 2, 15, 2015, 2, 20);
		Item item2 = new PricedPerPiece("Kor20GÄV058", 59.49);
		((PricedPerPiece) item2).setSpecialOfferDiscount("2ForThePriceOf1", 2015, 2, 15, 2015, 2, 20);
		
		
		assertEquals(item1, item2);
	}
	
	@Test
	public void testToString(){
		item = new PricedPerPiece("Äpp21DRI014", 50.0);
		item.setPriceDiscount(15.0, 2015, 2, 15, 2015, 2, 20);
		
		String expected = "Item ID: " + item.getID();
		expected += "\nDiscount: " + item.getDiscount();
		expected += "\nUnit Price: " + item.getUnitPrice();
		expected += "\nReduced Price: " + item.getReducedPrice();
		
		String actual = item.toString();
		
		assertEquals(expected, actual);
	}

}
