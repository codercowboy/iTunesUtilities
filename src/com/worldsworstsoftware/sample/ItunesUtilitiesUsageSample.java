package com.worldsworstsoftware.sample;

import java.util.Iterator;
import java.util.List;
import java.util.Random;
import java.util.Set;

import com.worldsworstsoftware.itunes.ItunesLibrary;
import com.worldsworstsoftware.itunes.ItunesPlaylist;
import com.worldsworstsoftware.itunes.ItunesTrack;
import com.worldsworstsoftware.itunes.parser.ItunesLibraryParser;
import com.worldsworstsoftware.itunes.parser.logging.DefaultParserStatusUpdateLogger;
import com.worldsworstsoftware.itunes.parser.logging.ParserStatusUpdateLogger;
import com.worldsworstsoftware.itunes.util.ItunesLibraryFinder;
import com.worldsworstsoftware.logging.DefaultStatusUpdateLogger;
import com.worldsworstsoftware.logging.StatusUpdateLogger;

public class ItunesUtilitiesUsageSample
{

	/**
	 * @param args
	 */
	public static void main(String[] args)
	{
		print("This sample program makes use of the ItunesUtilities.");
		
		print("--------------------[ StatusUpdateLogger ]-----------------");		
		
		print("Most of the ItunesUtilities use a StatusUpdateLogger object " +
				"to report status, so let's set one of those up first..");
		
		StatusUpdateLogger logger = new DefaultStatusUpdateLogger(true, System.out);
		
		print("We're using a DefaultStatusUpdateLogger implementation, " +
				"we've turned on debugging output and set the logger to just " +
				"print to System.out.");
		
		print("--------------------[ ItunesLibraryFinder ]-----------------");		
		
		print("First, lets use the library auto detector to see if we can " +
				"find your iTunes library..");
		
		print("The ItunesLibraryFinder prints some debugging messages to the " +
				"logger while it's doing its thing.. so here they come..");
		
		String libraryLocation = ItunesLibraryFinder.findLibrary(logger);
		
		if (libraryLocation == null)
		{
			print("Oops, it looks like the ItunesLibraryFinder couldn't find " +
					"the Itunes library in any of the standard places on your " +
					"computer, let's see if you passed the library location " +
					"in as an argument...");
			
			if (args.length == 0 || args[0] == null || args[0].equals(""))
			{
				print("\n\nIt looks like you didnt' pass your itunes library " +
						"file location into the sample program, try again and " +
						"this time specify your itunes library file please!");
				
				return;
			}
			
			libraryLocation = args[0];
			
		}
		
		print("It looks like the library file we're going to use is going to be " +
				"this one:" + libraryLocation);
		
		print("Just to make sure the Auto detection worked okay, lets call into " +
				"the ItunesLibraryFinder and doubly verify that the file is " +
				"indeed an itunes library with " +
				"ItunesLibraryFinder.fileIsItunesLibrary().");
		
		if (ItunesLibraryFinder.fileIsItunesLibrary(libraryLocation, logger))
		{
			print("Success! It's reassuring to know that the library has now been " +
					"verified to be an itunes library, though you probably wouldn't " +
					"need to do that verification in practice, because the " +
					"findLibrary() method of ItunesLibraryFinder calls into the " +
					"fileIsItunesLibrary() method too.");
		}
		else
		{
			print("Ooops, for some reason the library located at \"" + libraryLocation + 
					"\" isn't checking out as an itunes library. Could this be " +
					"user-error? Do we need to fix a bug of some sort?");
			return;
		}
			
		print("Now that we know where your library is, let's parse it into java objects " +
				"we can use such as ItunesLibrary, ItunesTrack, and ItunesPlaylist.");
		
		print("We're going to use the ItunesLibraryParser to parse a ItunesLibrary " +
				"object from our itunes library file. The ItunesLibrary object will " +
				"have internal lists of ItunesTrack and ItunesPlaylist objects that were " +
				"parsed from our library file.");

		print("--------------------[ ParserStatusUpdateLogger ]-----------------");
		
		print("Before we parse though, the ItunesLibraryParser needs a " +
				"ParserStatusUpdateLogger object. A ParserStatusUpdateLogger is a " +
				"StatusUpdateLogger with some added functionality, lets build one of " +
				"those..");
		
		DefaultParserStatusUpdateLogger logger2 = new DefaultParserStatusUpdateLogger(true, System.out);
		
		print("We've setup our parser status update logger to print debug messages and " +
				"print everything to system.out.");
		
		print("The ParserStatusUpdateLogger's additional functionality gives us a hook " +
				"into how often the parser itself will print out a message about parsing " +
				"status. It lets us specify how often a \"XXX tracks parsed\" status message " +
				"should be sent, and we can also specify how often a playlist parse update " +
				"message is sent. Let's set the parser up to tell us about every 200 tracks " +
				"that are parsed, and every playlist that's parsed.");
		
		logger2.setTrackParseUpdateFrequency(200);

		logger2.setPlaylistParseUpdateFrequency(ParserStatusUpdateLogger.UPDATE_FREQUENCY_ALWAYS); //we could also just do 1..
		//..note that there are other common update frequency constants defined in ParserStatusUpdateLogger
		
		print("--------------------[ ItunesLibraryParser ]-----------------");
		
		print("Okay now that our parser status update logger is all setup, we're ready to " +
				"parse, here we go..");
		
		//note that there's also a standard parseLibrary(LibraryLocation) option, in that case no parser status updates are going to be logged anywhere..
		ItunesLibrary library = ItunesLibraryParser.parseLibrary(libraryLocation, logger2);
		
		print("Awesome, all done. Let's check out the ItunesLibrary object..");
		
		print("--------------------[ ItunesLibrary ]-----------------");
		
		print("Let's see here, it looks like your library is for Itunes version " + 
				library.getApplicationVersion());
		
		print(".. and your library has " + library.getTracks().size() + " tracks and " + 
				library.getPlaylists().size() + " playlists.");
					
		print("--------------------[ ItunesTrack ]-----------------");
		
		print("Let's grab a random ItunesTrack out of our ItunesLibrary and examine it..");
		
		Random rand = new Random();
		
		Set trackIds = library.getTracks().keySet();
		
		Integer trackId = null;
		Iterator it = trackIds.iterator();
		for (int i = 0; i < rand.nextInt(trackIds.size() - 1); i++)
		{
			trackId = (Integer) it.next();
		}		 				
		
		ItunesTrack track = library.getTrackById(trackId.intValue());
		
		print("Let's see, our track's name is " + track.getName());
		
		print("It's by " + track.getArtist());
		
		print(".. and the track id is " + track.getTrackID());
		
		String trackName = track.getName();
		
		print("We can get at this track by looking it up in the library with " +
				"library.getTrackById(), or we could just iterate over the entire map of " +
				"tracks by calling library.getTracks() and then iterating through it's keyset. " +
				"(the map is keyed by track id..)");
		
		print("--------------------[ ItunesPlaylist ]-----------------");
		
		
		print("Now let's see what we can do with a ItunesPlaylist object..");
		
		List playlists = library.getPlaylists();
						
		ItunesPlaylist playlist = (ItunesPlaylist) playlists.get(rand.nextInt(playlists.size() - 1));
		
		print("The playlist we found is titled \"" + playlist.getName() + "\", isn't that cute?");
		
		print("Our playlist contains " + playlist.getTrackIDs().size() + " track references " +
				"(stored as a list of Track IDs).");
		
		print("We could just get the list of trackIds from our playlist and build a list of the " +
				"relevant tracks by using library.getTrackByID(), but ItunesPlaylist contains a " +
				"convenience method that'll do this work for us.. so we can just call " +
				"playlist.getPlaylistItems() to get an ordered list of the tracks in that playlist.. " +
				"let's do that..");
		
		List tracks = playlist.getPlaylistItems();
		
		print("Let's examine one of the tracks in this playlist..");
		
		track = (ItunesTrack) tracks.get(0);
		
		print("We already know that a track's track number can be retrieved with track.getTrackNumber()..");
		
		print("For example, this track's track number is " + track.getTrackNumber());
		
		print("But the getPlaylistItems() convenience method also populates each listed track's " +
				"playlistTrackNumber property, so we can call track.getPlaylistTrackNumber() to figure " +
				"out where this track lands in the playlist..");
						
		print("We just pulled this track from the front of the playlist's track list, so hopefully it'll " +
				"say it's track #1..");
		
		print("Does it? Let's See.. here goes..: " + track.getPlaylistTrackNumber());
		
				
		print("ItunesUtilities also contains some helpful utility classes, let's look into " +
				"those..");
		
		print("Well, that's it for the ItunesUtilitiesUsageSample. Bye!");
	}
	
	private static void print(String message)
	{
		int maxLength = 70;
		String messageLeft = message;
		while (messageLeft.length() > maxLength)
		{
			String tmpMessage = messageLeft.substring(0, maxLength);
			tmpMessage = tmpMessage.substring(0, tmpMessage.lastIndexOf(' '));
			System.out.println(tmpMessage);
			messageLeft = messageLeft.substring(tmpMessage.length());
		}
		System.out.println(messageLeft);
		System.out.println("");
	}

}
