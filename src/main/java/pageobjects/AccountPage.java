package pageobjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class AccountPage {

	public WebDriver driver;
	
	public AccountPage(WebDriver driver) {
		
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	
	@FindBy(linkText="Edit your account information")
	private WebElement displayEditYourAccountInformation;
	
	public WebElement displayEditYourAccountInformation() {
		
		return displayEditYourAccountInformation;
	}
	
	
	
}
