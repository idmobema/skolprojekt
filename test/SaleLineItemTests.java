import static org.junit.Assert.*;

import org.junit.Test;


public class SaleLineItemTests {

	@Test
	public void increaseQuantityTest() {
		SaleLineItem sli = new SaleLineItem(new Item(new ItemDescription("MumsMums", "", new Money(1550, "SEK"), "12345678902")));
		sli.increaseQuantity();
		assertEquals(sli.getQuantity(), 2);
		
	}
	
	@Test
	public void getSubTotal() {
		SaleLineItem sli = new SaleLineItem(new Item(new ItemDescription("MumsMums", "", new Money(1500, "SEK"), "12345678902")));
		sli.increaseQuantity();
		assertEquals(sli.getSubTotal(), new Money(3000, "SEK"));
	}
	

}
