package framework.utilities;

import java.io.File;
import java.net.URL;
import java.util.concurrent.TimeUnit;

import org.apache.log4j.Logger;
import org.openqa.selenium.remote.DesiredCapabilities;
import framework.pages.CalculatorPage;
import io.appium.java_client.MobileElement;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.service.local.AppiumDriverLocalService;
import io.appium.java_client.service.local.AppiumServiceBuilder;
import io.appium.java_client.service.local.flags.GeneralServerFlag;
import io.cucumber.java.Scenario;

/**
 * This class acts as a base class to all step definition class.
 * All the step definition classes will have drier and all page objects available to them on instantiation.
 * @author Ratnadip Chaudhuri
 */
public class BasePageSteps {
	
	
	private DesiredCapabilities serviceCapabilities;
	private AppiumDriverLocalService service;
	protected PropertiesFileReader propertiesInstance;
	protected static CalculatorPage calcPage;
	protected static AndroidDriver<MobileElement> driver;
	protected static FrameworkUtilities frameworkUtilities;
	protected static Scenario scenario;
	
	private Logger log = Logger.getLogger(BasePageSteps.class);
	
	public BasePageSteps() throws Throwable {
		propertiesInstance = PropertiesFileReader.getInstance();
		calcPage = new CalculatorPage();
		frameworkUtilities = new FrameworkUtilities();
	}
	
	public void setScenario(Scenario scenario) {
		this.scenario = scenario;
	}
	
	/**
	 * Starting the Appium Server
	 */
	public void startAppiumServer() {
		// Initialting Service capabilities
		AppiumServiceBuilder builder = new AppiumServiceBuilder();
		this.serviceCapabilities = new DesiredCapabilities();
		this.serviceCapabilities.setCapability("noReset", "false");
		builder.withIPAddress(propertiesInstance.getProperty("ServiceIpAddress"));
		builder.usingPort(Integer.parseInt(propertiesInstance.getProperty("ServicePort")));
		builder.withCapabilities(this.serviceCapabilities);
		builder.withArgument(GeneralServerFlag.LOG_LEVEL, propertiesInstance.getProperty("ServiceLogLevel"));
		builder.withArgument(GeneralServerFlag.SESSION_OVERRIDE);
		
		this.service = AppiumDriverLocalService.buildService(builder);
		
		// Launching Appium service
		this.service.start();
		log.info("============Started Appium Service============");
	}
	
	/**
	 * Stopping the Appium Server
	 */
	public void stopAppiumServer() {
		// Stopping Appium service
		if(this.service != null) {
			this.service.stop();
		}
		log.info("=============Stopped Appium Service============");
	}
	
	/**
	 * Setup Step - Launching the app in Android device before every test case
	 * @throws Throwable
	 */
	protected void launchApp() throws Throwable {
		if(driver == null) {
			// Initializing the capabilities and starting the Calculator app and starting the app for the first time.
			File apkFile = new File(propertiesInstance.getProperty("ApplicationFilePath"));
			DesiredCapabilities appCapabilities = new DesiredCapabilities();
			appCapabilities.setCapability("autoGrantPermissions", true);
			appCapabilities.setCapability("package", propertiesInstance.getProperty("AppPackage"));
			appCapabilities.setCapability("platformName", propertiesInstance.getProperty("platformName"));
			appCapabilities.setCapability("platformVersion", propertiesInstance.getProperty("platformVersion"));
			appCapabilities.setCapability("app", apkFile.getAbsolutePath());
			appCapabilities.setCapability("appActivity", propertiesInstance.getProperty("AppActivity"));
			
			driver = new AndroidDriver<MobileElement>(new URL(String.format("http://%s:%s/wd/hub", 
				propertiesInstance.getProperty("ServiceIpAddress"), propertiesInstance.getProperty("ServicePort"))), 
				appCapabilities);
			driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
			
			// Install app if it is not installed - Could involve permission from the device
			if(!driver.isAppInstalled(propertiesInstance.getProperty("AppBundle"))) {
				driver.installApp(apkFile.getAbsolutePath());
			}
	
			// Initializing the page objects
			initializePageObjects();
			
			log.info("Completed initialization of Calculator.apk MobileDriver and first launch.....");
			
		} else {
			// Starting the app before every test run
			driver.launchApp();
			log.info("Launch the Calculator.apk app for running test.....");
		}
		
		log.info("Launched App in Android device.");
		
	}

	/**
	 * Initializing Page Objects
	 */
	private void initializePageObjects() {
		// TODO Auto-generated method stub
		calcPage = new CalculatorPage();
		log.info("Initialized all page objects.");
	}

	/**
	 * Teardown step - Close the app after every test case is executed
	 */
	protected void closeApp() {
		driver.closeApp();
		log.info("Closing the Calculator.apk app");
	}
}
