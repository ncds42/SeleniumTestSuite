package tests;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

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

	@Test
	public void Test1() {
		WebDriver driver = DriverFactory.getDriver();
		driver.navigate().to("https://www.google.com");
		Assert.assertTrue(driver.getTitle().equalsIgnoreCase("Google"));
	}
	
	@Test
	public void Test2() {
		WebDriver driver = DriverFactory.getDriver();
		driver.navigate().to("https://news.ycombinator.com/");
		assertEquals(driver.getTitle(),"Google","The title didn't match. ");

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
