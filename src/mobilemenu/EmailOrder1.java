package mobilemenu;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Iterator;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import javax.mail.MessagingException;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import expertsolutions.EmailUtil;

public class EmailOrder1 {
	
	
	  private static String vUsername = "user01";
	  private static String vPassword = "guru99com";


	public static void main(String[] args) {
		     WebDriver driver = new FirefoxDriver();
		    driver.get("http://live.guru99.com/index.php/backendlogin");	    
		    driver.manage().window().maximize();
			driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);

		/*	
			
		//  2. Login the credentials provided
			
						driver.findElement(By.xpath("//input[@id = 'username']")).sendKeys(vUsername);	
							
							driver.findElement(By.xpath("//input[@id = 'login']")).sendKeys(vPassword);
							driver.findElement(By.xpath("//input[@value = 'Login']")).click();
							
							//for pop up windows
							Set<String> WinIds=	driver.getWindowHandles();
							Iterator<String> It = WinIds.iterator();
							//System.out.println("Total Windows Opened    " + WinIds.size());//2
							String mainWindow = It.next();
							String popUpWindow = It.next();
							
							System.out.println(mainWindow);
							System.out.println(popUpWindow);
							
							
							//Switch to pop up window
							driver.switchTo().window(popUpWindow);
							driver.close();//close current windows
							driver.switchTo().window(mainWindow);
							
							
							 //  3a. Go to Sales-> Orders menu                                                    // click the Sales tab
						    
						    // click the Sales tab 
						    driver.findElement(By.linkText("Sales")).click(); 
						    
						    
						    try {Thread.sleep(5000);}catch(Exception e){};
						    
						    //  3b. Go to Sales-> Orders menu
						    
						    driver.findElement(By.linkText("Orders")).click();                                    // click the Orders sub tab
						    
						    
						//  4. Select 'CSV' in Export To dropdown and click Export button.		
							
							driver.findElement(By.xpath("//button[@title = 'Export']")).click();
							*/
							
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

}
