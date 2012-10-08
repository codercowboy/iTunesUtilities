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
 * IPlaylistCopierFileCopier.java
 *
 * Created on September 2, 2006, 4:27 PM
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */

package com.worldsworstsoftware.itunes.util;

//TODO JAVADOC:

/**
 *
 * @author jason
 *
 *  A well behaved IPlaylistCopierFileCopier will behave in the following ways:
 *      1) It will throw descriptive RuntimeExceptions when an error occurs
 *      2) It will check that the output path is valid & is writable
 *      3) It will check that the input file exists and is readable
 *      4) It will put a final "/" or "\" on the output path if needed 
 *      5) It will strip invalid characters out of the filename
 *      6) It will strip "\" and "/" out of the filename if it will not create folders
 *      7) It will handle output file collisions if the the output file exists, or throw an exception
 *      8) It will copy the file in a fairly efficient manner
 *      9) It will close file & stream resources, even if an exception occurred
 *
 */
public interface IFileCopier
{
    public void copyFile(String inputFilePath, String outputPath, String newFileName);
}
