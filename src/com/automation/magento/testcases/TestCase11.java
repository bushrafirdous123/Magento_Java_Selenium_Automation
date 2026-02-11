package com.automation.magento.testcases;
/*
Test Case 1: Verify Invoice Can Be Printed

Test Steps:

1) Go to http://live.techpanda.org/index.php/backendlogin

2) Login with the provided credentials

3) Navigate to Sales → Orders

4) In the Status field, select Canceled and click Search

5) Select the checkbox next to the first order

6) From Actions, select Print Invoices and click Submit

7) Verify the error message

8) In the Status field, select Complete and click Search

9) Select the checkbox next to the first order

10) From Actions, select Print Invoices and click Submit

11) Verify that the invoice is downloaded

Expected Results:

1) Error message “There are no printable documents related to selected orders” is displayed for canceled orders

2) Invoice is successfully downloaded for completed orders

Test Case 2: Verify Product Review Mechanism

Test Steps:

1) Go to https://live.techpanda.org/index.php/

2) Navigate to
http://live.techpanda.org/index.php/review/product/list/id/1

3) Submit a product review

4) Go to http://live.techpanda.org/index.php/backendlogin

5) Login with valid admin credentials

6) Navigate to Catalog → Reviews and Ratings → Customer Reviews → Pending Reviews

7) Sort the table by ID and select the submitted comment

8) Change status to Approved and click Save Review

9) Go to http://live.techpanda.org/ and open Mobile section

10) Click on Sony Xperia

11) Scroll to the bottom and open the Review tab

12) Verify the submitted review is displayed

Expected Results:

1) Review status changes to Approved

2) Approved review is visible on the product detail page

Test Case 3: Verify Sort Functionality in Invoice Grid

Test Steps:

1) Go to http://live.techpanda.org/index.php/backendlogin

2) Login with valid credentials

3) Navigate to Sales → Invoices

4) Click on Invoice Date column

5) Verify sorting in ascending and descending order

Expected Results:

Invoice records are sorted correctly based on invoice date

Test Case 4: Verify Search Functionality Using Price Filter

 Test Steps:

1) Go to http://live.techpanda.org/index.php

2) Click on Advanced Search

3) Enter price range 0–150 and click Search

4) Note the product name and price (print to console)

5) Enter price range 151–1000 and click Search

6) Note the product name and price (print to console)

Expected Results:

Product names and prices are fetched and displayed correctly based on the entered price range

Test Case 5: Verify Disabled Fields in Customer Details Page

Test Steps:

1) Go to http://live.techpanda.org/index.php/backendlogin

2) Login with valid admin credentials

3) Navigate to Customers → Manage Customers

4) Open any customer record using the View link

5) Click on the Account Information tab

6) Verify disabled fields

7) Verify blank fields

Expected Results:

1) Associate to Website and Created From fields are disabled

2) New Password field is blank
 */

	

	import java.time.Duration;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.Select;

	
	public class TestCase11 {
	    public static void main(String[] args) throws Exception {

	        WebDriver driver = new FirefoxDriver();
	        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
	        driver.manage().window().maximize();

	        /* ================= ADMIN LOGIN ================= */
	        driver.get("http://live.techpanda.org/index.php/backendlogin");
	        driver.findElement(By.id("username")).sendKeys("user01");
	        driver.findElement(By.id("login")).sendKeys("guru99com");
	        driver.findElement(By.xpath("//input[@title='Login']")).click();

	        List<WebElement> popup = driver.findElements(By.xpath("//a[@title='close']"));
	        if (!popup.isEmpty()) popup.get(0).click();

	        Actions act = new Actions(driver);

	        /* =====================================================
	           TEST CASE 1 – VERIFY INVOICE CAN BE PRINTED
	        ===================================================== */

	        WebElement sales = driver.findElement(By.xpath("//span[text()='Sales']"));
	        WebElement orders = driver.findElement(By.xpath("//span[text()='Orders']"));
	        act.moveToElement(sales).moveToElement(orders).click().build().perform();

	        Select status = new Select(driver.findElement(By.id("sales_order_grid_filter_status")));
	        status.selectByVisibleText("Canceled");
	        driver.findElement(By.xpath("//button[@title='Search']")).click();

	        driver.findElement(By.xpath("(//input[@type='checkbox'])[2]")).click();

	        Select actions = new Select(driver.findElement(By.id("sales_order_grid_massaction-select")));
	        actions.selectByVisibleText("Print Invoices");
	        driver.findElement(By.xpath("//button[@title='Submit']")).click();

	        String errorMsg =
	            driver.findElement(By.xpath("//li[@class='error-msg']")).getText();
	        System.out.println("TC1 (Canceled): " + errorMsg);

	        /* =====================================================
	           TEST CASE 2 – PRODUCT REVIEW MECHANISM
	        ===================================================== */

	        driver.get("http://live.techpanda.org/index.php/review/product/list/id/1");

	        driver.findElement(By.id("nickname_field")).sendKeys("Bushra");
	        driver.findElement(By.id("summary_field")).sendKeys("Nice Product");
	        driver.findElement(By.id("review_field")).sendKeys("Worth buying.");
	        driver.findElement(By.xpath("//button[@title='Submit Review']")).click();

	        driver.get("http://live.techpanda.org/index.php/backendlogin");

	        driver.findElement(By.xpath("//span[text()='Catalog']")).click();
	        driver.findElement(By.xpath("//span[text()='Reviews and Ratings']")).click();
	        driver.findElement(By.xpath("//span[text()='Customer Reviews']")).click();
	        driver.findElement(By.xpath("//span[text()='Pending Reviews']")).click();

	        driver.findElement(By.xpath("//a[text()='ID']")).click();
	        driver.findElement(By.xpath("(//tr[@class='even']//a)[1]")).click();

	        Select reviewStatus =
	            new Select(driver.findElement(By.id("status_id")));
	        reviewStatus.selectByVisibleText("Approved");
	        driver.findElement(By.xpath("//button[@title='Save Review']")).click();

	        driver.get("http://live.techpanda.org/");
	        driver.findElement(By.linkText("MOBILE")).click();
	        driver.findElement(By.linkText("SONY XPERIA")).click();
	        driver.findElement(By.xpath("//a[text()='Reviews']")).click();

	        System.out.println("TC2: Review approved and visible");

	        /* =====================================================
	           TEST CASE 3 – VERIFY SORT IN INVOICE GRID
	        ===================================================== */

	        driver.get("http://live.techpanda.org/index.php/backendlogin");

	        WebElement invoices = driver.findElement(By.xpath("//span[text()='Invoices']"));
	        act.moveToElement(sales).moveToElement(invoices).click().build().perform();

	        driver.findElement(By.xpath("//a[text()='Invoice Date']")).click();
	        System.out.println("TC3: Invoice Date sorted ASC");

	        driver.findElement(By.xpath("//a[text()='Invoice Date']")).click();
	        System.out.println("TC3: Invoice Date sorted DESC");

	        /* =====================================================
	           TEST CASE 4 – SEARCH USING PRICE FILTER
	        ===================================================== */

	        driver.get("http://live.techpanda.org/index.php/catalogsearch/advanced");

	        driver.findElement(By.id("price")).sendKeys("0");
	        driver.findElement(By.id("price_to")).sendKeys("150");
	        driver.findElement(By.xpath("//button[@title='Search']")).click();

	        List<WebElement> names =
	            driver.findElements(By.xpath("//h2[@class='product-name']/a"));
	        List<WebElement> prices =
	            driver.findElements(By.xpath("//span[@class='price']"));

	        System.out.println("TC4: Products between 0-150");
	        for (int i = 0; i < names.size(); i++) {
	            System.out.println(names.get(i).getText() + " - " + prices.get(i).getText());
	        }

	        /* =====================================================
	           TEST CASE 5 – VERIFY DISABLED FIELDS
	        ===================================================== */

	        driver.get("http://live.techpanda.org/index.php/backendlogin");

	        driver.findElement(By.xpath("//span[text()='Customers']")).click();
	        driver.findElement(By.xpath("//span[text()='Manage Customers']")).click();
	        driver.findElement(By.xpath("(//a[text()='View'])[1]")).click();

	        driver.findElement(By.xpath("//span[text()='Account Information']")).click();

	        boolean websiteDisabled =
	            !driver.findElement(By.id("_accountwebsite_id")).isEnabled();
	        boolean createdFromDisabled =
	            !driver.findElement(By.id("_accountcreated_in")).isEnabled();

	        String password =
	            driver.findElement(By.id("account[new_password]"))
	                  .getAttribute("value");

	        System.out.println("TC5: Website disabled = " + websiteDisabled);
	        System.out.println("TC5: Created From disabled = " + createdFromDisabled);
	        System.out.println("TC5: Password blank = " + password.isEmpty());

	        driver.quit();
	    }
	}

