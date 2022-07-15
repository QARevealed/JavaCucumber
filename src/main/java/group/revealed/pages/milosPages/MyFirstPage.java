package group.revealed.pages.milosPages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;

public class MyFirstPage {

    public MyFirstPage(WebDriver driver) {
        PageFactory.initElements(driver, this);
    }
}
