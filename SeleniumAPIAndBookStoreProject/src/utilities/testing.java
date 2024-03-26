package utilities;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import configuration.GlobalVariables;

public class testing {
	
	public static WebDriver driver = null;
	
	
	public static void main (String args[]) 
	
	{
		
		System.setProperty("webdriver.chrome.driver", GlobalVariables.browserfilepath + "chromedriver_114.exe");
		driver = new ChromeDriver();
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(10,TimeUnit.SECONDS);
		
		driver.get("https://www.google.com/");
		
		System.out.println("aaaaaaaaaa");
		
		
	}
	
	
	
	
}
