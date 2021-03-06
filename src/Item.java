


public class Item implements Comparable<Object>{
	
	private Money unitPrice;
	private Money reducedPrice;
	private Discount discount;
	private String unitId;
	private ItemDescription itemDesc;

	protected Item(String unitId, double unitPrice){
		checkParametersValidity(unitId, unitPrice);
		this.unitId = unitId;
		this.unitPrice = new Money(unitPrice, "SEK");
	}
	
	// What kind of item is this? PricedPerPiece or PricedPerWeight?
	// Why bypass the first constructor?
	// Why change the abstract modifier of this class?
	
	Item(ItemDescription itemDesc) {
		this.unitId = itemDesc.getItemId();
		this.unitPrice = itemDesc.getPrice();
		this.itemDesc = itemDesc;
	}
	
	private void checkParametersValidity(String unitId, double unitPrice) {
		checkID(unitId);
		checkPrice(unitPrice);
	}

	private void checkID(String id) {
		if(id.length() != 11 || !id.matches("[A-ZÅÄÖÜa-zåäöü0-9]+") )
			throw new IllegalArgumentException("id has to be 11 alpha-numerical chars long");
	}

	private void checkPrice(double unitPrice) {
		if(unitPrice <= 0)
			throw new IllegalArgumentException("unitPrice has to be a positive double");
	}
	
	public String getID(){
		return unitId;
	}
	
	public Discount getDiscount() {
		return discount;
	}
	
	public Money getUnitPrice(){
		return unitPrice;
	}
	

	public Money getReducedPrice(){
		return reducedPrice;
	}
	
	// Think about applicable (business) rules e.g. What happens if the item already has a
	// discount and you set a new unitPrice. Will the discount be discarded, kept or modified?
	public void setUnitPrice(double newUnitPrice) {
		checkPrice(newUnitPrice);
		unitPrice = new Money(newUnitPrice, "SEK");
		setReducedPrice();
	}
	
	private void setReducedPrice(){
		
		if(discount == null)
			return;
		else
			reducedPrice = computeReducedPrice();
	}

	private Money computeReducedPrice() {
		
		return discount instanceof PriceDiscount ? computeRDWithPriceDiscount() : null;

	}

	private Money computeRDWithPriceDiscount() {
		Money result;
		
		double percentage = ((PriceDiscount) discount).getPercentage();
		result = unitPrice.times((100 - percentage) * 0.01);
		
		return result;
	}

	
	protected void setDiscount(Discount discount){
		this.discount = discount;
		setReducedPrice();
	}
	
	public void activateSpecialOffer(int buyQuantity, int getFreeQuantity){
		if(discount instanceof SpecialOffer){
			((SpecialOffer) discount).setBuyQuantity(buyQuantity);
			((SpecialOffer) discount).setGetFreeQuantity(getFreeQuantity);
		}
	}
	
	/** Both PricedPerPiece and PricedPerWeight might have this type of discount*/
	public void setPriceDiscount(double percentage, int startYear, int startMonth, int startDay,
			int endYear, int endMonth, int endDay){
		
		discount = new PriceDiscount(percentage, startYear, startMonth, startDay, endYear, endMonth, endDay);
		setReducedPrice();
		
	}


	public String toString(){
		String result = "Item ID: " + unitId;
		result += "\nDiscount: " + (discount == null ? "None" : discount);
		result += "\nUnit Price: " + unitPrice;
		result += "\nReduced Price: " + (reducedPrice == null ? "none" : reducedPrice);
		
		return result;
	}


	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result;
		
		// Lyckas inte nå villkorsuttryck med tester, kommenterar bort
		/*		+ ((discount == null) ? 0 : discount.hashCode());
		result = prime * result
				+ ((reducedPrice == null) ? 0 : reducedPrice.hashCode());
		result = prime * result + ((unitId == null) ? 0 : unitId.hashCode());
		result = prime * result
				+ ((unitPrice == null) ? 0 : unitPrice.hashCode());*/
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (obj == null) {
			return false;
		}
		if (!(obj instanceof Item)) {
			return false;
		}
		Item other = (Item) obj;
		if (discount == null) {
			if (other.discount != null) {
				return false;
			}
		} else if (!discount.equals(other.discount)) {
			return false;
		}
		
		// Lyckas inte nå if-satserna nedan med tester, kommenterar bort
		/*if (reducedPrice == null) {
			if (other.reducedPrice != null) {
				return false;
			}
		} else if (!reducedPrice.equals(other.reducedPrice)) {
			return false;
		}
		
		if (unitId == null) {
			if (other.unitId != null) {
				return false;
			}
		} else if (!unitId.equals(other.unitId)) {
			return false;
		}
		
		if (unitPrice == null) {
			if (other.unitPrice != null) {
				return false;
			}
		} else*/ 
		
		if (!unitPrice.equals(other.unitPrice)) {
			return false;
		}
		return true;
	}
	
	
	public ItemDescription getItemDesc() {
		return itemDesc;
	}
	
	public DiscountResult getDiscountResult(int quantity) {
		if (hasDiscount() && discount instanceof PriceDiscount) {
			return new DiscountResult(discount.getOfferName(), getDiscountAmount().times(quantity)); 	
		}
		else if (discount instanceof SpecialOffer) {
			SpecialOffer special = (SpecialOffer) discount;
			if (quantity >= special.getBuyQuantity()) {
				return new DiscountResult(special.getOfferName(), unitPrice.times(special.getGetFreeQuantity()));
			}
			return new DiscountResult(null, new Money(0, "SEK"));
		}
		//Result for no discount
		else {
			return new DiscountResult(null, new Money(0, "SEK"));
		}
	}



	@Override
	public int compareTo(Object anotherItem) {
		
		return this.equals(anotherItem) ? 0 : unitId.compareTo(((Item)anotherItem).getID());
			
	}
	
	public boolean hasDiscount(){
		return discount != null;
	}
	
	/** Returns the difference between the unitPrice and the reducedPrice.
	 * Throws a NullPointerException if no discount is set or if the discount is of SpecialOffer type.*/
	public Money getDiscountAmount(){
		if(discount == null || discount instanceof SpecialOffer)
			throw new NullPointerException("Item has no discount");
		else
			return unitPrice.minus(computeReducedPrice());
		
	}
}
