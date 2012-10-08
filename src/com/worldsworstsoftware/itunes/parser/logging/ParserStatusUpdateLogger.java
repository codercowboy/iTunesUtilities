package com.worldsworstsoftware.itunes.parser.logging;

import com.worldsworstsoftware.logging.StatusUpdateLogger;

public interface ParserStatusUpdateLogger extends StatusUpdateLogger
{
	public static final int UPDATE_FREQUENCY_ALWAYS = 1;
	public static final int UPDATE_FREQUENCY_1000 = 1000;
	public static final int UPDATE_FREQUENCY_100 = 100;
	public static final int UPDATE_FREQUENCY_10 = 10;
	public static final int UPDATE_FREQUENCY_5 = 5;
	public static final int UPDATE_FREQUENCY_NEVER = -1;
	
	public int getTrackParseUpdateFrequency();
	public int getPlaylistParseUpdateFrequency();
}
