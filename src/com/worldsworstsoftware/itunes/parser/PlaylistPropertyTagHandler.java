package com.worldsworstsoftware.itunes.parser;

import java.util.HashMap;
import java.util.Map;

import com.worldsworstsoftware.itunes.ItunesLibrary;
import com.worldsworstsoftware.itunes.ItunesPlaylist;
import com.worldsworstsoftware.itunes.parser.logging.ParserStatusMessage;
import com.worldsworstsoftware.itunes.parser.logging.ParserStatusUpdateLogger;
import com.worldsworstsoftware.xmltagparser.Tag;

class PlaylistPropertyTagHandler implements PropertyTagHandler
{
	protected static final String NO_PROPERTY = "NO PROPERTY";
	protected static final int NO_PROPERTY_HASH_CODE = NO_PROPERTY.hashCode();

	
	protected ItunesLibrary library = null;
	protected ItunesPlaylist currentPlaylist = null;
	
	protected String currentProperty = NO_PROPERTY;
	protected int currentPropertyHashCode = NO_PROPERTY_HASH_CODE;
	
	protected static Map propertyMap = null;
	
	protected ParserStatusUpdateLogger logger = null;
	
	protected int playlistParseCount = 0;
	
	static
	{
		propertyMap = new HashMap();
		addPropertyToPropertyMap(PlaylistProperty.NAME);
		addPropertyToPropertyMap(PlaylistProperty.PLAYLIST_ID);
		addPropertyToPropertyMap(PlaylistProperty.PLAYLIST_PERSISTENT_ID);
		addPropertyToPropertyMap(PlaylistProperty.VISIBLE);
		addPropertyToPropertyMap(PlaylistProperty.ALL_ITEMS);
		addPropertyToPropertyMap(PlaylistProperty.SMART_INFO);
		addPropertyToPropertyMap(PlaylistProperty.SMART_CRITERIA);
		addPropertyToPropertyMap(PlaylistProperty.PLAYLIST_ITEMS);
		addPropertyToPropertyMap(PlaylistProperty.MASTER);
		addPropertyToPropertyMap(PlaylistProperty.AUDIOBOOKS);
		addPropertyToPropertyMap(PlaylistProperty.MOVIES);
		addPropertyToPropertyMap(PlaylistProperty.MUSIC);
		addPropertyToPropertyMap(PlaylistProperty.PARTY_SHUFFLE);
		addPropertyToPropertyMap(PlaylistProperty.PODCASTS);
		addPropertyToPropertyMap(PlaylistProperty.TV_SHOWS);
		addPropertyToPropertyMap(PlaylistProperty.TRACK_ID);
		
	}
	
	private static void addPropertyToPropertyMap(String value)
	{
		propertyMap.put(value, value);
	}
	
	public PlaylistPropertyTagHandler(ItunesLibrary library, ParserStatusUpdateLogger logger)
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
		
		String playlistProperty = (String) propertyMap.get(propertyName);
		
