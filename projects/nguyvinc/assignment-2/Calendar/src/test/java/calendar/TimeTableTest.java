package calendar;
/**
 *  This class provides a basic set of test cases for the
 *  TimeTable class.
 */
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.LinkedList;
import java.util.List;


import org.junit.Test;

import static org.junit.Assert.*;

public class TimeTableTest {

	@Test
	public void test_constructor() throws Throwable{
		TimeTable table = new TimeTable();
		assertNotNull(table);
	}
	
	@Test
	public void test_getApptRange()  throws Throwable  {
		//Functions excludes second day, should only have one day
		TimeTable table = new TimeTable();
		GregorianCalendar day1 = new GregorianCalendar(2018, 1, 15);	//Year, month, day
		GregorianCalendar day2 = new GregorianCalendar(2018, 1, 16);
		
		//Add valid appt
		LinkedList<Appt> listAppts = new LinkedList<Appt>();
		Appt appt = new Appt(15, 30, 15, 1, 2018, "Title", "Description");	//Hour, minute, day, month, year
		listAppts.add(appt);
		
		//Add invalid appt
		Appt invalid_appt = new Appt(-14, 30, 16, 1, 2018, "Invalid", "No");
		listAppts.add(invalid_appt);
		
		//Add appt after day2
		appt = new Appt(18, 20, 24, 1, 2018, "Next Week", "Future");
		listAppts.add(appt);
		
		LinkedList<CalDay> days = new LinkedList<CalDay>();
		days = table.getApptRange(listAppts, day1, day2);
		
		GregorianCalendar day3 = new GregorianCalendar(2018, 1, 28);
		LinkedList<CalDay> multiple_days = new LinkedList<CalDay>();
		multiple_days = table.getApptRange(listAppts, day1, day3);
		
		assertEquals(1, days.size());
		assertEquals(2, multiple_days.size());
	}
	
	@Test(expected = DateOutOfRangeException.class)
	public void test_dateOutOfRange() throws Throwable{
		//Date should be out of range
		TimeTable table = new TimeTable();
		GregorianCalendar day1 = new GregorianCalendar(2018, 1, 15);
		GregorianCalendar oor = new GregorianCalendar(2018, 1, 5);
		
		LinkedList<Appt> listAppts = new LinkedList<Appt>();
		Appt appt = new Appt(15, 30, 15, 1, 2018, "Title", "Description");	//Hour, minute, day, month, year
		listAppts.add(appt);
		
		LinkedList<CalDay> invalid_list = new LinkedList<CalDay>();
		invalid_list = table.getApptRange(listAppts, day1, oor);
	}
}
