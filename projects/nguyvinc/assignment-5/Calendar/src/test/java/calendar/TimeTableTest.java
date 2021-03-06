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
		Appt appt1 = new Appt(15, 30, 15, 1, 2018, "Title", "Description");	//Hour, minute, day, month, year
		assertTrue(appt1.getValid());
		listAppts.add(appt1);
		
		//Add invalid appt
		Appt invalid_appt = new Appt(-14, 30, 16, 1, 2018, "Invalid", "No");
		listAppts.add(invalid_appt);
		
		//Add appt after day2
		Appt appt2 = new Appt(18, 20, 24, 1, 2018, "Next Week", "Future");
		listAppts.add(appt2);
		
		LinkedList<CalDay> days = new LinkedList<CalDay>();
		days = table.getApptRange(listAppts, day1, day2);
		assertEquals(1, days.size());
		assertEquals(appt1, days.get(0).appts.get(0));
	}	
	
	@Test
	public void test_getApptRangeRecur() throws Throwable{
		TimeTable table = new TimeTable();
		//Add recurring appointment
		int[] recurDays = {};
		int recurBy = 1;
		int recurIncrement = 1;
		int recurNumber = 2;
		Appt appt1 = new Appt(15, 30, 15, 1, 2018, "Title", "Description");	//Hour, minute, day, month, year
		appt1.setRecurrence(recurDays, recurBy, recurIncrement, recurNumber);
		LinkedList<Appt> recurAppts = new LinkedList<Appt>();
		recurAppts.add(appt1);
		
		
		//Create list of multiple days, add recurring appointment
		GregorianCalendar day1 = new GregorianCalendar(2018, 1, 15);	//Year, month, day
		GregorianCalendar day3 = new GregorianCalendar(2018, 2, 10);
		LinkedList<CalDay> multiple_days = new LinkedList<CalDay>();
		multiple_days = table.getApptRange(recurAppts, day1, day3);
		assertEquals(23, multiple_days.size());
		assertEquals(appt1, multiple_days.get(0).appts.get(0));
		assertEquals(appt1, multiple_days.get(7).appts.get(0));
		assertEquals(appt1, multiple_days.get(14).appts.get(0));
		assertEquals(0, multiple_days.get(21).appts.size());
		
		
		//Create list of multiple days, add recurring appointment, specifying days
		int[] newRecurDays = {1};
		appt1.setRecurrence(newRecurDays, recurBy, recurIncrement, recurNumber);
		multiple_days = new LinkedList<CalDay>();
		recurAppts = new LinkedList<Appt>();
		recurAppts.add(appt1);
		multiple_days = table.getApptRange(recurAppts, day1, day3);
		assertEquals(23, multiple_days.size());
		assertEquals(appt1, multiple_days.get(0).appts.get(0));
		assertEquals(appt1, multiple_days.get(3).appts.get(0));
		assertEquals(appt1, multiple_days.get(10).appts.get(0));
		assertEquals(0, multiple_days.get(17).appts.size());
		
		
		//Create list of multiple days, add recurring appointment, specifying invalid days
		int[] InvalidRecurDays = {100};
		appt1.setRecurrence(InvalidRecurDays, recurBy, recurIncrement, recurNumber);
		multiple_days = new LinkedList<CalDay>();
		recurAppts = new LinkedList<Appt>();
		recurAppts.add(appt1);
		multiple_days = table.getApptRange(recurAppts, day1, day3);
		assertEquals(23, multiple_days.size());
		
		multiple_days = table.getApptRange(recurAppts, day1, day3);
		assertEquals(appt1, multiple_days.get(0).appts.get(0));
	}
	
	@Test
	public void test_getApptRangeRecurMonthYear() throws Throwable{
		TimeTable table = new TimeTable();
		//Add monthly recurring appointment
		int[] recurDays = {};
		int recurBy = 2;
		int recurIncrement = 1;
		int recurNumber = 1;
		Appt appt1 = new Appt(15, 30, 15, 1, 2018, "Title", "Description");	//Hour, minute, day, month, year
		appt1.setRecurrence(recurDays, recurBy, recurIncrement, recurNumber);
		LinkedList<Appt> recurAppts = new LinkedList<Appt>();
		recurAppts.add(appt1);
		
		//Create list of multiple days, add recurring appointment
		GregorianCalendar day1 = new GregorianCalendar(2018, 1, 15);	//Year, month, day
		GregorianCalendar day2 = new GregorianCalendar(2018, 3, 20);
		LinkedList<CalDay> multiple_days = new LinkedList<CalDay>();
		multiple_days = table.getApptRange(recurAppts, day1, day2);
		assertEquals(64, multiple_days.size());
		//System.out.println(multiple_days);
		assertEquals(appt1, multiple_days.get(28).appts.get(0));
		assertEquals(0, multiple_days.get(59).appts.size());
		
		
		//Add yearly recurring appointment
		recurBy = 3;
		appt1.setRecurrence(recurDays, recurBy, recurIncrement, recurNumber);
		recurAppts = new LinkedList<Appt>();
		recurAppts.add(appt1);
		
		//Create list of multiple days, add recurring appointment
		day2 = new GregorianCalendar(2019, 1, 16);
		multiple_days = new LinkedList<CalDay>();
		multiple_days = table.getApptRange(recurAppts, day1, day2);
		assertEquals(366, multiple_days.size());
		assertEquals(appt1, multiple_days.get(365).appts.get(0));
		
		
		//Add invalid recurrence
		recurBy = 10;
		appt1.setRecurrence(recurDays, recurBy, recurIncrement, recurNumber);
		recurAppts = new LinkedList<Appt>();
		recurAppts.add(appt1);
		
		//Create list of multiple days, add recurring appointment
		day2 = new GregorianCalendar(2018, 1, 26);
		multiple_days = new LinkedList<CalDay>();
		multiple_days = table.getApptRange(recurAppts, day1, day2);
		assertEquals(11, multiple_days.size());
	}
	
	//Asserted that it should throw an exception
	@Test(expected = DateOutOfRangeException.class)
	public void test_dateOutOfRange() throws Throwable{
		//Date should be out of range, second date is before first day
		TimeTable table = new TimeTable();
		GregorianCalendar day1 = new GregorianCalendar(2018, 1, 15);
		GregorianCalendar oor = new GregorianCalendar(2018, 1, 5);
		
		LinkedList<Appt> listAppts = new LinkedList<Appt>();
		Appt appt = new Appt(15, 30, 15, 1, 2018, "Title", "Description");	//Hour, minute, day, month, year
		listAppts.add(appt);
		
		LinkedList<CalDay> invalid_list = new LinkedList<CalDay>();
		invalid_list = table.getApptRange(listAppts, day1, oor);
	}
	
	@Test
	public void test_deleteAppt() throws Throwable{
		TimeTable table = new TimeTable();
		LinkedList<Appt> listAppts = new LinkedList<Appt>();
		LinkedList<Appt> newList = new LinkedList<Appt>();
		Appt appt = new Appt(15, 30, 15, 1, 2018, "Title", "Description");
		
		//Remove a valid appointment that's not there
		listAppts = table.deleteAppt(listAppts, appt);
		assertNull(listAppts);
		
		
		//Try to remove nothing
		newList = table.deleteAppt(listAppts, null);
		assertNull(newList);
		
		
		//Try to remove an appointment from null
		newList = table.deleteAppt(null, appt);
		assertNull(newList);
		
		
		//Invalid appointment
		listAppts = new LinkedList<Appt>();
		Appt invalid_appt = new Appt(-14, 30, 16, 1, 2018, "Invalid", "No");
		listAppts.add(invalid_appt);
		//Try to remove an invalid appointment
		newList = table.deleteAppt(listAppts, invalid_appt);
		assertNull(newList);
		assertEquals(1, listAppts.size());
		
		
		//Remove a valid appointment that is there
		listAppts = new LinkedList<Appt>();
		appt = new Appt(15, 30, 15, 1, 2018, "Title", "Description");
		listAppts.add(appt);
		listAppts.add(appt);
		listAppts.add(appt);
		Appt diff_appt = new Appt(16, 30, 15, 1, 2018, "POTATO", "Description");
		listAppts.add(diff_appt);
		listAppts.add(appt);
		listAppts.add(appt);
		assertEquals(6, listAppts.size());
		
		listAppts = table.deleteAppt(listAppts, diff_appt);
		assertEquals(5, listAppts.size());
		assertEquals(appt, listAppts.get(0));
		assertEquals(appt, listAppts.get(1));
		assertEquals(appt, listAppts.get(2));
		assertEquals(appt, listAppts.get(3));
		assertEquals(appt, listAppts.get(4));

		for(int i=0; i<5; i++){
			listAppts = table.deleteAppt(listAppts, appt);
		}
		assertEquals(0, listAppts.size());
	}
	
	@Test
	public void test_permute() throws Throwable{
		TimeTable table = new TimeTable();
		LinkedList<Appt> apptList = new LinkedList<Appt>();
		int[] array = new int[]{};
		LinkedList<Appt> result = new LinkedList<Appt>();
		result = table.permute(apptList, array);
		assertEquals(0, result.size());
		
		Appt appt = new Appt(15, 30, 15, 1, 2018, "Title", "Description");
		Appt appt2 = new Appt(17, 20, 15, 1, 2018, "Different Title", "Another Description");
		apptList.add(appt);
		apptList.add(appt2);
		array = new int[]{1,1};
		
		result = table.permute(apptList, array);
		assertEquals(2, result.size());
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void test_failed_permute() throws Throwable{
		TimeTable table = new TimeTable();
		LinkedList<Appt> apptList = new LinkedList<Appt>();
		Appt appt = new Appt(15, 30, 15, 1, 2018, "Title", "Description");
		apptList.add(appt);
		
		int[] array = new int[]{};
		LinkedList<Appt> result = new LinkedList<Appt>();
		result = table.permute(apptList, array);
	}
}
