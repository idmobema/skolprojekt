import static org.junit.Assert.*;
import org.junit.Test;


public class SaleLineItemTests {

	@Test
	public void increaseQuantityTest() {
		SaleLineItem sli = new SaleLineItem(new PricedPerPiece("MumsMums", "123456789AB", 15));
		sli.increaseQuantity();
		assertEquals(sli.getQuantity(), 2);
		
	}
	
	@Test
	public void getSubTotal() {
		SaleLineItem sli = new SaleLineItem(new PricedPerPiece("MumsMums", "123456789AB", 15));
		sli.increaseQuantity();
		assertEquals(sli.getSubTotal(), 30, 0.1);
	}
	

}
