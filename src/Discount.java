/** An item might or might not have a discount. This simple class is thought to hold
 * and a "valid through" fields that is allocated to TimeValidity class.
 * Rules regarding the time validity of a discount are to be found in TimeValidity and
 * TimeValidityDatesChecker classes.
 * 
 * #BC141005# */
public abstract class Discount {

	
	private TimeValidity timeValidity;
	
	public Discount( int startYear, int startMonth, int startDay, int endYear, int endMonth, int endDay){
	
		timeValidity = new TimeValidity(startYear, startMonth,startDay, endYear, endMonth, endDay);

	}
	
	public TimeValidity getTimeValidity(){
		return timeValidity;
	}
	
	public void setTimeValidity (int startYear, int startMonth, int startDay, int endYear, int endMonth, int endDay){
		this.timeValidity = new TimeValidity(startYear, startMonth, startDay, endYear, endMonth, endDay);
	}
	
	public String toString(){
		
		return "Valid through:\n" + timeValidity;
	}

	public abstract String getDiscountType();
	
}
