package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import base.PredefinedActions;

public class MyProfilePage extends PredefinedActions {

	public String getHeaderText() {
		WebDriverWait wait = new WebDriverWait(driver, 30);
		String headerText = wait
				.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".header_user_info span")))
				.getText();
		return headerText;
	}

	public String getUserFullName() {
		WebDriverWait wait = new WebDriverWait(driver, 30);
		String fullName = wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".account>span")))
				.getText();
		return fullName;
	}

	public ProductCategoryPage selectSection(String sectionName) {
		WebDriverWait wait = new WebDriverWait(driver, 30);
		WebElement ele = null;
		switch (sectionName.toUpperCase()) {
		case "WOMEN":
			ele = wait.until(ExpectedConditions
					.visibilityOfElementLocated(By.cssSelector("#block_top_menu>ul>li:nth-child(1)")));
			break;
		case "DRESSES":
			ele = wait.until(ExpectedConditions
					.visibilityOfElementLocated(By.cssSelector("#block_top_menu>ul>li:nth-child(2)")));
			break;

		case "T-SHIRTS":
			ele = wait.until(ExpectedConditions
					.visibilityOfElementLocated(By.cssSelector("#block_top_menu>ul>li:nth-child(3)")));
			break;
		default:
			break;
		}
		ele.click();
		return new ProductCategoryPage();
	}

}
