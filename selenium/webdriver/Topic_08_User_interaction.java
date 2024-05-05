package webdriver;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import webdriver.common.BaseDriver;
import webdriver.common.Utils;

public class Topic_08_User_interaction extends BaseDriver {
    Actions actions;
    @BeforeClass
    @Override
    public void beforeClass() {
        super.beforeClass();
        actions = new Actions(driver);
        driver.get("https://automationfc.github.io/jquery-tooltip");
    }

    @Test
    private void TC_01_Tooltip() {
        actions.moveToElement(driver.findElement(By.cssSelector("input#age"))).perform();
        Utils.sleepInSecond(3);
        WebElement tooltip = driver.findElement(By.cssSelector("div.ui-tooltip-content"));
        Assert.assertEquals(tooltip.getText(),"We ask for your age only for statistical purposes.");
    }

    @Test
    private void TC_02_Tooltip() {
        driver.get("https://www.myntra.com");
        actions.moveToElement(driver.findElement(By.xpath("//div[@class='desktop-navLink']//a[text()='Kids']"))).perform();
        Utils.sleepInSecond(3);
        driver.findElement(By.xpath("//div[@class='desktop-navLink']//a[text()='Home & Bath']")).click();
        Utils.sleepInSecond(3);
    }

    @Test
    private void TC_02_Fahasa() {
        driver.get("https://www.fahasa.com");
        //Hover 1
        actions.moveToElement(driver.findElement(By.cssSelector("span.icon_menu"))).perform();
        Utils.sleepInSecond(3);
        //Hover 2
        actions.moveToElement(driver.findElement(By.xpath("//a[@title='Sách Trong Nước']"))).perform();
        Utils.sleepInSecond(3);

        driver.findElement(By.xpath("//div[contains(@class,'fhs_menu_content')]//a[text()='Quản Trị - Lãnh Đạo']")).click();
        Utils.sleepInSecond(3);

        Assert.assertTrue(driver.findElement(By.xpath("//ol[@class='breadcrumb']//strong[text()='Quản Trị - Lãnh Đạo']")).isDisplayed());
    }
}
