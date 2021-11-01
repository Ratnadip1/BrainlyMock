package stepdefs;


import org.apache.log4j.Logger;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;

import framework.utilities.BasePageSteps;
import interfaces.IServiceHook;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;

/**
 * Setup and teardown implementation for each scenario
 * @author Ratnadip Chaudhuri
 */
public class ServiceHooks extends BasePageSteps implements IServiceHook {
	
	private Logger log = Logger.getLogger(ServiceHooks.class);
	
	public ServiceHooks() throws Throwable{
	}
	
	@Before
	public void setUp(Scenario scenario) {
		// Launching application before every test scenario
		try {
			launchApp();
			setScenario(scenario);
		}catch(Throwable t) {
			t.printStackTrace();
			log.error("Error occurred during setup of test scenario.");
		}
	}
	
	@After
	public void tearDown(Scenario scenario) {
		// Screenshot on scenario failure
		if(scenario.isFailed()) {
			final byte[] screenshot = ((TakesScreenshot)driver).getScreenshotAs(OutputType.BYTES);
			scenario.attach(screenshot, "image/png", scenario.getName());
		}
		// Closing application after every test scenario
		closeApp();
		
	}

}
