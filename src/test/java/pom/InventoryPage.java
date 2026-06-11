package pom;

import org.openqa.selenium.By;
import utility.BasePage;

public class InventoryPage extends BasePage{
	
	public By pageLogo = By.cssSelector(".primary_header .app_logo");
	public By secondaryHeader = By.cssSelector(".header_secondary_container .title");
	public String priceOfProduct = "//a[normalize-space()='@placeholder']/../..//div[@class='inventory_item_price']";
	public String addProductToCartButton = "//a[normalize-space()='@placeholder']/../..//button[contains(@id,'add-to-cart')]";
	public By cartIcon = By.cssSelector(".shopping_cart_link");
	
	public String getProductPrice(String productName) {
		return getElementText(By.xpath(priceOfProduct.replace("@placeholder", productName)));
	}
	
	public void addProductToCart(String productName) {
		clickElement(By.xpath(addProductToCartButton.replace("@placeholder", productName)));
	}
}
