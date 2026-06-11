package pom;

import org.openqa.selenium.By;

public class LoginPage {
	
	public By pageHeader = By.className("login_logo");
	public By loginDiv = By.cssSelector(".login_container");
	public By userName = By.id("user-name");
	public By userPassword = By.id("password");
	public By loginBtn = By.id("login-button");
	public By errorMessage = By.xpath("//div[@class='error-message-container error']/h3");
	

}
