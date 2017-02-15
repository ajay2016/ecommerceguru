/*
1. Go to http://live.guru99.com
2. Click on My Account link
3. Login in application using previously created credentials
4. Click on MY WISHLIST link
5. in the next pAGE,click ADD TO CART LINK
6. CLICK proceed to checkout
7. Enter shipping information
8. Click estimate
9. Verify shipping cost generated
10. Select shipping cost, Update total
11. Verify shipping cost added to the total
12. Click PROCEED TO  CHECKOUT
13. ENTER BILLING INFORMATION
14. IN THE SHIPPING METHOD CLICK CONTINUE
15. IN THE PAYMENT INFORMATION SELECT CHECK/MONEY ORDER RADIO BUTTON
16. CLICK PLACE ORDER
17. VERIFY ORDER IS GENERATED. NOTE THE ORDER NUMBER



*/


package mobilemenu;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.Select;
import org.testng.annotations.AfterTest;

public class OrderProduct {
	
	  public static String firstName = "Ajay";    // These testdata stuff will be placed    
	  public static  String lastName = "Raj";  // in a TestData EXCEL file at some stage	  
	  public static  String vEmail = "ajayraj17@hotmail.com";
	  public static String vPW = "nepali";


	public static void main(String[] args) {
		WebDriver driver = new FirefoxDriver();
	    driver.get("http://live.guru99.com/index.php/");	    
	    driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
		
		
		//Click on my account link 
				driver.findElement(By.xpath("//*[@id='top']/body/div[1]/div/div[3]/div/div[4]/ul/li[1]/a")).click();
				
				
				//Login in application using previously created credentials
				driver.findElement(By.xpath("//*[@id='email']")).sendKeys(vEmail);
				driver.findElement(By.xpath("//*[@id='pass']")).sendKeys(vPW);
				driver.findElement(By.xpath("//*[@id='send2']")).click();
				
				
				
				//Go to TV menu		
				driver.findElement(By.xpath("//*[@id='nav']/ol/li[2]/a")).click();
				
				//Add products in your wish list 
				driver.findElement(By.xpath("//*[@id='top']/body/div[1]/div/div[2]/div/div[2]/div[1]/div[2]/ul/li[1]/div/div[2]/ul/li[1]/a")).click();
				
				//Click on MY WISHLIST link
				//driver.findElement(By.xpath("//*[@id='top']/body/div[1]/div/div[2]/div/div[1]/div/div[2]/ul/li[8]/a")).click();
				
				//in the next PAGE,click ADD TO CART LINK				
				driver.findElement(By.xpath("//*[@id='wishlist-view-form']/div/div/button[2]")).click();
				
				//Enter shipping information
				driver.findElement(By.xpath("//*[@id='country']")).sendKeys("United States");
				driver.findElement(By.xpath("//*[@id='region_id']")).sendKeys("New York");
				driver.findElement(By.xpath("//*[@id='postcode']")).sendKeys("542896");
				
				// Click estimate
				driver.findElement(By.xpath("//*[@id='shipping-zip-form']/div/button")).click();
				
				//Verify shipping cost generated
				String text = driver.findElement(By.xpath("//*[@id='co-shipping-method-form']/dl/dd/ul/li/label/span")).getText();
				System.out.println(text);
				
				//Select shipping cost, Update total
				driver.findElement(By.xpath("//*[@id='s_method_flatrate_flatrate']")).click();
				driver.findElement(By.xpath("//*[@id='co-shipping-method-form']/div/button")).click();
				
				
				// Remove the $ sign and comma from the string in one step
				//Verify shipping cost added to the total
				
				String subtotal = driver.findElement(By.xpath("//*[@id='shopping-cart-totals-table']/tbody/tr[1]/td[2]/span")).getText().split("\\$")[1].replace(",", "");
				System.out.println(subtotal);
				
				
				String shippingfee = driver.findElement(By.xpath("//*[@id='shopping-cart-totals-table']/tbody/tr[2]/td[2]/span")).getText().split("\\$")[1].replace(",", "");
				System.out.println(shippingfee);
				
				
				
				
				String grandtotal = driver.findElement(By.xpath("//*[@id='shopping-cart-totals-table']/tfoot/tr/td[2]/strong/span")).getText().split("\\$")[1].replace(",", ""); 
				System.out.println(grandtotal);
				
				
				/* // Remove the $ sign 
			    String subtotal1 = subtotal.replaceAll("\\,", " ");                // e.g. $500.00	  
			    System.out.println(subtotal1);
		        String shippingfee1 =shippingfee.replaceAll("\\,", " ");            // e.g. $25.00	        
		        String grandtotal1 = grandtotal.replaceAll("\\,", " ");
		        
		        
		        // trim the String variables to get it ready for calculation
		        String fsubtotal = subtotal1.trim();                                 // e.g. $500.00			       
		        String fshippingfee = shippingfee1.trim();                              // e.g. $25.00	        
		        String fgrandtotal = grandtotal1.trim(); // e.g. $475.00
		       */ 
		        
		        double dSubTot = Double.parseDouble(subtotal);                                 // e.g.        $500.00
			    double dShipfee = Double.parseDouble(shippingfee);                             // e.g.         $25.00
			    double calculatedtotal = dSubTot + dShipfee;			    
			    double dGtotal = Double.parseDouble(grandtotal); // e.g.        $475.00
			    
				
				// verify the before and after Grand Total price, to confirm it has changed
			    if (calculatedtotal == dGtotal){
			    	   System.out.println("*** Grand Total price has been verified. ***");
			    }else{
				       System.out.println("*** Grand Total price has changed. ***");
				}	
				
				
				
				// 11. Click PROCEED TO CHECKOUT                                                                       
			   /* driver.findElement(By.xpath("//button[@title='Proceed to Checkout']")).click();
			   
			    //Thread.sleep(3000);    
			    
			    
			    // switching to new window                                                                                
			    for (String handle : driver.getWindowHandles()) { 
			    	driver.switchTo().window(handle);
			    	}  
			    //Thread.sleep(2000); 
			    
			    //********************************************************************************************************
			    //*
			    //*  BILLING ADDRESS
			    //*
			    //********************************************************************************************************
			    // Check if Select element is present. If not present, it is the first time a customer has logged back in, to process what is in his/her wishlist
			    
			    try {
			    	Select bAddr = new Select(driver.findElement(By.name("billing_address_id")));
			    	int bAddrSize = bAddr.getOptions().size();
			    	bAddr.selectByIndex(bAddrSize-1); 
				    } catch (Exception e) {
				    	//e.printStackTrace();
				    	System.out.println("No dropdown element present");
				    }
			  
			    driver.findElement(By.id("billing:firstname")).clear();
			    driver.findElement(By.id("billing:firstname")).clear();
			    driver.findElement(By.id("billing:firstname")).sendKeys(firstName); 
			    driver.findElement(By.id("billing:lastname")).clear();
			    driver.findElement(By.id("billing:lastname")).sendKeys(lastName); 
			    driver.findElement(By.id("billing:company")).clear(); 
			    
			    driver.findElement(By.id("billing:street1")).clear();
			    driver.findElement(By.id("billing:street1")).sendKeys("148 Crown Street"); 
			    new Select(driver.findElement(By.xpath("//select[@id='billing:country_id']"))).selectByIndex(14);
			   // Thread.sleep(5000);	    
			    driver.findElement(By.id("billing:city")).clear();	
			    driver.findElement(By.id("billing:city")).sendKeys("Sydney"); 
			    driver.findElement(By.id("billing:region")).clear();
			    driver.findElement(By.id("billing:region")).sendKeys("New South Wales");
			    driver.findElement(By.id("billing:postcode")).clear();
			    driver.findElement(By.id("billing:postcode")).sendKeys("2000");
			    driver.findElement(By.id("billing:telephone")).clear();
			    driver.findElement(By.id("billing:telephone")).sendKeys("8850 6789"); 
			    
			    // check radio button to "Ship to different address" 
		        driver.findElement(By.id("billing:use_for_shipping_no")).click();
			    
		        // click the CONTINUE button 
			   
			    // after the click above, it is still on same web page: live.guru99.com/index.php/checkout/onepage/
			    driver.findElement(By.xpath(".//*[@id='billing-buttons-container']/button")).click();
			    
			    // switching to new window                                                                                
			    for (String handle : driver.getWindowHandles()) {  
			    	driver.switchTo().window(handle);
			    	}  
			   // Thread.sleep(2000); 
			    
			    //********************************************************************************************************
			    //*
			    //*  SHIPPING ADDRESS
			    //*
			    //********************************************************************************************************
			    // Check if Select element is present or not.  If not present, it is first time user has logged back in to process his/her order
			    
			    try {
			    	Select sAddr = new Select(driver.findElement(By.name("shipping_address_id")));
			    	int sAddrSize = sAddr.getOptions().size();
			    	sAddr.selectByIndex(sAddrSize-1); 
				    } catch (Exception e) {
				    	//e.printStackTrace();
				    	System.out.println("No dropdown element present");
				    }
			    
			  
			    //Thread.sleep(3000);   
			   
			    driver.findElement(By.id("shipping:firstname")).clear();
			    driver.findElement(By.id("shipping:firstname")).clear();
			    driver.findElement(By.id("shipping:firstname")).sendKeys(firstName); 
			    driver.findElement(By.id("shipping:lastname")).clear();
			    driver.findElement(By.id("shipping:lastname")).sendKeys(lastName); 
			    driver.findElement(By.id("shipping:company")).clear(); 
			    
			    driver.findElement(By.id("shipping:street1")).clear();
			    driver.findElement(By.id("shipping:street1")).sendKeys("50 Berry Street"); 
			    driver.findElement(By.id("shipping:street2")).clear();
			    // shipping country must be selected first from dropdown
			    new Select(driver.findElement(By.xpath("//select[@id='shipping:country_id']"))).selectByIndex(14); 
			    // once Australia is selected, then the region and city becomes simply a text input, instead of dropdown
			    driver.findElement(By.id("shipping:region")).clear();
			    driver.findElement(By.id("shipping:region")).sendKeys("New Sounth Wales"); 
			    driver.findElement(By.id("shipping:city")).clear();
			    driver.findElement(By.id("shipping:city")).sendKeys("Sydney"); 
			    driver.findElement(By.id("shipping:postcode")).clear();
			    driver.findElement(By.id("shipping:postcode")).sendKeys("2000"); 
			    driver.findElement(By.id("shipping:telephone")).clear();
			    driver.findElement(By.id("shipping:telephone")).sendKeys("8034 1234");
			        
			   // Thread.sleep(3000);	    
			    
			    driver.findElement(By.xpath(".//*[@id='shipping-buttons-container']/button")).click(); 
			    
			    // switching to new window                                                                                
			    for (String handle : driver.getWindowHandles()) {  
			    	driver.switchTo().window(handle);
			    	}  
			   // Thread.sleep(2000); 
			    
			    
			    // 13. In Shipping Method, Click Continue   
			    driver.findElement(By.xpath(".//*[@id='shipping-method-buttons-container']/button")).click(); 	
			   	 
			    //Thread.sleep(2000);
			    
			    // 14. In Payment Information select 'Check/Money Order' radio button. Click Continue
			    driver.findElement(By.xpath("//input[@title='Check / Money order']")).click();
			    
			    //Thread.sleep(3000);	    
			    
			    driver.findElement(By.xpath(".//*[@id='payment-buttons-container']/button")).click(); 
			     
			    //Thread.sleep(3000);
			    
			    // 15. Click 'PLACE ORDER' button  
			    driver.findElement(By.xpath(".//*[@id='review-buttons-container']/button")).click(); 
			    
			    //Thread.sleep(3000);
			    
			    // 16. Verify Oder is generated. Note the order number 
			    String orderNum = driver.findElement(By.xpath(".//*[@id='top']/body/div[1]/div/div[2]/div/div/p[1]/a")).getText();	
			    System.out.println("*** Your order number for your record = " + orderNum);
			    
			    
			    */
			    
			    
			   
			  }
			
			
			


				
				
	{}
{}
}


