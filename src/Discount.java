
public class Discount {

	private double percentage;
	private TimeValidity timeValidity;
	
	public Discount(double percentage, TimeValidity timeValidity){
	
		checkPercentage(percentage);	
		
		this.percentage = percentage;
		this.timeValidity = timeValidity;

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
}
