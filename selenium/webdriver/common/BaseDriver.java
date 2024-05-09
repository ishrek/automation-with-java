package webdriver.common;

import org.openqa.selenium.Alert;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;

import java.time.Duration;
import java.util.concurrent.TimeUnit;

public class BaseDriver {
    public WebDriver driver;
    public Alert alert;
    public JavascriptExecutor jsExcutor;
    public WebDriverWait explicitWait;
    String projectPath = System.getProperty("user.dir");
    public String osName = System.getProperty("os.name");

    WebDriverType driverType = WebDriverType.FIRE_FOX;
    public FirefoxOptions ffPptions;
    @BeforeClass
    public void beforeClass() {


        switch (driverType) {
            case CHORME -> {
                if (osName.contains("Windows")) {
                    System.setProperty("webdriver.chrome.driver", projectPath + "\\browserDrivers\\chromedriver.exe");
                } else {
                    System.setProperty("webdriver.chrome.driver", projectPath + "/browserDrivers/chromedriver");
                }
                driver = new ChromeDriver();
            }
            case EDGE -> {
                driver = new EdgeDriver();
            }
            case FIRE_FOX -> {
                if (osName.contains("Windows")) {
                    System.setProperty("webdriver.gecko.driver", projectPath + "\\browserDrivers\\geckodriver.exe");
                } else {
                    System.setProperty("webdriver.gecko.driver", projectPath + "/browserDrivers/geckodriver");
                }
                if (ffPptions == null) {
                    driver = new FirefoxDriver();
                } else {
                    driver = new FirefoxDriver(ffPptions);
                }

            }
        }
        jsExcutor =(JavascriptExecutor) driver;
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(15));
    }

    @AfterClass
    public void  afterClass() {
        driver.quit();
    }
}

enum WebDriverType {
    FIRE_FOX,
    CHORME,
    EDGE
}