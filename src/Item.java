import java.math.BigDecimal;


public abstract class Item implements Comparable<Item>{
	double price;
	String name;
	String itemId;
	
	public Item(String name, String itemId, double price) {
		this.price = price;
		this.name = name;
		this.itemId = itemId;
		this.setUnitPrice(price);
	}
	
	public String getName() {
		return name;
	}
	
	public double getPrice() {
		return price;
	}
	
	public String getItemId() {
		return itemId;
	}
	
	
	@Override
	public int compareTo(Item other) {
		return other.itemId.compareTo(itemId);
	}
	

	abstract public void setSpecialOfferDiscount(String discountType, int startYear, int startMonth, int startDay,
			int endYear, int endMonth, int endDay);
	
	private Money unitPrice;
	private Money reducedPrice;
	private Discount discount;
	
	protected Item(String unitId, double unitPrice){
		checkParametersValidity(unitId, unitPrice);
		this.itemId = unitId;
		this.unitPrice = new Money(unitPrice, "SEK");
	}
	
	private void checkParametersValidity(String unitId, double unitPrice) {
		checkID(unitId);
		checkPrice(unitPrice);
	}

	
	
	private void checkID(String id) {
		if(id.length() != 11 || !id.matches("^[\\w]+"))
			throw new IllegalArgumentException("id has to be 11 alpha-numerical chars long");
	}

	private void checkPrice(double unitPrice) {
		if(unitPrice <= 0)
			throw new IllegalArgumentException("unitPrice has to be a positive double");
	}
	
	public String getID(){
		return itemId;
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
		Money result;
		
		if(discount instanceof PriceDiscount)
			result = computeRDWithPriceDiscount();	
		else
			result = null;
		
		return result;
	}

	private Money computeRDWithPriceDiscount() {
		Money result;
		
		double percentage = ((PriceDiscount) discount).getPercentage();
		BigDecimal bDDividend = unitPrice.getAmount();
		BigDecimal bDDivisor = new BigDecimal(Double.toString(100 / percentage));
		
		bDDividend = bDDividend.subtract(bDDividend.divide(bDDivisor, 2, BigDecimal.ROUND_UP));
		result = new Money(bDDividend.doubleValue(), "SEK");

		return result;
	}

	
	protected void setDiscount(Discount discount){
		if(this instanceof PricedPerWeight && discount instanceof SpecialOffer)
			return;
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


	@Override
	public String toString(){
		String result = "Item ID: " + itemId;
		result += "\nDiscount: " + (discount == null ? "None" : discount);
		result += "\nUnit Price: " + unitPrice;
		result += "\nReduced Price: " + (reducedPrice == null ? "none" : reducedPrice);
		
		return result;
	}


	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((discount == null) ? 0 : discount.hashCode());
		result = prime * result
				+ ((reducedPrice == null) ? 0 : reducedPrice.hashCode());
		result = prime * result + ((itemId == null) ? 0 : itemId.hashCode());
		result = prime * result
				+ ((unitPrice == null) ? 0 : unitPrice.hashCode());
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
		if (reducedPrice == null) {
			if (other.reducedPrice != null) {
				return false;
			}
		} else if (!reducedPrice.equals(other.reducedPrice)) {
			return false;
		}
		if (itemId == null) {
			if (other.itemId != null) {
				return false;
			}
		} else if (!itemId.equals(other.itemId)) {
			return false;
		}
		if (unitPrice == null) {
			if (other.unitPrice != null) {
				return false;
			}
		} else if (!unitPrice.equals(other.unitPrice)) {
			return false;
		}
		return true;
	}
}
