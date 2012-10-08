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
 * FileCopier.java
 *
 * Created on September 2, 2006, 4:57 PM
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */

package com.worldsworstsoftware.itunes.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.channels.FileChannel;

import com.worldsworstsoftware.util.StringReplacer;

//TODO JAVADOC:

/**
 *
 * @author jason
 *  A well behaved IPlaylistCopierFileCopier will behave in the following ways:
 *      1) It will throw descriptive RuntimeExceptions when an error occurs
 *      2) It will check that the output path exists, is a valid directory & is writable
 *      3) It will check that the input file exists and is readable
 *      4) It will put a final "/" or "\" on the output path if needed
 *      5) It will strip invalid characters out of the filename
 *      6) It will strip "\" and "/" out of the filename if it will not create folders
 *      7) It will handle output file collisions if the the output file exists, or throw an exception
 *      8) It will copy the file in a fairly efficient manner
 *      9) It will close file & stream resources, even if an exception occurred
 *
 */
public class FileCopier implements IFileCopier
{
    
    /** Creates a new instance of FileCopier */
    public FileCopier()
    {
    }
    
    public void copyFile(String inputFilePath, String outputPath, String newFileName)
    {
        if (outputPath == null || outputPath.equals(""))
        {
            throw new RuntimeException("No Output Path Specified.");
        }
        
        //check that the output directory exists, is a directory, and is writeable
        File outputDirectory = new File(outputPath);
        if (outputDirectory.exists() == false)
        {
            throw new RuntimeException("Output Path \"" + outputPath + "\" does not exist.");
        }
        else if (outputDirectory.isDirectory() == false)
        {
            throw new RuntimeException("Output Path \"" + outputPath + "\" is not a directory.");
        }
        else if (outputDirectory.canWrite() == false)
        {
            throw new RuntimeException("Output Path \"" + outputPath + "\" is not writable, check permissions.");
        }
        
        //check that the input file exists, is a file, and is readable
        File inputFile = new File(inputFilePath);
        if (inputFile.exists() == false)
        {
            throw new RuntimeException("Input File \"" + inputFile + "\" does not exist.");
        }
        else if (inputFile.isFile() == false)
        {
            throw new RuntimeException("Input File \"" + inputFile + "\" is not a file.");
        }
        else if (inputFile.canRead() == false)
        {
            throw new RuntimeException("Input File \"" + inputFile + "\" is not readable, check permissions.");
        }
        
        //put a "/" or "\" on the end of the outputpath if needed
        String realOutputPath = outputDirectory.getPath() + File.separator;
        
        String realFileName = new String(newFileName);
        
        //this filecopier does not support creation of subfolders, so strip "\" and "/" out of the filename..
        realFileName = StringReplacer.replace(realFileName, "\\", " ");
        realFileName = StringReplacer.replace(realFileName, "/", " ");        
        
        //strip invalid characters out of the filename
        //invalid characters listed by Linux Box Admin at: http://linuxboxadmin.com/articles/filefriction.php
        String fileNameValidCharactersRegEx = "[\\*:\\\\/<>\\|\"?]";
        
        realFileName = realFileName.replaceAll(fileNameValidCharactersRegEx, "_");
        
        String finalFileName = realOutputPath + realFileName;
        
        //make sure the file doesnt already exist
        File outputFile = new File(finalFileName);
        if (outputFile.exists())
        {
            throw new RuntimeException("Cannot Copy File To \"" + finalFileName + "\", file already exists.");
        }
        
        //do the copy
        this.copyFile(inputFile, outputFile);
        
        //System.out.println("Fake Copy:" + newFileName);
    }
    
    //file copying algorithm example thanks to gforman and shiva_in 
    // at http://www.experts-exchange.com/Programming/Programming_Languages/Java/Q_10245809.html
    private void copyFile(File inputFile, File outputFile)
    {
        try
        {
            FileChannel in = null, out = null;
            try
            {
                in = new FileInputStream(inputFile).getChannel();
                out = new FileOutputStream(outputFile).getChannel();
                in.transferTo( 0, in.size(), out);
            }
            finally
            {
                //make sure to clean up resources if an exception occurs
                if (in != null)
                    in.close();
                if (out != null)
                    out.close();
            }
        }
        catch (IOException e)
        {
            throw new RuntimeException("Error while copying file:" + e.getMessage(), e);
        }
    }
    
}
