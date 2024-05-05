package webdriver;

import org.openqa.selenium.By;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import webdriver.common.BaseDriver;

public class Topic_03_Xpath_Css extends BaseDriver {
    @BeforeClass
    @Override
    public void beforeClass() {
        super.beforeClass();
        driver.get("http://live.techpanda.org/index.php/customer/account/login/");
    }

    @Test
    public void TC_01_Xpath_DirectChild() {
        driver.findElement(By.cssSelector("div>input[id='email']"));
    }

    @Test
    public void TC_01_Xpath_SubChild() {
        driver.findElement(By.cssSelector("ul[class='form-list'] input[id='email']"));
    }

    @Test
    public void TC_01_Xpath_Id() {
        driver.findElement(By.cssSelector("input[id='email']"));

        driver.findElement(By.cssSelector("input#email"));
    }

    @Test
    public void TC_01_Xpath_Class() {
        driver.findElement(By.cssSelector("div[class='account-login']"));

        driver.findElement(By.cssSelector("div.account-login"));

        driver.findElement(By.cssSelector("div.col-2.registered-users"));
    }

    @Test
    public void TC_01_Xpath_Attribute() {
        driver.findElement(By.cssSelector("input[name='login[username]']"));
    }

    @Test
    public void TC_01_Xpath_Multiple_Attribute() {
        driver.findElement(By.cssSelector("input[id='email'][title='Email Address']"));
    }

    @Test
    public void TC_01_Xpath_AXES() {
        driver.get("https://live.techpanda.org/index.php/mobile.html");
        /*
        *  Not support: get parent info
        *  Character: + get next node
        *  Character: ~ get next nodes
        * */
        driver.findElement(By.cssSelector("a[title='Xperia']~div>h2+div"));
    }
}
