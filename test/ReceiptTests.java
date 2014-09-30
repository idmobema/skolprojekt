import static org.junit.Assert.*;

import org.junit.Test;


public class ReceiptTests {

	@Test
	public void testIfToStringReturnsEmptyString() {
		Receipt r = new Receipt(new MockSale());
		Receipt.setDelimiter("");
		Receipt.setHeader("");
		assertEquals("", r.toString());
	}
	
	@Test
	public void testIfToStringReturnsSomething() {
		Receipt r = new Receipt(new MockSale());
		Receipt.setDelimiter("----------------------------------------");
		assertTrue(r.toString().length() != 0);
	}
	
	@Test
	public void testIfToStringReturnsSameString() {
		Receipt r1 = new Receipt(new MockSale());
		Receipt.setDelimiter("----------------------------------------");
		Receipt r2 = new Receipt(new MockSale());
		assertEquals(r1.toString(), r2.toString());
	}

	@Test
	public void testSetHeader() {
		String header = "      Coop Konsum Midsommarkransen      \n" +
						"              www.coop.se               \n" +
						"           Tel.nr: 010-7411160          \n";
		Receipt.setHeader(header);
		Receipt r = new Receipt(new MockSale());
		assertTrue(r.toString().contains("Konsum"));
	}
	
	@Test
	public void testSetSaleInfo() {
		Receipt r = new Receipt(new MockSale());
		r.setSaleInfo();
		assertTrue(r.toString().contains("Roger"));
		assertTrue(r.toString().contains("2014-09-29"));
		assertTrue(r.toString().contains("21:11:43"));
	}
	
	@Test
	public void testIfReceiptNoIncreases() {
		Receipt r1 = new Receipt(new MockSale());
		Receipt r2 = new Receipt(new MockSale());
		assertFalse(r1.getReceiptNo() == r2.getReceiptNo());
		Receipt r3 = new Receipt(new MockSale());
		assertTrue(r3.getReceiptNo() - r1.getReceiptNo() == 2);
	}
}
