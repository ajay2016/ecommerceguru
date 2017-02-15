package mobilemenu;

import java.io.File;
import java.io.IOException;
import java.util.concurrent.TimeUnit;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.Select;

public class Mobile {
    static WebDriver driver;
	public static void main(String[] args) throws IOException {
		    driver = new FirefoxDriver();
		    driver.get("http://live.guru99.com/index.php/");	    
		    driver.manage().window().maximize();
			driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
			
			//Get the title of the Website
			String TitlePage = driver.getTitle();
			System.out.println(TitlePage);
			 //Verify Title
			if(TitlePage.contains("Home page") ){
				System.out.println("Title matched");
			}else
				System.out.println("Title not matched");
			
			//Click the mobile link
			driver.findElement(By.xpath("//*[@id='nav']/ol/li[1]/a")).click();
			String text = driver.findElement(By.xpath("//*[@id='top']/body/div[1]/div/div[2]/div/div[2]/div[1]/div[1]/h1")).getText();
			System.out.println(text);
			
			
			
			//Verify text Display
			if(text.contains("MOBILE")){
				System.out.println("Title Verified as  " +text);
			}else
				System.out.println("Title not Verified");
			
			//Click on the  menu with select
			WebElement dropDownMenu =  driver.findElement(By.xpath("//*[@id='top']/body/div[1]/div/div[2]/div/div[2]/div[1]/div[3]/div[1]/div[1]/div/select"));
			Select clickThis = new Select(dropDownMenu);
			clickThis.selectByVisibleText("Name");
			
			//Verify title
			String text1 = driver.findElement(By.xpath("//*[@id='top']/body/div[1]/div/div[2]/div/div[2]/div[1]/div[3]/div[1]/div[1]/div/select")).getText();
			
			if(text1.contains("Name")){
				System.out.println("Title Verified");
			}else
				System.out.println("Title not Verified");
			
			//Screenshot to verify Name is displayed in sort list menu
			File srcFile = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
			FileUtils.copyFile(srcFile, new File(System.getProperty("user.dir")+"\\guru.jpg"));
			
			//quit the firefox browser
			driver.close();
	}
	
	
}

