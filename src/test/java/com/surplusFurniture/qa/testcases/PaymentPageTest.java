package com.surplusFurniture.qa.testcases;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.surplusFurniture.qa.base.TestBase;
import com.surplusFurniture.qa.pages.HomePage;
import com.surplusFurniture.qa.pages.PaymentPage;

import reusableMethods.SeleniumDropdownHandling;

public class PaymentPageTest extends TestBase{
	
	PaymentPage paymentPage;
	
	
	public PaymentPageTest(){
		super();
	}
	
	@BeforeMethod
	public void setup() throws InterruptedException{
		initialization();
		paymentPage = new PaymentPage();
		
	}

	//Test Case to place the order of  simple Product type
		@Test(priority=1)
		public void PaymentTestSimpleProduct () throws InterruptedException{
		
			paymentPage.addSimpleProductToCart();
			String PageTitle =paymentPage.paymentUsingCC();
			Assert.assertEquals(PageTitle, "Success Page", "Order is not Placed");
		
	    }
		
		
		//Test Case to place the order of  Bundled Product type
		@Test(priority=3)
		public void PaymentTestBundledProduct () throws InterruptedException{
		
			
			paymentPage.addBundledProductToCart();
			String PageTitle =paymentPage.paymentUsingCC();
			Assert.assertEquals(PageTitle, "Success Page", "Order is not Placed");
		
	    }
		
		//Test Case to place the order of  Bundled Product type
				@Test(priority=3)
				public void PaymentTesMatressWarrentyProduct () throws InterruptedException{
				
					
					paymentPage.addMatressWarrentyProductToCart();
					String PageTitle =paymentPage.paymentUsingCC();
					Assert.assertEquals(PageTitle, "Success Page", "Order is not Placed");
				
			    }
}

