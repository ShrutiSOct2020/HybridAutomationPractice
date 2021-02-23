package pages;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import base.PredefinedActions;
import exceptionHandling.ProductNotFoundException;

public class ProductCategoryPage extends PredefinedActions {

	public List<WebElement> getProductList() {
		WebDriverWait wait = new WebDriverWait(driver, 30);
		List<WebElement> productList = wait.until(ExpectedConditions
				.visibilityOfAllElementsLocatedBy(By.cssSelector("#center_column ul[class^='product_list'] h5 a")));
		return productList;
	}

	public ProductDetailsPage selectFirstAvailableProduct(List<WebElement> productList) {
		if (productList.size() > 0) {
			productList.get(0).click();
		} else {
			throw new ProductNotFoundException("Product Not displayed");
		}
		System.out.println("STEP - First product selected");
		return new ProductDetailsPage();
	}

}
