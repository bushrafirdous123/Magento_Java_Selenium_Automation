package com.automation.magento.testcases;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

/*
 Test Scenario:
	Verify you can create account in E-Commerce site and can share wishlist to other people

Test Steps:

1) Go to https://live.techpanda.org/index.php/

2) Click on My Account link

3) Click Create an Account link and fill New User information except Email ID

4) Click Register

5) Verify Registration is done

6) Go to TV menu

7) Add product in your wishList

8) Click Share WishList

9) In next page enter Email and a message and click Share WishList.

Expected Result:

Account registration done 
*/
public class TestCase5 {

	public static void main(String[] args) {
WebDriver driver = new FirefoxDriver();
driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));

driver.get("https://live.techpanda.org/index.php/");
driver.manage().window().maximize();

driver.findElement(By.linkText("ACCOUNT")).click();

driver.findElement(By.linkText("My Account")).click();

driver.findElement(By.xpath("//span[text()='Create an Account']")).click();
// NOTE: Please refill these fields with your information(credential) to automate this successfully.
driver.findElement(By.id("firstname")).sendKeys("Bushra"); 
driver.findElement(By.id("middlename")).sendKeys("F"); 
driver.findElement(By.id("lastname")).sendKeys("Azhari"); 
driver.findElement(By.id("password")).sendKeys("bushra123"); 
driver.findElement(By.id("confirmation")).sendKeys("bushra123"); 
driver.findElement(By.id("is_subscribed")).click(); 
driver.findElement(By.xpath("//span[text()='Register']")).click();

String emailError=driver.findElement(By.id("advice-required-entry-email_address")).getText();
if (emailError.equalsIgnoreCase("This is a required field.")) {
	System.out.println("Validation for Registration without Email - Failed");
} else {
	System.out.println("Validation for Registration without Email - Passed");
} 
//To make Registration Done Successfully, we must fill mandatory field.
driver.findElement(By.id("email_address")).sendKeys("bushra123@testing.com"); 
driver.findElement(By.xpath("//button[@title='Register']")).click();
driver.switchTo().alert().accept();
String validateRegistration = driver.findElement(By.xpath("//span[text()='Thank you for registering with Main Website Store.']")).getText();
if (validateRegistration.contains("Thank you for registering")) {
	System.out.println("Validation for Registration with Email - Passed");
} else { 
	System.out.println("Validation for Registration with Email - Failed");
}
WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

String registrationDone = wait
        .until(ExpectedConditions.visibilityOfElementLocated(
                By.xpath("//li[contains(@class,'success-msg')]")))
        .getText();

if (registrationDone.toLowerCase().contains("thank you for registering")) {
    System.out.println("Account Registration Done!");
    System.out.println(registrationDone);
} else {
    System.out.println("Account Registration Failed!");
    System.out.println("Actual message: " + registrationDone);
}

driver.findElement(By.linkText("TV")).click();
driver.findElement(By.linkText("Add to Wishlist")).click();
driver.findElement(By.xpath("//button[@title='Share Wishlist']")).click();
driver.switchTo().alert().accept();
driver.findElement(By.id("email_address")).sendKeys("firdous@testing.com" + "," + "azhari@testing.com"); 
driver.findElement(By.id("message")).sendKeys("Hopefully, You loved the product!"); 
driver.findElement(By.xpath("//button[@title='Share Wishlist']")).click();
driver.switchTo().alert().accept();

driver.quit();
	}

}
