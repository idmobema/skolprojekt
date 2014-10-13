
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
	
	@Override
	public String getID(){
		return unitID;
	}
	
	
	@Override
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
			return unitPrice / (100 / percentage);
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



	@Override
	public Discount getDiscount() {
		return discount;
	}

	// Think about applicable (business) rules
	// e.g. What happens if the item has already a
	// discount and you set a new unitPrice
	@Override
	public void setUnitPrice(double newUnitPrice) {
		checkPrice(newUnitPrice);
		unitPrice = newUnitPrice;
	}

	
	public void setSpecialOfferDiscount(String discountType, int startYear, int startMonth, int startDay,
			int endYear, int endMonth, int endDay) {
		discount = new SpecialOffer(discountType, startYear, startMonth, startDay, endYear, endMonth, endDay);

	}
	
	@Override
	public void setPriceDiscount(double percentage, int startYear, int startMonth, int startDay,
			int endYear, int endMonth, int endDay){
		discount = new PriceDiscount(percentage, startYear, startMonth, startDay, endYear, endMonth, endDay);
	}

}
