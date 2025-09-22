package stepDefinations;

import org.openqa.selenium.WebDriver;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import pages.HomePage;
import utils.DriverManager;

public class sampleSD {
	private final WebDriver driver=DriverManager.get();
	private final HomePage homePage=new HomePage(driver);
	
	@Given("I am on RedBus homePage")
	public void i_am_on_red_bus_home_page() {
		homePage.launchHomePage();
	}
	
	@Given("Validate if the home page loads fine")
	public void validate_if_the_home_page_loads_fine() {
		homePage.validateTitleText();
	}
	
	@Then("Click on Search button")
	public void click_on_search_button() {
		homePage.clickSearch();
	}
	@Then("Validate error toast message")
	public void validate_error_toast_message() {
		homePage.validateErrorMessage();
	}
}
