package calendar;

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
	public void randomtest() throws Throwable{
		for(int k=0; k<200; k++){
			//System.out.println("Test #" + k);
			try{
				long randomseed = System.currentTimeMillis();
				Random random = new Random(randomseed);
				
				int startHour = ValuesGenerator.RandInt(random);
				int startMinute = ValuesGenerator.RandInt(random);
				int startDay = ValuesGenerator.RandInt(random);
				int startMonth = ValuesGenerator.getRandomIntBetween(random, 1, 12);
				int startYear = ValuesGenerator.RandInt(random);
				String title = "Buy Groceries";
				String description = "We are almost out of ice cream.";
				Appt appt = new Appt(startHour, startMinute, startDay, startMonth, startYear, title, description);
				
				int NumDaysInMonth= CalendarUtil.NumDaysInMonth(startYear,startMonth-1);
				
				if(startHour < 0 || startHour > 23)
					assertFalse(appt.getValid());
				else if(startMinute < 0 || startMinute > 59)
					assertFalse(appt.getValid());
				else if(startDay < 1 || startDay > NumDaysInMonth)
					assertFalse(appt.getValid());
				else
					assertTrue(appt.getValid());
				
			}catch(NullPointerException e){
				
			}
		}
	}
		
		
	}
}
