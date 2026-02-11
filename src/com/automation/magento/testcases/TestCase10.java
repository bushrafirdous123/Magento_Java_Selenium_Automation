package com.automation.magento.testcases;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.time.Duration;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.Select;
/*
Test Scenarios:
Export all Orders in CSV file and display these information in console

Test Steps:

1) Go to http://live.techpanda.org/index.php/backendlogin

2) Login the credentials provided

3) Go to Sales → Orders

4) Select CSV in “Export To” dropdown and click Export button

5) Read downloaded file and display order information in console

Login Details (remember to remove the quotes):

1) Username: user01

2) Password: guru99com

Expected Result:

1) Console displays all order information

2) Data is exported successfully
*/
public class TestCase10 {

    public static void main(String[] args) throws Exception {

        String downloadPath = "C:\\Downloads"; // change if needed

        FirefoxOptions options = new FirefoxOptions();
        options.addPreference("browser.download.folderList", 2);
        options.addPreference("browser.download.dir", downloadPath);
        options.addPreference("browser.helperApps.neverAsk.saveToDisk", "text/csv,application/csv");
        options.addPreference("pdfjs.disabled", true);

        WebDriver driver = new FirefoxDriver(options);
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));

        driver.get("http://live.techpanda.org/index.php/backendlogin");
        driver.manage().window().maximize();

        driver.findElement(By.id("username")).sendKeys("user01");
        driver.findElement(By.id("login")).sendKeys("guru99com");
        driver.findElement(By.xpath("//input[@title='Login']")).click();

    
        List<WebElement> closePopup = driver.findElements(
            By.xpath("//div[@id='message-popup-window']//a[@title='close']")
        );

        if (!closePopup.isEmpty()) {
            closePopup.get(0).click();
        }

        WebElement sales = driver.findElement(By.xpath("//span[text()='Sales']"));
        WebElement orders = driver.findElement(By.xpath("//span[text()='Orders']"));

        Actions act = new Actions(driver);
        act.moveToElement(sales).pause(Duration.ofSeconds(1))
           .moveToElement(orders).click().build().perform();

        Select exportDropdown = new Select(driver.findElement(By.id("sales_order_grid_export")));
        exportDropdown.selectByVisibleText("CSV");

        driver.findElement(By.xpath("//button[@title='Export']")).click();

        // wait for download
        Thread.sleep(5000);

        File csvFile = new File(downloadPath + "\\orders.csv");

        System.out.println("===== ORDER DETAILS =====");

        BufferedReader br = new BufferedReader(new FileReader(csvFile));
        String line;

        while ((line = br.readLine()) != null) {
            System.out.println(line);
        }

        br.close();
        driver.quit();
    }
}