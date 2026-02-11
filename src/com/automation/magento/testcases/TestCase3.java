package com.automation.magento.testcases;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
/*
Test Scenario:

Verify that you cannot add more product in cart than the product availeable in store

Test Steps:

1) Go to https://live.techpanda.org/index.php/

2) Click on 'MOBILE' menu

3) In the list of all mobile, click on 'ADD TO CART' for Sony Xperia mobile

4) Change 'QTY' value to 1000 and click 'UPDATE' button

5) Verify error message

6) Then click on 'EMPTY CART' link in the footer of list of all mobiles

7) Verify cart is empty

Validation requirements:

a) On clicking update button an error is shown: 'The requested quantity for "Sony Xperia" is not available'
b) On clicking empty cart button, a message 'SHOPPING CART IS EMPTY' is shown
 */

public class TestCase3 {

    public static void main(String[] args) {
        WebDriver driver = new FirefoxDriver();
driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));	

driver.get("https://live.techpanda.org/index.php/");
driver.manage().window().maximize();

WebElement mobileLink=driver.findElement(By.linkText("MOBILE"));
mobileLink.click();

WebElement priceOnListing = driver.findElement(By.xpath("(//span[@class='price'])[1]"));
String listingPrice = priceOnListing.getText().trim();
System.out.println("Price on Listing Page: " +listingPrice); 

WebElement firstProduct = driver.findElement(By.xpath("(//h2[@class='product-name']/a)[1]"));
firstProduct.click();

WebElement qtyBox = driver.findElement(By.id("qty"));
qtyBox.clear();
qtyBox.sendKeys("1000");

WebElement addToCart = driver.findElement(By.xpath("//button[@class='button btn-cart']"));
addToCart.click();
driver.switchTo().alert().accept();

WebElement errorMsg = driver.findElement(By.xpath("//li[@class='error-msg']//span"));
String actualError = errorMsg.getText();

System.out.println("ERROR MESSAGE: " +actualError);

if (actualError.contains("maximum quantity")) {
	System.out.println("Proper stock validation message displayed.");
} else {
	System.out.println("Stock validation failed.");
}
driver.quit();
		
	}

}
