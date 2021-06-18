package utils;

import java.io.IOException;

import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class ExcelUtils {

	private static Workbook workbook =  null;
	private static Sheet sheet = null;
	static String Projpath;
	
	public ExcelUtils(String excelPath, String SheetName) throws IOException 
	{
		workbook = new XSSFWorkbook(excelPath);
		sheet = workbook.getSheet(SheetName);
	}
	
//Function to get Row count
	public  int getRowCount() throws IOException
	{

		int NumberofRows =  sheet.getPhysicalNumberOfRows();

		return NumberofRows;

	}
	//Function to get Col count
	public  int getColCount() throws IOException
	{

		int NumberofCols =  sheet.getRow(0).getPhysicalNumberOfCells();

		return NumberofCols;

	}
	//Function to get the string data
	public  String getcelldataString(int rowNum, int colNum ) throws IOException
	{
	
		String celldata =  sheet.getRow(rowNum).getCell(colNum).getStringCellValue();
			
		return celldata;
		
	}
	//Function to get the Numeric data
	public  int getcelldataNumeric(int rowNum, int colNum ) throws IOException
	{
		
		int celldata =  (int) sheet.getRow(rowNum).getCell(colNum).getNumericCellValue();
		
		return celldata;
		
	}
	
	public  String getcelldataValue(int rowNum, int colNum ) throws IOException
	{
		String celldata;
		CellType type = sheet.getRow(rowNum).getCell(colNum).getCellType();
		
		
		if (type == CellType.NUMERIC ) {
			System.out.println("Numeric");
			 celldata = String.valueOf(getcelldataNumeric ( rowNum,  colNum ));
		} else {
			 celldata =  getcelldataString ( rowNum,  colNum );
			
			
		}
		
		return  celldata;
		
	}
	
	
}
