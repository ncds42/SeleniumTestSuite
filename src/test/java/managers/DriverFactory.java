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

	private static DriverOptions optionsManager = new DriverOptions();
	private static ThreadLocal<WebDriver> tlDriver = new ThreadLocal<WebDriver>();

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

	public static synchronized WebDriverWait getWait(WebDriver driver) {
		return new WebDriverWait(driver, 20);
	}

	public static synchronized WebDriver getDriver() {
		return tlDriver.get();
	}
}