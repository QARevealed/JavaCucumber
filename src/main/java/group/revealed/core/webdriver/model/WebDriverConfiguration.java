package group.revealed.core.webdriver.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.openqa.selenium.MutableCapabilities;

import java.net.URL;

/**
 * Configuration to start new RemoteWebDriver.
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class WebDriverConfiguration {

    // Grid URL: URL or combination of URL and user's information for SauceLabs
    private URL gridHost;
    // Capabilities for WebDriver. Info like browser, OS, resolution, ...
    private MutableCapabilities mutableCapabilities;
}
