package stepdefs;

import java.util.List;

import org.apache.log4j.Logger;
import org.junit.Assert;
import org.openqa.selenium.WebElement;

import framework.utilities.BasePageSteps;
import io.cucumber.datatable.DataTable;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

/**
 * Step Definition class for Calculator app.
 * @author Ratnadip Chaudhuri
 */
public class CalculatorSteps extends BasePageSteps{
	
	public CalculatorSteps() throws Throwable {
		super();
		// TODO Auto-generated constructor stub
	}
	
	private static Logger log = Logger.getLogger(CalculatorSteps.class);

	@Given("User launches the Calculator application")
	public void user_launches_the_calculator_application() {
	    // Write code here that turns the phrase above into concrete actions
		// TODO Validate Calculator app is launched successfully.
	    try {
			Assert.assertTrue("Calculator app is not launched.", calcPage.getFormulaTextView(driver).isDisplayed());
			log.info("Calculator app launched successfully.");
			scenario.log("Calculator app is launched successfully.");
		}catch(Exception ex) {
			ex.printStackTrace();
			scenario.log("Error occurred while launching the Calculator app.");
			log.error(String.format("Error occurred while validating the app is launched successfully: %s", ex.getMessage()));
			Assert.fail(String.format("Error occurred while validating the app is launched successfully: %s", ex.getMessage()));
		}
	}
	
	@When("User adds the numbers")
	public void user_adds_the_numbers(DataTable dataTable) {
	    // Write code here that turns the phrase above into concrete actions
	    // For automatic transformation, change DataTable to one of
	    // E, List<E>, List<List<E>>, List<Map<K,V>>, Map<K,V> or
	    // Map<K, List<V>>. E,K,V must be a String, Integer, Float,
	    // Double, Byte, Short, Long, BigInteger or BigDecimal.
	    //
	    // For other transformations you can register a DataTableType.
		// TODO Construct the expression for adding numbers.
		try {
			List<String> numberList = dataTable.asList(String.class);
			calcPage.addNumbers(driver, numberList);
			log.info("Numbers for addition are entered in formula field.");
			scenario.log("Numbers for addition are entered in formula field.");
		}catch(Exception ex) {
			ex.printStackTrace();
			log.error(String.format("Error occurred while entering numbers to add: %s", ex.getMessage()));
			Assert.fail(String.format("Error occurred while entering numbers to add: %s", ex.getMessage()));
		}
	}

	@Then("Result should be {string}")
	public void result_should_be(String expectedResult) {
	    // Write code here that turns the phrase above into concrete actions
		// TODO Validate Result TextView element text
	    try {
	    	String actualResult = calcPage.getResultFieldValue(driver);
	    	scenario.log("Expected value: " + expectedResult);
	    	scenario.log("Actual value: " + actualResult);
	    	Assert.assertEquals("Result does not match the expected value.",
	    			expectedResult, actualResult);
	    	log.info("The result of the operation matches the expected result.");
	    	scenario.log("The result of the operation matches the expected result.");
	    	
	    }catch(Exception ex) {
	    	ex.printStackTrace();
	    	scenario.log("Error occurred while validating the result field value");
	    	log.error(String.format("Error occurred while validating the result field value: %s", ex.getMessage()));
	    	Assert.fail(String.format("Error occurred while validating the result field value: %s", ex.getMessage()));
	    }
	}

	@When("User enters the expression {string} and evaluates the value.")
	public void user_enters_the_expression(String expression) {
	    // Write code here that turns the phrase above into concrete actions
		// TODO Breaking down the expression into button attributes and constructing the expression by tapping on the buttons sequentially
	    try {
	    	String[] buttonContentArray = expression.split(",");
	    	calcPage.tapButtonsToConstructExpression(driver, buttonContentArray);
	    	log.info("Entered the expression to evaluate.");
	    	scenario.log("Entered expression to evaluate " + expression);
	    
	  
	    }catch(Exception ex) {
	    	ex.printStackTrace();
	    	scenario.log("Error occurred while entering the expression for evaluation.");
	    	log.error(String.format("Error occurred while entering the expression for evaluation: %s", ex.getMessage()));
	    	Assert.fail(String.format("Error occurred while entering the expression for evaluation: %s", ex.getMessage()));
	    }
	}

	@Then("Result should be {string} corrected to {int} decimal places")
	public void result_should_be_corrected_to_decimal_places(String expectedResult, Integer precision) {
	    // Write code here that turns the phrase above into concrete actions
		// TODO Validating the result by correcting the Calculator result to a precision.
	    try {
	    	
	    	String actualResult = calcPage.getResultFieldValue(driver);
	    	scenario.log("Expected result value: " + expectedResult);
	    	scenario.log("Actual result value: " + actualResult);
	    	//float expectedFloatResult = Float.parseFloat(expectedResult);
	    	float actualFloatResult = Float.parseFloat(actualResult);
	    	
	    	String updatedActualResult  = String.format("%." + precision + "f", actualFloatResult);
	    	scenario.log("Updated actual result by correcting to precision point: " + updatedActualResult);
	    	Assert.assertEquals("Result does not match the expected value.",
	    			expectedResult, updatedActualResult);
	    	log.info("The result of the operation matches the expected result.");
	    	scenario.log("The result of operation matches with the expected result.");
	    }catch(Exception ex) {
	    	ex.printStackTrace();
	    	scenario.log("Error occurred while validating the result from result text view.");
	    	log.error(String.format("Error occurred while validating the result from result text view: %s", ex.getMessage()));
	    	Assert.fail(String.format("Error occurred while validating the result from result text view: %s", ex.getMessage()));
	    }
	}
	
	@When("User taps on {string} to switch to radian mode.")
	public void user_taps_on_to_switch_to_radian_mode(String buttonText) {
		// Write code here that turns the phrase above into concrete actions
		// TODO Switching to RAD or DEG calculator mode
		try {
	    	WebElement modeButton = calcPage.switchToCalculatorMode(driver, buttonText);
	    	Assert.assertEquals(String.format("Calculator is not switched to %s mode", buttonText), 
	    			buttonText, modeButton.getAttribute("text"));
	    	scenario.log(String.format("Switched to %s calculator mode", buttonText));
	    	log.info(String.format("Switched to %s calculator mode", buttonText));
	    }catch(Exception ex) {
	    	ex.printStackTrace();
	    	scenario.log("Error occurred while switching Calculator mode.");
	    	log.error(String.format("Error occurred while switching Calculator mode: %s", ex.getMessage()));
	    	Assert.fail(String.format("Error occurred while switching Calculator mode: %s", ex.getMessage()));
	    }
	}

	@Then("Validate the validation message is {string}")
	public void validate_the_validation_message_is(String expectedMessage) {
	    // Write code here that turns the phrase above into concrete actions
		// TODO To compare and report the Validation message matches with the expected message.
		try {
	    	String actualMessageText = calcPage.getResultPreviewFieldValue(driver);
	    	scenario.log("Expected validation message: " + expectedMessage);
	    	scenario.log("Actual validation message: " + actualMessageText);
	    	Assert.assertEquals("Validation message does not match the expected message.", expectedMessage, actualMessageText);
	    	scenario.log("Validation message matches with the expected message.");
	    	log.info("Validation message matches with the expected message.");
	    }catch(Exception ex) {
	    	ex.printStackTrace();
	    	scenario.log("Error occurred while validating the Validation message.");
	    	log.error(String.format("Error occurred while validating the Validation message: %s", expectedMessage));
	    	Assert.fail(String.format("Error occurred while validating the Validation message: %s", expectedMessage));
	    }
	}
}
