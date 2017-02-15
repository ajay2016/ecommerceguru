package mobilemenu;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

public class VirtualJobScout {

	public static void main(String[] args) {
		
		    WebDriver driver = new FirefoxDriver();
		    driver.get("https://www.virtualjobscout.org/");	    
		    driver.manage().window().maximize();
			driver.manage().timeouts().implicitlyWait(40, TimeUnit.SECONDS);
			
			driver.findElement(By.xpath("//*[@id='index-page']/body/div[4]/div[1]/div/div[2]/button")).click();
			driver.findElement(By.xpath("//*[@id='index-page']/body/div[4]/div[1]/div/div[2]/nav/a[5]")).click();
			
		
			
			driver.findElement(By.name("_username")).sendKeys("qatesto586+spouse@gmail.com");
			driver.findElement(By.xpath("//*[@id='password']")).sendKeys("password");
			driver.findElement(By.xpath("//*[@id='_submit']")).click();
			
			

	}

}
