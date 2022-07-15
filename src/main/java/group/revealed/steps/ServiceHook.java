package group.revealed.steps;

import group.revealed.configuration.Configuration;
import group.revealed.driver.DriverFactory;
import group.revealed.driver.DriverManager;
import group.revealed.model.constant.SlackChannel;
import group.revealed.slack.Slack;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;

public class ServiceHook {

    private final Configuration configuration = new Configuration();

    @Before
    public void before(Scenario scenario) {
        DriverFactory.testName.set(scenario.getName());
    }

    @After
    public void after(Scenario scenario) {
        if (!scenario.isFailed()) {
       //     Slack.postSlackTextMessage(":white_check_mark: *" + scenario.getName() + "* :white_check_mark:", SlackChannel.QA_disco);
        }
        // send results to SauceLabs/BrowserStack
        if ("SauceLabs".equals(configuration.getServiceProvider())) {
            var script = String.format("sauce:job-result=%s", scenario.getStatus().toString().toLowerCase());
            DriverManager.getDriver().executeScript(script);
        } else if ("BrowserStack".equals(configuration.getServiceProvider())) {
            var testName = ("browserstack_executor: {\"action\": \"setSessionName\", \"arguments\": {\"name\":\"%s\" }}");
            var testStatus = "browserstack_executor: {\"action\": \"setSessionStatus\", \"arguments\": {\"status\":\"%s\"}}";
            DriverManager.getDriver().executeScript(String.format(testName, scenario.getName()));
            DriverManager.getDriver().executeScript(String.format(testStatus, scenario.getStatus()));
        }

        // closing browser if test runner class
        DriverManager.quitDriver();
    }
}
