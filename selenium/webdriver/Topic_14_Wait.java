package webdriver;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import webdriver.common.BaseDriver;
import webdriver.common.Utils;

import java.time.Duration;
import java.util.List;

public class Topic_14_Wait extends BaseDriver {
    /*
     * https://docs.google.com/document/d/12fm_slphnj78ZtgYPdPlx3lQzWCsih6s0EGOYhG0ftw/edit#heading=h.n1lxu52xg9bq
     * Async/ Sync
     * Element Condition:
     * case 1: element is display on UI and DOM
     * case 2: element not display UI but on DOM
     * case 3: element not display on UI and not DOM
     *
     * Element status
     * Visible/Display - Visibility
     * Invisible/ Un-display - Invisibility
     * Presence  (Wait sử dụng cho case phải có trong DOM - presenceOfAllElementsLocatedBy)
     * Stateless (Wait duoc su dung khi co 1 element da tim thay roi và sau khi apply wait thi element nay bat buoc phai out DOM
     * */

    By reconfirmEmail = By.cssSelector("input[name='reg_email_confirmation__']");
    private FluentWait fluentWait;
    @BeforeClass
    @Override
    public void beforeClass() {
        super.beforeClass();
        explicitWait = new WebDriverWait(driver, Duration.ofSeconds(30));
        driver.get("https://www.facebook.com/");
    }

    @Test
    private void TC_01_Element_Visible() {
        driver.findElement(By.cssSelector("a[data-testid='open-registration-form-button']")).click();
        Utils.sleepInSecond(2);
        driver.findElement(By.cssSelector("input[name='reg_email__']")).sendKeys("abc@gmail.com");
        Utils.sleepInSecond(3);
        // Tại thời điểm này thì confirm email textbox đang visibile/ displayed (height and width -> 0
        explicitWait.until(ExpectedConditions.visibilityOfElementLocated(reconfirmEmail));
        // Verify reConfirmEmail displayed
        Assert.assertTrue(driver.findElement(reconfirmEmail).isDisplayed());
    }

    @Test
    private void TC_02_Element_InVisible() {
        driver.findElement(By.cssSelector("a[data-testid='open-registration-form-button']")).click();
        Utils.sleepInSecond(2);
        /*
         * Case 2: not display on UI bug have in DOM
         * */
        driver.findElement(By.cssSelector("input[name='reg_email__']")).clear();
        Utils.sleepInSecond(3);

        explicitWait.until(ExpectedConditions.invisibilityOfElementLocated(reconfirmEmail));
        Utils.sleepInSecond(3);
        // Verify reConfirmEmail displayed
        Assert.assertFalse(driver.findElement(reconfirmEmail).isDisplayed());


        /*
         * Case 3: not display on UI bug have in DOM
         * */
    }

    @Test
    private void TC_03_Element_Presence() {
        driver.findElement(By.cssSelector("a[data-testid='open-registration-form-button']")).click();
        Utils.sleepInSecond(2);

        driver.findElement(By.cssSelector("input[name='reg_email__']")).sendKeys("abc@gmail.com");
        Utils.sleepInSecond(3);

        // Tại thời điểm này thì confirm email present
        explicitWait.until(ExpectedConditions.presenceOfElementLocated(reconfirmEmail));

        driver.findElement(By.cssSelector("input[name='reg_email__']")).clear();
        Utils.sleepInSecond(3);
    }

    @Test
    private void TC_04_Element_Stateless() {
        driver.findElement(By.cssSelector("a[data-testid='open-registration-form-button']")).click();
        Utils.sleepInSecond(3);

        WebElement ele = driver.findElement(reconfirmEmail);

        driver.findElement(By.xpath("//div[text()='Sign Up']/parent::div/preceding-sibling::img")).click();
        Utils.sleepInSecond(3);
        explicitWait.until(ExpectedConditions.stalenessOf(ele));
    }

    @Test
    private void TC_05_Mixing_Implicit_And_Explicit_FoundElement() {
        /*
         * Implicit: phu hop voi tim element
         * Explicit: phu hop voi trang thai cua element
         */
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(15));
        explicitWait = new WebDriverWait(driver, Duration.ofSeconds(15));
        driver.get("https://www.facebook.com/");

