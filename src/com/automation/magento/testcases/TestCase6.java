package com.automation.magento.testcases;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.Select;

/*
 Test Scenario:

Verify user is able to purchase product using registered user

Test Steps:

1) Go to https://live.techpanda.org/index.php/

2) Click on My Account link

3) Login in application using previously created credential

4) Click on MY WISHLIST link

5) Click ADD TO CART link

6) Enter Shipping Info

7) Click Estimate Shipping and Tax

8) Verify Shipping cost generated

9) Select Shipping Cost, Update Total

10) Verify Shipping cost is added to total

11) Click Proceed to Checkout

12) Enter Billing Information

13) In Shipping Method, click Continue

14) In Payment Information, select Check/Money Order radio button

15) Click Place Order button

Test Data:

1) User Credentials: created in test case 5

2) Shipping Information:

    a) Country: United States

    b) State: New York

    c) Zip: 542896

Expected Result:

1) Flat Rate Shipping of $5 is generated

2) Shipping cost is added to total product cost

3) Order is placed successfully 
 */
public class TestCase6 {

	public static void main(String[] args) throws InterruptedException {
		WebDriver driver = new FirefoxDriver();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));

		driver.get("https://live.techpanda.org/index.php/");
		driver.manage().window().maximize();
		
		driver.findElement(By.linkText("ACCOUNT")).click();
		driver.findElement(By.linkText("Log In")).click();
		
		driver.findElement(By.xpath("//input[@class='input-text required-entry validate-email']")).sendKeys("bushra123@testing.com");
		driver.findElement(By.xpath("//input[@class='input-text required-entry validate-password']")).sendKeys("bushra123");
		driver.findElement(By.xpath("//button[@id='send2']")).click();

		driver.switchTo().alert().accept();

		driver.findElement(By.linkText("MY WISHLIST")).click();
driver.findElement(By.xpath("//button[@title='Add to Cart']")).click();

WebElement cDropDown=driver.findElement(By.xpath("//select[@id='country']"));
Select drpCountry = new Select(cDropDown);
drpCountry.selectByValue("US");

WebElement sDropDown=driver.findElement(By.xpath("//select[@id='region_id']"));
Select drpState = new Select(sDropDown);
//drpState.selectByContainsVisibleText("New York");
drpState.selectByValue("43");

driver.findElement(By.xpath("//input[@id='postcode']")).sendKeys("542896");

driver.findElement(By.xpath("//button[@title='Estimate']")).click();
Thread.sleep(2000);
driver.switchTo().alert().accept();
String shippingCostVerification = driver.findElement(
	    By.xpath("//form[@id='co-shipping-method-form']")
	).getText();
if (shippingCostVerification.contains("Flat Rate")) {
    System.out.println("Flat Rate Shipping of $5 Verified - Passed");
} else {
    System.out.println("Shipping Cost Verification Failed");
}
driver.findElement(By.xpath("//input[@id='s_method_flatrate_flatrate']")).click();

driver.findElement(By.xpath("//button[@title='Update Total']")).click();
driver.switchTo().alert().accept();

String shippingAmount = driver.findElement(By.xpath("//table[@id='shopping-cart-totals-table']")
	).getText();

	if (shippingAmount.contains("$5.00")) {
	    System.out.println("Shipping Cost is Successfully Added to the Total.");
	}
	driver.findElement(By.xpath("//button[@title='Proceed to Checkout']")).click();
	
	driver.findElement(By.id("billing:firstname")).clear();
	driver.findElement(By.id("billing:firstname")).sendKeys("Bushra");

	driver.findElement(By.id("billing:middlename")).clear();
	driver.findElement(By.id("billing:middlename")).sendKeys("F");

	driver.findElement(By.id("billing:lastname")).clear();
	driver.findElement(By.id("billing:lastname")).sendKeys("Azhari");

	driver.findElement(By.id("billing:company")).clear();
	driver.findElement(By.id("billing:company")).sendKeys("Firdousia");

	driver.findElement(By.id("billing:street1")).clear();
	driver.findElement(By.id("billing:street1")).sendKeys("123 Main Street");

	driver.findElement(By.id("billing:city")).clear();
	driver.findElement(By.id("billing:city")).sendKeys("New York");

	new Select(driver.findElement(By.id("billing:region_id")))
	        .selectByVisibleText("New York");

	driver.findElement(By.id("billing:postcode")).clear();
	driver.findElement(By.id("billing:postcode")).sendKeys("10001");

	new Select(driver.findElement(By.id("billing:country_id")))
	        .selectByVisibleText("United States");

	driver.findElement(By.id("billing:telephone")).clear();
	driver.findElement(By.id("billing:telephone")).sendKeys("6465553890");
	
	driver.findElement(By.cssSelector("button[onclick='billing.save()']")).click();
	// NOTE: Checkout does not proceed further due to mixed content issue on site
    for (String handle : driver.getWindowHandles()) {  
    	driver.switchTo().window(handle);
    	}  
    Thread.sleep(2000); 
    
    
    driver.findElement(By.xpath(".//*[@id='shipping-method-buttons-container']/button")).click(); 	
   	 
    Thread.sleep(2000);
    
    driver.findElement(By.xpath("//input[@title='Check / Money order']")).click();
    
    Thread.sleep(3000);	    
    
    driver.findElement(By.xpath(".//*[@id='payment-buttons-container']/button")).click(); 
     
    Thread.sleep(3000);
    
    driver.findElement(By.xpath(".//*[@id='review-buttons-container']/button")).click(); 
    
    Thread.sleep(3000);
    
    String orderNum = driver.findElement(By.xpath(".//*[@id='top']/body/div[1]/div/div[2]/div/div/p[1]/a")).getText();	
    System.out.println("*** Your order number for your record = " + orderNum);
   
    driver.quit();
  }

}