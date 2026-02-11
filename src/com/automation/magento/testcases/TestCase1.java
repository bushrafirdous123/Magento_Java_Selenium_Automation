package com.automation.magento.testcases;

import java.time.Duration;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.Select;


/*Test Scenario:

Verify item in Mobile list page can be sorted by 'Name'.

Test Steps:

1) Go to https://live.techpanda.org/index.php/

2) Verify Title of the page

3) Click on 'MOBILE' menu

4) Verify Title of the page

5) In the list of all mobile, select 'SORT BY' dropdown as 'name'

6) Verify all products are sorted by name

Validation requirements:

a) Text 'THIS IS DEMO SITE' shown in home page

b) Title 'MOBILE' is shown on mobile list page

c) All 3 products sorted by name

Additional info:

The client has requested you to use WebDriver (Firefox Driver) for the project.
*/
public class TestCase1 {
public static void main (String[] args) throws InterruptedException{
	WebDriver driver = new FirefoxDriver();
	driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
	
	 driver.get("https://live.techpanda.org/index.php/");
	 driver.manage().window().maximize();
	 
	 String pageTitle = driver.getTitle();

	 if (pageTitle.contains("Home page")) {
	     System.out.println("Home Page validation PASSED");
	 } else {
	     System.out.println("Home Page validation FAILED");
	 }
	 
	driver.findElement(By.linkText("MOBILE")).click(); 
	
	String mobileTitle = driver.findElement(By.xpath("//h1")).getText();
	
	if (mobileTitle.equalsIgnoreCase("MOBILE")) {
		System.out.println("Validation Passed for Mobile Page Title");
	} else {
		System.out.println("Validation Failed for Mobile Page Title");
	}
	
	Select sortBy = new Select(driver.findElement(By.xpath("//select[@title='Sort By']")));
    sortBy.selectByVisibleText("Name"); 
	 
    List<WebElement> products = driver.findElements(By.xpath("//h2[@class='product-name']/a"));

    List<String> actualProductNames = new ArrayList<>();
    for (WebElement product : products) {
        actualProductNames.add(product.getText());
    }

    List<String> expectedProductNames = new ArrayList<>(actualProductNames);
    Collections.sort(expectedProductNames);

    if (actualProductNames.equals(expectedProductNames)) {
        System.out.println("Products are sorted by Name - PASSED");
    } else {
        System.out.println("Products are NOT sorted by Name - FAILED");
    }
	 driver.quit();
}
}
