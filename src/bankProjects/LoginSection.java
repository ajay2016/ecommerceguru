/* Test Steps
  *       1)  Go to http://www.demo.guru99.com/V4/
          2) Enter valid UserId
          3) Enter valid Password
          4) Click Login
          
          */


package bankProjects;

/*
 * Time to create a more professional Script
 * 1) All parameters will will be saved in File Util.java - Helps in easy code maintenance
 * 2) We will move the code to launch Webdriver in a separate method as SetUp. Helps in code understanding
 */

import java.io.File;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.*;
import org.openqa.selenium.firefox.FirefoxBinary;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxProfile;






import java.util.concurrent.TimeUnit;

import junit.framework.Assert;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;


public class LoginSection {
	
	static WebDriver driver; // Selenium control driver
    //private static String baseUrl; // baseUrl of Website Guru99
    
 // This method SetUp will read initialization parameters from the class Util.java & launch Firefox 

  /*  public static void setUp() throws Exception {
	/*
	 * Tells the Selenium client library to connect to the Webdriver
	 * service using firefox
	 * 
	 * In some PC's, Selenium can not find the binary file of Firefox because
	 * user doesn't install Firefox at its default location. We need to tell
	 * Selenium where the firefox.exe is
	 */
		//File pathToBinary = new File(Util.FIREFOX_PATH);
		//FirefoxBinary ffBinary = new FirefoxBinary(pathToBinary);

	/*
	 * Create new firefoxProfile for Testing
	 * 
	 * A profile in Firefox is a collection of bookmarks, browser settings,
	 * extensions, passwords, and history; in short, all of your personal
	 * settings. Firefox uses a DEFAULT profile to store all of your
	 * personal settings.
	 * 
	 * In this case, we use Firefox for "testing" purpose not as an "end user".
	 * We need to create NEW firefoxProfile because we want to separate the
	 * Firefox profile for testing purpose and that of an end user. If
	 * something wrong happens with the testing, you still have your DEFAULT
	 * profile to fall back to (your personal data still safe).
	 */
		/*FirefoxProfile firefoxProfile = new FirefoxProfile();
		driver = new FirefoxDriver(ffBinary, firefoxProfile);
		
		// Setting Base URL of website Guru99
		baseUrl = Util.BASE_URL;
		// Specifies the amount of time the driver should wait when searching for an element if it is not immediately present.
		// Refer - http://selenium.googlecode.com/git/docs/api/java/org/openqa/selenium/WebDriver.Timeouts.html
		
		driver.manage().window().maximize();
		driver.manage().timeouts()
			.implicitlyWait(Util.WAIT_TIME, TimeUnit.SECONDS);
		// Go to http://www.demo.guru99.com/V4/
		driver.get(baseUrl + "/V4/");
    }*/

	@SuppressWarnings("deprecation")
	public static void main(String[] args) {
		
		String username = "mngr17906";
		String password = "zArArEp";
		//String actualTitle;
		//String actualBoxtitle;
		
		String URL = " http://www.demo.guru99.com/V4/";
		
		
	    driver = new FirefoxDriver();
	    driver.get(Util.BASE_URL  + "/V4/");
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Util.WAIT_TIME, TimeUnit.SECONDS);
		
		//Setup Firefox driver
	    //setUp();
		
		driver.findElement(By.name("uid")).clear();
		driver.findElement(By.name("uid")).sendKeys(username);
		
		
		driver.findElement(By.name("password")).clear();
		driver.findElement(By.name("password")).sendKeys(password);
		
		driver.findElement(By.name("btnLogin")).click();
		
		verifyDynamicText("html/body/table/tbody/tr/td/table/tbody/tr[3]/td",username);
		Assert.assertEquals(Util.EXPECT_TITLE, driver.getTitle());
		
	}
	
	//verifyText
		public static boolean verifyDynamicText(String object,String data){
			 System.out.println("verifying Text");
			 try{	 
					
					String actual = driver.findElement(By.xpath(object)).getText().split("\\:")[1].trim();
					System.out.println(actual);
					String expected = data;
					System.out.println(expected);
					if(actual.equals(expected))
						System.out.println("Text Verified");
					else
						System.out.println("Text NOT Verified");
				 }catch (Exception e){
					 System.out.println(e.getMessage());
					 
				 }
			return false;
		}
}
				


