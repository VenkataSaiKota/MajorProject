package Tests;

import Pages.DiagnosticPage;
import ExcelFile.ExcelWriter;
import Report.ReportManager;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class DiagnosticTest extends HomePage {

    DiagnosticPage page;

    @BeforeMethod
    public void initPage() {
        page = new DiagnosticPage(driver);
    }

    @Test(priority = 0, groups = "diagnostic")
    public void testClickLabTests() throws InterruptedException {
        ReportManager.createTest("Diagnostic Test - Click Lab Tests");
        ReportManager.logInfo("Clicking on Lab Tests section...");

        try {
            page.clickLabTests();
            ReportManager.logPass("Lab Tests section clicked successfully.");
        } catch (Exception e) {
            ReportManager.logFail("Failed to click Lab Tests section: " + e.getMessage());
            throw e;
        }
    }

    @Test(priority = 1, groups = "diagnostic")
    public void testGetCityNames() {
        ReportManager.createTest("Diagnostic Test - Get City Names");
        ReportManager.logInfo("Fetching city names from diagnostic page...");

        try {
            page.getCityNames();
            ReportManager.logPass("City names fetched successfully.");
        } catch (Exception e) {
            ReportManager.logFail("Failed to fetch city names: " + e.getMessage());
            throw e;
        }
    }

    @Test(priority = 2, groups = "diagnostic")
    public void testWriteCityNamesToExcel() {
        ReportManager.createTest("Diagnostic Test - Write City Names to Excel");
        ReportManager.logInfo("Writing city names to Excel file...");

        try {
            ExcelWriter.writeCityNamesToSheet(DiagnosticPage.cityNames);
            ExcelWriter.saveWorkbook("C:/Users/2440823/eclipse-workspace/MajorProject/Finding_Hospitals.xlsx");
            ReportManager.logPass("City names written to Excel successfully.");
        } catch (Exception e) {
            ReportManager.logFail("Failed to write city names to Excel: " + e.getMessage());
            throw e;
        }
    }
}