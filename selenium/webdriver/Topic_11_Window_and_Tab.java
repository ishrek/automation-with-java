package webdriver;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.testng.annotations.Test;
import webdriver.common.BaseDriver;
import webdriver.common.Utils;

import java.util.List;
import java.util.Set;

public class Topic_11_Window_and_Tab extends BaseDriver {
    @Test
    private void TC_01_Window(){
        // Using for 2 tabns/windows
        By google = By.xpath("//a[text()='GOOGLE']");
        driver.get("https://automationfc.github.io/basic-form/index.html");
        // Get ID current driver
        String windowId = driver.getWindowHandle();
        System.out.println(windowId);
        driver.findElement(google).click();
        Utils.sleepInSecond(2);

        //Get all tab/Window
        Set<String> list = driver.getWindowHandles();
        for (String _id : list) {
            if (!_id.equals(windowId)) {
                // Switch to window
                driver.switchTo().window(_id);
                break;
            }
        }

        Utils.sleepInSecond(2);
        String googleId = driver.getWindowHandle();

        for (String _id : list) {
            if (!_id.equals(googleId)) {
                // Switch to window
                driver.switchTo().window(_id);
                break;
            }
        }

        Utils.sleepInSecond(2);
    }

    @Test
    private void TC_01_Multiple_Tab(){
        By google = By.xpath("//a[text()='GOOGLE']");
        driver.get("https://automationfc.github.io/basic-form/index.html");

        driver.findElement(google).click();
        Utils.sleepInSecond(2);
        switchMultipleWindowByPageTitle("Google");

        Utils.sendHumanKeys(driver.findElement(By.xpath("//textarea[@role='combobox']")), "iShrek");
        driver.findElement(By.xpath("//textarea[@role='combobox']")).sendKeys(Keys.ENTER);

        Utils.sleepInSecond(2);
    }

    @Test
    private void TC_01_Multiple_Tab_With_iframe(){
        driver.get("http://live.techpanda.org/index.php");

        driver.findElement(By.xpath("//a[text()='Mobile']")).click();
        Utils.sleepInSecond(2);

        driver.findElement(By.xpath("//a[text()='Samsung Galaxy']/parent::h2/following-sibling::div[@class='actions']//a[text()='Add to Compare']")).click();

        driver.findElement(By.xpath("//a[text()='Sony Xperia']/parent::h2/following-sibling::div[@class='actions']//a[text()='Add to Compare']")).click();
        driver.findElement(By.xpath("//button[@title='Compare']")).click();

        switchMultipleWindowByPageTitle("Products Comparison List - Magento Commerce");

        driver.findElement(By.xpath("//button[@title='Close Window']")).click();
        switchMultipleWindowByPageTitle("Mobile");
        Utils.sleepInSecond(2);
    }

    private void  switchMultipleWindow(String pageId) {
        //Get all tab/Window
        Set<String> list = driver.getWindowHandles();
        for (String _id : list) {
            if (!_id.equals(pageId)) {
                // Switch to window
                driver.switchTo().window(_id);
                Utils.sleepInSecond(1);
            }
        }
    }
    // Move to element has index more then 2 windows/tabs
    private void  switchMultipleWindowByPageTitle(String pageTitle) {
        //Get all tab/Window
        Set<String> list = driver.getWindowHandles();
        for (String _id : list) {
            if (!_id.equals(pageTitle)) {
                // Switch to window
                driver.switchTo().window(_id);
                Utils.sleepInSecond(1);
                String actualPageTitle = driver.getTitle();
                if (actualPageTitle.equals(pageTitle)) {
                    break;
                }
                Utils.sleepInSecond(1);
            }
        }
    }
}
