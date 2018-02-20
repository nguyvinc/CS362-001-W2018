package calendar;

import java.util.Calendar;
import java.util.LinkedList;
import java.util.Random;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 * Random Test Generator for TimeTable class.
 */

public class TimeTableRandomTest {
	
    /**
     * Generate Random Tests that tests TimeTable Class.
     */
	@Test
	public void randomTestDeleteAppt() throws Throwable{
		for(int k=0; k<2000; k++){	//2000 Tests
			//System.out.println("Test #" + k+1);
			int startHour, startMinute, startMonth, startYear, startDay, NumDaysInMonth, contains=0;
			TimeTable table = new TimeTable();
			try{
				long randomseed = System.currentTimeMillis();
				Random random = new Random(randomseed);
				
				LinkedList<Appt> appts = new LinkedList<Appt>();
				LinkedList<Appt> empty = null;
				for(int j=0; j<100; j++){	//Add 10 appt to the list
					startHour = ValuesGenerator.getRandomIntBetween(random, 23, 24);		//50% Valid hour
					startMinute = ValuesGenerator.getRandomIntBetween(random, 1, 2);		//Valid minute
					startMonth = ValuesGenerator.getRandomIntBetween(random, 1, 2);			//Valid month
					startYear = ValuesGenerator.getRandomIntBetween(random, 2000, 2002);	//Random year
					NumDaysInMonth= CalendarUtil.NumDaysInMonth(startYear,startMonth-1);
					startDay = ValuesGenerator.getRandomIntBetween(random, 1, 2);			//Valid day
					
					String title = "Test removal";
					String description = "If values match, one should be gone";
					Appt appt = new Appt(startHour, startMinute, startDay, startMonth, startYear, title, description);
				
					if(startHour < 0 || startHour > 23)
						assertFalse(appt.getValid());
					else if(startMinute < 0 || startMinute > 59)
						assertFalse(appt.getValid());
					else if(startDay < 1 || startDay > NumDaysInMonth)
						assertFalse(appt.getValid());
					else
						assertTrue(appt.getValid());
				
					appts.add(appt);
				}
				assertEquals(100, appts.size());		//Size of appt list equals 100
				
				startHour = ValuesGenerator.getRandomIntBetween(random, 23, 25);		//33% Valid hour
				startMinute = ValuesGenerator.getRandomIntBetween(random, 1, 2);		//Valid minute
				startMonth = ValuesGenerator.getRandomIntBetween(random, 1, 2);			//Valid month
				startYear = ValuesGenerator.getRandomIntBetween(random, 2000, 2002);	//Random year
				NumDaysInMonth= CalendarUtil.NumDaysInMonth(startYear,startMonth-1);
				startDay = ValuesGenerator.getRandomIntBetween(random, 1, 2);			//Valid day
				
				String title = "Test removal";
				String description = "If values match, one should be gone";
				Appt target_appt = new Appt(startHour, startMinute, startDay, startMonth, startYear, title, description);
				
				if(startHour == 25)
					target_appt = null;
				
				for(int j=0; j<100; j++){
					Appt tempAppt = appts.get(j);
					if(tempAppt.equals(target_appt)){
						contains = 1;
					}
				}
				LinkedList<Appt> new_appts = table.deleteAppt(appts, target_appt);
				LinkedList<Appt> null_list = table.deleteAppt(empty, target_appt);
				if(contains == 0)
					assertNull(new_appts);
				else
					assertEquals(99, new_appts.size());
				
			}catch(NullPointerException e){
				
			}
		}
	}
	
	@Test
	public void randomTestGetApptRange() throws Throwable{
		
	}
}
