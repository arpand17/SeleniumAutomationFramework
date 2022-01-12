package com.ea.framework.datareader;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.HashMap;
import java.util.Iterator;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import com.ea.framework.constants.FrameworkConstants;



public class ReadTestData {
	
	private ReadTestData() {}

	private static final HashMap<String, HashMap<String, String>> DATATABLE = readData();
	
	
	public static String getTestData(String key)
	{
		return DATATABLE.get(DataManager.gettestCaseName()).get(key);
	}
	
	
	
	private static  HashMap<String, HashMap<String, String> > readData() 
	{
	      HashMap<String,HashMap<String, String> > datatable = null ;
	      try(FileInputStream file = new FileInputStream(new File(FrameworkConstants.getTestData())))
	      {
	        XSSFWorkbook workbook = new XSSFWorkbook(file);
	        Iterator<Sheet> sheetIterator = workbook.iterator();
	        datatable = new HashMap<>();      
	        while (sheetIterator.hasNext())
	        {
			 Sheet sheet = sheetIterator.next();
			 int rowcount = sheet.getPhysicalNumberOfRows();
			 int columncount = sheet.getRow(0).getPhysicalNumberOfCells();
			 for(int i=1;i<rowcount;i++)
			 {
	           String TC_ID = sheet.getRow(i).getCell(0).getStringCellValue();
	           HashMap<String, String> data = new HashMap<>();
	           for(int j=0;j<columncount;j++)
	           {
				  String fieldName = sheet.getRow(0).getCell(j).getStringCellValue();
				  String fieldValue = "";
				  Cell cell = sheet.getRow(i).getCell(j);
				  if(cell.getCellType()==CellType.STRING)
				  {
				   fieldValue = cell.getStringCellValue();
				  }
				  else if(cell.getCellType()==CellType.NUMERIC)
				  {
					   int temp = (int) cell.getNumericCellValue();
					   fieldValue=String.valueOf(temp);
				  }
				  else if(cell.getCellType()==CellType.BOOLEAN)
				  {
				   fieldValue = String.valueOf(cell.getBooleanCellValue());
				  }
				  else if(cell.getCellType()==CellType.BLANK)
				  {
				   fieldValue = "";
				  }
				  data.put(fieldName, fieldValue) ;
	           }
	           datatable.put(TC_ID, data);
	         }
	        }
	             workbook.close();
	      }
	      catch (FileNotFoundException e1) {
				e1.printStackTrace();
			} 
			catch (IOException e) {
				e.printStackTrace();
			}
	      return datatable;
	 }

	
}
