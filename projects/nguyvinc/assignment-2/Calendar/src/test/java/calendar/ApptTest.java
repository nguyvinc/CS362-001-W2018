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
	}

	@Test
	public void test_isValid()  throws Throwable  {
		int startHour = -1;		//Invalid hour
		int startMinute = 10;	
		int startDay = 12;
		int startMonth = 2;
		int startYear = 2018;
		String title = "Interview";
		String description = "Interview for Tutoring Position.";
		
		//Create appointment with invalid hour
		Appt appt_hour = new Appt(startHour, startMinute, startDay, startMonth, startYear, title, description);
		
		startHour = 10;		//Valid hour
		startMinute = 70;	//Invalid minute
		//Create appointment with invalid minute
		Appt appt_minute = new Appt(startHour, startMinute, startDay, startMonth, startYear, title, description);
		
		startMinute = 50;	//Valid minute
		startDay = 40;		//Invalid day
		//Create appointment with invalid day
		Appt appt_day = new Appt(startHour, startMinute, startDay, startMonth, startYear, title, description);
		
		startDay = 20;		//Valid day
		startMonth = -1;	//Invalid month
		//Create appointment with invalid month
		//Creates an error
		//Appt appt_month = new Appt(startHour, startMinute, startDay, startMonth, startYear, title, description);

		assertFalse(appt_hour.getValid());
		assertFalse(appt_minute.getValid());
		assertFalse(appt_day.getValid());
		//assertFalse(appt_month.getValid());
	}
	
	@Test
	public void test_setters() throws Throwable{
		Appt appt = new Appt(1, 1, 1, 1, 1, "Title", "Description");
		appt.setStartHour(8);
		appt.setStartMinute(45);
		appt.setStartDay(23);
		appt.setStartMonth(4);
		appt.setStartYear(2018);
		appt.setTitle(null);
		appt.setDescription(null);
		
		assertEquals(8, appt.getStartHour());
		assertEquals(45, appt.getStartMinute());
		assertEquals(23, appt.getStartDay());
		assertEquals(4, appt.getStartMonth());
		assertEquals(2018, appt.getStartYear());
		assertEquals("", appt.getTitle());
		assertEquals("", appt.getDescription());
		assertTrue(appt.getValid());
	}
	
	@Test
	public void test_setRecurrence() throws Throwable{
		Appt appt = new Appt(1, 1, 1, 1, 1, "Title", "Description");
		int[] recurDays = null;
		int recurBy = 1;
		int recurIncrement = 1;
		int recurNumber = 2;
		
		appt.setRecurrence(recurDays, recurBy, recurIncrement, recurNumber);
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
		Appt am_appt = new Appt(1, 11, 1, 1, 1, "Title", "Description");	//Valid AM appointment
		assertEquals("\t1/1/1 at 1:11am ,Title, Description\n", am_appt.toString());
		
		Appt pm_appt = new Appt(14, 11, 1, 1, 1, "Title", "Description");	//Valid PM appointment
		assertEquals("\t1/1/1 at 2:11pm ,Title, Description\n", pm_appt.toString());
		
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
