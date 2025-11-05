package ExcelFile;
import java.io.FileInputStream;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import java.io.IOException;


public class ExcelReader {

	public static Object[][] getData(String filePath) throws IOException{
		 
		 Object[][] data = null;
		 
			 FileInputStream fis = new FileInputStream(filePath);
             try (Workbook workbook = new XSSFWorkbook(fis)) {
				Sheet sheet = workbook.getSheet("Sheet1");
				 int rows = sheet.getPhysicalNumberOfRows();
				 int cols = sheet.getRow(0).getLastCellNum();
				 data = new Object[rows][cols];
				 for(int i=0 ; i<rows ; i++) {
					 Row row = sheet.getRow(i);
					 for(int j=0 ; j<cols ; j++) {
						 Cell cell = row.getCell(j);
						 data[i][j] = cell.getStringCellValue();
					 }
				 }
			}

             return data;
		 }
	 }

