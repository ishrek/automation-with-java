package webdriver.common;

import org.openqa.selenium.WebElement;

import java.util.Random;

public class Utils {
    public static void sendHumanKeys(WebElement element, String text) {
        Random r = new Random();
        for(int i = 0; i < text.length(); i++) {
            try {
                Thread.sleep((int)(r.nextGaussian() * 15 + 100));
            } catch(InterruptedException e) {}
            String s = new StringBuilder().append(text.charAt(i)).toString();
            element.sendKeys(s);
        }
    }

    public  static void sleepInSecond(long timeInSecond) {
        try {
            Thread.sleep(timeInSecond * 1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
