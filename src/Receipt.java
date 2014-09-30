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
	
	public static void setDelimiter(String delimiter) {
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
	
	public void addLine(int quantity, String itemName, double itemPrice, double subTotal) {
		if (quantity <= 0 || quantity > MAX_QUANTITY)
			throw new IllegalArgumentException("Invalid quantity: " + quantity + ". Value between 1 and " + MAX_QUANTITY + " required.");
		if (itemName.length() < MIN_NAME_LENGTH)
			throw new IllegalArgumentException("Invalid item name. Minimum " + MIN_NAME_LENGTH + " characters required.");
		if (itemName.length() > MAX_NAME_LENGTH)
			itemName = itemName.substring(0, MAX_NAME_LENGTH);
		if (itemPrice <= 0) 
			throw new IllegalArgumentException("Invalid item price: " + itemPrice + ". Price must not be negative.");
		if (subTotal <= 0) 
			throw new IllegalArgumentException("Invalid subtotal: " + subTotal + ". Value must not be negative.");
		
		String str = "";
		str += itemName + "\t" + String.format(Locale.US, "%.2f", subTotal) + "\n"; // Priset måste formateras till formatet XX.XX
		if (quantity > 1) // Om större kvantitet än 1, lägg till extrarad med prisuträkning
			str += "  " + quantity + "st X " + String.format(Locale.US, "%.2f", itemPrice) + "\n";
		lines.add(str);
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
