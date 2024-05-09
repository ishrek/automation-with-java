package webdriver;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.Point;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import webdriver.common.BaseDriver;
import webdriver.common.Utils;

import java.awt.*;
import java.awt.event.InputEvent;
import java.io.IOException;
import java.util.List;

public class Topic_08_User_interaction extends BaseDriver {
    Actions actions;

    @BeforeClass
    @Override
    public void beforeClass() {
        super.beforeClass();
        actions = new Actions(driver);
        driver.get("https://automationfc.github.io/jquery-tooltip");
    }

    @Test
    private void TC_01_Tooltip() {
        actions.moveToElement(driver.findElement(By.cssSelector("input#age"))).perform();
        Utils.sleepInSecond(3);
        WebElement tooltip = driver.findElement(By.cssSelector("div.ui-tooltip-content"));
        Assert.assertEquals(tooltip.getText(), "We ask for your age only for statistical purposes.");
    }

    @Test
    private void TC_02_Tooltip() {
        driver.get("https://www.myntra.com");
        actions.moveToElement(driver.findElement(By.xpath("//div[@class='desktop-navLink']//a[text()='Kids']"))).perform();
        Utils.sleepInSecond(3);
        driver.findElement(By.xpath("//div[@class='desktop-navLink']//a[text()='Home & Bath']")).click();
        Utils.sleepInSecond(3);
    }

    @Test
    private void TC_03_Fahasa() {
        driver.get("https://www.fahasa.com");
        //Hover 1
        actions.moveToElement(driver.findElement(By.cssSelector("span.icon_menu"))).perform();
        Utils.sleepInSecond(3);
        //Hover 2
        actions.moveToElement(driver.findElement(By.xpath("//a[@title='Sách Trong Nước']"))).perform();
        Utils.sleepInSecond(3);

        driver.findElement(By.xpath("//div[contains(@class,'fhs_menu_content')]//a[text()='Quản Trị - Lãnh Đạo']")).click();
        Utils.sleepInSecond(3);

        Assert.assertTrue(driver.findElement(By.xpath("//ol[@class='breadcrumb']//strong[text()='Quản Trị - Lãnh Đạo']")).isDisplayed());
    }

    @Test
    private void TC_04_Click_and_Hold() {
        driver.get("https://automationfc.github.io/jquery-selectable");

        List<WebElement> items = driver.findElements(By.cssSelector("ol#selectable>li"));
        if (!items.isEmpty()) {
            // Click item 1
            // Move to last item
            // release() : nhả chuột ra
            actions.clickAndHold(items.get(0)).moveToElement(items.get(items.size() - 1)).release().perform();
        }
        Utils.sleepInSecond(2);

        List<WebElement> selectedItems = driver.findElements(By.cssSelector("ol#selectable>li.ui-selected"));
        Assert.assertEquals(selectedItems.size(), items.size());
    }

    @Test
    private void TC_05_Click_and_Select_Random_Multiple_Item() {
        driver.get("https://automationfc.github.io/jquery-selectable");

        List<WebElement> items = driver.findElements(By.cssSelector("ol#selectable>li"));
        if (!items.isEmpty()) {
            // Click item
            // Giữ Ctrl
            Keys key = Keys.CONTROL;
            if (!osName.contains("Windows")) {
                key = Keys.COMMAND;
            }
            actions.keyDown(key).perform();
            Utils.sleepInSecond(2);
            actions.click(items.get(0)).click(items.get(2)).click(items.get(3)).click(items.get(5)).click(items.get(9)).perform();
            Utils.sleepInSecond(2);
            // release() : nhả chuột ra
            actions.keyUp(key);
        }
    }

    @Test
    private void TC_06_Double_Click() {
        driver.get("https://automationfc.github.io/basic-form");
        driver.manage().window().maximize();
        By btn = By.xpath("//button[text()='Double click me']");
        jsExcutor.executeScript("arguments[0].scrollIntoView(false);", driver.findElement(btn));
        actions.doubleClick(driver.findElement(btn)).perform();
        Utils.sleepInSecond(2);
    }

