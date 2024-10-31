package org.selenium.example.test;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public abstract class TestsBaseClass {
    protected static TestConfig testConfig = TestConfig.testConfigObj();
    protected By navBarPath = By.xpath("//ul[@class='nav navbar-nav']/li");
    protected WebDriver driver;
    protected String baseUrl;

    public TestsBaseClass() {
        this.driver = testConfig.GetDriver(testConfig.getBrowser());
        this.baseUrl = testConfig.getBaseURL();
    }

}
