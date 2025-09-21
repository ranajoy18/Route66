package utils;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import io.cucumber.java.*;

public class hooks {

    private static ExtentReports extent;
    private static ThreadLocal<ExtentTest> test = new ThreadLocal<>();

    @BeforeAll
    public static void setupReport() {
        ExtentSparkReporter reporter = new ExtentSparkReporter("extent-report.html");
        extent = new ExtentReports();
        extent.attachReporter(reporter);
    }

    @Before(order = 0)
    public void initializeDriver() {
        DriverManager.initDriver();
    }

    @Before(order = 1)
    public void startScenario(Scenario scenario) {
        ExtentTest extentTest = extent.createTest(scenario.getName());
        test.set(extentTest);
    }

    @After(order = 0)
    public void tearDown(Scenario scenario) {
        if (scenario.isFailed()) {
            test.get().fail("Scenario failed: " + scenario.getName());
            // (Optional) attach screenshot here if you want
        } else {
            test.get().pass("Scenario passed");
        }
        DriverManager.quitDriver();
    }
    
    @AfterStep
    public void addScreenshot(Scenario scenario) {
        String screenshotBase64 = ScreenshotUtils.getBase64Screenshot();

        if (scenario.isFailed()) {
            getTest().fail("Step failed",
                com.aventstack.extentreports.MediaEntityBuilder
                    .createScreenCaptureFromBase64String(screenshotBase64, "Failed step screenshot")
                    .build()
            );
        } else {
            getTest().info("Step passed",
                com.aventstack.extentreports.MediaEntityBuilder
                    .createScreenCaptureFromBase64String(screenshotBase64, "Step screenshot")
                    .build()
            );
        }
    }

    @AfterAll
    public static void flushReport() {
        extent.flush();
    }

    // Expose ExtentTest to step defs
    public static ExtentTest getTest() {
        return test.get();
    }
}
