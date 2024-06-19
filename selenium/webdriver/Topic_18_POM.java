package webdriver;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import webdriver.common.BaseDriver;
import webdriver.pom.pageObject.HomePageObject;
import webdriver.pom.pageObject.RegisterPageObject;

import java.util.concurrent.TimeUnit;

public class Topic_18_POM extends BaseDriver {
    HomePageObject homePage;
    RegisterPageObject registerPage;

    @BeforeClass
    @Override
    public void beforeClass() {
        super.beforeClass();
        driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
        driver.get("https://demo.nopcommerce.com/");
        homePage = new HomePageObject(driver);
        registerPage = new RegisterPageObject(driver);
    }

    @Test()
    void TC_01_Register_Empty_Data() {
        System.out.println("Home Page - Step 01 : Click register link");
        homePage.clickToRegisterLink();
        System.out.println("Register Page - Step 02 : Click register button");
        registerPage.clickToRegisterButton();
        System.out.println("Register Page - Step 03 : Verify error message displayed");
        String messageAtFirstName = registerPage.getErrorMessageAtFirstNameTextBox();
        Assert.assertEquals(messageAtFirstName, "First name is required.");
    }


    @Test()
    void TC_02_Register_Invalid_Email() {
//        By locator = By.xpath("//a[@class='ico-register']");
//        Utils.waitForElementDisplay(driver, locator, 10);
//        Utils.clickToElement(driver, locator);
        homePage.clickToRegisterLink();
        System.out.println("Register Page - Step 02 : Input fields");
        registerPage.inputToFirstName("Hello");
        registerPage.inputToLastName("World");
        registerPage.inputToEmail("abc@gmail.com");
        registerPage.inputToPassword("123456");
        registerPage.inputToConfirmPassword("123456");

        registerPage.clickToRegisterButton();
    }

    @Test()
    void TC_02_Register_Success() {
//        By locator = By.xpath("//a[@class='ico-register']");
//        Utils.waitForElementDisplay(driver, locator, 10);
//        Utils.clickToElement(driver, locator);
        homePage.clickToRegisterLink();
        System.out.println("Register Page - Step 02 : Input fields");
        registerPage.inputToFirstName("Hello");
        registerPage.inputToLastName("World");
        registerPage.inputToEmail("abc3@gmail.com");
        registerPage.inputToPassword("123456");
        registerPage.inputToConfirmPassword("123456");

        registerPage.clickToRegisterButton();

        Assert.assertEquals(registerPage.getRegisterSuccessMessage(), "Your registration completed");

        registerPage.clickToLogoutLink();
    }
}
