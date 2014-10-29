import java.util.HashMap;
import java.util.Map;


public class Pos {
	public static void main(String[] args) {
		//Setup receipt
		Receipt.createHeader("Coop Konsum Midsommarkransen", "www.coop.se", "Tel.nr: 010-7411160");
		Receipt.createFooter("Tack för att du handlade på Coop!", "*** VÄLKOMMEN ÅTER! ***");
		Receipt.createDelimiter('-');
	
		//inventory: bar code to item mapping
		Map<String, Item> inventory = new HashMap<String, Item>();
		
		String kexchoklad = "12345678901"; 
		String butter = "12345678902";
		String milk = "12345678903";
		String pepparkakor ="7310521058507";
		
		
		inventory.put(kexchoklad, new Item(new ItemDescription("Kexchoklad", "", new Money(700, "SEK"), kexchoklad)));
		inventory.put(butter, new Item(new ItemDescription("Smör", "", new Money(2800, "SEK"), butter)));
		inventory.put(milk, new Item(new ItemDescription("Mjölk", "", new Money(1000, "SEK"), milk)));
		
		Discount disc = new PriceDiscount(10, 2014, 10, 29, 2015, 10, 29);
		Item item = new Item(new ItemDescription("Pepparkakor", "", new Money(2000, "SEK"), pepparkakor));
		item.setDiscount(disc);
		inventory.put(pepparkakor, item);
		
		//new customer
		Sale sale = new Sale();
		
		//purchase items "Beep!"
		sale.add(inventory.get(kexchoklad));
		sale.add(inventory.get(milk));
		sale.add(inventory.get(butter));
		sale.add(inventory.get(kexchoklad));
		sale.add(inventory.get(pepparkakor));
		sale.add(inventory.get(pepparkakor));
		
		//payment ?
		
		//print receipt
		System.out.print(new Receipt(sale).toString());
	}
}
