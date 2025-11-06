package com.surplusFurniture.qa.testcases;

import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.surplusFurniture.qa.base.TestBase;
import com.surplusFurniture.qa.pages.HomePage;
import com.surplusFurniture.qa.pages.LoginPage;



public class LoginPageTest extends TestBase{
	LoginPage loginPage;
	HomePage homePage;
	
	
	public LoginPageTest(){
		super();
	}
	
	@BeforeMethod
	public void setup() throws InterruptedException{
		initialization();
		homePage = new HomePage();
		loginPage = new LoginPage();
		homePage.clickOnSiginInLink();
		
		
	}
	
	// Verified the Login Functionality using below TC
	@Test(priority=1)
	public void loginTest() throws InterruptedException{
		
		homePage = loginPage.login(prop.getProperty("email_id"),prop.getProperty("password"));
		String CurrentPageURL= driver.getCurrentUrl();
		Assert.assertEquals(CurrentPageURL, "https://m2uat.surplusfurniture.com/customer/account/", "User is not login Successful");
	}
	
	@AfterMethod
	public void tearDown(){
		driver.close();
	}
}
