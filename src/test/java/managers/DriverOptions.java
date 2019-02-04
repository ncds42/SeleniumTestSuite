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
		options.addArguments("--start-maximized");
		options.addArguments("--ignore-certificate-errors");
		options.addArguments("--disable-popup-blocking");
		options.setCapability("platform", "LINUX");
		options.addArguments("--incognito");
		return options;
	}
	
	public FirefoxOptions getFirefoxOptions() {
		FirefoxOptions options = new FirefoxOptions();
		FirefoxProfile profile = new FirefoxProfile();
		// Accept Untrusted Certificates
		profile.setAcceptUntrustedCertificates(true);
		profile.setAssumeUntrustedCertificateIssuer(false);
		// Use No Proxy Settings
		profile.setPreference("network.proxy.type", 0);
		// Set Firefox profile to capabilities
		options.setProfile(profile);

		return options;
	}
	
	public ChromeOptions getHeadlessChromeOptions() {
		ChromeOptions options = new ChromeOptions();
		options.addArguments("--headless");
		options.addArguments("--ignore-certificate-errors");
		options.addArguments("--disable-popup-blocking");
		options.setCapability("platform", "LINUX");
		options.addArguments("--incognito");
		return options;
	}

}
