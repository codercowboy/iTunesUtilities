package com.worldsworstsoftware.itunes.parser;

import java.util.HashMap;
import java.util.Map;

import com.worldsworstsoftware.itunes.ItunesLibrary;
import com.worldsworstsoftware.itunes.ItunesTrack;
import com.worldsworstsoftware.itunes.parser.logging.ParserStatusMessage;
import com.worldsworstsoftware.itunes.parser.logging.ParserStatusUpdateLogger;
import com.worldsworstsoftware.xmltagparser.Tag;

class TrackPropertyTagHandler implements PropertyTagHandler
{
	protected static final String NO_PROPERTY = "NO PROPERTY";
	protected static final int NO_PROPERTY_HASH_CODE = NO_PROPERTY.hashCode();
	
	protected ItunesLibrary library = null;
	protected ItunesTrack currentTrack = null;
	
	protected String currentProperty = NO_PROPERTY;
	protected int currentPropertyHashCode = NO_PROPERTY_HASH_CODE;
	
	protected static Map propertyMap = null;	
	
	protected ParserStatusUpdateLogger logger = null;
	
	protected int trackParseCount = 0;
	
	static
	{
		propertyMap = new HashMap();
		addPropertyToPropertyMap(TrackProperty.TRACK_ID);
		addPropertyToPropertyMap(TrackProperty.NAME);
		addPropertyToPropertyMap(TrackProperty.ARTIST);
		addPropertyToPropertyMap(TrackProperty.ALBUM);
		addPropertyToPropertyMap(TrackProperty.GENRE);
		addPropertyToPropertyMap(TrackProperty.KIND);
		addPropertyToPropertyMap(TrackProperty.SIZE);
		addPropertyToPropertyMap(TrackProperty.TOTAL_TIME);
		addPropertyToPropertyMap(TrackProperty.NUMBER);
		addPropertyToPropertyMap(TrackProperty.COUNT);
		addPropertyToPropertyMap(TrackProperty.YEAR);
		addPropertyToPropertyMap(TrackProperty.DATE_MODIFIED);
		addPropertyToPropertyMap(TrackProperty.DATE_ADDED);
		addPropertyToPropertyMap(TrackProperty.BIT_RATE);
		addPropertyToPropertyMap(TrackProperty.SAMPLE_RATE);
		addPropertyToPropertyMap(TrackProperty.COMMENTS);
		addPropertyToPropertyMap(TrackProperty.PLAY_COUNT);
		addPropertyToPropertyMap(TrackProperty.PLAY_DATE);
		addPropertyToPropertyMap(TrackProperty.PLAY_DATE_UTC);
		addPropertyToPropertyMap(TrackProperty.PERSISTENT_ID);
		addPropertyToPropertyMap(TrackProperty.TRACK_TYPE);
		addPropertyToPropertyMap(TrackProperty.LOCATION);
		addPropertyToPropertyMap(TrackProperty.FILE_FOLDER_COUNT);
		addPropertyToPropertyMap(TrackProperty.LIBRARY_FOLDER_COUNT);
		addPropertyToPropertyMap(TrackProperty.DISABLED);		
		addPropertyToPropertyMap(TrackProperty.SKIP_COUNT);
		addPropertyToPropertyMap(TrackProperty.SKIP_DATE);
		addPropertyToPropertyMap(TrackProperty.COMPOSER);
		addPropertyToPropertyMap(TrackProperty.ALBUM_ARTIST);
		addPropertyToPropertyMap(TrackProperty.ARTWORK_COUNT);
		addPropertyToPropertyMap(TrackProperty.GROUPING);
		addPropertyToPropertyMap(TrackProperty.DISC_NUMBER);
		addPropertyToPropertyMap(TrackProperty.DISC_COUNT);
		addPropertyToPropertyMap(TrackProperty.BPM);							
	}
	
	private static void addPropertyToPropertyMap(String value)
	{
		propertyMap.put(value, value);
	}
	
	public TrackPropertyTagHandler(ItunesLibrary library, ParserStatusUpdateLogger logger)
	{
		this.library = library;
		this.logger = logger;
	}
	
