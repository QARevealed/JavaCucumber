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

    @Given("User opens Google search page")
    public void userOpensGoogleSearchPage() {
        base.getDriver().get("https://www.google.com");
    }

    @Given("^I navigate to QA Revealed homepage$")
    public void i_navigate_to_QA_Revealed_homepage()
    {
        // Write code here that turns the phrase above into concrete actions
        base.getDriver().get("https://www.qarevealed.com/");
    }

    @When("^I click on contact us tab in menu$")
    public void i_click_on_Nas_tim_link_in_menu()
    {
        // Write code here that turns the phrase above into concrete actions
        base.getRevealedHomePage().clickOnContactUs();
    }

    @Then("^page with team members is opened$")
    public void page_with_team_members_is_opened() throws Throwable
    {

    }


}
