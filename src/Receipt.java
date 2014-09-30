import java.util.List;
import java.util.ArrayList;
import java.util.Locale;

public class Receipt {
	// Alla kvitton använder samma "sidhuvud" och avgränsare, därför är de statiska
	private static String header = "";
	private static String delimiter = "";
	// Håller koll på antalet kvitton, används för att ta fram kvittonummer
	private static int numberOfReceipts = 0;
	
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
		String str = "";
		str += itemName + "\t" + String.format(Locale.US, "%.2f", subTotal) + "\n"; // Priset måste formateras till formatet XX.XX
		if(quantity > 1)
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
