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
 * Tag.java
 *
 * Created on September 11, 2006, 6:22 AM
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */

package com.worldsworstsoftware.xmltagparser;

//TODO JAVADOC:
/**
 *
 * @author jasbaker
 */
public class Tag
{
    private static int count = 0;    
    private static Object countLock = new Object();    
    
    public static int getTagInstanceCount()
    {
    	int result = 0;
    	synchronized (countLock)
    	{
    		result = count;
    	}
    	return result;
    }
    
    private String name = null; 
    private StringBuffer innerText = null;
   
    public Tag()
    {
        this(null, null);
    }
    
    public Tag(String name, String innerText)
    {
    	synchronized (countLock)
    	{
    		count++;
    	}
        this.setName(name);
        this.setInnerText(innerText);
    }
    
    public void clear()
    {
        name = null;
        innerText = null;
    }
      
    public String getName()
    {
    	return name;
    }
    
    public void setName(String value)
    {
        this.name = value;
    }  
    
    public String getInnerText()
    {
        if (innerText != null)
        {
            return innerText.toString();
        }        
        else
        {
            //no inner text
            return null;
        }
    }
    
    public void setInnerText(String value)
    {
        
        if (value != null)
        {
            this.addInnerText(value.toCharArray(), 0, value.length());
        }
    }
    
    public void addInnerText(char[] buffer, int start, int length)
    {
        if (innerText == null)
        {
        	this.innerText = new StringBuffer();
        }
        this.innerText.append(buffer, start, length);
    }
    
    public int hashCode()
    {
        return toString().hashCode();
    }
    
    public String toString()
    {
    	return "Tag {name: " + this.name + ", innerText: " + this.innerText + "}";
    }           
}
