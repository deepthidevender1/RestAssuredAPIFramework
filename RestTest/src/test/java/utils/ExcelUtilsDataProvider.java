package utils;

import java.io.IOException;
import java.util.Iterator;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class ExcelUtilsDataProvider {
	//This class is for using Dprovider and excelutil
	@Test(dataProvider = "testdata")
	public void test1(String username, String password)
	{
		System.out.println(username +"|" + password);
	}
	
	//gets the data abd from e
	@DataProvider(name = "testdata")
	public  Object[][] getdata() throws IOException
	{
		String Projpath = System.getProperty("user.dir");
		Object data[][] = testdata(Projpath  + "\\Excel\\Data.xlsx", "Sheet1");
		return data;
	}
	
	//Function to read the data from excel file
	public  Object[][] testdata(String excelPath, String SheetName) throws IOException
	
	{
		ExcelUtils excelutil = null;
		
			excelutil = new ExcelUtils(excelPath , SheetName);
			int rowCount =  excelutil.getRowCount();
			int colCount =  excelutil.getColCount();
			Object data[][] = new Object[rowCount-1] [colCount];
			for (int i = 1; i < rowCount; i++) 
			{
				for (int j = 0; j < colCount; j++) 
				{
					String celldata =  excelutil.getcelldataString(i, j);
					data[i-1][j] = celldata;
				}
				
			}

		return data;
		

		
	}
}
