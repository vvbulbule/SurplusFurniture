package com.surplusFurniture.qa.testcases;



import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.surplusFurniture.qa.base.TestBase;
import com.surplusFurniture.qa.pages.HomePage;

public class HomePageTest extends TestBase{

	HomePage homePage;
	
	
	public HomePageTest(){
		super();
	}
	
	
	@BeforeMethod
	public void setup() throws InterruptedException{
		initialization();
		homePage = new HomePage();
		
	}
	
	@Test (priority=1)
	public void validateHomePage() throws InterruptedException{
		Assert.assertTrue(homePage.validateHomePageLogo(), "Home logo is not displayed");
		Assert.assertEquals(homePage.validateHomePageTitle(), "Quality Home Furniture, Mattresses & Appliances | Surplus Furniture");
		Assert.assertEquals(homePage.OpenCategoryPLPHome(), "https://m2.surplusfurniture.com/home-decor", "Home Decore Category is not Open");
		
	
    }
	
	//Test Case to place the order of  simple Product type
	@Test(priority=2)
	public void PaymentTestSimpleProduct () throws InterruptedException{
	
		homePage.addSimpleProductToCart();
		String PageTitle =homePage.paymentUsingCC();
		Assert.assertEquals(PageTitle, "Success Page", "Order is not Placed");
	
    }
	
	//Test Case to place the order of  Bundled Product type
	@Test(priority=3)
	public void PaymentTestBundledProduct () throws InterruptedException{
	
		
		homePage.addBundledProductToCart();
		String PageTitle =homePage.paymentUsingCC();
		Assert.assertEquals(PageTitle, "Success Page", "Order is not Placed");
	
    }
	
	// Create Account Test Cases
	// Change new email id to create the account
	@Test(priority=4)
	public void CreateAccountTest() {
		homePage.CreateAccount("Vikrant", "Bulbule", "vmnbhvhk@mailinator.com", "V12bulbule@", "V12bulbule@");
		String AccountSectionURL= driver.getCurrentUrl();
		Assert.assertEquals(AccountSectionURL, "https://reqa.surplusfurniture.com/customer/account/");
	}
	
	// Create Account Test Cases with existing user
	// Account is not created for existing User
	@Test(priority=4)
	public void CreateAccountExistingEmailTest() throws InterruptedException {
		homePage.CreateAccount("Vikrant", "Bulbule", "vmnbhvhk@mailinator.com", "V12bulbule@", "V12bulbule@");
		Thread.sleep(5000);
		String ErrorMessage= driver.findElement(By.xpath("//*[@id=\"maincontent\"]/div[2]/div[2]/div/div/div")).getText();
		Assert.assertEquals(ErrorMessage, "There is already an account with this email address. If you are sure that it is your email address, click here to get your password and access your account.");
	}
	
}
