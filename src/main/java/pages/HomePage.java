package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class HomePage extends BasePage{
	
	public HomePage(WebDriver driver) {
		super(driver);
		// TODO Auto-generated constructor stub
	}

	@FindBy(xpath="//button[normalize-space()='Search buses']")
	private WebElement searchBox;
	
	
	public void launchHomePage() {
		open("");
	}
	
	public void clickSearch() {
		searchBox.click();
	}
}
