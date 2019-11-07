### How to run project
1. Clone the Project
2. Install [Maven](https://www.javahelps.com/2017/10/install-apache-maven-on-linux.html) as a command-line tool.
3. Install [geckodriver](https://github.com/mozilla/geckodriver/), [chromedriver](https://sites.google.com/a/chromium.org/chromedriver/) or other [third-party driver](https://www.seleniumhq.org/download/) for the browser you are trying to test and include them in your PATH variable.
4. Run "mvn install" on the command-line.
5. ??
6. Profit

# The purpose of this page is document all the steps it takes to set up an effective Selenium Suite with all features including:
- Thread-safe Parallel Testing
- Selenium Grid for a remote testing hub
- AWS hosting for Selenium Hub and Nodes
- Shell scripts to clean up stale nodes and fresh reboot of the whole Grid
- Jenkins server for kicking off whole test-suite. Also monitors bug reports and screenshotting at the time of bug

## Setting Up Selenium WebDriver with TestNG
1. Install [Maven](https://www.javahelps.com/2017/10/install-apache-maven-on-linux.html)
2. Download [Selenium WebDriver Dependency](https://mvnrepository.com/artifact/org.seleniumhq.selenium/selenium-java) from maven repo and put in pom.xml.
3. Install [geckodriver](https://github.com/mozilla/geckodriver/), [chromedriver](https://sites.google.com/a/chromium.org/chromedriver/) or other [third-party driver](https://www.seleniumhq.org/download/) for the browser you are trying to test and include them in your PATH variable.
4. This can be done with JUnit but TestNG is easier and more reliable for parallel testing. So if using TestNG, now is when you add the dependency to your pom.xml.
5. Add this maven-surefire-plugin inside the <plugins> portion of the pom.xml to allow maven to be able to kick off TestNG tests instead of having to depend on the IDE. This allows everything to be done from the command-line, this will pay off later.
```
<plugin>
  <groupId>org.apache.maven.plugins</groupId>
  <artifactId>maven-surefire-plugin</artifactId>
  <version>2.14.1</version>
  <configuration>
    <suiteXmlFiles>
      <suiteXmlFIle>testng.xml</suiteXmlFile>
    </suiteXmlFiles>
  </configuation>
</plugin>
  ```
6. Add 'testng.xml' to project directory.
```
  <?xml version="1.0" encoding="UTF-8"?>
<!DOCTYPE suite SYSTEM "http://testng.org/testng-1.0.dtd">
<suite thread-count="1" name="Suite" parallel="methods">
	<listeners>
		<listener class-name="managers.InvokedMethodListener"></listener>
	</listeners>
	<test name="00000" parallel="methods" thread-count="5">
		<parameter name="browser" value="chrome" />
		<parameter name="remote"  value="false"/>
		<parameter name="gridUrl" value="http://3.17.201.180:4444/wd/hub"/>
		<classes>
			<class name="tests.SampleTest" /> 
		</classes>
	</test>
</suite>
```
The parallel atribute specifies whether the classes, methods or classes and methods will produce separate threads. The thread-count will specify the amount of threads to be created. The listener will handle before and after methods. It will also be where parameters will be called. 
  
## Selenium Grid

This application is a parallel test suite using Selenium WebDriver for the purpose of deploying to an AWS hosted Selenium Grid. 


