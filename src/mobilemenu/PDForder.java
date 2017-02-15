
/*
1. Go to http://live.guru99.com
2. Click on My Account link
3. Login to the application using previously created credentials
4.Click on My Orders
5. Click on  View Order
6. Verify the previoulsly created order is displayed  in RECENT ORDERS table and status is pending
7. Click on print order link
8. Verify order is saved as PDF





*/


package mobilemenu;

import java.io.File;
import java.io.IOException;
import java.util.Iterator;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

public class PDForder {
	
	
	static WebDriver driver;
	 public static String firstName = "Ajay";    // These testdata stuff will be placed    
	  public static  String lastName = "Raj";  // in a TestData EXCEL file at some stage	  
	  public static  String vEmail = "ajayraj17@hotmail.com";
	  public static String vPW = "nepali";

	public static void main(String[] args) throws IOException {
	     driver = new FirefoxDriver();
	    driver.get("http://live.guru99.com/index.php/");	    
	    driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
		
		
		
		//Click on my account link 
		driver.findElement(By.xpath("//*[@id='top']/body/div[1]/div/div[3]/div/div[4]/ul/li[1]/a")).click();
		
		
		//Login in application using previously created credentials
		driver.findElement(By.xpath("//*[@id='email']")).sendKeys(vEmail);
		driver.findElement(By.xpath("//*[@id='pass']")).sendKeys(vPW);
		driver.findElement(By.xpath("//*[@id='send2']")).click();
		
		//Click on My Orders
		driver.findElement(By.xpath("//*[@id='top']/body/div[1]/div/div[2]/div/div[1]/div/div[2]/ul/li[4]/a")).click();
		
		// Verify the previously created order is displayed  in RECENT ORDERS table and status is pending
		// Breaking into two strings
				String part1 = "//*[@id='my-orders-table']/tbody/tr[";
				String part2 = "]/td[5]/em";
									
				int i =1;
				while(isElementPresent(part1+i+part2)){
					String text = driver.findElement(By.xpath(part1+i+part2)).getText();
					System.out.println("ORDER STATUS IS DISPALYED AS     "+text);
					i++;
				}
				
				
				
			//Click on print order link	
			driver.findElement(By.xpath("//*[@id='my-orders-table']/tbody/tr[1]/td[6]/span/a[1]")).click();	
			driver.findElement(By.xpath("//*[@id='top']/body/div[1]/div/div[2]/div/div[2]/div/div[1]/a[2]")).click();
			
			
			//for pop up windows
			Set<String> WinIds=	driver.getWindowHandles();
			Iterator<String> It = WinIds.iterator();
			String mainWindow = It.next();
			String popUpWindow = It.next();
			
			/*System.out.println(mainWindow);
			System.out.println(popUpWindow);*/
			
			
			//Switch to pop up window
			driver.switchTo().window(popUpWindow);
			String text = driver.findElement(By.xpath("//*[@id='top']/body/div[1]/h1")).getText();
			System.out.println("Your Order infomation as PDF file "+text);
			
			
			//Screenshot 
			File srcFile = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
			FileUtils.copyFile(srcFile, new File(System.getProperty("user.dir")+"\\pdfOrder.jpg"));
			
			
			//close pop up windows
			driver.close();//close current windows
			driver.switchTo().window(mainWindow);
			
		

	}
	
	
	//Check if the Element  is there
	public static boolean isElementPresent(String xPathEl){
		int size =	driver.findElements(By.xpath(xPathEl)).size();
				if(size>0)
					return true;
				else
					return false;
		}
}
