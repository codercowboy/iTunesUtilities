package com.worldsworstsoftware.itunes.parser.logging;

import java.io.PrintStream;

public class DefaultParserStatusUpdateLogger implements ParserStatusUpdateLogger
{
	protected boolean debug = false;
	protected PrintStream printStream = null;
	
	protected int trackParseUpdateFrequency = ParserStatusUpdateLogger.UPDATE_FREQUENCY_100;
	
	protected int playlistParseUpdateFrequency = ParserStatusUpdateLogger.UPDATE_FREQUENCY_5;
	
	public DefaultParserStatusUpdateLogger()
	{
		this(false, System.out);
	}
	
	public DefaultParserStatusUpdateLogger(boolean debugMode, PrintStream printStream)
	{
		this.debug = debugMode;
		this.printStream = printStream;
	}

	public void debug(String debugMessage)
	{
		if (this.debug)
		{
			printStream.println("Parsing Debug Message: " + debugMessage);
		}		
	}
	
	public int getPlaylistParseUpdateFrequency()
	{
		return this.playlistParseUpdateFrequency;
	}

	public int getTrackParseUpdateFrequency()
	{
		return this.trackParseUpdateFrequency;
	}	

	public void setTrackParseUpdateFrequency(int trackParseUpdateFrequency)
	{
		this.trackParseUpdateFrequency = trackParseUpdateFrequency;
	}

	public void setPlaylistParseUpdateFrequency(int playlistParseUpdateFrequency)
	{
		this.playlistParseUpdateFrequency = playlistParseUpdateFrequency;
	}

	public void statusUpdate(int updateMessageType, String extraInfo)
	{
		String message = "Parsing status update: ";
		switch (updateMessageType)
		{
			case ParserStatusMessage.PARSING_STARTED:
				message += "Parsing has started. Parsing library file \"" + extraInfo + "\".";
				break;
			case ParserStatusMessage.PARSING_LIBRARY:
				message += "Now parsing library properties.";
				break;
			case ParserStatusMessage.PARSING_TRACKS:
				message += "Now parsing tracks (" + extraInfo + " tracks parsed).";
				break;
			case ParserStatusMessage.PARSING_PLAYLISTS:
				message += "Now parsing playlists (" + extraInfo + " playlists parsed).";
				break;
			case ParserStatusMessage.PARSING_FINISHED:
				message += "Parsing has finished. " + extraInfo;
				break;
			case ParserStatusMessage.PARSE_TIME_MESSAGE:
				message += extraInfo;
				break;
		}
		
		//only print the message out if it's something we care about..
		if (!message.equals("Parsing status update: "))
		{
			printStream.println(message);
		}
		
	}

	public void error(String errorMessage, Exception e, boolean recoverableError)
	{
		printError("Error", errorMessage, e, recoverableError);
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
			printStream.println("Recoverable Parsing" + errorType + ": " + errorMessage);
		}
		else
		{
			printStream.println("Non-Recoverable Parsing" + errorType + ": " + errorMessage);
		}
		
		if (e != null)
		{
			e.printStackTrace(System.out);
		}
	}
}
