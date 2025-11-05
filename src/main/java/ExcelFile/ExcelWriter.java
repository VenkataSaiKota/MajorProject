package ExcelFile;

import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;

public class ExcelWriter {

    private static  Workbook workbook ;
    

		public static void resetWorkbook() {
		    try {
				workbook = new XSSFWorkbook();
			} catch (Exception e) {
				e.printStackTrace();
			} 
		}


    public static void writeHospitalNamesToSheet(List<String> hospitalNames) {
        Sheet sheet1 = workbook.createSheet("Hospital Names");
        Row headerRow = sheet1.createRow(0);
        headerRow.createCell(0).setCellValue("Hospital Name");

        int rowNum = 1;
        for (String name : hospitalNames) {
            Row row = sheet1.createRow(rowNum++);
            row.createCell(0).setCellValue(name);
        }

        sheet1.autoSizeColumn(0);
    }

    public static void writeCityNamesToSheet(List<String> cityNames) {
        Sheet sheet2 = workbook.createSheet("City Names");
        Row headerRow = sheet2.createRow(0);
        headerRow.createCell(0).setCellValue("City Name");

        int rowNum = 1;
        for (String name : cityNames) {
            Row row = sheet2.createRow(rowNum++);
            row.createCell(0).setCellValue(name);
        }

        sheet2.autoSizeColumn(0);
    }

    public static void saveWorkbook(String filePath) {
        try (FileOutputStream fileOut = new FileOutputStream(filePath)) {
            workbook.write(fileOut);
            workbook.close();
            System.out.println("Excel file saved to: " + filePath);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}