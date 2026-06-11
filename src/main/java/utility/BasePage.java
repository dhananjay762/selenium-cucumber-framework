package utility;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ThreadGuard;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class BasePage {
	
	protected static final ThreadLocal<WebDriver> driver = new ThreadLocal<>();
	
	protected void setDriver(WebDriver driver) {
		BasePage.driver.set(ThreadGuard.protect(driver));
	}
	
	public static WebDriver getDriver() {
		return driver.get();
	}
	
	public synchronized String readProperties(String key) {
		ConfigReader reader = new ConfigReader();
		return reader.loadProperties(key);
	}
	
	public String getPageTitle() {
		return getDriver().getTitle();
	}
	
	public void enterText(By loactor, String textToEnter) {
		getDriver().findElement(loactor).clear();
		getDriver().findElement(loactor).sendKeys(textToEnter);
	}
	
	public void clickElement(By loactor) {
		WebDriverWait wait = new WebDriverWait(getDriver(), Duration.ofSeconds(20));
		wait.until(ExpectedConditions.elementToBeClickable(loactor));
		scrollToElement(loactor);
		getDriver().findElement(loactor).click();
	}
	
	public void scrollToElement(By locator) {
		Actions actions = new Actions(getDriver());
		actions.scrollToElement(getDriver().findElement(locator)).build().perform();
	}
	
	public boolean isElementVisible(By locator) {
		return getDriver().findElement(locator).isDisplayed();
	}
	
	public void waitForElementVisible(By locator) {
		WebDriverWait wait = new WebDriverWait(getDriver(), Duration.ofSeconds(20));
		wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
	}
	
	public void waitForElementClickable(By locator) {
		WebDriverWait wait = new WebDriverWait(getDriver(), Duration.ofSeconds(20));
		wait.until(ExpectedConditions.elementToBeClickable(locator));
	}
	
	public String getElementText(By locator) {
		waitForElementVisible(locator);
		return getDriver().findElement(locator).getText();
	}
	
	public void waitFor(int timeInSeconds) throws InterruptedException {
		Thread.sleep(timeInSeconds*1000);
	}
	
	protected void removeDriver() {
		driver.remove();
	}

}
