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
 * XMLParserErrorHandler.java
 *
 * Created on September 11, 2006, 9:03 AM
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */

package com.worldsworstsoftware.xmltagparser;

import org.xml.sax.ErrorHandler;
import org.xml.sax.SAXException;
import org.xml.sax.SAXParseException;

import com.worldsworstsoftware.logging.StatusUpdateLogger;

/**
 *
 * @author jasbaker
 *
 *  This is the default error handler code shown in the great o'reilly sax parser example
 *  from java examples in a nutshell By David Flanagan.
 *  
 *  http://www.oreilly.com/catalog/jenut2/chapter/ch19.html
 */
class SimpleXMLErrorHandler implements ErrorHandler
{    
	private StatusUpdateLogger logger = null;

    
    /** Creates a new instance of XMLParserErrorHandler */
    public SimpleXMLErrorHandler(StatusUpdateLogger logger)
    {
        this.logger = logger;
    }
    
    /** This method is called when warnings occur */
    public void warning(SAXParseException exception)
    {
    	logger.warn(getErrorMessage(exception), exception, true);
    }
    
    /** This method is called when errors occur */
    public void error(SAXParseException exception)
    {
    	logger.error(getErrorMessage(exception), exception, true);
    }
    
    /** This method is called when non-recoverable errors occur. */
    public void fatalError(SAXParseException exception) throws SAXException
    {

    	logger.fatal(getErrorMessage(exception), exception, false);       
        throw(exception);
    }
    
    private String getErrorMessage(SAXParseException exception)
    {
    	return "XML parsing problem, line " + exception.getLineNumber() + ": " + exception.getMessage();
    }
    
}
