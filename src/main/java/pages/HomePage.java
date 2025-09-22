package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.testng.Assert;

public class HomePage extends BasePage{
	
	public HomePage(WebDriver driver) {
		super(driver);
		// TODO Auto-generated constructor stub
	}

	@FindBy(xpath="//button[normalize-space()='Search buses']")
	private WebElement searchBox;
	
	@FindBy(xpath="//h1[@data-autoid='headerText']")
	private WebElement titleTextBox;
	
	@FindBy(xpath="//div[@style='--timer-dur: 5;']/div/div")
	private WebElement errorToastMessage;
	
	public void launchHomePage() {
		open("");
	}
	
	public void clickSearch() {
		searchBox.click();
	}
	
	public void validateErrorMessage() {
		
		getWaitForVisibility(errorToastMessage);
		
		String expectedErrorToastMessageText="Please enter source and destination";
		String errorToastMessageTextFromUI=errorToastMessage.getText();
		
		System.out.println("errorToastMessage - "+errorToastMessageTextFromUI);
		
		Assert.assertEquals(errorToastMessageTextFromUI, expectedErrorToastMessageText);
		
	}

	public void validateTitleText() {
		
		getWaitForVisibility(titleTextBox);
		
		String expectedTitleBoxText="India's No. 1 online bus ticket booking site";
		String titleBoxTextFromUI=titleTextBox.getText();
		
		System.out.println("titleBoxTextFromUI - "+titleBoxTextFromUI);
		
		Assert.assertEquals(titleBoxTextFromUI, expectedTitleBoxText);
	}
}
