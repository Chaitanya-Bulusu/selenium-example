package org.selenium.example.test;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.LoadableComponent;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class Test_PageLoad extends LoadableComponent<Test_PageLoad> {
    private final WebDriver driver;

    private final String baseUrl = "https://www.lloydsauctions.in/";

    public Test_PageLoad(WebDriver driver) {
        this.driver = driver;
    }

    @Override
    protected void load() {
        driver.get("https://www.lloydsauctions.in/");
    }

    @Override
    protected void isLoaded() throws Error {
        //driver.get("https://www.lloydsauctions.in/");
        String url = driver.getCurrentUrl();
        if(!this.baseUrl.equals(url))
            throw new Error();
        assertTrue(url.contains("https://www.lloydsauctions.in/"));
    }
}
