package HomePagePOM;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class HomePagePOM{
	
	WebDriver driver;
	
	public HomePagePOM(WebDriver driver)
	{
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	
	@FindBy(xpath= "")
	WebElement Logo;
	public WebElement Logo() {
		return Logo;
	}
	
	
}