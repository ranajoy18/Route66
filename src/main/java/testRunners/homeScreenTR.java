package testRunners;

import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;
import org.testng.annotations.DataProvider;

@CucumberOptions(
		features="src/main/resources/Features/homeScreen.feature",
		glue={"stepDefinations","utils"},
		plugin={"pretty",
				"html:target/cucumber-reports.html",
				"json:target/cucumebrJson",
				"rerun:target/failedScenarios.txt"
				},
		monochrome=false,
		tags="@demo"
		)
public class homeScreenTR extends AbstractTestNGCucumberTests{
	
	@Override
	@DataProvider(parallel=true) //enable parallel execution
	public Object[][] scenarios(){
		return super.scenarios();
	}

}
