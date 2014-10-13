/** Simple concrete class. The only rule imposed by this class is that the price discount percentage to be in the range of 1 - 75.
 * Other rules regarding the discount time validity (how long this discount is available) are to be found in TimeValidity and 
 * TimeValidityDatesChecker classes.
 * 
 * #BC141007*/

public class PriceDiscount extends Discount{


	public static final String DISCOUNT_NAME = "Price Reduction";
	private double percentage;
	
	
	public PriceDiscount(double percentage, int startYear, int startMonth, int startDay,
			int endYear, int endMonth, int endDay) {
		
		super(startYear, startMonth, startDay, endYear, endMonth, endDay);
		
		checkPercentage(percentage);
		this.percentage = percentage;
	}
	
	private void checkPercentage(double percentage) {
		if(percentage >= 1 && percentage <= 75)
			return;
		else
			throw new IllegalArgumentException("Percentage must be in the range 1 to 75 inclusive.");
	}
	
	public double getPercentage(){
		return percentage;
	}
	
	public void setPercentage(double percentage){
		
			checkPercentage(percentage);
			this.percentage = percentage;
	}
	
	@Override
	public boolean equals(Object obj){
		if(!(obj instanceof PriceDiscount) || obj == null || !(((PriceDiscount) obj).getOfferName().equals("Price Reduction")))
			return false;
		
		PriceDiscount another = (PriceDiscount) obj;
		boolean sameTimeValidity = this.getTimeValidity().equals(another.getTimeValidity());
		boolean samePercentage = percentage == another.getPercentage();
		
		return sameTimeValidity && samePercentage;
	}
	
	@Override
	public int hashCode(){
		int hash = 1;
		
		hash = hash * 7 + (int)(percentage * 10);
		hash = hash * 13 + (DISCOUNT_NAME == null ? 0 : DISCOUNT_NAME.hashCode());
		hash = hash * 19 + (getTimeValidity() == null ? 0 : getTimeValidity().hashCode());
		
		return hash;
				
	}
	

	@Override
	public String getOfferName() {
		
		return DISCOUNT_NAME;
	}

	
	public String toString(){
		String result = DISCOUNT_NAME + "\n" + super.toString();
		result += "\nPercentage: " + percentage;
		
		return result;
	}
}
