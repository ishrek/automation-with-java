package webdriver;

import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.Select;
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
        By iframe = By.cssSelector("div.fanpage iframe");
        Assert.assertFalse(driver.findElement(iframe).isDisplayed());
        // Case1: Switch frame via index
//        driver.switchTo().frame(0);
        // Case2: Switch via id
//        driver.switchTo().frame("cs_chat_iframe");

        // Case3: Switch via element
        driver.switchTo().frame(driver.findElement(iframe));

        Utils.sleepInSecond(2);
        System.out.println(driver.findElement(By.xpath("//a[text()='Kyna.vn']/parent::div/following-sibling::div")).getText());

        //Click to chat iframe. thì phải quay ve parent của iframe
        driver.switchTo().defaultContent();
        Utils.sleepInSecond(1);
        By chatIframe = By.cssSelector("div#cs-live-chat iframe#cs_chat_iframe");
        driver.switchTo().frame(driver.findElement(chatIframe));
        Utils.sleepInSecond(2);

        driver.findElement(By.cssSelector("div.button_bar")).click();
        Utils.sleepInSecond(3);
        // Input chat
        Utils.sendHumanKeys(driver.findElement(By.cssSelector("input.input_name")), "Kai Havertz");
        Utils.sendHumanKeys(driver.findElement(By.cssSelector("input.input_phone")), "912123123");

        new Select(driver.findElement(By.cssSelector("select#serviceSelect"))).selectByVisibleText("TƯ VẤN TUYỂN SINH");
        Utils.sendHumanKeys(driver.findElement(By.name("message")), "Xin chào tôi muốn tư vấn");
        Utils.sleepInSecond(3);
        // Close chat
        driver.switchTo().defaultContent();

//        Utils.sendHumanKeys(driver.findElement(By.id("live-search-bar")), "Excel");
//        driver.findElement(By.cssSelector("button.search-button")).click();
//        Utils.sleepInSecond(3);
    }
}
