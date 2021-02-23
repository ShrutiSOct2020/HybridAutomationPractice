package pages;

import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import base.PredefinedActions;

public class CheckoutAddressPage extends PredefinedActions {

	public List<String> getDeliveryAddress() {
		WebDriverWait wait = new WebDriverWait(driver, 30);
		List<WebElement> eleList = wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(
				By.cssSelector("#address_delivery li:not([class='address_update']):not([class='address_title'])")));

		List<String> deliveryList = new ArrayList<>();
		for (WebElement ele : eleList) {
			deliveryList.add(ele.getText());
		}

		return deliveryList;

	}

	public List<String> getBilingAddress() {
		WebDriverWait wait = new WebDriverWait(driver, 30);
		List<WebElement> eleList = wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(
				By.cssSelector("#address_invoice li:not([class='address_update']):not([class='address_title'])")));

		List<String> billingList = new ArrayList<>();
		for (WebElement ele : eleList) {
			billingList.add(ele.getText());
		}
		return billingList;

	}

	public CheckoutShippingPage clickOnProcessToCheckout() {
		driver.findElement(By.cssSelector("button[name='processAddress']")).click();
		System.out.println("STEP - Click Proceed to checkout on Address page");
		return new CheckoutShippingPage();
	}

}
