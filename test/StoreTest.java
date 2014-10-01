

import static org.junit.Assert.*;

import org.junit.Test;

public class StoreTest {

	@Test
	public void testIfToStringReturnsInput() {
		Store s = new Store("Bra Butiken","Flintv�gen 1", "0745673231");
		assertEquals("Bra Butiken, Flintv�gen 1, tel. 0745673231", s.toString());
	}
	
	@Test
	public void testGetName(){
		Store s = new Store("Butik2", "V�gen 1", "0701234567");
		assertEquals("Butik2", s.getName());
	}
	
	@Test
	public void testGetAdress(){
		Store s = new Store("Butik3", "V�gen 2", "0707654321");
		assertEquals("V�gen 2", s.getAdress());
	}
	
	@Test
	public void testGetPhoneNbr(){
		Store s = new Store("Butik4", "V�gen 3", "0701122334");
		assertEquals("0701122334", s.getPhoneNbr());
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void testEmptyName(){
		Store s = new Store ("", "V�gen 4", "0702233445");
	}

}
