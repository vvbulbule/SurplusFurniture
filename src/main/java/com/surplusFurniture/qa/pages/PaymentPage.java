package com.surplusFurniture.qa.pages;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;

import com.surplusFurniture.qa.base.TestBase;

import reusableMethods.DropdownPage;
import reusableMethods.SeleniumDropdownHandling;

public class PaymentPage extends TestBase{
	
	HomePage homePage ;
	DropdownPage dropdownPage = new DropdownPage(driver);;
	
	@FindBy(xpath = "//*[@id=\"layer-product-list\"]/div[4]/ol/li[2]/div/div[2]/strong/a")
	WebElement PorcelainChocolate;
	
	
	@FindBy(xpath = "/html/body/div[2]/main/div[2]/div/div/div[2]/div[2]/ol/li[3]/div/form/fieldset/div[3]/div/div/div[3]/div[2]/div[2]/div/fieldset/div[2]/div/form/fieldset/div[4]/div/select")
	WebElement CountryDropdown;
	
	@FindBy(xpath = "//a[normalize-space()='Venaldi Gunmetal 2-Piece Sectional']")
	WebElement Venaldi2PieceSectional;
	
	@FindBy(xpath = "//*[@id=\"layer-product-list\"]/div[4]/ol/li[2]/div/div[2]/strong/a")
	WebElement GraphiteCasa2PieceSectional;
	
	@FindBy(xpath="//*[@id=\"product-addtocart-button\"]/span")
	WebElement AddToCart;
	
	@FindBy(xpath="//*[@id=\"minicart-content-wrapper\"]/div[2]/div[3]/div[3]/div/a")
	WebElement MiniCartViewCartButton;
	
	
	 @FindBy(xpath="//*[@id=\"maincontent\"]/div[3]/div/div/div[4]/div[2]/div/ul/li/button") 
	 WebElement ProceedToCheckout;
	
	//@FindBy(xpath="button[@type='button' and @data-role='proceed-to-checkout']")
	//WebElement ProceedToCheckout;
	
	@FindBy(xpath="//span[normalize-space()='Continue as a Guest']")
	WebElement ContinueAsGuestButton;
	
	@FindBy(xpath="//div[@class='control _with-tooltip']//input[@id='customer-email']")
	WebElement EmailCheckout;
	
	@FindBy(xpath="//*[@id=\"opc-sidebar\"]/div[2]/div[2]/button") 
	WebElement ProceedToBilling;
	
	
	public void OpenCategoryPLPHome() throws InterruptedException {
		
		//Thread.sleep(6000);
		driver.findElement(By.xpath("//a[@title='HOME DECOR']//span[contains(text(),'HOME DECOR')]")).click();
		//return driver.getCurrentUrl();
		
	}
	
	//Intialization of webElements
			public PaymentPage(){
				PageFactory.initElements(driver, this);
			}

