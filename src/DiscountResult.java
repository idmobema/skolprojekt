
public class DiscountResult {
	private String discountDescription;
	private Money discountAmount;
	
	public DiscountResult(String discountDescription, Money discountAmount) {
		this.discountDescription = discountDescription;
		this.discountAmount = discountAmount;
	}

	public String getDiscountDescription() {
		return discountDescription;
	}

	public Money getDiscountAmount() {
		return discountAmount;
	}

	

}
