package com.automation.magento.testcases;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;

/*
 Test Scenario:

Verify you are able to change or reorder previously added product.

Test Steps:

1) Go to https://live.techpanda.org/index.php/

2) Click on My Account link

3) Login in application using previously created credential

4) Click on Reorder link

5) Change Grand Total & click Update

6) Verify Grand Total is changed

7) Complete Billing & Shipping information

Expected Result:

1) Grand Total is changed

2) Order is placed successfully

QTY: 10
 */
public class TestCase8 {

	  public static void main(String[] args) throws InterruptedException {

	        WebDriver driver = new FirefoxDriver();
	        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
	        driver.manage().window().maximize();

	        // 1. Go to application
	        driver.get("//live.techpanda.org/index.php/");

	        // 2. Click on My Account
	        driver.findElement(By.linkText("ACCOUNT")).click();
	        driver.findElement(By.linkText("Log In")).click();

	        // 3. Login using existing credentials
	        driver.findElement(By.id("email")).sendKeys("bushra123@testing.com");
	        driver.findElement(By.id("pass")).sendKeys("bushra123");
	        driver.findElement(By.id("send2")).click();

	        // Handle alert if it appears
	        try {
	            driver.switchTo().alert().accept();
	        } catch (Exception e) {
	            // No alert present
	        }

	        // 4. Click on My Orders
	        driver.findElement(By.linkText("MY ORDERS")).click();

	        // 5. Click on Reorder link (first order)
	        driver.findElement(By.linkText("REORDER")).click();

	        // Capture old Grand Total
	        String oldTotal = driver.findElement(
	                By.xpath("//strong/span[@class='price']")
	        ).getText();

	        System.out.println("Old Grand Total = " + oldTotal);

	        // 6. Change Quantity to 10
	        WebElement qtyBox = driver.findElement(By.xpath("//input[@title='Qty']"));
	        qtyBox.clear();
	        qtyBox.sendKeys("10");

	        driver.findElement(By.xpath("//button[@title='Update']")).click();

	        // Capture new Grand Total
	        String newTotal = driver.findElement(
	                By.xpath("//strong/span[@class='price']")
	        ).getText();

	        System.out.println("New Grand Total = " + newTotal);

	        // 7. Verify Grand Total is changed
	        if (!oldTotal.equals(newTotal)) {
	            System.out.println("Grand Total changed successfully - PASSED");
	        } else {
	            System.out.println("Grand Total did not change - FAILED");
	        }

	        // 8. Proceed to Checkout
	        driver.findElement(By.xpath("//button[@title='Proceed to Checkout']")).click();
	        Thread.sleep(3000);

	        // 9. Billing Address – Continue
	        driver.findElement(By.xpath("//button[@onclick='billing.save()']")).click();
	        Thread.sleep(3000);

	        // 10. Shipping Method – Continue
	        driver.findElement(By.xpath("//div[@id='shipping-method-buttons-container']/button")).click();
	        Thread.sleep(3000);

	        // 11. Payment Method – Check / Money Order
	        driver.findElement(By.xpath("//input[@title='Check / Money order']")).click();
	        driver.findElement(By.xpath("//div[@id='payment-buttons-container']/button")).click();
	        Thread.sleep(3000);

	        // 12. Place Order
	        driver.findElement(By.xpath("//div[@id='review-buttons-container']/button")).click();
	        Thread.sleep(3000);

	        // 13. Verify order placed
	        String orderNumber = driver.findElement(
	                By.xpath("//p[contains(text(),'Your order #')]")
	        ).getText();

	        System.out.println(orderNumber);
	        System.out.println("Order placed successfully - PASSED");

	        driver.quit();
	    }
	}
