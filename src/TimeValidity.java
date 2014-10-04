/** TimeValidity is top level class coupled to the Discount class. (Most) Usually a discount has a period of 
 * validity. The setter for the fields are private in order to simplify the rules of changing the validity
 * period.
 * For example suppose that the validity for a certain discount is from 5th of Nov '14 to 10th of Dec '14.
 * Suppose also that the setters for the fields are public and You want to set the validity's startDay to 31.
 * So timeValidity.setStartDay(31)
 * By treating the setters publicly and individually in this step You'll have to check that the startMonth Nov
 * has 31 days, which it hasn't. More so complicated when the startMonth is Feb. Forwardly consider that
 * knowing now this information You'll try to set the startDay to 30th of Nov. Somewhere else in the system a
 * call to setStartMonth(1) occurs. (month 1 is Feb). Another conflict appears: the start date will have 30 as
 * startDay and 1(Feb) as startMonth.
 * Adding to this more complexities that come with leap years it is just justifiable to make the setters for
 * the fields private and have instead a common public changePeriod(startYear, startMonth, startDay, endYear,
 * endMonth, endDay) that will reset the current timeValidity to the new parameters. Clearly the reset will
 * undergo as the constructor does a series of checks that enforce the rules of the time validity mentioned
 * in the class' description of TimeValidityDatesChecker.java. 
 * 
 * #BC141004#*/


public class TimeValidity {
	

	private int startDay, startMonth, startYear, endDay, endMonth, endYear;
	
	
	/** Constructor */
	public TimeValidity(int startYear, int startMonth, int startDay, int endYear, int endMonth, int endDay){
		TimeValidityDatesChecker datesChecker = new TimeValidityDatesChecker();
		datesChecker.checkDates(startYear, startMonth, startDay, endYear, endMonth, endDay);
		
		this.startDay = startDay; this.startMonth = startMonth; this.startYear = startYear;
		this.endDay = endDay; this.endMonth = endMonth; this.endYear = endYear;
	}

	public int getStartDay() {
		return startDay;
	}

	public int getStartMonth() {
		return startMonth;
	}

	public int getStartYear() {
		return startYear;
	}

	public int getEndDay() {
		return endDay;
	}

	public int getEndMonth() {
		return endMonth;
	}

	public int getEndYear() {
		return endYear;
	}

	private void setStartDay(int startDay) {
		this.startDay = startDay;
	}

	private void setStartMonth(int startMonth) {
		this.startMonth = startMonth;
	}

	private void setStartYear(int startYear) {
		this.startYear = startYear;
	}

	private void setEndDay(int endDay) {
		this.endDay = endDay;
	}

	private void setEndMonth(int endMonth) {
		this.endMonth = endMonth;
	}

	private void setEndYear(int endYear) {
		this.endYear = endYear;
	}

	/** Changes the discount's time validity under the rules stipulated in TimeValidityDatesChecker.java */
	public void changePeriod(int startYear, int startMonth, int startDay, int endYear, int endMonth, int endDay){
		TimeValidityDatesChecker datesChecker = new TimeValidityDatesChecker();
		datesChecker.checkDates(startYear, startMonth, startDay, endYear, endMonth, endDay);
		
		setStartPeriod(startYear, startMonth, startDay);
		setEndPeriod(endYear, endMonth, endDay);
	}

	private void setStartPeriod(int startYear, int startMonth, int startDay) {
		
		setStartYear(startYear); 
		setStartMonth(startMonth); 
		setStartDay(startDay);
	}
	
	private void setEndPeriod(int endYear, int endMonth, int endDay) {
		setEndYear(endYear); 
		setEndMonth(endMonth); 
		setEndDay(endDay);
	}

	/** The startMonth and endMonth values to be read as +1. */
	public String toString(){
		String result = "";
		result += "Start date: " + startYear + "-" + startMonth + "-" + startDay + "\n";
		result += "End date: " + endYear + "-" + endMonth + "-" + endDay;
		
		return result;
	}
}