        // Implicit
        System.out.println("==== Start Implicit: " + System.currentTimeMillis() + " =====");
        driver.findElement(By.xpath("//button[@name='login']"));
        System.out.println("==== End: " + System.currentTimeMillis() + " =====");

        // Explicit
        System.out.println("==== Start Explicit: " + System.currentTimeMillis() + " =====");
        explicitWait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//button[@name='login']")));
        System.out.println("==== End: " + System.currentTimeMillis() + " =====");

    }

    @Test
    private void TC_05_Mixing_Implicit_NotFoundElement() {
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        driver.get("https://www.facebook.com/");

        // Implicit
        System.out.println("==== Start Implicit: " + System.currentTimeMillis() + " =====");
        List<WebElement> item = driver.findElements(By.xpath("//button[@name='adasd']"));
        if (item.isEmpty()) {
            System.out.println("==== Not found End: " + System.currentTimeMillis() + " =====");
            Assert.assertTrue(item.isEmpty());
        }
        System.out.println("==== End: " + System.currentTimeMillis() + " =====");
    }

    @Test
    private void TC_05_Fluent_wait() {
        // Fluent wait khác cac wait khac la polling (tgian tim lai element)
    }

    @Test
    private void TC_05_Page_Ready() {
        /**
           Handle Page Ready / Ajax Loading
            (Su dung cho cac phan co loading ngam ben duoi)
         */
        driver.get("https://api.orangehrm.com/");
        explicitWait = new WebDriverWait(driver, Duration.ofSeconds(30));

        explicitWait.until(ExpectedConditions.invisibilityOfElementLocated(By.cssSelector("div#loader>div.spinner")));
    }

    @Test
    private void TC_05_Admin_NopCommerce() {
        driver.get("https://admin-demo.nopcommerce.com/login?ReturnUrl=%2Fadmin%2F/");
        driver.findElement(By.cssSelector("input#Email")).clear();
        driver.findElement(By.cssSelector("input#Password")).clear();
        Utils.sendHumanKeys(driver.findElement(By.cssSelector("input#Email")), "admin@yourstore.com");
        Utils.sendHumanKeys(driver.findElement(By.cssSelector("input#Password")), "admin");
        driver.findElement(By.cssSelector("button.login-button")).click();
        // wait not worked : not Ok
//        explicitWait.until(ExpectedConditions.elementToBeClickable(By.xpath("//a[text()='Logout']")));

        //case 1: Wait ajax loading disappear : OK
        //explicitWait.until(ExpectedConditions.invisibilityOfElementLocated(By.cssSelector("div#ajaxBusy")));

        //case 2: Wait ajax function finished;
        Utils.isPageLoadedSuccess(explicitWait, driver, jsExcutor);

        driver.findElement(By.xpath("//a[text()='Logout']")).click();
    }

    @Test
    private void TC_05_Alert_Confirm() {
        driver.get("https://automationfc.github.io/basic-form");
        WebElement btn = driver.findElement(By.xpath("//button[text()='Click for JS Confirm']"));
        btn.click();
        Utils.sleepInSecond(3);

        //case 2 (Recommend). Wait to alert displayed and interact
        alert = explicitWait.until(ExpectedConditions.alertIsPresent());
        System.out.println(alert.getText());
        org.testng.Assert.assertEquals(alert.getText(), "I am a JS Confirm");
        alert.dismiss();
        Utils.sleepInSecond(2);
    }

    @Test
    private void TC_05_Wait_For_Attribute_Contain_Tobe_Value() {
        driver.get("http://live.techpanda.org/index.php/");
        explicitWait = new WebDriverWait(driver, Duration.ofSeconds(30));
        explicitWait.until(ExpectedConditions.attributeContains(By.cssSelector("input#search"), "placeholder", "Search entire store"));
        explicitWait.until(ExpectedConditions.attributeContains(By.cssSelector("input#search"), "placeholder", "Search entire store here..."));

        // Wait exactly element ( attributeToBe tìm chính xác value của element)
        explicitWait.until(ExpectedConditions.attributeToBe(By.cssSelector("input#search"), "placeholder", "Search entire store here..."));

        Assert.assertEquals(driver.findElement(By.cssSelector("input#search")).getAttribute("placeholder"), "Search entire store here...");
    }

    @Test
    private void TC_05_Wait_For_Clickable() {
        driver.get("https://automationfc.github.io/dynamic-loading");
        explicitWait = new WebDriverWait(driver, Duration.ofSeconds(10));
        By startBtn = By.cssSelector("div#start>button");
        explicitWait.until(ExpectedConditions.elementToBeClickable(startBtn));
        // Đợi cho Start button được ready to click
        driver.findElement(startBtn).click();
    }

    @Test
    private void TC_05_Wait_CheckBox_Multiple_selected() {
        driver.get("https://automationfc.github.io/multiple-fields/");
        List<WebElement> items = driver.findElements(By.cssSelector("input.form-checkbox"));
        for (WebElement item : items) {
            item.click();
        }
        for (WebElement item : items) {
            explicitWait.until(ExpectedConditions.elementToBeSelected(item));
            Assert.assertTrue(item.isSelected());
        }
    }

    @Test
    private void TC_05_Wait_Switch_iframe() {
        driver.get("https://netbanking.hdfcbank.com/netbanking/");
        explicitWait = new WebDriverWait(driver, Duration.ofSeconds(30));
        // Switch frame
//        driver.switchTo().frame("login_page");
        explicitWait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(By.name("login_page")));

        // Integration elements
        Utils.sendHumanKeys(driver.findElement(By.name("fldLoginUserId")), "helloworld");
        driver.findElement(By.cssSelector("a.login-btn")).click();

