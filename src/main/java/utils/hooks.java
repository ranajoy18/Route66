package utils;

import io.cucumber.java.After;
import io.cucumber.java.Before;

public class hooks {

	@Before(order = 0)
	public void initializeDriver() {
		DriverManager.initDriver();
	}

	@After(order=0)
	public void tearDown() {
		DriverManager.quitDriver();
	}
}
