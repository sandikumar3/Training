package base;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;

import utilities.ExcelReader;

public class BaseClass {
	public String sheetName;
	public ExcelReader excl;
	public String browser;
	public String url;
	public static String username;
	public static String password;
	public static WebDriver driver;
	public static String myTestCaseName;
	public static boolean continuetestcase;
	public static int startIter;
	
	
	public void executeDriverScript(){
		readExcelData();
		sheetName="General";
		int rowCountGeneralSheet=excl.getRowCount(sheetName);
		
		for(int row=1;row<=rowCountGeneralSheet;row++){
			if(myTestCaseName.equals(excl.getCellData(sheetName, 0, row))){
				if(excl.getCellData(sheetName, 4, row).equalsIgnoreCase("Yes")){
					continuetestcase=true;
					url=excl.getCellData(sheetName, 1, row);
					browser=excl.getCellData(sheetName, 5, row);
					username=excl.getCellData(sheetName, 2, row);
					password=excl.getCellData(sheetName, 3, row);
					
					if(browser.equalsIgnoreCase("chrome")){
						System.setProperty("webdriver.chrome.driver", System.getProperty("user.dir")+"\\src\\main\\java\\resources\\chromedriver.exe");
						driver=new ChromeDriver();
						
					}else if(browser.equalsIgnoreCase("IE")){
						System.setProperty("webdriver.ie.driver", System.getProperty("user.dir")+"\\src\\main\\java\\resources\\IEDriverServer.exe");
						driver=new InternetExplorerDriver();
						
					}else if(browser.equalsIgnoreCase("Firefox")){
						System.setProperty("webdriver.gecko.driver", System.getProperty("user.dir")+"\\src\\main\\java\\resources\\geckodriver.exe");
						driver=new FirefoxDriver();
					}
					driver.manage().window().maximize();//maximize the browser
					driver.get(url);//navigate to the url
				}
				
			}
		}
		
	}
	
	public void readExcelData(){
		excl=new ExcelReader(System.getProperty("user.dir")+"\\src\\main\\java\\resources\\TestData.xlsx");
	}
}
