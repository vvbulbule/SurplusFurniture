package reusableMethods;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

public class DropdownPage {
	
	private final WebDriver driver;

    // Constructor
    public DropdownPage(WebDriver driver) {
        this.driver = driver;
    }

    // Locator
    By dropdownLocator = By.xpath("/html/body/div[2]/main/div[2]/div/div/div[2]/div[2]/ol/li[3]/div/form/fieldset/div[3]/div/div/div[3]/div[2]/div[2]/div/fieldset/div[2]/div/form/fieldset/div[4]/div/select");

    // Action
    public void selectOptionByVisibleText(String optionText) {
        WebElement dropdown = driver.findElement(dropdownLocator);
        Select select = new Select(dropdown);
        select.selectByVisibleText(optionText);
    }

    public String getSelectedOptionText() {
        WebElement dropdown = driver.findElement(dropdownLocator);
        Select select = new Select(dropdown);
        return select.getFirstSelectedOption().getText();
    }

}
