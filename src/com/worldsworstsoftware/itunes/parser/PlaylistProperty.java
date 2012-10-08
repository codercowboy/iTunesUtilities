package com.worldsworstsoftware.itunes.parser;

class PlaylistProperty
{
	/*
		example playlist properties:
		<key>Name</key><string>Library</string>
		<key>Playlist ID</key><integer>4881</integer>
		<key>Playlist Persistent ID</key><string>4EC2FAC25152379F</string>
		<key>Visible</key><false/>
		<key>All Items</key><true/>
		<key>Smart Info</key>
		<data>
		AQEAAwAAAAIAAAAZAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAA
		AAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAA
		AAAAAA==
		</data>
		<key>Smart Criteria</key>
		<data>
		U0xzdAABAAEAAAABAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAA
		AAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAA
		AAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAABAAAAIAAAAAAAAAAAAAAAAAAAAAAAAA
		AAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAABELa4tri2uLa7//////////wAAAAAAAVGA
		La4tri2uLa4AAAAAAAAAAAAAAAAAAAABAAAAAAAAAAAAAAAAAAAAAAAAAAA=
		</data>
		<key>Playlist Items</key>
		<key>Master</key><true/>
		<key>Audiobooks</key><true/>
		<key>Movies</key><true/>
		<key>Music</key><true/>
		<key>Party Shuffle</key><true/>
		<key>Podcasts</key><true/>
		<key>TV Shows</key><true/>
		
		<key>Track ID</key><integer>4002</integer>
	*/

	public static final String NAME = "Name";
	public static final String PLAYLIST_ID = "Playlist ID";
	public static final String PLAYLIST_PERSISTENT_ID = "Playlist Persistent ID";
	public static final String VISIBLE = "Visible";
	public static final String ALL_ITEMS = "All Items";
	public static final String SMART_INFO = "Smart Info";
	public static final String SMART_CRITERIA = "Smart Criteria";
	public static final String PLAYLIST_ITEMS = "Playlist Items";
	public static final String MASTER = "Master";
	public static final String AUDIOBOOKS = "Audiobooks";
	public static final String MOVIES = "Movies";
	public static final String MUSIC = "Music";
	public static final String PARTY_SHUFFLE = "Party Shuffle";
	public static final String PODCASTS = "Podcasts";
	public static final String TV_SHOWS = "TV Shows";
	public static final String TRACK_ID = "Track ID";
	
	public static final int NAME_HASH_CODE = NAME.hashCode();
	public static final int PLAYLIST_ID_HASH_CODE = PLAYLIST_ID.hashCode();
	public static final int PLAYLIST_PERSISTENT_ID_HASH_CODE = PLAYLIST_PERSISTENT_ID.hashCode();
	public static final int VISIBLE_HASH_CODE = VISIBLE.hashCode();
	public static final int ALL_ITEMS_HASH_CODE = ALL_ITEMS.hashCode();
	public static final int SMART_INFO_HASH_CODE = SMART_INFO.hashCode();
	public static final int SMART_CRITERIA_HASH_CODE = SMART_CRITERIA.hashCode();
	public static final int PLAYLIST_ITEMS_HASH_CODE = PLAYLIST_ITEMS.hashCode();
	public static final int MASTER_HASH_CODE = MASTER.hashCode();
	public static final int AUDIOBOOKS_HASH_CODE = AUDIOBOOKS.hashCode();
	public static final int MOVIES_HASH_CODE = MOVIES.hashCode();
	public static final int MUSIC_HASH_CODE = MUSIC.hashCode();
	public static final int PARTY_SHUFFLE_HASH_CODE = PARTY_SHUFFLE.hashCode();
	public static final int PODCASTS_HASH_CODE = PODCASTS.hashCode();
	public static final int TV_SHOWS_HASH_CODE = TV_SHOWS.hashCode();
	public static final int TRACK_ID_HASH_CODE = TRACK_ID.hashCode();
}
