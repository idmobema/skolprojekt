
import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

public class ItemDescriptionTests {
	
	ItemDescription id;
	
	@Before
	public void setUp() throws Exception {
		id = new ItemDescription("Gurka","En grön grönsak", new Money(1550, "SEK"), "314");
	}

	@Test
	public void testGetPrice() {
		assertEquals(new Money(1550, "SEK"), id.getPrice());
	}
	
	@Test
	public void testGetName() {
		assertEquals("Gurka", id.getName());
	}
	
	@Test
	public void testGetDescription() {
		assertEquals("En grön grönsak", id.getDescription());
	}
	
	@Test
	public void testGetItemId() {
		assertEquals("314", id.getItemId());
	}
	
	@Test
	public void testToString() {
		assertEquals(id.getName(), id.toString());
	}
	

}
