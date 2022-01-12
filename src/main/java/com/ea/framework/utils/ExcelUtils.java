package com.ea.framework.utils;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import com.ea.framework.constants.FrameworkConstants;
import com.ea.framework.exceptions.FrameworkException;
import com.ea.framework.exceptions.InvalidPathForExcelException;

public class ExcelUtils {

	private ExcelUtils() {}
	
	

	public static List<Map<String,String>> getTestDetails()
	{
		List<Map<String,String>> list = null ;
		try(FileInputStream fs=new FileInputStream(FrameworkConstants.getRunManager())) {
			XSSFWorkbook wb = new XSSFWorkbook(fs);
			Map<String,String> map = null;
			list = new ArrayList<>();
			int sheetCount = wb.getNumberOfSheets();
			for(int k=0;k<sheetCount;k++)
			{
				XSSFSheet sheet = wb.getSheetAt(k);
				int lastrownum = sheet.getLastRowNum();
				int lastcolnum = sheet.getRow(0).getLastCellNum();
				for(int i=1;i<=lastrownum;i++)
				{
					map = new HashMap<>();

					for(int j=0;j<lastcolnum;j++)
					{
						String key = sheet.getRow(0).getCell(j).getStringCellValue();
						String value = "";
						Cell cell = sheet.getRow(i).getCell(j);
						if(cell.getCellType()==CellType.STRING)
						{
							value = cell.getStringCellValue();
						}
						else if(cell.getCellType()==CellType.NUMERIC)
						{
							int temp = (int) cell.getNumericCellValue();
							value=String.valueOf(temp);
						}
						else if(cell.getCellType()==CellType.BOOLEAN)
						{
							value = String.valueOf(cell.getBooleanCellValue());
						}
						else if(cell.getCellType()==CellType.BLANK)
						{
							value = "";
						}
						map.put(key, value);
					}
					list.add(map);
				}
			}
			wb.close();
		} 
		catch (FileNotFoundException e1) {
			throw new InvalidPathForExcelException("RunManager File you trying to read is not found");
		} 
		catch (IOException e) {
			throw new FrameworkException("Some issues occured while reading RunManager");
		}
		return list;
	}
}
