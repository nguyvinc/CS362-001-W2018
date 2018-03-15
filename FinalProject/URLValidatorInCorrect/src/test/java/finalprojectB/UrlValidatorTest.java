
package finalprojectB;

import junit.framework.TestCase;

//You can use this as a skeleton for your 3 different test approach
//It is an optional to use this file, you can generate your own test file(s) to test the target function!
// Again, it is up to you to use this file or not!





public class UrlValidatorTest extends TestCase {


   public UrlValidatorTest(String testName) {
      super(testName);
   }



    public void testManualTest() {
    //You can use this function to implement your manual testing
        UrlValidator urlVal = new UrlValidator(null,null,UrlValidator.ALLOW_ALL_SCHEMES);

        assertTrue(urlVal.isValid("http://www.cdc.gov"));
        assertTrue(urlVal.isValid("http://www.google.com/"));
        assertTrue(urlVal.isValid("http://google.com/index"));
        assertTrue(urlVal.isValid("http://google.com/index.html#bar"));
        assertTrue(urlVal.isValid("http://oregonstate.edu/"));
        //assertTrue(urlVal.isValid("ftp://www.google.com"));     //Failed, ftp is valid, but is considered invalid
        //assertTrue(urlVal.isValid("www.yahoo.com"));            //Failed, no scheme is valid, but is considered invalid

        assertFalse(urlVal.isValid(null));
        assertFalse(urlVal.isValid("facebook.com"));
        assertFalse(urlVal.isValid("test"));
        assertFalse(urlVal.isValid("noodle/123"));
        assertFalse(urlVal.isValid("test_here"));
    }
   
   
    public void testYourFirstPartition(){
    //You can use this function to implement your First Partition testing

    }
   
    public void testYourSecondPartition(){
    //You can use this function to implement your Second Partition testing

    }
    //You need to create more test cases for your Partitions if you need to
   
    public void testIsValid(){
	//You can use this function for programming based testing
        UrlValidator urlVal = new UrlValidator(null,null,UrlValidator.ALLOW_ALL_SCHEMES);
        testURLSchemes(urlVal);
    }

    public void testURLSchemes(UrlValidator urlVal){
        String[] ValidURLSchemes = {"http://", "ftp://", ""};
        for(int i=0; i<3; i++){
            String url = ValidURLSchemes[i] + "www.google.com";
            assertTrue(urlVal.isValid(url));
        }
        String[] InvalidURLSchemes = {"htp://", "http/", "http:/", "http//", "fnp://"};
        for(int i=0; i<5; i++){
            String url = ValidURLSchemes[i] + "www.google.com";
            assertFalse(urlVal.isValid(url));
        }
    }
}
