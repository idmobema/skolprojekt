import static org.junit.Assert.*;

import org.junit.Test;


public class ReceiptTests {

	@Test
	public void testIfToStringReturnsEmptyString() {
		Receipt r = new Receipt();
		Receipt.setDelimiter("");
		Receipt.setHeader("");
		assertEquals("", r.toString());
	}
	
	@Test
	public void testIfToStringReturnsSomething() {
		Receipt r = new Receipt();
		Receipt.setDelimiter("----------------------------------------");
		assertTrue(r.toString().length() != 0);
	}
	
	@Test
	public void testIfToStringReturnsSameString() {
		Receipt r1 = new Receipt();
		Receipt.setDelimiter("----------------------------------------");
		Receipt r2 = new Receipt();
		assertEquals(r1.toString(), r2.toString());
	}

	@Test
	public void testSetHeader() {
		String header = "      Coop Konsum Midsommarkransen      \n" +
						"              www.coop.se               \n" +
						"           Tel.nr: 010-7411160          \n";
		Receipt.setHeader(header);
		Receipt r = new Receipt();
		assertTrue(r.toString().indexOf("Konsum") != -1);
		System.out.println(r.toString());
	}
	
}
