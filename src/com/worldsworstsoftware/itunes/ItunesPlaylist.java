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
 * PlayListType.java
 *
 * Created on November 6, 2005, 3:32 PM
 *
 * To change this template, choose Tools | Options and locate the template under
 * the Source Creation and Management node. Right-click the template and choose
 * Open. You can then make changes to the template in the Source Editor.
 */

package com.worldsworstsoftware.itunes;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;


//TODO JAVADOC:

/**
 *
 * @author jason
 */
public class ItunesPlaylist
{
    
/*
	example playlist XML properties:
	<dict>
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
		<array>
			<dict>
				<key>Track ID</key><integer>4002</integer>
			</dict>
			<dict>
				<key>Track ID</key><integer>4003</integer>
			</dict>
		</array>
	</dict>
	
	note that special playlists may have these properties:
	
	<key>Master</key><true/>
	<key>Audiobooks</key><true/>
	<key>Movies</key><true/>
	<key>Music</key><true/>
	<key>Party Shuffle</key><true/>
	<key>Podcasts</key><true/>
	<key>TV Shows</key><true/>
 */
	
    protected String name = null;
    protected int playlistID = -1;
    protected String playlistPersistentId = null;
    protected boolean visible = false;
    protected boolean allItems = false;
    protected byte[] smartInfo = null;
    protected byte[] smartCriteria = null;
       
    protected boolean master = false;
    protected boolean audiobooks = false;
    protected boolean movies = false;
    protected boolean music = false;
    protected boolean partyShuffle = false;
    protected boolean podcasts = false;
    protected boolean tvShows = false;    
    
    protected List playlistItems = new ArrayList();
    protected List trackIDs = new ArrayList();
    
    /** a reference to the library this playlist is a part of */
    private ItunesLibrary library = null;
    
    /** an accumulated total time (in seconds) for all tracks in this playlist */
    private long totalTime = 0;
    
    /** an accumulated total time (in bytes) for all tracks in this playlist */
    private long totalSize = 0;
        
    public ItunesPlaylist(ItunesLibrary library)
    {
        if (library == null)
        {
            throw new RuntimeException("argument 'library' cannot be null");
        }                
        
        this.library = library;
    }
    
    public String getName()
    {
        return name;
    }
    
    public void setName(String value)
    {
        name = value;
    }
    
    public int getId()
    {
        return this.playlistID;
    }
    
    public void setId(int value)
    {
        this.playlistID = value;
    }
    
    public List getPlaylistItems()
    {
        populateTracksArray();
        return playlistItems;
    }    
    
    public void addTrackID(int trackID)
    {
    	trackIDs.add(new Integer(trackID));
    }
    
    public long getTotalTime()
    {
        populateTracksArray();
        return totalTime;
    }
    
    public long getTotalSize()
    {
        populateTracksArray();
        return totalSize;
    }
    
    private void populateTracksArray()
    {
        if (playlistItems.size() == 0 && trackIDs.size() != 0)
        {
            int trackNumber = 1;                  
            //populate track array with tracks from library
            Iterator it = trackIDs.iterator();
            while (it.hasNext())
            {
                ItunesTrack track = library.getTrackById(((Integer) it.next()).intValue());
                totalTime += track.getTotalTime();
                totalSize += track.getSize();
                //make a copy of the track because the tracknumber is diff for diff playlists
                ItunesTrack trackCopy = new ItunesTrack(track);
                trackCopy.setPlaylistTrackNumber(trackNumber);
                playlistItems.add(trackCopy);
                trackNumber++;
            }
        }
    }
    
   

	public boolean isMaster()
	{
		return master;
	}

	public void setMaster(boolean master)
	{
		this.master = master;
	}

	public int getPlaylistID()
	{
		return playlistID;
	}

	public void setPlaylistID(int playlistID)
	{
		this.playlistID = playlistID;
	}

	public String getPlaylistPersistentId()
	{
		return playlistPersistentId;
	}

	public void setPlaylistPersistentId(String playlistPersistentId)
	{
		this.playlistPersistentId = playlistPersistentId;
	}

	public boolean isVisible()
	{
		return visible;
	}

	public void setVisible(boolean visible)
	{
		this.visible = visible;
	}

	public boolean isAllItems()
	{
		return allItems;
	}

	public void setAllItems(boolean allItems)
	{
		this.allItems = allItems;
	}

	public List getTrackIDs()
	{
		return trackIDs;
	}

	public void setTrackIDs(List trackIDs)
	{
		this.trackIDs = trackIDs;
	}

	public boolean isAudiobooks()
	{
		return audiobooks;
	}

	public void setAudiobooks(boolean audiobooks)
	{
		this.audiobooks = audiobooks;
	}

	public boolean isMovies()
	{
		return movies;
	}

	public void setMovies(boolean movies)
	{
		this.movies = movies;
	}

	public boolean isMusic()
	{
		return music;
	}

	public void setMusic(boolean music)
	{
		this.music = music;
	}

	public boolean isPartyShuffle()
	{
		return partyShuffle;
	}

	public void setPartyShuffle(boolean partyShuffle)
	{
		this.partyShuffle = partyShuffle;
	}

	public boolean isPodcasts()
	{
		return podcasts;
	}

	public void setPodcasts(boolean podcasts)
	{
		this.podcasts = podcasts;
	}

	public boolean isTvShows()
	{
		return tvShows;
	}

	public void setTvShows(boolean tvShows)
	{
		this.tvShows = tvShows;
	}

	public byte[] getSmartInfo()
	{
		return smartInfo;
	}

	public void setSmartInfo(byte[] smartInfo)
	{
		this.smartInfo = smartInfo;
	}

	public byte[] getSmartCriteria()
	{
		return smartCriteria;
	}

	public void setSmartCriteria(byte[] smartCriteria)
	{
		this.smartCriteria = smartCriteria;
	}	
	
	
	
    
}
