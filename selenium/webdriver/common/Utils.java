package webdriver.common;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.*;
import java.nio.charset.Charset;
import java.time.Duration;
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

    public static String getContentFile(String filePath) throws IOException {
        Charset cs = Charset.forName("UTF-8");
        FileInputStream stream = new FileInputStream(filePath);
        try {
            Reader reader = new BufferedReader(new InputStreamReader(stream, cs));
            StringBuilder builder = new StringBuilder();
            char[] buffer = new char[8192];
            int read;
            while ((read = reader.read(buffer, 0, buffer.length)) > 0) {
                builder.append(buffer, 0, read);
            }
            return builder.toString();
        } finally {
            stream.close();
        }
    }

    public  static boolean isPageLoadedSuccess(WebDriverWait explicitWait, WebDriver driver, JavascriptExecutor jsExcutor) {
        explicitWait = new WebDriverWait(driver, Duration.ofSeconds(15));
        jsExcutor = (JavascriptExecutor) driver;
        JavascriptExecutor finalJsExcutor = jsExcutor;
        ExpectedCondition<Boolean> jQueryLoad = new ExpectedCondition<Boolean>(){
            @Override
            public Boolean apply(WebDriver input) {
                return (Boolean) finalJsExcutor.executeScript("return (window.jQuery != null) && (jQuery.active === 0);");
            }
        };

        ExpectedCondition<Boolean> jsLoad = new ExpectedCondition<Boolean>(){
            @Override
            public Boolean apply(WebDriver input) {
                return finalJsExcutor.executeScript("return document.readyState").toString().equals("complete");
            }
        };
        return explicitWait.until(jQueryLoad) && explicitWait.until(jsLoad);
    }
}