	public void handlePropertyChange(String propertyName)
	{
		/**
		 * we store the list of valid properties in the internal property map,
		 * if the given propertyName is not found in that map, then we know we're
		 * dealing with an unsupported property and shouldn't worry about it in 
		 * handlePropertyValue().
		 */
		
		String trackProperty = (String) propertyMap.get(propertyName);
		
		if (trackProperty != null)
		{
			currentProperty = trackProperty;
			currentPropertyHashCode = trackProperty.hashCode();
		}
		else
		{
			currentProperty = NO_PROPERTY;
			currentPropertyHashCode = NO_PROPERTY_HASH_CODE;
			/*
			 * the tracks area of the xml has stuff like <key>401<key> which is a 
			 * track id per track.. we don't need to print thouse as unsupported track properties..
			 */
			if (!stringIsNumeric(propertyName))
			{
				logger.debug("Unsupported Itunes Track Property: " + propertyName);
			}
		}				
	}
	
	private boolean stringIsNumeric(String value)
	{
		try
		{
			Long.parseLong(value);
			return true;
		}
		catch (NumberFormatException e)
		{
			return false;
		}
	}

	public void handlePropertyValue(Tag propertyValue)
	{
		try
		{
			if (currentPropertyHashCode == NO_PROPERTY_HASH_CODE)
			{
				// don't do anything...
			} 
			else if (currentPropertyHashCode == TrackProperty.TRACK_ID_HASH_CODE)
			{
				// example property: <key>Track ID</key><integer>401</integer>

				// the track id signifies a new track is being parsed
				currentTrack = new ItunesTrack();

				// the library's internal map of tracks is keyed off of the
				// track id, so let's set that first
				currentTrack.setTrackID(DataTypeParser.parseInteger(
						TrackProperty.TRACK_ID, propertyValue));

				// add a reference to the track to the library's list..
				library.addTrack(currentTrack);

				trackParseCount++;
				int trackParseupdateFrequency = logger
						.getTrackParseUpdateFrequency();
				if (trackParseupdateFrequency != ParserStatusUpdateLogger.UPDATE_FREQUENCY_NEVER)
				{
					if (trackParseCount % trackParseupdateFrequency == 0)
					{
						logger.statusUpdate(ParserStatusMessage.PARSING_TRACKS,
								String.valueOf(trackParseCount));
					}
				}
			} 
			else if (currentPropertyHashCode == TrackProperty.NAME_HASH_CODE)
			{
				// example property: <key>Name</key><string>The Hollow</string>
				currentTrack.setName(DataTypeParser.parseString(
						TrackProperty.NAME, propertyValue));
			} 
			else if (currentPropertyHashCode == TrackProperty.ARTIST_HASH_CODE)
			{
				// example property: <key>Artist</key><string>A Perfect
				// Circle</string>
				currentTrack.setArtist(DataTypeParser.parseString(
						TrackProperty.ARTIST, propertyValue));
			} 
			else if (currentPropertyHashCode == TrackProperty.ALBUM_HASH_CODE)
			{
				// example property: <key>Album</key><string>Mer de
				// Noms</string>
				currentTrack.setAlbum(DataTypeParser.parseString(
						TrackProperty.ALBUM, propertyValue));
			} 
			else if (currentPropertyHashCode == TrackProperty.GENRE_HASH_CODE)
			{
				// example property: <key>Genre</key><string>Progressive
				// Rock</string>
				currentTrack.setGenre(DataTypeParser.parseString(
						TrackProperty.GENRE, propertyValue));
			} 
			else if (currentPropertyHashCode == TrackProperty.KIND_HASH_CODE)
			{
				// example property: <key>Kind</key><string>MPEG audio
				// file</string>
				currentTrack.setKind(DataTypeParser.parseString(
						TrackProperty.KIND, propertyValue));
			} 
			else if (currentPropertyHashCode == TrackProperty.SIZE_HASH_CODE)
			{
				// example property: <key>Size</key><integer>4299529</integer>
				currentTrack.setSize(DataTypeParser.parseInteger(
						TrackProperty.SIZE, propertyValue));
			} 
			else if (currentPropertyHashCode == TrackProperty.TOTAL_TIME_HASH_CODE)
			{
				// example property: <key>Total
				// Time</key><integer>179043</integer>
				currentTrack.setTotalTime(DataTypeParser.parseInteger(
						TrackProperty.TOTAL_TIME, propertyValue));
			} 
			else if (currentPropertyHashCode == TrackProperty.NUMBER_HASH_CODE)
			{
				// example property: <key>Track Number</key><integer>1</integer>
				currentTrack.setTrackNumber(DataTypeParser.parseInteger(
						TrackProperty.NUMBER, propertyValue));
			} 
			else if (currentPropertyHashCode == TrackProperty.COUNT_HASH_CODE)
			{
				// example property: <key>Track Count</key><integer>12</integer>
				currentTrack.setTrackCount(DataTypeParser.parseInteger(
						TrackProperty.COUNT, propertyValue));
			} 
			else if (currentPropertyHashCode == TrackProperty.YEAR_HASH_CODE)
			{
				// example property: <key>Year</key><integer>2000</integer>
				currentTrack.setYear(DataTypeParser.parseInteger(
						TrackProperty.YEAR, propertyValue));
			} 
			else if (currentPropertyHashCode == TrackProperty.DATE_MODIFIED_HASH_CODE)
			{
				// example property: <key>Date
				// Modified</key><date>2006-07-21T21:48:32Z</date>
				currentTrack.setDateModified(DataTypeParser.parseDate(
						TrackProperty.DATE_MODIFIED, propertyValue));
			} 
			else if (currentPropertyHashCode == TrackProperty.DATE_ADDED_HASH_CODE)
			{
				// example property: <key>Date
				// Added</key><date>2005-10-20T02:43:19Z</date>
				currentTrack.setDateAdded(DataTypeParser.parseDate(
						TrackProperty.DATE_ADDED, propertyValue));
			} 
			else if (currentPropertyHashCode == TrackProperty.BIT_RATE_HASH_CODE)
			{
				// example property: <key>Bit Rate</key><integer>192</integer>
				currentTrack.setBitRate(DataTypeParser.parseInteger(
						TrackProperty.BIT_RATE, propertyValue));
			} 
			else if (currentPropertyHashCode == TrackProperty.SAMPLE_RATE_HASH_CODE)
			{
				// example property: <key>Sample
				// Rate</key><integer>44100</integer>
				currentTrack.setSampleRate(DataTypeParser.parseInteger(
						TrackProperty.SAMPLE_RATE, propertyValue));
			} 
			else if (currentPropertyHashCode == TrackProperty.COMMENTS_HASH_CODE)
			{
				// example property: <key>Comments</key><string>Track 1</string>
				currentTrack.setComments(DataTypeParser.parseString(
						TrackProperty.COMMENTS, propertyValue));
			} 
			else if (currentPropertyHashCode == TrackProperty.PLAY_COUNT_HASH_CODE)
			{
				// example property: <key>Play Count</key><integer>5</integer>
				currentTrack.setPlayCount(DataTypeParser.parseInteger(
						TrackProperty.PLAY_COUNT, propertyValue));
			} 
			else if (currentPropertyHashCode == TrackProperty.PLAY_DATE_HASH_CODE)
			{
				// example property: <key>Play
				// Date</key><integer>3236345492</integer>
				currentTrack.setPlayDate(DataTypeParser.parseLong(
						TrackProperty.PLAY_DATE, propertyValue));
			} 
			else if (currentPropertyHashCode == TrackProperty.PLAY_DATE_UTC_HASH_CODE)
			{
				// example property: <key>Play Date
				// UTC</key><date>2006-07-21T21:51:32Z</date>
				currentTrack.setPlayDateUTC(DataTypeParser.parseDate(
						TrackProperty.PLAY_DATE_UTC, propertyValue));
			} 
			else if (currentPropertyHashCode == TrackProperty.PERSISTENT_ID_HASH_CODE)
			{
				// example property: <key>Persistent
				// ID</key><string>51C977107B2FE940</string>
				currentTrack.setPersistentID(DataTypeParser.parseString(
						TrackProperty.PERSISTENT_ID, propertyValue));
			} 
			else if (currentPropertyHashCode == TrackProperty.TRACK_TYPE_HASH_CODE)
			{
				// example property: <key>Track Type</key><string>File</string>
				currentTrack.setTrackType(DataTypeParser.parseString(
						TrackProperty.TRACK_TYPE, propertyValue));
			} 
			else if (currentPropertyHashCode == TrackProperty.LOCATION_HASH_CODE)
			{
				// example property:
				// <key>Location</key><string>file://localhost/E:/itunes/A%20Perfect%20Circle/Mer%20de%20Noms/01%20The%20Hollow.mp3</string>
				currentTrack.setLocation(DataTypeParser.parseString(
						TrackProperty.LOCATION, propertyValue));
			} 
			else if (currentPropertyHashCode == TrackProperty.FILE_FOLDER_COUNT_HASH_CODE)
			{
				// example property: <key>File Folder
				// Count</key><integer>4</integer>
				currentTrack.setFileFolderCount(DataTypeParser.parseInteger(
						TrackProperty.FILE_FOLDER_COUNT, propertyValue));
			} 
			else if (currentPropertyHashCode == TrackProperty.LIBRARY_FOLDER_COUNT_HASH_CODE)
			{
				// example property: <key>Library Folder
				// Count</key><integer>1</integer>
				currentTrack.setLibraryFolderCount(DataTypeParser.parseInteger(
						TrackProperty.LIBRARY_FOLDER_COUNT, propertyValue));
			} 
			else if (currentPropertyHashCode == TrackProperty.DISABLED_HASH_CODE)
			{
				// example property: <key>Disabled</key><true/>
				currentTrack.setDisabled(DataTypeParser.parseBoolean(
						TrackProperty.DISABLED, propertyValue));
			} 
			else if (currentPropertyHashCode == TrackProperty.SKIP_COUNT_HASH_CODE)
			{
				// example property: <key>Skip Count</key><integer>1</integer>
				currentTrack.setSkipCount(DataTypeParser.parseInteger(
						TrackProperty.SKIP_COUNT, propertyValue));
			} 
			else if (currentPropertyHashCode == TrackProperty.SKIP_DATE_HASH_CODE)
			{
				// example property: <key>Skip
				// Date</key><date>2007-04-21T00:25:49Z</date>
				currentTrack.setSkipDate(DataTypeParser.parseDate(
						TrackProperty.SKIP_DATE, propertyValue));
			} 
			else if (currentPropertyHashCode == TrackProperty.COMPOSER_HASH_CODE)
			{
				// example property: <key>Composer</key><string>Trent
				// Reznor</string>
				currentTrack.setComposer(DataTypeParser.parseString(
						TrackProperty.COMPOSER, propertyValue));
			} 
			else if (currentPropertyHashCode == TrackProperty.ALBUM_ARTIST_HASH_CODE)
			{
				// example property: <key>Album Artist</key><string>The Red
				// Jumpsuit Apparatus</string>
				currentTrack.setAlbumArtist(DataTypeParser.parseString(
						TrackProperty.ALBUM_ARTIST, propertyValue));
			} 
			else if (currentPropertyHashCode == TrackProperty.ARTWORK_COUNT_HASH_CODE)
			{
				// example property: <key>Artwork
				// Count</key><integer>1</integer>
				currentTrack.setArtworkCount(DataTypeParser.parseInteger(
						TrackProperty.ARTWORK_COUNT, propertyValue));
			} 
			else if (currentPropertyHashCode == TrackProperty.GROUPING_HASH_CODE)
			{
				// example property: <key>Grouping</key><string>Alternative
				// General</string>
				currentTrack.setGrouping(DataTypeParser.parseString(
						TrackProperty.GROUPING, propertyValue));
			} 
			else if (currentPropertyHashCode == TrackProperty.DISC_NUMBER_HASH_CODE)
			{
				// example property: <key>Disc Number</key><integer>1</integer>
				currentTrack.setDiscNumber(DataTypeParser.parseInteger(
						TrackProperty.DISC_NUMBER, propertyValue));
			} 
			else if (currentPropertyHashCode == TrackProperty.DISC_COUNT_HASH_CODE)
			{
				// example property: <key>Disc Count</key><integer>2</integer>
				currentTrack.setDiscCount(DataTypeParser.parseInteger(
						TrackProperty.DISC_COUNT, propertyValue));
			} 
			else if (currentPropertyHashCode == TrackProperty.BPM_HASH_CODE)
			{
				// example property: <key>BPM</key><integer>192</integer>
				currentTrack.setBPM(DataTypeParser.parseInteger(
						TrackProperty.BPM, propertyValue));
			} 
			else
			{
				logger
						.debug("Supported Itunes Track Property Was Not Handled Correctly: "
								+ currentProperty);
			}
		} 
		catch (Exception e)
		{
			logger.error("Error occured during track property parsing: " + e.getMessage(), e, true);
		}

		//now that we've handled the data for this property, we need to reset ourselves back to "no property"
		currentProperty = NO_PROPERTY;
		currentPropertyHashCode = NO_PROPERTY_HASH_CODE;
	}
}
