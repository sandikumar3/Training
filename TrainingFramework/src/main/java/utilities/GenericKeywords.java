package utilities;

import java.io.File;
import java.io.IOException;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import com.aventstack.extentreports.ExtentTest;

import base.BaseClass;
//import junit.framework.Assert;

public class GenericKeywords extends BaseClass{

	public static WebElement getElement(String locator){
		WebElement element=null;
		WebDriverWait wait=new WebDriverWait(driver,40);
		element=(WebElement)wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(locator)));
		return element;
	}
	
	public static void verifyElementPresent(String locator){
		WebElement element=getElement(locator);
		boolean elementPresent=element.isDisplayed();
		if(elementPresent){
			Assert.assertTrue(true);
		}else{
			Assert.assertTrue(false);
		}
	}
	
	public static void takescreenshot(String filename,ExtentTest test) throws IOException{
		TakesScreenshot screen=(TakesScreenshot)driver;
		File src=screen.getScreenshotAs(OutputType.FILE);
		String dest=System.getProperty("user.dir")+"//screenshots//"+filename+"_Error.png";
		File target=new File(dest);
		FileUtils.copyFile(src, target);
		test.addScreenCaptureFromPath(dest);
		
	}
	
	/*
	 * enter text
	 * click
	 * selectvalue from dropdown
	 * verifytext
	 * verifydropdown
	 */
	
}
