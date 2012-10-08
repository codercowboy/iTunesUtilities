package com.worldsworstsoftware.logging;

public interface StatusUpdateLogger
{				
	public void debug(String debugMessage);
	public void warn(String errorMessage, Exception e, boolean recoverableError);	
	public void error(String errorMessage, Exception e, boolean recoverableError);
	public void fatal(String errorMessage, Exception e, boolean recoverableError);
	public void statusUpdate(int updateMessageType, String extraInfo);		
}
