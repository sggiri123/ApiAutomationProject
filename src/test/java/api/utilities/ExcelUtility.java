package api.utilities;

import java.io.FileInputStream;
import java.io.IOException;

import org.apache.poi.EncryptedDocumentException;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;

public class ExcelUtility {
	
	public  FileInputStream file;
	public  Workbook wb;
	public  Sheet sheet;
	public  Row row;
	public Cell cell;
	public  String path;
	
	public ExcelUtility(String path)
	{
		this.path=path;
	}
	
   public int getRowCount(String sheetName) throws EncryptedDocumentException, IOException
   {
	   file = new FileInputStream(path);
	   wb = WorkbookFactory.create(file);
	   sheet = wb.getSheet(sheetName);
	   int rowCount = sheet.getLastRowNum();
	   wb.close();
	   file.close();
	   return rowCount;
   }
	
   public int getCellCount(String sheetName, int rowNumber) throws EncryptedDocumentException, IOException
   {
	   file = new FileInputStream(path);
	   wb = WorkbookFactory.create(file);
	   sheet = wb.getSheet(sheetName);
	   row = sheet.getRow(rowNumber);
	   int cellCount = row.getLastCellNum();
	   wb.close();
	   file.close();
	   return cellCount;
   }
	
   public String getCellData(String sheetName, int rowNumber, int cellNumber) throws EncryptedDocumentException, IOException
   {
	   file = new FileInputStream(path);
	   wb = WorkbookFactory.create(file);
	   sheet = wb.getSheet(sheetName);
	   row = sheet.getRow(rowNumber);
	   cell = row.getCell(cellNumber);
	  
	   DataFormatter formatter = new DataFormatter();
	   String data;
	   try {
		   data= formatter.formatCellValue(cell);
	   }
	   catch(Exception e) {
		   data="";   
	   }
	 
	   wb.close();
	   file.close();
	   return data;
   }
	
	
	
	
	
	
	
	

}
