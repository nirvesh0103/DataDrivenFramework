package utilities;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class ExcelUtils {
	private static XSSFWorkbook workbook;
	private static XSSFSheet worksheet;
	private static XSSFRow row;
	private static XSSFCell cell;

	public void setupExcelFile(String filePath, String worksheetName) throws IOException {

		File excelFile = new File(filePath);

		FileInputStream inputStream = new FileInputStream(excelFile);

		workbook = new XSSFWorkbook(inputStream);

		worksheet = workbook.getSheet(worksheetName);

	}

	public String getCellValue(int rowNumer, int cellNumber) {

		cell = worksheet.getRow(rowNumer).getCell(cellNumber);
		String cellValue = cell.getStringCellValue();
		System.out.println("Cell value is: " + cellValue);
		return cellValue;
	}

	public int getRowCountInExcelWorkSheet() {
		int rowCount = worksheet.getLastRowNum() - worksheet.getFirstRowNum();
		return rowCount;
	}

	public void setCellValue(int rowNum, int cellNum, String cellValue, String excelFilePath) throws IOException {

		row = worksheet.getRow(rowNum);
		row.createCell(cellNum).setCellValue(cellValue);

		FileOutputStream outputStream = new FileOutputStream(excelFilePath);
		workbook.write(outputStream);
	}
}
