package webdriver;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Topic_03_Xpath_2 {
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
        driver.get("https://automationfc.github.io/basic-form");
    }

    @Test
    public void TC_01_Nested_Text() {
        driver.findElement(By.xpath("//h5[@id='nested']"));
        System.out.println(driver.findElement(By.xpath("//h5[@id='nested']")).getText());
        /*==================
        * Tìm text ko có tab  hoặc khoảng trắng trước và sau. ko quan tâm đứng ở vị trí bao nhiêu
        * text()=''
        * */
        driver.findElement(By.xpath("//h5[text()='Michael Jackson']"));

        /*==================
         * Tìm text có tab  hoặc khoảng trắng trước và sau. text phải nằm ở vị tr đầu tiêu
         * contains()=''
         * */
        driver.findElement(By.xpath("//h5[contains(text(),'Hello World')]"));
    }

    @Test
    public void TC_01_And_Or() {
        driver.findElement(By.xpath("//h5[text()='Michael Jackson' or @id='seven']"));
    }

    @Test
    public void TC_01_Not() {
//        driver.findElement(By.xpath("//h5[not(contains(text(),'Michael Jackson'))"));
    }

    @Test
    public void TC_01_Inside() {
        driver.get("https://live.techpanda.org/index.php/mobile.html");
        driver.findElement(By.xpath("(//span[text()='Add to Cart'])[1]"));
    }

    @AfterClass
    public void  afterClass() {
        driver.quit();
    }
}
