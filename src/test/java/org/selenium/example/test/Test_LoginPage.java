package org.selenium.example.test;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import static org.testng.Assert.assertTrue;


public class Test_LoginPage extends Test_PageLoad {

    private final String email;
    private final String password;
    @FindBy(id = "HeaderContentPlaceHolder_loginControl_txtEmail")
    private WebElement txtEmail;
    @FindBy(id = "HeaderContentPlaceHolder_loginControl_txtPassword")
    private WebElement txtPassword;
    @FindBy(id = "HeaderContentPlaceHolder_loginControl_btnSignIn")
    private WebElement btnSignIn;

    @FindBy(id = "HeaderContentPlaceHolder_LoginNotice_lblDispName")
    private WebElement lblUser;


    public Test_LoginPage() {
        this.email = testConfig.getEmail();
        this.password = testConfig.getPassword();

    }

    @BeforeClass
    public void setUp() {
        super.isLoaded();
        PageFactory.initElements(driver, this);
    }

    @Test(priority = 1)
    public void login() {
        clearAndType(txtEmail, email);
        clearAndType(txtPassword, password);
        btnSignIn.click();
        TestConfig.testConfigObj().getWait(driver).until(d -> lblUser.isDisplayed());

        assertTrue(lblUser.getText().equals("Chaitanya Bulusu"));

    }

    private void clearAndType(WebElement element, String text) {
        element.clear();
        element.sendKeys(text);
    }
}
