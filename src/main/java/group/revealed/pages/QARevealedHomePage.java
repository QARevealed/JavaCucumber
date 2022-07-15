package group.revealed.pages;

import group.revealed.utils.BasePage;
import lombok.NonNull;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.List;


public class QARevealedHomePage extends BasePage
{
    @FindBy(id = "nav-14887")
    private WebElement navMenuHolder;



    public QARevealedHomePage(@NonNull WebDriver driver) {
        super(driver);
        PageFactory.initElements(driver, this);
    }

    public void clickOnContactUs()
    {
        List<WebElement> navMenuItems = navMenuHolder.findElements(By.tagName("li"));
        WebElement contactUsTab = navMenuItems.get(2);
        click(contactUsTab);

    }
}
