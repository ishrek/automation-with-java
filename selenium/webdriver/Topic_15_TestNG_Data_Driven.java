package webdriver;

import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import webdriver.common.BaseDriver;

public class Topic_15_TestNG_Data_Driven extends BaseDriver {
    // https://viblo.asia/p/su-dung-google-spreadsheet-google-sheet-de-thuc-hien-data-driven-testing-trong-selenium-java-1Je5E9oL5nL
    // Dung de tach data ra khoi source code
    //https://gist.github.com/daominhdam/ff3658ef5e7605d80de26a8b17eaa5a6
    By emailTextbox = By.xpath("//*[@id='email']");
    By passwordTextbox = By.xpath("//*[@id='pass']");
    By loginButton = By.xpath("//*[@id='send2']");
    @Test(dataProvider = "loginData")
    public void TC_01_LoginToSystem(String username, String password)  {
        driver.get("http://live.techpanda.org/index.php/customer/account/login/");

        driver.findElement(emailTextbox).sendKeys(username);
        driver.findElement(passwordTextbox).sendKeys(password);
        driver.findElement(loginButton).click();
        Assert.assertTrue(driver.findElement(By.xpath("//div[@class='col-1']//p")).getText().contains(username));

        // ....

        driver.findElement(By.xpath("//header[@id='header']//span[text()='Account']")).click();
        driver.findElement(By.xpath("//a[text()='Log Out']")).click();
    }

    @DataProvider(name = "loginData")
    public Object[][] UserAndPasswordData() {
        return new Object[][]{
                {"selenium_11_01@gmail.com", "111111"},
                {"selenium_11_02@gmail.com", "111111"},
                {"selenium_11_03@gmail.com", "111111"}};
    }
}
