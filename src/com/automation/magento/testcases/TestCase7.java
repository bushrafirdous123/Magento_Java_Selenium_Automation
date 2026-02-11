package com.automation.magento.testcases;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
/*
 Test Scenario:

Verify that you will be able to save previously placed order as a PDF file.

Test Steps:

1) Go to https://live.techpanda.org/index.php/

2) Click on My Account link

3) Login in application using previously created credential

4) Click on My Orders

5) Click on View Order

6) Verify the status is Pending

7) Click on Print Order and save as PDF

Expected Result:

1) Previously created order is displayed in RECENT ORDERS table and status is Pending

2) Order is saved as PDF

Browser:
Use Firefox
 */
public class TestCase7 {

    public void testTestCase7a() {

        WebDriver driver = new FirefoxDriver();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        driver.manage().window().maximize();

        // Step 1: Go to application
        driver.get("https://live.techpanda.org/index.php/");

        // Step 2: Click My Account
        driver.findElement(By.linkText("ACCOUNT")).click();
        driver.findElement(By.linkText("Log In")).click();

        // Step 3: Login
        driver.findElement(By.id("email")).sendKeys("bushra123@testing.com");
        driver.findElement(By.id("pass")).sendKeys("bushra123");
        driver.findElement(By.id("send2")).click();

        // Step 4: Click My Orders
        driver.findElement(By.linkText("MY ORDERS")).click();

        // Step 5: Click View Order
        driver.findElement(By.linkText("VIEW ORDER")).click();

        // Step 6: Verify Order Status = Pending
        String orderStatus = driver.findElement(
                By.xpath("//span[contains(text(),'Pending')]")).getText();

        if (orderStatus.equalsIgnoreCase("Pending")) {
            System.out.println("Order status is Pending - Passed");
        } else {
            System.out.println("Order status verification Failed");
        }

        // Step 7: Click Print Order
        driver.findElement(By.linkText("Print Order")).click();
        System.out.println("Print Order clicked successfully");

        /*
         EXPECTED RESULT:
         - Browser Print dialog opens
         - User can manually select 'Save as PDF'

         NOTE:
         Selenium cannot automate browser print or Save as PDF dialog
         because it is outside DOM (OS-level popup).
        */

        driver.quit();
    }
}


