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
		
		
		inventory.put(kexchoklad, new Item("Kexchoklad", kexchoklad, 7));
		inventory.put(butter, new Item("Butter", butter, 29));
		inventory.put(milk, new Item("Milk", milk, 10));
		
		//new customer
		Sale sale = new Sale();
		
		//purchase items "Beep!"
		sale.add(inventory.get(kexchoklad));
		sale.add(inventory.get(milk));
		sale.add(inventory.get(butter));
		sale.add(inventory.get(kexchoklad));
		
		//payment ?
		
		//print receipt
		System.out.print(new Receipt(sale).toString());
	}
}
