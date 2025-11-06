package reusableMethods;


import java.util.List;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

public class SeleniumDropdownHandling extends WebPageOperations {

	/**
	 * Constructor to use webdriver object
	 */
	public SeleniumDropdownHandling(WebDriver driver) {

		super(driver);

	}
	
	

	/**
	 * This method is for simple dropdown selection by visibleText
	 * 
	 * @param element    is the unique attribute to find an dropdown element   
	 * @param visibleText-This is the text which is to be selected as dropdown value
	 * @param fieldName-is the name of the field
	 */
	public  void dropDownSelectionByText(WebElement element, String visibleText)  {
	
		try {			
			Select dropdown = new Select(element);
			dropdown.selectByVisibleText(visibleText);
			System.out.println("PASS: Successfully selected " + visibleText + " from " + element + " dropdown");
		} catch (Exception e) {
			System.out.println(
					"FAIL: Not able to select " + visibleText + " from " + element + " dropdown" + e.getMessage());
		}
		
	}
	
	
	

	/**
	 * This method is for simple dropdown selection by value
	 * 
	 * @param element    is the unique attribute to find an dropdown element   
	 * @param value-This is the value attribute value which is to be selected as dropdown value
	 * @param fieldName-is the name of the field
	 */
	public  void dropDownSelectionByValue(WebElement element, String value,String fieldName)  {
		elementToBeVisible(element,fieldName);
		try {		
			Select dropdownElement = new Select(element);
			dropdownElement.selectByValue(value);
			System.out.println("PASS: Successfully selected " + value + " from " + fieldName + " dropdown");
		} catch (Exception e) {
			System.out.println("FAIL: Not able to select " + value + " from " + fieldName + " dropdown");
		}
	}

	/**
	 * This method is for simple dropdown selection by using index 
	 * 
	 * @param element    is the unique attribute to find an dropdown element   
	 * @param index-index starting with 0 will select values based on indexing.
	 * @param fieldName-is the name of the field
	 */
	public  void dropDownSelectionByIndex(WebElement element, int dropDownValueIndex,String fieldName)  {
		elementToBeVisible(element,fieldName);
		try {
			highlightElement(element);
			Select dropdownElement = new Select(element);
			dropdownElement.selectByIndex(dropDownValueIndex);
			System.out.println(
					"PASS: Successfully selected " + dropDownValueIndex + " Index value from " + element + " dropdown");
		} catch (Exception e) {
			System.out.println("FAIL: Not able to select " + dropDownValueIndex + " Index value from " + element
					+ " dropdown" + e.getMessage());
		}
	}

	/**
	 * This method is for fetch all available options from a dropdown
	 * 
	 * @param element  - is the unique attribute to find an dropdown element   
	 * @param fieldName-is the name of the field
	 */
	public List<WebElement> getDropdownOptions(WebElement element,String fieldName) {
		List<WebElement> listOptions = null;
		elementToBeVisible(element,fieldName);
		try {
			Select dropdown = new Select(element);
			 listOptions = dropdown.getOptions();
			if (listOptions != null) {
				for (WebElement listOption : listOptions) {
					System.out.println("PASS : List of dropdown values : ");
					System.out.println(listOption.getText());
				}
			} else
				System.out.println(element + " does not contain any values");

		} catch (Exception e) {
			System.out.println("FAIL : Unable to fetch dropdown values from " + element + e.getMessage());
		}
		return listOptions;

	}
	
	/**
	 * 
	 * returns the first dropdown option selected
	 * 
	 * @param element  - is the unique attribute to find an dropdown element   
	 * @param fieldName-is the name of the field
	 */
	public WebElement getSelectedOption(WebElement element, String fieldName) {
		WebElement selectedOption = null;
		elementToBeVisible(element,fieldName);
		try {
			Select dropdown = new Select(element);
			 selectedOption = dropdown.getFirstSelectedOption();
		} catch (Exception e) {
			System.out.println("FAIL : Unable to fetch selected option from " + element + e.getMessage());
		}
		return selectedOption;

	}
	
	public void SelectMultipleDropdownValuesByText(WebElement element, int numberOfValues, List<String> inputText) throws Exception {
		
		Select select = new Select(element);
		if(select.isMultiple()) {
			List<WebElement> options = select.getOptions();
			int totalOptionsCount = options.size();
			if(options.isEmpty()) {
				throw new NullPointerException("drop down is empty");
			}
			else if (!options.isEmpty() && numberOfValues>totalOptionsCount) {
				throw new Exception("total drop down values to be selected are greater than available dropdown options");
				
			}
			else if(!options.isEmpty() && numberOfValues<totalOptionsCount) {
			for (int i = 0; i < numberOfValues; i++) {
				//dropDownSelectionByText(element, inputText.get(i), "select " + i + "option from dropdown");
			}
			
		}
		}
		
		
	}
	
	
}
