package webdriver.pom.pageObject;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import webdriver.common.Utils;
import webdriver.pom.pageUI.HomePageUI;

public class HomePageObject {
    private final WebDriver driver;

    public HomePageObject(WebDriver driver) {
        this.driver = driver;
    }

    public void clickToRegisterLink() {
        Utils.waitForElementDisplay(driver,By.xpath(HomePageUI.EMAIL_TEXTBOX), 10);
        // Click
        Utils.clickToElement(driver, By.xpath(HomePageUI.EMAIL_TEXTBOX));
    }
}
