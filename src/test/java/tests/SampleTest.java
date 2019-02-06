package tests;

import java.net.MalformedURLException;
import java.net.URL;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.Assert;
import org.testng.annotations.*;
import org.testng.annotations.Test;

import managers.DriverFactory;

public class SampleTest {
	
//	@Parameters("gridUrl")
//	@Test
//	public void indie_test(String url) {
//		ChromeOptions options = new ChromeOptions();
//		options.addArguments("--start-maximized");
//		options.addArguments("--ignore-certificate-errors");
//		options.addArguments("--disable-popup-blocking");
//		options.setCapability("platform", "LINUX");
//		options.addArguments("--incognito");
//		WebDriver driver;
//		try {
//			System.out.println("grid hub = " + url);
//			driver = new RemoteWebDriver(new URL(url), options);
//			driver.navigate().to("https://www.google.com");
//			Assert.assertTrue(driver.getTitle().equalsIgnoreCase("Google"));
//			driver.quit();
//		} catch (MalformedURLException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//		
//	}
//	@Test
//	public void test() {
//		System.out.println("randomchange");
//		WebDriver driver = DriverFactory.getDriver();
//		driver.navigate().to("https://www.google.com");
//		Assert.assertTrue(driver.getTitle().equalsIgnoreCase("Google"));
//	}
	
	
	@Test
	public void test1() {
		WebDriver driver = DriverFactory.getDriver();
		driver.navigate().to("https://www.google.com");
		Assert.assertTrue(driver.getTitle().equalsIgnoreCase("Google"));
	}
	
	@Test
	public void test2() {
		WebDriver driver = DriverFactory.getDriver();
		driver.navigate().to("https://www.google.com");
		Assert.assertTrue(driver.getTitle().equalsIgnoreCase("Google"));
	}
	
	@Test
	public void test3() {
		WebDriver driver = DriverFactory.getDriver();
		driver.navigate().to("https://www.google.com");
		Assert.assertTrue(driver.getTitle().equalsIgnoreCase("Google"));
	}
	
	@Test
	public void test4() {
		WebDriver driver = DriverFactory.getDriver();
		driver.navigate().to("https://www.google.com");
		Assert.assertTrue(driver.getTitle().equalsIgnoreCase("Google"));
	}
	
	@Test
	public void test5() {
		WebDriver driver = DriverFactory.getDriver();
		driver.navigate().to("https://www.google.com");
		Assert.assertTrue(driver.getTitle().equalsIgnoreCase("Google"));
	}
	
	@Test
	public void test6() {
		WebDriver driver = DriverFactory.getDriver();
		driver.navigate().to("https://www.google.com");
		Assert.assertTrue(driver.getTitle().equalsIgnoreCase("Google"));
	}
	

}
