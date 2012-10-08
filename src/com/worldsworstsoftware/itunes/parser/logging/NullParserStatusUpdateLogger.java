package com.worldsworstsoftware.itunes.parser.logging;

public class NullParserStatusUpdateLogger implements ParserStatusUpdateLogger
{

	public int getPlaylistParseUpdateFrequency()
	{
		return ParserStatusUpdateLogger.UPDATE_FREQUENCY_NEVER;
	}

	public int getTrackParseUpdateFrequency()
	{
		return ParserStatusUpdateLogger.UPDATE_FREQUENCY_NEVER;
	}

	public void debug(String debugMessage)
	{
		//do nothing		
	}

	public void error(String errorMessage, Exception e, boolean recoverableError)
	{
		//do nothing		
	}

	public void statusUpdate(int updateMessageType, String extraInfo)
	{
		//do nothing		
	}

	public void fatal(String errorMessage, Exception e, boolean recoverableError)
	{
		//do nothing				
	}

	public void warn(String errorMessage, Exception e, boolean recoverableError)
	{
		//do nothing				
	}

}
