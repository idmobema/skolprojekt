/** To be described
 * #BC141007*/
public class PriceDiscount extends Discount{


	public static final String DISCOUNT_TYPE = "Price Reduction";
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
	
	public String toString(){
		String result = DISCOUNT_TYPE + "\n" + super.toString();
		result += "\nPercentage: " + percentage;
		
		return result;
	}

	@Override
	public String getDiscountType() {
		
		return DISCOUNT_TYPE;
	}

}
