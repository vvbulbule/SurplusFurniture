package reusableMethods;

import java.awt.AWTException;
import java.awt.Robot;
import java.time.Duration;
import java.util.Date;
import java.util.HashMap;
import java.util.NoSuchElementException;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Point;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class WebPageOperations {

	/**
	 * author =Vikrant Bulbule Created Date : 9 March 2023
	 */

	protected WebDriver driver;
	public static int waitTimeInSeconds;
	public static int pageLoadWaitTimeInSeconds;
	public static int alertWaitTimeInSeconds;

	public WebPageOperations(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);

	}

	static {

		// ConfigFileReader configFileReader = new ConfigFileReader();
		// ConfigFileReader.loadPropertiesFile("configuration");
		/*
		 * waitTimeInSeconds = ConfigFileReader.getExplicitWait();
		 * pageLoadWaitTimeInSeconds = ConfigFileReader.getPageLoadWaitTime();
		 * alertWaitTimeInSeconds = ConfigFileReader.getAlertWaitTime();
		 */
		System.out.println(
				"Explicit wait time " + waitTimeInSeconds + " seconds is added for each element to be located");
	}

	public void setWebDriver(WebDriver driver) {
		this.driver = driver;
	}

	public WebDriver getWebDriver() {
		return driver;
	}

	/**
	 * This method is to open a new page
	 * 
	 * @param URL      is the link to be opened in new tab
	 * @param pageName is the name of the page which is expected to redirtect
	 */

	public void OpenNewLink(String URL, String pageName) {
		try {
			System.out.println(" Redirecting to " + pageName + "through URL");
			driver.get(URL);
		} catch (Exception e) {
			// Log.info("Not able to redirect to " + pageName + " through URL");
		}
	}

	public String getCurrentURL() {
		String currentURL = null;
		try {
			currentURL = driver.getCurrentUrl();
		} catch (Exception e) {
			System.out.println("Failed to get current URL");
		}
		return currentURL;

	}

	/**
	 * This method is to used to navigate to particular URL
	 * 
	 * @param URL      is the link to be opened in new tab
	 * @param pageName is the name of the page which is expected to redirtect
	 */
	public void navigateToURL(String URL, String pageName) {
		try {
			System.out.println(" Redirecting to " + pageName + "through URL");
			driver.navigate().to(URL);
		} catch (Exception e) {
			// Log.info("Not able to redirect to " + pageName + " through URL");
		}
	}

	/**
	 * This method is to used to highlight the webelement
	 * 
	 * @param element is the field name which is user defined
	 */

	public void highlightElement(WebElement element) {
		JavascriptExecutor js = (JavascriptExecutor) driver;
		// js.executeScript("arguments[0].setAttribute('style', arguments[1]);",
		// element, "border: 2px solid red;");
		js.executeScript("arguments[0].style.setProperty(arguments[1],arguments[2],'');", element, "border",
				"2px  solid red");
	}

	/**
	 * This method is to used to double click on a webelement
	 * 
	 * @param element is the field name which is user defined
	 */

	public void doubleClick(WebElement element) {
		if ((driver != null) && (element != null))
			(new Actions(driver)).doubleClick(element).build().perform();
	}

	/**
	 * This method is for to check if element is displayed on webpage
	 * 
	 * @param element is WebElement reference
	 * @throws Exception
	 */
	public boolean elementToBeVisible(WebElement element, String elementName) {
		boolean elementVisibilityCheck = false;
		try {
			WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(waitTimeInSeconds));
			wait.until(ExpectedConditions.refreshed(ExpectedConditions.visibilityOf(element)));
			highlightElement(element);
			elementVisibilityCheck = true;

		} catch (Exception e) {
			e.printStackTrace();
			System.out.println(elementName + " is not visible");
			elementVisibilityCheck = false;

		}
		return elementVisibilityCheck;

	}

	/**
	 * This method is for to check if element is displayed on webpage
	 * 
	 * @param element is WebElement reference
	 * @param waiTime is time to wait for element to be visible before throwing
	 *                exception if element not found
	 */
	public boolean elementToBeVisible(WebElement element, String elementName, int waiTime) {
		boolean elementVisibilityCheck = false;
		try {

			WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(waiTime));
			wait.until(ExpectedConditions.refreshed(ExpectedConditions.visibilityOf(element)));
			highlightElement(element);
			elementVisibilityCheck = true;
		} catch (Exception e) {
			System.out.println(element + " is not visible");
			elementVisibilityCheck = false;

		}
		return elementVisibilityCheck;
	}

	/**
	 * This method is used to click the element with throw exception
	 * 
	 * @param element is WebElement reference
	 */

	//
	public void click(WebElement element, String elementName) {
		try {
			WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(waitTimeInSeconds));
			wait.until(ExpectedConditions.refreshed(ExpectedConditions.visibilityOf(element)));
			element.click();
			System.out.println("PASS: successfully clicked on " + elementName);
		} catch (Exception e) {
			System.out.println("Fail: to click on" + elementName);
			e.printStackTrace();

		}
	}

	/**
	 * This method is used to enter Text into text field
	 * 
	 * @param element   is WebElement reference
	 * @param inputText is the text that is passed as in input
	 * @param fieldName is the name of the field to which we need to pass the text
	 * @throws Exception
	 */

	public void enterTextintoField(WebElement element, String inputText, String fieldName) {
		elementToBeVisible(element, fieldName);
		try {
			clear(element, fieldName);
			element.sendKeys(inputText);
			System.out.println("PASS: sucessfully entered " + inputText + " into " + fieldName);
		} catch (NoSuchElementException e) {
			System.out.println("FAIL: To send value on " + fieldName);
			System.out.println(e);
		}
	}

	/**
	 * This method is used to clear text from web application field
	 * 
	 * @param element     is WebElement reference
	 * @param elementName is field name which is user defined
	 */

	public boolean clear(WebElement element, String elementName) {
		boolean isTextCleared = false;
		try {
			element.clear();
			isTextCleared = true;
			System.out.println("PASS: Cleared text from element " + elementName);
		} catch (Exception e) {
			System.out.println("FAIL: To clear value on " + elementName);
		}
		return isTextCleared;
	}

	/**
	 * This method is used to get the text of the field
	 * 
	 * @param element     is WebElement reference
	 * @param elementName is field name which is user defined
	 */

	public String getText(WebElement element, String fieldName) {

		String text = null;
		try {
			elementToBeVisible(element, fieldName);
			text = element.getText();
			System.out.println("PASS: " + fieldName + " text is " + text);
		} catch (Exception e) {
			System.out.println("FAIL: failed to retrieve the element text from" + fieldName);
		}
		return text;
	}

	/**
	 * This method is used to get text of the element using JavaScriptd
	 * 
	 * @param element     is WebElement reference
	 * @param elementName is field name which is user defined
	 */

	public String getTextUsingJavaScript(WebElement element, String fieldName) {
		String text = null;
		try {
			elementToBeVisible(element, fieldName);
			JavascriptExecutor jsExecutor = (JavascriptExecutor) driver;
			text = (String) jsExecutor.executeScript("return arguments[0].value", element);
			System.out.println("PASS: " + fieldName + " text is " + text);
		} catch (Exception e) {
			System.out.println("FAIL: failed to retrieve the element text from" + fieldName);
		}
		return text;
	}

	/**
	 * This method is used to get attributes of the element
	 * 
	 * @param element is WebElement reference
	 * @param name    is field name which is user defined
	 */

	public String getAttribute(WebElement element, String name) {
		return element.getAttribute(name);
	}

	/**
	 * This method is used for thread.sleep
	 * 
	 * @param seconds is the time unit in seconds
	 */

	public static void waitForSeconds(int seconds) {
		try {

			Integer timeInSeconds = Integer.valueOf(seconds);
			Thread.sleep(timeInSeconds * 1000);
		} catch (InterruptedException e) {
			Thread.currentThread().interrupt();
			System.out.println("Problem Rise During Custom Sleep for the page.....");
		}
	}

	/**
	 * 
	 * This method is used to get the page title
	 */

	public String getActualPageTitle() {
		return driver.getTitle();
	}

