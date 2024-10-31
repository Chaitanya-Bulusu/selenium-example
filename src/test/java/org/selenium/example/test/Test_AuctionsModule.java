package org.selenium.example.test;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.testng.annotations.AfterClass;
import org.testng.annotations.Test;

import java.text.SimpleDateFormat;
import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.Date;
import java.util.List;

import static org.testng.Assert.assertFalse;
import static org.testng.Assert.assertTrue;

public class
Test_AuctionsModule extends Test_LoginPage {
    protected List<WebElement> navBar;

    @AfterClass()
    public void close() {
        driver.manage().deleteAllCookies();
        driver.close();
        driver.quit();
    }

    @Test(priority = 2)
    public void when_Clicked_Auctions_Should_Load_Auctions() {
        navBar = driver.findElements(navBarPath);
        WebElement navAuctions = navBar.get(1);
        // navBar.findElement(By.xpath("//li/a[@href='/AuctionList.aspx']"));
        navAuctions.click();
        assertTrue(driver.getCurrentUrl().contains("/AuctionList.aspx"));
        By xpathAuctions = By.xpath("//div[@class='auction list responsive ']/div");
        testConfig.getWait(driver).until(t -> (t.findElements(xpathAuctions)).size() > 0);
        assertFalse(driver.findElements(xpathAuctions).isEmpty());
    }

    @Test(priority = 3)
    public void when_Clicked_Auctions_Should_Load_RecentAuctions() {
        navBar = driver.findElements(navBarPath);
        WebElement navAuctions = navBar.get(1);
        Actions mouseOverAction = new Actions(driver);
        mouseOverAction.moveToElement(navAuctions).build().perform();
        driver.findElement(By.cssSelector(".navbar-nav .menu-item:nth-child(2) .menu-item:nth-child(2) > a")).click();
        testConfig.getWait(driver).until(t -> t.getCurrentUrl().contains("/AuctionList.aspx?altype=1"));
        By div_RecentAuctions_xpath = By.xpath("//div[@class='auction list responsive ']/div[contains(@class,'auction_list_item')]/a/div[@class='auct_date']");
        testConfig.getWait(driver).until(t -> (t.findElements(div_RecentAuctions_xpath)).size() > 0);
        List<WebElement> div_RecentAuctions = driver.findElements(div_RecentAuctions_xpath);
        List<WebElement> element =  div_RecentAuctions.stream().filter(t -> t.getText().toUpperCase().contains(getLocalDateTOString())).toList();
        assertFalse(element.isEmpty());

    }

    private String getLocalDateTOString() {
        Instant yesterday = Instant.now().minus(1, ChronoUnit.DAYS);
        Date dt = Date.from(yesterday);
        SimpleDateFormat formatter = new SimpleDateFormat("dd-MMM-yyyy");
        return formatter.format(dt).toUpperCase();
    }

}
