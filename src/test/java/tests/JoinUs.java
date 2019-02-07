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

public class JoinUs {

	@Test
	public void joinUsTest() {
		System.out.print("\n\n");
		System.out.println("-------------------------------------");
		System.out.println("Join Us Test begun..");
		WebDriver driver = DriverFactory.getDriver();
		Wait<WebDriver> wait = DriverFactory.getWait(driver);

		driver.get("http://alistgear.com");
		wait.until(ExpectedConditions.presenceOfElementLocated(By.className("joinUsBt")));
		System.out.println("Reached alistgear.com");

		WebElement joinUsButton = driver.findElement(By.className("join_us-bt"));
		joinUsButton.click();
		wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.id("address")));
		System.out.println("Join Us Form reached");

		WebElement city = driver.findElement(By.id("city"));
		city.sendKeys("Huntsville");
		WebElement name = driver.findElement(By.id("name"));
		name.sendKeys("Nitish Chauhan");
		WebElement email = driver.findElement(By.id("email"));
		email.sendKeys("ncds42@gmail.com");
		WebElement affiliation = driver.findElement(By.id("affiliation"));
		affiliation.sendKeys("tester");
		WebElement companyAffiliation = driver.findElement(By.id("company"));
		companyAffiliation.sendKeys("none");
		WebElement areasOfInterest = driver.findElement(By.id("areaOi"));
		areasOfInterest.sendKeys("selenium");
		System.out.println("Forms filled");

		driver.findElement(By.id("email-submit")).click();
		System.out.println("New user submit attempted");

		Assert.assertFalse(true, "No confirmation email sent");

	}
}
