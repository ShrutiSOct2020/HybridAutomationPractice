package base;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class PredefinedActions {
	protected static WebDriver driver;

	public static void start() {
		String path = "./resources/windows/chromedriver.exe";
		System.setProperty("webdriver.chrome.driver", path);
		driver = new ChromeDriver();
		driver.manage().window().maximize();
		// driver.get("http://automationpractice.com/index.php");
		driver.get("http://automationpractice.com/index.php");
		System.out.println("Navigate to Application");
	}

	public static void close() {
		driver.close();
	}
}
