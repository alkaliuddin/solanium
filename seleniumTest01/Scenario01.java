package testPack01;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.By;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;

public class Scenario01 {
	private final static Double TEST_VALUE = 250.00; //Change test value for input
	
	public static void main(String[] args) {
		System.setProperty("webdriver.chrome.driver", "D:\\chromedriver.exe");
		WebDriver driver = new ChromeDriver();
		driver.get("http://dbankdemo.com");
		
		System.out.println("Executing Test Case ID: TS01-TC01");
		
		//Login
		System.out.println("Logging in");
		WebElement username_field = driver.findElement(By.id("username"));
		username_field.sendKeys("tester@unisza.edu.my");
		WebElement password_field = driver.findElement(By.id("password"));
		password_field.sendKeys("Abcde@12345");
		WebElement login_button = driver.findElement(By.id("submit"));
		login_button.click();
		
		//Goto savings info
		System.out.println("Going to Savings");
		WebElement savings_menu = driver.findElement(By.id("savings-menu"));
		savings_menu.click();
		WebElement view_savings = driver.findElement(By.linkText("View Savings"));
		view_savings.click();
		
		//Get initial savings info
		String first_Balance = driver.findElement(By.xpath("/html/body/div[1]/div[2]/div/div[1]/div[1]/div/form/div/div[7]")).getText();
		
		//Goto transactions menu
		WebElement transaction_menu = driver.findElement(By.linkText("Transactions"));
		transaction_menu.click();
		WebElement deposit_ = driver.findElement(By.linkText("Deposit"));
		deposit_.click();
		
		//Select drop-down
		WebElement dropdown_Select = driver.findElement(By.id("id"));
		Select acc_Select = new Select(dropdown_Select);
		acc_Select.selectByIndex(1);
		//WebElement selected_Value = acc_Select.getFirstSelectedOption();
		//System.out.println("Selected value is " + selected_Value.getText());
		
		//Input deposit amount
		WebElement deposit_Box = driver.findElement(By.id("amount"));
		deposit_Box.sendKeys(Double.toString(TEST_VALUE));
		WebElement submit_Deposit = driver.findElement(By.xpath("//button[@class='btn btn-primary btn-sm']"));
		submit_Deposit.click();
		
		//Get new savings info
		String new_Balance = driver.findElement(By.xpath("/html/body/div[1]/div[2]/div/div[1]/div[1]/div/form/div/div[7]")).getText();
		
		//Validation
		String [] delimit1 = first_Balance.split("([$]+)");
		Double double_InitialBalance = Double.parseDouble(delimit1[1]);
		System.out.println("Initial Balance: $" + double_InitialBalance);
		
		String [] delimit2 = new_Balance.split("([$]+)");
		Double double_NewBalance = Double.parseDouble(delimit2[1]);
		System.out.println("New Balance: $" + double_NewBalance);
		
		Double expected_Balance = double_InitialBalance + TEST_VALUE;
		System.out.println("Expected Balance: $" + expected_Balance);
		
		if (Double.compare(expected_Balance, double_NewBalance) == 0) {
			System.out.println(" -- Deposit amount validated --");
			System.out.println(" -- Test Case ID: TS01-TC01 Successful --");
			driver.quit();
		} else {
			System.out.println("-- Deposit amount invalid -- ");
			System.out.println(" -- Test Case ID: TS01-TC01 Failed --");
			driver.quit();
		}
	}
}
