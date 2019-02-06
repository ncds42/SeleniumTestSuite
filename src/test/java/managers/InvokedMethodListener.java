package managers;

import java.io.File;
import java.io.IOException;
import java.sql.Timestamp;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.io.FileHandler;
import org.testng.IInvokedMethod;
import org.testng.IInvokedMethodListener;
import org.testng.ITestResult;

public class InvokedMethodListener implements IInvokedMethodListener {

	public void beforeInvocation(IInvokedMethod method, ITestResult testResult) {
		if (method.isTestMethod()) {
//			System.out.println("New Thread Started " + Thread.currentThread().getId());
			String browserName = method.getTestMethod().getXmlTest().getLocalParameters().get("browser");
			Boolean remote = Boolean
					.parseBoolean(method.getTestMethod().getXmlTest().getLocalParameters().get("remote"));
			if (remote) {
				String url = method.getTestMethod().getXmlTest().getLocalParameters().get("gridUrl");
				DriverFactory.setDriver(browserName, url);
			} else {
				DriverFactory.setDriver(browserName);
			}
		}
	}

	public void afterInvocation(IInvokedMethod method, ITestResult testResult) {
		if (method.isTestMethod()) {
//			System.out.println("Current Thread ended " + Thread.currentThread().getId());
			WebDriver driver = DriverFactory.getDriver();
			System.out.println();

			if (testResult.getStatus() == ITestResult.SUCCESS) {
				System.out.println(method.getTestMethod().getMethodName() + " was successful!");
			} else if (testResult.getStatus() == ITestResult.FAILURE) {
				System.out.println(method.getTestMethod().getMethodName() + " failed!");
				System.out.println(testResult.getThrowable().getMessage());

				File src = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
				try {
					String dest = "/home/ubuntu/testSuitePictures/";
					String fileName = new Timestamp(System.currentTimeMillis()).toString();
					fileName = fileName.replaceAll("-","");
					fileName = fileName.replaceAll(" ","");
					fileName = fileName.replaceAll(":","");
					fileName = fileName.replaceAll("\\.","");
					fileName = fileName + ".png";
					System.out.println("Screenshot taken: " + fileName);
					FileHandler.copy(src, new File(dest+fileName));
				} catch (IOException e) {
				}

			}
			if (driver != null) {
				driver.quit();
			}
		}
	}
}