package testcases;

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

public class VerifyMercuryLogout extends GenericKeywords{
	
	ExtentReports extent;
	ExtentTest test;

	@BeforeTest
	public void setUp() throws InterruptedException{
		myTestCaseName=this.getClass().getSimpleName();
		
//extent report object initialization
		
	extent=ExtentManager.GetExtent();
	test=extent.createTest(myTestCaseName);
	
	executeDriverScript();
	test.log(Status.INFO, "Browser launched & url navigated");
		Thread.sleep(3000);
	}
	
	@Test
	public void VerifyMercuryLogout() throws InterruptedException{		
		getElement(ObjectRepository.mr_username_txt).sendKeys(username);
		test.log(Status.INFO, "username entered");
		getElement(ObjectRepository.mr_password_txt).sendKeys(password);
		test.log(Status.INFO, "Password is entered");
		getElement(ObjectRepository.mr_login_btn).click();
		test.log(Status.INFO, "Login button clicked");
		
		verifyElementPresent(ObjectRepository.mr_signoff_lnk);
		test.log(Status.INFO, "Sign off link visible");
		
		getElement(ObjectRepository.mr_signoff_lnk).click();
		test.log(Status.INFO, "Sign Off link clicked");
		
		Thread.sleep(3000);
		verifyElementPresent(ObjectRepository.mr_username_txt);
		test.log(Status.INFO, "username text box visible");
	}
	
	@AfterMethod
	public void afterMethod(ITestResult result ) throws IOException{
		if(result.getStatus()==ITestResult.FAILURE){
			test.log(Status.FAIL, "Test Failed"+result.getThrowable());
			takescreenshot(this.getClass().getSimpleName(),test);
		}else{
			test.log(Status.PASS, "Verify Mercury Logout test case working fine");
		}
	}
	
	@AfterTest
	public void tearDown(){
		extent.flush();
		driver.close();
	}
}
