

# The purpose of this page is document all the steps it takes to set up an effective Selenium Suite with all features including:
This application is a parallel test suite using Selenium WebDriver built in Java for the purpose of deploying to an AWS hosted Selenium Grid. 
- Thread-safe Parallel Testing
- Selenium Grid for a remote testing hub
- AWS hosting for Selenium Hub and Nodes
- Shell scripts to clean up stale nodes and fresh reboot of the whole Grid
- Jenkins server for kicking off whole test-suite. Also monitors bug reports and screenshotting at the time of bug

### How to run project
1. Clone the Project
2. Install [Maven](https://www.javahelps.com/2017/10/install-apache-maven-on-linux.html) as a command-line tool.
3. Install [geckodriver](https://github.com/mozilla/geckodriver/), [chromedriver](https://sites.google.com/a/chromium.org/chromedriver/) or other [third-party driver](https://www.seleniumhq.org/download/) for the browser you are trying to test and include them in your PATH variable.
4. Run "mvn install" on the command-line.
5. ??
6. Profit


## Setting Up Selenium WebDriver with TestNG
Selenium WebDriver is the code that will translate Java into actions on the browser with the assistance of a driver.

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
The parallel atribute specifies whether the classes, methods or classes and methods will produce separate threads. The thread-count will specify the amount of threads to be created. Although the maximum thread-count on remote tests is 5 due to constraints with Selenium Grid. The listener will handle before and after methods. It will also be where xml parameters will be called in Java for testing different browsers. 
7. With this all set up, a simple `mvn test` will run the test-suite from the command-line. This will make configuring Jenkins much easier in the future.
  
## Selenium Grid
Selenium Grid is a server that will allow you to run tests on different remote machines (nodes) in parallel. This comes in handy when you want a test-suite to be always available and to run remotely. When scaling up the test-suite beyond the capacity of a single machine is when this really becomes invaluable. It also has a GUI called the grid console which allows you to monitor which nodes are active or in-use and how they were configured.

1. Download [Selenium Grid 3.141.59](https://bit.ly/2TlkRyu) jar
2. This should launch the hub.`java -jar selenium-server-standalone-3.141.59.jar -role hub`  Verify by hitting [localhost:4444/grid/console](localhost:4444/grid/console)
3. Now to open a node in a separate terminal `java -jar selenium-server-standalone-3.141.59.jar -role webdriver -hub http://localhost:4444/grid/register -port 5566` This node will pull resources from the machine its on and search the machine's PATH variable for test-drivers if not specified in-line.
4. Look into DriverFactory.java for more details on how to point WebDriver at the grid.
5. At this point, running the test-suite with the remote options will use the node's machine's resources to open test-drivers.

Without a proper setup, and ensuring resources are given back to the machine at the end of running tests (quitting drivers, etc.) this can create stale nodes which force manual rebooting of nodes. Selenium Grid has a time-out for a stale node task but one shouldn't rely on this. Clean tests are vital for having a conclusive test. 



## AWS Selenium Test
1. Sign-up for free AWS (requires credit card on file) and navigate to EC2 page. Startup a micro 1GB Ubuntu instance with the default settings and download key .pem file.
2. To connect to the instance, an ssh is required using the .pem.
```
chmod 400 AWS_Key.pem
ssh -i "AWS_Key.pem" ubuntu@ec2-1-333-111-22.us-east-2.compute.amazonaws.com
```
Once inside the new terminal name should be the name of some version of ubuntu@ip-xxx-xx-xx-xxx
3. Run the following commands to install all the necessary files
	1. Install Java, Maven and Unzip
	2. Download Selenium Server
	3. Download chromedriver
	4. Add chromedriver to PATH
	5. Install chromium for chromedriver to run against
	6. Clone Selenium Test Suite
	7. Run Maven
```
sudo apt-get update
sudo apt install default-jre maven unzip
cd home/ubuntu
mkdir Selenium
cd Selenium
wget -O selenium-server-standalone-3.141.59.jar https://bit.ly/2TlkRyu
wget -O chromedriver https://chromedriver.storage.googleapis.com/79.0.3945.16/chromedriver_linux64.zip
unzip -o chromedriver
echo "export PATH=$PATH:/home/ubuntu/chromedriver" > .bash_profile
sudo apt install chromium-chromedriver
git clone https://github.com/ncds42/SeleniumTestSuite.git
cd SeleniumTestSuite
mvn install
```
Run `mvn test`. At this point, this verifies the local AWS enviroment is setup properly.

## AWS Selenium Grid
After setting up the test environment, the setup for Grid can start.
1. Run `java -jar selenium-server-standalone-3.141.59.jar -role hub` to start the grid
2. In a separate ssh terminal run `java -jar selenium-server-standalone-3.141.59.jar -role webdriver -hub http://localhost:4444/grid/register -port 5566` to start a node.
3. In another separate ssh terminal. Edit testng.xml and change remote parate to true.
4. Run `mvn test`

This should work but is basically running a grid locally in the cloud. Now the goal is to send a test to the Grid from a local machine not in the cloud. The gridUrl parameter is pointed to localhost, so now it must changed to the EC2 server.

1. Go into Security Groups on the EC2 Management Console
2. Add Inbound Rule for TCP, Port 88, From Anywhere
3. Add Inbound Rule for TCP, Port 4444, From Anywhere
4. Use Public IP to check if grid access is open. For now, the Dummy Public IP will = '3.134.106.31' Replace with own
5. Run `3.134.106.31:4444/grid/console` on the browser to see if a webpage is returned.
6. Then change the gridUrl parameter in testng.xml to `3.134.106.31:4444/wd/hub`
7. Run `mvn test` on local machine and see if tests were registered on node terminal.

