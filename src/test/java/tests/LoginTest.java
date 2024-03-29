package tests;

import java.io.IOException;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import pageobjects.AccountPage;
import pageobjects.Landingpage;
import pageobjects.LoginPage;
import resources.Base;

public class LoginTest extends Base {

	 public WebDriver driver;
	 Logger log ;
	
	@Test(dataProvider="getLoginData")
	public void login(String email, String password, String expectedResult) throws IOException {
	
		
		Landingpage landingpage = new Landingpage(driver);
				
		landingpage.myAccountDropdown().click();
		log.debug("Clicked on My Account dropdown");
		landingpage.loginOption().click();
		log.debug("Clicked on login option");
		
		
		LoginPage loginpage = new LoginPage(driver);
		
		loginpage.emailAddressField().sendKeys(email);
		log.debug("Email addressed got entered");
		loginpage.passwordField().sendKeys(password);
		log.debug("Password got entered");
		loginpage.loginButton().click();
		log.debug("Clicked on Login Button");
		
		
		
		AccountPage accountpage= new AccountPage(driver);
		
		String actualResult = null;
		
		try {
			
			if(accountpage.displayEditYourAccountInformation().isDisplayed()) {
				
			log.debug("User got logged in");	
			actualResult = "Success";
			
			}
			
		}catch (Exception e) {
			
			log.debug("User didn't log in");
			actualResult = "Fail";
			
		}
		
		Assert.assertEquals(actualResult, expectedResult);
		log.info("Login Test got passed");
		
	}
	
	@BeforeMethod
	public void openApplication() throws IOException {
		
		log = LogManager.getLogger(LoginTest.class.getName());
		
		driver = intializeDriver();
		log.debug("Browser got launched");
		driver.get(prop.getProperty("url"));
		log.debug("Navigated to application URL");
		
	}
	
	@AfterMethod
	public void closure() {
		
		driver.close();
		log.debug("Browser got closed");
	}
	
	@DataProvider
	public Object[][] getLoginData() {
		
		Object[][] data = {{"madhu.selenium@gmail.com","madhu@7777","Success"},{"dummy@test.com","dummy","Fail"}};
		return data;
		
	}
	
	
	
	
}
