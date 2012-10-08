/*
 * Source code from iPlaylist Copier is (C) Jason Baker 2006
 * 
 * Please make an effort to document your additions to this source code file,
 * so future developers can give you credit where due.
 * 
 * Please include this copyright information in these source files when
 * redistributing source code. 
 *
 * Please make note of this copyright information in documentation for
 * binary redistributions that contain any or all of the source code. 
 *
 * If you are having any trouble understanding the meaning of this code
 * email jason directly at jason@onejasonforsale.com.
 *
 * Thanks, and happy coding!
 */


/*
 * LibraryType.java
 *
 * Created on November 6, 2005, 3:47 PM
 *
 * To change this template, choose Tools | Options and locate the template under
 * the Source Creation and Management node. Right-click the template and choose
 * Open. You can then make changes to the template in the Source Editor.
 */



package com.worldsworstsoftware.itunes;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

//TODO JAVADOC:

/**
 *
 * @author jason
 */
public class ItunesLibrary {
    
/*
	Example Itunes Library Properties:
	<key>Major Version</key><integer>1</integer>
	<key>Minor Version</key><integer>1</integer>
	<key>Application Version</key><string>7.0.1</string>
	<key>Features</key><integer>1</integer>
	<key>Show Content Ratings</key><true/>
	<key>Music Folder</key><string>file://localhost/E:/itunes/</string>
	<key>Library Persistent ID</key><string>4EC2FAC25152379E</string>
	
*/	
	
	protected int majorVersion = -1;
	protected int minorVersion = -1;
	protected String applicationVersion = null;
	protected int features = -1;
	protected boolean showContentRatings = false;
	protected String musicFolder = null;
	protected String libraryPersistentID = null;
	
	/** this stores the path the library was parsed from */
	protected String libraryXmlPath = null;
	
	protected Map tracks = new HashMap();
	protected ArrayList playlists = new ArrayList();
    /** Creates a new instance of LibraryType */
    public ItunesLibrary() {
    }
    
    public void addTrack(ItunesTrack track)
    {        
        tracks.put(new Integer(track.getTrackID()), track);
    }    
       
    public ItunesTrack getTrackById(int trackId)
    {
    	Integer key = new Integer(trackId);
        if (!tracks.containsKey(key))
        {
            throw new RuntimeException ("Can't find the track with id: " + trackId);
        }
        return (ItunesTrack) tracks.get(key);
    }
    
    public void addPlaylist(ItunesPlaylist playlist)
    {
        playlists.add(playlist);
    }
    
    public ArrayList getPlaylists()
    {
        return playlists;
    }
    
    public ItunesPlaylist findPlaylistByName(String playlistName)
    {
    	Iterator it = this.getPlaylists().iterator();
        while (it.hasNext())
        {
            ItunesPlaylist playlist = (ItunesPlaylist) it.next();
            if (playlist.getName().equals(playlistName))
            {
                return playlist;
            }           
        }
        throw new RuntimeException("Cannot find playlist with name \"" + playlistName + "\"");   
    }
    
    public ItunesPlaylist findPlayListByID(int playlistID)
    {
        Iterator it = this.getPlaylists().iterator();
        while (it.hasNext())
        {
            ItunesPlaylist playlist = (ItunesPlaylist) it.next();
            if (playlist.getPlaylistID() == playlistID)
            {
                return playlist;
            }
        }
        throw new RuntimeException("Cannot find playlist with id \"" + playlistID + "\"");
    }
    
    public Map getTracks()
    {
        return tracks;
    }

	public int getMajorVersion()
	{
		return majorVersion;
	}

	public void setMajorVersion(int majorVersion)
	{
		this.majorVersion = majorVersion;
	}

	public int getMinorVersion()
	{
		return minorVersion;
	}

	public void setMinorVersion(int minorVersion)
	{
		this.minorVersion = minorVersion;
	}

	public String getApplicationVersion()
	{
		return applicationVersion;
	}

	public void setApplicationVersion(String applicationVersion)
	{
		this.applicationVersion = applicationVersion;
	}

	public int getFeatures()
	{
		return features;
	}

	public void setFeatures(int features)
	{
		this.features = features;
	}

	public boolean isShowContentRatings()
	{
		return showContentRatings;
	}

	public void setShowContentRatings(boolean showContentRatings)
	{
		this.showContentRatings = showContentRatings;
	}

	public String getMusicFolder()
	{
		return musicFolder;
	}

	public void setMusicFolder(String musicFolder)
	{
		this.musicFolder = musicFolder;
	}

	public String getLibraryPersistentID()
	{
		return libraryPersistentID;
	}

	public void setLibraryPersistentID(String libraryPersistentID)
	{
		this.libraryPersistentID = libraryPersistentID;
	}

	public String getLibraryXmlPath()
	{
		return libraryXmlPath;
	}

	public void setLibraryXmlPath(String libraryXmlPath)
	{
		this.libraryXmlPath = libraryXmlPath;
	}
    
    
}
