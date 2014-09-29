
public class Receipt {
	private static String delimiter;
	
	public void setDelimiter(String delimiter) {
		this.delimiter = delimiter;
	}
	
	@Override
	public String toString() {
		return delimiter;
	}
}
