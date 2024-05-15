package webdriver;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.annotations.Test;
import webdriver.common.BaseDriver;
import webdriver.common.Utils;

import java.util.List;

public class Topic_13_Upload_File extends BaseDriver {
    @Test
    private void TC_01_One_File() {
        driver.get("https://blueimp.github.io/jQuery-File-Upload/");
        driver.findElement(By.xpath("//input[@type='file']")).sendKeys(projectPath + "/resources/1.jpg");
        Utils.sleepInSecond(3);
        driver.findElement(By.xpath("//input[@type='file']")).sendKeys(projectPath + "/resources/2.jpg");
        Utils.sleepInSecond(3);
        List<WebElement> startBtns = driver.findElements(By.cssSelector("tbody.files button.start"));
        for (WebElement btn: startBtns) {
            btn.click();
            Utils.sleepInSecond(3);
        }
    }

    @Test
    private void TC_01_Multiple_File() {
        driver.get("https://blueimp.github.io/jQuery-File-Upload/");
        driver.findElement(By.xpath("//input[@type='file']")).sendKeys(projectPath + "/resources/1.jpg" + "\n" + projectPath + "/resources/2.jpg" );
        Utils.sleepInSecond(3);
    }
}
