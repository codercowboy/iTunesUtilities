package com.worldsworstsoftware.logging;

import java.io.PrintStream;


public class DefaultStatusUpdateLogger implements StatusUpdateLogger
{
	protected boolean debug = false;
	protected PrintStream printStream = null;
	
	public DefaultStatusUpdateLogger()
	{
		this(false, System.out);
	}
	
	public DefaultStatusUpdateLogger(boolean debugMode, PrintStream printStream)
	{
		this.debug = debugMode;
		this.printStream = printStream;
	}

	public void debug(String debugMessage)
	{
		if (this.debug)
		{
			printStream.println("Debug Message: " + debugMessage);
		}		
	}

	public void error(String errorMessage, Exception e, boolean recoverableError)
	{
		printError("Error", errorMessage, e, recoverableError);
	}

	public void statusUpdate(int updateMessageType, String extraInfo)
	{
		printStream.println("Status Update: Message Id #" + updateMessageType + " info:" + extraInfo);		
	}

	public void fatal(String errorMessage, Exception e, boolean recoverableError)
	{
		printError("Fatal Error", errorMessage, e, recoverableError);
	}

	public void warn(String errorMessage, Exception e, boolean recoverableError)
	{
		printError("Warning", errorMessage, e, recoverableError);		
	}
	
	protected void printError(String errorType, String errorMessage, Exception e, boolean recoverableError)
	{
		if (recoverableError)
		{
			printStream.println("Recoverable " + errorType + ": " + errorMessage);
		}
		else
		{
			printStream.println("Non-Recoverable " + errorType + ": " + errorMessage);
		}
		
		if (e != null)
		{
			e.printStackTrace(System.out);
		}
	}
	

	

}
