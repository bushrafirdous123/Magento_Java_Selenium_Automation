package com.automation.magento.testcases;

import java.time.Duration;
import java.util.Set;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
/*
Test Scenario:

Verify that you are able to compare two products

Test Steps:

1) Go to https://live.techpanda.org/index.php/

2) Click on 'MOBILE' menu

3) In the mobile products list, click on 'Add to Compare' for 2 mobiles

4) Click on 'COMPARE' button

5) Verify the pop-up window and check that the products are reflected in it

6) Close the pop-up window

Test data:

1) Phone 1: Sony Xperia

2) Phone 2: iPhone

Validation requirements:

a) A pop-up window opens with heading as 'COMPARE PRODUCTS' and the selected products are present in it

b) Pop-up window is closed.
*/


	public class TestCase4 {

	    public static void main(String[] args) {

	        WebDriver driver = new FirefoxDriver();
	        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
	        driver.manage().window().maximize();

	        driver.get("https://live.techpanda.org/index.php/");

	        driver.findElement(By.linkText("MOBILE")).click();

	        driver.findElement(By.xpath("(//a[text()='Add to Compare'])[1]")).click();
	        driver.findElement(By.xpath("(//a[text()='Add to Compare'])[2]")).click();

	        driver.findElement(By.xpath("//button[@title='Compare']")).click();

	        String parentWindow = driver.getWindowHandle();

	        Set<String> allWindows = driver.getWindowHandles();
	        for (String window : allWindows) {
	            if (!window.equals(parentWindow)) {
	                driver.switchTo().window(window);
	                break;
	            }
}
	        String heading = driver.findElement(By.xpath("//h1")).getText();
	        String popupPageText = driver.findElement(By.tagName("body")).getText();

	        if (heading.equals("COMPARE PRODUCTS")
	                && popupPageText.contains("Sony Xperia")
	                && popupPageText.contains("IPHONE")) {

	            System.out.println("Compare popup displayed correctly with selected products.");
	        } else {
	            System.out.println("Compare popup validation failed.");
	        }

	        driver.close();

	        driver.switchTo().window(parentWindow);

	        driver.quit();
	    }
	}