package calendar;
/**
 *  This class provides a basic set of test cases for the
 *  Appt class.
 */
import org.junit.Test;

import static org.junit.Assert.*;
public class ApptTest {
    /**
     * Test that the gets methods work as expected.
     */
	@Test
	public void test_apptConstructor ()  throws Throwable  {
		int startHour=21;
		int startMinute=30;
		int startDay=15;
		int startMonth=01;
		int startYear=2018;
		String title="Birthday Party";
		String description="This is my birthday party.";
		//Construct a new Appointment object with the initial data	 
		Appt appt = new Appt(startHour,
		          startMinute ,
		          startDay ,
		          startMonth ,
		          startYear ,
		          title,
		         description);
	// assertions
		assertTrue(appt.getValid());
		assertEquals(21, appt.getStartHour());
		assertEquals(30, appt.getStartMinute());
		assertEquals(15, appt.getStartDay());
		assertEquals(01, appt.getStartMonth());
		assertEquals(2018, appt.getStartYear());
		assertEquals("Birthday Party", appt.getTitle());
		assertEquals("This is my birthday party.", appt.getDescription()); 
		assertEquals(0, appt.getRecurNumber());
		assertEquals(0, appt.getRecurIncrement());
		assertEquals(2, appt.getRecurBy());
		assertNotNull(appt.getRecurDays());
	}

	@Test
	public void test_isValid()  throws Throwable  {
		int startHour = -1;		//Invalid hour below
		int startMinute = 10;	
		int startDay = 12;
		int startMonth = 1;
		int startYear = 2018;
		String title = "Interview";
		String description = "Interview for Tutoring Position.";
		
		//Create appointment with invalid hours
		Appt appt_hour = new Appt(startHour, startMinute, startDay, startMonth, startYear, title, description);
		assertFalse(appt_hour.getValid());
		appt_hour.setStartHour(24);		//Invalid hour above
		assertFalse(appt_hour.getValid());
		
		//Create appointment with valid hour at border
		appt_hour.setStartHour(0); 		//Valid hour, lower bound
		assertTrue(appt_hour.getValid());
		appt_hour.setStartHour(23);		//Valid hour, upper bound
		assertTrue(appt_hour.getValid());		
		
		
		//Create appointment with invalid minutes
		startHour = 10;
		startMinute = 60;	//Invalid minute above
		Appt appt_minute = new Appt(startHour, startMinute, startDay, startMonth, startYear, title, description);
		assertFalse(appt_minute.getValid());
		appt_minute.setStartMinute(-1);	//Invalid minute below
		assertFalse(appt_minute.getValid());
		
		//Create appointment with valid minute at border
		appt_minute.setStartMinute(0);	//Valid minute, lower bound
		assertTrue(appt_minute.getValid());
		appt_minute.setStartMinute(59);	//Valid minute, upper bound
		assertTrue(appt_minute.getValid());
		
		
		//Create appointment with invalid days
		startMinute = 10;
		startDay = 35;					//Invalid day above
		Appt appt_day = new Appt(startHour, startMinute, startDay, startMonth, startYear, title, description);
		assertFalse(appt_day.getValid());
		appt_day.setStartDay(0);		//Invalid day below
		assertFalse(appt_day.getValid());

		//Create appointment with valid day at border
		appt_day.setStartDay(1);		//Valid day, lower bound
		assertTrue(appt_day.getValid());
		appt_day.setStartDay(31);		//Valid day, upper bound (Month is January: 31 days)
		assertTrue(appt_day.getValid());		
		
		
		//Create appointment with valid month at border
		startDay = 20;		
		startMonth = 1;		//Valid month, lower bound
		Appt appt_month = new Appt(startHour, startMinute, startDay, startMonth, startYear, title, description);
		assertTrue(appt_month.getValid());
		appt_month.setStartMonth(12);	//Valid month, upper bound
		assertTrue(appt_month.getValid());
		
		//Create appointment with invalid months
		appt_month.setStartMonth(0);
		assertFalse(appt_month.getValid());
		appt_month.setStartMonth(13);
		assertFalse(appt_month.getValid());
		
		
		//Create appointment with a year
		startYear = 2000;
		startMonth = 3;
		Appt appt_year = new Appt(startHour, startMinute, startDay, startMonth, startYear, title, description);
		appt_year.setStartYear(2010);
		assertTrue(appt_year.getValid());
		
	}
	
