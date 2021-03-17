
package RedBusDataProvider;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import io.github.bonigarcia.wdm.WebDriverManager;


public class RedBuscities extends BaseClassRedBus
{
	
	@BeforeTest
	public void setFileName()
	{
		excelFileName = "RedBusCitiesData";
	}
	
	
	
	@Test(dataProvider = "fetchCity")
	public void readCities(String Sourcecity , String Destinationcity) throws Exception 
	{
		
		driver.findElement(By.xpath("//input[@data-message='Please enter a source city']")).sendKeys(Sourcecity);
		System.out.println("Source City Keyed IN");
		Thread.sleep(2000);
		driver.findElement(By.xpath("//input[@data-message='Please enter a destination city']")).sendKeys(Destinationcity);
		Thread.sleep(2000);
		driver.findElement(By.xpath("(//input[@type='text'])[last()-1]")).click();
		driver.findElement(By.xpath("//td[@class='current day']/following-sibling::td[1]")).click();
		Thread.sleep(2000);
		driver.findElement(By.id("search_btn")).click();
		driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
		Thread.sleep(4000);
		driver.findElement(By.xpath("//div[@class='close']")).click();
		driver.findElement(By.xpath("//span[text()='Ok, got it']")).click();
		String Total_Buses = driver.findElement(By.xpath("//span[@class='f-bold busFound']")).getText();
		System.out.println("The Total number of Buses found is : " + Total_Buses);
		List<WebElement> Count = driver.findElementsByXPath("//div[@class='travels lh-24 f-bold d-color']");
		List<String> TravelsList = new ArrayList<String>();
		for (int i = 1; i <= Count.size(); i++) {
			String ListTL = driver.findElement(By.xpath("(//div[@class='travels lh-24 f-bold d-color'][1])[" + i + "]"))
					.getText();
			TravelsList.add(ListTL);

		}
		Set<String> SetTravelList = new HashSet<String>(TravelsList);

		List<String> FinalTravelsList = new ArrayList<String>(SetTravelList);
		System.out.println("The Travels names without Duplication");
		for (int j = 1; j <= FinalTravelsList.size(); j++) {
			System.out.println(FinalTravelsList.get(j - 1));
		}

		driver.findElement(By.xpath("//div[text()='View Seats']")).click();
		driver.findElement(By.xpath("//i[@class='icon-close closepopupbtn']")).click();
		WebElement output = driver.findElement(By.xpath("//div[@class='seat-container-wrapper']"));
		File Souce = output.getScreenshotAs(OutputType.FILE);// Snap of a Element
		// File Sourse = driver.getScreenshotAs(OutputType.FILE);// Snap of Entire
		// Visible page
		File Destination = new File("./snaps/Redbus/seats.png");
		FileUtils.copyFile(Souce, Destination);
		
	}

	

}