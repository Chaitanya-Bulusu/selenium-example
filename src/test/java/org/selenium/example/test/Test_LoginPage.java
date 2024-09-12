package org.selenium.example.test;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.LoadableComponent;

import static org.junit.jupiter.api.Assertions.assertTrue;


public class Test_LoginPage extends LoadableComponent<Test_LoginPage> {
    private final WebDriver driver;

    private final LoadableComponent<?> testPageLoad;
    @FindBy(id = "HeaderContentPlaceHolder_loginControl_txtEmail")
    private WebElement txtEmail;
    @FindBy(id = "HeaderContentPlaceHolder_loginControl_txtPassword")
    private WebElement txtPassword;
    @FindBy(id = "HeaderContentPlaceHolder_loginControl_btnSignIn")
    private WebElement btnSignIn;

    @FindBy(id = "HeaderContentPlaceHolder_LoginNotice_lblDispName")
    private WebElement lblUser;

    public Test_LoginPage(WebDriver driver, LoadableComponent<?> testPageLoad) {
        this.testPageLoad = testPageLoad;
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    @Override
    protected void load() {

    }

    @Override
    protected void isLoaded() throws Error {
        this.testPageLoad.get();
        this.login();
    }

    private void login() {
        clearAndType(txtEmail, "chaitanya97@gmail.com");
        clearAndType(txtPassword, "Chaitu@3");
        btnSignIn.click();
        TestConfig.testConfigObj().getWait(driver).until(d -> lblUser.isDisplayed());
        assertTrue(lblUser.getText().equals("Chaitanya Bulusu"));
    }

    private void clearAndType(WebElement element, String text) {
        element.clear();
        element.sendKeys(text);
    }
}
