package com.ea.framework.utils;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;

import org.apache.commons.io.FileUtils;

import com.ea.framework.constants.FrameworkConstants;

public class LogUtils {


	private LogUtils() {}


	public static void deleteOldLogs()
	{
		File file = new File(FrameworkConstants.getTempLogPath());
		if((file.isDirectory()))
		{
			try {
				FileUtils.cleanDirectory(file);
				FileUtils.deleteDirectory(file);
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		
	}

	private static void mergeFile(File[] fileList,File logFile) 
	{
		try(PrintWriter pw = new PrintWriter(logFile)) 
		{
			for(int i=0;i<fileList.length;i++)
			{
				try(BufferedReader br = new BufferedReader(new FileReader(fileList[i].getAbsolutePath())))
				{
					String line = br.readLine();
					while(line!=null)
					{
						pw.println(line);
						line = br.readLine();
					}
				}
			}
			pw.flush();
		}
		catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	

	public static void generateLog()
	{
		ZonedDateTime date = ZonedDateTime.now();
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("ddMMyyyyHHMMSS");
		String fileNameFormat = date.format(formatter);
		File file = new File(FrameworkConstants.getTempLogPath());
		File[] fileList = file.listFiles();
		File logFile = new File(FrameworkConstants.getLogName()+"_"+fileNameFormat+".log");
		if(!(logFile.getParentFile().isDirectory()))
		{
			logFile.getParentFile().mkdir();
		}
		mergeFile(fileList,logFile);
		if(PropertyUtils.get("keeptemplog").equalsIgnoreCase("No"))
		{
			deleteOldLogs();
		}
	}

}

