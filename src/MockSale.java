
public class MockSale {
	public String getDate() {
		return "2014-09-29";
	}
	
	public String getTime() {
		return "21:11:43";
	}
	
	public String getCashier() {
		return "Roger";
	}
	
	public Money getTotal() {
		return Money.getCrowns(606.56);
	}
}
