package webdriver;

import org.openqa.selenium.Alert;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import webdriver.common.BaseDriver;

import java.time.Duration;

public class Topic_15_TestNG_Multiple_Env {
    public WebDriver driver;

    public String projectPath = System.getProperty("user.dir");
    public String osName = System.getProperty("os.name");

    /*
     * Su ding TestNG parameter duoc su dung de support multiple evn (chrome, egde, firefox, safari...), platform(Dev, testing, stag...), level(web, mobile, api...)
     * https://docs.google.com/document/d/1Icx24E9tRuK0K_KDp96ebPIiGihYNBA_qyAavus3yq0/edit
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

    @Parameters("environment")
    @Test
    private void TC_01(String environment) {
        System.out.println("TC_01");
        driver.get(getENV(environment));
    }

    @AfterClass(alwaysRun = true)
    private void after_class() {
        driver.quit();
    }

    private String getENV(String env) {
        String url = null;
        if (env.equals("dev")) {
            url = "https://www.facebook.com/";
        } else {
            url = "http://live.techpanda.org/index.php";
        }
        return url;
    }
}
