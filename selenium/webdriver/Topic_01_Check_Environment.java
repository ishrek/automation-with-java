package webdriver;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.concurrent.TimeUnit;

public class Topic_01_Check_Environment {
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
    }

    @Test
    public void TC_01_Run_On_Firefox() {
        driver = new FirefoxDriver();
        driver.get("https://www.facebook.com/");
    }

    @Test
    public void TC_02_Run_On_Chrome() {
        driver = new ChromeDriver();
//        driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
        driver.manage().window().maximize();
        driver.get("https://www.facebook.com/");
    }

//    @Test
//    public void TC_03_Run_On_Edge() {
//        driver = new EdgeDriver();
//        driver.get("https://www.facebook.com/");
//        driver.quit();
//    }
    @AfterClass
    public void  afterClass() {
        driver.quit();
    }
}

