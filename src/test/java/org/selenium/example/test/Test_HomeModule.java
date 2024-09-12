package org.selenium.example.test;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class Test_HomeModule {
    private final WebDriver driver;

    private Test_LoginPage testLoginPage;
    @FindBy(xpath = "//div[@class='page_content']")
    private List<WebElement> lstBidItems;

    public Test_HomeModule() {
        driver = TestConfig.testConfigObj().GetDriver(EnumsMetaData.Browsers.FIREFOX);
        PageFactory.initElements(driver, this);

    }

    @BeforeAll
    public void setUp() {
        Test_PageLoad pageLoad = new Test_PageLoad(driver);
        Test_LoginPage testLoginPage = new Test_LoginPage(driver, pageLoad);
        testLoginPage.get();

    }

    @AfterAll()
    public void close() {
        driver.quit();
    }

    @Test
    public void test_placeOrderSuccessfully() {
        List<WebElement> itemList = lstBidItems.get(1).findElement(By.className("auction")).findElements(By.xpath("//div/div"));
       // itemList.get(1).findElement(By.tagName("a")).click();
        assertFalse(itemList.isEmpty());
    }
}
