package org.selenium.example.test;

import org.openqa.selenium.ElementNotInteractableException;
import org.openqa.selenium.PageLoadStrategy;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Wait;

import java.time.Duration;


public final class TestConfig {
    private TestConfig() {
    }

    private static volatile TestConfig testConfig_instance = null;

    public static TestConfig testConfigObj() {
        if (testConfig_instance == null) {
            synchronized (TestConfig.class) {
                if (testConfig_instance == null) {
                    testConfig_instance = new TestConfig();
                }
            }
        }
        return testConfig_instance;
    }

    public WebDriver GetDriver(EnumsMetaData.Browsers browser) {
        WebDriver driver;
        if (browser == EnumsMetaData.Browsers.CHROME) driver = new ChromeDriver(getChromeOptions());
        else if (browser == EnumsMetaData.Browsers.FIREFOX) driver = new FirefoxDriver(getFireFoxOptions());
        else driver = new EdgeDriver(getEdgeOptions());
        return driver;
    }

    public Wait<WebDriver> getWait(WebDriver driver) {
        return new FluentWait<>(driver).withTimeout(Duration.ofSeconds(10)).pollingEvery(Duration.ofMillis(300))
                .ignoring(ElementNotInteractableException.class);

    }

    private ChromeOptions getChromeOptions() {
        ChromeOptions options = new ChromeOptions();
        return options;
    }

    private FirefoxOptions getFireFoxOptions() {
        FirefoxOptions options = new FirefoxOptions();
        return options;
    }

    private EdgeOptions getEdgeOptions() {
        EdgeOptions options = new EdgeOptions();
        return options;
    }


}
