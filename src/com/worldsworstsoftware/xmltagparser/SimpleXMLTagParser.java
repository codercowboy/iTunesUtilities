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

package com.worldsworstsoftware.xmltagparser;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParserFactory;

import org.xml.sax.AttributeList;
import org.xml.sax.ErrorHandler;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;

import com.worldsworstsoftware.logging.StatusUpdateLogger;

/**
 * The SimpleXMLTagParser uses a SAX XML parser to parse the given
 * file into tags. 
 * 
 * A typical XML parser would worry about parsing nested tags, but 
 * apple's xml file format allows us to not worry about the complexities
 * of nested tags.
 * 
 * For example in a typical XML format you might expect track information
 * to be formatted like so:
 * 
 *    <Track>
 * 	   <Name>Come Together</Name>
 *     <Artist>The Beatles</Artist>
 *    </Track>
 * 
 * But Apple's format for the same information is a bit different:
 * 
 *    <key>Track ID</key><integer>401</integer>
 *    <key>Name</key><string>Come Together</string>
 *    <key>Artist</key><string>The Beatles</string>
 * 
 * So for our purposes we can simply pass the latest parsed tag and its 
 * inner text up to whatever tag handling mechanism we've implemented. 
 *
 * @author Jason Baker of http://www.worldsworstsoftware.com
 */
public class SimpleXMLTagParser extends org.xml.sax.HandlerBase
{        
	
	//TODO JAVADOC:
	
    private TagHandler tagHandler = null;
    private String xmlFileLocation = null;
    private ErrorHandler errorHandler;
    private boolean resolveEntities = false;
    private Tag currentTag = null;
    
    //provided for performance testing
    private int charactersCallsCount = 0;
    
    /** Creates a new instance of SimpleXMLTagParser */
    public SimpleXMLTagParser(String xmlFileLocation, 
    		TagHandler tagHandler,
    		StatusUpdateLogger logger, 
			boolean resolveEntities)
    {
        if (tagHandler == null)
        {
            throw new RuntimeException("tagHandler cannot be null.");
        }        
        
        this.tagHandler = tagHandler;        
        this.xmlFileLocation = xmlFileLocation;
        this.resolveEntities = resolveEntities;
        this.errorHandler = new SimpleXMLErrorHandler(logger);
        this.currentTag = new Tag();
        this.charactersCallsCount = 0;
    }
    
    public int getCharacterCallsCount()
    {
    	return this.charactersCallsCount;
    }    
    
    public void setErrorHandler(ErrorHandler errorHandler)
    {
        if (errorHandler == null)
        {
            throw new RuntimeException("errorHandler cannot be null.");
        }
        
        this.errorHandler = errorHandler;
    }
    
    public void parse() throws IOException, SAXException, ParserConfigurationException
    {
        
        // parser stuff figured out with great ease thanks to java examples in a nutshell By David Flanagan
        // http://www.oreilly.com/catalog/jenut2/chapter/ch19.html
        
        // Create a JAXP "parser factory" for creating SAX parsers
        SAXParserFactory spf = SAXParserFactory.newInstance();
        
        // Configure the parser factory for the type of parsers we require
        spf.setValidating(false);  // No validation required
        
        // Now use the parser factory to create a SAXParser object
        // Note that SAXParser is a JAXP class, not a SAX class
        javax.xml.parsers.SAXParser sp = spf.newSAXParser();
        
        // Create a SAX input source for the file argument
        org.xml.sax.InputSource input = new InputSource(new FileReader(this.xmlFileLocation));
        
        // Give the InputSource an absolute URL for the file, so that
        // it can resolve relative URLs in a <!DOCTYPE> declaration, e.g.
        input.setSystemId("file://" + new File(this.xmlFileLocation).getAbsolutePath());
        
        // Finally, tell the parser to parse the input and notify the handler
        // sp.parse(input, this);
        
        // Instead of using the SAXParser.parse() method, which is part of the
        // JAXP API, we could also use the SAX1 API directly.  Note the
        // difference between the JAXP class javax.xml.parsers.SAXParser and
        // the SAX1 class org.xml.sax.Parser
        //
        org.xml.sax.Parser parser = sp.getParser();  // Get the SAX parser
        
        if (this.resolveEntities)
        {
            parser.setEntityResolver(this);
        }
        else
        {
            parser.setEntityResolver(new SAXFakeEntityResolver());
        }
        
        parser.setDocumentHandler(this);          // Set main handler
        parser.setErrorHandler(errorHandler);             // Set error handler
        parser.parse(input);                         // Parse!
    }
            
    public void startElement(String name, AttributeList attributes)
    {      
        currentTag.clear();        
        currentTag.setName(name);        
    }        
    
    public void endElement(String name)
    {
        //TODO FUTURE: we could do some sanity checking here to make sure the end tag name matches the tag on the top of the stack      
        this.tagHandler.handleTag(currentTag);                        
    }
            
    public void characters(char[] buffer, int start, int length)
    {
    	charactersCallsCount++;        

    	//put char data into the tag
        currentTag.addInnerText(buffer, start, length);        
    }
    
}
