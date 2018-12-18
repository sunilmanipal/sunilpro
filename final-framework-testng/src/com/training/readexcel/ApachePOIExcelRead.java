package com.training.readexcel;

import java.io.File;
import java.io.FileInputStream;
import java.util.Iterator;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

/**
 * 
 * @author Naveen
 * @see this class will take the records from excel sheet, and return it as list
 *      of list of object, and can be generic, can given any records until it
 *      exists. Test it with main method provided, and the path is hard coded,
 *      participatns are asked to refractor this path in the property file and
 *      access.
 */
public class ApachePOIExcelRead {
	public  String [][] getExcelContent(String fileName) {
		int rowCount =0; 
		String [][] list1 = null; 
		
		try {
			System.out.println("File Name Got " + fileName);
			FileInputStream file = new FileInputStream(new File(fileName));

			// Create Workbook instance holding reference to .xlsx file
			XSSFWorkbook workbook = new XSSFWorkbook(file);

			// Get first/desired sheet from the workbook
			XSSFSheet sheet = workbook.getSheetAt(0);
			
			int rowTotal = sheet.getLastRowNum();

			if ((rowTotal > 0) || (sheet.getPhysicalNumberOfRows() > 0)) {
			    rowTotal++;
			}
			
			
			// Iterate through each rows one by one
			Iterator<Row> rowIterator = sheet.iterator();
			 list1 = new String[rowTotal][2];
			 
			while (rowIterator.hasNext()) {
				Row row = rowIterator.next();
				// For each row, iterate through all the columns
				Iterator<Cell> cellIterator = row.cellIterator();

				int cellCount = 0; 
				int noOfColumns = row.getLastCellNum(); 
				String[] tempList1 = new String[noOfColumns];
				
				
				
				while (cellIterator.hasNext()) {
					Cell cell = cellIterator.next();
					// Check the cell type and format accordingly
					switch (cell.getCellType()) {

					case Cell.CELL_TYPE_NUMERIC:
						
						if(((Double) cell.getNumericCellValue()).toString()!=null){
							tempList1[cellCount] = ((Double) cell.getNumericCellValue()).toString(); 
						} 
						break;
					case Cell.CELL_TYPE_STRING:
						if(cell.getStringCellValue()!=null){
							tempList1[cellCount] =cell.getStringCellValue();
						}
						break;
					}
					cellCount ++; 
				}
				if(tempList1 != null){
					list1[rowCount++] = tempList1;
				}
			}
		
			
			file.close();
		} catch (Exception e) {
			e.printStackTrace();
		}

		return list1;
	}

	public static void main(String[] args) {
		String fileName = "C:/Users/Naveen/Desktop/Testing.xlsx";
		
		for(String [] temp : new ApachePOIExcelRead().getExcelContent(fileName)){
			for(String  tt : temp){
				System.out.println(tt);
			}
		}

	}
}