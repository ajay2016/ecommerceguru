package mobilemenu;

import static org.junit.Assert.assertEquals;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

public class DiscountVerification {
	
	 public static double GURU50 = 0.05;
	
	public static void main(String[] args) {
		WebDriver driver = new FirefoxDriver();
	    driver.get("http://live.guru99.com/index.php/");	    
	    driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
		
		// 2a. Go to Mobile 
	    driver.findElement(By.linkText("MOBILE")).click();
	    
	    
	 // 2b. and add IPHONE to cart
	    driver.findElement(By.xpath(".//*[@id='top']/body/div[1]/div/div[2]/div/div[2]/div[1]/div[3]/ul/li[3]/div/div[3]/button")).click();
	    
	    
	    driver.findElement(By.id("coupon_code")).clear();
	    driver.findElement(By.id("coupon_code")).sendKeys("GURU50");
	   
	    driver.findElement(By.xpath("//button[@title='Apply']")).click();
	    
	    
	    
	 // Remove the $ sign and comma from the string in one step
	 // this is the Sub Total amount displayed (e.g. $500.00)
	    String vSubTot = driver.findElement(By.xpath(".//*[@id='shopping-cart-totals-table']/tbody/tr[1]/td[2]/span")).getText().split("\\$")[1].replace(",", "");
	    
	    // this is the discount amount displayed (e.g. $50.00)
	    String vDiscDisp = driver.findElement(By.xpath(".//*[@id='shopping-cart-totals-table']/tbody/tr[2]/td[2]/span")).getText().split("\\$")[1].replace(",", "");
	    // Remove the - negative sign
        String sNegDisc = vDiscDisp.replaceAll("\\-", " ");              // e.g. -25.00
	    
	    // this is the Generated discount amount displayed (e.g. $475.00)
	    String vDiscountedAmtDisplayed = driver.findElement(By.xpath(".//*[@id='shopping-cart-totals-table']/tfoot/tr/td[2]/strong/span")).getText().split("\\$")[1].replace(",", "");
	    		     
	    // ***************************************************************************************************************************
	    // discount amount calculations and comparisons between the derived amount against the discount amount displayed on the page
	    // ***************************************************************************************************************************		    
	    
	    double dSubTot = Double.parseDouble(vSubTot);                                 // e.g.        $500.00
	    double dDiscDisp = Double.parseDouble(sNegDisc);                             // e.g.         $25.00
	    double dDiscountedAmtDisplayed = Double.parseDouble(vDiscountedAmtDisplayed); // e.g.        $475.00
	    
	    // multiply the dSubTot by the GURU50 discount rate (GURU50 = 5% = 0.05) 
	    double discountedAmt = (dSubTot * GURU50);     // discountedAmt is the calculated discounted amount (e.g. $25.00)	
	    double dDiscPrice = (dSubTot - discountedAmt); // e.g. Discounted Price (e.g. $475) = Sub Total ($500.00) less the 5% discount amount ($25.00)
	    
	    // ************************************************************************************************
	    //   compare & assert that the displayed discount amount is equal to the derived discount amount
	    // ************************************************************************************************
	    // the amount of discount (e.g. $25.00 = $500 * 5%) compared to the amount of discount as displayed on the page (e.g. $25)
	    if (discountedAmt == dDiscDisp){
	    		System.out.println("*** Derived discount amount   = " + discountedAmt);
	    		System.out.println("*** Displayed discount amount = " + dDiscDisp);
	    }else
	      { System.out.println("*** Derived discount amount not equal displayed discount amount ***");
	    }	
	    
	    String string_discountedAmt = Double.toString(discountedAmt);
	    String string_dDiscDisp = Double.toString(dDiscDisp);
	    try {
	        assertEquals(string_discountedAmt, string_dDiscDisp);
	      } catch (Error e) {
	    	  System.out.println("*** Derived discount amount not equal displayed discount amount ***");
	    	  e.printStackTrace();	
	      }
	 
	    // **************************************************************************************************************************************
	    // compare & assert that the displayed discounted price is equal to the derived discounted price
	    // derived discounted price (e.g. $475.00 = $500 less $25)) compared to the displayed discounted price ($475.00) as displayed on the page
	    // **************************************************************************************************************************************
	    if (dDiscPrice == dDiscountedAmtDisplayed){
    		System.out.println("*** Derived discounted price   = " + dDiscPrice);
    		System.out.println("*** Displayed discounted price = " + dDiscountedAmtDisplayed);
    		System.out.println("***Prices has been verified***");
    		
	    }else
	    	{ System.out.println("*** Derived discounted price not equal displayed discounted price ***");
	    }	
	    
	    String string_dDiscPrice = Double.toString(dDiscPrice);
	    String string_discountedAmtDisplayed = Double.toString(dDiscountedAmtDisplayed);
	    try {
	        assertEquals(string_dDiscPrice, string_discountedAmtDisplayed);
	      } catch (Error e) {
	    	  System.out.println("*** Derived discount amount not equal displayed discount amount ***");
	    	  e.printStackTrace();	
	      }
  }


	    
	}


