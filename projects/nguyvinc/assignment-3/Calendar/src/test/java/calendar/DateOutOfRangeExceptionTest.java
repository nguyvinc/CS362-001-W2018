//Provides test for DateOutOfRangeException class
package calendar;
import org.junit.Test;
import static org.junit.Assert.*;

public class DateOutOfRangeExceptionTest {
	@Test
	public void test_default(){
		DateOutOfRangeException date = new DateOutOfRangeException();
		assertNotNull(date);
	}
	
	@Test
	public void test_nondefault(){
		String msg = "I am hungry";
		DateOutOfRangeException date = new DateOutOfRangeException(msg);
		assertNotNull(date);
	}
}