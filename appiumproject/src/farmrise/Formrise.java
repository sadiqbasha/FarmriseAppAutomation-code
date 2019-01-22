package farmrise;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.text.SimpleDateFormat;

import java.util.Date;


import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import io.appium.java_client.MobileElement;
import io.appium.java_client.TouchAction;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.nativekey.AndroidKey;
import io.appium.java_client.android.nativekey.KeyEvent;
import io.appium.java_client.touch.offset.ElementOption;

public class Formrise {

	public static void main(String[] args) throws Exception {	
		//start Appium server
				Runtime.getRuntime().exec("cmd.exe /c start cmd.exe /k \"appium -a 127.0.0.1 -p 4723\" ");
				//get appium address 
				URL u = new URL("http://127.0.0.1:4723/wd/hub");
				//maintain details of app and ARD
				DesiredCapabilities dc= new DesiredCapabilities();
				dc.setCapability(CapabilityType.BROWSER_NAME,"");
				dc.setCapability("deviceName","ZH33L29FQW");
				dc.setCapability("platformName","android");
				dc.setCapability("platformVersion","7.1.1");
				dc.setCapability("automationName","uiautomator2");
				dc.setCapability("appPackage","com.android.dialer");
				dc.setCapability("appActivity","com.android.dialer.DialtactsActivity");
				//create driver object
				AndroidDriver driver;
				while(2>1) {
					try {
						driver=new AndroidDriver(u,dc);
						break;
					}
					catch(Exception ex1) {
						System.out.println(ex1.getMessage());	
					}
				}
			
				
				//go to home screen
				KeyEvent k= new KeyEvent(AndroidKey.HOME);
			    driver.pressKey(k);
			    Thread.sleep(5000);
			    //launch "FormRise app"
			    driver.findElement(By.xpath("//*[@text='FarmRise']")).click();
			    Thread.sleep(5000);
			    WebDriverWait wait= new WebDriverWait(driver,20);
				// select any language
				wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath
						            ("//*[@text='Choose your preferred language']")));
				driver.findElement(By.xpath("//*[@text='English']")).click();
				//click on proceed
				driver.findElement(By.xpath("//*[@text='Proceed']")).click();
				//click on agree and continue
				wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@text='Agree and Continue']")));
				driver.findElement(By.xpath("//*[@text='Agree and Continue']")).click();
				
				//Add any crop and proceed.
				wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@text='Cauliflower']")));
				driver.findElement(By.xpath("//*[@text='Cauliflower']")).click();
				
				//allo access to device location
				wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@text='Allow and Proceed']")));
				driver.findElement(By.xpath("//*[@text='Allow and Proceed']")).click();
				wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@text='ALLOW']")));
				driver.findElement(By.xpath("//*[@text='ALLOW']")).click();
				
				//navigation to home page
				wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@text='Home']")));
				driver.findElement(By.xpath("//*[@text='OK']")).click();
				
				wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@text='Mandi Prices']")));
				driver.findElement(By.xpath("//*[@text='OK']")).click();
				
				wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@text='Agronomy']")));
				driver.findElement(By.xpath("//*[@text='OK']")).click();
				
				wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@text='Chat']")));
				driver.findElement(By.xpath("//*[@text='OK']")).click();
				
				wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@text='More']")));
				driver.findElement(By.xpath("//*[@text='OK']")).click(); 
				
				// validate home screen tabs
				if(driver.findElement(By.xpath("//*[@text='Home']")).isDisplayed()) {
					System.out.println("homeScreen tabs test is passed");					
				}
				else {
					System.out.println("homeScreen tabs test is failed");
				}
				
				// Scenario 1(weather access details validation)
				WebElement e=driver.findElement(By.xpath("//*[@text='More']"));
				TouchAction ta = new TouchAction(driver);
				ta.tap(ElementOption.element(e)).perform();
				wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@text='Weather']")));
				WebElement e1=driver.findElement(By.xpath("//*[@text='Weather']"));
				ta.tap(ElementOption.element(e1)).perform();
				Thread.sleep(5000);
				int flag=0;
				try {
					
					if(driver.findElement(By.xpath("//*[@text='Try Again']")).isDisplayed()) {
						driver.findElement(By.xpath("//*[@text='Try Again']")).click();
						Thread.sleep(5000);				
					  }
				if(flag==0) {
					System.out.println("Weather timings test is failed");
					//take Screeenshot
					SimpleDateFormat sf = new SimpleDateFormat("dd-MM-yy-hh-mm-ss");
					Date d= new Date();
					String fname=sf.format(d)+".png";
					File src= driver.getScreenshotAs(OutputType.FILE);
					File dst=new File(fname);
					FileUtils.copyFile(src, dst);
				   }
				}
				catch(Exception ex1) {
					System.out.println(ex1.getMessage());
				   }
				
				//scenario 2(government schemes)
				// click on 'more' tab
				wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@text='More']")));
				driver.findElement(By.xpath("//*[@text='More']")).click();
				//navigate to more screen
				wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@text='Government Schemes']")));
				//tap on 'government schemes'
				WebElement e2= driver.findElement(By.xpath("//*[@text='Government Schemes']"));
				ta.tap(ElementOption.element(e2)).perform();
				
				//scroll to bottom for "Load More schemes"
				while(2>1) {
					try {
						if(driver.findElement(By.xpath("//*[@text='Load More schemes']")).isDisplayed()) {
							driver.findElement(By.xpath("//*[@text='Load More schemes']")).click();
							break;
						}
					}
					catch(Exception ex2) {
						try {
						ta.press(ElementOption.point(400,1000)).moveTo(ElementOption.point(400,300)).release().perform();
						}
						catch(Exception ex3) {
						System.out.println("\"load more schemes\" button is not available.Hence load more schemes test is failed");
						break;
						}
					  }
				     }
				
				//scenario 3(search "scheme" in government schemes)
				wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@text='More']")));
				driver.findElement(By.xpath("//*[@text='More']")).click();
				wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@text='Government Schemes']")));
				//tap on 'government schemes'
				WebElement e3= driver.findElement(By.xpath("//*[@text='Government Schemes']"));
				ta.tap(ElementOption.element(e3)).perform();
				
				wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@content-desc='Search']")));
				WebElement e4=driver.findElement(By.xpath("//*[@content-desc='Search']"));
				ta.tap(ElementOption.element(e4)).perform();
				//enter "schemes" in search box
				driver.findElement(By.xpath("//*[@text='Search']")).sendKeys("schemes");
				Thread.sleep(5000);
				//validate results
				if(driver.findElement(By.xpath("//*[@text='Unread']")).isDisplayed()){
					System.out.println("schemes result test is passed");				
				   }
				else {
					System.out.println("schemes result test is failed");
					//take Screeenshot
					SimpleDateFormat sf = new SimpleDateFormat("dd-MM-yy-hh-mm-ss");
					Date d= new Date();
					String fname=sf.format(d)+".png";
					File src= driver.getScreenshotAs(OutputType.FILE);
					File dst=new File(fname);
					FileUtils.copyFile(src, dst);
				}
	           //close app
		       driver.closeApp();
              //stop appium server
	       	Runtime.getRuntime().exec("taskkill /F /IM node.exe");
		    Runtime.getRuntime().exec("taskkill /F /IM cmd.exe");

	}

}
