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
 * LibraryFinder.java
 *
 * Created on September 7, 2006, 4:09 PM
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */

package com.worldsworstsoftware.itunes.util;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;

import com.worldsworstsoftware.logging.NullStatusUpdateLogger;
import com.worldsworstsoftware.logging.StatusUpdateLogger;
import com.worldsworstsoftware.util.StringReplacer;

//TODO JAVADOC:

/**
 *
 * @author jasbaker
 */
public class ItunesLibraryFinder
{
    public static boolean osIsMacosx = false;
    
    static
    {
    	osIsMacosx = System.getProperty("os.name").toLowerCase().startsWith("mac os x");
    }
    
    public static String findLibrary(StatusUpdateLogger logger)
    {     
    	if (logger == null)
    	{
    		logger = new NullStatusUpdateLogger();
    	}
    	
        String userHomePath = System.getProperty("user.home");
        String libraryFilename = "";
        
        logger.debug("ItunesLibraryFinder Detected " + ((osIsMacosx) ? "OS X" : "Windows"));
        
        if (osIsMacosx)
        {
            //thanks to Jay of the indyJt weblog for os xitunes lib location hints
            //http://www.indyjt.com/blog/?p=51
            
            //test ~/Music/iTunes/iTunes Music Library.xml
            libraryFilename = createPath(userHomePath, "Music/iTunes/iTunes Music Library.xml");
            if (fileIsItunesLibrary(libraryFilename, logger))
            {
                return libraryFilename;
            }
            
            //test ~/Documents/iTunes/iTunes Music Library.xml
            libraryFilename = createPath(userHomePath, "Documents/iTunes/iTunes Music Library.xml");
            if (fileIsItunesLibrary(libraryFilename, logger))
            {
                return libraryFilename;
            }
        }
        else //not macosx
        {
            //test ~/My Documents/My Music/iTunes/FILE HERE.xml
            libraryFilename = createPath(userHomePath, "My Documents/My Music/iTunes/iTunes Music Library.xml");
            if (fileIsItunesLibrary(libraryFilename, logger))
            {
                return libraryFilename;
            }
        }
        
        //no library was found
        return null;
    }
    
    public String getUnableToLocateLibraryError(boolean macosx)
    {
        String value = "";
        String lineSeperator = System.getProperty("line.separator");
        value += "Unable to automatically locate your Itunes Library file." + lineSeperator;
        value += lineSeperator;
        value += "  The library is typically stored in one of the following" + lineSeperator;
        value += "     locations on a " + (macosx ? "Mac OS X" : "Windows") + " system:" + lineSeperator;
        value += lineSeperator;
        if (macosx)
        {
            value += "       ~/Music/iTunes/iTunes Music Library.xml" + lineSeperator;
            value += "       ~/Documents/iTunes/iTunes Music Library.xml" + lineSeperator;
        }
        else
        {
            value += "        ~\\My Documents\\My Music\\iTunes\\iTunes Music Library.xml" + lineSeperator;
        }
        value += lineSeperator;
        value += "  Please locate your Itunes Library file and specify it " + lineSeperator;
        value += "   explicitly using the -l command line argument." + lineSeperator;
        return value;
    }
    
    protected static String createPath(String basepath, String pathaddon)
    {        
        String newPathAddon = StringReplacer.replace(pathaddon, "/", File.separator);     
        return (new File(basepath)).getPath() + File.separator + newPathAddon;
    }
        
    public static boolean fileIsItunesLibrary(String libraryFilename, StatusUpdateLogger logger)
    {
    	if (logger == null)
    	{
    		logger = new NullStatusUpdateLogger();
    	}
    	
        logger.debug("Looking for iTunes library at: " + libraryFilename);
        
        if (libraryFilename == null || libraryFilename.equals(""))
        {
        	logger.warn("LibraryFilename is empty or null.", null, true);            
            return false;
        }
        
        File libraryFile = new File(libraryFilename);
        if (libraryFile == null)
        {
            logger.warn("LibraryFile object wasn't created.", null, true);
            return false;
        }
        
        if (libraryFile.exists() == false)
        {
            logger.debug("File does not exist: " + libraryFilename);
            return false;
        }
        
        if (libraryFile.isFile() == false)
        {
        	logger.debug("File is not a file (probably a directory): " + libraryFilename);
            return false;
        }
        
        if (libraryFile.canRead() == false)
        {
            logger.warn("File is not readable (check permissions): " + libraryFilename, null, true);
            return false;
        }
        
        try
        {
        	//test if file looks like a library (debug print error)
        	return verifyItunesLibrary(libraryFilename, logger);
        }
        catch (Exception e)
        {
        	logger.error("Exception occurred while trying to verify file \"" + libraryFilename + "\" is an itunes library:" + e.getMessage(), e, true);
        	return false;
        }
    }
    
    /**
     * 
     * @param libraryFilename
     * @param logger
     * @return
     * @throws IOException if the file specified by libraryFilename is not found, or if there is a problem reading from the file into the internal buffer.
     */
    public static boolean verifyItunesLibrary(String libraryFilename, StatusUpdateLogger logger) throws IOException
    {
    	if (logger == null)
    	{
    		logger = new NullStatusUpdateLogger();
    	}
    	
        //libraryFilename is verified as existing, and being readable by fileIsItunesLibrary()
        
        //the standard library file looks something like this at the top:
        //    <?xml version="1.0" encoding="UTF-8"?>
        //    <!DOCTYPE plist PUBLIC "-//Apple Computer//DTD PLIST 1.0//EN" "http://www.apple.com/DTDs/PropertyList-1.0.dtd">
        //    <plist version="1.0">
        //        <dict>
        //            <key>Major Version</key><integer>1</integer>
        //            <key>Minor Version</key><integer>1</integer>
        //            <key>Application Version</key><string>6.0.5</string>
        //            <key>Features</key><integer>1</integer>
        //            <key>Music Folder</key><string>file://localhost/E:/itunes/</string>
        //            <key>Library Persistent ID</key><string>4EC2FAC25152379E</string>
        //            <key>Tracks</key>
        //
        // so all we're going to do is read a reasonable amount of the library header into a buffer
        //  and then do some quick checks on some identifying strings existing in the buffer
        FileReader libraryFile = new FileReader(libraryFilename);
        
        char[] buffer = new char[3000];
        libraryFile.read(buffer, 0, 3000);
        libraryFile.close();
        
        String betterBuffer = new String(buffer);
        
        if (checkBufferForString(betterBuffer, "Apple Computer") &&
            checkBufferForString(betterBuffer, "<key>Major Version</key>") &&
            checkBufferForString(betterBuffer, "<key>Minor Version</key>") &&
            checkBufferForString(betterBuffer, "<key>Music Folder</key>") &&
            checkBufferForString(betterBuffer, "<key>Application Version</key>") &&
            checkBufferForString(betterBuffer, "<key>Tracks</key>"))
        {
            return true;
        }
        else
        {
            return false;
        }            
    }
    
    private static boolean checkBufferForString(String buffer, String needle)    
    {
        if (buffer.indexOf(needle) == -1)
        {
            return false;
        }
        return true;
    }
}


