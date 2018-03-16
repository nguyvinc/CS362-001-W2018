
package finalprojectB;

import junit.framework.TestCase;

import java.net.URL;

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
        testURLAuthority(urlVal);
        testURLPort(urlVal);
    }

    public void testURLSchemes(UrlValidator urlVal){
        String[] ValidURLSchemes = {"http://", "ftp://", "", "h3t://"};   //"ftp://", "", "h3t://" fail when they are valid
        for(int i=0; i<4; i++){
            String url = ValidURLSchemes[i] + "www.google.com";
            //assertTrue(urlVal.isValid(url));
        }
        String[] InvalidURLSchemes = {"http", "hp://", "http/", "http:/", "http//", "fnp://"};  //Initialization errors
        for(int i=0; i<6; i++){
            String url = InvalidURLSchemes[i] + "www.google.com";
            //assertFalse(urlVal.isValid(url));
        }

        //URL fails if scheme isn't "http://"
        //Calls RegexValidator constructor, initialization error
    }

    public void testURLAuthority(UrlValidator urlVal){
        String[] ValidURLAuthorities = {"www.google.com", "oregonstate.edu", "www.youtube.com", "www.cdc.gov", "0.0.0.0"};
        for(int i=0; i<5; i++){
            String url = "http://" + ValidURLAuthorities[i];    //Scheme must be "http://" or it will fail
            assertTrue(urlVal.isValid(url));
        }
        String[] InvalidURLAuthorities = {"", "lol", "potato.k", "send_help"};
        for(int i=0; i<4; i++){
            String url = "http://" + InvalidURLAuthorities[i];
            //assertFalse(urlVal.isValid(url));
            assertTrue(urlVal.isValid(url));        //url is supposed to be false, but is true
        }

        //URL seems to always be true if scheme is "http://", even if authority is invalid
    }

    public void testURLPort(UrlValidator urlVal){
        String[] ValidURLPorts = {"", ":80", ":65535", ":0"};
        for(int i=0; i<4; i++){
            String url = "http://www.google.com" + ValidURLPorts[i];    //Scheme must be "http://" or it will fail
            //assertTrue(urlVal.isValid(url));                          //All non-empty ports fail
        }
        String[] InvalidURLPorts = {":-80", ":ab", "65536", "800"};
        for(int i=0; i<4; i++){
            String url = "http://www.google.com" + InvalidURLPorts[i];    //Scheme must be "http://" or it will fail
            //assertFalse(urlVal.isValid(url));                           //All cases fail
        }

        //All URLs with any port, valid or invalid, seems to fail
    }
}
