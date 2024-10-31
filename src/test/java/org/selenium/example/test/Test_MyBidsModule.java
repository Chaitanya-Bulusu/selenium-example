package org.selenium.example.test;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.annotations.AfterClass;
import org.testng.annotations.Test;

import java.util.List;

import static org.testng.Assert.assertFalse;
import static org.testng.Assert.assertTrue;

public class Test_MyBidsModule extends Test_LoginPage {
    protected List<WebElement> navBar;

    private By myBidsHeaderSpanElement = By.id("MainContentPlaceHolder_MainContentPlaceHolder_MainContentPlaceHolder_MyBidsListControl_lblHeading");

    @AfterClass()
    public void close() {
        driver.manage().deleteAllCookies();
        driver.close();
        driver.quit();
    }

    @Test(priority = 2)
    public void when_clicked_myBids_should_loadMyBids() {
        navBar = driver.findElements(navBarPath);
        WebElement navAuctions = navBar.get(2);
        testConfig.moveToElement(driver, navAuctions);
        driver.findElement(By.cssSelector(".navbar-nav .menu-item:nth-child(3) .menu-item:nth-child(7) > a")).click();
        testConfig.getWait(driver).until(t -> t.getCurrentUrl().contains("/MyBids.aspx"));
        testConfig.getWait(driver).until(t -> t.findElement(myBidsHeaderSpanElement).isDisplayed());
        WebElement element = driver.findElement(myBidsHeaderSpanElement);
        assertTrue(element.getText().equals("My Bids"));
    }

    @Test(priority = 3)
    public void when_clicked_myBids_should_presentMyBids() {
        By myBidsList = By.xpath("//div[@class='list-wrapper']/div[@class='list_item lot_list_item']");
        testConfig.getWait(driver).until(t -> (t.findElements(myBidsList).size() > 0));
        assertFalse(driver.findElements(myBidsList).isEmpty());
    }
}
