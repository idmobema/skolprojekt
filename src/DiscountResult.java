
public class DiscountResult {
	private String discountDescription;
	private Money discountAmount;
	
	public DiscountResult(String discountDescription, Money discountAmount) {
		this.discountDescription = discountDescription;
		this.discountAmount = discountAmount;
	}

	public Money getDiscountAmount() {
		return discountAmount;
	}
	
	public String toString() {
		if (discountDescription != null) {
			return Receipt.formatDiscountLine(discountDescription, discountAmount);
		}
		else {
			return "";
		}
	}
}
