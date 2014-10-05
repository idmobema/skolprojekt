/** An item might or might not have a discount. This simple class is thought to hold the percentage
 * (in the range of 1 to 75) and a "valid through" fields that is allocated to TimeValidity
 * class. Rules regarding the time validity of a discount are to be found in TimeValidity and
 * TimeValidityDatesChecker classes.
 * #BC141005# */
public class Discount {

	private double percentage;
	private TimeValidity timeValidity;
	
	public Discount(double percentage, int startYear, int startMonth, int startDay, int endYear, int endMonth, int endDay){
	
		checkPercentage(percentage);	
		
		this.percentage = percentage;
		timeValidity = new TimeValidity(startYear, startMonth,startDay, endYear, endMonth, endDay);

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
	
	public TimeValidity getTimeValidity(){
		return timeValidity;
	}
	
	public void setTimeValidity (int startYear, int startMonth, int startDay, int endYear, int endMonth, int endDay){
		this.timeValidity = new TimeValidity(startYear, startMonth, startDay, endYear, endMonth, endDay);
	}
	
	public String toString(){
		String result = "";
		
		result += "Percentage: " + percentage + "\n";
		result += "Valid through: " + timeValidity;
		
		return result;
	}
	
}
