package stepDefinitions;

import java.util.List;

import org.junit.Assert;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.WebDriverWait;


import configuration.GlobalVariables;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import utilities.GeneralFunctions;


public class BookStoreTesting {
	
	
	@Given("^Login to the demoQA website$")
	public void login_to_the_demoQA_website() throws Throwable {
	   
		System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~Starting Execution~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
		GlobalVariables.propertiesfilename = "LoginDetails.properties";
		GeneralFunctions.fngetWebDriver();
		
		if (GeneralFunctions.fnTestDatapropdata("AUT").equalsIgnoreCase("QA"))
		{
			
			GlobalVariables.driver.get(GeneralFunctions.fnTestDatapropdata("qa"));
						
		}
		
		Thread.sleep(1000);
	}

	@Then("^Go to the book store application$")
	public void go_to_the_book_store_application() throws Throwable {
				
		WebElement loginButton = GlobalVariables.driver.findElement(By.xpath(".//button[@id='login']")); 
		loginButton.click();
	}

	@Then("^Login to the application$")
	public void login_to_the_application() throws Throwable {
		
		GlobalVariables.driver.get(GeneralFunctions.fnTestDatapropdata("qa1"));
		Thread.sleep(2000);
		
		WebElement userName = GlobalVariables.driver.findElement(By.xpath(".//input[@id='userName']")); 
		userName.sendKeys(APITesting.USERNAME);
		
		WebElement password = GlobalVariables.driver.findElement(By.xpath(".//input[@id='password']")); 
		password.sendKeys(APITesting.PASSWORD);
		
		JavascriptExecutor js = (JavascriptExecutor) GlobalVariables.driver;
		js.executeScript("window.scrollBy(0,250)", "");
		
		WebElement loginButton = GlobalVariables.driver.findElement(By.xpath(".//button[@id='login']")); 
		loginButton.click();
		
		Thread.sleep(2000);
	}

	@Then("^Verify the number of books that were added to user against the number of books displayed in the website$")
	public void verify_the_number_of_books_that_were_added_to_user_against_the_number_of_books_displayed_in_the_website() throws Throwable {
		//GlobalVariables.driver.get(GlobalVariables.driver.getCurrentUrl());
		//Thread.sleep(1000);
		JavascriptExecutor js = (JavascriptExecutor) GlobalVariables.driver;
		js.executeScript("window.scrollBy(0,760)", "");
		
		
		Thread.sleep(4000);
		List<WebElement> pageNoDD_List = GlobalVariables.driver.findElements(By.xpath(".//select[@aria-label='rows per page']/option"));
		
				
	for(WebElement pageNoDD_Elements: pageNoDD_List)
	{
			String pageNoDD_Element = pageNoDD_Elements.getText();
			System.out.println(pageNoDD_Element);
			if(pageNoDD_Element.contains("10 rows"))
			{
				pageNoDD_Elements.click();
			}
		}
	List<WebElement> listOfBooksAddedInUI = GlobalVariables.driver.findElements(By.xpath(".//div[@class='rt-tbody']/div[@class='rt-tr-group']/div[contains (@class, 'rt-tr -even') or contains (@class, 'rt-tr -odd')]"));
	int listOfBooksAddedInUIQty = listOfBooksAddedInUI.size();
	System.out.println(listOfBooksAddedInUIQty+"****************************");
	
	System.out.println(APITesting.noOfBooks+"&&&&&&&&&&&&&&&&&&&&&&&&&&&&");
	if(listOfBooksAddedInUIQty == APITesting.noOfBooks)
	{
		System.out.println("List of books added in UI according the books added from API: "+ listOfBooksAddedInUIQty);
	}
		
		Thread.sleep(2000);
		//System.out.println(listOfBooksAddedInUI+"##############################");
	
	}

	@Then("^For each book on the website, verify the book details$")
	public void for_each_book_on_the_website_verify_the_book_details() throws Throwable {
		
		List<WebElement> bookLists = GlobalVariables.driver.findElements(By.xpath(".//div[@class='rt-tbody']/div[@class='rt-tr-group']/div[contains (@class, 'rt-tr -even') or contains (@class, 'rt-tr -odd')]"));
		System.out.println(bookLists.size());
		
		for(int i=0; i<bookLists.size(); i++)
		{
			WebElement detailsOfEachBookLinks = GlobalVariables.driver.findElement(By.xpath("(.//div[@class='rt-tbody']/div[@class='rt-tr-group']/div[contains (@class, 'rt-tr -even') or contains (@class, 'rt-tr -odd')]//div[@class='rt-td'][2]//span/a)["+(i+1)+"]"));
			JavascriptExecutor js1 = (JavascriptExecutor) GlobalVariables.driver;
			js1.executeScript("arguments[0].scrollIntoView();", detailsOfEachBookLinks);
			Thread.sleep(2000);
			detailsOfEachBookLinks.click();
			Thread.sleep(2000);
			JavascriptExecutor js = (JavascriptExecutor) GlobalVariables.driver;
			js.executeScript("window.scrollBy(0,550)", "");
			Thread.sleep(2000);
			WebElement backToBookStoreButton = GlobalVariables.driver.findElement(By.xpath(".//button[@id='addNewRecordButton']"));
			backToBookStoreButton.click();
			Thread.sleep(2000);
			if(i >= 4)
			{
				
				System.out.println("##########################################################");
				//JavascriptExecutor js2 = (JavascriptExecutor) GlobalVariables.driver;
				//js2.executeScript("window.scrollBy(0,760)", "");
				
				
				Thread.sleep(4000);
				List<WebElement> pageNoDD_List = GlobalVariables.driver.findElements(By.xpath(".//select[@aria-label='rows per page']/option"));
				
						
			for(WebElement pageNoDD_Elements: pageNoDD_List)
			{
					String pageNoDD_Element = pageNoDD_Elements.getText();
					JavascriptExecutor js2 = (JavascriptExecutor) GlobalVariables.driver;
					js2.executeScript("window.scrollBy(0,760)", "");
					System.out.println(pageNoDD_Element);
					if(pageNoDD_Element.contains("10 rows"))
					{
						pageNoDD_Elements.click();
						break;
					}
				}
				
			}
			
		}
	  
	}


}