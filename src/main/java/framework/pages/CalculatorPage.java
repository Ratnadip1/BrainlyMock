package framework.pages;

import java.util.List;

import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebElement;
import framework.utilities.FrameworkUtilities;
import io.appium.java_client.MobileElement;
import io.appium.java_client.android.AndroidDriver;


/**
 * Page object class for Calculator app screen.
 * @author Ratnadip
 */
public class CalculatorPage {
	
	private Logger log = Logger.getLogger(CalculatorPage.class);
	
	public static final By formulaTextViewElementBy = By.xpath("//android.widget.TextView[@resource-id='com.google.android.calculator:id/formula']");
	private static final By resultTextViewElementBy = By.xpath("//android.widget.TextView[@resource-id='com.google.android.calculator:id/result_final']");
	private static final By resultPreviewElementBy = By.xpath("//android.widget.TextView[@resource-id='com.google.android.calculator:id/result_preview']");
	private static final By calculatorModeButtonBy = By.xpath("//android.widget.Button[@resource-id='com.google.android.calculator:id/mode']");
	private FrameworkUtilities utilities;
	
	public CalculatorPage() {
		// TODO Auto-generated constructor stub
		utilities = new FrameworkUtilities();
	}

	/**
	 * get Formula TextView element
	 * @param driver
	 * @return
	 */
	public WebElement getFormulaTextView(AndroidDriver<MobileElement> driver) {
		return driver.findElement(formulaTextViewElementBy);
	}

	/**
	 * Performs the operation of adding the list of numbers provided in the Calculator app.
	 * @param driver - Mobile driver
	 * @param numberList - List of numbers to add
	 * @throws Exception 
	 */
	public void addNumbers(AndroidDriver<MobileElement> driver, List<String> numberList) throws Exception {
		// TODO Auto-generated method stub
		try {
			for(int opCtr=0; opCtr < numberList.size(); opCtr++) {
				// Fetching and tapping individual numbers to add.
				WebElement numberButton = utilities.getKeyButtonByText(driver, numberList.get(opCtr));
				
				if(numberButton != null) {
					numberButton.click();
					log.info(String.format("Clicked on number button '%s'.", numberList.get(opCtr)));
				} else {
					throw new NoSuchElementException(String.format("Number button '%s' is not available in the view.", numberList.get(opCtr)));
				}
			
				if(opCtr == numberList.size()-1) {
					// Tapping '=' button if the last number is entered. 
					WebElement equalButton = utilities.getKeyButtonByText(driver, "=");
				
					if(equalButton != null) {
						equalButton.click();
						log.info("Tapped on equals button at the end of the addition expression.");
					} else {
						throw new NoSuchElementException("Button '=' is not available in the view.");
					}
				} else {
					// Tapping '+' button after tapping individual numbers to add.
					WebElement plusButton = utilities.getKeyButtonByText(driver, "+");
					if(plusButton != null) {
						plusButton.click();
						log.info("Tapped on plus button.");
					} else {
						throw new NoSuchElementException("Button '+' is not available in the view.");
					}
				}
			}
		}catch(Exception e) {
			throw e;
		}
	}

	/**
	 * Get Result TextView element value
	 * @param driver
	 * @return
	 */
	public String getResultFieldValue(AndroidDriver<MobileElement> driver) {
		// TODO Auto-generated method stub
		String textViewFieldValue = null;
		try {
			WebElement resultTextViewElement = utilities.waitForElement(driver, resultTextViewElementBy);
			textViewFieldValue = utilities.getValueFromTextViewElement(resultTextViewElement);
			log.info("Result Text View Value: " + textViewFieldValue);
		}catch(Exception ex) {
			log.error("Unexpected exception occurred fetching the value of the Calculator result field");
		}
		return textViewFieldValue;
	}

	/**
	 * Constructs the formula expression by tapping on buttons one by one.
	 * @param driver
	 * @param buttonContentArray
	 * @throws Exception
	 */
	public void tapButtonsToConstructExpression(AndroidDriver<MobileElement> driver, String[] buttonContentArray) throws Exception {
		// TODO Auto-generated method stub
		try {
			// Iterate and tap the buttons to construct the expression
			for(String button: buttonContentArray) {
				WebElement buttonElement = null;
				// If the expression is surrounded by [], it is identified by 'text' attribute otherwise it is identified by 'content-desc'
				if(button.trim().contains("[")) {
					button = button.trim().replace("[", "").replace("]", "");
					buttonElement = utilities.getKeyButtonByText(driver, button.trim());
				} else {
					buttonElement = utilities.getKeyButtonByContentDesc(driver, button.trim());
				}
				
				if(buttonElement != null) {
					buttonElement.click();
					log.info(String.format("Clicked on button with content-desc or text '%s'.", button));
				}else {
					throw new NoSuchElementException(String.format("Button with content-desc or text '%s' is not available in the view.", button));
				}				
			}
			log.info("Entered the expression to evaluate.");
		}catch(Exception ex) {
			ex.printStackTrace();
			log.error(String.format("Error occurred while tapping to construct expression: %s", ex.getMessage()));
			throw ex;
		}
		
	}

	/**
	 * Changes Calculator mode based on buttonText input
	 * @param driver
	 * @param buttonText
	 * @return
	 */
	public WebElement switchToCalculatorMode(AndroidDriver<MobileElement> driver, String buttonText) {
		// TODO Auto-generated method stub
		WebElement radianButtonElement = null;
		try {
			radianButtonElement = utilities.waitForElement(driver, calculatorModeButtonBy);
			if(!radianButtonElement.getAttribute("text").equalsIgnoreCase(buttonText)) {
				radianButtonElement.click();
				log.info("Tapped on Mode button to switch to Radian mode.");
			} else {
				log.info("Calculator already in desired mode.");
			}
			
			
		}catch(NoSuchElementException nse) {
			throw new NoSuchElementException("Calculator mode button is not available.");
		}
		return radianButtonElement;	
	}

	public String getResultPreviewFieldValue(AndroidDriver<MobileElement> driver) {
		// TODO Auto-generated method stub
		String textViewFieldValue = null;
		try {
			textViewFieldValue = utilities.getValueFromTextViewElement(driver.findElement(resultPreviewElementBy));
		}catch(Exception ex) {
			log.error("Unexpected exception occurred fetching the value of the Calculator result preview field");
		}
		return textViewFieldValue;
	}

}
