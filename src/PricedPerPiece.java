
public class PricedPerPiece implements Item {

	private double unitPrice;
	private String id;
	private Discount discount;
	
	public PricedPerPiece(String id, double unitPrice){
		checkParametersValidity(id, unitPrice);
		this.id = id;
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
		return id;
	}
	@Override
	public double getUnitPrice() {
		if(!((SpecialOffer) getDiscount()).isOfferActive())
			return unitPrice;
		
		Discount discount = getDiscount();
		int buyQuantity = ((SpecialOffer) discount).getBuyQuantity();
		int getFreeQuantity = ((SpecialOffer) discount).getGetFreeQuantity();
		
		return unitPrice / (buyQuantity + getFreeQuantity);
	}



	@Override
	public Discount getDiscount() {
		return discount;
	}

	@Override
	public void setUnitPrice(double newUnitPrice) {
		checkPrice(newUnitPrice);
		unitPrice = newUnitPrice;
	}

	@Override
	public void setSpecialOfferDiscount(String discountType, int startYear, int startMonth, int startDay,
			int endYear, int endMonth, int endDay) {
		discount = new SpecialOffer(discountType, startYear, startMonth, startDay, endYear, endMonth, endDay);

	}

}
