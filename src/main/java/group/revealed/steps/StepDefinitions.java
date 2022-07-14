package group.revealed.steps;

import com.github.javafaker.Faker;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import lombok.AllArgsConstructor;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Assert;

import java.util.ArrayList;
import java.util.List;

@AllArgsConstructor
public class StepDefinitions {

    private final Base base;

    private void clickOnAcceptCookies()
    {
        List<WebElement> cookies = base.getDriver().findElements(By.id("onetrust-accept-btn-handler"));
        if (cookies.size()>0)
        {
            WebElement acceptAll = cookies.get(0);
            acceptAll.click();
        }
    }

    @Given("User opens Google search page")
    public void userOpensGoogleSearchPage() {
        base.getDriver().get("https://www.google.com");
    }

    @Given("I am on a profile page")
    public void iAmOnAProfilePage()
    {
/*        base.getDriver().get("https://profile.s.onelog.ch/global.profile?lang=en");
        clickOnAcceptCookies();
        WebElement email = base.getDriver().findElement(By.id("email"));
        email.sendKeys("velibor.mandzo@onelog.ch");
        WebElement continueButton = base.getDriver().findElement(By.id("first-step-continue-btn"));
        continueButton.click();

        base.getWdWait().until(ExpectedConditions.presenceOfElementLocated(By.name("password")));
        base.getDriver().findElement(By.name("password")).sendKeys("Italija2022");
        List<WebElement> natives = base.getDriver().findElements(By.id("native-login-btn"));
        if(natives.size()>0)
        {
            base.getDriver().findElement(By.id("native-login-btn")).click();
        }
        List<WebElement> nobttns = base.getDriver().findElements(By.id("webauthn-no-btn"));
        if (nobttns.size()>0)
        {
            base.getDriver().findElement(By.id("webauthn-no-btn")).click();
        }
        base.getWdWait().until(ExpectedConditions.urlContains("global.profile"));*/
    }

    @Given("User is on the Welcome page")
    public void userIsOnTheWelcomePage()
    {
        base.getDriver().get("https://welcome.s.onelog.ch");
        WebElement cookie = base.getDriver().findElement(By.id("onetrust-accept-btn-handler"));
        base.getWdWait().until(ExpectedConditions.stalenessOf(cookie));
        clickOnAcceptCookies();
    }

    @And("User populated {string} email address")
    public void userPopulatedEmailAddress(String arg0)
    {
        base.getWdWait().until(ExpectedConditions.presenceOfElementLocated(By.id("email")));
        WebElement emailField = base.getDriver().findElement(By.id("email"));
        emailField.sendKeys(arg0);
    }


    @Given("^I navigate to QA Revealed homepage$")
    public void i_navigate_to_QA_Revealed_homepage()
    {
        // Write code here that turns the phrase above into concrete actions
        base.getDriver().get("https://www.qarevealed.com/");
    }

    @When("^I click on Nas tim link in menu$")
    public void i_click_on_Nas_tim_link_in_menu()
    {
        // Write code here that turns the phrase above into concrete actions
        WebElement nasTim = base.getDriver().findElement(By.id("comp-kggecc8g5label"));
        nasTim.click();
    }

    @Then("^page with team members is opened$")
    public void page_with_team_members_is_opened() throws Throwable
    {
        // Write code here that turns the phrase above into concrete actions
        base.getWdWait().until(ExpectedConditions.presenceOfElementLocated(By.id("comp-kggf11xo1")));
        WebElement title = base.getDriver().findElement(By.id("comp-kggf11xo1"));
        System.out.println(title.getText());
        Assert.assertTrue(title.getText().contains("NAŠ TIM"));
        Assert.assertTrue(base.getDriver().getCurrentUrl().contains("https://www.qarevealed.com/nas-tim"));
        Thread.sleep(4000); // left for visual confirmation
    }


}
