package webdriver;

import org.openqa.selenium.*;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import webdriver.common.BaseDriver;
import webdriver.common.Utils;

import java.util.Set;
import java.util.concurrent.TimeUnit;

public class Topic_04_Web_Element extends BaseDriver {

    @BeforeClass
    @Override
    public void beforeClass() {
        super.beforeClass();
        driver.get("https://live.techpanda.org/index.php/mobile.html");
    }

    @Test
    public void TC_01_WebElement() {
        String tabId = driver.getWindowHandle();

        Set<String> tabIds = driver.getWindowHandles();
//        System.out.println(tabIds.size());
        System.out.println(tabId);

        // Cookie /cache
        WebDriver.Options opt = driver.manage();
        Set<Cookie> cookies = opt.getCookies();
        System.out.println(cookies.size());

        WebDriver.Window win = opt.window();
        //win.setSize(); // Set size for window(browser)
        //win.getPosition() // get current position brower in screen
        //win.setPosition(); // set current position brower in screen

        // Khoang thoi giam
        WebDriver.Timeouts time = opt.timeouts();
        // Khoang thoi gian waiting element appear trong x giay
        time.implicitlyWait(5, TimeUnit.SECONDS);

        // Time space for page load to finish in x seconds
        time.pageLoadTimeout(5, TimeUnit.SECONDS);

        // WebDriver API - Javascript executor lib
        // Time space for script run to finish in x seconds
        time.setScriptTimeout(5, TimeUnit.SECONDS);

        WebDriver.Navigation nav = driver.navigate();
        // nav.back(); // Back to previous page

        WebDriver.TargetLocator target = driver.switchTo();
//        target.alert(); // swith to alert if you have
//        target.frame() // swith to frame if you have
//        target.window() // swith to window if you have
    }

    @Test
    public void TC_01_WebElement_2() {
        driver.get("https://demo.nopcommerce.com/login");
        WebElement email = driver.findElement(By.id("Email"));
        email.isDisplayed();
        email.clear();
        email.sendKeys("");
        Utils.sendHumanKeys(driver.findElement(By.id("Password")), "Hello");



    }

    @Test
    public void TC_01_WebElement_3() {
        driver.get("https://demo.nopcommerce.com/login");
        WebElement searchBtn = driver.findElement(By.className("search-box-button"));
        String cssColor = searchBtn.getCssValue("background-color");
        searchBtn.getLocation(); // get location cua element so vs web
        System.out.println(cssColor);
        // Chup hinh khi testcase fail
        searchBtn.getScreenshotAs(OutputType.FILE);
    }
}
