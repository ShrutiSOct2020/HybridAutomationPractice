package testScripts;

import java.util.List;

import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import pages.AuthenticationPage;
import pages.CheckoutAddressPage;
import pages.CheckoutPaymentPage;
import pages.CheckoutShippingPage;
import pages.HomePage;
import pages.MyProfilePage;
import pages.OrderConfirmationPage;
import pages.OrderHistoryPage;
import pages.OrderSummaryPage;
import pages.ProductCategoryPage;
import pages.ProductDetailsPage;
import pages.ShoppingSummaryPage;
import pojo.ProductDetailsPojo;

public class E2EProductPurchaseTest extends TestBase {

	@Test
	public void e2eProductPurchase() {

		HomePage homePage = new HomePage();
		AuthenticationPage authenticationPage = homePage.clickOnSignIn();
		MyProfilePage myProfilePage = authenticationPage.doLogin("", "");

		ProductCategoryPage productCategoryPage = myProfilePage.selectSection("women");
		List<WebElement> productList = productCategoryPage.getProductList();
		SoftAssert softAssert = new SoftAssert();
		softAssert.assertTrue(productList.size() >= 1);

		ProductDetailsPojo productDetailsPojo = new ProductDetailsPojo();

		// Product Details Page
		ProductDetailsPage productDetailsPage = productCategoryPage.selectFirstAvailableProduct(productList);
		productDetailsPage.setQuantity("10");
		productDetailsPage.setSize("L");
		productDetailsPage.selectColour("Blue");
		productDetailsPojo = productDetailsPage.capatureProductDetails(productDetailsPojo);
		productDetailsPage.clickOnAddToCart();

		// Verification on Product Details page
		Assert.assertEquals(productDetailsPojo.getProductName(), productDetailsPage.verifyProductName());
		Assert.assertEquals(productDetailsPojo.getQuantity(), productDetailsPage.verifyQuantity());
		Assert.assertEquals(productDetailsPojo.getColor() + ", " + productDetailsPojo.getSize(),
				productDetailsPage.verifySizeAndColor());

		String qun = productDetailsPojo.getQuantity();
		String price = productDetailsPojo.getUnitRatePrice();

		double totalPrice = Double.parseDouble(price) * Integer.parseInt(qun);
		productDetailsPojo.setTotalproductPrice(String.format("%.2f", totalPrice));

		Assert.assertEquals(productDetailsPojo.getTotalproductPrice(), productDetailsPage.verifyTotalPrice(),
				"In cprrect value in quantity & price on Product details page");

		// Navigate to Shopping Summary Page
		ShoppingSummaryPage shoppingSummaryPage = productDetailsPage.clickOnProceedToCheckout();

		// Verification on Shopping Summary Page
		Assert.assertEquals(productDetailsPojo.getProductName(), shoppingSummaryPage.getProductName(),
				"Product Name mismatch on summary Page");
		Assert.assertEquals(productDetailsPojo.getUnitRatePrice(), shoppingSummaryPage.getProductPrice(),
				"Product unit rate mismatch on summary Page");
		Assert.assertEquals(productDetailsPojo.getTotalproductPrice(), shoppingSummaryPage.getTotalPrice(),
				"Product price mismatch on summary Page");

		productDetailsPojo = shoppingSummaryPage.capatureShippingCharges(productDetailsPojo);
		productDetailsPojo = shoppingSummaryPage.capatureFinalPrice(productDetailsPojo);

		// Navigated to Checkout Address Page
		CheckoutAddressPage checkoutAddressPage = shoppingSummaryPage.clickOnProceedToCheckout();
		List<String> billAdddress = checkoutAddressPage.getBilingAddress();
		List<String> deliveryAdddress = checkoutAddressPage.getDeliveryAddress();
		Assert.assertEquals(billAdddress, deliveryAdddress, "Billing Address & Delivery Address is not matching");

		// Navigated to Checkout Shipping Page
		CheckoutShippingPage checkoutShippingPage = checkoutAddressPage.clickOnProcessToCheckout();
		Assert.assertEquals(productDetailsPojo.getTotalShipping(), checkoutShippingPage.capatureShippingCharges());
		checkoutShippingPage.clickOnTermAndCondition();

		// Navigated to Checkout Payment Page
		CheckoutPaymentPage checkoutPaymentPage = checkoutShippingPage.clickOnProcessToCheckout();

		double totalPriceWithShipping = Double.parseDouble(productDetailsPojo.getTotalPrice())
				+ Double.parseDouble(productDetailsPojo.getTotalShipping());

		productDetailsPojo.setTotalproductPrice(String.format("%.2f", totalPriceWithShipping));

		Assert.assertEquals(productDetailsPojo.getTotalPrice(), checkoutPaymentPage.getToalPrice());

		// Payment Done using check
		OrderSummaryPage orderSummaryPage = checkoutPaymentPage.paymentBy("Check");

		Assert.assertEquals(productDetailsPojo.getTotalPrice(), orderSummaryPage.captureAmount());

		// Navigated to Order Confirmation Page
		OrderConfirmationPage orderConfirmationPage = orderSummaryPage.clickOnConfirmOrder();

		Assert.assertEquals(productDetailsPojo.getTotalPrice(), orderConfirmationPage.capatureTotalAmount());

		// Navigated to Order History Page
		OrderHistoryPage orderHistoryPage = orderConfirmationPage.clickOnBackToOrder();
		Assert.assertEquals("Order history - My Store", orderHistoryPage.getPageTitle());
	}

}
