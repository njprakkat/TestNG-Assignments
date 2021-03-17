package RedBusDataProvider;

import java.io.IOException;

import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Parameters;

import io.github.bonigarcia.wdm.WebDriverManager;



public class BaseClassRedBus {
	public ChromeDriver driver;
	public String excelFileName;
	
	@Parameters("url")
	@BeforeMethod
	public void precondition(String url)
	{
	WebDriverManager.chromedriver().setup();
	ChromeOptions options = new ChromeOptions();
	options.addArguments("--disable-notifications");
	ChromeDriver driver = new ChromeDriver(options);
	driver.manage().window().maximize();
	driver.get(url);
	}

	@AfterMethod
	public void postCondition() {
		driver.close();

	}
	
	@DataProvider(name="fetchCity")
	public String[][] sendData() throws IOException
	{
		ReadfromExcel obj = new ReadfromExcel();
		return obj.readExcel(excelFileName);
		
	}
}
