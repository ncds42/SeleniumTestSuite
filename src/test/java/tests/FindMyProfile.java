package tests;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

import java.net.MalformedURLException;
import java.net.URL;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Wait;
import org.testng.Assert;
import org.testng.annotations.*;
import org.testng.annotations.Test;

import managers.DriverFactory;

public class FindMyProfile {

	@Test
	public void findMyProfileTest() {
		WebDriver driver = DriverFactory.getDriver();
		Wait<WebDriver> wait = DriverFactory.getWait(driver);

		driver.get("http://alistgear.com");
		wait.until(ExpectedConditions.presenceOfElementLocated(By.className("joinUsBt")));

		driver.findElement(By.className("my_profile-bt")).click();
		
		wait.until(ExpectedConditions.presenceOfElementLocated(By.id("fname")));

		WebElement fname, lname;
		fname = driver.findElement(By.id("fname"));
		lname = driver.findElement(By.id("lname"));

		String knownFname = "Nitish";
		String knownLname = "Chauhan";

		System.out.println("Searching known user: " + knownFname + " " + knownLname);
		fname.sendKeys("Nitish");
		lname.sendKeys("Chauhan");
		
		driver.findElement(By.id("profileme")).click();
		
		try {
			wait.until(ExpectedConditions.presenceOfElementLocated(By.id("success-alert")));
			Assert.assertTrue(false, "User" + "[" + knownFname + " " + knownLname + "]" + "not found!");
		}catch(Exception e) {}
		
	}

}
