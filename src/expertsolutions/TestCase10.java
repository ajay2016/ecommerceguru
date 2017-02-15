//********************************************************************************************
//*    Guru99 eCommerce Live Project    
//*    Participant : Krishna Rungta                   
//*                                                                                                                                                         *
//********************************************************************************************
/* Test Case requirements/descriptions:
 *    Export all Orders in csv file and display these information in console and 
 *    you are able to send this file to another email id as attachment 
 * Note: Access to backend of the site. UserId "user01" and pw "user001"
 *    
1. Go to http://live.guru99.com/index.php/backendlogin
2. Login the credentials provided
3. Go to Sales-> Orders menu
4. Select 'CSV' in Export To dropdown and click Export button.
5. Read downloaded file and display all order information in console windows
6. Attach this exported file and email to another email id

Expected results:
1) Console displays all order information
2) Email is sent successfully
*/

package expertsolutions;

import org.testng.annotations.AfterTest;
import org.testng.annotations.Test;
import org.testng.annotations.BeforeTest;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Iterator;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import javax.mail.MessagingException;

import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class TestCase10 {
	  private WebDriver driver;
	  private String baseUrl;
	  private String vUsername = "user01";
	  private String vPassword = "guru99com";
	  
	@BeforeTest
	public void setUp() throws Exception {
		System.setProperty("webdriver.chrome.driver", "D:\\webdriver\\chromedriver.exe");
	    driver = new ChromeDriver();
	    baseUrl = "http://live.guru99.com/index.php/backendlogin";
	    driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
	  }
	  
	  @Test
	  public void testTestCase10() throws Exception {
		  
		    //  1. Go to http://live.guru99.com/index.php/backendlogin			
		    driver.get(baseUrl);
			
			
			//  2. Login the credentials provided
			
			driver.findElement(By.xpath("//input[@id = 'username']")).sendKeys(vUsername);	
			
			driver.findElement(By.xpath("//input[@id = 'login']")).sendKeys(vPassword);
			driver.findElement(By.xpath("//input[@value = 'Login']")).click();
			
			//for pop up windows
			Set<String> WinIds=	driver.getWindowHandles();
			Iterator<String> It = WinIds.iterator();
			String mainWindow = It.next();
			String popUpWindow = It.next();
			
			/*System.out.println(mainWindow);
			System.out.println(popUpWindow);*/
			
			
			//Switch to pop up window
			driver.switchTo().window(popUpWindow);
			driver.close();//close current windows
			driver.switchTo().window(mainWindow);
			
			try {Thread.sleep(5000);}catch(Exception e){};
			
			for (String handle : driver.getWindowHandles()) {                                             
		    	driver.switchTo().window(handle);
		    	} 	    		
		    		   		    
		    //  3a. Go to Sales-> Orders menu                                                    // click the Sales tab
		    
		    // click the Sales tab 
		    driver.findElement(By.linkText("Sales")).click(); 
		    
		    
		    try {Thread.sleep(5000);}catch(Exception e){};
		    
		    //  3b. Go to Sales-> Orders menu
		    
		    driver.findElement(By.linkText("Orders")).click();                                    // click the Orders sub tab
		    
		    
		    try {Thread.sleep(5000);}catch(Exception e){};
		    
			
			for (String handle : driver.getWindowHandles()) {                                             
		    	driver.switchTo().window(handle);
		    	} 
			
			try {Thread.sleep(5000);}catch(Exception e){};			
						
			//  4. Select 'CSV' in Export To dropdown and click Export button.		
			
			driver.findElement(By.xpath("//button[@title = 'Export']")).click();
			
			
			try {
				Thread.sleep(5000);
			} catch (InterruptedException e2) {
				// TODO Auto-generated catch block
				e2.printStackTrace();
			}
			
			
			String filePath = System.getProperty("user.home")+"/Downloads/orders.csv";
			try {
				EmailUtil.emailUtil(filePath);
				//Mail.mail(filePath);
			} catch (MessagingException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			// 5. Read downloaded file and display the Heading and all the Order details in the console windows
			File f = new File(filePath);
			try {
				FileReader fr = new FileReader(f);
				BufferedReader br = new BufferedReader(fr);
				String line = br.readLine();
				while(line!=null){
					System.out.println(line);
					line = br.readLine();
				}
				fr.close();
				br.close();
			} catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			  
	  }
	
	@AfterTest
	public void tearDown() throws Exception {
		Thread.sleep(3000);
		driver.quit();
	  }
	}
