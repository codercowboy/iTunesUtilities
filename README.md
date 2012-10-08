iTunes Utilities
================

iTunes Utilities is an open-source Java library that provides cross-platform functionality related to parsing and detecting the Itunes library xml file. 

* written by Jason Baker ([jason@onejasonforsale.com](mailto:jason@onejasonforsale.com))
* on github: [https://github.com/codercowboy/iTunesUtilities](https://github.com/codercowboy/iTunesUtilities)
* more info: [http://www.codercowboy.com](http://www.codercowboy.com)

Originally published on Jason's World's Worst Software website.

Description
===========

The Itunes Utilities java library is a collection of java classes you can use in your own application to work with iTunes' library data. This library is used in iPlaylist Copier to parse track and playlist information out of iTunes library files.

Features:
* Fully-featured Java object representations of the Itunes library, playlists, and tracks.
* Easy to use itunes library parser.
* Itunes library auto-detector.

Usage Instructions
==================

* Step 1: Add ItunesUtilities.jar to your project

First you need to put the ItunesUtilities.jar file in your project. Copy the jar to your classpath, and if you use an IDE such as eclipse, make sure the project is configured to use the jar library in your project.

* Step 2: Run the ItunesUtilities demo 

Type the following on the command line in the directory with the jar file:

java -jar ItunesUtilities.jar

Look over what the sample program outputs and then look at the code for the sample in ItunesUtilitiesUsageSample.java. The sample program is in the package 'com.worldsworstsoftware.sample'. 

* Step 3: Code Away!

Use ItunesUtilities to your heart's desire. Hopefully the library will add value to your project and save you some time from writing some code you'd otherwise have to write on your own. 

Developer Documentation
=======================

Note: This project is dormant/old. It was created/published in 2007, back when iTunes was at version 7. It may or may not work with the latest versions of iTunes. 

The iTunes Utilities library is a dormant project that was a work in progress. Jason was working on javadocing the entire library and writing some unit tests to make sure the library functions reliably moving forward. 

For now the library should be considered in beta and your mileage may vary.

The Java Source level is 1.4, because OS X 10.3 does not have Java 1.5 and I wanted the library to be compatible with OS X 10.3+. 

The jar file is built with the "build jar.jardesc" file, you should be able to right click this in eclipse and click "Create Jar" to overwrite the jar that's in the build subdirectory.

You can try out the jar file by typing the following on the command line:

java -jar ItunesUtilities.jar

That will run the ItunesUtilitiesUsageSample Main() function that's under the source code in the sample subdirectory. 

If you have any questions about using ItunesUtilities in your own project or need help coding with the library, don't hesitate to email me at jason@onejasonforsale.com.

Library Details:
* ItunesUtilities is iTunes 7 compatible.
* Java source level in library is 1.4 (os x 10.3 does not have java 1.5)
* Project is an eclipse 3.3 project.
* Included sample program shows example usage of the library.
* Library is distributed as a standard java JAR file, for easy inclusion in your own projects.
* Full source code to the library is available within JAR file, as well as in the source distribution zip file.

There are probably easier ways to do the iTunes library XML parsing using other libraries out there for apple-style XML files, if you know of something, e-mail me, I'll note it here.   

The project file is an old netbeans 5.0 project, with some tie-ins to some netbeans helper classes netbeans provided.

If you'd like to fix something with the app, have at it, fork, contribute back, whatever. I'll give you credit here if you want, including linking to your fork. 

This project was a half-hearted attempt to encapsulate many of the re-usable components of [iPlaylistCopier](https://github.com/codercowboy/iPlaylistCopier) into a library. Features that may be in this library (or may still be in the iPlaylistCopier source..) include: 

Reusable Components:
* Itunes Library XML Parser
* Itunes Playlist File Copier
* POJO objects representing Itunes Library, Playlists, and Tracks
* Cross-Platform Itunes Library Auto-Detector
* Simple String to RegEx method
* String Replacer [java 1.5 String.replace(String, String) w/ 1.4 code]
* Performance Timer Utility Class

The ItunesUtilities library was tested on Windows XP and OS X 10.3.

Credit Where Credit Is Due
==========================

the ItunesUtilities source code benefits from the following examples, tutorials, or hints:

* [David Flanagen's xml sax parser example code](http://www.oreilly.com/catalog/jenut2/chapter/ch19.html)
* [Pawe³ Stobiñski's ignore DTD validation code](http://www.velocityreviews.com/forums/t139773-saxparser-ignore-ltdoctypegt-line.html)
* [gforman and shiva_in's file copying algorithm](http://www.experts-exchange.com/Programming/Programming_Languages/Java/Q_10245809.html)
* [Roedy Green's encode url code](http://mindprod.com/jgloss/urlencoded.html)
* [Linux Box Admin's invalid filename characters list](http://linuxboxadmin.com/articles/filefriction.php)
* [Jay of indyJt's os x iTunes library location hints](http://www.indyjt.com/blog/?p=51)
* [Roedy Green's java regex special characters list](http://mindprod.com/jgloss/regex.html)

Licensing
=========

Licensed with the [Apache license](http://en.wikipedia.org/wiki/Apache_license), which is a great license because, essentially it:
* a) covers liability - my code should work, but I'm not liable if you do something stupid with it
* b) allows you to copy, fork, and use the code, even commercially
* c) is [non-viral](http://en.wikipedia.org/wiki/Viral_license), that is, your derivative code doesn't *have to be* open source to use it

Other great licensing options for your own code: the BSD License, or the MIT License.

Here's the license:

Copyright (c) 2012, Coder Cowboy, LLC. All rights reserved.

Redistribution and use in source and binary forms, with or without
modification, are permitted provided that the following conditions are met:
* 1. Redistributions of source code must retain the above copyright notice, this
list of conditions and the following disclaimer.
* 2. Redistributions in binary form must reproduce the above copyright notice,
this list of conditions and the following disclaimer in the documentation
and/or other materials provided with the distribution.
  
THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS "AS IS" AND
ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO, THE IMPLIED
WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE ARE
DISCLAIMED. IN NO EVENT SHALL THE COPYRIGHT OWNER OR CONTRIBUTORS BE LIABLE FOR
ANY DIRECT, INDIRECT, INCIDENTAL, SPECIAL, EXEMPLARY, OR CONSEQUENTIAL DAMAGES
(INCLUDING, BUT NOT LIMITED TO, PROCUREMENT OF SUBSTITUTE GOODS OR SERVICES;
LOSS OF USE, DATA, OR PROFITS; OR BUSINESS INTERRUPTION) HOWEVER CAUSED AND
ON ANY THEORY OF LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY, OR TORT
(INCLUDING NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE OF THIS
SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.
  
The views and conclusions contained in the software and documentation are those
of the authors and should not be interpreted as representing official policies,
either expressed or implied.

Disclaimer
==========

Jason Baker, World's Worst Software, Coder Cowboy, and iTunes Utilities are not affiliated with or endorsed by Apple Computer in any way.