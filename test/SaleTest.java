import static org.junit.Assert.*;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;

import org.junit.Test;


public class SaleTest {
	
	@Test
	public void testAdd() {
		Sale sale = new Sale();
		Item kexchoklad = new Item(new ItemDescription("Kexchoklad", "", new Money(700, "SEK"), "12345678902"));
		sale.add(kexchoklad);
		for(SaleLineItem i : sale) {
			assertEquals(i.getItemName(), "Kexchoklad");
			
		}
		
	}
	
	@Test
	public void testAddTwo() {
		Sale sale = new Sale();
		Item kexchoklad = new Item(new ItemDescription("Kexchoklad", "", new Money(700, "SEK"), "12345678902"));
		sale.add(kexchoklad);
		sale.add(kexchoklad);
		for(SaleLineItem i : sale) {
			assertEquals(i.getQuantity(), 2);
		}
	}
	
	@Test
	public void testGetDate() {
		Sale sale = new Sale();
		Date date = new Date();
		DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		String dateStr = dateFormat.format(date);
		assertEquals(dateStr, sale.getDate());
		
	}
	
	@Test
	public void testGetTime() {
		Sale sale = new Sale();
		Date date = new Date();
		DateFormat dateFormat = new SimpleDateFormat("HH:mm:ss");
		String timeStr = dateFormat.format(date);
		assertEquals(timeStr, sale.getTime());
		
	}
	
	@Test
	public void testGetTotal() {
		Sale sale = new Sale();
		Item kexchoklad = new Item(new ItemDescription("Kexchoklad", "", new Money(700, "SEK"), "12345678902"));
		sale.add(kexchoklad);
		Item darjeling = new Item(new ItemDescription("Darjeling", "", new Money(4500, "SEK"), "12345678903"));
		sale.add(darjeling);
		assertEquals(new Money(5200, "SEK"), sale.getTotal());
	}
	
	@Test
	public void testGetCashier() {
		Sale sale = new Sale();
		assertEquals("Icander Coop", sale.getCashier());
	}
	
	@Test
	public void testLineOrder() {
		Sale sale = new Sale();
		ArrayList<Item> testList = new ArrayList<Item>();
		
		Item kexchoklad = new Item(new ItemDescription("Kexchoklad", "", new Money(700, "SEK"), "12345678903"));
		sale.add(kexchoklad);
		sale.add(kexchoklad);
		sale.add(kexchoklad);
		testList.add(kexchoklad);
		
		Item darjeling = new Item(new ItemDescription("Darjeling", "", new Money(4500, "SEK"), "12345678902"));
		sale.add(darjeling);
		testList.add(darjeling);
		
		Iterator<SaleLineItem> iter = sale.iterator();
		for(int i = 0 ; testList.size() > i ; ++i) {
			assertEquals(iter.next().getItemName(), testList.get(i).getItemDesc().getName());
		}
	}
	
	

}
