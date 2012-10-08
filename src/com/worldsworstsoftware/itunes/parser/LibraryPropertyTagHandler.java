package com.worldsworstsoftware.itunes.parser;

import java.util.HashMap;
import java.util.Map;

import com.worldsworstsoftware.itunes.ItunesLibrary;
import com.worldsworstsoftware.itunes.parser.logging.ParserStatusUpdateLogger;
import com.worldsworstsoftware.xmltagparser.Tag;

class LibraryPropertyTagHandler implements PropertyTagHandler
{

	protected static final String NO_PROPERTY = "NO PROPERTY";
	protected static final int NO_PROPERTY_HASH_CODE = NO_PROPERTY.hashCode();

	
	protected ItunesLibrary library = null;
	protected String currentProperty = NO_PROPERTY;
	protected int currentPropertyHashCode = NO_PROPERTY_HASH_CODE;
	
	protected static Map propertyMap = null;
	
	protected ParserStatusUpdateLogger logger = null;
	
	static
	{
		propertyMap = new HashMap();
		addPropertyToPropertyMap(LibraryProperty.MAJOR_VERSION);
		addPropertyToPropertyMap(LibraryProperty.MINOR_VERSION);
		addPropertyToPropertyMap(LibraryProperty.APPLICATION_VERSION);
		addPropertyToPropertyMap(LibraryProperty.FEATURES);
		addPropertyToPropertyMap(LibraryProperty.SHOW_CONTENT_RATINGS);
		addPropertyToPropertyMap(LibraryProperty.MUSIC_FOLDER);
		addPropertyToPropertyMap(LibraryProperty.LIBRARY_PERSISTENT_ID);				
	}
	
	private static void addPropertyToPropertyMap(String value)
	{
		propertyMap.put(value, value);
	}
	
	public LibraryPropertyTagHandler(ItunesLibrary library,ParserStatusUpdateLogger logger)
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
		
		String libraryProperty = (String) propertyMap.get(propertyName);
		
		if (libraryProperty != null)
		{
			currentProperty = libraryProperty;
			currentPropertyHashCode = libraryProperty.hashCode();
		}
		else
		{
			currentProperty = NO_PROPERTY;
			currentPropertyHashCode = NO_PROPERTY_HASH_CODE;
			System.out.println("Unsupported Itunes Library Property: " + propertyName);
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
		    else if (currentPropertyHashCode == LibraryProperty.MAJOR_VERSION_HASH_CODE)
			{
				//example property: <key>Major Version</key><integer>1</integer>
				library.setMajorVersion(DataTypeParser.parseInteger(LibraryProperty.MAJOR_VERSION, propertyValue));
			}
			else if (currentPropertyHashCode == LibraryProperty.MINOR_VERSION_HASH_CODE)
			{
				//example property: <key>Minor Version</key><integer>1</integer>
				library.setMinorVersion(DataTypeParser.parseInteger(LibraryProperty.MINOR_VERSION, propertyValue));
			}
			else if (currentPropertyHashCode == LibraryProperty.APPLICATION_VERSION_HASH_CODE)
			{
				//example property: <key>Application Version</key><string>7.0.1</string>
				library.setApplicationVersion(DataTypeParser.parseString(LibraryProperty.APPLICATION_VERSION, propertyValue));
			}
			else if (currentPropertyHashCode == LibraryProperty.FEATURES_HASH_CODE)
			{
				//example property: <key>Features</key><integer>1</integer>
				library.setFeatures(DataTypeParser.parseInteger(LibraryProperty.FEATURES, propertyValue));
			}
			else if (currentPropertyHashCode == LibraryProperty.SHOW_CONTENT_RATINGS_HASH_CODE)
			{
				//example property: <key>Show Content Ratings</key><true/>
				library.setShowContentRatings(DataTypeParser.parseBoolean(LibraryProperty.SHOW_CONTENT_RATINGS, propertyValue));
			}
			else if (currentPropertyHashCode == LibraryProperty.MUSIC_FOLDER_HASH_CODE)
			{
				//example property: <key>Music Folder</key><string>file://localhost/E:/itunes/</string>
				library.setMusicFolder(DataTypeParser.parseString(LibraryProperty.MUSIC_FOLDER, propertyValue));
			}
			else if (currentPropertyHashCode == LibraryProperty.LIBRARY_PERSISTENT_ID_HASH_CODE)
			{
				//example property: <key>Library Persistent ID</key><string>4EC2FAC25152379E</string>
				library.setLibraryPersistentID(DataTypeParser.parseString(LibraryProperty.LIBRARY_PERSISTENT_ID, propertyValue));
			}
			else
			{
				System.out.println("Supported Itunes Library Property Was Not Handled Correctly: " + currentProperty);
			}
		} 
		catch (Exception e)
		{
			logger.error("Error occured during library property parsing: " + e.getMessage(), e, true);
		}
		
		//now that we've handled the data for this property, we need to reset ourselves back to "no property"
		currentProperty = NO_PROPERTY;
		currentPropertyHashCode = NO_PROPERTY_HASH_CODE;

	}

}
