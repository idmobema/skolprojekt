import java.util.List;
import java.util.ArrayList;
import java.util.Locale;

public class Receipt {
	// Alla kvitton använder samma "sidhuvud", "sidfot" och avgränsare, därför är de statiska
	private static String header = "";
	private static String footer = "";
	private static String delimiter = "";
	// Håller koll på antalet kvitton, används för att ta fram kvittonummer
	private static int numberOfReceipts = 0;
	// Konstanter för gemensamma egenskaper
	public static final int MAX_QUANTITY = 999;
	public static final int MIN_NAME_LENGTH = 2;
	public static final int MAX_NAME_LENGTH = 20;
	public static final int LINE_WIDTH = 40;
	
	private Sale sale;
	private String saleInfo = "";
	private int receiptNo;
	private List<String> lines = new ArrayList<>();
	
	protected Receipt(Sale sale) {
		this.sale = sale;
		numberOfReceipts++;
		receiptNo = numberOfReceipts;
	}
	
	// Skapar kvittots "sidhuvud"
	protected static void createHeader(String ... headerStrings) {
		String header = "";
		for (String str : headerStrings)
			header += center(str) + "\n";
		Receipt.header = header;
	}
	
	// Skapar kvittots "sidfot"
	protected static void createFooter(String ... footerStrings) {
		String footer = "";
		for (String str : footerStrings)
			footer += center(str) + "\n";
		Receipt.footer = footer;		
	}
	
	// Hjälpmetod för att skapa rader med centrerad text
	private static String center(String str) {
		int leftSpace, rightSpace = 0;
		leftSpace = (LINE_WIDTH - str.length()) / 2;
		rightSpace = LINE_WIDTH - str.length() - leftSpace;
		return makeSpace(leftSpace) + str + makeSpace(rightSpace);
	}
	
	// Hjälpmetod för att skapa mellanrum
	private static String makeSpace(int spaces) {
		String str = "";
		for (int i = 0; i < spaces; i++)
			str += " ";
		return str;
	}
	
	// Skapar avgränsare mellan kvittots olika delar
	protected static void createDelimiter(char delimiterChar) {
		String delimiter = "";
		for (int i=0; i < LINE_WIDTH; i++)
			delimiter += delimiterChar;
		delimiter += "\n";
		Receipt.delimiter = delimiter;
	}
	
	// Skapar avsnitt med information om försäljningstillfället
	protected String createSaleInfo() {
		saleInfo = "Kvittonr: " + receiptNo + "\n";
		saleInfo += "Kassör: " + sale.getCashier() + "\n";
		saleInfo += sale.getDate() + " " + sale.getTime() + "\n";
		return saleInfo;
	}
	
	public int getReceiptNo() {
		return receiptNo;
	}
	
	// Lägger till rad med styckvara, tar priser i Money
	protected void addLine(int quantity, String itemName, Money itemPrice, Money subTotal) {
		if (quantity <= 0 || quantity > MAX_QUANTITY)
			throw new IllegalArgumentException("Invalid quantity: " + quantity + ". Value between 1 and " + MAX_QUANTITY + " required.");
		itemName = checkItemName(itemName);
		String str = formatLine(itemName, "" + subTotal.getAmount());
		if (quantity > 1) // Om större kvantitet än 1, lägg till extrarad med prisuträkning
			str += "  " + quantity + "st x " + itemPrice.getAmount() + "\n";
		lines.add(str);
	}
	
	// Lägger till rad med vägd vara
	protected void addLine(double weight, String itemName, double itemPrice, double subTotal) {
		if (weight <= 0) 
			throw new IllegalArgumentException("Invalid weight: " + weight + ". Value must be positive.");
		itemName = checkItemName(itemName);
		checkPrices(itemPrice, subTotal);
		String str = formatLine(itemName, formatPrice(subTotal));
		str += "  " + weight + "kg x " + formatPrice(itemPrice) + "\n";
		lines.add(str);
	}
	
	// Motsvarande metod som tar priser i Money
	protected void addLine(double weight, String itemName, Money itemPrice, Money subTotal) {
		if (weight <= 0) 
			throw new IllegalArgumentException("Invalid weight: " + weight + ". Value must be positive.");
		itemName = checkItemName(itemName);
		String str = formatLine(itemName, "" + subTotal.getAmount());
		str += "  " + weight + "kg x " + itemPrice.getAmount() + "\n";
		lines.add(str);
	}
	
	// Hjälpmetod för att kontrollera och eventuellt formatera varunamnet
	protected static String checkItemName(String itemName) {
		if (itemName.length() < MIN_NAME_LENGTH)
			throw new IllegalArgumentException("Invalid item name. Minimum " + MIN_NAME_LENGTH + " characters required.");
		if (itemName.length() > MAX_NAME_LENGTH)
			itemName = itemName.substring(0, MAX_NAME_LENGTH);
		return itemName;
	}
	
	// Hjälpmetod för att kontrollera styck- och totalpriser
	protected static void checkPrices(double itemPrice, double subTotal) {
		if (itemPrice <= 0) 
			throw new IllegalArgumentException("Invalid item price: " + itemPrice + ". Price must not be negative.");
		if (subTotal <= 0) 
			throw new IllegalArgumentException("Invalid subtotal: " + subTotal + ". Value must not be negative.");				
	}
	
	// Hjälpmetod för att formatera pris
	protected static String formatPrice(double price) {
		return String.format(Locale.US, "%.2f", price);
	}
	
	// Hjälpmetod för att formatera kvittorad, protected för att underlätta testning
	protected static String formatLine(String first, String second) {
		int numberOfSpaces = LINE_WIDTH - first.length() - second.length();		
		return first + makeSpace(numberOfSpaces) + second + "\n";
	}
	
	// Hjälpmetod för att formatera extrarad för rabatterade varor
	protected static String formatDiscountLine(String discountDescription, Money discountAmount) {
		return formatLine("  " + discountDescription, " -" + discountAmount);
	}

	protected void deleteLine(String itemName) {
		String lineToRemove = null;
		for (String line : lines) {
			if (line.contains(itemName))
				lineToRemove = line;
		}
		lines.remove(lineToRemove);
	}
	
	// Skapar avsnitt med totalpriset
	protected String createTotal() {
		String total = "";
		String priceString = "" + sale.getTotal();
		total += makeSpace(LINE_WIDTH - 10) + "==========\n";
		total += formatLine("TOTALT ATT BETALA", priceString);
		return total;
	}
	
	@Override
	public String toString() {
		String str = "";
		str += header;
		str += delimiter;
		str += createSaleInfo();
		str += delimiter;
		for (SaleLineItem sli : sale)
			str += sli.toString();
		for (String line : lines)
			str += line;
		str += createTotal();
		str += delimiter;
		str += footer;
		return str;
	}
}
