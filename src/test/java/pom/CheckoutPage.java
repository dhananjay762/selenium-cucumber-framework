package pom;

import org.openqa.selenium.By;

import utility.BasePage;

public class CheckoutPage extends BasePage{
	
	public By pageLogo = By.cssSelector(".primary_header .app_logo");
	public By secondaryHeader = By.cssSelector(".header_secondary_container .title");
	public By firstNameTxt = By.id("first-name");
	public By lastNameTxt = By.id("last-name");
	public By zipCodeTxt = By.id("postal-code");
	public By continueBtn = By.id("continue");
	public By addedProductName = By.cssSelector(".cart_item .inventory_item_name");
	public By addedProductPrice = By.cssSelector(".cart_item .inventory_item_price");
	public By finishBtn = By.id("finish");
	public By thankYouMsgHdr = By.cssSelector(".complete-header");
	public By menuBurger = By.id("react-burger-menu-btn");
	public By logoutSideBtn = By.id("logout_sidebar_link");
	
	public void enterCheckoutDetails(String firstName, String lastName, String zipCode) {
		enterText(firstNameTxt, firstName);
		enterText(lastNameTxt, lastName);
		enterText(zipCodeTxt, zipCode);
	}

}
