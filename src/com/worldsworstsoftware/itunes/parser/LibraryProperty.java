package com.worldsworstsoftware.itunes.parser;

class LibraryProperty
{
	/*
		example library properties:
		<key>Major Version</key><integer>1</integer>
		<key>Minor Version</key><integer>1</integer>
		<key>Application Version</key><string>7.0.1</string>
		<key>Features</key><integer>1</integer>
		<key>Show Content Ratings</key><true/>
		<key>Music Folder</key><string>file://localhost/E:/itunes/</string>
		<key>Library Persistent ID</key><string>4EC2FAC25152379E</string>
	 */

	public static final String MAJOR_VERSION = "Major Version";
	public static final String MINOR_VERSION = "Minor Version";
	public static final String APPLICATION_VERSION = "Application Version";
	public static final String FEATURES = "Features";
	public static final String SHOW_CONTENT_RATINGS = "Show Content Ratings";
	public static final String MUSIC_FOLDER = "Music Folder";
	public static final String LIBRARY_PERSISTENT_ID = "Library Persistent ID";
	
	public static final int MAJOR_VERSION_HASH_CODE = MAJOR_VERSION.hashCode();
	public static final int MINOR_VERSION_HASH_CODE = MINOR_VERSION.hashCode();
	public static final int APPLICATION_VERSION_HASH_CODE = APPLICATION_VERSION.hashCode();
	public static final int FEATURES_HASH_CODE = FEATURES.hashCode();
	public static final int SHOW_CONTENT_RATINGS_HASH_CODE = SHOW_CONTENT_RATINGS.hashCode();
	public static final int MUSIC_FOLDER_HASH_CODE = MUSIC_FOLDER.hashCode();
	public static final int LIBRARY_PERSISTENT_ID_HASH_CODE = LIBRARY_PERSISTENT_ID.hashCode();
	
	public static final String TRACKS = "Tracks";
	public static final String PLAYLISTS = "Playlists";
}
