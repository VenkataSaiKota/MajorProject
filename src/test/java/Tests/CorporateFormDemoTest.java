package Tests;

import ExcelFile.ExcelReaderDemo;
import Pages.CorporateFormDemoPage;
import Report.RM;
import com.aventstack.extentreports.ExtentTest;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.*;

import java.io.File;
import java.io.IOException;
import java.time.Duration;

public class CorporateFormDemoTest extends HomePage {

    CorporateFormDemoPage formPage;
    public static final String screenshotPath = "C:\\Users\\2440652\\Desktop\\SeleniumPractice\\MajorProject\\CorporatePageScreenshot.png";
    ExtentTest test;

    @BeforeClass
    public void initPage() throws IOException {
        formPage = new CorporateFormDemoPage(driver);
    }

    @Test(dataProvider = "formDataProvider")
    public void fillFormAndCaptureScreenshot(String name, String org, String phone, String email, String orgSize, String interest) throws IOException {
        test = RM.extent.createTest("Corporate Form Submission Test");

        try {
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
           
            test.info("Filling out the corporate form with test data.");
            formPage.fillForm(name, org, phone, email, orgSize, interest);
            test.pass("Form fields filled successfully.");

            WebElement scheduleButton = formPage.getScheduleButton();
            ((JavascriptExecutor) driver).executeScript("window.scrollBy(0, 76);");
            test.info("Scrolled down to the Schedule Demo button.");

            wait.until(driver -> !scheduleButton.isEnabled());
            test.pass("Schedule button is disabled as expected.");

            File src = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
            File dest = new File(screenshotPath);
            FileUtils.copyFile(src, dest);
            test.pass("Screenshot captured and saved.");
            test.addScreenCaptureFromPath(screenshotPath);

            Assert.assertTrue(dest.exists() && dest.length() > 0, "Screenshot was not saved.");
            test.pass("Screenshot verification passed.");
            test.info("Screenshot saved at: " + screenshotPath);

        } catch (Exception e) {
            test.fail("Test failed due to exception: " + e.getMessage());
            throw e;
        }
    }

    @DataProvider(name = "formDataProvider")
    public Object[][] formDataProvider() throws IOException {
        return ExcelReaderDemo.getFormData();
    }
}
