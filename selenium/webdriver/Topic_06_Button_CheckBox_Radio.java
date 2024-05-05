package webdriver;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import webdriver.common.BaseDriver;
import webdriver.common.Utils;

import java.util.ArrayList;
import java.util.List;

public class Topic_06_Button_CheckBox_Radio extends BaseDriver {
    @BeforeClass
    @Override
    public void beforeClass() {
        super.beforeClass();
        driver.get("https://fahasa.com/customer/account/create");
    }

    @Test
    private void  TC_01_Verify_Button() {
        driver.findElement(By.cssSelector("li.popup-login-tab-login")).click();
        Utils.sleepInSecond(2);
        WebElement loginBtn = driver.findElement(By.cssSelector("button.fhs-btn-login"));
        if (!loginBtn.isEnabled()) {
            String bgColor = loginBtn.getCssValue("background-image");
            Assert.assertTrue(bgColor.contains("rgb(224, 224, 224) 0%"));
        }
        Assert.assertFalse(loginBtn.isEnabled());
    }

    @Test
    private void  TC_01_CheckBox() {
        // isSelected just only for checkbox default (input field)
        driver.get("https://automationfc.github.io/multiple-fields/");
        // check box
        driver.findElement(By.xpath("//label[contains(text(),'Diabetes')]/preceding-sibling::input")).click();
        // Radio
        driver.findElement(By.xpath("//label[contains(text(),\"I don't drink\")]/preceding-sibling::input")).click();
        Utils.sleepInSecond(2);
    }

    @Test
    private void  TC_02_CheckBox_Multiple() {
        driver.get("https://automationfc.github.io/multiple-fields/");
        List<WebElement> items = driver.findElements(By.cssSelector("input.form-checkbox"));
        for (WebElement item: items) {
            item.click();
            Utils.sleepInSecond(1);
        }
        Utils.sleepInSecond(2);
    }

    @Test
    private void  TC_02_CheckBox_Custom() {
        driver.get("https://mui.com/material-ui/react-checkbox/");
        // Lấy ra thằng phần tử che bên trên input để thao tác
        String labelItem = "//span[parent::div and descendant::input]";
        driver.findElement(By.xpath(labelItem)).click();
        // Sau khi thao tác xong thì input sẽ thay đổi trạng thái selected
        String inputItemXpath = "//div//span/input[following-sibling::span]";
        WebElement inputItem = driver.findElement(By.xpath(inputItemXpath));
        Assert.assertFalse(inputItem.isSelected());
        Utils.sleepInSecond(2);
        // Click via Javascript
        // Condition: input must have size more than 0
        jsExcutor.executeScript("arguments[0].click()", inputItem);
        Utils.sleepInSecond(2);
        Assert.assertTrue(inputItem.isSelected());
        Utils.sleepInSecond(5);
    }

    @Test
    private void  TC_02_Radio_Custom() {
        driver.get("https://docs.google.com/forms/d/1_J9S-p-H5_PTZVlKC0GApTJnFE0CM5yleJY7B28VKBo/edit");
        By radio = By.cssSelector("div[aria-label='XS']");
        By checkbox = By.cssSelector("div[aria-label='Option 1']");

        // Click to Radio
        jsExcutor.executeScript("arguments[0].click()", driver.findElement(radio));
        Utils.sleepInSecond(2);
        // Click to check box
        jsExcutor.executeScript("arguments[0].click()", driver.findElement(checkbox));

        Assert.assertEquals(driver.findElement(radio).getAttribute("aria-checked"), "true");
    }
}
