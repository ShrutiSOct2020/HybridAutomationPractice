package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import base.PredefinedActions;

public class AuthenticationPage extends PredefinedActions {

	public void enterEmailAdress(String emailId) {
		WebDriverWait wait = new WebDriverWait(driver, 30);
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("email_create"))).sendKeys(emailId);
		System.out.println("Enter email addressfor the create user name");
	}

	public CreateAccountPage clickOnCreateAccount() {
		WebDriverWait wait = new WebDriverWait(driver, 30);
		wait.until(ExpectedConditions.elementToBeClickable(By.id("SubmitCreate"))).click();
		System.out.println("Clicked on the submit button to fill the other details");
		return new CreateAccountPage();
	}

	public boolean isAuthenticationHeaderVisible() {
		WebDriverWait wait = new WebDriverWait(driver, 30);
		WebElement element = wait
				.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//h1[text()='Authentication']")));
		return element.isDisplayed();
	}

	public MyProfilePage doLogin(String emailAddress, String pwd) {
		enterEmailAddressInLogin(emailAddress);
		enterPasswordInLogin(pwd);
		clickOnSignInButton();
		return new MyProfilePage();
	}

	public void clickOnSignInButton() {
		WebDriverWait wait = new WebDriverWait(driver, 30);
		wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector("#SubmitLogin"))).click();
		System.out.println("STEP - Click on sign button");
	}

	public void enterPasswordInLogin(String pwd) {
		WebDriverWait wait = new WebDriverWait(driver, 30);
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//input[@id='passwd']"))).sendKeys(pwd);
		System.out.println("STEP - Enter Password on login section");
	}

	public void enterEmailAddressInLogin(String emailAddress) {
		WebDriverWait wait = new WebDriverWait(driver, 30);
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("#email"))).sendKeys(emailAddress);
		System.out.println("STEP - Enter email address on login section");
	}

	public String verifyErrorMessage() {
		WebDriverWait wait = new WebDriverWait(driver, 30);
		return wait.until(
				ExpectedConditions.visibilityOfElementLocated(By.cssSelector("div[class='alert alert-danger'] li")))
				.getText();
	}

}
