
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
 * ItunesPrinter.java
 *
 * Created on September 2, 2006, 2:24 PM
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */

package com.worldsworstsoftware.itunes.util;


import java.io.PrintStream;
import java.util.Iterator;

import com.worldsworstsoftware.itunes.ItunesLibrary;
import com.worldsworstsoftware.itunes.ItunesPlaylist;
import com.worldsworstsoftware.itunes.ItunesTrack;

//TODO JAVADOC:
/**
 *
 * @author jason
 */
public class ItunesPrinter
{
    
    /** Creates a new instance of ItunesPrinter */
    public ItunesPrinter()
    {
    }
    
    public static void printPlayLists(PrintStream out, ItunesLibrary library,  boolean printLibraryPlaylist)
    {
        out.println("Listing Playlists:");        
        if (library.getPlaylists().size() == 0)
        {
            out.println("  Error: No Playlists Found!");
        }
        Iterator it = library.getPlaylists().iterator();
        while (it.hasNext())
        {
            
            ItunesPlaylist playlist = (ItunesPlaylist) it.next();
            if (!printLibraryPlaylist && playlist.getName().toLowerCase().endsWith("library"))
            {
                //skip the "library" playlist
                continue;
            }
            
            out.print("  " + playlist.getName() + " ");
            out.print("[ID:" + playlist.getId() + " - ");
            out.print(playlist.getTrackIDs().size() + " tracks - ");
            out.print(getPrintableItunesTime(playlist.getTotalTime()) + " - ");        
            out.println(getPrintableFileSize(playlist.getTotalSize()) + "]");
        }
    }
    
    public static void printTracks(PrintStream out, ItunesPlaylist playlist, String trackNameFormat)
    {
        out.println("Listing Tracks For Playlist \"" + playlist.getName() + "\":");
        Iterator it = playlist.getPlaylistItems().iterator();
        while (it.hasNext())
        {
            ItunesTrack track  = (ItunesTrack) it.next();
            out.print("  ");
            printTrack(out, track, trackNameFormat);            
        }
        out.println("    Total Playlist Time: " + getPrintableItunesTime(playlist.getTotalTime()));                
        out.println("    Total Playlist File Size: " + getPrintableFileSize(playlist.getTotalSize()));
    }
        
    
    public static void printTrackFilenames(PrintStream out, ItunesPlaylist playlist)
    {
        Iterator it = playlist.getPlaylistItems().iterator();
        while (it.hasNext())
        {
            ItunesTrack track  = (ItunesTrack) it.next();
            out.println(ItunesFileNameUtils.CleanItunesFilename(track.getLocation()));
        }
    }
    
    public static String getPrintableItunesTime(long time)
    {
        long seconds = (time / 1000);
        long minutes = seconds / 60;
        seconds -= (minutes * 60);
        long hours = minutes / 60;
        minutes -= (hours * 60);
        if (hours > 0)
        {
            return hours + ":" + getBufferedLong(minutes,2) + ":" + getBufferedLong(seconds,2);
        }
        else
        {
            return minutes + ":" + getBufferedLong(seconds,2);
        }                
    }
    
    private static String getBufferedLong(long num, int numberOfSpaces)
    {
        String value = String.valueOf(num);
        while (value.length() < numberOfSpaces)
        {
            value = "0" + value;
        }
        return value;
    }
    
    public static String getPrintableFileSize(long fileSize)
    {
        long testsize = 1000000000; //gigabyte
        if (fileSize > testsize) //bigger than a gigabyte..
        {
            return getPrintableFileSize(fileSize, testsize, "GB");
        }
        
        testsize = 1000000; //megabyte
        if (fileSize > testsize) //bigger than a gigabyte..
        {
            return getPrintableFileSize(fileSize, testsize, "MB");
        }
        
        testsize = 1000; //kilobyte
        if (fileSize > testsize) //bigger than a gigabyte..
        {
            return getPrintableFileSize(fileSize, testsize, "KB");        
        }        
        
        return fileSize + " bytes";
    }
    
    public static String getPrintableFileSize(long fileSize, long divisibleUnit, String unitString)
    {
        return (fileSize / divisibleUnit) + "." + String.valueOf(fileSize % divisibleUnit).substring(0, 2) + unitString;
    }
    
    public static void printTrack(PrintStream out, ItunesTrack track,  String trackNameFormat)
    {     
    	//TODO: FIX ME
        //out.print(ItunesFileNameUtils.formatTrackName(track, trackNameFormat));
        out.print(" [" + getPrintableItunesTime(track.getTotalTime()) + " - ");        
        out.println(getPrintableFileSize(track.getSize()) + "]");
    }
    
}
