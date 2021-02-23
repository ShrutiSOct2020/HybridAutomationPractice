package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import base.PredefinedActions;

public class CheckoutPaymentPage extends PredefinedActions {

	public String getToalPrice() {
		WebDriverWait wait = new WebDriverWait(driver, 30);
		return wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("total_price"))).getText().substring(1);
	}

	public OrderSummaryPage paymentBy(String paymentType) {
		WebDriverWait wait = new WebDriverWait(driver, 30);
		switch (paymentType.toUpperCase()) {
		case "CHECK":
			wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("#HOOK_PAYMENT a[title*='check']")))
					.click();
			break;

		case "BANK WIRE":
			wait.until(ExpectedConditions
					.visibilityOfElementLocated(By.cssSelector("#HOOK_PAYMENT a[title*='bank wire']"))).click();
			break;
		default:
			break;
		}
		return new OrderSummaryPage();
	}

}
