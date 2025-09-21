package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.testng.Assert;

import com.aventstack.extentreports.markuputils.ExtentColor;
import com.aventstack.extentreports.markuputils.MarkupHelper;

import utils.hooks;

public class HomePage extends BasePage{
	
	public HomePage(WebDriver driver) {
		super(driver);
		// TODO Auto-generated constructor stub
	}

	@FindBy(xpath="//button[normalize-space()='Search buses']")
	private WebElement searchBox;
	
	@FindBy(xpath="//h1[@data-autoid='headerText']")
	private WebElement titleTextBox;
	
	
	public void launchHomePage() {
		open("");
	}
	
	public void clickSearch() {
		searchBox.click();
	}

	public void validateTitleText() {
		
		getWaitForVisibility(titleTextBox);
		
		String expectedTitleBoxText="India's No. 1 online bus ticket booking site";
		String titleBoxTextFromUI=titleTextBox.getText();
		
		System.out.println("titleBoxTextFromUI - "+titleBoxTextFromUI);
		hooks.getTest().info(MarkupHelper.createLabel("titleBoxTextFromUI = " + titleBoxTextFromUI, ExtentColor.BLUE));
		
		Assert.assertEquals(titleBoxTextFromUI, expectedTitleBoxText);
	}
}
