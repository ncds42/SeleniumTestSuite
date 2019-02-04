package tests;

import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.Test;

import managers.DriverFactory;

public class SampleTest {
	
	
	
	@Test
	public void test() {
		WebDriver driver = DriverFactory.getDriver();
		driver.navigate().to("https://www.google.com");
		Assert.assertTrue(driver.getTitle().equalsIgnoreCase("Google"));
	}
	
//	@Test
//	public void test1() {
//		WebDriver driver = DriverFactory.getDriver();
//		driver.navigate().to("https://www.google.com");
//		Assert.assertTrue(driver.getTitle().equalsIgnoreCase("Google"));
//	}
//	
//	@Test
//	public void test2() {
//		WebDriver driver = DriverFactory.getDriver();
//		driver.navigate().to("https://www.google.com");
//		Assert.assertTrue(driver.getTitle().equalsIgnoreCase("Google"));
//	}
//	
//	@Test
//	public void test3() {
//		WebDriver driver = DriverFactory.getDriver();
//		driver.navigate().to("https://www.google.com");
//		Assert.assertTrue(driver.getTitle().equalsIgnoreCase("Google"));
//	}
//	
//	@Test
//	public void test4() {
//		WebDriver driver = DriverFactory.getDriver();
//		driver.navigate().to("https://www.google.com");
//		Assert.assertTrue(driver.getTitle().equalsIgnoreCase("Google"));
//	}
//	
//	@Test
//	public void test5() {
//		WebDriver driver = DriverFactory.getDriver();
//		driver.navigate().to("https://www.google.com");
//		Assert.assertTrue(driver.getTitle().equalsIgnoreCase("Google"));
//	}
//	
//	@Test
//	public void test6() {
//		WebDriver driver = DriverFactory.getDriver();
//		driver.navigate().to("https://www.google.com");
//		Assert.assertTrue(driver.getTitle().equalsIgnoreCase("Google"));
//	}
//	

}
