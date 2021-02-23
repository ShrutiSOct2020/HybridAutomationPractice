package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import base.PredefinedActions;

public class OrderSummaryPage extends PredefinedActions {

	public String captureAmount() {
		WebDriverWait wait = new WebDriverWait(driver, 30);
		return wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("amount"))).getText().substring(1);
	}

	public OrderConfirmationPage clickOnConfirmOrder() {
		WebDriverWait wait = new WebDriverWait(driver, 30);
		wait.until(ExpectedConditions
				.visibilityOfElementLocated(By.cssSelector("#cart_navigation button[type='submit']>span"))).click();
		System.out.println("STEP - Click Proceed to checkout on Order confirmation page");
		return new OrderConfirmationPage();
	}

}
