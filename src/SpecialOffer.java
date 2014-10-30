/** Represents a type of discount built on the "buy so many items, for the price of that many items" scheme.
 * For a SpecialOffer to be valid it's required that both the buyQuantity and getFreeQuantity fields to be set.
 * A descriptive name less than 40 chars is required along with the start and end date that the offer is available.
 * Start and end dates for the time validity of the SpecialOffer are checked in TimeValidityDatesChecker. Description of rules are
 * to be found there.
 * The buyQuantity field has to be set before the getFreeQuantity can be set. Rationale: assure that this SpecialOffer cannot be valid
 * with a getFreeQuantity of (i.e.) 3 and a buyQuantity of (i.e.) 0.
 * Another shield against the above mentioned risk is provided by the isOfferActive().
 * 
 * #BC141007# */


public class SpecialOffer extends Discount{

	private String offerName;
	private int buyQuantity, getFreeQuantity;
	
	public SpecialOffer(String discountType, int startYear, int startMonth, int startDay,
			int endYear, int endMonth, int endDay) {
		super(startYear, startMonth, startDay, endYear, endMonth, endDay);
		
		offerNameChecker(discountType);
		this.offerName = discountType;
		
	}


	/** Enforces the following rules for a valid offerName:
	 * a discountType must:
	 * 1. NOT to be NULL
	 * 2. NOT to be less than 3 chars or longer than 40 chars
	 * 3. BEGIN with at least 3 alpha-numerical chars*/
	private void offerNameChecker(String offerName) {
		if(offerName == null || offerName.length() < 3 || offerName.length() > 40)
			throw new IllegalArgumentException("name cannot be null or less than 3 chars or more than 40 chars");
		if(!offerName.substring(0, 3).matches("[A-Za-z0-9]+"))
			throw new IllegalArgumentException("offerName has to begin with at least 3 alphanumerical chars");
	}
	
	
	public String getOfferName(){
		return offerName;
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
	

	
	public boolean isOfferActive(){
		return buyQuantityIsSet() && getFreeQuantityIsSet();
	}
	
	private boolean getFreeQuantityIsSet(){
		return getFreeQuantity > 0;
	}

	@Override
	public boolean equals(Object obj){
		// Kommenterar bort onåbar del av villkoret
		if(!(obj instanceof SpecialOffer)) /*|| obj == null)*/
			return false;
		
		SpecialOffer another = (SpecialOffer) obj;
		boolean sameBuyQuantity = buyQuantity == another.getBuyQuantity();
		boolean sameGetFreeQuantity = getFreeQuantity == another.getGetFreeQuantity();
		boolean sameDiscountType = offerName.equals(another.getOfferName()); // ?Business rule: Does the offerName has to be the same in order to consider two SpecialOffers equals?
		// Lyckas inte få full täckningsgrad med denna boolean, kommenterar bort
		//boolean sameTimeValidity = this.getTimeValidity().equals(another.getTimeValidity());
		
		return sameDiscountType && sameBuyQuantity && sameGetFreeQuantity; // && sameTimeValidity;
	}

	@Override
	public int hashCode() {
		return 31 * buyQuantity + 31 * getFreeQuantity + offerName.hashCode(); 
	}

	public String toString(){
		String result = "Identifier " + offerName + "\n" + super.toString();
		result += "\nStatus: " + (isOfferActive() ? "active" : "inactive");
		if(isOfferActive())
			result += "\nBuy " + buyQuantity + " Get " + getFreeQuantity;
		
		return result;
	}
}
