package testScripts;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import base.PredefinedActions;
import pages.AuthenticationPage;
import pages.CreateAccountPage;
import pages.HomePage;
import pages.MyProfilePage;
import pojo.CreateAccountDetailsPojo;
import util.ExcelOperation;

public class CreateAccountTest extends TestBase{
	
	@Test
	public void createAccountTest() {
		HomePage homePage = new HomePage();
		
		System.out.println("STEP - ClickOnSignIn");
		AuthenticationPage authenticationPage = homePage.clickOnSignIn();
		
		System.out.println("Verify - Authentication Header is displayed");
		boolean authenticationHeaderFlag = authenticationPage.isAuthenticationHeaderVisible();
		Assert.assertTrue(authenticationHeaderFlag);
		
		System.out.println("STEP - Enter email address");
		authenticationPage.enterEmailAdress("automation4@gmail.com");
		CreateAccountPage createAccountPage = authenticationPage.clickOnCreateAccount();
		
		System.out.println("Verify - Create Account Page Heading Text is as Expected");
		boolean isheadingTextDisplayed = createAccountPage.isPageHeadingTextDisplayed();
		Assert.assertTrue(isheadingTextDisplayed,"Header text was not displayed");
		
		System.out.println("Navigate to create account page");
		CreateAccountDetailsPojo createAccountDetailsPojo = new CreateAccountDetailsPojo();
		createAccountDetailsPojo.setMale(true);
		createAccountDetailsPojo.setFirstName("Automation");
		createAccountDetailsPojo.setLastName("Technocredits");
		createAccountDetailsPojo.setPassword("Automation123");
		createAccountDetailsPojo.setDays("12");
		createAccountDetailsPojo.setMonth("January");
		createAccountDetailsPojo.setYear("1997");
		createAccountDetailsPojo.setCompany("PTC");
		createAccountDetailsPojo.setAddress1("650 Grassmere park");
		createAccountDetailsPojo.setCity("nashville");
		createAccountDetailsPojo.setState("Tennessee");
		createAccountDetailsPojo.setPostCode("37211");
		createAccountDetailsPojo.setAdditionalInfo("NA");
		createAccountDetailsPojo.sethPhone("8905714840");
		createAccountDetailsPojo.setmNumber("8905714840");
		createAccountDetailsPojo.setAliasAddress("");
		
		createAccountPage.enterCreateAccountDetails(createAccountDetailsPojo);
		MyProfilePage myProfilePage = createAccountPage.clickOnRegistration();
		String actual = myProfilePage.getHeaderText();
		String expected = "Automation Technocredits";
		Assert.assertEquals(actual, expected, "Verification of headertext failed");
	}
	
