package stepDef;

import org.testng.Assert;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.When;
import pom.PageFactory;
import utility.GlobalVars;
import io.cucumber.java.en.Then;

public class TestStepDef extends PageFactory{
	
	@Given("User is on Login page")
	public void user_is_on_login_page() {
	    Assert.assertEquals(getPageTitle(), "Swag Labs");
	    Assert.assertEquals(getElementText(getLoginPage().pageHeader), "Swag Labs");
	}
	
	@When("I enter username as {string}")
	public void i_enter_username_as(String userName) {
		enterText(getLoginPage().userName, userName);
	}
	
	@When("I enter password as {string}")
	public void i_enter_password_as(String password) {
		enterText(getLoginPage().userPassword, password);
	}
	
	@When("I click on Login button")
	public void i_click_on_login_button() throws InterruptedException {
		clickElement(getLoginPage().loginBtn);
	}
	
	@Then("I should be navigated to the Inventory page")
	public void i_should_be_navigated_to_the_dashboard_page() {
		Assert.assertEquals(getElementText(getInventoryPage().pageLogo), "Swag Labs");
		Assert.assertEquals(getElementText(getInventoryPage().secondaryHeader), "Products");
	}
	
	@Then("Application should throw validation message for incorrect credentials")
	public void application_should_throw_validation_message_for_incorrect_credentials() {
		Assert.assertEquals(getElementText(getLoginPage().errorMessage), 
				"Epic sadface: Username and password do not match any user in this service");
	}
	
	@When("I select the product {string} and add to cart")
	public void i_select_the_product_and_add_to_cart(String productName) {
		GlobalVars.setGlobalVars("productName", productName);
		GlobalVars.setGlobalVars("productPrice", getInventoryPage().getProductPrice(productName));
		getInventoryPage().addProductToCart(productName);
	}
	
	@When("I click on the cart icon")
	public void i_click_on_the_cart_icon() throws InterruptedException {
	    clickElement(getInventoryPage().cartIcon);	
	    waitFor(2);
	}
	
	@Then("I should be able to verify the added product")
	public void i_should_be_able_to_verify_the_added_product() {
	    Assert.assertEquals(getElementText(getCartPage().secondaryHeader), "Your Cart");
	    Assert.assertEquals(getElementText(getCartPage().addedProductName), GlobalVars.getGlobalVars("productName"));
	    Assert.assertEquals(getElementText(getCartPage().addedProductPrice), GlobalVars.getGlobalVars("productPrice"));
	}
	
	@When("I click on checkout button")
	public void i_click_on_checkout_button() throws InterruptedException {
	    clickElement(getCartPage().checkoutBtn);
	    waitFor(2);
	}
	
	@When("I enter the firstname, lastname and zipcode as {string}, {string} and {string} respectively")
	public void i_enter_the_firstname_lastname_and_zipcode_as_and_respectively(String firstName, String lastName, String zipCode) {
		Assert.assertEquals(getElementText(getCheckoutPage().secondaryHeader), "Checkout: Your Information");
		getCheckoutPage().enterCheckoutDetails(firstName, lastName, zipCode);
	}
	
	@When("I click on continue button")
	public void i_click_on_continue_button() {
	    clickElement(getCheckoutPage().continueBtn);
	}
	
	@Then("Verify the checkout product details")
	public void verify_the_checkout_product_details() {
		Assert.assertEquals(getElementText(getCheckoutPage().secondaryHeader), "Checkout: Overview");
	    Assert.assertEquals(getElementText(getCheckoutPage().addedProductName), GlobalVars.getGlobalVars("productName"));
	    Assert.assertEquals(getElementText(getCheckoutPage().addedProductPrice), GlobalVars.getGlobalVars("productPrice"));
	}
	
	@When("I click on finish button")
	public void i_click_on_finish_button() {
		clickElement(getCheckoutPage().finishBtn);
	}
	
	@Then("Order should be successfully placed")
	public void order_should_be_successfully_placed() {
		Assert.assertEquals(getElementText(getCheckoutPage().secondaryHeader), "Checkout: Complete!");
		Assert.assertEquals(getElementText(getCheckoutPage().thankYouMsgHdr), 
				"Thank you for your order!");
	}
	
	@When("I click on logout button")
	public void i_click_on_logout_button() {
		clickElement(getCheckoutPage().menuBurger);
		clickElement(getCheckoutPage().logoutSideBtn);
	}
	
	@Then("I should be able to logged out from the application")
	public void i_should_be_able_to_logged_out_from_the_application() {
		Assert.assertEquals(getPageTitle(), "Swag Labs");
	    Assert.assertEquals(getElementText(getLoginPage().pageHeader), "Swag Labs");
	    Assert.assertTrue(isElementVisible(getLoginPage().loginDiv));
	}
	

}
