package webdriver;

import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import webdriver.common.BaseDriver;
import webdriver.common.Utils;

import java.time.Duration;

public class Topic_05_ScrollDown extends BaseDriver {
    @BeforeClass
    @Override
    public void beforeClass() {
        super.beforeClass();
        driver.get("http://omayo.blogspot.com/");
    }

    @Test
    public void TC_01_Scroll() {
        Utils.sleepInSecond(3);
        WebElement myBtn =  driver.findElement(By.id("myBtn"));
//        JavascriptExecutor js = (JavascriptExecutor) driver;
//        String jsStr = "arguments[0].scrollIntoView(true);";
//        js.executeAsyncScript(jsStr,myBtn);

        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", myBtn);
        Actions builder = new Actions(driver);
        builder.moveToElement(myBtn).build().perform();
        Utils.sleepInSecond(3);
    }

    @Test
    public void TC_02_Scroll_without_JS() {
        driver.manage().window().maximize();
        driver.get("https://accounts.google.com");
        Utils.sleepInSecond(3);
        driver.findElement(By.cssSelector("[aria-haspopup='listbox']")).click();
        Utils.sleepInSecond(3);
        WebElement element = driver.findElement(By.cssSelector("[aria-label='Change language'] >li[data-value='te']"));
        scrollToElement(element,driver);
        Utils.sleepInSecond(3);
    }

    @Test
    public void TC_03() {
        driver.get("http://www.executeautomation.com/demosite/seleniumwebdriver.html");
        new WebDriverWait(driver, Duration.ofSeconds(5)).until(ExpectedConditions.titleIs("Execute Automation"));
        new Actions(driver).moveToElement(new WebDriverWait(driver, Duration.ofSeconds(5)).until(ExpectedConditions.elementToBeClickable(By.xpath("//span[@id='Automation Tools']")))).build().perform();
        new Actions(driver).moveToElement(new WebDriverWait(driver, Duration.ofSeconds(5)).until(ExpectedConditions.elementToBeClickable(By.xpath("//li[@class='active has-sub']/a/span//following::ul[1]/li[@class='has-sub']/a/span[@id='Selenium']")))).build().perform();
        new WebDriverWait(driver, Duration.ofSeconds(5)).until(ExpectedConditions.elementToBeClickable(By.xpath("//li[@class='active has-sub']/a/span//following::ul[1]/li/a/span[@id='Selenium']//following::ul[1]/li/a/span[text()='Selenium WebDriver']"))).click();
    }

    public static WebElement scrollToElement(WebElement elementToScroll, WebDriver webDriver) {

        Rectangle rect = elementToScroll.getRect();
        int deltaY = rect.y + rect.height;
        new Actions(webDriver)
                .scrollByAmount(0, deltaY)
                .perform();
        return elementToScroll;

    }
}
