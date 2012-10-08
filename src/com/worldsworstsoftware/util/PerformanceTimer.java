
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
 * PerformanceTimer.java
 *
 * Created on September 8, 2006, 10:59 AM
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */

package com.worldsworstsoftware.util;

//TODO JAVADOC:
/**
 *
 * @author jasbaker
 */
public class PerformanceTimer
{
    private long initialTime = 0;
    private long lastResetTime = 0;  
    
    public PerformanceTimer()
    {
    	long currentTime = System.currentTimeMillis(); 
    	initialTime = currentTime;
    	lastResetTime = currentTime;    	
    }
    
    public void reset()
    {
    	lastResetTime = System.currentTimeMillis();
    }
    
    public String getTimeElapsedSinceLastReset()
    {
    	long currentTime = System.currentTimeMillis(); 
        return formatTime(currentTime - lastResetTime);
    }
    
    public String getTotalTimeElapsed()
    {
    	long currentTime = System.currentTimeMillis(); 
        return formatTime(currentTime - initialTime);
    }
    
    private String formatTime(long timeInMillis)
    {
        if (timeInMillis > 1000)
        {
            return (timeInMillis / 1000) + "." + (timeInMillis % 1000) + " s";
        }
        else
        {
            return timeInMillis + " ms";
        }
    }
    
}
