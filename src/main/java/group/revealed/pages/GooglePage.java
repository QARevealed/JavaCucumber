package group.revealed.pages;

import org.openqa.selenium.*;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.List;

public class GooglePage {

    @FindBy(id = "L2AGLb")
    private WebElement consentAgreeButton;
    @FindBy(name = "q")
    private WebElement searchInputField;
    @FindBy(css = "#search .g")
    private List<WebElement> searchResults;

    public GooglePage(WebDriver driver) {
        PageFactory.initElements(driver, this);
    }

    public void performSearch(String query) {
        acceptConsent();
        searchInputField.clear();
        searchInputField.sendKeys(query);
        searchInputField.sendKeys(Keys.ENTER);
    }

    public String getFirstResult() {
        return searchResults.get(0).getText();
    }

    public String getSiteOfFirstResult() {
        return searchResults.get(0).findElement(By.tagName("cite")).getText();
    }

    private void acceptConsent() {
        try {
            consentAgreeButton.click();
        } catch (NoSuchElementException ignored) {
        }
    }
}
