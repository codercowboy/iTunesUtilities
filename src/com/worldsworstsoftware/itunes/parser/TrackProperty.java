package com.worldsworstsoftware.itunes.parser;

class TrackProperty
{
	/*
	example track properties:
	<key>Track ID</key><integer>401</integer>
	<key>Name</key><string>The Hollow</string>
	<key>Artist</key><string>A Perfect Circle</string>
	<key>Album</key><string>Mer de Noms</string>
	<key>Genre</key><string>Progressive Rock</string>
	<key>Kind</key><string>MPEG audio file</string>
	<key>Size</key><integer>4299529</integer>
	<key>Total Time</key><integer>179043</integer>
	<key>Track Number</key><integer>1</integer>
	<key>Track Count</key><integer>12</integer>
	<key>Year</key><integer>2000</integer>
	<key>Date Modified</key><date>2006-07-21T21:48:32Z</date>
	<key>Date Added</key><date>2005-10-20T02:43:19Z</date>
	<key>Bit Rate</key><integer>192</integer>
	<key>Sample Rate</key><integer>44100</integer>
	<key>Comments</key><string>Track 1</string>
	<key>Play Count</key><integer>5</integer>
	<key>Play Date</key><integer>3236345492</integer>
	<key>Play Date UTC</key><date>2006-07-21T21:51:32Z</date>
	<key>Persistent ID</key><string>51C977107B2FE940</string>
	<key>Track Type</key><string>File</string>
	<key>Location</key><string>file://localhost/E:/itunes/A%20Perfect%20Circle/Mer%20de%20Noms/01%20The%20Hollow.mp3</string>
	<key>File Folder Count</key><integer>4</integer>
	<key>Library Folder Count</key><integer>1</integer>
	<key>Disabled</key><true/>
	<key>Skip Count</key><integer>1</integer>
    <key>Skip Date</key><date>2007-04-21T00:25:49Z</date>
	<key>Composer</key><string>Trent Reznor</string>
	<key>Album Artist</key><string>The Red Jumpsuit Apparatus</string>
	<key>Artwork Count</key><integer>1</integer>
	<key>Grouping</key><string>Alternative General</string>
	<key>Disc Number</key><integer>1</integer>
	<key>Disc Count</key><integer>2</integer>
	<key>BPM</key><integer>192</integer>
	 */
	
	public static final String TRACK_ID = "Track ID";
	public static final String NAME = "Name";
	public static final String ARTIST = "Artist";
	public static final String ALBUM = "Album";
	public static final String GENRE = "Genre";
	public static final String KIND = "Kind";
	public static final String SIZE = "Size";
	public static final String TOTAL_TIME = "Total Time";
	public static final String NUMBER = "Track Number";
	public static final String COUNT = "Track Count";
	public static final String YEAR = "Year";
	public static final String DATE_MODIFIED = "Date Modified";
	public static final String DATE_ADDED = "Date Added";
	public static final String BIT_RATE = "Bit Rate";
	public static final String SAMPLE_RATE = "Sample Rate";
	public static final String COMMENTS = "Comments";
	public static final String PLAY_COUNT = "Play Count";
	public static final String PLAY_DATE = "Play Date";
	public static final String PLAY_DATE_UTC = "Play Date UTC";
	public static final String PERSISTENT_ID = "Persistent ID";
	public static final String TRACK_TYPE = "Track Type";
	public static final String LOCATION = "Location";
	public static final String FILE_FOLDER_COUNT = "File Folder Count";
	public static final String LIBRARY_FOLDER_COUNT = "Library Folder Count";
	public static final String DISABLED = "Disabled";
	public static final String SKIP_COUNT = "Skip Count";
	public static final String SKIP_DATE = "Skip Date";
	public static final String COMPOSER = "Composer";
	public static final String ALBUM_ARTIST = "Album Artist";
	public static final String ARTWORK_COUNT = "Artwork Count";
	public static final String GROUPING = "Grouping";
	public static final String DISC_NUMBER = "Disc Number";
	public static final String DISC_COUNT = "Disc Count";
	public static final String BPM = "BPM";
	
	
	/*
	
	<key>Skip Count</key><integer>1</integer>
    <key>Skip Date</key><date>2007-04-21T00:25:49Z</date>
	<key>Composer</key><string>Trent Reznor</string>
	<key>Album Artist</key><string>The Red Jumpsuit Apparatus</string>
	<key>Artwork Count</key><integer>1</integer>
	<key>Grouping</key><string>Alternative General</string>
	<key>Disc Number</key><integer>1</integer>
	<key>Disc Count</key><integer>2</integer>
	<key>BPM</key><integer>192</integer>
	 */
	
