package com.rezonmedia.mobile.util;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public final class BaseCore {

    private final RemoteWebDriver driver;

    private final Configuration configuration;

    public BaseCore(final Configuration configuration) {
        this.configuration = configuration;
        WebDriverManager.chromedriver().setup(); // setup chromedriver

        driver = new ChromeDriver();
    }

    public RemoteWebDriver getDriver() {
        return driver;
    }

    /**
     * The method waits for WebElement and returns it if the element is
     * present on the page, no matter if it is visible or invisible.
     *
     * @param by By locator
     * @return WebElement that is present on the page, no matter if it is visible or invisible.
     */
    public boolean waitForElement(final By by) {
        Wait<WebDriver> wait = new WebDriverWait(driver, Duration.ofSeconds(configuration.getWebDriverElementWaitTimeoutInSeconds()));
        return wait.until(d -> driver.findElement(by).isDisplayed());
    }

    /**
     * Wait until element is clickable.
     *
     * @param by By locator
     */
    public WebElement waitUntilElementToBeClickable(final By by) {
        return getWebDriverWaitByDuration(Duration.ofSeconds(configuration.getWebDriverElementWaitTimeoutInSeconds())).until(
                ExpectedConditions.elementToBeClickable(by));
    }

    /**
     * Wraps the WebDriverWait instantiation.
     *
     * @param duration
     * @return WebDriverWait instance
     */
    public WebDriverWait getWebDriverWaitByDuration(final Duration duration) {
        return new WebDriverWait(driver, duration);
    }

}
