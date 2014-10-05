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


import java.util.Calendar;
import java.util.GregorianCalendar;

public class TimeValidity implements Comparable<TimeValidity>{
	

	private GregorianCalendar startDate, endDate;
	
	
	/** Constructor */
	public TimeValidity(int startYear, int startMonth, int startDay, int endYear, int endMonth, int endDay){

		TimeValidityDatesChecker datesChecker = new TimeValidityDatesChecker();
		datesChecker.checkDates(startYear, startMonth, startDay, endYear, endMonth, endDay);
		
		startDate = new GregorianCalendar(startYear , startMonth, startDay);
		endDate = new GregorianCalendar(endYear, endMonth, endDay);

	}

	public int getStartDay() {
		return startDate.get(Calendar.DAY_OF_MONTH);
	}

	public int getStartMonth() {
		return startDate.get(Calendar.MONTH);
	}

	public int getStartYear() {
		return startDate.get(Calendar.YEAR);
	}

	public int getEndDay() {
		return endDate.get(Calendar.DAY_OF_MONTH);
	}

	public int getEndMonth() {
		return endDate.get(Calendar.MONTH);
	}

	public int getEndYear() {
		return endDate.get(Calendar.YEAR);
	}

	private void setStartDay(int startDay) {
		startDate.set(Calendar.DAY_OF_MONTH, startDay);
	}

	private void setStartMonth(int startMonth) {
		startDate.set(Calendar.MONTH, startMonth);
	}

	private void setStartYear(int startYear) {
		startDate.set(Calendar.YEAR, startYear);
	}

	private void setEndDay(int endDay) {
		endDate.set(Calendar.DAY_OF_MONTH, endDay);
	}

	private void setEndMonth(int endMonth) {
		endDate.set(Calendar.MONTH, endMonth);
	}

	private void setEndYear(int endYear) {
		endDate.set(Calendar.YEAR, endYear);
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
		
		result += "Start date: " + this.getStartDay() + "/" + this.getStartMonth() + "/" + this.getStartYear() + "\n";
		result += "End date: " + this.getEndDay() + "/" + this.getEndMonth() + "/" + this.getEndYear();
		
		return result;
	}

	@Override
	public int compareTo(TimeValidity timeValidity) {
		
		if(this.startDate.equals(timeValidity.startDate) && this.endDate.equals(timeValidity.endDate))
			return 0;
		else
			if(this.startDate.before(((TimeValidity)timeValidity).startDate))
				return -1;
			else
				return 1;
	}
	@Override
	public boolean equals(Object tV){
		if(this.compareTo((TimeValidity)tV) == 0)
			return true;
		else
			return false;
	}
}
