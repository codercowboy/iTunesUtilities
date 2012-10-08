
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
 * Util.java
 *
 * Created on September 2, 2006, 6:41 PM
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */

package com.worldsworstsoftware.itunes.util;

import java.net.URLDecoder;

import com.worldsworstsoftware.itunes.ItunesTrack;
import com.worldsworstsoftware.util.StringReplacer;

//TODO JAVADOC:


/**
 *
 * @author jason
 */
public class ItunesFileNameUtils
{    
    public static final String DefaultNameFormat = "%# %A - %N";
    
    public static String formatTrackName(ItunesTrack track, String trackNameFormat, int maximumTrackNumber)
    {
        //where %N is track name, %# is track #, %A is artist, %R is album [does not support folders, slashes will be removed]
        
        String value = new String(trackNameFormat);
        value = StringReplacer.replace(value, "%#", getBufferedTrackNumber(track.getPlaylistTrackNumber(), maximumTrackNumber));
        value = StringReplacer.replace(value, "%A", track.getArtist());
        value = StringReplacer.replace(value, "%N", track.getName());
        value = StringReplacer.replace(value, "%R", track.getAlbum());
        return value;
    }
    
    private static String getBufferedTrackNumber(int num, int maximumTrackNumber)
    {
    	int numberOfSpaces = String.valueOf(maximumTrackNumber).length();  
    	
        String value = String.valueOf(num);
        while (value.length() < numberOfSpaces)
        {
            value = "0" + value;
        }
        return value;
    }
    
    public static String CleanItunesFilename(String originalFileName)
    {
        String filename = originalFileName;
        
        //remove possible html encoded characters such as %20 for space,
        // example java decode code thanks to Roedy Green
        // http://mindprod.com/jgloss/urlencoded.html
        try
        {
            filename = URLDecoder.decode(filename, "UTF-8" );
        }
        catch (Exception e)
        {
            //swallow the unsupported encoding exception
        }
        
        //get rid of the file:\\\localhost\ crap
        boolean osIsMacosx = System.getProperty("os.name").toLowerCase().startsWith("mac os x");
        if (osIsMacosx)
        {
            filename = StringReplacer.replace(filename, "file:\\localhost", "");
            filename = StringReplacer.replace(filename, "file://localhost", "");
        }
        else //windows, remove the slash at the end of localhost
        {
            filename = StringReplacer.replace(filename, "file:\\localhost\\", "");
            filename = StringReplacer.replace(filename, "file://localhost/", "");
        }
        return filename;
    }
    
}
