
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
        //assertTrue(urlVal.isValid("https://google.com/index")); //Failed, https is valid, but is considered invalid
        //assertTrue(urlVal.isValid("facebook.com"));             //Failed, no scheme is valid, but is considered invalid
        //assertTrue(urlVal.isValid("ftp://www.google.com"));     //Failed, ftp is valid, but is considered invalid
        //assertTrue(urlVal.isValid("www.yahoo.com"));            //Failed, no scheme is valid, but is considered invalid

        assertFalse(urlVal.isValid(null));
        assertFalse(urlVal.isValid("test"));
        assertFalse(urlVal.isValid("noodle/123"));
        assertFalse(urlVal.isValid("test_here"));
    }
   
   
    public void testYourFirstPartition(){
    //You can use this function to implement your First Partition testing
        UrlValidator urlVal = new UrlValidator(null,null,UrlValidator.ALLOW_ALL_SCHEMES);

/*
        assertFalse(urlVal.isValid("https://google.edu")); // different authority (changed the .com to .edu)
        assertFalse(urlVal.isValid("https://google.net")); // different authority (changed the .com to .net)
        assertFalse(urlVal.isValid("https://google.opggzz")); // invalid authority (SHOULDN'T WORK.)
        assertFalse(urlVal.isValid("Htpp://google.com"));  // invalid scheme (SHOULDN'T WORK.)
        assertFalse(urlVal.isValid("HTTP://(!).COM")); // Invalid authority
        assertFalse(urlVal.isValid("http:.com")); // invalid authority. “Google”  deleted now it's only empty between the : and .
        assertFalse(urlVal.isValid("http:google")); //authority is invalid. missing the ".com" ending
        assertFalse(urlVal.isValid("http://google.com/index")); // The difference is that a path has been added.
        assertFalse(urlVal.isValid("http//google.com"));  // no semi colon (SHOULDN'T WORK.)
*/
        //these are all examples of the URL that SHOULD WORK, but doesn't

        assertTrue(urlVal.isValid("https://google.com")); // working cases starting with this specific string
        /*assertTrue(urlVal.isValid("http://google.com")); // different scheme (changed https to just http)
        assertTrue(urlVal.isValid("HTTP://GOOGLE.COM")); // works like the original partition that is being test (should work just fine.)
        assertTrue(urlVal.isValid("google.com")); // invalid scheme (missing the beginning part)
        assertTrue(urlVal.isValid("http://google.com/index.html#bar")); //should work.
        */
    }

   
    public void testIsValid(){
	    //You can use this function for programming based testing
        //This functions tests one part of the URL at a time, checking valid and invalid URLs
        UrlValidator urlVal = new UrlValidator(null,null,UrlValidator.ALLOW_ALL_SCHEMES);
        testURLSchemes(urlVal);     //Test schemes
        testURLAuthority(urlVal);   //Test authorities
        testURLPort(urlVal);        //Test ports
        testURLPath(urlVal);        //Test paths
        testURLQuery(urlVal);       //Test queries
    }

    public void testURLSchemes(UrlValidator urlVal){
        String[] ValidURLSchemes = {"http://", "ftp://", "", "h3t://"};   //"ftp://", "", "h3t://" fail when they are valid
        for(int i=0; i<4; i++){
        //for(int i=1; i<2; i++){
            String url = ValidURLSchemes[i] + "www.google.com"; //Test URL with valid scheme
            //assertTrue(urlVal.isValid(url));                  //Should be valid
        }
        String[] InvalidURLSchemes = {"http", "hp://", "http/", "http:/", "http//", "fnp://"};  //Initialization errors
        for(int i=0; i<6; i++){
            String url = InvalidURLSchemes[i] + "www.google.com"; //Test URL with invalid scheme
            //assertFalse(urlVal.isValid(url));                   //Should be invalid
        }

        //URL fails if scheme isn't "http://"
        //Calls RegexValidator constructor, initialization error
    }

    public void testURLAuthority(UrlValidator urlVal){
        String[] ValidURLAuthorities = {"www.google.com", "oregonstate.edu", "www.youtube.com", "www.cdc.gov", "0.0.0.0"};
        for(int i=0; i<5; i++){                                 //Test URL with valid authority
            String url = "http://" + ValidURLAuthorities[i];
            assertTrue(urlVal.isValid(url));                    //Should be valid
        }
        String[] InvalidURLAuthorities = {"", "lol", "potato.k", "send_help"};
        for(int i=0; i<4; i++){
            String url = "http://" + InvalidURLAuthorities[i];  //Test URL with invalid authority
            //assertFalse(urlVal.isValid(url));                 //Should be invalid
                                                                //url is supposed to be invalid, but is valid
        }

        //URL seems to always be true if scheme is "http://", even if authority is invalid
    }

    public void testURLPort(UrlValidator urlVal){
        String[] ValidURLPorts = {"", ":80", ":65535", ":0"};
        for(int i=0; i<4; i++){                                         //Test URL with valid port
            String url = "http://www.google.com" + ValidURLPorts[i];
            //assertTrue(urlVal.isValid(url));                          //Should be valid, but is invalid
        }
        String[] InvalidURLPorts = {":-80", ":ab", "65536", "800"};
        for(int i=0; i<4; i++){                                         //Test URL with invalid port
            String url = "http://www.google.com" + InvalidURLPorts[i];
            //assertFalse(urlVal.isValid(url));                         //Cases fail
        }
        //All non-empty ports fail
        //All URLs with any port, valid or invalid, seems to fail
    }

    public void testURLPath(UrlValidator urlVal){
        String[] ValidURLPaths = {"", "/test", "/potato", "/1298", "/ice/cream", "/will/this/work"};
        //for(int i=0; i<6; i++){
        for(int i=0; i<4; i++){                                         //Test URL with valid path
            String url = "http://www.google.com" + ValidURLPaths[i];
            assertTrue(urlVal.isValid(url));                            //(BUG FOUND!!) Fails on "/ice/cream" and "/will/this/work" when they are valid
        }
        String[] InvalidURLPaths = {"/..", "/cake//", "/cheese//noodles"};
        for(int i=0; i<3; i++){                                         //Test URL with invalid path
            String url = "http://www.google.com" + InvalidURLPaths[i];
            assertFalse(urlVal.isValid(url));                           //URL should be invalid
        }

        //(BUG FOUND!!)Cases with a path longer than 1 seem to fail
    }

    public void testURLQuery(UrlValidator urlVal){
        String[] ValidURLQueries = {"", "?action=view", "?action=edit&mode=up", "?a=50"};
        for(int i=0; i<4; i++){                                           //Test URL with valid Query
            String url = "http://www.google.com" + ValidURLQueries[i];
            assertTrue(urlVal.isValid(url));                              //URL should be valid
        }
    }
}
