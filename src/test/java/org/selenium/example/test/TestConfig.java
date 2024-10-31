package org.selenium.example.test;

import org.openqa.selenium.ElementNotInteractableException;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Wait;

import java.io.FileInputStream;
import java.io.IOException;
import java.time.Duration;
import java.util.Properties;


public final class TestConfig {
    private final EnumsMetaData.Browsers browser;
    private final String baseURL;
    private final String email;
    private final String password;

    private static volatile TestConfig testConfig_instance = null;

    public EnumsMetaData.Browsers getBrowser() {
        return this.browser;
    }

    public String getBaseURL() {
        return baseURL;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }


    private TestConfig() {
        try {
            String path = Thread.currentThread().getContextClassLoader().getResource("application.properties").getPath();
            Properties prop = new Properties();
            prop.load(new FileInputStream(path));
            this.browser = EnumsMetaData.Browsers.valueOf(prop.getProperty("browser", "EDGE"));
            this.baseURL = prop.getProperty("baseURL");
            this.email = prop.getProperty("email");
            this.password = prop.getProperty("password");

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }


    public WebDriver GetDriver(EnumsMetaData.Browsers browser) {
        WebDriver driver;
        if (browser == EnumsMetaData.Browsers.CHROME) driver = new ChromeDriver(getChromeOptions());
        else if (browser == EnumsMetaData.Browsers.FIREFOX) driver = new FirefoxDriver(getFireFoxOptions());
        else driver = new EdgeDriver(getEdgeOptions());
        driver.manage().deleteAllCookies();
        driver.manage().window().maximize();
        return driver;
    }

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

    public Wait<WebDriver> getWait(WebDriver driver) {
        return new FluentWait<>(driver).withTimeout(Duration.ofSeconds(10)).pollingEvery(Duration.ofMillis(300))
                .ignoring(ElementNotInteractableException.class);

    }

    public void scrollBy(WebDriver driver, int scrollHeight) {
        JavascriptExecutor jse = (JavascriptExecutor) driver;
        jse.executeScript("window.scrollBy(0," + scrollHeight + ")");
    }

    public void scrollToElement(WebDriver driver, WebElement element) {
        new Actions(driver).scrollToElement(element).perform();
    }

    public void moveToElement(WebDriver driver, WebElement element) {
        new Actions(driver).moveToElement(element).perform();
    }

    public void moveToLocation(WebDriver driver, int x, int y){
        new Actions(driver).moveToLocation(x,y).perform();
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
