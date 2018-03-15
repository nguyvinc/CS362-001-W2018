
package finalprojectB;

import junit.framework.TestCase;

//You can use this as a skeleton for your 3 different test approach
//It is an optional to use this file, you can generate your own test file(s) to test the target function!
// Again, it is up to you to use this file or not!





public class UrlValidatorTest extends TestCase {


   public UrlValidatorTest(String testName) {
      super(testName);
   }

   
   
   public void testManualTest()
   {
//You can use this function to implement your manual testing	   
	   UrlValidator urlVal = new UrlValidator();
      
      assertFalse(urlVal.isValid(null));
      assertFalse(urlVal.isValid("https://www.google.com")); //BUG FOUND - SHOULD RETURN TRUE
      assertFalse(urlVal.isValid("https://www.google.com/")); //BUG FOUND - SHOULD RETURN TRUE
      assertFalse(urlVal.isValid("google.com")); // no "https://www."
      assertFalse(urlVal.isValid("test"));
      assertFalse(urlVal.isValid("testing.com")); //random
      assertFalse(urlVal.isValid("http://oregonstate.edu/")); //BUG FOUND - SHOULD RETURN TRUE
      assertFalse(urlVal.isValid("http://google.com/index"));
      assertFalse(urlVal.isValid("http://google.com/index.html#bar"));

      UrlValidator urlVal1 = new UrlValidator(null,null,UrlValidator.ALLOW_ALL_SCHEMES);

      //assertTrue(urlVal1.isValid("https://www.google.com")); //initiliazation exception error
      assertFalse(urlVal1.isValid("test_here"));
   }
   
   
   public void testYourFirstPartition()
   {
	 //You can use this function to implement your First Partition testing	   

   }
   
   public void testYourSecondPartition(){
		 //You can use this function to implement your Second Partition testing	   

   }
   //You need to create more test cases for your Partitions if you need to 
   
   public void testIsValid()
   {
	   //You can use this function for programming based testing

   }
   


}
