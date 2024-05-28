package webdriver;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.firefox.FirefoxProfile;
import org.openqa.selenium.support.CacheLookup;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import webdriver.common.BaseDriver;
import webdriver.common.Utils;

import java.util.List;
import java.util.Random;

public class Topic_09_PopUp extends BaseDriver {
    @FindBy(xpath = "//label[text()='Female']/following-sibling::input") // Using PageFactory
    @CacheLookup // Tuy nhiên, có 1 cách để chỉ tìm 1 lần rồi sử dụng lại, đó là sử dụng annotation @CacheLookup
    private WebElement femaleOption;

    @BeforeClass
    @Override
    public void beforeClass() {
        ffPptions = new FirefoxOptions();
        ffPptions.setProfile(new FirefoxProfile());
        ffPptions.addPreference("dom.webnotifications.enabled", false);
        ffPptions.addPreference("browser.cache.disk.enable", false);
        ffPptions.addPreference("browser.cache.memory.enable", false);
        ffPptions.addPreference("browser.cache.offline.enable", false);
        ffPptions.addPreference("network.http.use-cache", false);
        super.beforeClass();
        // Và khởi tạo initElements (Using PageFactory)
        PageFactory.initElements(driver, this);
    }

    @Test
    private void TC_01_Popup_In_DOM() {
        // Khi ẩn popup đi sẽ vẫn còn element trong html
        driver.get("https://ngoaingu24h.vn");
        By modal = By.cssSelector("div#modal-login-v1 div.modal-content");
        Assert.assertFalse(driver.findElement(modal).isDisplayed());

        By loginBtn = By.cssSelector("button.login_");
        driver.findElement(loginBtn).click();

        Assert.assertTrue(driver.findElement(loginBtn).isDisplayed());
        Utils.sendHumanKeys(driver.findElement(By.cssSelector("input#account-input")), "automationfc");
        Utils.sendHumanKeys(driver.findElement(By.cssSelector("input#password-input")), "automationfc");
        driver.findElement(By.cssSelector("button.btn-login-v1")).click();
        Utils.sleepInSecond(3);
    }

    @Test
    private void TC_02_Popup_In_DOM() {
        // Khi ẩn popup đi sẽ vẫn còn element trong html
        driver.get("https://skills.kynaenglish.vn/dang-nhap?1=%2Fuser%2Fsecurity%2Flogin");
        By registerModal = By.cssSelector("div.k-popup-account-mb div.modal-content");
        Assert.assertTrue(driver.findElement(registerModal).isDisplayed());

        Utils.sendHumanKeys(driver.findElement(By.cssSelector("input#user-login")), "automationfc");
        Utils.sendHumanKeys(driver.findElement(By.cssSelector("input#user-password")), "automationfc");
        driver.findElement(By.cssSelector("button#btn-submit-login")).click();
        Utils.sleepInSecond(3);
    }

    @Test
    private void TC_01_Popup_Out_DOM_Facebook() {
        driver.get("https://facebook.com");
        // Khi ẩn popup đi sẽ ko còn element trong html
        By popUp = By.xpath("//div[text()='Sign Up']/parent::div/parent::div");
        //Verify xem popup đã hiển thị hay chưa
        Assert.assertEquals(driver.findElements(popUp).size(), 0);

        By createAccountBtn = By.cssSelector("a[data-testid='open-registration-form-button']");
        driver.findElement(createAccountBtn).click();
        Utils.sleepInSecond(2);

        Assert.assertFalse(driver.findElements(popUp).isEmpty());

        // Input infor to text
        Utils.sendHumanKeys(driver.findElement(By.name("firstname")), "Martin");
        Utils.sendHumanKeys(driver.findElement(By.name("lastname")), "Odergaad");
        Utils.sendHumanKeys(driver.findElement(By.name("reg_email__")), "hello@gmail.com");
        Utils.sendHumanKeys(driver.findElement(By.name("reg_passwd__")), "hello123");
        new Select(driver.findElement(By.id("day"))).selectByVisibleText("8");
        Utils.sleepInSecond(2);
        new Select(driver.findElement(By.id("month"))).selectByVisibleText("Sep");
        Utils.sleepInSecond(2);
        new Select(driver.findElement(By.id("year"))).selectByVisibleText("1990");
        Utils.sleepInSecond(2);
        femaleOption.click();
        Utils.sleepInSecond(2);
        //Close
        By closeBtn = By.xpath("//div[text()='Sign Up']/parent::div/preceding-sibling::img");
        driver.findElement(closeBtn).click();
    }

