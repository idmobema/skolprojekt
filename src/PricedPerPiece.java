/** PricedPerPiece represents a concrete item. This can have either a SpecialOffer or a PriceDiscount discount or no discount.
 * 
 * Item with SpecialOffer type of discount:
 * The (final) price is calculated depending of the type of discount this item has/hasn't. By setting up a SpecialOffer discount 
 * for this object the next step is to call this discount's setBuyQuantity() and setGetFreeQuantity() in order to have a relevance
 * for the price's calculation. The price is then price / (buyQuantity + getFreeQuantity). The task for checking the number
 * of items of the same type bought in the same sale should be delegated to SaleLineItem or Receipt. However, if these discount fields
 * are not set the getprice() will return the price that was originally set in the PricedPerPiece() constructor.
 * 
 * Item with PriceDiscoun type of discount:
 * If the discount for this item is not set then the getprice() will return the initial price set with the constructor or the
 * setprice(). If the discount is set then price will be price - price / (100 / percentage).
 * 
 * The methods setSpecialOfferDiscount() and setPriceDiscount() will change the discount field accordingly, meaning that an instance of
 * this class can have only one type of discount at a time or none.
 * 
 * Helper methods to enforce the following rules:
 * - price NOT negative or zero
 * - unitID 11 alpha-numerical chars long.
 * Other methods that are called by discount are enforcing rules applicable to TimeValidity.
 * 
 * Comments on the field unitID:
 * The first three chars would represent the product category. E.g. "Mej12ARL051". Mej would designate the Mejeri category.
 * The next two chars would represent the quantity left on the shelf or deposit. Here 12 left. Might be a warning to start to refill.
 * The next three chars would be a manufacturer code. Here ARL Arla.
 * The last three chars would be this products unique identifier among other products from the same manufacturer. E.g. 051 would repre-
 * sent the milk with 3.5 % fat. 035 would be a yoghurt with vanilla from the same manufacturer like this: Mej12ARL035
 * 
 * unitID is REDUNDANT if this sold unit's data is not send to a database.
 * 
 * #BC141013# */

public class PricedPerPiece extends Item {

	private Discount discount;
	
	public PricedPerPiece(String name, String unitID, double price){
		super(name, unitID, price);
		checkParametersValidity(unitID, price);
		this.itemId = unitID;
		this.price = price;
	}
	
	private void checkParametersValidity(String id, double price) {
		checkID(id);
		checkPrice(price);
	}

	private void checkID(String id) {
		if(id.length() != 11 || !id.matches("^[\\w]+") )
			throw new IllegalArgumentException("id has to be 11 alpha-numerical chars long");
	}

	private void checkPrice(double price) {
		if(price <= 0)
			throw new IllegalArgumentException("price has to be a positive double");
	}
	

	public String getID(){
		return itemId;
	}
	
	
	public double getUnitPrice() {
		if(getDiscount() == null)
			return price;
		if(getDiscount() instanceof SpecialOffer){
			return getpriceWithSpecialOffer();
			
		} else
			return getpriceWithPriceDiscount();

	}

	private double getpriceWithPriceDiscount() {
		if(getDiscount() instanceof PriceDiscount){
			
			PriceDiscount pDiscount = (PriceDiscount) getDiscount();
			double percentage = pDiscount.getPercentage();
			return price -= price/ (100 / percentage);
		}
		else
			return price;
	}

	private double getpriceWithSpecialOffer() {
		if(((SpecialOffer) getDiscount()).isOfferActive()){
			
			SpecialOffer sODiscount = (SpecialOffer) getDiscount();
			int buyQuantity = sODiscount.getBuyQuantity();
			int getFreeQuantity = sODiscount.getGetFreeQuantity();
		
			return price / (buyQuantity + getFreeQuantity);
		}
		else
			return price;
	}


	public Discount getDiscount() {
		return discount;
	}

	// Think about applicable (business) rules
	// e.g. What happens if the item already has a
	// discount and you set a new price. Will the discount be discarded, kept or modified?
	
	public void setUnitPrice(double newprice) {
		checkPrice(newprice);
		price = newprice;
	}

	
	public void setSpecialOfferDiscount(String discountType, int startYear, int startMonth, int startDay,
			int endYear, int endMonth, int endDay) {
		discount = new SpecialOffer(discountType, startYear, startMonth, startDay, endYear, endMonth, endDay);

	}
	
	
	public void setPriceDiscount(double percentage, int startYear, int startMonth, int startDay,
			int endYear, int endMonth, int endDay){
		discount = new PriceDiscount(percentage, startYear, startMonth, startDay, endYear, endMonth, endDay);
	}
	
	
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((discount == null) ? 0 : discount.hashCode());
		result = prime * result + ((itemId == null) ? 0 : itemId.hashCode());
		long temp;
		temp = Double.doubleToLongBits(price);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		PricedPerPiece other = (PricedPerPiece) obj;
		if (discount == null) {
			if (other.discount != null)
				return false;
		} else if (!discount.equals(other.discount))
			return false;
		if (itemId == null) {
			if (other.itemId != null)
				return false;
		} else if (!itemId.equals(other.itemId))
			return false;
		if (Double.doubleToLongBits(price) != Double
				.doubleToLongBits(other.price))
			return false;
		return true;
	}

	public String toString(){
		String result = "Item ID: " + itemId;
		result += "\nDiscount: " + (discount == null ? "None" : discount);
		result += "\nUnit Price: " + price;
		
		return result;
	}

}
