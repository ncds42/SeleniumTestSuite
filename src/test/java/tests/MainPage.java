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

public class MainPage {

	@Test
	public void MainPageTest() {
		WebDriver driver = DriverFactory.getDriver();
		Wait<WebDriver> wait = DriverFactory.getWait(driver);

		driver.get("http://alistgear.com");
		wait.until(ExpectedConditions.presenceOfElementLocated(By.className("pt13")));

		WebElement joinUsButton = driver.findElement(By.className("main_container"));
		joinUsButton.click();
		wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.id("signUp")));

		WebElement signUp = driver.findElement(By.id("signUp"));
        signUp.click();

        wait.until(ExpectedConditions.presenceOfElementLocated(By.class("user"));
		

		Assert.assertFalse(true, "SignUp Page working");
	}
}
