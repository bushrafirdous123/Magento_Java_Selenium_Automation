package com.automation.magento.testcases;
import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
/* 
 Test Scenario:

Verify that cost of product in list page and details page are equal

Test Steps:

1) Go to https://live.techpanda.org/index.php/

2) Click on 'MOBILE' menu

3) In the list of all mobile, read the cost of Sony Xperia mobile. Note this value

4) Click on Sony Xperia mobile

5) Read the Sony Xperia mobile from detail page

6) Compare value in step 3 & 5

Validation requirements:

a) Price on step 3 & 5 are the same
b) Product value in list and tealis page should be equal ($100)
*/
public class TestCase2 {
	
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

WebElement priceOnDetails = driver.findElement(By.xpath("//span[@class='price']"));
String detailsPrice = priceOnDetails.getText().trim();
System.out.println("Price on Details Page: " +detailsPrice);

if(listingPrice.equals(detailsPrice)) {
	System.out.println("Prices matched!");
} else {
	System.out.println("Price do not match!");
}
driver.quit();
	    }
	}