	@Test
	public void test_setters() throws Throwable{
		//Hour, Minute, Day, Month, Year, title, description
		//Invalid hour
		Appt appt = new Appt(-1, 1, 1, 1, 1, "Title", "Description");
		assertEquals(-1, appt.getStartHour());
		assertFalse(appt.getValid());
		
		//Valid hour
		appt.setStartHour(8);
		assertEquals(8, appt.getStartHour());
		assertTrue(appt.getValid());
		
		//Invalid minute
		appt.setStartMinute(-1);
		assertEquals(-1, appt.getStartMinute());
		assertFalse(appt.getValid());
		
		//Valid minute
		appt.setStartMinute(45);
		assertEquals(45, appt.getStartMinute());
		assertTrue(appt.getValid());
		
		//Invalid day
		appt.setStartDay(45);
		assertEquals(45, appt.getStartDay());
		assertFalse(appt.getValid());
		
		//Valid day
		appt.setStartDay(23);
		assertEquals(23, appt.getStartDay());
		assertTrue(appt.getValid());
		
		//Invalid month, causes errors
		appt.setStartMonth(14);
		assertEquals(14, appt.getStartMonth());
		assertFalse(appt.getValid());
		
		//Valid month
		appt.setStartMonth(4);
		assertEquals(4, appt.getStartMonth());
		assertTrue(appt.getValid());
		
		//Any year, title, and description is valid
		appt.setStartYear(2018);
		appt.setTitle(null);
		appt.setDescription(null);
		
		assertEquals(2018, appt.getStartYear());
		assertEquals("", appt.getTitle());
		assertEquals("", appt.getDescription());
		assertTrue(appt.getValid());
	}
	
	@Test
	public void test_setRecurrence() throws Throwable{
		Appt appt = new Appt(1, 1, 1, 1, 1, "Title", "Description");
		assertFalse(appt.isRecurring());
		
		int[] recurDays = null;
		int recurBy = 1;
		int recurIncrement = 1;
		int recurNumber = 2;
		
		appt.setRecurrence(recurDays, recurBy, recurIncrement, recurNumber);
		assertTrue(appt.isRecurring());
		assertNotNull(appt.getRecurDays());
		assertEquals(1, appt.getRecurBy());
		assertEquals(1, appt.getRecurIncrement());
		assertEquals(2, appt.getRecurNumber());
		
		int[] recurDays2 = {1,2};
		appt.setRecurrence(recurDays2, recurBy, recurIncrement, recurNumber);
		assertNotNull(appt.getRecurDays());
	}
	
	@Test
	public void test_toString() throws Throwable{
		Appt am_appt = new Appt(11, 15, 1, 1, 1, "Title", "Description");	//Valid AM appointment
		assertEquals("\t1/1/1 at 11:15am ,Title, Description\n", am_appt.toString());
		
		Appt pm_appt = new Appt(13, 11, 1, 1, 1, "Title", "Description");	//Valid PM appointment
		assertEquals("\t1/1/1 at 1:11pm ,Title, Description\n", pm_appt.toString());
		
		Appt midnight_appt = new Appt(0, 11, 1, 1, 1, "Title", "Description");	//Valid 12AM appointment
		assertEquals("\t1/1/1 at 12:11am ,Title, Description\n", midnight_appt.toString());
		
		Appt invalid_appt = new Appt(1, -1, 1, 1, 1, "Title", "Description");	//Invalid appointment
		assertNull(invalid_appt.toString());
	}
	
	@Test
	public void test_compartTo() throws Throwable{
		Appt appt = new Appt(1, 11, 1, 1, 1, "Title", "Description");
		Appt appt_2 = new Appt(2, 22, 2, 2, 2, "Title 2", "Description 2");
		assertEquals(15, appt_2.compareTo(appt));
	}
	
}
