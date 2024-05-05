package webdriver;

import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.Select;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import webdriver.common.BaseDriver;

import java.util.Random;

public class Topic_05_Dropdown extends BaseDriver {

    String passport = "12322-123-21-32133";
    String comment = "THis is abc";
    String firstName = "a";
    String lastname = "b";
    String email = "abc@gmail.com";
    String companyName = "hello";
    String password = "asdasd";
    Select select;
    @BeforeClass
    @Override
    public void beforeClass() {
        super.beforeClass();
        driver.get("https://demo.nopcommerce.com/register");
    }

    @Test
    public void TC_01_Register() {
        driver.findElement(By.id("FirstName")).sendKeys(firstName);
        driver.findElement(By.id("LastName")).sendKeys(lastname);
        select = new Select(driver.findElement(By.name("DateOfBirthDay")));
        select.selectByIndex(2);
    }

    private static int randomNumber() {
        Random rand = new Random();
        return rand.nextInt(999);
    }
}
