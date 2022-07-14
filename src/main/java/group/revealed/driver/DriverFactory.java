package group.revealed.driver;

import group.revealed.configuration.Configuration;
import group.revealed.core.webdriver.WebDriverProvider;
import group.revealed.core.webdriver.model.WebDriverConfiguration;
import org.openqa.selenium.MutableCapabilities;
import org.openqa.selenium.Platform;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.stringtemplate.v4.ST;

import java.net.MalformedURLException;
import java.net.URL;
import java.time.Duration;
import java.util.Arrays;
import java.util.concurrent.TimeUnit;


public class DriverFactory {

    public static final ThreadLocal<String> testName = new ThreadLocal<>();


    private DriverFactory() {
    }

    public static void createInstance(Configuration configuration)
    {
        try
        {
            var url = "";
            if ("local".equals(configuration.getServiceProvider()))
            {
                createLocalWebDriverInstance(configuration);
            }
            else
            {
                if ("SauceLabs".equals(configuration.getServiceProvider()))
                {
                    url = new ST(configuration.getSauceLabsURL()).add("user", configuration.getSauceLabsUser()).add("apikey", configuration.getSauceLabsApiKey()).render();
                }
                else if ("BrowserStack".equals(configuration.getServiceProvider()))
                {
                    url = new ST(configuration.getBrowserstackURL()).add("user", configuration.getBrowserstackUser()).add("apikey", configuration.getBrowserstackApiKey()).render();
                }
                final var webDriverUrl = new URL(url);

                var webDriverConfiguration = new WebDriverConfiguration(webDriverUrl, getCapabilities(configuration));
                RemoteWebDriver driver = new WebDriverProvider(webDriverConfiguration).createWebDriver();

                DriverManager.driver.set(driver);

                updateWebDriverSettings(configuration);
            }
        }
        catch (MalformedURLException e)
        {
            e.printStackTrace();
        }
    }

        /**
     * Create local instance of WebDriver.
     *
     * @param configuration - basic configuration
     */
    private static void createLocalWebDriverInstance(Configuration configuration) {


            DriverManager.driver.set(new ChromeDriver());
            updateLocalWebDriverSettings(configuration);
    }

    /**
     * Setup Capabilities for WebDriver
     */
    private static MutableCapabilities getCapabilities(Configuration configuration) {
        var driverOpts = new MutableCapabilities();
        if ("SauceLabs".equals(configuration.getServiceProvider())) {
            driverOpts = getSauceLabsCapabilities(configuration);
        } else if ("BrowserStack".equals(configuration.getServiceProvider())) {
            driverOpts = getBrowserStackCapabilities(configuration);
        }

        return driverOpts;
    }

    /**
     * Setup Basic WebDriver Browser Settings
     */
    private static void updateWebDriverSettings(Configuration configuration) {
        DriverManager.driver.get().manage().timeouts().implicitlyWait(Integer.parseInt(configuration.getImplicitWaitTime()), TimeUnit.SECONDS);
        DriverManager.driver.get().manage().window().maximize();
        DriverManager.driver.get().manage().deleteAllCookies();
    }


    private static void updateLocalWebDriverSettings(Configuration configuration) {
        final var manage = DriverManager.driver.get().manage();
        manage.timeouts().implicitlyWait(Duration.ofSeconds(Long.parseLong(configuration.getImplicitWaitTime())));
        manage.window().maximize();
        manage.deleteAllCookies();
    }
    /**
     * Setup SauceLabs WebDriver capabilities
     *
     * @param configuration
     * @return SauceLabs capabilities
     */
    private static MutableCapabilities getSauceLabsCapabilities(Configuration configuration) {
        final var platformName = configuration.getPlatform().toUpperCase();
        final var browserName = configuration.getBrowser().toLowerCase();
        final var browserVersion = configuration.getBrowserVersion().toLowerCase();

        var sauceOpts = new MutableCapabilities();
        sauceOpts.setCapability("extendedDebugging", true);
        sauceOpts.setCapability("timeZone", "Zurich");
        sauceOpts.setCapability("name", testName.get());
        sauceOpts.setCapability("screenResolution",
                Arrays.asList("MAC", "MACOS", "SIERRA", "HIGH_SIERRA", "MOJAVE").contains(platformName) ? "1920x1440" : "1920x1080");

        var driverOpts = new MutableCapabilities();
        driverOpts.setCapability("sauce:options", sauceOpts);
        driverOpts.setCapability(CapabilityType.PLATFORM_NAME, Platform.valueOf(platformName));
        driverOpts.setCapability(CapabilityType.BROWSER_NAME, browserName);
        driverOpts.setCapability(CapabilityType.BROWSER_VERSION, browserVersion);

        return driverOpts;
    }

    /**
     * Setup BrowserStack WebDriver capabilities
     *
     * @param configuration
     * @return BrowserStack capabilities
     */
    private static MutableCapabilities getBrowserStackCapabilities(Configuration configuration) {
        final var platformName = configuration.getPlatform().toUpperCase();
        final var platformVersion = configuration.getPlatformVersion();
        final var browserName = configuration.getBrowser().toLowerCase();
        final var browserVersion = configuration.getBrowserVersion().toLowerCase();

        var driverOpts = new MutableCapabilities();
        driverOpts.setCapability("os", platformName);
        driverOpts.setCapability("os_version", platformVersion);
        driverOpts.setCapability("browser", browserName);
        driverOpts.setCapability("browser_version", browserVersion);

        return driverOpts;
    }
}
