package webdriver;

import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.Test;
import webdriver.common.BaseDriver;
import webdriver.common.Utils;

public class Topic_10_iFrame extends BaseDriver {
    // Frame: nhúng 1 trang có cùng domain
    // iFrame: nhúng 1 trang khác ko cùng domain
    @Test
    private void TC_01() {
        driver.get("https://skills.kynaenglish.vn/");

        Assert.assertFalse(driver.findElement(By.cssSelector("div.fanpage iframe")).isDisplayed());
        // Switch frame via index
//        driver.switchTo().frame(0);
        // Switch via id
//        driver.switchTo().frame("cs_chat_iframe");

        // Switch via element
        driver.switchTo().frame(driver.findElement(By.cssSelector("div.fanpage iframe")));

        Utils.sleepInSecond(2);
        System.out.println(driver.findElement(By.xpath("//a[text()='Kyna.vn']/parent::div/following-sibling::div")).getText());


    }
}