	@Test(dataProvider = "CreateAccountDataProvider")
	public void createAccountDataDrivenTest
		(String email, String gender, String firstName,
		 String lastName, String password, String days, String month,
		 String year, String company, String add1, String city, String state,
		 String postcode, String additionalInfo, String hphone, String mNumber,
		 String alias) {
		HomePage homePage = new HomePage();
		
		System.out.println("STEP - ClickOnSignIn");
		AuthenticationPage authenticationPage = homePage.clickOnSignIn();
		
		System.out.println("Verify - Authentication Header is displayed");
		boolean authenticationHeaderFlag = authenticationPage.isAuthenticationHeaderVisible();
		Assert.assertTrue(authenticationHeaderFlag);
		
		System.out.println("STEP - Enter email address");
		authenticationPage.enterEmailAdress(email);
		CreateAccountPage createAccountPage = authenticationPage.clickOnCreateAccount();
		
		System.out.println("Verify - Create Account Page Heading Text is as Expected");
		boolean isheadingTextDisplayed = createAccountPage.isPageHeadingTextDisplayed();
		Assert.assertTrue(isheadingTextDisplayed,"Header text was not displayed");
		
		System.out.println("Navigate to create account page");
		CreateAccountDetailsPojo createAccountDetailsPojo = new CreateAccountDetailsPojo();
		boolean mFlag = gender.equalsIgnoreCase("male") ? true : false;
		createAccountDetailsPojo.setMale(mFlag);
		createAccountDetailsPojo.setFirstName(firstName);
		createAccountDetailsPojo.setLastName(lastName);
		createAccountDetailsPojo.setPassword(password);
		createAccountDetailsPojo.setDays(days);
		createAccountDetailsPojo.setMonth(month);
		createAccountDetailsPojo.setYear(year);
		createAccountDetailsPojo.setCompany(company);
		createAccountDetailsPojo.setAddress1(add1);
		createAccountDetailsPojo.setCity(city);
		createAccountDetailsPojo.setState(state);
		createAccountDetailsPojo.setPostCode(postcode);
		createAccountDetailsPojo.setAdditionalInfo(additionalInfo);
		createAccountDetailsPojo.sethPhone(hphone);
		createAccountDetailsPojo.setmNumber(mNumber);
		createAccountDetailsPojo.setAliasAddress(alias);
		
		createAccountPage.enterCreateAccountDetails(createAccountDetailsPojo);
		MyProfilePage myProfilePage = createAccountPage.clickOnRegistration();
		String actual = myProfilePage.getHeaderText();
		String expected = firstName + " " + lastName;
		Assert.assertEquals(actual, expected, "Verification of headertext failed");
	}
	
	@DataProvider(name = "CreateAccountDataProvider")
	public String[][] getData() throws IOException{
		return ExcelOperation.getExcelData("data.xlsx", "CreateAccount");
	}
	
	@Test(dataProvider = "CreateAccountDataProviderPojo")
	public void createAccountDataDrivenTest(CreateAccountDetailsPojo createAccountDetailsPojo) {
		HomePage homePage = new HomePage();
		
		System.out.println("STEP - ClickOnSignIn");
		AuthenticationPage authenticationPage = homePage.clickOnSignIn();
		
		System.out.println("Verify - Authentication Header is displayed");
		boolean authenticationHeaderFlag = authenticationPage.isAuthenticationHeaderVisible();
		Assert.assertTrue(authenticationHeaderFlag);
		
		System.out.println("STEP - Enter email address");
		authenticationPage.enterEmailAdress(createAccountDetailsPojo.getEmail());
		CreateAccountPage createAccountPage = authenticationPage.clickOnCreateAccount();
		
		System.out.println("Verify - Create Account Page Heading Text is as Expected");
		boolean isheadingTextDisplayed = createAccountPage.isPageHeadingTextDisplayed();
		Assert.assertTrue(isheadingTextDisplayed,"Header text was not displayed");
		
		System.out.println("Navigate to create account page");
		
		createAccountPage.enterCreateAccountDetails(createAccountDetailsPojo);
		MyProfilePage myProfilePage = createAccountPage.clickOnRegistration();
		String actual = myProfilePage.getHeaderText();
		String expected = createAccountDetailsPojo.getFirstName() + " " + createAccountDetailsPojo.getLastName();
		Assert.assertEquals(actual, expected, "Verification of headertext failed");
	}
	
