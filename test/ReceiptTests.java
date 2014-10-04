import static org.junit.Assert.*;

import org.junit.Test;


public class ReceiptTests {

	@Test
	public void testIfToStringReturnsEmptyString() {
		Receipt r = new Receipt(new MockSale());
		Receipt.createDelimiter('\0');
		Receipt.createHeader("");
		assertEquals("", r.toString().trim());
	}
	
	@Test
	public void testIfToStringReturnsSomething() {
		Receipt r = new Receipt(new MockSale());
		Receipt.createDelimiter('-');
		assertTrue(r.toString().length() != 0);
	}
	
	@Test
	public void testIfToStringReturnsSameString() {
		Receipt r1 = new Receipt(new MockSale());
		Receipt.createDelimiter('-');
		Receipt r2 = new Receipt(new MockSale());
		assertEquals(r1.toString(), r2.toString());
	}

	@Test
	public void testcreateHeader() {
		Receipt.createHeader("Coop Konsum Midsommarkransen", "www.coop.se", "Tel.nr: 010-7411160");
		Receipt r = new Receipt(new MockSale());
		assertTrue(r.toString().contains("Konsum"));
		assertTrue(r.toString().contains("www"));
		assertTrue(r.toString().contains("7411160"));
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
		String item = "Skogaholmslimpa";
		r1.addLine(2, item, 19.9, 39.8);
		assertTrue(r1.toString().length() > r2.toString().length());
		assertTrue(r1.toString().contains(item));
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
		String item = "Jordnötssmör Crunchy eko";
		r.addLine(1, item, 33.5, 33.5);
		assertFalse(r.toString().contains(item));
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

	@Test
	public void testOtherAddLine() {
		Receipt r1 = new Receipt(new MockSale());
		Receipt r2 = new Receipt(new MockSale());
		String item = "Bananer eko";
		r1.addLine(0.757, item, 24.9, 18.85);
		assertTrue(r1.toString().length() > r2.toString().length());
		assertTrue(r1.toString().contains(item));
		assertTrue(r1.toString().contains("0.757kg"));
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void testOtherAddLineWithNegativeWeight() {
		Receipt r = new Receipt(new MockSale());
		r.addLine(-0.757, "Bananer eko", 24.9, 18.85);
	}

	@Test(expected = IllegalArgumentException.class)
	public void testOtherAddLineWithZeroWeight() {
		Receipt r = new Receipt(new MockSale());
		r.addLine(0.0, "Bananer eko", 24.9, 18.85);
	}
	
	@Test
	public void testIfReceiptContainsTotal() {
		MockSale sale = new MockSale();
		Receipt r = new Receipt(sale);
		r.createTotal();
		assertTrue(r.toString().contains("" + sale.getTotal()));
	}
	
	@Test
	public void testFormattingOfTotalPrice() {
		Receipt r = new Receipt(new MockSale());
		r.createTotal();
		assertTrue(r.toString().contains("606.56"));
	}
}
