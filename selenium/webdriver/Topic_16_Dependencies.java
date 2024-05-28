package webdriver;

import org.testng.Assert;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;
import webdriver.common.BaseDriver;
@Listeners(Topic_17_Listener.class)
public class Topic_16_Dependencies extends BaseDriver {
    // Duoc sd khi testcase sau phu thuoc vao testcase truoc. Neu test truoc false thi test sau se fail
    @Test()
    void TC_01() {
        Assert.assertFalse(true);
    }
    @Test(dependsOnMethods = "TC_01")
    void TC_02() {

    }

    @Test(dependsOnMethods = {"TC_01","TC_02"})
    void TC_03() {

    }
}
