/** An item might or might not have a discount. This simple class is thought to hold
 * a "valid through" field that is an instantiation of the TimeValidity class.
 * Rules regarding the time validity of a discount are to be found in TimeValidity and
 * TimeValidityDatesChecker classes.
 * 
 * #BC141005# */
public abstract class Discount{

	
	private TimeValidity timeValidity;
	
	protected Discount( int startYear, int startMonth, int startDay, int endYear, int endMonth, int endDay){
	
		timeValidity = new TimeValidity(startYear, startMonth,startDay, endYear, endMonth, endDay);

	}
	
	public TimeValidity getTimeValidity(){
		return timeValidity;
	}
	
	public void setTimeValidity (int startYear, int startMonth, int startDay, int endYear, int endMonth, int endDay){
		this.timeValidity = new TimeValidity(startYear, startMonth, startDay, endYear, endMonth, endDay);
	}
	


	public abstract String getOfferName();
	
	
	@Override
	public boolean equals(Object obj){
		
		if(! (obj instanceof Discount) || obj == null)
			return false;
		Discount other = (Discount) obj;

		if(this.getTimeValidity() == other.timeValidity && this.getOfferName().equals(other.getOfferName()))
			return true;
		else
			return false;
	}
	
	@Override
	public int hashCode(){
		int hash = 1;
		hash = hash * 13 + (timeValidity == null ? 0 : timeValidity.hashCode());
		return hash;
	}
	
	public String toString(){
		
		return "Valid through:\n" + timeValidity;
	}
}
