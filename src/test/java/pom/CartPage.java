package pom;

import org.openqa.selenium.By;

public class CartPage {
	
	public By pageLogo = By.cssSelector(".primary_header .app_logo");
	public By secondaryHeader = By.cssSelector(".header_secondary_container .title");
	public By addedProductName = By.cssSelector(".cart_item .inventory_item_name");
	public By addedProductPrice = By.cssSelector(".cart_item .inventory_item_price");
	public By checkoutBtn = By.id("checkout");

}
