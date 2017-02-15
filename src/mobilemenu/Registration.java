/* TEST STEPS
1. Got to http://live.guru.com
2. Click on my account link
3. Click create account link and fill user information except Email id
4. click register 
5. Verify registration is done
6. Go to TV menu
7. Add products in your wish list
8 click SHARE WISHLIST
9. IN THE NEXT PAGE enter email and a message and click SHARE WISHLIST
10. CHECK WISHLIST SHARED

*/

package mobilemenu;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

public class Registration {

	public static void main(String[] args) {
		WebDriver driver = new FirefoxDriver();
	    driver.get("http://live.guru99.com/index.php/");	    
	    driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);

		
		//Click on my account link 
		driver.findElement(By.xpath("//*[@id='top']/body/div[1]/div/div[3]/div/div[4]/ul/li[1]/a")).click();
		
		//Click create account link and fill user information except Email id
		driver.findElement(By.xpath("//*[@id='login-form']/div/div[1]/div[2]/a/span/span")).click();
		driver.findElement(By.xpath("//*[@id='firstname']")).sendKeys("Ajay1");
		driver.findElement(By.xpath("//*[@id='lastname']")).sendKeys("Raj");
		driver.findElement(By.xpath("//*[@id='email_address']")).sendKeys("ajayraj1711@hotmail.com");
		driver.findElement(By.xpath("//*[@id='password']")).sendKeys("nepali");
		driver.findElement(By.xpath("//*[@id='confirmation']")).sendKeys("nepali");
		
		
		
		// click register 
		driver.findElement(By.xpath("//*[@id='form-validate']/div[2]/button")).click();
		
		//Verify registration is done
		String text = driver.findElement(By.xpath("//*[@id='top']/body/div[1]/div/div[2]/div/div[2]/div/div/ul/li/ul/li/span")).getText();
		System.out.println(text);
		
		//Verify text Display
		if(text.contains("Thank you for registering with Main Website Store.")){
			System.out.println("Registration Verified as  " +text);
		}else
			System.out.println("Registration not Verified");
		
		//Go to TV menu		
		driver.findElement(By.xpath("//*[@id='nav']/ol/li[2]/a")).click();
		
		//Add products in your wish list
		driver.findElement(By.xpath("//*[@id='top']/body/div[1]/div/div[2]/div/div[2]/div[1]/div[2]/ul/li[1]/div/div[2]/ul/li[1]/a")).click();
		
		//click SHARE WISHLIST
		driver.findElement(By.xpath("//*[@id='wishlist-view-form']/div/div/button[1]")).click();
		
		
		//IN THE NEXT PAGE enter email and a message and click SHARE WISHLIST 		
		driver.findElement(By.xpath("//*[@id='email_address']")).sendKeys("ajayraj17@hotmail.com");
		driver.findElement(By.xpath("//*[@id='message']")).sendKeys("LG");
		driver.findElement(By.xpath("//*[@id='form-validate']/div[2]/button")).click();
		
		
		//
		
		String text1 = driver.findElement(By.xpath("//*[@id='top']/body/div[1]/div/div[2]/div/div[2]/div/div[1]/ul/li/ul/li/span")).getText();
		System.out.println(text1);
		
		//Verify text Display
				if(text1.contains("Your Wishlist has been shared.")){
					System.out.println("Text Verified as  " +text1);
				}else
					System.out.println("Text not Verified");
	}

}
