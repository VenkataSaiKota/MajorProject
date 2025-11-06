//package ExcelFile;
//
//import java.io.FileInputStream;
//import java.io.IOException;
//
//import org.apache.poi.ss.usermodel.CellType;
//import org.apache.poi.ss.usermodel.Row;
//import org.apache.poi.ss.usermodel.Sheet;
//import org.apache.poi.ss.usermodel.Workbook;
//import org.apache.poi.xssf.usermodel.XSSFWorkbook;
//
//public class ExcelLoginReader {
//	public static final String basePath = "C:\\Users\\2440652\\Desktop\\SeleniumPractice\\MajorProject\\";
//    public static final String excelPath = basePath + "MajorExcel.xlsx";
//	 public void readExcelData() throws IOException {
//		 
//		    String name, org, phone, email, orgSize, interest;
//	        FileInputStream fis = new FileInputStream(excelPath);
//	        Workbook workbook = new XSSFWorkbook(fis);
//	        Sheet sheet = workbook.getSheet("Sheet1");
//	        Row row = sheet.getRow(1); // Read second row (index 1)
//
//	        name = row.getCell(0).getStringCellValue();
//	        org = row.getCell(1).getStringCellValue();
//	        phone = (row.getCell(2).getCellType() == CellType.NUMERIC)
//	                ? String.valueOf((long) row.getCell(2).getNumericCellValue())
//	                : row.getCell(2).getStringCellValue();
//	        email = row.getCell(3).getStringCellValue();
//	        orgSize = row.getCell(4).getStringCellValue();
//	        interest = row.getCell(5).getStringCellValue();
//
//	        workbook.close();
//	        fis.close();
//	        
//	    }
//}
