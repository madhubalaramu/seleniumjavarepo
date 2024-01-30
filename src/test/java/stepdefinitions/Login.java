package stepdefinitions;

import java.io.IOException;

import org.openqa.selenium.WebDriver;

import io.cucumber.java.After;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import junit.framework.Assert;
import pageobjects.AccountPage;
import pageobjects.Landingpage;
import pageobjects.LoginPage;
import resources.Base;

public class Login extends Base {
	
	WebDriver driver;
	Landingpage landingpage;
	LoginPage loginpage;
	AccountPage accountpage;
	
	
	@Given("^Open any Browser$")
	public void open_any_browser() throws IOException {
	    
		 driver = intializeDriver();
		
	}

	@And("^Navigate to Login page$")
	public void navigate_to_login_page() {
	   
		driver.get(prop.getProperty("url"));
		
		landingpage = new Landingpage(driver);
		
		landingpage.myAccountDropdown().click();
		
		landingpage.loginOption().click();
		
	}

	@When("^User enters username as \"([^\"]*)\" and password as \"([^\"]*)\" into the fields$")
	public void user_enters_username_as_and_password_as_into_the_fields(String email, String password) {
		
		loginpage = new LoginPage(driver);
		
		loginpage.emailAddressField().sendKeys(email);
		
		loginpage.passwordField().sendKeys(password);
		
	    	}

	@And("^User clicks on Login button$")
	public void user_clicks_on_login_button() {
	   
		
		loginpage.loginButton().click();
		
	}

	@Then("^Verify user is able to successfully login$")
	public void verify_user_is_able_to_successfully_login() {
	    
		accountpage= new AccountPage(driver);
		Assert.assertTrue(accountpage.displayEditYourAccountInformation().isDisplayed());	
	}


	@After
	public void closeBrowser()
	{
		driver.close();
	}
	
	

}
