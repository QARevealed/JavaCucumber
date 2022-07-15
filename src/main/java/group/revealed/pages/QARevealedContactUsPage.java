package group.revealed.pages;

import group.revealed.utils.BasePage;
import lombok.NonNull;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;

import java.util.List;

public class QARevealedContactUsPage extends BasePage {
    @FindBy(tagName = "h1")
    private WebElement title;
    @FindBy(css = "[data-aid='CONTACT_HOURS_COLLAPSED_REND']")
    private WebElement openToday;
    @FindBy(css = "#bs-3 tr")
    private List<WebElement> openHoursList;



    public QARevealedContactUsPage(@NonNull WebDriver driver) {
        super(driver);
        PageFactory.initElements(driver, this);
    }
    public void checkTitle(){
        waitForElementToBeDisplayed(title);
        Assert.assertEquals("Contact Us",title.getText(),"Page title is incorrect");
    }
    public void openHours(){
        click(openToday);
        waitForElementToBeDisplayed(openHoursList.get(0));
        for (WebElement element : openHoursList) {
            System.out.println(element.getText());
        }


    }
}
