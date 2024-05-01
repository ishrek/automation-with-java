package webdriver;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.concurrent.TimeUnit;

public class Topic_02_Selenium_Locator {
    WebDriver driver;
    String projectPath = System.getProperty("user.dir");
    String osName = System.getProperty("os.name");

    @BeforeClass
    public void beforeClass() {
        if (osName.contains("Windows")) {
            System.setProperty("webdriver.gecko.driver", projectPath + "\\browserDrivers\\geckodriver.exe");
        } else {
            System.setProperty("webdriver.gecko.driver", projectPath + "/browserDrivers/geckodriver");
        }

        driver = new FirefoxDriver();
        driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
        driver.get("https://demo.nopcommerce.com/register");
    }
// <input type="text" data-val="true" data-val-required="First name is required." id="FirstName" name="FirstName">
    @Test
    public void TC_01_ID() {
        WebElement inputField = driver.findElement(By.id("FirstName"));
        inputField.sendKeys("Hello world");
        System.out.println(inputField);
    }

    @Test
    public void TC_01_Class() {
// <button type="submit" class="button-1 search-box-button">Search</button>
//        WebElement btnSearch = driver.findElement(By.className("button-1"));
//        btnSearch.click();
    }

    @Test
    public void TC_01_Name() {
        driver.findElement(By.name("FirstName")).sendKeys("Arsenal");

    }

    @Test
    public void TC_01_TagName() {
        driver.findElement(By.tagName("input"));
    }
    @Test
    public void TC_01_LinkText() {
        driver.findElement(By.linkText("Shipping & returns"));
    }

    @Test
    public void TC_01_Css() {
        driver.findElement(By.cssSelector("input[id='FirstName']"));
        driver.findElement(By.cssSelector("input#FirstName"));
        driver.findElement(By.cssSelector("#FirstName"));

        driver.findElement(By.cssSelector("div.page-title"));
    }

    @Test
    public void TC_01_Xpath() {
        driver.findElement(By.xpath("//input[@id='FirstName']"));

        driver.findElement(By.xpath("//a[@href='/customer/addresses']"));

        driver.findElement(By.xpath("//a[contains(@href,'addresses')]"));
    }

    @Test
    public void TC_01_Partial_LinkText() {
        driver.findElement(By.partialLinkText("vendor"));
    }

    @AfterClass
    public void  afterClass() {
        driver.quit();
    }
}
