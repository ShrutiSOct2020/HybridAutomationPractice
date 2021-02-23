package testScripts;

import org.testng.Assert;
import org.testng.annotations.Test;

import pages.AuthenticationPage;
import pages.HomePage;
import pages.MyProfilePage;

public class LoginAcountTest extends TestBase {

	@Test(enabled = false)
	public void verifyValidLogin() {
		HomePage homePage = new HomePage();
		AuthenticationPage authenticationPage = homePage.clickOnSignIn();
		MyProfilePage myProfilePage = authenticationPage.doLogin("automation101@gmail.com", "automation101");

		String expectedUser = "harsh patel";
		String actualUser = myProfilePage.getUserFullName();

		Assert.assertEquals(actualUser, expectedUser);
	}

	// @DataProvider(name = "LoinAccountDataProvider")
	// public String[][] getData() throws IOException{
	// return ExcelOperation.getExcelData("data.xlsx", "CreateAccount");
	// }

	@Test(enabled = false)
	public void verifyInvalidEmailAddressLogin() {
		HomePage homePage = new HomePage();
		AuthenticationPage authenticationPage = homePage.clickOnSignIn();
		MyProfilePage myProfilePage = authenticationPage.doLogin("automat@gmail.com", "automation101");

		String expectedMessage = "Authentication failed.";
		String actualMessage = authenticationPage.verifyErrorMessage();
		Assert.assertEquals(actualMessage, expectedMessage);
	}

	@Test(enabled = false)
	public void verifyInvalidPasswordLogin() {
		HomePage homePage = new HomePage();
		AuthenticationPage authenticationPage = homePage.clickOnSignIn();
		MyProfilePage myProfilePage = authenticationPage.doLogin("automation101@gmail.com", "automation");

		String expectedMessage = "Authentication failed.";
		String actualMessage = authenticationPage.verifyErrorMessage();
		Assert.assertEquals(actualMessage, expectedMessage);
	}

	@Test(enabled = false)
	public void verifyEnterCredentialsLogin() {
		HomePage homePage = new HomePage();
		AuthenticationPage authenticationPage = homePage.clickOnSignIn();
		authenticationPage.clickOnSignInButton();

		String expectedMessage = "An email address required.";
		String actualMessage = authenticationPage.verifyErrorMessage();
		Assert.assertEquals(actualMessage, expectedMessage);
	}

}
