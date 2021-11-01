package runner;

import org.apache.log4j.Logger;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.runner.RunWith;

import framework.utilities.BasePageSteps;
import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import io.cucumber.junit.CucumberOptions.SnippetType;

@RunWith(Cucumber.class)
@CucumberOptions(features = {"src/test/resources/features"}, glue = {"stepdefs"},
snippets = SnippetType.CAMELCASE, tags = "@calc", plugin = {"pretty", "html:target/cucumber-pretty/cucumber-report.html", "json:target/cucumber-reports/CucumberTestReport.json"})
public class TestRunnerCalculator {
	
	private static Logger log = Logger.getLogger(TestRunnerCalculator.class);
	
	private static BasePageSteps baseSteps;
	
	public TestRunnerCalculator() throws Throwable {
	}
	
	@BeforeClass
	public static void frameworkSetup() throws Throwable {
		try {
			baseSteps = new BasePageSteps();
			
			baseSteps.startAppiumServer();
		}catch(Exception ex) {
			ex.printStackTrace();
			log.error("Error occurred while starting the Appium server.");
		}
	}
	
	@AfterClass
	public static void frameworkTeardown() {
		try {
			baseSteps.stopAppiumServer();
		}catch(Exception ex) {
			ex.printStackTrace();
			log.error("Error occurred while stopping the Appium server.");
		}
	}

}