//	protected List<WebElement> getVisibleElementLists(By locator, String elementName , long waitTimeInSecs) {
//		int visibleElementsCount = 0;
//		int elementsCount = 0;
//		List<WebElement> visibleElementsList = null;
//		try {
//			WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(waitTimeInSecs));
//			visibleElementsList = wait
//					.until(ExpectedConditions.refreshed(ExpectedConditions.visibilityOfAllElementsLocatedBy(locator)));
//			// visibleElementsList = driver.findElements(locator);
//			visibleElementsCount = visibleElementsList.size();
//			System.out.println(
//					"Found visibility for list of elements " + elementName + " with size = " + visibleElementsCount);
//		} catch (Exception e) {
//			try {
//				// if all elements are not visible, then get the list of all Elements by Locator
//				System.out.println(
//						"All elements not visible for " + elementName + " , getting only visible element list...");
//				List<WebElement> lstElements = driver.findElements(locator);
//				if (lstElements != null && lstElements.size() > 0) {
//					visibleElementsList = new ArrayList<WebElement>();
//					elementsCount = lstElements.size();
//					for (int i = 0; i <= elementsCount; i++) {
//						WebElement element = lstElements.get(i);
//						if (element.isDisplayed()) {
//							visibleElementsList.add(element);
//							visibleElementsCount++;
//						}
//					}
//				} else {
//					System.out.println(elementName + " web element list is found with size = " + elementsCount
//							+ " , Visible elements count =" + visibleElementsCount);
//				}
//			} catch (Exception e1) {
//				String errorMessage = elementName + " web element list is found with size = " + elementsCount
//						+ " , Visible elements count =" + visibleElementsCount;
//				System.out.println(errorMessage);
//			}
//		}
//		return visibleElementsList;
//	}

	/**
	 * 
	 * This method is used to hover the mouse of particular webelement
	 * 
	 * @param element     This is the unique attribute to find the of webelement for
	 *                    mousehover action
	 * @param elementname is field name which is user defined
	 * 
	 */

	public WebElement mouseHover(WebElement element, String fieldName) {
		try {
			elementToBeVisible(element, fieldName);
			Actions builder = new Actions(driver);
			builder.moveToElement(element).release().build().perform();
			System.out.println("PASS: Element drag to " + fieldName);
		} catch (Exception e) {
			System.out.println("FAIL: Element to drag to " + fieldName);
		}
		return element;
	}

	/**
	 * 
	 * This method is used to get the value of a webelement
	 * 
	 * @param element     This is the unique attribute to find the webelement
	 * @param elementname is field name which is user defined
	 * 
	 */

	public String getElementValue(WebElement element, String fieldName) {
		String value = null;
		try {
			elementToBeVisible(element, fieldName);
			value = element.getAttribute("value");
			System.out.println(value);
		} catch (Exception e) {
			System.out.println("FAIL: to check the element value");
		}
		return value;
	}

	/**
	 * 
	 * This method is used to get to the value of attribute alt of img tag
	 * 
	 * @param element     This is the unique attribute to find the image
	 * @param elementname is field name which is user defined
	 * 
	 */
	public String getImageAltValue(WebElement element, String fieldName) {

		String alt = null;
		try {
			elementToBeVisible(element, fieldName);
			alt = element.getAttribute("alt");
			System.out.println(alt);
		} catch (Exception e) {
			System.out.println("FAIL: to check the element value");
		}
		return alt;
	}

	/**
	 * 
	 * This method is used to get to the classname of the webelement
	 * 
	 * @param element     This is the unique attribute to find the webelement
	 * @param elementname is field name which is user defined
	 * 
	 */

	public String getElementClassValue(WebElement element, String fieldName) {

		String eleClass = null;
		try {
			elementToBeVisible(element, fieldName);
			eleClass = element.getAttribute("class");
			System.out.println(eleClass);
		} catch (Exception e) {
			System.out.println("FAIL: to check the element value");
		}
		return eleClass;
	}