		if (playlistProperty != null)
		{
			currentProperty = playlistProperty;
			currentPropertyHashCode = playlistProperty.hashCode();
		}
		else
		{
			currentProperty = NO_PROPERTY;
			currentPropertyHashCode = NO_PROPERTY_HASH_CODE;
			logger.debug("Unsupported Itunes Playlist Property: " + propertyName);
		}				
	}

	public void handlePropertyValue(Tag propertyValue)
	{		
		try
		{
			if (currentPropertyHashCode == NO_PROPERTY_HASH_CODE)
			{
				//don't do anything...
			}
			else if (currentPropertyHashCode == PlaylistProperty.NAME_HASH_CODE)
			{
				//example property: <key>Name</key><string>Library</string>
	
				//the Name tag  signifies a new playlist is being parsed..
				currentPlaylist = new ItunesPlaylist(library);
				
				//add a reference to the new playlist to the library.
				library.addPlaylist(currentPlaylist);	
				
				playlistParseCount++;
				
				int playlistParseupdateFrequency = logger.getPlaylistParseUpdateFrequency();
				if (playlistParseupdateFrequency != ParserStatusUpdateLogger.UPDATE_FREQUENCY_NEVER)
				{
					if (playlistParseCount % playlistParseupdateFrequency == 0)
					{
						logger.statusUpdate(ParserStatusMessage.PARSING_PLAYLISTS, String.valueOf(playlistParseCount));
					}
				}
					
				
				currentPlaylist.setName(DataTypeParser.parseString(PlaylistProperty.NAME, propertyValue));
			}
			else if (currentPropertyHashCode == PlaylistProperty.PLAYLIST_ID_HASH_CODE)
			{
				//example property: <key>Playlist ID</key><integer>4881</integer>
				currentPlaylist.setPlaylistID(DataTypeParser.parseInteger(PlaylistProperty.PLAYLIST_ID, propertyValue));
			}
			else if (currentPropertyHashCode == PlaylistProperty.PLAYLIST_PERSISTENT_ID_HASH_CODE)
			{
				//example property: <key>Playlist Persistent ID</key><string>4EC2FAC25152379F</string>
				currentPlaylist.setPlaylistPersistentId(DataTypeParser.parseString(PlaylistProperty.PLAYLIST_PERSISTENT_ID, propertyValue));
			}
			else if (currentPropertyHashCode == PlaylistProperty.VISIBLE_HASH_CODE)
			{
				//example property: <key>Visible</key><false/>
				currentPlaylist.setVisible(DataTypeParser.parseBoolean(PlaylistProperty.VISIBLE, propertyValue));
			}
			else if (currentPropertyHashCode == PlaylistProperty.ALL_ITEMS_HASH_CODE)
			{
				//example property: <key>All Items</key><true/>
				currentPlaylist.setAllItems(DataTypeParser.parseBoolean(PlaylistProperty.ALL_ITEMS, propertyValue));
			}
			else if (currentPropertyHashCode == PlaylistProperty.SMART_INFO_HASH_CODE)
			{
				//example property: <key>Smart Info</key><data>AQEAAwAAAAIAAAAZAA</data>
				currentPlaylist.setSmartInfo(DataTypeParser.parseBytes(PlaylistProperty.SMART_INFO, propertyValue));
			}
			else if (currentPropertyHashCode == PlaylistProperty.SMART_CRITERIA_HASH_CODE)
			{
				//example property: <key>Smart Criteria</key><data>U0xzdAABAAEAAAAB</data>
				currentPlaylist.setSmartCriteria(DataTypeParser.parseBytes(PlaylistProperty.SMART_CRITERIA, propertyValue));
			}
			else if (currentPropertyHashCode == PlaylistProperty.PLAYLIST_ITEMS_HASH_CODE)
			{
				//example property: <key>Playlist Items</key>
				//this signifies the start of the tracks for this playlist, do nothing..?
			}
			else if (currentPropertyHashCode == PlaylistProperty.MASTER_HASH_CODE)
			{
				//example property: <key>Master</key><true/>
				currentPlaylist.setMaster(DataTypeParser.parseBoolean(PlaylistProperty.MASTER, propertyValue));
			}
			else if (currentPropertyHashCode == PlaylistProperty.AUDIOBOOKS_HASH_CODE)
			{
				//example property: <key>Audiobooks</key><true/>
				currentPlaylist.setAudiobooks(DataTypeParser.parseBoolean(PlaylistProperty.AUDIOBOOKS, propertyValue));
			}
			else if (currentPropertyHashCode == PlaylistProperty.MOVIES_HASH_CODE)
			{
				//example property: <key>Movies</key><true/>
				currentPlaylist.setMovies(DataTypeParser.parseBoolean(PlaylistProperty.MOVIES, propertyValue));
			}
			else if (currentPropertyHashCode == PlaylistProperty.MUSIC_HASH_CODE)
			{
				//example property: <key>Music</key><true/>
				currentPlaylist.setMusic(DataTypeParser.parseBoolean(PlaylistProperty.MUSIC, propertyValue));
			}
			else if (currentPropertyHashCode == PlaylistProperty.PARTY_SHUFFLE_HASH_CODE)
			{
				//example property: <key>Party Shuffle</key><true/>
				currentPlaylist.setPartyShuffle(DataTypeParser.parseBoolean(PlaylistProperty.PARTY_SHUFFLE, propertyValue));
			}
			else if (currentPropertyHashCode == PlaylistProperty.PODCASTS_HASH_CODE)
			{
				//example property: <key>Podcasts</key><true/>
				currentPlaylist.setPodcasts(DataTypeParser.parseBoolean(PlaylistProperty.PODCASTS, propertyValue));
			}
			else if (currentPropertyHashCode == PlaylistProperty.TV_SHOWS_HASH_CODE)
			{
				//example property: <key>TV Shows</key><true/>
				currentPlaylist.setTvShows(DataTypeParser.parseBoolean(PlaylistProperty.TV_SHOWS, propertyValue));
			}
			else if (currentPropertyHashCode == PlaylistProperty.TRACK_ID_HASH_CODE)
			{
				//example property: <key>Track ID</key><integer>4002</integer>
				currentPlaylist.addTrackID(DataTypeParser.parseInteger(PlaylistProperty.TRACK_ID, propertyValue));
			}				
			else
			{
				logger.debug("Supported Itunes Playlist Property Was Not Handled Correctly: " + currentProperty);
			}
		} 
		catch (Exception e)
		{
			logger.error("Error occured during playlist property parsing: " + e.getMessage(), e, true);
		}
		
		//now that we've handled the data for this property, we need to reset ourselves back to "no property"
		currentProperty = NO_PROPERTY;
		currentPropertyHashCode = NO_PROPERTY_HASH_CODE;
	}

}
