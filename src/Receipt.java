import java.util.List;
import java.util.ArrayList;
import java.util.Locale;

public class Receipt {
	// Alla kvitton använder samma "sidhuvud" och avgränsare, därför är de statiska
	private static String header = "";
	private static String delimiter = "";
	// Håller koll på antalet kvitton, används för att ta fram kvittonummer
	private static int numberOfReceipts = 0;
	private static final int MAX_QUANTITY = 999;
	private static final int MIN_NAME_LENGTH = 2;
	private static final int MAX_NAME_LENGTH = 20;
	private static final int LINE_WIDTH = 40;
	
	private MockSale sale;
	private String saleInfo = "";
	private int receiptNo;
	private List<String> lines = new ArrayList<>();
	
	public Receipt(MockSale ms) {
		sale = ms;
		numberOfReceipts++;
		receiptNo = numberOfReceipts;
	}
	
	public static void setHeader(String header) {
		Receipt.header = header;
	}
	
	public static void createDelimiter(char delimiterChar) {
		String delimiter = "";
		for (int i=0; i < LINE_WIDTH; i++)
			delimiter += delimiterChar;
		delimiter += "\n";
		Receipt.delimiter = delimiter;
	}
	
	public void createSaleInfo() {
		saleInfo = "Kvittonr: " + receiptNo + "\n";
		saleInfo += "Kassör: " + sale.getCashier() + "\n";
		saleInfo += sale.getDate() + " " + sale.getTime() + "\n";
	}
	
	public int getReceiptNo() {
		return receiptNo;
	}
	
	// Lägger till rad med styckvara
	public void addLine(int quantity, String itemName, double itemPrice, double subTotal) {
		if (quantity <= 0 || quantity > MAX_QUANTITY)
			throw new IllegalArgumentException("Invalid quantity: " + quantity + ". Value between 1 and " + MAX_QUANTITY + " required.");
		itemName = checkItemName(itemName);
		checkPrices(itemPrice, subTotal);
		String str = formatLine(itemName, subTotal);
		if (quantity > 1) // Om större kvantitet än 1, lägg till extrarad med prisuträkning
			str += "  " + quantity + "st x " + String.format(Locale.US, "%.2f", itemPrice) + "\n";
		lines.add(str);
	}
	
	// Lägger till rad med vägd vara
	public void addLine(double weight, String itemName, double itemPrice, double subTotal) {
		if (weight <= 0) 
			throw new IllegalArgumentException("Invalid weight: " + weight + ". Value must be positive.");
		itemName = checkItemName(itemName);
		checkPrices(itemPrice, subTotal);
		String str = formatLine(itemName, subTotal);
		str += "  " + weight + "kg x " + String.format(Locale.US, "%.2f", itemPrice) + "\n";
		lines.add(str);
	}
	
	// Hjälpmetod för att kontrollera och eventuellt formatera varunamnet
	private String checkItemName(String itemName) {
		if (itemName.length() < MIN_NAME_LENGTH)
			throw new IllegalArgumentException("Invalid item name. Minimum " + MIN_NAME_LENGTH + " characters required.");
		if (itemName.length() > MAX_NAME_LENGTH)
			itemName = itemName.substring(0, MAX_NAME_LENGTH);
		return itemName;
	}
	
	// Hjälpmetod för att kontrollera styck- och totalpriser
	private void checkPrices(double itemPrice, double subTotal) {
		if (itemPrice <= 0) 
			throw new IllegalArgumentException("Invalid item price: " + itemPrice + ". Price must not be negative.");
		if (subTotal <= 0) 
			throw new IllegalArgumentException("Invalid subtotal: " + subTotal + ". Value must not be negative.");				
	}
	
	// Hjälpmetod för att formatera kvittorad
	private String formatLine(String itemName, double subTotal) {
		return itemName + "\t" + String.format(Locale.US, "%.2f", subTotal) + "\n"; // Priset måste formateras till formatet XX.XX		
	}
	
	@Override
	public String toString() {
		String str = "";
		str += header;
		str += delimiter;
		str += saleInfo;
		for (String line : lines)
			str += line;
		return str;
	}
}
