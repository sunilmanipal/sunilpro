package com.training.readexcel;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

import org.testng.annotations.DataProvider;

import jxl.Sheet;
import jxl.Workbook;
import jxl.read.biff.BiffException;

public class ReadExcel {
	
	public String[][] getExcelData(String fileName, String sheetName) 
	   {
			String[][] arrayExcelData = null;
			try 
			{
				FileInputStream fs = new FileInputStream(fileName);
				Workbook wb = Workbook.getWorkbook(fs);
				Sheet sh = wb.getSheet(sheetName);

				int totalNoOfCols = sh.getColumns();
				int totalNoOfRows = sh.getRows();
				System.out.println("Total Number Of Rows : " +totalNoOfRows);
				System.out.println("Number of Cols " + totalNoOfCols);
				
				arrayExcelData = new String[totalNoOfRows-1][totalNoOfCols];
				
				for (int i= 1 ; i < totalNoOfRows; i++) 
				{
					for (int j=0; j < totalNoOfCols; j++) 
					{
						arrayExcelData[i-1][j] = sh.getCell(j, i).getContents();
					}
				}
			}catch (IOException | BiffException e) 
			{
				e.printStackTrace();
			}
			return arrayExcelData;
		}

	// Test method, change the path of the .xls file 
	public static void main(String[] args) {
		String[][] result = new ReadExcel().getExcelData("C:/Users/Naveen/Desktop/Testing.xls", "Sheet1"); 
		
		for(String [] temp : result){
			for(String temp1: temp){
				System.out.println(temp1);
			}
		}
	}
}
