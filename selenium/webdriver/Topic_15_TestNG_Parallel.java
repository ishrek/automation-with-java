package webdriver;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import java.time.Duration;

public class Topic_15_TestNG_Parallel {
    public WebDriver driver;

    public String projectPath = System.getProperty("user.dir");
    public String osName = System.getProperty("os.name");

    /*
     * Parallel chạy dễ bị fail nếu
     * + caus hinh may ko du
     * + Bang thong ko du
     * + Hover chuot de bi xung dot
     *
     * Parallel = "methods"
     * + Cac testcase ko nen phu thuoc vao nhau
     * + Can khoi tao/clean browser(driver)
     *
     * */
    @Parameters("browser") // name duoc setting trong RunTestingSuile.xml
    @BeforeClass
    public void beforeClass(String browser) {
        System.out.println("Browser Name: " + browser);
        switch (browser) {
            case "chrome" -> {
                if (osName.contains("Windows")) {
                    System.setProperty("webdriver.chrome.driver", projectPath + "\\browserDrivers\\chromedriver.exe");
                } else {
                    System.setProperty("webdriver.chrome.driver", projectPath + "/browserDrivers/chromedriver");
                }
                driver = new ChromeDriver();
            }
            case "edge" -> {
                driver = new EdgeDriver();
            }
            case "firefox" -> {
                if (osName.contains("Windows")) {
                    System.setProperty("webdriver.gecko.driver", projectPath + "\\browserDrivers\\geckodriver.exe");
                } else {
                    System.setProperty("webdriver.gecko.driver", projectPath + "/browserDrivers/geckodriver");
                }
                driver = new FirefoxDriver();
            }
        }
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(15));
    }

    @Test
    private void TC_01() {
        driver.get("https://live.techpanda.org/index.php/mobile.html");
    }
    @AfterClass(alwaysRun = true)
    private void after_class() {
        driver.quit();
    }
}
