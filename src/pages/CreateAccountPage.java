package pages;

import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import base.PredefinedActions;
import pojo.CreateAccountDetailsPojo;

public class CreateAccountPage extends PredefinedActions{
	WebDriverWait wait;
	
	public boolean isPageHeadingTextDisplayed() {
		wait = new WebDriverWait(driver, 10);
		return wait.until(ExpectedConditions.textToBe(By.cssSelector("#noSlide h1"),"CREATE AN ACCOUNT"));
	}
	//TODO : remove static wait
	private void selectGender(boolean isMale) {
		try {
			Thread.sleep(5000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		wait = new WebDriverWait(driver,30);
		WebElement titleElement;
		System.out.println("STEP - Select title");
		//titleElement = isMale ? wait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector("#id_gender1")))
			//	                          : wait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector("#id_gender2")));  
		//wait.until(ExpectedConditions.elementToBeClickable(titleElement));
		if(isMale)
			titleElement = driver.findElement(By.id("id_gender1"));
		else
			titleElement = driver.findElement(By.id("id_gender2"));
		titleElement.click();	
	}
	
	private void enterFirstName(String firtName) {
		System.out.println("STEP - Enter First Name");
		if(firtName != null) {
			driver.findElement(By.id("customer_firstname")).sendKeys(firtName);
		}else
			System.out.println("FirstName field is blank");
	}
	
	private void enterLastName(String lastName) {
		System.out.println("STEP - Enter Last Name");
		if(lastName != null) {
			driver.findElement(By.id("customer_lastname")).sendKeys(lastName);
		}else {
			System.out.println("Last Name field is blank");
		}
	}
	
	private void enterPassword(String password) {
		System.out.println("STEP - Enter Password");
		if(password != null)
			driver.findElement(By.id("passwd")).sendKeys(password);
		else
			System.out.println("Password field is blank");
	}
	
	private void selectDays(String day) {
		System.out.println("STEP - Select Birthdate from drop down");
		if(day != null) {
			wait.until(ExpectedConditions.visibilityOfElementLocated((By.id("uniform-days")))).click();
			Select s = new Select(driver.findElement(By.id("days")));
			s.selectByValue(day);
		}else
			System.out.println("Day is not given");
	}
	
	private void selectMonth(String month) {
		System.out.println("STEP - Select Birth month");
		if(month != null) {
			wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("uniform-months"))).click();
			Select s = new Select(driver.findElement(By.id("months")));
			s.selectByValue(month);
		}else {
			System.out.println("Birth month is not given");
		}
	}

	private void selectYear(String year) {
		System.out.println("STEP - Select Birth year");
		if(year != null) {
			wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("uniform-years"))).click();
			Select s = new Select(driver.findElement(By.id("years")));
			s.selectByValue(year);
		}else
			System.out.println("Year is not given");
	}
	
	private void selectCompany(String company) {
		System.out.println("STEP - Enter Company Name");
		if(company != null)
			driver.findElement(By.id("company")).sendKeys(company);
		else
			System.out.println("Company field is blank");
	}
	
	private void enterAddress(String address) {
		System.out.println("STEP - Enter Address Name");
		if(address != null)
			driver.findElement(By.id("address1")).sendKeys(address);
		else
			System.out.println("address field is blank");
	}
	
	private void enterCity(String city) {
		System.out.println("STEP - Enter city Name");
		if(city != null)
			driver.findElement(By.id("city")).sendKeys(city);
		else
			System.out.println("city field is blank");
	}
	
	private void selectState(String state) {
		System.out.println("STEP - Select State");
		if(state != null) {
			wait.until(ExpectedConditions.visibilityOfElementLocated((By.id("uniform-id_state")))).click();
			Select s = new Select(driver.findElement(By.id("id_state")));
			s.selectByVisibleText(state);

		}else
			System.out.println("state is not given");
	}
	
	private void enterPostCode(String postCode) {
		System.out.println("STEP - Enter Postcode");
		if(postCode != null)
			driver.findElement(By.id("postcode")).sendKeys(postCode);
		else
			System.out.println("PostCode field is blank");
	}
	
	private void enterAdditionalInformation(String additionalInfo) {
		System.out.println("STEP - Enter Additional information");
		if(additionalInfo != null)
			driver.findElement(By.id("other")).sendKeys(additionalInfo);
		else
			System.out.println("additionalInfo field is blank");
	}
	
	private void enterHomePhone(String homePhone) {
		System.out.println("STEP - Enter Home mobile number");
		if(homePhone != null)
			driver.findElement(By.id("phone")).sendKeys(homePhone);
		else
			System.out.println("Homephone field is blank");
	}
	
	private void enterMobile(String mobileNo) {
		System.out.println("STEP - Enter Mobile number");
		if(mobileNo != null) {
			driver.findElement(By.id("phone_mobile")).sendKeys(mobileNo);
		}else {
			System.out.println("MobileNumber field is blank");
		}

	}
	public void enterCreateAccountDetails(CreateAccountDetailsPojo createAccountDetailsPojo) {
		selectGender(createAccountDetailsPojo.isMale());
		enterFirstName(createAccountDetailsPojo.getFirstName());
		enterLastName(createAccountDetailsPojo.getLastName());
		enterPassword(createAccountDetailsPojo.getPassword());
		selectDays(createAccountDetailsPojo.getDays());
		selectMonth(createAccountDetailsPojo.getMonth());
		selectYear(createAccountDetailsPojo.getYear());
		selectCompany(createAccountDetailsPojo.getCompany());
		enterAddress(createAccountDetailsPojo.getAddress1());
		enterCity(createAccountDetailsPojo.getCity());
		selectState(createAccountDetailsPojo.getState());
		enterPostCode(createAccountDetailsPojo.getPostCode());
		enterAdditionalInformation(createAccountDetailsPojo.getAdditionalInfo());
		enterHomePhone(createAccountDetailsPojo.gethPhone());
		enterMobile(createAccountDetailsPojo.getmNumber());
	}
	
	
	public MyProfilePage clickOnRegistration() {
		
		wait = new WebDriverWait(driver,30);
		wait.until(ExpectedConditions.elementToBeClickable(By.id("submitAccount"))).click();
		System.out.println("Details Registered in Application");
		return new MyProfilePage();
	}
	
	public List<String> getErrorMessage() {
		List<WebElement> listOfErrorElements = driver.findElements(By.cssSelector("ol>li"));
		List<String> listOfErrorText = new ArrayList<String>();
		String totalErrorCount = driver.findElement(By.cssSelector(".alert.alert-danger>p")).getText();
		listOfErrorText.add(totalErrorCount);
		for(WebElement element : listOfErrorElements) {
			listOfErrorText.add(element.getText());
		}
		return listOfErrorText;
	}
}
