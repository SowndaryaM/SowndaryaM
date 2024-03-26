package stepDefinitions;

import java.util.HashMap;
import java.util.Map;

import org.mortbay.jetty.HttpParser.EventHandler;
import org.openqa.selenium.UnexpectedAlertBehaviour;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.events.EventFiringWebDriver;
import org.openqa.selenium.support.events.WebDriverEventListener;
import org.openqa.selenium.support.events.AbstractWebDriverEventListener;

import configuration.GlobalVariables;
import utilities.GeneralFunctions;

public class Login{
	
	public static WebDriver driver = null;
	public static WebDriver SetUp() {
		try {
			if(GeneralFunctions.fnTestDatapropdata("browser").equalsIgnoreCase("Chrome")) {
				System.setProperty("webdriver.chrome.driver", GlobalVariables.browserfilepath+"chromedriver_115.exe");
				
				driver = new ChromeDriver();
				
				driver.manage().window().maximize();
				driver.manage().deleteAllCookies();
			}
			
			
			  else if(GeneralFunctions.fnTestDatapropdata("browser").equalsIgnoreCase("Firefox")) 
			  { 
				  System.setProperty("webdriver.gecko.driver", GlobalVariables.browserfilepath+"geckodriver_116.exe");
			  
			  driver = new FirefoxDriver(); 
			  driver.manage().window().maximize();
			  driver.manage().deleteAllCookies(); 
			  
			  }
			  
			  else if(GeneralFunctions.fnTestDatapropdata("browser").equalsIgnoreCase("Edge")) 
			  {
			  System.setProperty("webdriver.edge.driver", GlobalVariables.browserfilepath+"msedgedriver_114.exe");
			  
			  driver = new EdgeDriver(); 
			  Thread.sleep(500);
			  driver.manage().window().maximize(); 
			  driver.manage().deleteAllCookies(); 
			  }
			 
			Runtime.getRuntime().addShutdownHook(new Thread() {
			public void run()
			{
				driver.quit();
			}
			});
		}
		catch (Exception e)
		{
			System.out.println("User is not able to Login");
		}
		return driver;
	}
}