package managers;

import org.openqa.selenium.MutableCapabilities;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.net.MalformedURLException;
import java.net.URL;

public class DriverFactory {
	
	
	/* ThreadLocal will help Java lock each driver thread and not allow shared memory between threads */
	private static ThreadLocal<WebDriver> tlDriver = new ThreadLocal<WebDriver>();
	
	/* Calls on DriverOptions to set options based on browser + OS */
	private static DriverOptions optionsManager = new DriverOptions();

	/* IMPORTANT: **************************************************************
	 * It's important for all the DriverFactory object methods to be synchronized 
	 * to prevent any race conditions */
	
	
	/**
	 * @param driver This is will be supplied by the test after calling getDriver
	 * @return returns a new wait object 
	 */
	public static synchronized WebDriverWait getWait(WebDriver driver) {
		return new WebDriverWait(driver, 20);
	}
	
	/**
	 * This method is the main way of getting the current WebDriver by the tests and is to be called
	 * at the beginning of each test
	 * @return returns the driver to use for each unit test. 
	 */
	public static synchronized WebDriver getDriver() {
		return tlDriver.get();
	}
	
	/* This method is used by the InvokedMethodListener to set the Driver. This method should
	 * not be called in the actual test code 
	 * browser: */
	
	/**
	 * This is the setDriver method for remote testing using a Selenium Grid.
	 * @param browser name of the browser 'chrome','firefox'
	 * @param url This url must point to your Selenium Grid Hub. Ex: http://3.17.201.180:4444/wd/hub. 
	 * 			  Can be set in the testng.xml files as a parameter tag
	 */
	public static synchronized void setDriver(String browser, String url) {
		
		MutableCapabilities options;
		
		if (browser.equalsIgnoreCase("chrome")) {
			options = optionsManager.getChromeOptions();
		} else if (browser.equalsIgnoreCase("firefox")) {
			options = optionsManager.getFirefoxOptions();
		} else if (browser.equalsIgnoreCase("headlessChrome")) {
			options = optionsManager.getHeadlessChromeOptions();
		} else {
			throw new IllegalStateException("No browser set in testng.xml");
		}
		
		try {
			tlDriver.set(new RemoteWebDriver(new URL(url), options));
		} catch (MalformedURLException e) {
			e.printStackTrace();
		}

	}
	
	/**
	 * This is the setDriver method for local testing
	 * @param browser name of the browser 'chrome','firefox'
	 */
	public static synchronized void setDriver(String browser) {
		if (browser.equalsIgnoreCase("chrome")) {
			tlDriver.set(new ChromeDriver(optionsManager.getChromeOptions()));
		} else if (browser.equalsIgnoreCase("firefox")) {
			tlDriver.set(new FirefoxDriver(optionsManager.getFirefoxOptions()));
		} else if (browser.equalsIgnoreCase("headlessChrome")) {
			tlDriver.set(new ChromeDriver(optionsManager.getHeadlessChromeOptions()));
		} else {
			throw new IllegalStateException("No browser set in testng.xml");
		}

	}

}