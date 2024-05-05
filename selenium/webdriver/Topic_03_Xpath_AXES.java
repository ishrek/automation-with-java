package webdriver;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import webdriver.common.BaseDriver;

import java.util.concurrent.TimeUnit;

public class Topic_03_Xpath_AXES extends BaseDriver {
    /*
    * Axes: từ node hiện tại có thể move đến bất cứ node nào cunngx được
    * */

    @Test
    public void TC_01_Xpath_Following_Sibling() {

        driver.get("https://live.techpanda.org/index.php/mobile.html");
        /*
        * Output: lấy ra buttong add to cart của thằng Sony xperia
        * 1.Dung o text = Sony Xperia
        * 2.Lay thang cha : //parent::
        * 3.Di chuyen sang thanng em: following-sibling:div
        * Mục đích: từ cái cố định lấy ra cái không cố định
        * Warning: Css not support ancestor/parent/preceding (ko support đi ngược về cha)
        * */
        driver.findElement(By.xpath("//a[text()='Sony Xperia']/parent::h2/following-sibling::div[@class='actions']/button")).click();
    }
}
