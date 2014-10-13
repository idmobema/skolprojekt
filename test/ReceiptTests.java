import static org.junit.Assert.*;

import org.junit.Test;
import org.junit.Before;

public class ReceiptTests {
	private MockSale sale;
	private Receipt r1, r2;
	private String item1, item2;
	
	@Before
	public void setUp() {
		sale = new MockSale();
		r1 = new Receipt(sale);
		r2 = new Receipt(sale);
		item1 = "Skogaholmslimpa";
		item2 = "Bananer eko";
	}
	
	@Test
	public void testIfToStringReturnsEmptyString() {
		Receipt.createDelimiter('\0');
		Receipt.createHeader("");
		assertEquals("", r1.toString().trim());
	}
	
	@Test
	public void testIfToStringReturnsSomething() {
		Receipt.createDelimiter('-');
		assertTrue(r1.toString().length() != 0);
	}
	
	@Test
	public void testIfToStringReturnsSameString() {
		Receipt.createDelimiter('-');
		assertEquals(r1.toString(), r2.toString());
	}

	@Test
	public void testcreateHeader() {
		Receipt.createHeader("Coop Konsum Midsommarkransen", "www.coop.se", "Tel.nr: 010-7411160");
		assertTrue(r1.toString().contains("Konsum"));
		assertTrue(r1.toString().contains("www"));
		assertTrue(r1.toString().contains("7411160"));
	}
	
	@Test
	public void testSetSaleInfo() {
		r1.createSaleInfo();
		assertTrue(r1.toString().contains("Roger"));
		assertTrue(r1.toString().contains("2014-09-29"));
		assertTrue(r1.toString().contains("21:11:43"));
	}
	
	@Test
	public void testIfReceiptNoIncreases() {
		assertFalse(r1.getReceiptNo() == r2.getReceiptNo());
		Receipt r3 = new Receipt(new MockSale());
		assertTrue(r3.getReceiptNo() - r1.getReceiptNo() == 2);
	}
	
	@Test
	public void testAddLine() {
		r1.addLine(2, item1, 19.9, 39.8);
		assertTrue(r1.toString().length() > r2.toString().length());
		assertTrue(r1.toString().contains(item1));
	}
	
	@Test
	public void testFormattingOfPrice() {
		r1.addLine(2, item1, 19.9, 39.8);
		assertTrue(r1.toString().contains("19.90"));
		assertTrue(r1.toString().contains("39.80"));
		r1.addLine(1, "Apelsiner 750g eko", 29.95, 29.95);
		assertTrue(r1.toString().contains("29.95"));
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void testAddLineWithNegativeQuantity() {
		r1.addLine(-1, item1, 19.9, 39.8);
	}

	@Test(expected = IllegalArgumentException.class)
	public void testAddLineWithZeroQuantity() {
		r1.addLine(0, item1, 19.9, 39.8);
	}

	@Test(expected = IllegalArgumentException.class)
	public void testAddLineWithTooLargeQuantity() {
		r1.addLine(1000, item1, 19.9, 39.8);
	}
	
	@Test
	public void testAddLineWithTooLongItemName() {
		String item = "Jordnötssmör Crunchy eko";
		r1.addLine(1, item, 33.5, 33.5);
		assertFalse(r1.toString().contains(item));
	}

	@Test(expected = IllegalArgumentException.class)
	public void testAddLineWithTooShortItemName() {
		r1.addLine(2, "A", 7.5, 15.0);
	}

	@Test(expected = IllegalArgumentException.class)
	public void testAddLineWithNegativeItemPrice() {
		r1.addLine(1, item1, -23.7, 39.8);
	}

	@Test(expected = IllegalArgumentException.class)
	public void testAddLineWithNegativeSubTotal() {
		r1.addLine(1, item1, 19.9, -142.1);
	}

	@Test
	public void testOtherAddLine() {
		r1.addLine(0.757, item2, 24.9, 18.85);
		assertTrue(r1.toString().length() > r2.toString().length());
		assertTrue(r1.toString().contains(item2));
		assertTrue(r1.toString().contains("0.757kg"));
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void testOtherAddLineWithNegativeWeight() {
		r1.addLine(-0.757, item2, 24.9, 18.85);
	}

	@Test(expected = IllegalArgumentException.class)
	public void testOtherAddLineWithZeroWeight() {
		r1.addLine(0.0, item2, 24.9, 18.85);
	}
	
	@Test
	public void testIfReceiptContainsTotal() {
		r1.createTotal();
		assertTrue(r1.toString().contains("" + sale.getTotal()));
	}
	
	@Test
	public void testFormattingOfTotalPrice() {
		r1.createTotal();
		assertTrue(r1.toString().contains("606.56"));
	}
	
	@Test
	public void testDeleteLine() {
		r1.addLine(0.757, item2, 24.9, 18.85);
		r1.addLine(2, item1, 19.9, 39.8);
		assertTrue(r1.toString().contains(item2));
		r1.deleteLine(item2);
		assertFalse(r1.toString().contains(item2));
		assertTrue(r1.toString().contains(item1));
		r1.deleteLine(item1);
		assertFalse(r1.toString().contains(item1));
	}
}
