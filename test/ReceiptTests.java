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
		r.createSaleInfo();
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
	
	@Test
	public void testAddLine() {
		Receipt r1 = new Receipt(new MockSale());
		Receipt r2 = new Receipt(new MockSale());
		r1.addLine(2, "Skogaholmslimpa", 19.9, 39.8);
		assertTrue(r1.toString().length() > r2.toString().length());
		r2.addLine(1, "Apelsiner 750g eko", 29.95, 29.95);
	}
	
	@Test
	public void testFormattingOfPrice() {
		Receipt r = new Receipt(new MockSale());
		r.addLine(2, "Skogaholmslimpa", 19.9, 39.8);
		assertTrue(r.toString().contains("19.90"));
		assertTrue(r.toString().contains("39.80"));
		r.addLine(1, "Apelsiner 750g eko", 29.95, 29.95);
		assertTrue(r.toString().contains("29.95"));
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void testAddLineWithNegativeQuantity() {
		Receipt r = new Receipt(new MockSale());
		r.addLine(-1, "Skogaholmslimpa", 19.9, 39.8);
	}

	@Test(expected = IllegalArgumentException.class)
	public void testAddLineWithZeroQuantity() {
		Receipt r = new Receipt(new MockSale());
		r.addLine(0, "Skogaholmslimpa", 19.9, 39.8);
	}

	@Test(expected = IllegalArgumentException.class)
	public void testAddLineWithTooLargeQuantity() {
		Receipt r = new Receipt(new MockSale());
		r.addLine(1000, "Skogaholmslimpa", 19.9, 39.8);
	}
	
	@Test
	public void testAddLineWithTooLongItemName() {
		Receipt r = new Receipt(new MockSale());
		r.addLine(1, "Jordnötssmör Crunchy eko", 33.5, 33.5);
		assertFalse(r.toString().contains("Jordnötssmör Crunchy eko"));
	}

	@Test(expected = IllegalArgumentException.class)
	public void testAddLineWithTooShortItemName() {
		Receipt r = new Receipt(new MockSale());
		r.addLine(2, "A", 7.5, 15.0);
	}

	@Test(expected = IllegalArgumentException.class)
	public void testAddLineWithNegativeItemPrice() {
		Receipt r = new Receipt(new MockSale());
		r.addLine(1, "Skogaholmslimpa", -23.7, 39.8);
	}

	@Test(expected = IllegalArgumentException.class)
	public void testAddLineWithNegativeSubTotal() {
		Receipt r = new Receipt(new MockSale());
		r.addLine(1, "Skogaholmslimpa", 19.9, -142.1);
	}

}