	public static final int TRACK_ID_HASH_CODE = TRACK_ID.hashCode();
	public static final int NAME_HASH_CODE = NAME.hashCode();
	public static final int ARTIST_HASH_CODE = ARTIST.hashCode();
	public static final int ALBUM_HASH_CODE = ALBUM.hashCode();
	public static final int GENRE_HASH_CODE = GENRE.hashCode();
	public static final int KIND_HASH_CODE = KIND.hashCode();
	public static final int SIZE_HASH_CODE = SIZE.hashCode();
	public static final int TOTAL_TIME_HASH_CODE = TOTAL_TIME.hashCode();
	public static final int NUMBER_HASH_CODE = NUMBER.hashCode();
	public static final int COUNT_HASH_CODE = COUNT.hashCode();
	public static final int YEAR_HASH_CODE = YEAR.hashCode();
	public static final int DATE_MODIFIED_HASH_CODE = DATE_MODIFIED.hashCode();
	public static final int DATE_ADDED_HASH_CODE = DATE_ADDED.hashCode();
	public static final int BIT_RATE_HASH_CODE = BIT_RATE.hashCode();
	public static final int SAMPLE_RATE_HASH_CODE = SAMPLE_RATE.hashCode();
	public static final int COMMENTS_HASH_CODE = COMMENTS.hashCode();
	public static final int PLAY_COUNT_HASH_CODE = PLAY_COUNT.hashCode();
	public static final int PLAY_DATE_HASH_CODE = PLAY_DATE.hashCode();
	public static final int PLAY_DATE_UTC_HASH_CODE = PLAY_DATE_UTC.hashCode();
	public static final int PERSISTENT_ID_HASH_CODE = PERSISTENT_ID.hashCode();
	public static final int TRACK_TYPE_HASH_CODE = TRACK_TYPE.hashCode();
	public static final int LOCATION_HASH_CODE = LOCATION.hashCode();
	public static final int FILE_FOLDER_COUNT_HASH_CODE = FILE_FOLDER_COUNT.hashCode();
	public static final int LIBRARY_FOLDER_COUNT_HASH_CODE = LIBRARY_FOLDER_COUNT.hashCode();	
	public static final int DISABLED_HASH_CODE = DISABLED.hashCode();
	public static final int SKIP_COUNT_HASH_CODE = SKIP_COUNT.hashCode();
	public static final int SKIP_DATE_HASH_CODE = SKIP_DATE.hashCode();
	public static final int COMPOSER_HASH_CODE = COMPOSER.hashCode();
	public static final int ALBUM_ARTIST_HASH_CODE = ALBUM_ARTIST.hashCode();
	public static final int ARTWORK_COUNT_HASH_CODE = ARTWORK_COUNT.hashCode();
	public static final int GROUPING_HASH_CODE = GROUPING.hashCode();
	public static final int DISC_NUMBER_HASH_CODE = DISC_NUMBER.hashCode();
	public static final int DISC_COUNT_HASH_CODE = DISC_COUNT.hashCode();
	public static final int BPM_HASH_CODE = BPM.hashCode();
}
