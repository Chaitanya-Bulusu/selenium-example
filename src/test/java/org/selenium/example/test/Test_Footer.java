package org.selenium.example.test;


import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import static org.testng.Assert.assertTrue;

public class Test_Footer extends Test_PageLoad {

    @FindBy(xpath = "//a[@href='/TermsAndConditions.aspx']")
    private WebElement href_BidderTermsAndConditions;

    @FindBy(xpath = "//a[@href='/VendorTermsAndConditions.aspx']")
    private WebElement href_VendorTermsAndConditions;

    @FindBy(xpath = "//a[@href='/PrivacyStatement.aspx']")
    private WebElement href_PrivacyStatement;

    @FindBy(xpath = "//a[@href='/Review.aspx']")
    private WebElement href_Review;

    @FindBy(xpath = "//a[@href='/Contact.aspx']")
    private WebElement href_Contact;

    @FindBy(xpath = "//a[@href='/Help.aspx']")
    private WebElement href_Help;

    @BeforeClass
    public void setUp() {
        super.isLoaded();
        PageFactory.initElements(driver, this);
        WebElement footer = driver.findElement(By.tagName("footer"));
        testConfig.getWait(driver).until(t -> footer.isDisplayed());
        testConfig.scrollToElement(driver, footer);

    }

    @AfterClass()
    public void close() {
        driver.manage().deleteAllCookies();
        driver.close();
        driver.quit();
    }

    @Test(priority = 1)
    public void should_navigate_whenClicked_bidder8TermsAndConditionsLink() throws InterruptedException {
        href_BidderTermsAndConditions.click();
        testConfig.getWait(driver).until(t -> t.getCurrentUrl().contains("/TermsAndConditions.aspx"));
        WebElement lbl_UserAgreement = driver.findElement(By.xpath("//div[contains(@class,'TermsAndConditions')]/p/span/span"));
        assertTrue(lbl_UserAgreement.getText().contains("USERS AGREEMENT - Terms & Conditions"));
        driver.navigate().back();
    }

    @Test(priority = 2)
    public void should_navigate_whenClicked_VendorTermsAndConditionsLink() throws InterruptedException {
        href_VendorTermsAndConditions.click();
        testConfig.getWait(driver).until(t -> t.getCurrentUrl().contains("/VendorTermsAndConditions.aspx"));
        WebElement lbl_UserAgreement = driver.findElement(By.xpath("//div[contains(@class,'TermsAndConditions')]/p/span/span"));
        assertTrue(lbl_UserAgreement.getText().contains("USERS AGREEMENT - Vendor Terms & Conditions"));
        driver.navigate().back();
    }

    @Test(priority = 3)
    public void should_navigate_whenClicked_PrivacyStatementLink() throws InterruptedException {
        href_PrivacyStatement.click();
        testConfig.getWait(driver).until(t -> t.getCurrentUrl().contains("/PrivacyStatement.aspx"));
        WebElement h4_PrivacyStatement = driver.findElement(By.xpath("//div[contains(@class,'Privacy')]/h4"));
        assertTrue(h4_PrivacyStatement.getText().contains("Privacy Statement"));
        driver.navigate().back();
    }

    @Test(priority = 4)
    public void should_navigate_whenClicked_LeaveReviewLink() throws InterruptedException {
        href_Review.click();
        testConfig.getWait(driver).until(t -> t.getCurrentUrl().contains("/Review.aspx"));
        WebElement h1_Review = driver.findElement(By.xpath("//h1[contains(text(),'Leave Your Review')]"));
        assertTrue(h1_Review.isDisplayed());
        driver.navigate().back();
    }

    @Test(priority = 5)
    public void should_navigate_whenClicked_ContactUsLink() throws InterruptedException {
        href_Contact.click();
        testConfig.getWait(driver).until(t -> t.getCurrentUrl().contains("/Contact.aspx"));
        WebElement h3_ContactUs = driver.findElement(By.xpath("//h3[contains(text(),'Office Locations')]"));
        assertTrue(h3_ContactUs.isDisplayed());
        driver.navigate().back();
    }

    @Test(priority = 6)
    public void should_navigate_whenClicked_HelpLink() throws InterruptedException {
        href_Help.click();
        testConfig.getWait(driver).until(t -> t.getCurrentUrl().contains("/Help.aspx"));
        WebElement div_TitleCollection = driver.findElement(By.id("title-collection"));
        assertTrue(div_TitleCollection.isDisplayed());
        driver.navigate().back();
    }


}
