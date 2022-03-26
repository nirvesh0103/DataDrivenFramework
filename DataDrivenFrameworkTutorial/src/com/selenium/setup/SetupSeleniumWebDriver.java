package com.selenium.setup;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import io.github.bonigarcia.wdm.WebDriverManager;

public class SetupSeleniumWebDriver {
	public static WebDriver browserInstance = null;

	/**
	 * Function to set the system property for the given browser drivers.
	 * 
	 * @param browserName Type - String, Values - "firefox", "edge"
	 */
	public static void setBrowserDriver(String browserName) {
		String browserDriverDir = System.getProperty("user.dir") + "\\browserDrivers";
		switch (browserName) {
		case "firefox":
			System.setProperty("webdriver.gecko.driver", browserDriverDir + "\\geckodriver.exe");
			break;
		case "edge":
			System.setProperty("webdriver.edge.driver", browserDriverDir + "\\msedgedriver.exe");
			break;
		default:
			System.setProperty("webdriver.chrome.driver", browserDriverDir + "\\chromedriver.exe");
			break;
		}

	}

	/**
	 * Function to launch the browser
	 * 
	 * @param browserName - Type String - Values - "firefox", "edge"
	 * @return Type WebDriver - returns the WebDriver instance.
	 */
	public static WebDriver launchBrowser(String browserName) {

		switch (browserName) {
		case "firefox":
			browserInstance = new FirefoxDriver();
			break;
		case "edge":
			browserInstance = new EdgeDriver();
			break;
		default:
			browserInstance = new ChromeDriver();
			break;
		}
		browserInstance.manage().window().maximize();
		return browserInstance;
	}

	/**
	 * Function to launch a browser using WebDriverManager.
	 * 
	 * @param browserName: Type String - Values : firefox, ege
	 * @return Type WebDriver - returns the WebDriver instance.
	 */
	public static WebDriver launchBrowserUsingWDM(String browserName) {

		switch (browserName) {
		case "firefox":
			WebDriverManager.firefoxdriver().setup();
			browserInstance = new FirefoxDriver();
			break;
		case "edge":
			WebDriverManager.edgedriver().setup();
			browserInstance = new EdgeDriver();
			break;
		default:
			WebDriverManager.chromedriver().setup();
			browserInstance = new ChromeDriver();
			break;
		}
		return browserInstance;
	}
}