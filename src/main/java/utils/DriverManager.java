package utils;

import org.openqa.selenium.MutableCapabilities;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.remote.RemoteWebDriver;

import java.net.URI;
import java.time.Duration;

import org.aeonbits.owner.ConfigFactory;

public class DriverManager {
	private static final ThreadLocal<WebDriver> DRIVER = new ThreadLocal<>();
	private static final testConfig cfg = ConfigFactory.create(testConfig.class, System.getProperties());
	
	//Initialize WebDriver
	public static void initDriver() {
		if (DRIVER.get() != null)
			return;

		String browser = cfg.browser().toLowerCase();
		WebDriver driver;

		switch (browser) {
		case "firefox": {
			FirefoxOptions options = new FirefoxOptions();
			if (cfg.headless())
				options.addArguments("-headless");
			driver = new FirefoxDriver(options);
			break;
		}
		case "edge": {
			EdgeOptions options = new EdgeOptions();
			if (cfg.headless())
				options.addArguments("--headless=new");
			driver = new EdgeDriver(options);
			break;
		}
		default: {
			ChromeOptions options = new ChromeOptions();
			if (cfg.headless())
				options.addArguments("--headless=new");
			// Helpful flags for CI stability
			options.addArguments("--no-sandbox", "--disable-dev-shm-usage", "--disable-gpu");
			driver = new ChromeDriver(options);
		}
		}

		// Used in case of RemoteBrowser like BrowserStack in play
		if (cfg.gridUrl() != null && !cfg.gridUrl().isBlank()) {
			MutableCapabilities caps = ((MutableCapabilities) ((RemoteWebDriver) driver).getCapabilities());
			try {
				driver = new RemoteWebDriver(URI.create(cfg.gridUrl()).toURL(), caps);
			} catch (Exception e) {
				throw new RuntimeException("Bad gridUrl: " + cfg.gridUrl(), e);
			}
		}

		if (cfg.implicitWaitMs() > 0) {
			driver.manage().timeouts().implicitlyWait(Duration.ofMillis(cfg.implicitWaitMs()));
		}
		driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(cfg.explicitWaitSec()));
		driver.manage().window().maximize();
		
		DRIVER.set(driver);
	}
	
	//Fetch Driver
	public static WebDriver get() {
		return DRIVER.get();
	}
	
	//TearDown
	public static void quitDriver() {
		WebDriver d= DRIVER.get();
		d.quit();
		DRIVER.remove();
	}

}