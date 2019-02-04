package managers;

import org.openqa.selenium.WebDriver;
import org.testng.IInvokedMethod;
import org.testng.IInvokedMethodListener;
import org.testng.ITestResult;

public class InvokedMethodListener implements IInvokedMethodListener {

	public void beforeInvocation(IInvokedMethod method, ITestResult testResult) {
		if (method.isTestMethod()) {
			System.out.println("Test Method BeforeInvocation is started. " + Thread.currentThread().getId());
			String browserName = method.getTestMethod().getXmlTest().getLocalParameters().get("browser");
			Boolean remote = Boolean
					.parseBoolean(method.getTestMethod().getXmlTest().getLocalParameters().get("remote"));
			if (remote) {
				String url = method.getTestMethod().getXmlTest().getLocalParameters().get("gridUrl");
				DriverFactory.setDriver(browserName,url);
			} else {
				DriverFactory.setDriver(browserName);
			}
		}
	}

	public void afterInvocation(IInvokedMethod method, ITestResult testResult) {
		if (method.isTestMethod()) {
			System.out.println("Test Method AfterInvocation is started. " + Thread.currentThread().getId());
			WebDriver driver = DriverFactory.getDriver();
			if (driver != null) {
				driver.quit();
			}
		}
	}
}