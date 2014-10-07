/** To be described
 * #BC141007#*/


public class SpecialOffer extends Discount{

	private String discountType;
	private int buyQuantity, getFreeQuantity;
	
	public SpecialOffer(String discountType, int startYear, int startMonth, int startDay,
			int endYear, int endMonth, int endDay) {
		super(startYear, startMonth, startDay, endYear, endMonth, endDay);
		
		discountTypeChecker(discountType);
		this.discountType = discountType;
		
	}


	/** Enforces the following rules for a valid discountType:
	 * a discountType must:
	 * 1. NOT to be NULL
	 * 2. NOT to be less than 3 chars or longer than 40 chars
	 * 3. BEGIN with at least 3 alpha-numerical chars*/
	private void discountTypeChecker(String discountType) {
		if(discountType == null || discountType.length() < 3 || discountType.length() > 40)
			throw new IllegalArgumentException("name cannot be null or less than 3 chars or more than 40 chars");
		if(!discountType.substring(0, 3).matches("[A-Za-z0-9]+"))
			throw new IllegalArgumentException("offerName has to begin with at least 3 alphanumerical chars");
	}
	
	
	public String getDiscountType(){
		return discountType;
	}
	
	public int getBuyQuantity(){
		return buyQuantity;
	}


	public void setBuyQuantity(int newBuyQuantity) {
		this.buyQuantity = newBuyQuantity;
	}
	
	private boolean buyQuantityIsSet(){
		return buyQuantity > 0;
	}
	
	public void setGetFreeQuantity(int getFreeQuantity){
		if(buyQuantityIsSet())
			this.getFreeQuantity = getFreeQuantity;
		else
			throw new IllegalStateException("a buyQuantity has to be set before a getFreeQuantity can be set");
	}
	
	public int getGetFreeQuantity(){
		return getFreeQuantity;
	}
	
	public String toString(){
		String result = "Identifier: " + discountType + "\n" + super.toString();
		result += "\nBuy " + buyQuantity + " Get " + getFreeQuantity;
		
		return result;
	}
	
	public boolean isOfferActive(){
		return buyQuantityIsSet() && getFreeQuantityIsSet();
	}
	
	private boolean getFreeQuantityIsSet(){
		return getFreeQuantity > 0;
	}

}
