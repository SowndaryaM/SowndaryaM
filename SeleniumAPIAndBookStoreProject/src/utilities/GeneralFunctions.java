package utilities;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;

import configuration.GlobalVariables;
import stepDefinitions.Login;

public class GeneralFunctions {
	
	public static String status = "Pass";
	public static String exception = "";
	
	public static String fnTestDatapropdata(String propdata) {
		Properties prop;
	
		File file = new File(GlobalVariables.TestDatapropertiesfilepath + GlobalVariables.propertiesfilename);
		FileInputStream fileInput = null;
		
		try {
			fileInput = new FileInputStream(file);
		}
		catch (FileNotFoundException e){
			e.printStackTrace();
		}
		
		prop = new Properties();
		
		try {
			prop.load(fileInput);
			}
		catch (IOException e){
			e.printStackTrace();
		}
		
		return prop.getProperty(propdata);
		
		}
	
	public static void fngetWebDriver() {
		GlobalVariables.driver = Login.SetUp();
	}
	
	public static void fnValidateTextBoxAndEnter(WebElement element, String Pagename, String valToBeEntered)
	{
		
		try {
			if(element != null)
			{
				//GeneralFunctions.fnhighLightElement(element,Pagename + "text box is present in" + GlobalVariables.driver.getTitle() + "page");
				//element.clear();
				element.sendKeys(valToBeEntered);
			}
		}
		
		catch(Exception e)
		{
			status = "False";
			exception = e.getMessage();
			System.out.println(exception);
			status = "True";
		}
	}
	
	public static void fnhighLightElement(WebElement webElement) throws InterruptedException
	{
		JavascriptExecutor js = (JavascriptExecutor) GlobalVariables.driver;
		Thread.sleep(500);
		js.executeScript("arguments[0].setAttribute('style','border: 2px solid red;');", webElement);
	}
	
	public static void fnValidateandClickonElement(WebElement element, String Pagename, String ElementType)
	{
		try {
			if(element != null)
			{
				//GeneralFunctions.fnhighLightElement(element,Pagename + " " + ElementType + "is present in" + GlobalVariables.driver.getTitle() + "page");
				//element.clear();
				element.click();
			}
		}
		
		catch(Exception e)
		{
			status = "False";
			exception = e.getMessage();
			System.out.println(exception);
			status = "True";
		}
	}
}