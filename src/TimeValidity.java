/** TimeValidity is coupled to the Discount class. (Most) Usually a discount has a period of validity.
 * The setter for the fields are private in order to simplify the rules of changing the validity period.
 * For example suppose that the validity for a certain discount is from 5th of Nov '14 to 10th of Dec '14.
 * Suppose also that the setters for the fields are public and You want to set the validity's startDay to 31.
 * So timeValidity.setStartDay(31)
 * By treating the setters publicly and individually in this step You'll have to check that the startMonth Nov has
 * 31 days, which it hasn't. More so complicated when the startMonth is Feb. Forwardly consider that knowing now this
 * information You'll try to set the startDay to 30th of Nov. Somewhere else in the system a call to setStartMonth(1)
 * occurs. (month 1 is Feb). Another conflict appears: the start date will have 30 as startDay and 1(Feb) as startMonth.
 * Adding to this more complexities that come with leap years it is just justifiable to make the setters for the fields
 * private and have instead a common public changePeriod(startYear, startMonth, startDay, endYear, endMonth, endDay)
 * that will reset the current timeValidity to the new parameters. Clearly the reset will undergo as the constructor does
 * a series of checks that enforce the rules of the time validity mentioned below at checkDates() method. 
 * 
 * #BC141004#*/

import java.util.Calendar;
import java.util.GregorianCalendar;

public class TimeValidity {
	
	private GregorianCalendar CALENDAR = new GregorianCalendar();

	private int startDay, startMonth, startYear, endDay, endMonth, endYear;
	

	/** Constructor */
	public TimeValidity(int startYear, int startMonth, int startDay, int endYear, int endMonth, int endDay){
		
		checkDates(startYear, startMonth, startDay, endYear, endMonth, endDay);
		
		this.startDay = startDay; this.startMonth = startMonth; this.startYear = startYear;
		this.endDay = endDay; this.endMonth = endMonth; this.endYear = endYear;
	}

	/** Runs a suite of checks on the input dates.
	 * Rules:
	 * The timeValidity cannot be retroactive.
	 * The order of start and end dates matters.
	 * The start and end day have to comply with the number of days in that month of that given year.(leap or not for February)
	 * The month is limited to the range of 0 to 11 inclusive. */
	private void checkDates(int startYear, int startMonth, int startDay,
			int endYear, int endMonth, int endDay) {
		
		checkRetroactiveStartDate(startYear, startMonth, startDay);
		
		checkStartEndOrder(startYear, startMonth, startDay, endYear, endMonth,
				endDay);
		
		checkDateBoundaries(startYear, startMonth, startDay, endYear, endMonth,
				endDay);
	}

	private void checkDateBoundaries(int startYear, int startMonth,
			int startDay, int endYear, int endMonth, int endDay) {
		
		checkStartMonthAndStartDayBoundaries(startYear, startMonth, startDay);
		checkEndMonthAndEndDayBoundaries(endYear, endMonth, endDay);
	}

	/** Checks for the startDay that it is in the range for the given month (1 - 28, 29, 30, 31)
	 * and for the startMonth being between 0 and 11 inclusive*/
	private void checkStartMonthAndStartDayBoundaries(int startYear, int startMonth, int startDay) {
		
		GregorianCalendar temp = new GregorianCalendar(startYear, startMonth, 1);
		int daysInThatMonth = temp.getActualMaximum(Calendar.DAY_OF_MONTH);
		
		if(startDay < 1 || startDay > daysInThatMonth || startMonth > 11 || startMonth < 0)
			throw new IllegalArgumentException("day or month out of boundaries for given year");
	}
	
	/** Checks for the endDay that it is in the range for the given month (1 - 28, 29, 30, 31)
	 * and for the endMonth being between 0 and 11 inclusive*/
	private void checkEndMonthAndEndDayBoundaries(int endYear, int endMonth, int endDay) {
		
		GregorianCalendar temp = new GregorianCalendar(endYear, endMonth, 1);
		int daysInThatMonth = temp.getActualMaximum(Calendar.DAY_OF_MONTH);
		
		if(endDay < 1 || endDay > daysInThatMonth || endMonth > 11 || endMonth < 0)
			throw new IllegalArgumentException("day or month out of boundaries for given year");
	}

	/** Checks for the order of introduced parameters. The startDate - startYear, startMonth and startDay - have to come before
	 * the endDate - endYear, endMonth, endDay. */
	private void checkStartEndOrder(int startYear, int startMonth,
			int startDay, int endYear, int endMonth, int endDay) {
		
		if(startYear > endYear)
			throw new IllegalArgumentException("wrong order of introduced parameters.");
		if(startYear == endYear && startMonth > endMonth)
			throw new IllegalArgumentException("wrong order of introduced parameters.");
		if(startYear == endYear && startMonth == endMonth && startDay > endDay)
			throw new IllegalArgumentException("wrong order of introduced parameters.");
	}

	/** Checks for the startDate. startDay has to be the present day. No retroactive discount
	 * validity periods are allowed. */
	private void checkRetroactiveStartDate(int startYear, int startMonth,
			int startDay) {
		
		if(startYear < CALENDAR.get(Calendar.YEAR))
			throw new IllegalArgumentException("startDay has to be the present date or later");
		if(startYear == CALENDAR.get(Calendar.YEAR) && startMonth < CALENDAR.get(Calendar.MONTH))
			throw new IllegalArgumentException("startDay has to be the present date or later");
		if(startYear == CALENDAR.get(Calendar.YEAR) && startMonth == CALENDAR.get(Calendar.MONTH) && startDay < CALENDAR.get(Calendar.DAY_OF_MONTH))
			throw new IllegalArgumentException("startDay has to be the present date or later");
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

	public void changePeriod(int startYear, int startMonth, int startDay, int endYear, int endMonth, int endDay){
		
		checkDates(startYear, startMonth, startDay, endYear, endMonth, endDay);
		
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


	public static void main(String[] args){
		TimeValidity tv = new TimeValidity(2014, 10, 30, 2015, 1, 28);
		System.out.println(tv);
		System.out.println(CALENDAR);
	}
}
