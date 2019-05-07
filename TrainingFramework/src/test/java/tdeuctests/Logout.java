package tdeuctests;

import java.io.IOException;

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

public class Logout extends GenericKeywords{
	
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
				getElement(ObjectRepository.usernametxt).sendKeys(username);
				test.log(Status.INFO, "username entered");
				getElement(ObjectRepository.passwordtxt).sendKeys(password);
				test.log(Status.INFO, "Password is entered");
				getElement(ObjectRepository.loginbtn).click();
				test.log(Status.INFO, "Login button clicked");
				Thread.sleep(3000);
	}
	
	@Test
	public void VerifyLogout() throws InterruptedException{
		verifyElementPresent(ObjectRepository.logoutlnk);
		test.log(Status.INFO, "Logout link visible");
		getElement(ObjectRepository.logoutlnk).click();
		test.log(Status.INFO, "Logout button clicked");
		Thread.sleep(6000);
		verifyElementPresent(ObjectRepository.loginbtn);
		test.log(Status.INFO, "Login link visible");
		
	}
	
	@AfterMethod
	public void afterMethod(ITestResult result ) throws IOException{
		if(result.getStatus()==ITestResult.FAILURE){
			test.log(Status.FAIL, "Test Failed - "+result.getThrowable());
			takescreenshot(this.getClass().getSimpleName(),test);
		}else{
			test.log(Status.PASS, "VerifyLogout test case working fine");
		}
	}
	
	@AfterTest
	public void tearDown(){
		extent.flush();
		driver.close();
	}

}
