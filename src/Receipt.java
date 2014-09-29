
public class Receipt {
	// Alla kvitton använder samma "sidhuvud" och avgränsare, därför är de statiska
	private static String header;
	private static String delimiter;
	
	public static void setHeader(String header) {
		Receipt.header = header;
	}
	
	public static void setDelimiter(String delimiter) {
		Receipt.delimiter = delimiter;
	}
	
	@Override
	public String toString() {
		String str = "";
		str += header;
		str += delimiter;
		return str;
	}
}
