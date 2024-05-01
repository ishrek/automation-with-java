package webdriver;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.concurrent.TimeUnit;

public class Topic_03_Xpath {
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
        driver.get("http://live.techpanda.org/index.php/customer/account/login/");
    }

    @Test
    public void TC_01_Xpath_Absolute() {
        driver.findElement(By.xpath("/html/body/div/div/div[2]/div/div/div/form/div/div[2]/div[1]/ul/li[1]/div/input"));
    }

    @Test
    public void TC_01_Xpath_Relative() {
        /*
         * //tagname[@attribute='value']
         */
        driver.findElement(By.xpath("//input[@id='email']"));
    }

    @Test
    public void TC_01_Xpath_Text() {
        /*
         * //tagname[@attribute='value']
         */
        driver.findElement(By.xpath("//ul[@class='form-list']//label[text()='Email Address']"));
    }

    @Test
    public void TC_01_Xpath_Parent_Node_With_Child() {
        driver.findElement(By.xpath("//input[@id='email']")).sendKeys("abc@gmail.com");
        driver.findElement(By.xpath("//input[@id='pass']")).sendKeys("abc@gmail.com");
        driver.findElement(By.xpath("//button[@id='send2']")).click();
        driver.findElement(By.xpath("//li[@class='error-msg']//span"));
    }

    @Test
    public void TC_01_Xpath_Contains() {;
        driver.findElement(By.xpath("//div[@class='account-cart-wrapper']//span[contains(text(),'Account')]"));
    }

    @AfterClass
    public void  afterClass() {
        driver.quit();
    }
}
