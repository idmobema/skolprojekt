
public class Receipt {
	// Alla kvitton använder samma "sidhuvud" och avgränsare, därför är de statiska
	private static String header = "";
	private static String delimiter = "";
	// Håller koll på antalet kvitton, används för att ta fram kvittonummer
	private static int numberOfReceipts = 0;
	
	private MockSale sale;
	private String saleInfo = "";
	private int receiptNo;
	
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
	
	public void setSaleInfo() {
		saleInfo = "Kassör: " + sale.getCashier() + "\n";
		saleInfo += sale.getDate() + " " + sale.getTime() + "\n";
	}
	
	public int getReceiptNo() {
		return receiptNo;
	}
	
	@Override
	public String toString() {
		String str = "";
		str += header;
		str += delimiter;
		str += saleInfo;
		return str;
	}
}