    @Test
    private void TC_06_Right_Click() {
        driver.get("http://swisnl.github.io/jQuery-contextMenu/demo.html");
        Utils.sleepInSecond(2);
        actions.contextClick(driver.findElement(By.cssSelector("span.context-menu-one"))).perform();
        Utils.sleepInSecond(2);
        Assert.assertTrue(driver.findElement(By.cssSelector("li.context-menu-icon-quit")).isDisplayed());

        Utils.sleepInSecond(2);
        actions.moveToElement(driver.findElement(By.cssSelector("li.context-menu-icon-quit"))).perform();
    }

    @Test
    private void TC_06_Drag_and_Drop() throws IOException {
        String pathJs = "";
        if (osName.contains("Windows")) {
            pathJs = System.getProperty("user.dir") + "\\selenium\\webdriver\\dragAndDrop\\drag_and_drop_helper.js";
        } else {
            pathJs = System.getProperty("user.dir") + "/selenium/webdriver/dragAndDrop/drag_and_drop_helper.js";
        }

        String jsHelper = Utils.getContentFile(pathJs);
        driver.get("https://automationfc.github.io/drag-drop-html5/");

        String sourceCss = "#column-a";
        String targetCss = "#column-b";

        // A to B
        jsHelper = jsHelper + "$(\"" + sourceCss + "\").simulateDragDrop({ dropTarget: \"" + targetCss + "\"});";
        jsExcutor.executeScript(jsHelper);
        Utils.sleepInSecond(3);
    }

    @Test
    private void TC_06_Drag_and_Drop_By_Robot() throws IOException, AWTException {
        driver.get("https://automationfc.github.io/drag-drop-html5/");
        // Độ ổn định ko cao
        String sourceXpath = "//div[@id='column-a']";
        String targetXpath = "//div[@id='column-b']";

        dragAndDropHTML5ByXpath(sourceXpath, targetXpath);
        Utils.sleepInSecond(3);

        dragAndDropHTML5ByXpath(targetXpath, sourceXpath);
        Utils.sleepInSecond(3);
    }

    public void dragAndDropHTML5ByXpath(String sourceLocator, String targetLocator) throws AWTException {

        WebElement source = driver.findElement(By.xpath(sourceLocator));
        WebElement target = driver.findElement(By.xpath(targetLocator));

        // Setup robot
        Robot robot = new Robot();
        robot.setAutoDelay(500);

        // Get size of elements
        Dimension sourceSize = source.getSize();
        Dimension targetSize = target.getSize();

        // Get center distance
        int xCentreSource = sourceSize.width / 2;
        int yCentreSource = sourceSize.height / 2;
        int xCentreTarget = targetSize.width / 2;
        int yCentreTarget = targetSize.height / 2;

        Point sourceLocation = source.getLocation();
        Point targetLocation = target.getLocation();

        // Make Mouse coordinate center of element
        sourceLocation.x += 20 + xCentreSource;
        sourceLocation.y += 110 + yCentreSource;
        targetLocation.x += 20 + xCentreTarget;
        targetLocation.y += 110 + yCentreTarget;

        // Move mouse to drag from location
        robot.mouseMove(sourceLocation.x, sourceLocation.y);

        // Click and drag
        robot.mousePress(InputEvent.BUTTON1_MASK);
        robot.mousePress(InputEvent.BUTTON1_MASK);
        robot.mouseMove(((sourceLocation.x - targetLocation.x) / 2) + targetLocation.x, ((sourceLocation.y - targetLocation.y) / 2) + targetLocation.y);

        // Move to final position
        robot.mouseMove(targetLocation.x, targetLocation.y);

        // Drop
        robot.mouseRelease(InputEvent.BUTTON1_MASK);
    }
}
