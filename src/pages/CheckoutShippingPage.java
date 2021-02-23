package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import base.PredefinedActions;

public class CheckoutShippingPage extends PredefinedActions {

	public String capatureShippingCharges() {
		WebDriverWait wait = new WebDriverWait(driver, 30);
		return wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("div.delivery_option_price")))
				.getText().substring(1);
	}

	public void clickOnTermAndCondition() {
		WebDriverWait wait = new WebDriverWait(driver, 30);
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("uniform-cgv"))).click();
	}

	public CheckoutPaymentPage clickOnProcessToCheckout() {
		driver.findElement(By.cssSelector("button[name='processCarrier']")).click();
		System.out.println("STEP - Click Proceed to checkout on Payment page");
		return new CheckoutPaymentPage();
	}
}