//	protected List<WebElement> getAllElements(List<WebElement> element, String elementName) {
//
//		int totalSize = 0;
//		try {
//			WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(waitTimeInSeconds));
//			wait.until(ExpectedConditions.refreshed(ExpectedConditions.visibilityOfAllElements(element)));
//			totalSize = element.size();
//			System.out.println("Element size = " + totalSize);
//			for(WebElement webElement  : element) {
//				System.out.println(webElement.getText());
//			}
//		} catch (Exception e) {
//			System.out.println("FAIL: Element not found");
//		}
//		return element;
//	}
	/**
	 * 
	 * This method is used to get to scroll the page till the element is found
	 * 
	 * @param element This is the unique attribute to find the webelement
	 * @param seconds is the unit of time in seconds
	 * 
	 */

	public WebElement scrollDownToElement(WebElement element, String fieldName) {
		try {
			elementToBeVisible(element, fieldName);
			JavascriptExecutor js = (JavascriptExecutor) driver;
			js.executeScript("arguments[0].scrollIntoView();", element);
			System.out.println("PASS: Scrolled down to the view of" + fieldName);
		} catch (Exception e) {
			System.out.println("FAIL: not able to scroll to the view of" + fieldName);
		}
		return element;
	}

	/**
	 * 
	 * This method is used to get to scroll down to the bottom of the page
	 * 
	 */

	public void scrollDownToBottom() {
		try {
			System.out.println("Scrolling down to the bottom of the page");
			JavascriptExecutor js = (JavascriptExecutor) driver;
			js.executeScript("window.scrollTo(0,document.body.scrollHeight)");
		} catch (Exception e) {
			System.out.println("FAIL to Scroll down");
		}
	}

	/**
	 * 
	 * This method is used to Page Refresh or reload
	 * 
	 */
	public void pageRefresh() {
		System.out.println("Reloading the page");
		driver.navigate().refresh();
	}

	/**
	 * 
	 * This method is used to navigate to back button
	 */
	public void browserBackButton() {
		System.out.println("Clicking on browser back button");
		driver.navigate().back();
	}

	/**
	 * 
	 * This method is used for drag and drop the element
	 * 
	 * @param element This is the unique attribute to find the webelement
	 * @param seconds is the unit of time in seconds
	 * @throws Exception
	 * 
	 */

	public void dragToElementByMouse(WebElement element, String fieldName) throws Exception {
		Point elementToBeDragged = null;
		elementToBeVisible(element, fieldName);
		elementToBeDragged = element.getLocation();
		try {
			Robot robot = new Robot();
			// robot.mouseMove(elementToBeDragged.getLocation().x+elementToBeDragged.getSize().width/2,
			// elementToBeDragged.getLocation().y+elementToBeDragged.getSize().getHeight()/2);
			robot.mouseMove(elementToBeDragged.getX(), elementToBeDragged.getY() + 120);
			waitForSeconds(waitTimeInSeconds);
		} catch (AWTException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	/**
	 * 
	 * A convenience method that drags webelement from one area to another area in the webpage. 
	 * 
	 * @param source location of the source element 
	 * @param target is the location of the element to be placed
	 * @param sourceName name of the source
	 * @param targetFieldName name of the target
	 */

	public void dragAndDrop(WebElement source, WebElement target, String sourceName, String targetFieldName) {
		try {
		Actions actions = new Actions(driver);
		elementToBeVisible(source,sourceName);
		elementToBeVisible(target, targetFieldName);
		actions.dragAndDrop(source, target).build().perform();
		System.out.println("PASS: Successfully dragged from " + sourceName + " to " + targetFieldName);
		} catch (Exception e) {
			System.out.println("Fail: failed to drag and drop");
			e.printStackTrace();
		}
	}

	/**
	 * 
	 * This method is used for drag and drop the element uisng javascript executor
	 * 
	 * @param source      is the field from the element have to be dragged from
	 * @param destination is the field from the element have to be dropped
	 * 
	 */

	public void DragAndDropJS(WebElement source, WebElement destination) throws Exception {
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("function createEvent(typeOfEvent) {\n" + "var event =document.createEvent(\"CustomEvent\");\n"
				+ "event.initCustomEvent(typeOfEvent,true, true, null);\n" + "event.dataTransfer = {\n" + "data: {},\n"
				+ "setData: function (key, value) {\n" + "this.data[key] = value;\n" + "},\n"
				+ "getData: function (key) {\n" + "return this.data[key];\n" + "}\n" + "};\n" + "return event;\n"
				+ "}\n" + "\n" + "function dispatchEvent(element, event,transferData) {\n"
				+ "if (transferData !== undefined) {\n" + "event.dataTransfer = transferData;\n" + "}\n"
				+ "if (element.dispatchEvent) {\n" + "element.dispatchEvent(event);\n"
				+ "} else if (element.fireEvent) {\n" + "element.fireEvent(\"on\" + event.type, event);\n" + "}\n"
				+ "}\n" + "\n" + "function simulateHTML5DragAndDrop(element, destination) {\n"
				+ "var dragStartEvent =createEvent('dragstart');\n" + "dispatchEvent(element, dragStartEvent);\n"
				+ "var dropEvent = createEvent('drop');\n"
				+ "dispatchEvent(destination, dropEvent,dragStartEvent.dataTransfer);\n"
				+ "var dragEndEvent = createEvent('dragend');\n"
				+ "dispatchEvent(element, dragEndEvent,dropEvent.dataTransfer);\n" + "}\n" + "\n"
				+ "var source = arguments[0];\n" + "var destination = arguments[1];\n"
				+ "simulateHTML5DragAndDrop(source,destination);", source, destination);
		Thread.sleep(1500);

	}

	/**
	 * 
	 * This method is used to wait for alert to be loaded based on given wait time
	 * 
	 * 
	 */
	public boolean checlIfAlertPresent() {
		boolean alertIsPresent = false;
		try {
			WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(alertWaitTimeInSeconds));
			wait.until(ExpectedConditions.alertIsPresent());
			alertIsPresent = true;
			System.out.println("Alert is right here, switch to it :) ");
		} catch (Exception e) {
			alertIsPresent = false;
			System.out.println("Alert ain't here :(");
		}
		return alertIsPresent;

	}

	public void alertAccept() {
		if (checlIfAlertPresent() == true) {
			driver.switchTo().alert().accept();
			System.out.println("Alert accepted ");

		}

	}

	/**
	 * This method is to Switch to an default iframe
	 * 
	 * @param driver              is the webdriver name
	 * @param attributeValue-This is the unique attribute of the frame to be
	 *                            switched
	 * 
	 */
	public void switchToDefaultFrame() {
		try {
			driver.switchTo().defaultContent();

		} catch (Exception e) {
			System.out.println("Unable to switch to default frame");
			e.printStackTrace();
		}
	}

	public void switchToFrame(WebElement element) {
		try {
			driver.switchTo().frame(element);
		} catch (Exception e) {
			System.out.println("Failed to switch to frame");
			e.printStackTrace();
		}
	}

	public void switchToFrame(int index) {
		try {
			driver.switchTo().frame(index);
		} catch (Exception e) {
			System.out.println("Failed to switch to frame");
			e.printStackTrace();
		}
	}

	public void switchToFrame(String name) {
		try {
			driver.switchTo().frame(name);
		} catch (Exception e) {
			System.out.println("Failed to switch to frame");
			e.printStackTrace();
		}
	}

	/**
	 * This method is to wait for an element to be clickable
	 * 
	 * @param driverriver  is the webdriver value
	 * @param locator-This is the unique attribute of the element
	 * 
	 */
	public boolean waitForElementToBeClickable(WebElement element) {
		boolean flag = false;
		try {
			new WebDriverWait(driver, Duration.ofSeconds(waitTimeInSeconds))
					.ignoring(StaleElementReferenceException.class)
					.until(ExpectedConditions.elementToBeClickable(element));
			highlightElement(element);
			flag = true;
			return flag;

		} catch (Exception e) {

			System.out.println("Element is not clickable");
			e.printStackTrace();
			return flag;
		}
	}

	/**
	 * @Method:closeAllOtherWindows - This method is used to close all open windows
	 *                              except parent window.
	 * @return
	 * @throws InterruptedException
	 */
	public boolean closeAllOtherWindows() throws InterruptedException {
		String Parent_Window = driver.getWindowHandle();
		java.util.Set<String> allWindowHandles = driver.getWindowHandles();
		for (String currentWindowHandle : allWindowHandles) {
			if (!currentWindowHandle.equals(Parent_Window)) {
				driver.switchTo().window(currentWindowHandle);
				driver.close();
				Thread.sleep(2000);
			}
		}
		driver.switchTo().window(Parent_Window);
		if (driver.getWindowHandles().size() == 1)
			return true;
		else
			return false;
	}

	public void closeAllBrowserWindows() {
		driver.quit();
	}

	/**
	 * @Method:getcurrenttime This method is used to return system time in seconds.
	 */
	public int getcurrenttime() {
		long currentDateMS = new Date().getTime();
		int seconds = (int) ((currentDateMS / 1000) % 3600);
		return seconds;
	}

	/**
	 * @Method:MouseClickActionMoveToElement This method is used to move to element
	 *                                       by mouse click action
	 * @param element is the unique attribute value of the webelement
	 */

	public void MouseClickActionMoveToElement(WebElement element) {
		try {
			if
//The below method is defined above
			(waitForElementToBeClickable(element)) {
				// element.click();
				Actions builder = new Actions(driver);
				builder.moveToElement(element).click().build().perform();
				System.out.println("Able to locate and click to element !");

			} else {
				System.out.println("Not able to locate the element !");
			}
		} catch (Exception Ex) {
			System.out.println("Exception occured");
		}

	}

	/*
	 * public boolean pageLoaded() { // wait for jQuery to load and catch if jQuery
	 * doesn't exist JavascriptExecutor js = (JavascriptExecutor) driver;
	 * WebDriverWait wait = new WebDriverWait(driver,
	 * Duration.ofSeconds(pageLoadWaitTimeInSeconds)); ExpectedCondition<Boolean>
	 * jQueryLoad = driver -> { try { return (Boolean)
	 * js.executeScript("return jQuery.active == 0"); } catch (JavascriptException
	 * e) { // no jQuery present return true; } }; // wait for Javascript to load
	 * ExpectedCondition<Boolean> jsLoad = driver ->
	 * js.executeScript("return document.readyState").toString()
	 * .equals("complete"); try { return wait.until(jQueryLoad) &&
	 * wait.until(jsLoad); } catch (TimeoutException e) {
	 * System.err.println("Timeout exception occurred for pageLoaded"); } return
	 * false;
	 * 
	 * }
	 */

	/**
	 * @Method:waitForPageLoad This method will wait 30 seconds for page to load
	 */
	public void waitForPageLoad() {
		try {
			driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(pageLoadWaitTimeInSeconds));
		} catch (TimeoutException e) {
			System.out.println("faile to load page after waiting " + pageLoadWaitTimeInSeconds);
		}
	}

	public void typeCharactersIntoField(WebElement element, String fieldName, String value) throws Exception {
		if(elementToBeVisible(element, fieldName)==true) {
		try {
			String val = value;
			element.clear();
			for (int i = 0; i < val.length(); i++) {
				char c = val.charAt(i);
				String s = new StringBuilder().append(c).toString();
				element.sendKeys(s);
			}
		} catch (Exception e) {

			e.printStackTrace();
		}}
		else {
			
			throw new Exception(fieldName + "not visible"); 
		}
	}

	public static int stringToInt(String input) {
		return Integer.parseInt(input);
	}

	public HashMap<String, String> emailContents() {

		HashMap<String, String> emailContents = new HashMap<String, String>();
		emailContents.put("to", System.getProperty("toEmailAddress"));
		emailContents.put("from", System.getProperty("fromEmailAddress"));
		emailContents.put("cc", System.getProperty("CCEmailAddress"));
		emailContents.put("subject", System.getProperty("emailSubject"));
		emailContents.put("body", System.getProperty("emailBody"));
		emailContents.put("attachment", null);

		return emailContents;

	}

}
