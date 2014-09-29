import static org.junit.Assert.*;

import org.junit.Test;


public class ReceiptTests {

	@Test
	public void testIfToStringReturnsEmptyString() {
		Receipt r = new Receipt();
		r.setDelimiter("");
		assertEquals("", r.toString());
	}
	
	@Test
	public void testIfToStringReturnsSomething() {
		Receipt r = new Receipt();
		r.setDelimiter("----------------------------------------");
		assertTrue(r.toString().length() != 0);
	}
	
	@Test
	public void testIfToStringReturnsSameString() {
		Receipt r1 = new Receipt();
		r1.setDelimiter("----------------------------------------");
		Receipt r2 = new Receipt();
		assertEquals(r1.toString(), r2.toString());
	}

}
