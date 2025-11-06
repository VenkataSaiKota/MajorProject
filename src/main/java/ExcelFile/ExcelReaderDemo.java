package ExcelFile;

import java.io.FileInputStream;
import java.io.IOException;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
public class ExcelReaderDemo {
    public static final String excelPath = "C:\\Users\\2440652\\Desktop\\SeleniumPractice\\MajorProject\\MajorExcel.xlsx";

    public static Object[][] getFormData() throws IOException {
        FileInputStream fis = new FileInputStream(excelPath);
        Workbook workbook = new XSSFWorkbook(fis);
        Sheet sheet = workbook.getSheet("Sheet1");
        Row row = sheet.getRow(1); // Row 2 (index 1)

        String name = row.getCell(0).getStringCellValue();
        String org = row.getCell(1).getStringCellValue();
        String phone = (row.getCell(2).getCellType() == CellType.NUMERIC)
                ? String.valueOf(row.getCell(2).getNumericCellValue())
                : row.getCell(2).getStringCellValue();
        String email = row.getCell(3).getStringCellValue();
        String orgSize = row.getCell(4).getStringCellValue();
        String interest = row.getCell(5).getStringCellValue();

        workbook.close();
        fis.close();

        return new Object[][] {
            { name, org, phone, email, orgSize, interest }
        };
    }
}

