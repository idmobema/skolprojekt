/** 
 * IMPORTANT!!!
 * Most, if not all, of the tests are time related (/ bounded) to the current system's time.
 * Tests are constrained to FAIL if the time validity is SET / CREATED for a date that PRECEDES the current date.
 * Read more about the rules on assigning a time validity to a Discount in TimeValidity.java and 
 * TimeValidityDatesChecker.java 
 * 
 * #BC141004# */

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Test;

public class TimeValidityTest {
	TimeValidity timeValidity;
	@After
	public void tearDown(){
		timeValidity = null;
	}
	
	@Test (expected = IllegalArgumentException.class)
	public void testTimeValidityConstructor(){
		timeValidity = new TimeValidity(2014, 14, 5, 2014, 15, 5);
	}
	@Test (expected = IllegalArgumentException.class)
	public void testTimeValidityConstructorNotCreatesIllegalObjs1(){
		timeValidity = new TimeValidity(2013, 11, 3, 2014, 10, 5);
		
	}
	@Test (expected = IllegalArgumentException.class)
	public void testTimeValidityConstructorNotCreatesIllegalObjs2(){
		timeValidity = new TimeValidity(2014, 10, 5, 2013, 10, 20);
		
	}
	
	@Test (expected = IllegalArgumentException.class)
	public void testCheckStartMonthAndStartDayBoundaries(){
		timeValidity = new TimeValidity(2014, 10, 31, 2014, 11, 31);
		assertNull("this obj should not have been created", timeValidity);
	}
	
	@Test (expected = IllegalArgumentException.class)
	public void testCheckEndMonthAndEndDayBoundarie(){
		timeValidity = new TimeValidity(2014, 11, 10, 2014, 11, 32);
	}
	
	@Test
	public void testGetStartDay(){
		timeValidity = new TimeValidity(2014, 9, 10, 2014, 9, 15);
		int expected = 10;
		int result = timeValidity.getStartDay();
		
		assertTrue(expected == result);
		
	}
	
	
	@Test
	public void testChangePeriod(){
		timeValidity = new TimeValidity(2014, 10, 10, 2014, 11, 15);
		timeValidity.changePeriod(2014, 10, 5, 2014, 11, 10);
		
		int expectedStartDay = 5;
		int resultedStartDay = timeValidity.getStartDay();
		
		int expectedEndMonth = 11;
		int resultedEndMonth = timeValidity.getEndMonth();
		assertTrue( expectedStartDay == resultedStartDay && expectedEndMonth == resultedEndMonth);
	}
	
	@Test (expected = IllegalArgumentException.class)
	public void testChangePeriodWithWrongArguments1(){
		timeValidity = new TimeValidity(2014, 10, 10, 2014, 11, 15);
		timeValidity.changePeriod(2013, 10, 11, 2014, 10, 11);
	}
	
	@Test (expected = IllegalArgumentException.class)
	public void testChangePeriodWithWrongArguments2(){
		timeValidity = new TimeValidity(2014, 10, 10, 2014, 11, 15);
		timeValidity.changePeriod(2015, 10, 11, 2014, 10, 11);
	}
	
	@Test (expected = IllegalArgumentException.class)
	public void testChangePeriodWithWrongArguments3(){
		timeValidity = new TimeValidity(2014, 10, 10, 2014, 11, 15);
		timeValidity.changePeriod(2015, 1, 29, 2015, 10, 11);
	}
}
