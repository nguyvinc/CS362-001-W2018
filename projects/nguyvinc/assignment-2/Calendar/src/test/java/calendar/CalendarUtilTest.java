package calendar;
/* This class provides test cases for the CalendarUtil class. */

import org.junit.Test;
import static org.junit.Assert.*;

public class CalendarUtilTest{
	@Test
	public void test_numDaysInMonth() throws Throwable{
		int days = NumDaysInMonth(2018, 0);
		int feb = NumDaysInMonth(2017, 1);
		int leap = NumDaysInMonth(2000, 1);
		
		assertEquals(31, days);
		assertEquals(28, feb);
		assertEquals(29, leap);
	}
	
	@Test
	public void test_isLeapYear() throws Throwable{
		boolean leap100 = IsLeapYear(100);
		boolean leap400 = IsLeapYear(400);
		boolean leap4 = IsLeapYear(2004);
		boolean not_leap = IsLeapYear(2005);
		
		assertTrue(leap100);	//Supposed to be true, but is false...
		assertTrue(leap400);
		assertTrue(leap4);
		assertFalse(not_leap);
	}
}