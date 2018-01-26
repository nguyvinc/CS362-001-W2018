package calendar;
/**
 *  This class provides a basic set of test cases for the
 *  CalDay class.
 */
import java.util.Calendar;
import java.util.GregorianCalendar;


import org.junit.Test;

import static org.junit.Assert.*;

public class CalDayTest {

	@Test
	public void test_defaultCalDay()  throws Throwable  {
		CalDay def_calDay = new CalDay();
		assertFalse(def_calDay.isValid());
	}
	
	@Test
	public void test_get_and_nondef_const()  throws Throwable  {
		GregorianCalendar cal = new GregorianCalendar(2017, 5, 13);
		CalDay d = new CalDay(cal);
		
		assertEquals(13, d.getDay());
		assertEquals(5, d.getMonth());
		assertEquals(2017, d.getYear());
		assertEquals(0, d.getSizeAppts());
		assertTrue(d.isValid());
	}
	//add more unit tests as you need
	
	@Test
	public void test_addAppt() throws Throwable{
		GregorianCalendar cal = new GregorianCalendar(2018, 1, 21);
		Appt appt = new Appt(14, 11, 21, 1, 2018, "Title", "Description");
		CalDay d = new CalDay(cal);
		d.addAppt(appt);
		int add1 = d.getSizeAppts();
		
		Appt appt2 = new Appt(12, 11, 21, 1, 2018, "Title 2", "Description 2");
		d.addAppt(appt2);
		int add2 = d.getSizeAppts();
		
		Appt appt3 = new Appt(16, 11, 21, 1, 2018, "Title 3", "Description 3");
		d.addAppt(appt3);
		
		Appt invalid_appt = new Appt(12, 11, -2, 1, 2018, "NOPE", "OKAY");
		d.addAppt(invalid_appt);
		
		
		assertEquals(1, add1);
		assertEquals(2, add2);
		assertEquals(3, d.getSizeAppts());
	}
	
	@Test
	public void test_toString() throws Throwable{
		GregorianCalendar cal = new GregorianCalendar(2018, 1, 21);
		CalDay d = new CalDay(cal);
		String day1 = d.toString();
		
		CalDay invalid = new CalDay();
		String day2 = invalid.toString();
		
		Appt appt = new Appt(14, 11, 21, 1, 2018, "Sleep", "Catch up on sleep");
		d.addAppt(appt);
		String day3 = d.toString();
		
		
		assertNotNull(day1);
		assertNotNull(day2);
		assertNotNull(day3);
	}
	
	@Test
	public void test_iterator() throws Throwable{
		GregorianCalendar cal = new GregorianCalendar(2018, 1, 21);
		CalDay d = new CalDay(cal);
		Appt appt = new Appt(14, 11, 21, 1, 2018, "Sleep", "Catch up on sleep");
		d.addAppt(appt);
		assertNotNull(d.iterator());	//Valid iterator
		
		d.valid = false;
		CalDay invalid = new CalDay();
		assertNull(invalid.iterator());
	}
}
