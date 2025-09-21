package pages;

import java.time.Duration;

import org.aeonbits.owner.ConfigFactory;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.pagefactory.AjaxElementLocatorFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import utils.testConfig;

public class BasePage {
	protected final WebDriver driver;
	protected final testConfig cfg = ConfigFactory.create(testConfig.class, System.getProperties());

	protected BasePage(WebDriver driver) {
		this.driver = driver;
		int timeoutSec = cfg.explicitWaitSec();
		PageFactory.initElements(new AjaxElementLocatorFactory(driver, timeoutSec), this);
	}

	public void absoluteOpen(String url) {
		driver.get(url);
	}

	public void open(String path) {
		driver.get(cfg.baseUrl() + path);
	}
	
	public void getWaitForVisibility(WebElement element) {
		
		WebDriverWait wait=new WebDriverWait(driver, Duration.ofSeconds(15));
		wait.until(ExpectedConditions.visibilityOf(element));
	}
	
//	public WebDriver getDriver() {
//		return driver;
//	}
}
