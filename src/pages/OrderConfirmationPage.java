package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import base.PredefinedActions;

public class OrderConfirmationPage extends PredefinedActions {

	public String capatureTotalAmount() {
		WebDriverWait wait = new WebDriverWait(driver, 30);
		return wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".price strong"))).getText()
				.substring(1);
	}

	public OrderHistoryPage clickOnBackToOrder() {

		WebDriverWait wait = new WebDriverWait(driver, 30);
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("a[title='Back to orders']"))).click();
		System.out.println("STEP - Click Proceed to checkout on Order History page");
		return new OrderHistoryPage();
	}

}
