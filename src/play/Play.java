package play;

import java.io.*;
import java.util.*;
import unit.*;
import quote.*;
import configuration.*;

public class Play {

	private static String fileName = null;
	private static String title = null;
	private static ArrayList<Character> characters = null;
	private static ArrayList<Quote> quotes = null;
	private static PlayConfig pconf = null;

	public Play(PlayConfig pconf) {
		this.pconf = pconf;
	}
	
	private String parseTitle() {
		return null;
	}
	
	private ArrayList<Character> parseCharacters() {
		return characters;
	}

	public void constructFeatures() {
		ArrayList<String> rawQuotes = null;
		try { 
			rawQuotes = pconf.returnLines();
		} catch(Exception e) {
			System.out.println("Error with the reading of the fileName: " + pconf.get("fileName"));
		}
		this.title = parseTitle();
		this.characters = parseCharacters();
	}

	public ArrayList<Character> returnCharacters(Unit time) {
		// Returns the characters at the defined unit of time
		return new ArrayList<Character>();
	}
}