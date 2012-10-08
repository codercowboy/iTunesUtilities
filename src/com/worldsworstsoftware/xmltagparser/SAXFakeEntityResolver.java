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
 * SaxFakeEntityResolver.java
 *
 * Created on September 11, 2006, 9:13 AM
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */

package com.worldsworstsoftware.xmltagparser;

import java.io.ByteArrayInputStream;

import org.xml.sax.EntityResolver;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;

/**
 * The SAXFakeEntityResolver helps to shortcut expensive DTD loading
 * and document validation steps.  
 * 
 * This is useful in the ItunesLibraryParser sense because the underlying
 * SAX xml parser implementation will automatically attempt to validate 
 * the entire Itunes library xml file according to the validation DTDs 
 * referenced by the library file. That validation is an expensive operation
 * because it's going to cause the parser to attempt to fetch the validation
 * DTD from a webhost, and then it's going to do the validation. 
 * 
 * The SAX xml parser uses it's EntityResolver to go get the DTD off the web,
 * which is convenient for us. We can implement a EntityResolver that'll feed 
 * a bogus, minimal xml stream back into the xml parser. The parser will take 
 * that xml stream as attempt to use it as the validation DTD, but the stream
 * won't have any DTD directives in it, thus validation will be skipped entirely.
 * 
 * Thanks to Pawe³ Stobiñski for example code: 
 *   http://www.velocityreviews.com/forums/t139773-saxparser-ignore-ltdoctypegt-line.html
 */
class SAXFakeEntityResolver implements EntityResolver
{
	private static byte[] XML_STREAM = null;
	
	/**
	 * Static initializer to setup the minimal XML Stream that's required
	 * for a DTD validation doc.
	 */
	static
	{
		XML_STREAM = "<?xml version='1.0' encoding='UTF-8'?>".getBytes();
	}
    
    public InputSource resolveEntity(String publicId, String systemId) throws SAXException
    {
        return new InputSource(new ByteArrayInputStream(XML_STREAM));
    }
    
}
