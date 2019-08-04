package managers;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.firefox.FirefoxProfile;
import org.openqa.selenium.remote.RemoteWebDriver;

public class DriverOptions {
	
	public ChromeOptions getChromeOptions() {
		ChromeOptions options = new ChromeOptions();
		System.setProperty("webdriver.chrome.args", "--disable-logging");
		System.setProperty("webdriver.chrome.silentOutput", "true");
		options.addArguments("--start-maximized"); // This will help with viewport problems
		options.addArguments("--ignore-certificate-errors");
		options.addArguments("--disable-popup-blocking"); // Popups will change the focus so disabled is best
		options.setCapability("platform", "LINUX"); 
		options.addArguments("--incognito"); // Cookies will sometimes change the view of the page so disable
		options.addArguments("--silent");
		options.addArguments("--log-level=3");
		return options;
	}
	
	public FirefoxOptions getFirefoxOptions() {
		FirefoxOptions options = new FirefoxOptions();
		FirefoxProfile profile = new FirefoxProfile();
		profile.setAcceptUntrustedCertificates(true);
		profile.setAssumeUntrustedCertificateIssuer(false);
		profile.setPreference("network.proxy.type", 0);
		options.setProfile(profile);
		
		options.addPreference("--log", "error");
		System.setProperty(FirefoxDriver.SystemProperty.DRIVER_USE_MARIONETTE,"true");
		System.setProperty(FirefoxDriver.SystemProperty.BROWSER_LOGFILE,"/dev/null");

		return options;
	}
	
	public ChromeOptions getHeadlessChromeOptions() {
		ChromeOptions options = new ChromeOptions();
		System.setProperty("webdriver.chrome.args", "--disable-logging");
		System.setProperty("webdriver.chrome.silentOutput", "true");
		options.addArguments("--headless");
		options.addArguments("--start-maximized");
		options.addArguments("--ignore-certificate-errors");
		options.addArguments("--disable-popup-blocking");
		options.setCapability("platform", "LINUX");
		options.addArguments("--incognito");
		options.addArguments("--silent");
		options.addArguments("--log-level=3");
		return options;
	}

}
