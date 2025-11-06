package com.surplusFurniture.qa.pages;


import java.time.Duration;

import javax.xml.xpath.XPath;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;

import org.openqa.selenium.support.ui.WebDriverWait;


import com.surplusFurniture.qa.base.TestBase;
import com.surplusFurniture.qa.util.TestUtil;

public class HomePage extends TestBase{
	
	@FindBy(xpath="//span[@class='header-logo d-none d-lg-block']//img[@alt='Surplus Furniture & Mattress Warehouse Logo']")
	WebElement homeLogo;
	
	@FindBy(xpath="//span[normalize-space()='Cart']")
	WebElement CartIcon;
	
	@FindBy(xpath="//span[@class='text-label' and text()='Sign in']")
	WebElement signIn_Link;
	
	@FindBy(xpath="//a[@title='HOME DECOR']//span[contains(text(),'HOME DECOR')]")
	WebElement HOMEDECOR_Menu;
	
	@FindBy(xpath = "//*[@id=\"layer-product-list\"]/div[4]/ol/li[1]/div/div[2]/strong/a")
	WebElement BlackLamp;
	
	@FindBy(xpath = "//a[normalize-space()='Venaldi Gunmetal 2-Piece Sectional']")
	WebElement Venaldi2PieceSectional;
	
	@FindBy(xpath="//*[@id=\"product-addtocart-button\"]/span")
	WebElement AddToCart;
	
	@FindBy(xpath="//*[@id=\"minicart-content-wrapper\"]/div[2]/div[3]/div[3]/div/a")
	WebElement MiniCartViewCartButton;
	
	
	 @FindBy(xpath="//span[contains(text(),'Proceed to')]") 
	 WebElement ProceedToCheckout;
	
	
	 @FindBy(xpath="//span[text()='Create an Account']") 
	 WebElement CreateAccountBtn;

	 
	 @FindBy(xpath="//input[@id='firstname']")
	WebElement FirstName;	 
	 
	 @FindBy(xpath="//input[@id='lastname']")
	WebElement LastName;
	 
	 @FindBy(xpath="//input[@id='email_address']")
		WebElement Email;
	
	 @FindBy(xpath="//input[@id='password']")
		WebElement Password;
	 
	 @FindBy(xpath="//input[@id='password-confirmation']")
		WebElement ConfirmPassword;
	 
	 @FindBy(xpath="//button[@title='Create an Account']") 
	 WebElement CreateAccounInAccountForm;
	 
	//@FindBy(xpath="button[@type='button' and @data-role='proceed-to-checkout']")
	//WebElement ProceedToCheckout;
	
	@FindBy(xpath="//span[normalize-space()='Continue as a Guest']")
	WebElement ContinueAsGuestButton;
	
	@FindBy(xpath="//div[@class='control _with-tooltip']//input[@id='customer-email']")
	WebElement EmailCheckout;
	
	@FindBy(xpath="//*[@id=\"opc-sidebar\"]/div[1]/div[2]/button")
	WebElement ProceedToBilling;
	

	//Intialization of webElements
		public HomePage(){
			PageFactory.initElements(driver, this);
		}
		
		
	
	//Actions
	
		// method to check home logo is displayed
		public boolean validateHomePageLogo(){
			return homeLogo.isDisplayed();
		}
		
		
		//method to click on sign in button
				public LoginPage clickOnSiginInLink() {
					signIn_Link.click();
					return new LoginPage();
				}

	//method to check home page title
				public String validateHomePageTitle(){
					return driver.getTitle();
				}
				
	//method to click on Home Decore category
			public String OpenCategoryPLPHome() throws InterruptedException {
				
					Thread.sleep(6000);
					driver.findElement(By.xpath("//a[@title='HOME DECOR']//span[contains(text(),'HOME DECOR')]")).click();
					return driver.getCurrentUrl();
					
				}
			
