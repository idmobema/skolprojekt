/** TimeValidityDatesChecker takes over the task of verifying the correctness of input parameters to the methods
 * of the TimeValidity class hence increasing its cohesion but also its coupling. 
 * 
 * Rules for a valid discount period:
 * 1. a discount cannot be set to a preceding date.
 * 2. the order of parameters when creating a TimeValidity obj does matter. The order is: Start Date for the
 * 		period - startYear, startMonth, startDay and then End Date for the period as follows: endYear, endMonth
 * 		endDay. 
 * 3. boundaries are set both for start-, and end dates, meaning that a month argument accepts values between
 * 		0 and 11 inclusive (0 for January,..., 11 for December). Both end and start day inputs are limited to the
 * 		maximum number of days a given month in a given year can have. This task is executed by creating a new 
 * 		GregorianCalendar instance and calling the getActualMaximum(Calendar.CONSTANT_FIELD) that returns the
 * 		maximum of that field considering the other related fields.
 * 
 * The private timeValidityCalendar field is used only as reference to the current time. It is instantiated with
 * the constructor and used only to enforce Rule No 1 in the checkRetroactiveStartDate() method. 
 * 
 * #BC141004# */

import java.util.Calendar;
import java.util.GregorianCalendar;


public class TimeValidityDatesChecker {

	
	private GregorianCalendar timeValidityCalendar = new GregorianCalendar();
	
	public TimeValidityDatesChecker(){
		this.timeValidityCalendar = new GregorianCalendar();
	}
	

	/** A suite of checks used for the TimeValidity's constructor and changePeriod() method. */
	public void checkDates(int startYear, int startMonth, int startDay,
			int endYear, int endMonth, int endDay) {
		
		checkRetroactiveStartDate(startYear, startMonth, startDay);
		
		checkStartEndOrder(startYear, startMonth, startDay, endYear, endMonth,
				endDay);
		
		checkDateBoundaries(startYear, startMonth, startDay, endYear, endMonth,
				endDay);
	}
	/** Checks both the boundaries for start and end dates. */
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
		
		boolean wrongYear = (startYear > endYear);
		boolean wrongMonth = (startYear == endYear && startMonth > endMonth);
		boolean wrongDay = (startYear == endYear && startMonth == endMonth && startDay > endDay);
		
		if(wrongYear || wrongMonth || wrongDay)
			throw new IllegalArgumentException("wrong order of introduced parameters.");
	}
	
	/** Checks for the startDate. startDay has to be the present day. No retroactive discount
	 * validity periods are allowed. */
	private void checkRetroactiveStartDate(int startYear, int startMonth,
			int startDay) {
		
		int currentYear = timeValidityCalendar.get(Calendar.YEAR);
		int currentMonth = timeValidityCalendar.get(Calendar.MONTH);
		int currentDay = timeValidityCalendar.get(Calendar.DAY_OF_MONTH);
		boolean wrongYear = (startYear < currentYear);
		boolean wrongMonth = (startYear == currentYear && startMonth < currentMonth);
		boolean wrongDay = (startYear == currentYear && startMonth < currentMonth && startDay < currentDay);
		
		if(wrongYear || wrongMonth || wrongDay)
			throw new IllegalArgumentException("startDay has to be the present date or later");

	}

}
