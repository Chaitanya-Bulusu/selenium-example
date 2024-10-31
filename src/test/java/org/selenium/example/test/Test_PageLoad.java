package org.selenium.example.test;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.testng.annotations.Test;

import static org.testng.Assert.assertTrue;


public class Test_PageLoad extends TestsBaseClass {
    @FindBy(id = "FooterBrandBannerContentPlaceHolder_CookieConsentBox_acceptButton")
    private WebElement btn_FooterBannerAccept;

    @Test(priority = 0)
    public void isLoaded() throws Error {
        driver.get(baseUrl);

        String url = driver.getCurrentUrl();
        if (!this.baseUrl.equals(url))
            throw new Error();
        testConfig.getWait(driver).until(t ->
                t.findElement(By.id("FooterBrandBannerContentPlaceHolder_CookieConsentBox_acceptButton")));
        testConfig.scrollBy(driver, 100);
        if (btn_FooterBannerAccept != null && btn_FooterBannerAccept.isDisplayed()) {
            btn_FooterBannerAccept.click();
        }
        assertTrue(url.contains(baseUrl));
    }


}
