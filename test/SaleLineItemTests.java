import static org.junit.Assert.*;

import org.junit.Test;


public class SaleLineItemTests {

	@Test
	public void increaseQuantityTest() {
		SaleLineItem sli = new SaleLineItem(new Item(new ItemDescription("MumsMums", "", new Money(1550, "SEK"), "12345678902")), 1);
		sli.increaseQuantity();
		assertEquals(sli.getQuantity(), 2);
		
	}
	
	@Test
	public void getSubTotal() {
		SaleLineItem sli = new SaleLineItem(new Item(new ItemDescription("MumsMums", "", new Money(1500, "SEK"), "12345678902")), 1);
		sli.increaseQuantity();
		assertEquals(sli.getSubTotal(), new Money(3000, "SEK"));
	}
	
	@Test
	public void testToString() {
		SaleLineItem sli = new SaleLineItem(new Item(new ItemDescription("MumsMums", "", new Money(1500, "SEK"), "12345678902")), 1);
		assertTrue(sli.toString().contains("MumsMums"));
		sli.increaseQuantity();
		assertTrue(sli.toString().contains("2"));
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void testToBigQuantity() {
		SaleLineItem sli = new SaleLineItem(new Item(new ItemDescription("MumsMums", "", new Money(1500, "SEK"), "12345678902")), 1);
		for(int i = 0; i <= 1000; i++)
			sli.increaseQuantity();
		sli.toString();
	}
	
	@Test
	public void testDiscountResultLine() {
		SaleLineItem sli = new SaleLineItem(new TestItem(new ItemDescription("MumsMums", "", new Money(1500, "SEK"), "12345678902")), 1);
		assertTrue(sli.toString().contains("Rabatt: 2 för 25:-"));
		assertTrue(sli.toString().contains("-2.90"));
		
	}
	
	
}
