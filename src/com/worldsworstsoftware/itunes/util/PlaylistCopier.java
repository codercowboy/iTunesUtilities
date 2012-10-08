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
 * ItunesPlaylistCopier.java
 *
 * Created on September 2, 2006, 2:22 PM
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */

package com.worldsworstsoftware.itunes.util;

import java.io.PrintStream;
import java.util.Iterator;

import com.worldsworstsoftware.itunes.ItunesPlaylist;
import com.worldsworstsoftware.itunes.ItunesTrack;

//TODO JAVADOC:

/**
 *
 * @author jason
 */
public class PlaylistCopier
{
    private IFileCopier _fileCopier = null;
    private ItunesPlaylist _playlist = null;
    private String _outputPath = null;
    private String _nameFormat = null;
    protected String _fileName = null;
    protected boolean _debug = false;
    protected boolean _verbose = false;
    
    public PlaylistCopier(ItunesPlaylist playlist, String outputPath, String nameFormat)
    {
        this(playlist, outputPath, nameFormat, false, false);
    }
    
    public PlaylistCopier(ItunesPlaylist playlist, String outputPath, String nameFormat, boolean verbose, boolean debug)
    {
        this._playlist = playlist;
        this._outputPath = outputPath;
        this._nameFormat = nameFormat;
        this._fileCopier = new FileCopier();
        this._verbose = verbose;
        this._debug = debug;
    }
    
    public void setFileCopier(IFileCopier fileCopier)
    {
        this._fileCopier = fileCopier;
    }
    
    public void copyPlaylist()
    {
        try
        {
            PrintStream out = System.out;
            
            if (_verbose)
            {
                out.println("Copying Playlist \"" + _playlist.getName() + "\" (" + _playlist.getPlaylistItems().size() + " tracks) ..");
            }
            
            int trackCount = 0;
            int errorCount = 0;
            Iterator it = _playlist.getPlaylistItems().iterator();
            while (it.hasNext())
            {
                
                ItunesTrack track = (ItunesTrack) it.next();
                
                //format the track name
                
                //TODO: FIX ME
                //_fileName = ItunesFileNameUtils.formatTrackName(track,_nameFormat);
                //put the extention on the end of the trackname (ie mp3, aaif, whatever)
                _fileName += "." + findExtension(track.getLocation());
                
                updateCopyStatus("  Copying \"" + _fileName + "\"");
                
                try
                {
                    _fileCopier.copyFile(ItunesFileNameUtils.CleanItunesFilename(track.getLocation()), _outputPath, _fileName);
                }
                catch (Exception e)
                {
                    
                    errorCount ++;
                    handleException(e);
                }
                trackCount ++;
                
            } //end of track loop
            if (_verbose)
            {
                out.println("    " + trackCount + " Tracks Processed.");
                out.println("    " + (trackCount - errorCount) + " Tracks Copied.");
            }
        }
        catch (Exception e)
        {
            handleException(e);
        }
        
    }
    
    private static String findExtension(String filename)
    {
        int position = filename.lastIndexOf('.');
        if (position != -1)
        {
            return filename.substring(position + 1);
        }
        else
        {
            //there isnt a '.' in the filename, couldnt find extension..
            return "";
        }
    }
    
    protected void updateCopyStatus(String copyStatus)
    {
        if (_verbose)
        {
            System.out.println(copyStatus);
        }
    }
    
    protected void handleException(Exception e)
    {
        PrintStream out = System.out;
        if (_fileName != null && _verbose == false)
        {
            //in verbose mode we don't display the copying file info, so we should do that now..
            out.println("Error While Copying \"" + _fileName + "\"..");
        }                        
        
        out.println("  Error: " + e.getMessage());
        
        //print the stack trace if we're in debug mode
        if (_debug)
        {
            Throwable cause = (Throwable) e;
            //get to the root cause stack trace
            while (cause.getCause() != null)
            {
                cause = cause.getCause();
            }
            
            cause.printStackTrace(out);
        }
    }
}
