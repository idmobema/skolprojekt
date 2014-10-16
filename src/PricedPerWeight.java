
public class PricedPerWeight extends Item{

	private String measureUnit;
	
	public PricedPerWeight(String unitId, double unitPrice, String measureUnit){
		super(unitId, unitPrice);
		checkMeasureUnit(measureUnit);
		this.measureUnit = measureUnit;
	}

	private void checkMeasureUnit(String measureUnit) {
		if(measureUnit.length() < 1 || ! measureUnit.substring(0, 1).matches("[A-Z����a-z����]+"))
			throw new IllegalArgumentException("Wrong measure unit.");
	}
	
	public String getMeasureUnit(){
		return measureUnit;
	}
	
	public void setMeasureUnit(String measureUnit){
		this.measureUnit = measureUnit;
	}
	
	/** This type of Item CANNOT have other type of Discount than PriceDiscount. */
	@Override
	public void setDiscount(Discount discount){
		if(discount instanceof SpecialOffer)
			throw new IllegalArgumentException("this type of product cannot have the SpecialOffer type of discount");
		super.setDiscount(discount);
	}

	@Override
	public void setSpecialOfferDiscount(String discountType, int startYear,
			int startMonth, int startDay, int endYear, int endMonth, int endDay) {
		// TODO Auto-generated method stub
		
	}
	
}
