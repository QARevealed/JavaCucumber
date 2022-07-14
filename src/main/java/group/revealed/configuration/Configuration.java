package group.revealed.configuration;

import lombok.Getter;
import lombok.Setter;
import org.jeasy.props.annotations.Property;
import org.jeasy.props.annotations.SystemProperty;

import static org.jeasy.props.PropertiesInjectorBuilder.aNewPropertiesInjector;

@Getter
@Setter
public class Configuration {

    private static final String CONFIGURATION_FILE_PATH = "config/configuration.properties";

    public Configuration() {
        aNewPropertiesInjector().injectProperties(this);
    }

    @Property(source = CONFIGURATION_FILE_PATH, key = "implicitWaitTime")
    private String implicitWaitTime;

    @SystemProperty(value = "serviceProvider", defaultValue = "local")
    private String serviceProvider;
    @SystemProperty(value = "platform", defaultValue = "Windows")
    private String platform;
    @SystemProperty(value = "platformVersion", defaultValue = "10")
    private String platformVersion;
    @SystemProperty(value = "browser", defaultValue = "Chrome")
    private String browser;
    @SystemProperty(value = "browserVersion", defaultValue = "latest-1")
    private String browserVersion;

    // Saucelabs parameters
    @Property(source = CONFIGURATION_FILE_PATH, key = "saucelabs.URL")
    private String sauceLabsURL;
    @Property(source = CONFIGURATION_FILE_PATH, key = "saucelabs.user")
    private String sauceLabsUser;
    @Property(source = CONFIGURATION_FILE_PATH, key = "saucelabs.apikey")
    private String sauceLabsApiKey;

    // BrowserStack parameters
    @Property(source = CONFIGURATION_FILE_PATH, key = "browserstack.URL")
    private String browserstackURL;
    @Property(source = CONFIGURATION_FILE_PATH, key = "browserstack.user")
    private String browserstackUser;
    @Property(source = CONFIGURATION_FILE_PATH, key = "browserstack.apikey")
    private String browserstackApiKey;

    // Jira and Xray parameters

    @Property(source = CONFIGURATION_FILE_PATH, key = "jira.username")
    private String jiraUsername;
    @Property(source = CONFIGURATION_FILE_PATH, key = "jira.token")
    private String jiraToken;
    @Property(source = CONFIGURATION_FILE_PATH, key = "jira.url")
    private String jiraURL;
    @Property(source = CONFIGURATION_FILE_PATH, key = "xray.clientId")
    private String xrayClientId;
    @Property(source = CONFIGURATION_FILE_PATH, key = "xray.clientSecret")
    private String xrayClientSecret;
}
