package webdriver;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.firefox.FirefoxProfile;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import webdriver.common.BaseDriver;
import webdriver.common.Utils;

import java.time.Duration;

public class Topic_07_Alert_Handle extends BaseDriver {
    @BeforeClass
    @Override
    public void beforeClass() {
        // Remove notification alert
        ffPptions = new FirefoxOptions();
        ffPptions.setProfile(new FirefoxProfile());
        ffPptions.addPreference("dom.webnotifications.enabled", false);
        super.beforeClass();
        explicitWait = new WebDriverWait(driver, Duration.ofSeconds(10));
        driver.get("https://automationfc.github.io/basic-form");
    }

    @Test
    private void TC_01_Alert_JS() {
        /*
        Type alert/window/iframe/pop-up
        * 1. Iframe -> Html dduowjc nhúng trong web của mình ( giống kieeur redirect web bank)
        * 2. Window -> open new site over current your site
        * 3. Alert
        * 4. Pop-up/dialog
        * */
        WebElement btn = driver.findElement(By.xpath("//button[text()='Click for JS Alert']"));
        btn.click();
        Utils.sleepInSecond(3);
        // case 1. Switch to alert and interact
//        alert = driver.switchTo().alert();

        //case 2 (Recommend). Wait to alert displayed and interact
        alert = explicitWait.until(ExpectedConditions.alertIsPresent());
        System.out.println(alert.getText());
        Assert.assertEquals(alert.getText(), "I am a JS Alert");
        alert.accept();
        Utils.sleepInSecond(2);
    }

    @Test
    private void TC_02_Alert_Confirm() {
        WebElement btn = driver.findElement(By.xpath("//button[text()='Click for JS Confirm']"));
        btn.click();
        Utils.sleepInSecond(3);

        //case 2 (Recommend). Wait to alert displayed and interact
        alert = explicitWait.until(ExpectedConditions.alertIsPresent());
        System.out.println(alert.getText());
        Assert.assertEquals(alert.getText(), "I am a JS Confirm");
        alert.dismiss();
        Utils.sleepInSecond(2);
    }

    @Test
    private void TC_03_Alert_Prompt() {
        WebElement btn = driver.findElement(By.xpath("//button[text()='Click for JS Prompt']"));
        btn.click();
        Utils.sleepInSecond(3);

        //case 2 (Recommend). Wait to alert displayed and interact
        alert = explicitWait.until(ExpectedConditions.alertIsPresent());
        System.out.println(alert.getText());

        Assert.assertEquals(alert.getText(), "I am a JS prompt");

        alert.sendKeys("asdbasd");

        alert.dismiss();
        Utils.sleepInSecond(2);
    }

    @Test
    private void TC_03_Alert_Authentication_ByPass_via_Link() {
        String url = "http://the-internet.herokuapp.com";
        driver.get(url);
        By href = By.xpath("//a[text()='Basic Auth']");
        String authenURL = driver.findElement(href).getAttribute("href");

        // Case 1: pass user and pass to url
        // http:// + username: password: @ url
        String[] arr = url.split("//");
        url = arr[0] + "//" + "admin" + ":" + "admin" + "@" + arr[1];
        driver.get(url);
    }

    @Test
    private void TC_03_Alert_Authentication_ByPass_via_AutoIT() {
        // AutoIT just only work on Windows
        if (osName.contains("Windows")) {
            String url = "http://the-internet.herokuapp.com/basic_auth";
            driver.get(url);
        }
    }
}
