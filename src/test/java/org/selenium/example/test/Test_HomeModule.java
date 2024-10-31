package org.selenium.example.test;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.annotations.AfterClass;
import org.testng.annotations.Test;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

import static org.testng.Assert.assertTrue;


public class Test_HomeModule extends Test_LoginPage {
    @FindBy(xpath = "//div[contains(@class,'auction Homelist')]/div[contains(@class,'list_item auction_list_item')]")
    private List<WebElement> lstBidItems;


    @AfterClass()
    public void close() {
        driver.manage().deleteAllCookies();
        driver.close();
        driver.quit();
    }

    @Test(priority = 2)
    public void when_clicked_lotItm_then_navigateToLotDetailsPage() throws MalformedURLException {
        PageFactory.initElements(driver, this);
        Random random = new Random();
        int index = random.nextInt(0, lstBidItems.size()-1);
        lstBidItems.get(index).click();
        testConfig.getWait(driver).until(t -> t.getCurrentUrl().contains("/AuctionDetails.aspx"));
        URL url = new URL(driver.getCurrentUrl());
        testConfig.getWait(driver).until(t -> t.findElements(By.xpath("//a[contains(@href, 'AuctionLots.aspx')]")));
        String id = Arrays.stream(url.getQuery().split("=")).toList().get(1);
        WebElement href_ActionLots = driver.findElement(By.xpath("//a[contains(@href, 'AuctionLots.aspx')]"));
        assertTrue(href_ActionLots.getAttribute("href").contains(id));

        navigateToLotDetailsPage(href_ActionLots);
        assertTrue(driver.findElement(By.className("bidding_centre")).isDisplayed());
        editBidDetailsForm();
    }

    private void navigateToLotDetailsPage(WebElement href_ActionLots) {
        href_ActionLots.click();
        List<WebElement> div_LotItems = driver.findElements(By.xpath("//div[contains(@class, 'lot_list')]/div[contains(@class, 'lot_list_item')]"));
        WebElement div_LotItem = div_LotItems.get(0);
        div_LotItem.findElement(By.tagName("a")).click();
    }

    private void editBidDetailsForm() {
        WebElement span_CurrentBidValue = driver.findElement(By.className("current_bid_value"));
        String startingBid = span_CurrentBidValue.getText();
        Double startingBidAmt = EnumsMetaData.convertAmountTONumber(startingBid);
        Double bidRaiseAmt = startingBidAmt + 5;

        WebElement text_MaxBidAmt = driver
                .findElement(By.xpath("//div[contains(@class,'max_amt')]/div[@class='value']/div/span[contains(@id,'txtMaxBid_wrapper')]/input[contains(@class, 'bid_amount')]"));
        text_MaxBidAmt.click();
        text_MaxBidAmt.clear();
        text_MaxBidAmt.sendKeys(bidRaiseAmt.toString());
        text_MaxBidAmt.submit();
        WebElement btn_BidUp = driver.findElement(By.xpath("//a[contains(@id,'lnkBidNow')]"));
        btn_BidUp.click();
    }
}