    @Test
    private void TC_01_Popup_Out_DOM_Tiki() {
        driver.get("https://tiki.vn");
        By popUp = By.cssSelector("div.ReactModal__Content");

        //Verify xem popup đã hiển thị hay chưa
        Assert.assertEquals(driver.findElements(popUp).size(), 0);
        // Open login form
        By loginButton = By.cssSelector("div[data-view-id*='header_account']");
        driver.findElement(loginButton).click();
        Utils.sleepInSecond(2);
        // Input loging
        Utils.sendHumanKeys(driver.findElement(By.cssSelector("input[name='tel']")), "1132123123");
        Utils.sleepInSecond(2);

        //Login via gmail
        By loginViaEmail = By.cssSelector("p.login-with-email");
        driver.findElement(loginViaEmail).click();
        Utils.sleepInSecond(2);

        driver.findElement(By.xpath("//button[text()='Đăng nhập']")).click();
        Utils.sleepInSecond(2);

        Assert.assertTrue(driver.findElement(By.xpath("//span[@class='error-mess' and text()='Email không được để trống']")).isDisplayed());
        Assert.assertTrue(driver.findElement(By.xpath("//span[@class='error-mess' and text()='Mật khẩu không được để trống']")).isDisplayed());

        driver.findElement(By.cssSelector("img.close-img")).click();
        Utils.sleepInSecond(2);
    }

    @Test
    private void TC_01_Popup_Random_In_DOM() {
        /* Random popup là loại popup có thể hiển thị 1 cách ngẫu nhiên
         * Nếu nó hiển thị thì caafn thao tác lên pop up-> next step
         * Nếu o hiển thị thì qua next step luôn
         * Khi đống thì có thể refresh lại web
         * */
        driver.manage().deleteAllCookies();
        driver.get("https://www.javacodegeeks.com/");
        Utils.sleepInSecond(40);
        // Button có nút ok
        By popUpRandom = By.cssSelector("div.lepopup-popup-container>div:not([style^='display:none'])");
        // Nếu luông có trong DOM thì có thể dùng isDisplay để hiển thị
        if (driver.findElement(popUpRandom).isDisplayed()) {
            By email = By.cssSelector("div.lepopup-input>input[placeholder='Your Email'],[placeholder='Enter your e-mail address']");
            Utils.sendHumanKeys(driver.findElement(email), "elonMusk" + new Random().nextInt(9999) + "@gmail.com");
            driver.findElement(By.cssSelector("a[data-label='Get the Books'],[data-label='OK']>span")).click();
            Utils.sleepInSecond(10);
        }

        // Next step
        driver.findElement(By.cssSelector("input#search-input")).sendKeys("Agile Testing Explained");
        driver.findElement(By.cssSelector("button#search-submit")).click();
    }

    @Test
    private void TC_02_Popup_Random_In_DOM() {
        driver.get("https://vnk.edu.vn/");
        Utils.sleepInSecond(40);
        By popUp = By.cssSelector("div#tve-p-scroller");
        if (driver.findElement(popUp).isDisplayed()) {
            driver.findElement(By.cssSelector("div#tve-p-scroller div.thrv_icon")).click();
            Utils.sleepInSecond(3);
        }

        driver.findElement(By.xpath("//button[text()='Danh sách khóa học']")).click();
        Utils.sleepInSecond(3);
    }

    @Test
    private void TC_01_Popup_Random_OUT_DOM() {
        driver.get("https://dehieu.vn");
        Utils.sleepInSecond(3);
        By element = By.cssSelector("popup-content");
        List<WebElement> dataSource = driver.findElements(element);
        if (!dataSource.isEmpty() && dataSource.get(0).isDisplayed()) {
            Utils.sendHumanKeys(driver.findElement(By.id("popup-name")), "Elon");
            Utils.sendHumanKeys(driver.findElement(By.id("popup-email")), "elonMusk" + new Random().nextInt(9999) + "@gmail.com");
            Utils.sendHumanKeys(driver.findElement(By.id("popup-phone")), "123123123");

            driver.findElement(By.cssSelector("button#close-popup")).click();
            Utils.sleepInSecond(5);
        }

        WebElement inputItem = driver.findElement(By.xpath("//a[text()=' Tất cả khóa học']"));
        jsExcutor.executeScript("arguments[0].click()", inputItem);
        Utils.sleepInSecond(3);

        Utils.sendHumanKeys(driver.findElement(By.cssSelector("input[placeholder='Tìm khóa học']")), "Khóa học Thiết kế và Thi công Hệ Thống BMS");
        Utils.sleepInSecond(3);
        driver.findElement(By.cssSelector("button.header-search-style")).click();
        Utils.sleepInSecond(3);
    }
}
