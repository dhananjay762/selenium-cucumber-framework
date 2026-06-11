package utility;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.PageLoadStrategy;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;

import io.cucumber.java.After;
import io.cucumber.java.AfterStep;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;
import java.sql.Timestamp;
import java.util.HashMap;
import java.util.Map;

public class Hooks extends BasePage{
	
	String browser;
	String headless;
	String url;
	
	@Before
	public void setUp() {
		url = readProperties("url");
		browser = System.getProperty("browser", readProperties("browser"));
		headless = System.getProperty("headless", readProperties("headless"));
		switch (browser.toUpperCase()) {
		case "CHROME": {
			ChromeOptions options = new ChromeOptions();
			options.addArguments("start-maximized");
			//Core arguments to suppress automation & credential features
			options.addArguments("--disable-infobars");
			options.addArguments("--disable-notifications");
			options.addArguments("--ignore-certificate-errors");
			options.addArguments("--password-store=basic");
			//Block the background server communication that checks for leaks
			options.addArguments("--disable-features=AutofillServerCommunication,SafeBrowsing,PasswordLeakToggleMove");
			options.setPageLoadStrategy(PageLoadStrategy.NORMAL);
			
			//Map preferences including the Leak Detection toggle
			Map<String, Object> prefs = new HashMap<>();
			prefs.put("credentials_enable_service", false);
			prefs.put("profile.password_manager_enabled", false);
			// This specifically prevents the "Data Breach / Change your password" dialog
			prefs.put("profile.password_manager_leak_detection", false); 
			options.setExperimentalOption("prefs", prefs);
			
			if(Boolean.parseBoolean(headless)) {
				options.addArguments("--headless=new");
			}
			setDriver(new ChromeDriver(options));
			getDriver().navigate().to(url);
			break;
		}
		case "EDGE": {
			EdgeOptions options = new EdgeOptions();	
			options.addArguments("start-maximized");
			options.addArguments("--remote-allow-origins=*");
			options.addArguments("--remote-debugging-port=9222");
			options.addArguments("--disable-infobars");
			options.addArguments("--disable-notifications");
			options.addArguments("--password-store=basic");
			// Disables Edge-specific smart-shopping and credential server features
			options.addArguments("--disable-features=AutofillServerCommunication,SafeBrowsing,PasswordLeakToggleMove");
			options.setPageLoadStrategy(PageLoadStrategy.NORMAL);
			
			java.util.Map<String, Object> prefs = new java.util.HashMap<>();
			prefs.put("credentials_enable_service", false);
			prefs.put("profile.password_manager_enabled", false);
			prefs.put("profile.password_manager_leak_detection", false); // Suppresses Edge's version of the breach alert

			options.setExperimentalOption("prefs", prefs);
			if(Boolean.parseBoolean(headless)) {
				options.addArguments("--headless=new");
			}
			setDriver(new EdgeDriver(options));
			getDriver().navigate().to(url);
			break;
		}
		case "FIREFOX": {
			FirefoxOptions options = new FirefoxOptions();
			if(Boolean.parseBoolean(headless)) {
				options.addArguments("--headless");
			}
			options.setPageLoadStrategy(PageLoadStrategy.NORMAL);
			setDriver(new FirefoxDriver(options));
			getDriver().manage().window().maximize();
			getDriver().navigate().to(url);
			break;
		}
		default:
			throw new IllegalArgumentException("Unexpected browser value: " + browser);
		}
	}
	
	@AfterStep
	public void captureExceptionImage(Scenario scenario) {
		if(scenario.isFailed()) {
			Timestamp timestamp = new Timestamp(System.currentTimeMillis());
			String timeMilliSeconds = Long.toString(timestamp.getTime());
			byte[] screenshot = ((TakesScreenshot)driver.get()).getScreenshotAs(OutputType.BYTES);
			scenario.attach(screenshot, "image/png", timeMilliSeconds);
		}
	}
	
	@After
	public void tearDown() {
		getDriver().quit();
		removeDriver();
		GlobalVars.unload();
	}
	
	

}
