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
 * StringReplacer.java
 *
 * Created on September 8, 2006, 11:50 AM
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */

package com.worldsworstsoftware.util;

//TODO JAVADOC:

/**
 *
 * @author jasbaker
 *
 */
public class StringReplacer
{
    
    /** Creates a new instance of StringReplacer */
    public StringReplacer()
    {
    }
    
    // oops, started development in jdk 1.5 which provides a nice String.replace(String,String) method
    // then discovered mac os x 10.3 can only have jdk 1.4.x on it, 
    // so i tried to work w/ String.replaceAll(regex, String) (see StringToRegEx)
    // that didnt work with two special characters due to this bug:
    // http://bugs.sun.com/bugdatabase/view_bug.do?bug_id=4968604
    // so i'm writing the string.Replace(String,String) from scratch using a slower algorithm..
    public static String replace(String haystack, String needle, String replaceString)
    {
        if (haystack == null)
        {
            return haystack;
        }
        
        String newReplaceString = "";
        if (replaceString != null)
        {
            newReplaceString = replaceString;
        }
        
        String newHaystack = new String(haystack);
        
        int len = needle.length();
        int pos = newHaystack.indexOf(needle);  
        int replaceLen = newReplaceString.length();
        
        while (pos != -1)
        {
            if (pos == 0)
            {
                newHaystack = replaceString + newHaystack.substring(len);
            }
            else
            {
                newHaystack = newHaystack.substring(0, pos) + newReplaceString + newHaystack.substring(pos + len);
            }
            pos = newHaystack.indexOf(needle, pos + replaceLen);
        }
        
        return newHaystack;
    }
    

    // oops, started development in jdk 1.5 which provides a nice String.replace(String,String) method
    // then discovered mac os x 10.3 can only have jdk 1.4.x on it, 
    // so i've got to work w/ String.replaceAll(regex, String)
    public static String ConvertToRegEx(String value)
    {
        String newValue = new String(value);
        //put escape commands before special characters in the new regex
        //Roedy Green lists the special chars as . - + * ? ( ) [ ] { } \ | $ ^ < =
        //http://mindprod.com/jgloss/regex.html 
        
        //there's probably a more robust way to do this in a one shot regex, but i'm not too great w/ regex stuff
        newValue = newValue.replaceAll("\\.", "\\.");
        newValue = newValue.replaceAll("\\-", "\\-");
        newValue = newValue.replaceAll("\\+", "\\+");
        newValue = newValue.replaceAll("\\*", "\\*");
        newValue = newValue.replaceAll("\\?", "\\?");
        newValue = newValue.replaceAll("\\(", "\\(");
        newValue = newValue.replaceAll("\\)", "\\)");
        newValue = newValue.replaceAll("\\[", "\\[");
        newValue = newValue.replaceAll("\\]", "\\]");
        newValue = newValue.replaceAll("\\{", "\\{");
        newValue = newValue.replaceAll("\\}", "\\}");
        newValue = newValue.replaceAll("\\\\", "\\\\\\\\");
        newValue = newValue.replaceAll("\\|", "\\|");
        newValue = newValue.replaceAll("\\$", "\\$");
        newValue = newValue.replaceAll("\\^", "\\^");
        newValue = newValue.replaceAll("\\<", "\\<");
        newValue = newValue.replaceAll("\\=", "\\=");
        
        return newValue;
    }
    
}