//        driver.switchTo().defaultContent();
        //placeholder="Password/ IPIN"
        explicitWait.until(ExpectedConditions.visibilityOfElementLocated(By.name("fldPassword")));
        Utils.sendHumanKeys(driver.findElement(By.name("fldPassword")), "helloworld");
        Assert.assertTrue(driver.findElement(By.name("fldPassword")).isDisplayed());
    }

    @Test
    private void TC_05_Wait_Show_Hide_DatePikcer() {
        driver.get("https://demos.telerik.com/aspnet-ajax/ajaxloadingpanel/functionality/explicit-show-hide/defaultcs.aspx");
        explicitWait = new WebDriverWait(driver, Duration.ofSeconds(10));
        By td = By.xpath("//a[text()='9']/parent::td");
        explicitWait.until(ExpectedConditions.elementToBeClickable(td));
        driver.findElement(td).click();

        // Wait loading icon biếnt mất
        explicitWait.until(ExpectedConditions.invisibilityOfElementLocated(By.cssSelector("div:not([style='display:none;'])>div.raDiv")));
        explicitWait.until(ExpectedConditions.elementToBeClickable(By.xpath("//a[text()='9']/parent::td[@class='rcSelected']")));
    }

    @Test
    private void TC_05_Wait_Upload_File() {
        driver.get("https://gofile.io/?t=uploadFiles");
        explicitWait = new WebDriverWait(driver, Duration.ofSeconds(10));
        explicitWait.until(ExpectedConditions.invisibilityOfElementLocated(By.cssSelector("div#mainContent>div>div>div.spinner-border")));
        explicitWait.until(ExpectedConditions.invisibilityOfElementLocated(By.cssSelector("div#mainUploadInitInfo>div>div.spinner-border")));
        String file1 = projectPath + "/resources/1.jpg";
        String file2 = projectPath + "/resources/2.jpg";

        explicitWait.until(ExpectedConditions.elementToBeClickable(By.cssSelector("div#mainContent>div>div>a.ajaxLink>button"))).click();

        By uploadFile = By.xpath("//input[@type='file']");
        driver.findElement(uploadFile).sendKeys(file1 + "\n" + file2);
        Utils.sleepInSecond(3);
        // Wait progress-bar disappear
        explicitWait.until(ExpectedConditions.invisibilityOfAllElements(driver.findElements(By.cssSelector("div.progress-bar"))));
        // Wait message success appear
        explicitWait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[contains(@class,'mainUploadSuccess')]//div[text()='Your files have been successfully uploaded']")));
// Wail + click tp linnk
        explicitWait.until(ExpectedConditions.elementToBeClickable(By.xpath("//div[contains(@class,'mainUploadSuccess')]//a[@class='ajaxLink']"))).click();
// Wait table include all items uploaded
        explicitWait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("div#filesContentTable")));
    }
}

