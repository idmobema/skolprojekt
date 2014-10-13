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
	
	@Override
	public boolean equals(Object obj){
		if(!(obj instanceof PriceDiscount) || obj == null || !(((PriceDiscount) obj).getDiscountType().equals("Price Reduction")))
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
		hash = hash * 13 + (DISCOUNT_TYPE == null ? 0 : DISCOUNT_TYPE.hashCode());
		hash = hash * 19 + (getTimeValidity() == null ? 0 : getTimeValidity().hashCode());
		
		return hash;
				
	}
	

	@Override
	public String getDiscountType() {
		
		return DISCOUNT_TYPE;
	}

	
	public String toString(){
		String result = DISCOUNT_TYPE + "\n" + super.toString();
		result += "\nPercentage: " + percentage;
		
		return result;
	}
}
