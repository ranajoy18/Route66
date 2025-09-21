package utils;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import utils.DriverManager;

import java.util.Base64;

public class ScreenshotUtils {

    public static String getBase64Screenshot() {
        WebDriver driver = DriverManager.get();
        return ((TakesScreenshot) driver).getScreenshotAs(OutputType.BASE64);
    }
}
