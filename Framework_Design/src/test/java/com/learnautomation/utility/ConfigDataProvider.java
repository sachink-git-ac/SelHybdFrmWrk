package com.learnautomation.utility;

import java.io.File;
import java.io.FileInputStream;
import java.util.Properties;

public class ConfigDataProvider
{
	Properties pro;
	
	public ConfigDataProvider()
	{
		File src=new File("./Config/Config.properties");
		
		try {
			FileInputStream fis=new FileInputStream(src);
			pro=new Properties();
			pro.load(fis);
		} catch (Exception e) {
			System.out.println("Configuration file could not be loaded, error is: "+e.getMessage());
		} 
	}
	
	public String getDataFromConfig(String key)
	{
		return pro.getProperty(key);
	}
	
	public String getBrowser()
	{
		return pro.getProperty("Browser");
	}
	
	public String getStageURL()
	{
		return pro.getProperty("qaUrL");
	}

}
