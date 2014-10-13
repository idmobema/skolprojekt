/** PricedPerPiece represents a concrete item. This can have either a SpecialOffer or a PriceDiscount discount or no discount.
 * 
 * Item with SpecialOffer type of discount:
 * The (final) unitPrice is calculated depending of the type of discount this item has/hasn't. By setting up a SpecialOffer discount 
 * for this object the next step is to call this discount's setBuyQuantity() and setGetFreeQuantity() in order to have a relevance
 * for the unitPrice's calculation. The unitPrice is then unitPrice / (buyQuantity + getFreeQuantity). The task for checking the number
 * of items of the same type bought in the same sale should be delegated to SaleLineItem or Receipt. However, if these discount fields
 * are not set the getUnitPrice() will return the unitPrice that was originally set in the PricedPerPiece() constructor.
 * 
 * Item with PriceDiscoun type of discount:
 * If the discount for this item is not set then the getUnitPrice() will return the initial unitPrice set with the constructor or the
 * setUnitPrice(). If the discount is set then unitPrice will be unitPrice - unitPrice / (100 / percentage).
 * 
 * The methods setSpecialOfferDiscount() and setPriceDiscount() will change the discount field accordingly, meaning that an instance of
 * this class can have only one type of discount at a time or none.
 * 
 * Helper methods to enforce the following rules:
 * - unitPrice NOT negative or zero
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

public class PricedPerPiece implements Item {

	private double unitPrice;
	private String unitID;
	private Discount discount;
	
	public PricedPerPiece(String unitID, double unitPrice){
		checkParametersValidity(unitID, unitPrice);
		this.unitID = unitID;
		this.unitPrice = unitPrice;
	}
	
	private void checkParametersValidity(String id, double unitPrice) {
		checkID(id);
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
		return unitID;
	}
	
	
	public double getUnitPrice() {
		if(getDiscount() == null)
			return unitPrice;
		if(getDiscount() instanceof SpecialOffer){
			return getUnitPriceWithSpecialOffer();
			
		} else
			return getUnitPriceWithPriceDiscount();

	}

	private double getUnitPriceWithPriceDiscount() {
		if(getDiscount() instanceof PriceDiscount){
			
			PriceDiscount pDiscount = (PriceDiscount) getDiscount();
			double percentage = pDiscount.getPercentage();
			return unitPrice -= unitPrice/ (100 / percentage);
		}
		else
			return unitPrice;
	}

	private double getUnitPriceWithSpecialOffer() {
		if(((SpecialOffer) getDiscount()).isOfferActive()){
			
			SpecialOffer sODiscount = (SpecialOffer) getDiscount();
			int buyQuantity = sODiscount.getBuyQuantity();
			int getFreeQuantity = sODiscount.getGetFreeQuantity();
		
			return unitPrice / (buyQuantity + getFreeQuantity);
		}
		else
			return unitPrice;
	}


	public Discount getDiscount() {
		return discount;
	}

	// Think about applicable (business) rules
	// e.g. What happens if the item already has a
	// discount and you set a new unitPrice. Will the discount be discarded, kept or modified?
	
	public void setUnitPrice(double newUnitPrice) {
		checkPrice(newUnitPrice);
		unitPrice = newUnitPrice;
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
		result = prime * result + ((unitID == null) ? 0 : unitID.hashCode());
		long temp;
		temp = Double.doubleToLongBits(unitPrice);
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
		if (unitID == null) {
			if (other.unitID != null)
				return false;
		} else if (!unitID.equals(other.unitID))
			return false;
		if (Double.doubleToLongBits(unitPrice) != Double
				.doubleToLongBits(other.unitPrice))
			return false;
		return true;
	}

	public String toString(){
		String result = "Item ID: " + unitID;
		result += "\nDiscount: " + (discount == null ? "None" : discount);
		result += "\nUnit Price: " + unitPrice;
		
		return result;
	}

}