			public void addSimpleProductToCart() throws InterruptedException {
				Thread.sleep(8000);
				driver.findElement(By.xpath("//a[@title='HOME DECOR']//span[contains(text(),'HOME DECOR')]")).click();
				driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));
				Thread.sleep(8000);
				BlackLamp.click();
				Thread.sleep(8000);
				AddToCart.click();
				//Thread.sleep(8000);
				//CartIcon.click();
				Thread.sleep(8000);
				driver.get("https://m2uat.surplusfurniture.com/checkout/cart/");
				//MiniCartViewCartButton.click();
				Thread.sleep(8000);
				JavascriptExecutor js = (JavascriptExecutor) driver;
				js.executeScript("window.scrollBy(0,250)", "");
				//WebDriverWait wait=new WebDriverWait(driver, Duration.ofSeconds(10));
				//wait.until(ExpectedConditions.elementToBeClickable(ProceedToCheckout)).click();
				ProceedToCheckout.click();
				Thread.sleep(8000);
				ContinueAsGuestButton.click();
				Thread.sleep(8000);
				EmailCheckout.sendKeys("Vikrant.bulbule@encora.com");
				Thread.sleep(5000);
				ProceedToBilling.click();
			}
			
			public void addBundledProductToCart() throws InterruptedException {
				driver.findElement(By.xpath("//a[@title='LIVINGROOMS']//span[contains(text(),'LIVINGROOMS')]")).click();
				Thread.sleep(8000);
				Venaldi2PieceSectional.click();
				Thread.sleep(8000);
				JavascriptExecutor js = (JavascriptExecutor) driver;
				js.executeScript("window.scrollBy(0,250)", "");
				AddToCart.click();
				Thread.sleep(8000);
				//CartIcon.click();
				//Thread.sleep(12000);
				driver.get("https://m2uat.surplusfurniture.com/checkout/cart/");
				//MiniCartViewCartButton.click();
				Thread.sleep(8000);
				js.executeScript("window.scrollBy(0,250)", "");
				//WebDriverWait wait=new WebDriverWait(driver, Duration.ofSeconds(10));
				//wait.until(ExpectedConditions.elementToBeClickable(ProceedToCheckout)).click();
				ProceedToCheckout.click();
				Thread.sleep(8000);
				ContinueAsGuestButton.click();
				Thread.sleep(8000);
				EmailCheckout.sendKeys("Vikrant.bulbule@encora.com");
				Thread.sleep(5000);
				ProceedToBilling.click();
			}
			
			public String paymentUsingCC() throws InterruptedException{
				Thread.sleep(8000);
				driver.findElement(By.xpath("//a[@class='moneris']")).click();
				Thread.sleep(8000);
				driver.findElement(By.xpath("/html[1]/body[1]/div[2]/main[1]/div[2]/div[1]/div[4]/div[2]/ol[1]/li[3]/div[1]/form[1]/fieldset[1]/div[3]/div[1]/div[1]/div[4]/div[2]/div[2]/div[1]/fieldset[1]/div[2]/div[1]/form[1]/fieldset[1]/div[1]/div[1]/input[1]")).sendKeys("Vikrant");
				driver.findElement(By.xpath("/html/body/div[2]/main/div[2]/div/div[4]/div[2]/ol/li[3]/div/form/fieldset/div[3]/div/div/div[4]/div[2]/div[2]/div/fieldset/div[2]/div/form/fieldset/div[2]/div/input")).sendKeys("Bulbule");
				 driver.findElement(By.xpath("/html/body/div[2]/main/div[2]/div/div[4]/div[2]/ol/li[3]/div/form/fieldset/div[3]/div/div/div[4]/div[2]/div[2]/div/fieldset/div[2]/div/form/fieldset/fieldset/div/div/div/input")).sendKeys("Test Address");
				driver.findElement(By.xpath("/html/body/div[2]/main/div[2]/div/div[4]/div[2]/ol/li[3]/div/form/fieldset/div[3]/div/div/div[4]/div[2]/div[2]/div/fieldset/div[2]/div/form/fieldset/div[3]/div/input")).sendKeys("Barrie");
				 Select State_dropdown= new Select (driver.findElement(By.xpath("/html/body/div[2]/main/div[2]/div/div[4]/div[2]/ol/li[3]/div/form/fieldset/div[3]/div/div/div[4]/div[2]/div[2]/div/fieldset/div[2]/div/form/fieldset/div[4]/div/select")));
				 State_dropdown.selectByVisibleText("Ontario");
				 driver.findElement(By.xpath("/html/body/div[2]/main/div[2]/div/div[4]/div[2]/ol/li[3]/div/form/fieldset/div[3]/div/div/div[4]/div[2]/div[2]/div/fieldset/div[2]/div/form/fieldset/div[6]/div/input")).sendKeys("A1B 2C3");
				 Select country_dropdown= new Select (driver.findElement(By.xpath("/html[1]/body[1]/div[2]/main[1]/div[2]/div[1]/div[4]/div[2]/ol[1]/li[3]/div[1]/form[1]/fieldset[1]/div[3]/div[1]/div[1]/div[4]/div[2]/div[2]/div[1]/fieldset[1]/div[2]/div[1]/form[1]/fieldset[1]/div[7]/div[1]/select[1]")));
				  country_dropdown.selectByVisibleText("Canada");
				  driver.findElement(By.xpath("/html/body/div[2]/main/div[2]/div/div[4]/div[2]/ol/li[3]/div/form/fieldset/div[3]/div/div/div[4]/div[2]/div[2]/div/fieldset/div[2]/div/form/fieldset/div[8]/div/input")).sendKeys("9595478220");
				driver.findElement(By.xpath("//div[@class='payment-method _active']//button[@type='button']")).click();
				driver.findElement(By.xpath("//*[@id=\"moneriscc_cardholder_name\"]")).sendKeys("Vikrant Bulbule"); 
				driver.findElement(By.xpath("//*[@id=\"moneris_cc_number\"]")).sendKeys("4242 4242 4242 4242");
				Select Month_dropdown= new Select (driver.findElement(By.xpath("//*[@id=\"moneris_expiration\"]")));
				Month_dropdown.selectByVisibleText("12 - December");
				Select Year_dropdown= new Select (driver.findElement(By.xpath("//*[@id=\"moneris_expiration_yr\"]")));
				Year_dropdown.selectByVisibleText("2025");
				driver.findElement(By.xpath("//*[@id=\"place-order-trigger\"]/span")).click();
				Thread.sleep(10000);
				return  driver.getTitle();
			}
			
			public String CreateAccount(String FtName,String LtName, String EmailID, String Pwd, String ConfirmPwd) {
				signIn_Link.click();
				CreateAccountBtn.click();
				FirstName.sendKeys(FtName);
				LastName.sendKeys(LtName);
				Email.sendKeys(EmailID);
				Password.sendKeys(Pwd);
				ConfirmPassword.sendKeys(ConfirmPwd);
				CreateAccounInAccountForm.click();
				return  driver.getTitle();
				
			}
				
		
}
