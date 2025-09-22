package utils;

import io.cucumber.java.*;
import java.util.Base64;

public class hooks {

    @Before(order = 0)
    public void initializeDriver() {
        DriverManager.initDriver();
    }

    @Before(order = 1)
    public void logScenarioStart(Scenario scenario) {
        System.out.println("Starting scenario: " + scenario.getName());
    }

    @AfterStep
    public void addScreenshot(Scenario scenario) {
        String screenshotBase64 = ScreenshotUtils.getBase64Screenshot();
        if (screenshotBase64 == null || screenshotBase64.isEmpty()) {
            return;
        }

        byte[] screenshotBytes = Base64.getDecoder().decode(screenshotBase64);
        String screenshotName = scenario.isFailed() ? "Failed step screenshot" : "Step screenshot";
        scenario.attach(screenshotBytes, "image/png", screenshotName);
    }

    @After(order = 0)
    public void tearDown(Scenario scenario) {
        if (scenario.isFailed()) {
            System.out.println("Scenario failed: " + scenario.getName());
        } else {
            System.out.println("Scenario passed: " + scenario.getName());
        }
        DriverManager.quitDriver();
    }
}
