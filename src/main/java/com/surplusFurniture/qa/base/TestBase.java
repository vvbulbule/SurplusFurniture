package com.surplusFurniture.qa.base;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.time.Duration;
import java.util.HashMap;
import java.util.Properties;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.Select;

import com.surplusFurniture.qa.util.TestUtil;

import io.github.bonigarcia.wdm.WebDriverManager;
import reusableMethods.SeleniumDropdownHandling;

public class TestBase {
	
	
	public static Properties prop;
	
	public static WebDriver driver;
	public static SeleniumDropdownHandling  seleniumDropdownHandling; 
	
	public TestBase(){
		
		try{
			prop= new Properties();
			//FileInputStream ip =new FileInputStream("/home/vbulbule/git/AutomationFramework/AutomationFrameWork/src/main/java/com/automation/qa/config/config.properties");
			FileInputStream ip =new FileInputStream(System.getProperty("user.dir")+"/src/main/java/com/surplusFurniture/qa/config/config.properties");
			prop.load(ip);
		}catch(FileNotFoundException e){
			e.printStackTrace();
		}catch (IOException e){
			e.printStackTrace();
		}
	}

	public static void initialization() throws InterruptedException {
		String browserName = prop.getProperty("browser");
		
		if(browserName.equals("chrome")){
			//System.setProperty("webdriver.chrome.driver", "/home/vbulbule/Selenium 3.14/chromedriver");
			//System.setProperty("webdriver.chrome.driver", System.getProperty("user.dir")+"/src/main/resources/chromedriver");
			//Handled the Geo Location Popup
			
			  HashMap<String, Integer> conentSettings = new HashMap<String, Integer>();
			  HashMap<String, Object> profile = new HashMap<String, Object>();
			  HashMap<String, Object> prefs = new HashMap<String, Object>();
			  
			  //To Block Geo Location popup 0 - Ask (Default), 1- Allow, 2- Block
			  conentSettings.put("geolocation", 2);
			  
			  profile.put("managed_default_content_settings", conentSettings);
			  prefs.put("profile", profile);
			 

			ChromeOptions options = new ChromeOptions();
			options.setExperimentalOption("prefs", prefs);	
			options.addArguments("--remote-allow-origins=*");
			System.setProperty("webdriver.chrome.driver", "C:\\Users\\Encora\\Desktop\\Learning\\Selenium Training\\New Driver As on 19 Dec 2023\\chromedriver-win64\\chromedriver.exe");
			//WebDriverManager.chromedriver().setup();
			 driver= new ChromeDriver(options);
			
		} else if (browserName.equals("FF")){
			
			WebDriverManager.firefoxdriver().setup();
			 driver= new FirefoxDriver();
		}
		
		
		
		
		driver.manage().window().maximize();
		
		driver.get(prop.getProperty("url"));
		//in latest selenium above 4 Duration.ofSeconds is used and wait time comes from TestUtil Class
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(TestUtil.PAGE_LOAD_TIMEOUT));
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(TestUtil.IMPLICIT_WAIT));
		
		
	
		
		
		  Select country_dropdown= new Select (driver.findElement(By.id("country")));
		  country_dropdown.selectByVisibleText("Canada");
		  
		  Thread.sleep(5000);
		  //driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(70));
		  
		  
		  
		  Select state_dropdown= new Select (driver.findElement(By.id("provinces")));
		  //dropdown.selectByIndex(2); 
		  state_dropdown.selectByVisibleText("Ontario");
		 
		 
		
		    //Using Generic method to select dropdown 
			// Create the object of SeleniumDropdownHandling Class
			//seleniumDropdownHandling= new SeleniumDropdownHandling(driver);
			//seleniumDropdownHandling.dropDownSelectionByText(driver.findElement(By.id("country")), "country");
			//seleniumDropdownHandling.dropDownSelectionByText(driver.findElement(By.id("provinces")), "Ontario");
		 
		
		
		//Select Belleville Store
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		//WebElement selectstore = driver.findElement(By.xpath("//a[@class='action submit primary button' and @href='https://m2uat.surplusfurniture.com/warehouse/store/choose/city/30?referer=https://m2uat.surplusfurniture.com/']"));
		//Select Barrie Store
		WebElement selectstore = driver.findElement(By.xpath("//div[@id='modal-overlay']//div[1]//a[1]"));
		
		selectstore.click();
		Thread.sleep(6000);
	}
	
	
}
