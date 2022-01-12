package com.ea.framework.utils;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.Properties;

import com.ea.framework.constants.FrameworkConstants;
import com.ea.framework.exceptions.PropertyFileUsageException;

public final class PropertyUtils {

	private PropertyUtils() {

	}	

	private static Properties property = new Properties();
	private static final Map<String,String> CONFIGMAP = new HashMap<>();

	static 
	{
		try (FileInputStream file = new FileInputStream(FrameworkConstants.getConfigFilePath());)
		{
			property.load(file);
			for(Object key: property.keySet())
			{
				CONFIGMAP.put(String.valueOf(key), String.valueOf(property.get(key)).trim());
			}
		} 
		catch (IOException e)
		{
			e.printStackTrace();
			System.exit(0);
		}
	}
	
	public static String get(String key)  
	{
		if(Objects.isNull(key) || Objects.isNull(CONFIGMAP.get(key)))
		{
			throw new PropertyFileUsageException("Property name " + key +" is not found in config file. Please check config file");
		}
		return CONFIGMAP.get(key);
	}

}
