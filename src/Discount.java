
public class Discount {

	private double percentage;
	
	public Discount(double percentage){
		try{
			checkPercentage(percentage);
			this.percentage = percentage;
		}
		catch(IllegalArgumentException iae){
			System.out.println("Percentage has to be between 0 and 75 inclusive.");
		}
		
		
	}

	private void checkPercentage(double percentage) {
		if(percentage < 0 || percentage > 75)
			throw new IllegalArgumentException ();
	}
	
	public double getPercentage(){
		return percentage;
	}
	
	public void setPercentage(double percentage){
		try{
			checkPercentage(percentage);
			this.percentage = percentage;
		}
		catch(IllegalArgumentException iae){
			System.out.println("Percentage has to be between 0 and 75 inclusive.");
		}
	}
}
