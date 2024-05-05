package webdriver;

import org.openqa.selenium.By;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import webdriver.common.BaseDriver;
import webdriver.common.Utils;

import java.util.concurrent.TimeUnit;

public class Topic_05_TextBox_TextArea extends BaseDriver {
    @BeforeClass
    @Override
    public void beforeClass() {
        super.beforeClass();
        driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
        driver.get("https://opensource-demo.orangehrmlive.com/web/index.php/auth/login");
    }

    @Test
    public void TC_01_TextBox() {
        Utils.sendHumanKeys(driver.findElement(By.name("username")),"Admin");
        Utils.sendHumanKeys(driver.findElement(By.name("password")),"admin123");
        driver.findElement(By.cssSelector("button.orangehrm-login-button")).click();
    }
}
