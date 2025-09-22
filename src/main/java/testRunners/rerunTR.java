package testRunners;

import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;
import org.testng.annotations.DataProvider;

@CucumberOptions(
        features = "@target/failedScenarios.txt",
        glue = {"stepDefinations", "utils"},
        plugin = {"pretty",
                "html:target/rerun-cucumber-reports.html",
                "json:target/rerun-cucumebrJson",
                "rerun:target/failedScenarios.txt"
        },
        monochrome = false
)
public class rerunTR extends AbstractTestNGCucumberTests {

    @Override
    @DataProvider(parallel = true)
    public Object[][] scenarios() {
        return super.scenarios();
    }
}
