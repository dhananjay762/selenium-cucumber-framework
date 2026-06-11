package pom;

import utility.BasePage;

public class PageFactory extends BasePage{
	
	private static LoginPage loginPage;
	private static InventoryPage inventoryPage;
	private static CartPage cartPage;
	private static CheckoutPage checkoutPage;
	
	public LoginPage getLoginPage() {
		if(loginPage==null) {
			loginPage = new LoginPage();
		}
		return loginPage;
	}
	
	public InventoryPage getInventoryPage() {
		if(inventoryPage==null) {
			inventoryPage = new InventoryPage();
		}
		return inventoryPage;
	}
	
	public CartPage getCartPage() {
		if(cartPage==null) {
			cartPage = new CartPage();
		}
		return cartPage;
	}
	
	public CheckoutPage getCheckoutPage() {
		if(checkoutPage==null) {
			checkoutPage = new CheckoutPage();
		}
		return checkoutPage;
	}


}
