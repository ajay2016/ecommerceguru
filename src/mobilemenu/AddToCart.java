/*      
Test Steps:
1. Goto http://live.guru99.com/
2. Click on ‘MOBILE’ menu
3. In the list of all mobile , click on ‘ADD TO CART’ for Sony Xperia mobile
4. Change ‘QTY’ value to 1000 and click ‘UPDATE’ button. Expected that an error is displayed 
   "The requested quantity for "Sony Xperia" is not available.
5. Verify the error message
6. Then click on ‘EMPTY CART’ link in the footer of list of all mobiles. A message "SHOPPING CART IS EMPTY" is shown.
7. Verify cart is empty
*/



package mobilemenu;

import java.io.File;
import java.io.IOException;
import java.util.concurrent.TimeUnit;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

public class AddToCart {

	public static void main(String[] args) throws IOException {
		 WebDriver driver = new FirefoxDriver();
		    driver.get("http://live.guru99.com/index.php/");	    
		    driver.manage().window().maximize();
			driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
			
			//Click the mobile link
			driver.findElement(By.xpath("//*[@id='nav']/ol/li[1]/a")).click();
			driver.findElement(By.xpath("//*[@id='top']/body/div[1]/div/div[2]/div/div[2]/div[1]/div[3]/ul/li[2]/div/div[3]/button")).click();
			driver.findElement(By.xpath("//*[@id='shopping-cart-table']/tbody/tr/td[4]/input")).sendKeys("1000");
			driver.findElement(By.xpath("//*[@id='shopping-cart-table']/tbody/tr/td[4]/button")).click();
			String text = driver.findElement(By.xpath("//*[@id='shopping-cart-table']/tbody/tr/td[2]/p")).getText();
			System.out.println(text);
			
			//Screenshot to verify Name is displayed in sort list menu
			File srcFile = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
			FileUtils.copyFile(srcFile, new File(System.getProperty("user.dir")+"\\addtocarterror.jpg"));
			
			driver.findElement(By.xpath("//*[@id='empty_cart_button']")).click();
			
			String text1 = driver.findElement(By.xpath("//*[@id='top']/body/div[1]/div/div[2]/div/div/div[1]/h1")).getText();
			System.out.println(text1);
			
			//Verify text Display
			if(text1.contains("SHOPPING CART IS EMPTY")){
				System.out.println("Title Verified as  " +text1);
			}else
				System.out.println("Title not Verified");

	}

}
