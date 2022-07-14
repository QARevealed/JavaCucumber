package group.revealed.utils;

import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.reflect.FieldUtils;
import org.awaitility.core.ConditionTimeoutException;
import org.openqa.selenium.*;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.ArrayList;
import java.util.Objects;

import static java.util.concurrent.TimeUnit.MILLISECONDS;
import static java.util.concurrent.TimeUnit.SECONDS;
import static org.awaitility.Awaitility.await;
import static org.testng.Assert.fail;

    @RequiredArgsConstructor
    public abstract class BasePage
    {


        @NonNull
        protected final WebDriver driver;

        /**
         * Basic web element click method. Before actual click action, wait for element to be clickable will be performed
         * with move focus to web element.
         *
         * @param element on which to perform click
         */
        public void click(WebElement element) {
            waitForElementToBeClickable(element);
            moveFocusToElement(element);
            element.click();
        }

        /**
         * Basic web element populate with text method. Before actual populate action, wait for element to be display will be performed
         * with clear text of web element.
         *
         * @param element which to populate
         * @param text    to populate web element
         */
        public void populateField(WebElement element, String text) {
            waitForElementToBeDisplayed(element);
            element.clear();
            element.sendKeys(text);
        }

        /**
         * Basic/Char by char web element populate with text method. Before actual populate action, clear text of web element will be performed
         * and text will be populated char by char.
         *
         * @param element which to populate
         * @param text    to populate web element
         */
        public void populateFieldCharByChar(WebElement element, String text) {
            element.clear();
            for (char letter : text.toCharArray()) {
                element.sendKeys(Character.toString(letter));
            }
        }

        /**
         * Basic COMMAND+A + DEL to clear input field.
         *
         * @param element which to clear
         */
        public void manuallyClearInputFieldCmnd(WebElement element) {
            element.sendKeys(Keys.chord(Keys.COMMAND, "a"));
            element.sendKeys(Keys.DELETE);
        }

        /**
         * Basic CONTROL+A + DEL to clear input field.
         *
         * @param element which to clear
         */
        public void manuallyClearInputFieldCtrl(WebElement element) {
            element.sendKeys(Keys.chord(Keys.CONTROL, "a"));
            element.sendKeys(Keys.DELETE);
        }

        /**
         * Basic/Char by char web element clear text method.
         *
         * @param element which to clear
         */
        public void manuallyClearInputFieldCharByChar(WebElement element) {
            while (element.getAttribute("value").length() > 0)
                element.sendKeys(Keys.BACK_SPACE);
        }

        /**
         * Wait for element to be clickable.
         *
         * @param element on which to perform wait
         * @param timeout wait in seconds
         * @return boolean if condition was met
         */
        public boolean waitForElementToBeClickable(WebElement element, Integer... timeout) {
            int timeToWait = timeout.length > 0 ? timeout[0] : 10;
            return waitForCondition(ExpectedConditions.elementToBeClickable(element), timeToWait);
        }

        /**
         * Wait for element to be clickable and return web element.
         *
         * @param element on which to perform wait
         * @param timeout wait in seconds
         * @return web element on which wait was performed
         */
        public WebElement waitForElementToBeClickableAndGetElement(WebElement element, Integer... timeout) {
            return waitForElementToBeClickable(element, timeout) ? element : null;
        }

        /**
         * Wait for element to be displayed.
         *
         * @param element on which to perform wait
         * @param timeout wait in seconds
         * @return boolean if condition was met
         */
        public boolean waitForElementToBeDisplayed(WebElement element, Integer... timeout) {
            int timeToWait = timeout.length > 0 ? timeout[0] : 10;
            return waitForCondition(ExpectedConditions.visibilityOf(element), timeToWait);
        }

        /**
         * Wait for element to be displayed and return web element.
         *
         * @param element on which to perform wait
         * @param timeout wait in seconds
         * @return web element on which wait was performed
         */
        public WebElement waitForElementToBeDisplayedAndGetElement(WebElement element, Integer... timeout) {
            return waitForElementToBeDisplayed(element, timeout) ? element : null;
        }

        /**
         * Wait for frame to be available and switch to it.
         *
         * @param element (frame) on which to perform wait
         * @param timeout wait in seconds
         * @return boolean if condition was met
         */
        public boolean waitForFrameToBeAvailableAndSwitchToIt(WebElement element, Integer... timeout) {
            int timeToWait = timeout.length > 0 ? timeout[0] : 10;
            return waitForCondition(ExpectedConditions.frameToBeAvailableAndSwitchToIt(element), timeToWait);
        }

        /**
         * Wait for web element to be enabled, attribute not to be disabled.
         *
         * @param element on which to perform wait
         */
        public void waitForElementToBeEnabled(WebElement element) {
            try {
                await()
                        .atMost(Duration.ofSeconds(10))
                        .pollInterval(Duration.ofMillis(250))
                        .until(() -> !Boolean.parseBoolean(element.getAttribute("disabled")));
            } catch (ConditionTimeoutException e) {
                fail("Element is not enabled");
            }
        }

        /**
         * Wait for web element to be disabled, attribute to be disabled.
         *
         * @param element on which to perform wait
         */
        public void waitForElementToBeDisabled(WebElement element) {
            try {
                await()
                        .atMost(Duration.ofSeconds(10))
                        .pollInterval(Duration.ofMillis(250))
                        .until(() -> Boolean.parseBoolean(element.getAttribute("disabled")));
            } catch (ConditionTimeoutException e) {
                fail("Element is not disabled");
            }
        }

        /**
         * Wait for web element to be invisible.
         *
         * @param element on which to perform wait
         */
        public void waitForElementToBeInvisible(WebElement element, Integer... timeout) {
            waitForElementToBeDisplayed(element);
            int timeToWait = timeout.length > 0 ? timeout[0] : 10;
            try {
                await()
                        .atMost(Duration.ofSeconds(timeToWait))
                        .pollInterval(Duration.ofMillis(250))
                        .until(() -> !waitForElementToBeDisplayed(element, 0));
            } catch (ConditionTimeoutException e) {
                fail("Element is still displayed");
            }
        }

        /**
         * Wait for web element to be stale.
         *
         * @param element on which to perform wait
         */
        public void waitForElementToBeStale(WebElement element, Integer... timeout) {
            int timeToWait = timeout.length > 0 ? timeout[0] : 10;
            waitForCondition(ExpectedConditions.stalenessOf(element), timeToWait);
        }

        /**
         * Wait for URL to be equals with provided URL.
         *
         * @param url     to perform check
         * @param timeout wait in seconds
         */
        public void waitForUrlToBe(String url, Integer... timeout) {
            int timeToWait = timeout.length > 0 ? timeout[0] : 30;
            waitForCondition(ExpectedConditions.urlToBe(url), timeToWait);
        }

        /**
         * Wait for URL to contains provided URL.
         *
         * @param url     to perform check
         * @param timeout wait in seconds
         * @return boolean if condition was met
         */
        public boolean waitForUrlToContains(String url, Integer... timeout) {
            int timeToWait = timeout.length > 0 ? timeout[0] : 30;
            return waitForCondition(ExpectedConditions.urlContains(url), timeToWait);
        }

        /**
         * Wait for URL to be equals with provided URL using awaitility.
         *
         * @param url     to perform check
         * @param timeout wait in seconds
         */
        public void waitForUrlToBeAwait(String url, Integer... timeout) {
            int timeToWait = timeout.length > 0 ? timeout[0] : 30;
            await()
                    .atMost(timeToWait, SECONDS)
                    .pollInterval(500, MILLISECONDS)
                    .until(() -> url.equals(getCurrentUrl()));
        }

        /**
         * Wait for URL not to be equals with provided URL using awaitility.
         *
         * @param url     to perform check
         * @param timeout wait in seconds
         */
        public void waitForUrlNotToBeAwait(String url, Integer... timeout) {
            int timeToWait = timeout.length > 0 ? timeout[0] : 30;
            await()
                    .atMost(timeToWait, SECONDS)
                    .pollInterval(500, MILLISECONDS)
                    .until(() -> !url.equals(getCurrentUrl()));
        }

        /**
         * Wait for URL to contains provided URL using awaitility.
         *
         * @param url     to perform check
         * @param timeout wait in seconds
         */
        public void waitForUrlToContainsAwait(String url, Integer... timeout) {
            int timeToWait = timeout.length > 0 ? timeout[0] : 30;
            await()
                    .atMost(timeToWait, SECONDS)
                    .pollInterval(500, MILLISECONDS)
                    .until(() -> getCurrentUrl().contains(url));
        }

        /**
         * Wait for URL not to contains provided URL using awaitility.
         *
         * @param url     to perform check
         * @param timeout wait in seconds
         */
        public void waitForUrlNotToContainsAwait(String url, Integer... timeout) {
            int timeToWait = timeout.length > 0 ? timeout[0] : 30;
            await()
                    .atMost(timeToWait, SECONDS)
                    .pollInterval(500, MILLISECONDS)
                    .until(() -> !getCurrentUrl().contains(url));
        }

        /**
         * Click on link, get URL from new tab and close previous.
         *
         * @param element to click on
         * @return URL from new tab
         */
        public String clickOnLinkAndGetLinkUrl(WebElement element) {
            click(element);

            var tabs = new ArrayList<>(driver.getWindowHandles());
            driver.switchTo().window(tabs.get(1));
            String url = driver.getCurrentUrl();
            driver.switchTo().window(tabs.get(1)).close();
            driver.switchTo().window(tabs.get(0));

            return url;
        }

        /**
         * Switch to last opened tab.
         */
        public void switchToLastTab() {
            var tabs = new ArrayList<>(driver.getWindowHandles());
            driver.switchTo().window(tabs.get(tabs.size() - 1));
        }

        /**
         * Move focus to web element.
         *
         * @param element on which focus should be moved
         */
        public void moveFocusToElement(WebElement element) {
            ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", element);
        }

        /**
         * Scroll at the bottom of the page.
         */
        public void scrollToBottomOfPage() {
            waitForPageToFinishWithLoading();
            JavascriptExecutor js = (JavascriptExecutor) driver;
            var lastHeight = ((Number) js.executeScript("return document.body.scrollHeight")).longValue();
            while (true) {
                ((JavascriptExecutor) driver).executeScript("window.scrollTo(0, document.body.scrollHeight);");
                waitForPageToFinishWithLoading();
                var newHeight = ((Number) js.executeScript("return document.body.scrollHeight")).longValue();
                if (newHeight == lastHeight) {
                    break;
                }
                lastHeight = newHeight;
            }
            waitForPageToFinishWithLoading();
        }

        /**
         * Scroll at the top of the page.
         */
        public void scrollToTopOfPage() {
            waitForPageToFinishWithLoading();
            ((JavascriptExecutor) driver).executeScript("window.scrollTo(0, 0);");
            waitForPageToFinishWithLoading();
        }

        /**
         * Wait until document.readyState becomes complete
         */
        public void waitForPageToFinishWithLoading() {
            new WebDriverWait(driver, Duration.ofSeconds(2)).until(wd ->
                    ((JavascriptExecutor) Objects.requireNonNull(wd)).executeScript("return document.readyState").equals("complete"));
        }

        /**
         * Convert web element to By.
         *
         * @param element which should be transformed to By
         * @return By locator
         */
        public By fromWebElementToBy(WebElement element) {
            Object findBy = null;
            try {
                Object proxyOrigin = FieldUtils.readField(element, "h", true);
                Object locator = FieldUtils.readField(proxyOrigin, "locator", true);
                findBy = FieldUtils.readField(locator, "by", true);
            } catch (IllegalAccessException e) {
                System.out.println(e.getMessage());
            }

            return (By) findBy;
        }

        /**
         * Get current URL.
         *
         * @return current tab URL
         */
        public String getCurrentUrl() {
            return driver.getCurrentUrl().trim();
        }

        /**
         * Wait for custom provided condition.
         *
         * @param condition - condition which should be met
         * @param timeout   - timeout wait in seconds
         * @return boolean if condition was met
         */
        public boolean waitForCondition(ExpectedCondition<?> condition, Integer timeout) {
            driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(0));
            try {
                new WebDriverWait(driver, Duration.ofSeconds(timeout)).until(condition);
            } catch (TimeoutException exception) {
                //LOGGER.log(Level.INFO, String.format("Condition is not met: %s. Session: %s.", condition.toString(), ((RemoteWebDriver) driver).getSessionId()));
                driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(timeout));
                return false;
            }
            driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(timeout));
            return true;
        }
}