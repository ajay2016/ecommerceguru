/*      
Test Steps:
1. Goto http://live.guru99.com/
2. Click on ‘MOBILE’ menu
3. In mobile compare list  , click on ‘ADD TO COMPARE’ for Sony Xperia mobile and iphone
4. Click on compare button
5. Verify the pop up window and products are reflected on the page
6. Close the pop up window
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

public class CompareProducts {

	public static void main(String[] args) throws IOException {
		 WebDriver driver = new FirefoxDriver();
		    driver.get("http://live.guru99.com/index.php/");	    
		    driver.manage().window().maximize();
			driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
			
			//Click the mobile link
			driver.findElement(By.xpath("//*[@id='nav']/ol/li[1]/a")).click();
			
			//Click on add to compare
			driver.findElement(By.xpath("//*[@id='top']/body/div[1]/div/div[2]/div/div[2]/div[1]/div[3]/ul/li[1]/div/div[3]/ul/li[2]/a")).click();
			driver.findElement(By.xpath("//*[@id='top']/body/div[1]/div/div[2]/div/div[2]/div[1]/div[3]/ul/li[2]/div/div[3]/ul/li[2]/a")).click();
			
			//Click on compare button
			driver.findElement(By.xpath("//*[@id='top']/body/div[1]/div/div[2]/div/div[3]/div[1]/div[2]/div/button")).click();
			
			//for pop up windows
			Set<String> WinIds=	driver.getWindowHandles();
			Iterator<String> It = WinIds.iterator();
			String mainWindow = It.next();
			String popUpWindow = It.next();
			
			/*System.out.println(mainWindow);
			System.out.println(popUpWindow);*/
			
			
			//Switch to pop up window
			driver.switchTo().window(popUpWindow);
			String text = driver.findElement(By.xpath("//*[@id='top']/body/div[1]/div[1]/h1")).getText();
			System.out.println(text);
			
			
			//Screenshot to verify Name is displayed in sort list menu
			File srcFile = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
			FileUtils.copyFile(srcFile, new File(System.getProperty("user.dir")+"\\compareproducts.jpg"));
			
			
			//close pop up windows
			driver.close();//close current windows
			driver.switchTo().window(mainWindow);
			
			
	}

}
