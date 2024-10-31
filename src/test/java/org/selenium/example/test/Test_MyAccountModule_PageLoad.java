package org.selenium.example.test;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.annotations.AfterClass;
import org.testng.annotations.Test;

import java.util.List;

import static org.testng.Assert.assertTrue;

public class Test_MyAccountModule_PageLoad extends Test_LoginPage {

    protected List<WebElement> navBar;

    By noticeBoardHeaderElement = By.id("MainContentPlaceHolder_MainContentPlaceHolder_MainContentPlaceHolder_NoticeBoardCtrl_lblTitle");
    By customerDetailsHeaderSpanElement = By.id("MainContentPlaceHolder_MainContentPlaceHolder_MainContentPlaceHolder_CustomerDetailsCtrl_lblTitle");
    By passwordChangeHeaderSpanElement = By.id("MainContentPlaceHolder_MainContentPlaceHolder_MainContentPlaceHolder_ChangePasswordCtrl_lblTitle");
    By preferencesHeaderSpanElement = By.id("MainContentPlaceHolder_MainContentPlaceHolder_MainContentPlaceHolder_NotificationPreferencesCtrl_lblTitle");


    @AfterClass()
    public void close() {
        driver.manage().deleteAllCookies();
        driver.close();
        driver.quit();
    }

    @Test(priority = 2)
    public void when_clicked_myAccountNoticeBoard_should_loadNoticeBoardPage() {
        navBar = driver.findElements(navBarPath);
        WebElement navAuctions = navBar.get(2);
        testConfig.moveToElement(driver, navAuctions);
        driver.findElement(By.cssSelector(".navbar-nav .menu-item:nth-child(3) .menu-item:nth-child(1) > a")).click();
        testConfig.getWait(driver).until(t -> t.getCurrentUrl().contains("/NoticeBoard.aspx"));
        testConfig.getWait(driver).until(t -> t.findElement(noticeBoardHeaderElement).isDisplayed());
        WebElement element = driver.findElement(noticeBoardHeaderElement);
        assertTrue(element.getText().equals("NOTICE BOARD"));

    }

    @Test(priority = 2)
    public void when_clicked_myDetails_should_loadMyDetails() {
        navBar = driver.findElements(navBarPath);
        WebElement navAuctions = navBar.get(2);
        testConfig.moveToElement(driver, navAuctions);
        driver.findElement(By.cssSelector(".navbar-nav .menu-item:nth-child(3) .menu-item:nth-child(2) > a")).click();
        testConfig.getWait(driver).until(t -> t.getCurrentUrl().contains("/CustomerDetails.aspx"));
        testConfig.getWait(driver).until(t -> t.findElement(customerDetailsHeaderSpanElement).isDisplayed());
        WebElement element = driver.findElement(customerDetailsHeaderSpanElement);
        assertTrue(element.getText().equals("CUSTOMER DETAILS"));

    }

    @Test(priority = 2)
    public void when_clicked_changePassword_should_loadChangePassword() {
        navBar = driver.findElements(navBarPath);
        WebElement navAuctions = navBar.get(2);
        testConfig.moveToElement(driver, navAuctions);
        driver.findElement(By.cssSelector(".navbar-nav .menu-item:nth-child(3) .menu-item:nth-child(3) > a")).click();
        testConfig.getWait(driver).until(t -> t.getCurrentUrl().contains("/ChangePassword.aspx"));
        testConfig.getWait(driver).until(t -> t.findElement(passwordChangeHeaderSpanElement).isDisplayed());
        WebElement element = driver.findElement(passwordChangeHeaderSpanElement);
        assertTrue(element.getText().equals("CHANGE PASSWORD"));

    }

    @Test(priority = 2)
    public void when_clicked_preferences_should_loadPreferences() {
        navBar = driver.findElements(navBarPath);
        WebElement navAuctions = navBar.get(2);
        testConfig.moveToElement(driver, navAuctions);
        driver.findElement(By.cssSelector(".navbar-nav .menu-item:nth-child(3) .menu-item:nth-child(4) > a")).click();
        testConfig.getWait(driver).until(t -> t.getCurrentUrl().contains("/Preferences.aspx"));
        testConfig.getWait(driver).until(t -> t.findElement(preferencesHeaderSpanElement).isDisplayed());
        WebElement element = driver.findElement(preferencesHeaderSpanElement);
        assertTrue(element.getText().equals("PREFERENCES"));

    }
}