package webdriver.pom.pageObject;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import webdriver.common.Utils;
import webdriver.pom.pageUI.HomePageUI;
import webdriver.pom.pageUI.RegisterPageUI;

public class RegisterPageObject {
    private final WebDriver driver;

    public RegisterPageObject(WebDriver driver) {
        this.driver = driver;
    }

    public void clickToRegisterButton() {
        Utils.waitForElementDisplay(driver, By.xpath(RegisterPageUI.REGISTER_BUTTON), 10);
        Utils.clickToElement(driver, By.xpath(RegisterPageUI.REGISTER_BUTTON));
    }

    public String getErrorMessageAtFirstNameTextBox() {
        Utils.waitForElementDisplay(driver, By.xpath(RegisterPageUI.FirstName_Error), 10);
        return driver.findElement(By.xpath(RegisterPageUI.FirstName_Error)).getText();
    }

    public void inputToFirstName(String hello) {
        Utils.waitForElementDisplay(driver, By.xpath(RegisterPageUI.FIRSTNAME_TEXTBOX), 10);
        Utils.sendHumanKeys(driver.findElement(By.xpath(RegisterPageUI.FIRSTNAME_TEXTBOX)), hello);
    }

    public void inputToLastName(String world) {
        Utils.waitForElementDisplay(driver, By.xpath(RegisterPageUI.LASTNAME_TEXTBOX), 10);
        Utils.sendHumanKeys(driver.findElement(By.xpath(RegisterPageUI.LASTNAME_TEXTBOX)), world);
    }

    public void inputToEmail(String mail) {
        Utils.waitForElementDisplay(driver, By.xpath(RegisterPageUI.EMAIL_TEXTBOX), 10);
        Utils.sendHumanKeys(driver.findElement(By.xpath(RegisterPageUI.EMAIL_TEXTBOX)), mail);
    }

    public void inputToPassword(String pass) {
        Utils.waitForElementDisplay(driver, By.xpath(RegisterPageUI.PASSWORD_TEXTBOX), 10);
        Utils.sendHumanKeys(driver.findElement(By.xpath(RegisterPageUI.PASSWORD_TEXTBOX)), pass);
    }

    public void inputToConfirmPassword(String confirmPass) {
        Utils.waitForElementDisplay(driver, By.xpath(RegisterPageUI.CONFIRM_PASSWORD_TEXTBOX), 10);
        Utils.sendHumanKeys(driver.findElement(By.xpath(RegisterPageUI.CONFIRM_PASSWORD_TEXTBOX)), confirmPass);
    }

    public String getRegisterSuccessMessage() {
        Utils.waitForElementDisplay(driver, By.xpath(RegisterPageUI.Register_success_message), 10);
        return driver.findElement(By.xpath(RegisterPageUI.Register_success_message)).getText();
    }

    public void clickToLogoutLink() {
        Utils.waitForElementDisplay(driver, By.xpath(RegisterPageUI.LOGOUT_LINK), 10);
        Utils.clickToElement(driver, By.xpath(RegisterPageUI.LOGOUT_LINK));
    }
}
