package framework.utilities;

import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriverException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import io.appium.java_client.MobileElement;
import io.appium.java_client.android.AndroidDriver;

/**
 * This class contains the utility methods that could be reused across page object classes and elsewhere.
 * @author Ratnadip Chaudhuri
 */
public class FrameworkUtilities {
	
	public FrameworkUtilities() {
		
	}
	
	private Logger log = Logger.getLogger(FrameworkUtilities.class);

	/**
	 * Get Calculator button elements by 'text' attribute
	 * @param driver
	 * @param buttonText
	 * @return
	 */
	public WebElement getKeyButtonByText(AndroidDriver<MobileElement> driver, String buttonText) {
		// TODO Auto-generated method stub
		WebElement buttonElement = null;
		try {
			buttonElement = driver.findElement(By.xpath(String.format("//android.widget.Button[@text='%s']", buttonText)));
		}catch(NoSuchElementException ex) {
			log.error(String.format("The button with text %s is not available in the current view.", buttonText));
		}
		return buttonElement;
	}

	/**
	 * Get 'text' attribute value from TextView
	 * @param textViewElement
	 * @return
	 */
	public String getValueFromTextViewElement(WebElement textViewElement) {
		// TODO Auto-generated method stub
		String value = null;
		try {
			value = textViewElement.getAttribute("text");
		}catch(Exception ex) {
			ex.printStackTrace();
			log.error("Error occured while fetching the text value from TextView field");
		}
		return value;
	}

	/**
	 * Get Calculator app button elements by 'content-desc' attribute.
	 * @param driver
	 * @param contentDesc
	 * @return
	 */
	public WebElement getKeyButtonByContentDesc(AndroidDriver<MobileElement> driver, String contentDesc) {
		// TODO Auto-generated method stub
		WebElement buttonElement = null;
		try {
			buttonElement = driver.findElement(By.xpath(String.format("//android.widget.Button[@content-desc='%s']", contentDesc)));
		}catch(NoSuchElementException ex) {
			log.error(String.format("The button with content-desc %s is not available in the current view.", contentDesc));
		}
		return buttonElement;
	}

	/**
	 * Fetch element after waiting till presence of element, timeout = 60 seconds.
	 * @param driver
	 * @param by
	 * @return
	 */
	public WebElement waitForElement(AndroidDriver<MobileElement> driver, By by) {
		// TODO Auto-generated method stub
		WebElement element = null;
		try {
			WebDriverWait wait = new WebDriverWait(driver, 60);
			element = wait.until(ExpectedConditions.presenceOfElementLocated(by));
			log.info("Waited and retrieved the element object.");
		}catch(WebDriverException wde) {
			log.error("Error occurred while waiting for the element.");
		}
		return element;
	}

}
