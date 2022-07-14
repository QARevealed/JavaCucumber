package group.revealed.core.webdriver;

import group.revealed.core.webdriver.model.WebDriverConfiguration;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.openqa.selenium.MutableCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;

import java.net.URL;

/**
 * WebDriver provider class.
 */
@RequiredArgsConstructor
public class WebDriverProvider {

    @NonNull
    private final WebDriverConfiguration webDriverConfiguration;

    /**
     * RemoteWebDriver provider method. Creates remote web driver for provided information.
     *
     * @return new instance of remote web driver
     */
    public RemoteWebDriver createWebDriver() {
        final URL gridHost = webDriverConfiguration.getGridHost();
        final MutableCapabilities capabilities = webDriverConfiguration.getMutableCapabilities();
        return new RemoteWebDriver(gridHost, capabilities);
    }
}
