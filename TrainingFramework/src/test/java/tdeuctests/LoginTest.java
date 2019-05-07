package tdeuctests;

import java.io.IOException;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;

import locators.ObjectRepository;
import utilities.ExtentManager;
import utilities.GenericKeywords;

public class LoginTest extends GenericKeywords{
	
	ExtentReports extent;//blank extent report object
	ExtentTest test;

	@BeforeTest
	public void setUp() throws InterruptedException{
		myTestCaseName=this.getClass().getSimpleName();
		
//extent report object initialization
		
	extent=ExtentManager.GetExtent();
	test=extent.createTest(myTestCaseName);
	
	executeDriverScript();
	test.log(Status.INFO, "Browser launched & url navigated");
		Thread.sleep(6000);
	}
	
	@Test
	public void verifyLogin() throws InterruptedException{
		Thread.sleep(7000);
		
		getElement(ObjectRepository.usernametxt).sendKeys(username);
		test.log(Status.INFO, "username entered");
		getElement(ObjectRepository.passwordtxt).sendKeys(password);
		test.log(Status.INFO, "Password is entered");
		getElement(ObjectRepository.loginbtn).click();
		test.log(Status.INFO, "Login button clicked");
		
		verifyElementPresent(ObjectRepository.logoutlnk);
		test.log(Status.INFO, "Logout link visible");
//		WebElement logoutLink=getElement(ObjectRepository.logoutlnk);
//		Assert.assertTrue(logoutLink.isDisplayed());
	}
	
	@AfterMethod
	public void afterMethod(ITestResult result ) throws IOException{
		if(result.getStatus()==ITestResult.FAILURE){
			test.log(Status.FAIL, "Test Failed"+result.getThrowable());
			takescreenshot(this.getClass().getSimpleName(),test);
		}else{
			test.log(Status.PASS, "VerifyLogin test case working fine");
		}
	}
	
	@AfterTest
	public void tearDown(){
		extent.flush();
		driver.close();
	}
}
