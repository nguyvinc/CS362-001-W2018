package calendar;

import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.Random;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 * Random Test Generator for CalDay class.
 */

public class CalDayRandomTest {
	
    /**
     * Generate Random Tests that tests CalDay Class.
     */
	@Test
	public void randomTestAddAppt() throws Throwable{
		long randomseed = System.currentTimeMillis();
		Random random = new Random(randomseed);
		
		int valid=0, startHour;
		int startDay = ValuesGenerator.RandInt(random);
		int startMonth = ValuesGenerator.getRandomIntBetween(random, 1, 12);
		int startYear = ValuesGenerator.RandInt(random);
		GregorianCalendar cal = new GregorianCalendar(startYear, startMonth, startDay);	//New random day
		CalDay day = new CalDay(cal);
		assertEquals(0, day.getAppts().size());
		
		for(int k=0; k<2000; k++){	//1000 Tests
			//System.out.println("Test #" + k+1);
			try{
				randomseed = System.currentTimeMillis();
				random = new Random(randomseed);
				
				if(k<1900)
					startHour = ValuesGenerator.getRandomIntBetween(random, 4, 24);			//Random hour, might not be valid
				else
					startHour = ValuesGenerator.getRandomIntBetween(random, 0, 4);			//Random valid hour, less than first 1900 appts
				int startMinute = ValuesGenerator.getRandomIntBetween(random, 0, 119);		//50% valid minute
				startMonth = ValuesGenerator.getRandomIntBetween(random, 1, 12);			//Random valid month
				startYear = ValuesGenerator.RandInt(random);								//Random year
				int NumDaysInMonth= CalendarUtil.NumDaysInMonth(startYear,startMonth-1);
				startDay = ValuesGenerator.getRandomIntBetween(random, 1, NumDaysInMonth+1);//Majority valid day
				
				String title = ValuesGenerator.getString(random);
				String description = ValuesGenerator.getString(random);
				Appt appt = new Appt(startHour, startMinute, startDay, startMonth, startYear, title, description);
				
				if(startHour < 0 || startHour > 23)
					assertFalse(appt.getValid());
				else if(startMinute < 0 || startMinute > 59)
					assertFalse(appt.getValid());
				else if(startDay < 1 || startDay > NumDaysInMonth)
					assertFalse(appt.getValid());
				else{
					assertTrue(appt.getValid());
					valid += 1;
				}
				
				day.addAppt(appt);
				assertEquals(valid, day.getSizeAppts());		//Size of appt list equals number of valid appts
								
			}catch(NullPointerException e){
				
			}
		}
	}
}
