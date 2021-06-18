package utils;

import java.io.IOException;

public class ExcelUtilsDemo {
	//This clss is s java program to test excel utils packahe
	static String Projpath;
	public static void main(String[] args) throws IOException {
		Projpath = System.getProperty("user.dir");
		ExcelUtils excel =  new  ExcelUtils(Projpath  + "\\Excel\\Data.xlsx" , "Sheet1");
		excel.getRowCount();
		excel.getcelldataString(0, 0);
		excel.getcelldataNumeric(1, 1);
	}
	
}

