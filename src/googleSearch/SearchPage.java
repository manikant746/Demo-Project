package googleSearch;

import org.testng.annotations.Test;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Test;


public class SearchPage {
	
	WebDriver driver;
	
	@Test
	public void openApplicaiton() throws IOException {
		FileReader fr=new FileReader("C:\\Users\\Mani\\eclipse-workspace\\DemoProject\\src\\googleSearch\\TestData.properties");
	    Properties props=new Properties();
		props.load(fr);
		String loadUrl=props.getProperty("url");
		String useBrowser=props.getProperty("browser");
		String validateSite=props.getProperty("checkSite");
		
		if(useBrowser.equals("chrome"))
		{
		System.setProperty("webdriver.chrome.driver", "F:\\Selenium\\jars\\latest\\New\\new\\driver\\chromedriver.exe");

		driver = new ChromeDriver();
		driver.get(loadUrl);
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
		
		}
		String search=props.getProperty("searchString");
	    System.out.println(search);
	    driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
	    WebElement element= driver.findElement(By.name("q"));
	    element.sendKeys(search);
	    element.submit();
	    String result = driver.findElement(By.id("result-stats")).getText().split(" ")[1];
	    String str = result.replace(",","");
	    Long number = Long.valueOf(str); 
	    if(number!=0)
	    {
	    	 System.out.println("valid results");
	    }
	    else
	    {
	    	 System.out.println("enter valid search string or connection problem");
	    }
	    driver.findElement(By.partialLinkText("speedtest")).click();
	    String Title = driver.getTitle();
	    Boolean b=Title.contains(search);
	    if (b==true)
	    {
	    	System.out.println("Page contains initial search phrase");
	    }
	    else
	    {
	    	System.out.println("Page doesn't contain initial search phrase");
	    }
	    
	    driver.quit();
}
}
