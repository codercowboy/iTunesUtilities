package com.worldsworstsoftware.itunes.parser;

import com.worldsworstsoftware.xmltagparser.Tag;

class DataTypeParser
{

	public static int parseInteger(String propertyName, Tag propertyValue) throws Exception
	{
		if (!propertyValue.getName().equals(TagType.INTEGER))
		{
			throwTypeMismatchError(propertyName, TagType.INTEGER, propertyValue);
		}

		try
		{
			return Integer.parseInt(propertyValue.getInnerText());
		}
		catch (NumberFormatException e)
		{
			throw new Exception("NumberFormatException occurred while trying to parse property \"" + propertyName + "\"'s integer value from the following string:" + propertyValue.getInnerText());			
		}
	}
	

	public static long parseLong(String propertyName, Tag propertyValue) throws Exception
	{
		if (!propertyValue.getName().equals(TagType.INTEGER))
		{
			throwTypeMismatchError(propertyName, TagType.INTEGER, propertyValue);
			return Long.MIN_VALUE;
		}

		try
		{
			return Long.parseLong(propertyValue.getInnerText());
		}
		catch (NumberFormatException e)
		{
			throw new Exception("NumberFormatException occurred while trying to parse property \"" + propertyName + "\"'s integer value from the following string:" + propertyValue.getInnerText());			
		}
	}
	
	public static String parseDate(String propertyName, Tag propertyValue) throws Exception
	{
		if (!propertyValue.getName().equals(TagType.DATE))
		{
			throwTypeMismatchError(propertyName, TagType.DATE, propertyValue);
		}
		
		return propertyValue.getInnerText();
	}
	
	public static String parseString(String propertyName, Tag propertyValue) throws Exception
	{
		if (!propertyValue.getName().equals(TagType.STRING))
		{
			throwTypeMismatchError(propertyName, TagType.STRING, propertyValue);
		}
		
		return propertyValue.getInnerText();
	}
	
	public static boolean parseBoolean(String propertyName, Tag propertyValue) throws Exception
	{
		if (!propertyValue.getName().equals(TagType.TRUE) && !propertyValue.getName().equals(TagType.FALSE))
		{
			throwTypeMismatchError(propertyName, "boolean value of 'true' or 'false'", propertyValue);
		}
		
		if (propertyValue.getName().equals(TagType.TRUE))
		{
			return true;
		}
		else if (propertyValue.getName().equals(TagType.FALSE))
		{
			return false;
		}
		else
		{
			throw new Exception("Could not parse boolean value for property \"" + propertyName + "\" from the following tag: " + propertyValue);
		}
	}
	
	public static byte[] parseBytes(String propertyName, Tag propertyValue) throws Exception
	{
		if (!propertyValue.getName().equals(TagType.DATA))
		{
			throwTypeMismatchError(propertyName, TagType.DATA, propertyValue);
		}
				
		return propertyValue.getInnerText().getBytes();		
	}
	
	protected static void throwTypeMismatchError(String propertyName, String dataType, Tag propertyValue) throws Exception
	{
		throw new Exception("Parsing Exception while parsing property \"" + propertyName + "\", property value found is not " + dataType + ": " + propertyValue);
	}
}
