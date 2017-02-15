package utililty;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Iterator;
import java.util.Set;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.NoAlertPresentException;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;






import ExcelRead.Xls_Reader;
import tests.TestBase;

public class TestUtil extends TestBase{
	
	
	static String actualTitle;
	static String actualBoxtitle;
	
	public static void doLogin(String username, String password) throws InterruptedException{
		
		if(isLoggedIn){
			//return;
			logout();
		}
		
//getObject("signIn_Link").click();
		
		
		/*Set<String> WinIds=	driver.getWindowHandles();
		Iterator<String> It = WinIds.iterator();
		
		String popUpWindow = It.next();
		driver.switchTo().window(popUpWindow);*/
		getObject("Username_signin_Input").sendKeys(username);
		
		getObject("Password_signin_Input").sendKeys(password);
		
		getObject("Submit_Button").click();
		
		 /* Determine Pass Fail Status of the Script
         * If login credentials are correct,  Alert(Pop up) is NOT present. An Exception is thrown and code in catch block is executed	
         * If login credentials are invalid, Alert is present. Code in try block is executed 	    
         */
	    try{ 
	        
	       	Alert alt = driver.switchTo().alert();
			actualBoxtitle = alt.getText(); // get content of the Alter Message
			alt.accept();
			if (actualBoxtitle.contains(OR.getProperty("Error_Title_Text"))) { // Compare Error Text with Expected Error Value
			    System.out.println("Test case [" + actualBoxtitle + "] Passed"); 
			} else {
			    System.out.println("Test case [" + actualBoxtitle + "] Failed");
			}
		}    
	    catch (NoAlertPresentException Ex){ 
	    	actualTitle = driver.getTitle();
			// On Successful login compare Actual Page Title with Expected Title
			if (actualTitle.contains(OR.getProperty("Page_Title_Text"))) {
			    System.out.println("Test case [" + actualTitle + "]Passed");
			} else {
			    System.out.println("Test case [" + actualTitle + "] Failed");
			}
	    }
		//driver.close();//close current windows
		
		
		//Thread.sleep(5000L);
		/*try{
			//get object throws error messages so by xpath is used
			String displayedUserName=driver.findElement(By.xpath(OR.getProperty("Username_TopLink"))).getText();
		
			if(displayedUserName.equals(username)){
				isLoggedIn=true;
			}else{
			isLoggedIn =false;
			}
		}catch(Throwable t){
			isLoggedIn =false;*/

		}	
	
	//
	
	public static void logout(){
		if(isLoggedIn){
			getObject("//*[@id='logindiv']").click();
    		getObject("//*[@id='logged_in_div']/li[6]/a").click();
			isLoggedIn=false;
		}
	}
	
	// get the skip condition
	// true - N
	// false - Y
	public static boolean isSkip(String testCase){
		
		for(int rowNum=2 ; rowNum<=datatable.getRowCount("Test Cases");rowNum++){
			if(testCase.equals(datatable.getCellData("Test Cases", "TCID", rowNum))){
				if(datatable.getCellData("Test Cases", "Runmode", rowNum).equals("Y"))
					return false;
				else
					return true;
			}
		}
		
		return false;
	}
	
	// get the data from xls file
	public static Object[][] getData(String testName){
		// return test data;
		// read test data from xls
		if(datatable == null){
			// load the suite 1 sheet
			datatable = new Xls_Reader(System.getProperty("user.dir")+"//src//config//Suite1.xlsx");
			
		}
		
		int rows=datatable.getRowCount(testName)-1;
		if(rows <=0){
			Object[][] testData =new Object[1][0];
			return testData;
			
		}
	    rows = datatable.getRowCount(testName);  // 3
		int cols = datatable.getColumnCount(testName);
		System.out.println("Test Name -- "+testName);
		System.out.println("total rows -- "+ rows);
		System.out.println("total cols -- "+cols);
		Object data[][] = new Object[rows-1][cols];
		
		for(int rowNum = 2 ; rowNum <= rows ; rowNum++){
			
			for(int colNum=0 ; colNum< cols; colNum++){
				data[rowNum-2][colNum]=datatable.getCellData(testName, colNum, rowNum);
			}
		}
		
		return data;
		
	}
	
	// store screenshots
	public static void takeScreenShot(String fileName) {
		File scrFile = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
	    try {
			FileUtils.copyFile(scrFile, new File(System.getProperty("user.dir")+"\\screenshots\\"+fileName+".jpg"));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}	   
	    
	}
	
	// make zip of reports
	public static void zip(String filepath){
	 	try
	 	{
	 		File inFolder=new File(filepath);
	 		File outFolder=new File("Reports.zip");
	 		ZipOutputStream out = new ZipOutputStream(new BufferedOutputStream(new FileOutputStream(outFolder)));
	 		BufferedInputStream in = null;
	 		byte[] data  = new byte[1000];
	 		String files[] = inFolder.list();
	 		for (int i=0; i<files.length; i++)
	 		{
	 			in = new BufferedInputStream(new FileInputStream
	 			(inFolder.getPath() + "/" + files[i]), 1000);  
	 			out.putNextEntry(new ZipEntry(files[i])); 
	 			int count;
	 			while((count = in.read(data,0,1000)) != -1)
	 			{
	 				out.write(data, 0, count);
	 			}
	 			out.closeEntry();
  }
  out.flush();
  out.close();
	 	
}
  catch(Exception e)
  {
	  e.printStackTrace();
  } 
 }	
	
	//verifyText
			public static boolean verifyText(String object,String data){
				 System.out.println("verifying Text");
				 try{	 
						
						String actual = driver.findElement(By.xpath(OR.getProperty(object))).getText();
						String expected = OR.getProperty(data);
						if(actual.equals(expected))
							System.out.println("Text Verified");
						else
							System.out.println("Text NOT Verified");
					 }catch (Exception e){
						 System.out.println(e.getMessage());
						 
					 }
				return false;
			}	
	
	
}
