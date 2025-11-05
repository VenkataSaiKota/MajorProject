package Tests;

import Pages.HospitalSearchPage;
import ExcelFile.ExcelReader;
import ExcelFile.ExcelWriter;
import Report.ReportManager;
import org.testng.annotations.*;

import java.io.IOException;

public class HospitalSearchTest extends HomePage {

    HospitalSearchPage page;

    @BeforeMethod
    public void initPage() {
        page = new HospitalSearchPage(driver);
    }

    @Test(priority = 0, dataProvider = "excelData", groups = "hospital")
    public void testSetLocationAndSearch(String loc, String name) {
        ReportManager.createTest("Hospital Search - Set Location and Search");
        ReportManager.logInfo("Starting location and search for: " + loc + ", " + name);

        try {
            page.setLocation(loc);
            page.searchHospital(name);
            ReportManager.logPass("Location and hospital search completed successfully.");
        } catch (Exception e) {
            ReportManager.logFail("Failed to set location or search hospital: " + e.getMessage());
            throw e;
        }
    }

    @Test(priority = 1, groups = "hospital")
    public void testFilterHospitals() {
        ReportManager.createTest("Hospital Search - Filter Hospitals");
        ReportManager.logInfo("Filtering hospitals based on rating and availability...");

        try {
            page.filterHospitals();
            ReportManager.logPass("Hospital filtering completed.");
        } catch (Exception e) {
            ReportManager.logFail("Failed to filter hospitals: " + e.getMessage());
            throw e;
        }
    }

    @Test(priority = 2, groups = "hospital")
    public void testExtractHospitalNames() throws InterruptedException {
        ReportManager.createTest("Hospital Search - Extract Hospital Names");
        ReportManager.logInfo("Extracting hospital names with parking availability...");

        try {
            page.extractHospitalNames();
            ReportManager.logPass("Hospital names extracted successfully.");
        } catch (Exception e) {
            ReportManager.logFail("Failed to extract hospital names: " + e.getMessage());
            throw e;
        }
    }

    @Test(priority = 3, groups = "hospital")
    public void testWriteToExcel() {
        ReportManager.createTest("Hospital Search - Write to Excel");
        ReportManager.logInfo("Writing hospital names to Excel...");

        try {
            ExcelWriter.resetWorkbook();
            ExcelWriter.writeHospitalNamesToSheet(HospitalSearchPage.hospitalNames);
            page.navi();
            ReportManager.logPass("Hospital names written to Excel successfully.");
        } catch (Exception e) {
            ReportManager.logFail("Failed to write hospital names to Excel: " + e.getMessage());
            throw e;
        }
    }

    @DataProvider(name = "excelData")
    public Object[][] readExcelData() throws IOException {
        String filePath = "C:/Users/2440823/eclipse-workspace/MajorProject/DataLoc.xlsx";
        return ExcelReader.getData(filePath);
    }
}