	@DataProvider(name="CreateAccountDataProviderPojo")
	public Object[][] getDataForCreateAccountPojo() throws IOException{
		String[][] data =  ExcelOperation.getExcelData("data.xlsx", "CreateAccount");
		Object[][] output = new Object[data.length][1];
		for(int index=0;index<data.length;index++) {
			CreateAccountDetailsPojo createAccountDetailsPojo = new CreateAccountDetailsPojo();
			createAccountDetailsPojo.setEmail(data[index][0]);
			boolean mFlag = data[index][1].equalsIgnoreCase("male") ? true : false;
			createAccountDetailsPojo.setMale(mFlag);
			createAccountDetailsPojo.setFirstName(data[index][2]);
			createAccountDetailsPojo.setLastName(data[index][3]);
			createAccountDetailsPojo.setPassword(data[index][4]);
			createAccountDetailsPojo.setDays(data[index][5]);
			createAccountDetailsPojo.setMonth(data[index][6]);
			createAccountDetailsPojo.setYear(data[index][7]);
			createAccountDetailsPojo.setCompany(data[index][8]);
			createAccountDetailsPojo.setAddress1(data[index][9]);
			createAccountDetailsPojo.setCity(data[index][10]);
			createAccountDetailsPojo.setState(data[index][11]);
			createAccountDetailsPojo.setPostCode(data[index][12]);
			createAccountDetailsPojo.setAdditionalInfo(data[index][13]);
			createAccountDetailsPojo.sethPhone(data[index][14]);
			createAccountDetailsPojo.setmNumber(data[index][15]);
			createAccountDetailsPojo.setAliasAddress(data[index][16]);
			output[index][0] = createAccountDetailsPojo;			
		}
		
		return output;
	}
	
	@Test(dataProvider="CreateAccountDataProviderMap")
	public void createAccountWithMapTest(HashMap<String,String> hm) {
		System.out.println(hm.get("email"));
		System.out.println(hm.get("gender"));
	}
	
	@DataProvider(name="CreateAccountDataProviderMap")
	public Object[][] getDataForCreateAccountMap() throws IOException{
		String[][] data =  ExcelOperation.getExcelData("data.xlsx", "CreateAccount");
		Object[][] output = new Object[data.length][1];
		
		for(int index=0;index<data.length;index++) {
			HashMap<String,String> hm = new HashMap<String,String>();
			hm.put("email", data[index][0]);
			hm.put("gender", data[index][1]);
			output[index][0] = hm;			
		}
		return output;
	}
	@Test
	public void createAccountUIValidationTest() {
		System.out.println("STEP - Open Browser");
		PredefinedActions.start();
		HomePage homePage = new HomePage();
		
		System.out.println("STEP - ClickOnSignIn");
		AuthenticationPage authenticationPage = homePage.clickOnSignIn();
		
		System.out.println("Verify - Authentication Header is displayed");
		boolean authenticationHeaderFlag = authenticationPage.isAuthenticationHeaderVisible();
		Assert.assertTrue(authenticationHeaderFlag);
		
		System.out.println("STEP - Enter email address");
		authenticationPage.enterEmailAdress("automation5@gmail.com");
		CreateAccountPage createAccountPage = authenticationPage.clickOnCreateAccount();
		
		System.out.println("Verify - Create Account Page Heading Text is as Expected");
		boolean isheadingTextDisplayed = createAccountPage.isPageHeadingTextDisplayed();
		Assert.assertTrue(isheadingTextDisplayed,"Header text was not displayed");
		
		System.out.println("Navigate to create account page");
		CreateAccountDetailsPojo createAccountDetailsPojo = new CreateAccountDetailsPojo();
		
		createAccountPage.enterCreateAccountDetails(createAccountDetailsPojo);
		createAccountPage.clickOnRegistration();
		
		List<String> expectedErrorMessages = new ArrayList<String>();
		expectedErrorMessages.add("There are 8 errors");
		expectedErrorMessages.add("You must register at least one phone number.");
		expectedErrorMessages.add("lastname is required.");
		expectedErrorMessages.add("firstname is required.");
		expectedErrorMessages.add("passwd is required.");
		expectedErrorMessages.add("address1 is required.");
		expectedErrorMessages.add("city is required.");
		expectedErrorMessages.add("The Zip/Postal code you've entered is invalid. It must follow this format: 00000");
		expectedErrorMessages.add("This country requires you to choose a State.");
		
		List<String> actualErrorMessages = createAccountPage.getErrorMessage();
		System.out.println("DEBUG - print actualErrorMessages");
		System.out.println(actualErrorMessages);
		
		System.out.println("DEBUG - print expectedErrorMessages");
		System.out.println(expectedErrorMessages);
		Assert.assertEquals(actualErrorMessages, expectedErrorMessages);
	}
}