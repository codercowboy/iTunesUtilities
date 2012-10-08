package com.worldsworstsoftware.itunes.parser;

import com.worldsworstsoftware.itunes.ItunesLibrary;
import com.worldsworstsoftware.itunes.parser.logging.NullParserStatusUpdateLogger;
import com.worldsworstsoftware.itunes.parser.logging.ParserStatusMessage;
import com.worldsworstsoftware.itunes.parser.logging.ParserStatusUpdateLogger;
import com.worldsworstsoftware.util.PerformanceTimer;
import com.worldsworstsoftware.xmltagparser.SimpleXMLTagParser;

//TODO: javadoc

public class ItunesLibraryParser
{
	public static ItunesLibrary parseLibrary(String itunesLibraryFilePath)
	{
		return parseLibrary(itunesLibraryFilePath, null);
	}	
	
	public static ItunesLibrary parseLibrary(String itunesLibraryFilePath, ParserStatusUpdateLogger logger)
	{		
		
		if (logger == null)
		{
			logger = new NullParserStatusUpdateLogger();
		}
		
		ItunesLibrary library = new ItunesLibrary();
		
		ItunesTagHandler tagHandler = new ItunesTagHandler(library, logger);
		
		SimpleXMLTagParser parser = new SimpleXMLTagParser(itunesLibraryFilePath, tagHandler, logger, false);       
		
		PerformanceTimer timer = new PerformanceTimer();
		
		logger.statusUpdate(ParserStatusMessage.PARSING_STARTED, itunesLibraryFilePath);
		logger.statusUpdate(ParserStatusMessage.PARSING_LIBRARY, null);
		try
        {
            parser.parse();
        }
        catch (Exception e)
        {
            throw new RuntimeException("Error occurred during library parsing: " + e.getMessage(), e);
        }

        logger.statusUpdate(ParserStatusMessage.PARSING_FINISHED, "(" +  library.getTracks().size() + " tracks and " + library.getPlaylists().size() + " playlists parsed.)");
        logger.statusUpdate(ParserStatusMessage.PARSE_TIME_MESSAGE, "Total Parsing Time: " + timer.getTimeElapsedSinceLastReset());

		
		return library;
	}
}