	public void addSimpleProductToCart() throws InterruptedException {
		driver.findElement(By.xpath("//a[@title='HOME DECOR']//span[contains(text(),'HOME DECOR')]")).click();
		//homePage.OpenCategoryPLPHome();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));
		Thread.sleep(8000);
		PorcelainChocolate.click();
		Thread.sleep(8000);
		AddToCart.click();
		//Thread.sleep(8000);
		//CartIcon.click();
		Thread.sleep(10000);
		//driver.get("https://m2uat.surplusfurniture.com/checkout/cart/");
		//driver.get("https://reqa.surplusfurniture.com/checkout/cart/");
		MiniCartViewCartButton.click();
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
		Thread.sleep(10000);
		//CartIcon.click();
		//Thread.sleep(12000);
		//driver.get("https://reqa.surplusfurniture.com/checkout/cart/");
		MiniCartViewCartButton.click();
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
	
	
	public void addMatressWarrentyProductToCart() throws InterruptedException {
		driver.findElement(By.xpath("//a[@title='LIVINGROOMS']//span[contains(text(),'LIVINGROOMS')]")).click();
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("window.scrollBy(0,250)", "");
		Thread.sleep(8000);
		GraphiteCasa2PieceSectional.click();
		Thread.sleep(8000);
		js.executeScript("window.scrollBy(0,250)", "");
		Thread.sleep(8000);
		driver.findElement(By.xpath("//*[@id=\"warranty_sku\" and @value=\"TOTALPRO\"][1]")).click();
		Thread.sleep(8000);
		js.executeScript("window.scrollBy(0,250)", "");
		AddToCart.click();
		Thread.sleep(8000);
		//CartIcon.click();
		//Thread.sleep(12000);
		driver.get("https://reqa.surplusfurniture.com/checkout/cart/");
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
		Thread.sleep(8000);
		ProceedToBilling.click();
	}
	
	
	public String paymentUsingCC() throws InterruptedException{
		Thread.sleep(8000);
		driver.findElement(By.xpath("//a[@class='moneris']")).click();
		Thread.sleep(8000);
		//driver.findElement(By.xpath("/html[1]/body[1]/div[2]/main[1]/div[2]/div[1]/div[4]/div[2]/ol[1]/li[3]/div[1]/form[1]/fieldset[1]/div[3]/div[1]/div[1]/div[4]/div[2]/div[2]/div[1]/fieldset[1]/div[2]/div[1]/form[1]/fieldset[1]/div[1]/div[1]/input[1]")).sendKeys("Vikrant");
		//driver.findElement(By.xpath("/html/body/div[2]/main/div[2]/div/div[4]/div[2]/ol/li[3]/div/form/fieldset/div[3]/div/div/div[4]/div[2]/div[2]/div/fieldset/div[2]/div/form/fieldset/div[2]/div/input")).sendKeys("Bulbule");
		// driver.findElement(By.xpath("/html/body/div[2]/main/div[2]/div/div[4]/div[2]/ol/li[3]/div/form/fieldset/div[3]/div/div/div[4]/div[2]/div[2]/div/fieldset/div[2]/div/form/fieldset/fieldset/div/div/div/input")).sendKeys("Test Address");
		//driver.findElement(By.xpath("/html/body/div[2]/main/div[2]/div/div[4]/div[2]/ol/li[3]/div/form/fieldset/div[3]/div/div/div[4]/div[2]/div[2]/div/fieldset/div[2]/div/form/fieldset/div[3]/div/input")).sendKeys("Barrie");
		 //Select State_dropdown= new Select (driver.findElement(By.xpath("")));
		// State_dropdown.selectByVisibleText("Ontario");
		dropdownPage.selectOptionByVisibleText("Ontario");
		// seleniumDropdownHandling.dropDownSelectionByText(CountryDropdown,"Ontario");
		 //driver.findElement(By.xpath("/html/body/div[2]/main/div[2]/div/div[4]/div[2]/ol/li[3]/div/form/fieldset/div[3]/div/div/div[4]/div[2]/div[2]/div/fieldset/div[2]/div/form/fieldset/div[6]/div/input")).sendKeys("A1B 2C3");
		 //Select country_dropdown= new Select (driver.findElement(By.xpath("/html[1]/body[1]/div[2]/main[1]/div[2]/div[1]/div[4]/div[2]/ol[1]/li[3]/div[1]/form[1]/fieldset[1]/div[3]/div[1]/div[1]/div[4]/div[2]/div[2]/div[1]/fieldset[1]/div[2]/div[1]/form[1]/fieldset[1]/div[7]/div[1]/select[1]")));
		  //country_dropdown.selectByVisibleText("Canada");
			/*
			 * driver.findElement(By.xpath(
			 * "/html/body/div[2]/main/div[2]/div/div[4]/div[2]/ol/li[3]/div/form/fieldset/div[3]/div/div/div[4]/div[2]/div[2]/div/fieldset/div[2]/div/form/fieldset/div[8]/div/input"
			 * )).sendKeys("9595478220"); driver.findElement(By.
			 * xpath("//div[@class='payment-method _active']//button[@type='button']")).
			 * click();
			 * driver.findElement(By.xpath("//*[@id=\"moneriscc_cardholder_name\"]")).
			 * sendKeys("Vikrant Bulbule");
			 * driver.findElement(By.xpath("//*[@id=\"moneris_cc_number\"]")).sendKeys(
			 * "5105105105105100"); Select Month_dropdown= new Select
			 * (driver.findElement(By.xpath("//*[@id=\"moneris_expiration\"]")));
			 * Month_dropdown.selectByVisibleText("05 - May"); Select Year_dropdown= new
			 * Select (driver.findElement(By.xpath("//*[@id=\"moneris_expiration_yr\"]")));
			 * Year_dropdown.selectByVisibleText("2026");
			 * driver.findElement(By.xpath("//*[@id=\"moneris_cc_cid\"]")).sendKeys("123");
			 * This CC Field is not availble on UAT server
			 * driver.findElement(By.xpath("//*[@id=\"place-order-trigger\"]/span")).click
			 * (); //Thread.sleep(10000); 
			 
			 */
		 return driver.getTitle();
	}
}
