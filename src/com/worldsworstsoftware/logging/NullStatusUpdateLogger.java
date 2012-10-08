package com.worldsworstsoftware.logging;


public class NullStatusUpdateLogger implements StatusUpdateLogger
{

	public void debug(String debugMessage)
	{
		//do nothing..		
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
