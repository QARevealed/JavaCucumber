package runners;

import org.junit.runner.RunWith;

import cucumber.api.CucumberOptions;
import cucumber.api.junit.Cucumber;

@RunWith(Cucumber.class)
@CucumberOptions(
        plugin = {"html:target/cucumber-reports/cucumber.html",
        "json:target/cucumber-reports/cucumber.json",
                "pretty"},
        monochrome = true,
        tags = {"@runit,@run","~@ignore"},
        features = {"src/test/resources"},
        glue = {"bindings"}
)

public class MyRunner {

}