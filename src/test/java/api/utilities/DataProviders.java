package api.utilities;

import java.io.IOException;

import org.apache.poi.EncryptedDocumentException;
import org.testng.annotations.DataProvider;

public class DataProviders {
	
	@DataProvider(name="Data")
	public String [][] getAllData() throws EncryptedDocumentException, IOException
	{
		String path = System.getProperty("user.dir")+"//TestData//UserData.xlsx";
		
		ExcelUtility excel = new ExcelUtility(path);
		
		int rowCount = excel.getRowCount("Sheet1");
		int cellCount = excel.getCellCount("Sheet1", 1);
		
		String [][] apiData= new String [rowCount][cellCount];
		
		for(int i=1; i<=rowCount; i++)
		{
			for (int j=0; j<cellCount; j++)
			{
				apiData [i-1][j] =excel.getCellData("Sheet1", i, j);
			}
		}
		return apiData;
			
	}
	
	@DataProvider(name="UserNames")
	public String [] getUsers() throws EncryptedDocumentException, IOException
	{
		String path = System.getProperty("user.dir")+"//TestData//UserData.xlsx";
		
        ExcelUtility excel = new ExcelUtility(path);
        
		int rowCount = excel.getRowCount("Sheet1");
		String [] apiData= new String [rowCount];
		
		for(int i=1; i<=rowCount;i++)
		{
			apiData[i-1]=excel.getCellData("Sheet1", i, 1);
		}
		return apiData;
	}
	
}
