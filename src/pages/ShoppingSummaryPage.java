package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import base.PredefinedActions;
import pojo.ProductDetailsPojo;

public class ShoppingSummaryPage extends PredefinedActions {

	public String getProductName() {
		WebDriverWait wait = new WebDriverWait(driver, 30);
		return wait
				.until(ExpectedConditions
						.visibilityOfElementLocated(By.cssSelector("#order-detail-content p[class='product-name'] a")))
				.getText();
	}

	public String getColorAndSize() {
		return driver.findElement(By.cssSelector(".cart_item .cart_description small:nth-child(3)>a")).getText();
	}

	public String getProductPrice() {
		return driver.findElement(By.cssSelector(".cart_item .cart_unit span>span")).getText().substring(1);
	}

	public String getTotalPrice() {
		return driver.findElement(By.cssSelector(".cart_item .cart_total>span")).getText().substring(1);
	}

	public CheckoutAddressPage clickOnProceedToCheckout() {
		driver.findElement(By.xpath("//div[@id='center_column']//a/span[contains(text(), 'Proceed to checkout')]"))
				.click();
		System.out.println("STEP - Click Proceed to checkout on Shopping Summary page");
		return new CheckoutAddressPage();
	}

	public ProductDetailsPojo capatureShippingCharges(ProductDetailsPojo productDetailsPojo) {
		String shippingCharge = driver.findElement(By.id("total_shipping")).getText().substring(1);
		productDetailsPojo.setTotalShipping(shippingCharge);
		return productDetailsPojo;
	}

	public ProductDetailsPojo capatureFinalPrice(ProductDetailsPojo productDetailsPojo) {
		String finalPrice = driver.findElement(By.id("total_price")).getText().substring(1);
		productDetailsPojo.setTotalPrice(finalPrice);
		return productDetailsPojo;
	}
}
