package com.surplusFurniture.qa.pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import com.surplusFurniture.qa.base.TestBase;

public class LoginPage extends TestBase{
	@FindBy(xpath="//input[@id='email']")
	WebElement Email;
	
	@FindBy(xpath="//input[@name='login[password]']")
	WebElement Password;
	
	@FindBy(xpath="//div[5]/div[1]/button[1]")
	WebElement signIn_Button;
	
	
	//Intialization of webElements
			public LoginPage(){
				PageFactory.initElements(driver, this);
			}
			
			
		//Actions
			
		public HomePage login (String userName, String password) throws InterruptedException  {
			
			Email.sendKeys(userName);
			Password.sendKeys(password);
			Thread.sleep(5000);
			signIn_Button.click();
			return new HomePage();
		}
		
		
}
