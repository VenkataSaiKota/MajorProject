package Tests;

import Pages.DiagnosticPage;
import ExcelFile.ExcelWriter;
import Report.RM;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class DiagnosticTest extends HomePage {

    DiagnosticPage page;

    @BeforeMethod
    public void initPage() {
        page = new DiagnosticPage(driver);
    }

    @Test(priority = 0, groups = {"diagnostic"})
    public void testClickLabTests() throws InterruptedException {
        RM.createTest("Diagnostic Test - Click Lab Tests");
        RM.logInfo("Clicking on Lab Tests section...");

        try {
            page.clickLabTests();
            RM.logPass("Lab Tests section clicked successfully.");
        } catch (Exception e) {
            RM.logFail("Failed to click Lab Tests section: " + e.getMessage());
            throw e;
        }
    }

    @Test(priority = 1, groups = "diagnostic")
    public void testGetCityNames() {
        RM.createTest("Diagnostic Test - Get City Names");
        RM.logInfo("Fetching city names from diagnostic page...");

        try {
            page.getCityNames();
            RM.logPass("City names fetched successfully.");
        } catch (Exception e) {
            RM.logFail("Failed to fetch city names: " + e.getMessage());
            throw e;
        }
    }

    @Test(priority = 2, groups = "diagnostic")
    public void testWriteCityNamesToExcel() {
        RM.createTest("Diagnostic Test - Write City Names to Excel");
        RM.logInfo("Writing city names to Excel file...");

        try {
            ExcelWriter.writeCityNamesToSheet(DiagnosticPage.cityNames);
            ExcelWriter.saveWorkbook("C:\\Users\\2440652\\Desktop\\SeleniumPractice\\MajorProject\\Finding_Hospitals.xlsx");
            RM.logPass("City names written to Excel successfully.");
        } catch (Exception e) {
            RM.logFail("Failed to write city names to Excel: " + e.getMessage());
            throw e;
        }
    }
}