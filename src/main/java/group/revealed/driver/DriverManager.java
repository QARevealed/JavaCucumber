package group.revealed.driver;

import group.revealed.configuration.Configuration;
import org.openqa.selenium.remote.RemoteWebDriver;

public class DriverManager {

    public static final ThreadLocal<RemoteWebDriver> driver = new ThreadLocal<>();
    private static final Configuration configuration = new Configuration();

    private DriverManager() {
    }

    public static RemoteWebDriver getDriver() {
        if (driver.get() == null ? driver.get() == null : driver.get().getSessionId() == null) {
            DriverFactory.createInstance(configuration);
        }
        return driver.get();
    }

    public static void quitDriver() {
        if (driver.get() != null) {
            driver.get().quit();
        }
    }
}
