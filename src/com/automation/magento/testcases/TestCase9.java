package com.automation.magento.testcases;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

/*
 Test Scenarios:

 Verify Discount Coupon works correctly

Test Steps:

1) Go to https://live.techpanda.org/index.php/

2) Go to Mobile and add iPhone to cart

3) Enter Coupon Code

4) Verify Discount generated

Coupon Code: GURU50

Expected Result:

Price is discounted by 5%
 */
public class TestCase9 {

	public static void main(String[] args) {
		WebDriver driver = new FirefoxDriver();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));

		driver.get("https://live.techpanda.org/index.php/");
		driver.manage().window().maximize();
		
 driver.findElement(By.linkText("MOBILE")).click();
 driver.findElement(By.xpath("(//button[@title='Add to Cart'])[2]")).click();
 String subTotalText = driver.findElement(
         By.xpath("//td[@class='product-cart-total']/span")
 ).getText(); // $500.00

 double subTotal = Double.parseDouble(subTotalText.replace("$", ""));
 driver.findElement(By.xpath("//input[@id='coupon_code']")).sendKeys("GURU50");
 driver.findElement(By.xpath("//button[@onclick='discountForm.submit(false)']")).click();
 driver.switchTo().alert().accept();
 String discountText = driver.findElement(
	        By.xpath("//tr[td[contains(text(),'Discount')]]/td[2]/span")
	).getText();   // -$25.00

 double discount = Double.parseDouble(discountText.replace("-$", ""));
 double expectedDiscount = subTotal * 0.05;

 if (discount == expectedDiscount) {
     System.out.println("Discount Coupon Applied Correctly - Passed");
     System.out.println("Expected Discount: $" + expectedDiscount);
 } else {
     System.out.println("Discount Verification Failed");
 }

 driver.quit();
}
}