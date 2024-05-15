package webdriver;

import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import webdriver.common.BaseDriver;
import webdriver.common.JsExecutor;
import webdriver.common.Utils;

public class Topic_12_Apply_JSExecutor extends BaseDriver {
    /*
    * Selenium Javascript (support automation on Javascript)
    * Javascript Executor (Execute js statement on other
    * */
    public JsExecutor jsExecutor;

    @BeforeClass
    @Override
    public void beforeClass() {
        super.beforeClass();
        jsExecutor = new JsExecutor(driver);
    }

    @Test
    private void TC_01() {
        jsExecutor.navigateToUrlByJS("https://live.techpanda.org/");
        Utils.sleepInSecond(5);
        String domain = (String) jsExecutor.executeForBrowser("return document.domain;");
        String url = (String) jsExecutor.executeForBrowser("return document.URL;");
        System.out.println(domain + " " + url);

        jsExecutor.clickToElementByJS("//a[text()='Mobile']");
        jsExecutor.clickToElementByJS("//a[text()='Samsung Galaxy']/parent::h2/following-sibling::div[@class='actions']/button");
        jsExecutor.clickToElementByJS("//a[text()='Customer Service']");
        jsExecutor.scrollToElementOnDown("//input[@id='newsletter']");
        Utils.sleepInSecond(5);
        jsExecutor.sendkeyToElementByJS("//input[@id='newsletter']", "abc@gmail.com");
        Utils.sleepInSecond(5);
    }

    @Test
    private void TC_02() {
        driver.get("https://warranty.rode.com/");
        Utils.sleepInSecond(5);
        By registerBtn = By.xpath("//a[contains(text(), 'Create an Account')]");
        driver.findElement(registerBtn).click();
        Utils.sleepInSecond(3);
        jsExecutor.getElement("//input[@id='name']").sendKeys("automation");
        Utils.sleepInSecond(3);
        jsExecutor.getElement("//input[@id='email']").sendKeys("automation");
        Utils.sleepInSecond(3);
        Assert.assertEquals(jsExecutor.getElementValidationMessage("//input[@id='password']"), "Please fill out this field.");
        jsExecutor.getElement("//input[@id='password']").sendKeys("automation");
    }
}
