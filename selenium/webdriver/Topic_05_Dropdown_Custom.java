package webdriver;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import webdriver.common.BaseDriver;
import webdriver.common.Utils;

import java.time.Duration;
import java.util.List;

public class Topic_05_Dropdown_Custom extends BaseDriver {
    @BeforeClass
    @Override
    public void beforeClass() {
        super.beforeClass();
        explicitWait = new WebDriverWait(driver, Duration.ofSeconds(30));
        driver.get("https://jqueryui.com/resources/demos/selectmenu/default.html");
    }

    @Test
    private void TC_01_Selectable() {
        didSelectedItemInDropDown("span#speed-button", "ul#speed-menu div[role='option']", "Faster");
        Assert.assertEquals(driver.findElement(By.cssSelector("span#speed-button>span.ui-selectmenu-text")).getText(), "Faster");
    }

    @Test
    private void TC_01_Editable() {
        driver.get("https://react.semantic-ui.com/maximize/dropdown-example-search-selection/");
        enterAndDidSelectedItemInDropDown("input.search", "span.text", "Angola");
    }

    private void didSelectedItemInDropDown(String parent, String cssItem, String searchText) {/*
     * 1. Xác định loai dropdown đang sử dụng
     * 2. Chờ cho tất cả item load thành công
     * 3. Tìm xem trong danh sách item có cái mình mong muốn ko
     * + Neesu có thì ko cần scolldown
     * + Neeus ko có thì scroll down
     * 4. Kiểm tra có item mong muốn hay chưa
     * 5. Click to item
     * */
        /* Chọn lần đầu */
        Utils.sleepInSecond(2);
        driver.findElement(By.cssSelector(parent)).click();
        Utils.sleepInSecond(2);
        //2. Chờ item load xong
        // Locator lấy phải đại diện cho all items (Phải lấy đến level chứ text value)
        explicitWait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.cssSelector(cssItem)));

        List<WebElement> speedDropDownItems = driver.findElements(By.cssSelector(cssItem));
        for (WebElement ele : speedDropDownItems) {
            String value = ele.getText();
            if (value.equals(searchText)) {
                ele.click();
                break;
            }
        }
        Utils.sleepInSecond(3);
    }

    private void enterAndDidSelectedItemInDropDown(String parent, String cssItem, String searchText) {
        //1. Input text to edit field
        driver.findElement(By.cssSelector(parent)).clear();
        Utils.sendHumanKeys(driver.findElement(By.cssSelector(parent)), searchText);

        /* Chọn lần đầu */
        Utils.sleepInSecond(2);
        driver.findElement(By.cssSelector(parent)).click();
        Utils.sleepInSecond(2);
        //2. Chờ item load xong
        // Locator lấy phải đại diện cho all items (Phải lấy đến level chứ text value)
        explicitWait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.cssSelector(cssItem)));

        List<WebElement> speedDropDownItems = driver.findElements(By.cssSelector(cssItem));
        for (WebElement ele : speedDropDownItems) {
            String value = ele.getText();
            if (value.equals(searchText)) {
                ele.click();
                break;
            }
        }
        Utils.sleepInSecond(2);
    }
}
