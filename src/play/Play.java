package play;

import java.io.*;
import java.util.*;
import unit.*;
import quote.*;
import configuration.*;
import character.Character;

public class Play {

	private static final String CHARACTER_TOKEN = "CHARACTER";
	private static final String TITLE_TOKEN = "TITLE";
	
	private static String fileName = null;
	private static String title = null;
	private static ArrayList<Character> characters = new ArrayList<Character>();
	private static ArrayList<Quote> quotes = null;
	private static ArrayList<String> rawLines = null;
	private static PlayConfig pconf = null;
	
	// Index into the ArrayList of raw lines, 
	// describing up to what file the lines have 
	// been parsed
	private static int parseCursor = 0;

	public Play(PlayConfig pconf) {
		this.pconf = pconf;
	}
	
	private void parseTitle() {
		for (int i = parseCursor; i < rawLines.size(); i++) {
			StringTokenizer titleTokenizer = new StringTokenizer(rawLines.get(i), " ,:");
			String titleToken = null;
			if (titleTokenizer.hasMoreTokens()) {
				titleToken = titleTokenizer.nextToken();
			}
			if (titleToken != null && titleToken.equals(TITLE_TOKEN)) {
				this.title = titleToken;
				break;
			}
			parseCursor ++;
		}
		this.title = null;
	}
	
	private String concatenateTokens(StringTokenizer tokenizer) {
		StringBuffer totalString = new StringBuffer("");
		while (tokenizer.hasMoreTokens()) {
			totalString.append(tokenizer.nextToken());
		}
		return totalString.toString();
	}
	
	private void parseCharacters() {
		boolean characterSectionStarted = false;
		for (int i = parseCursor; i < rawLines.size(); i++) {
			if (characterSectionStarted && rawLines.get(i).trim().length() == 0) {
				break;
			}
			StringTokenizer characterTokenizer = new StringTokenizer(rawLines.get(i), " ,");
			String characterToken = null;
			if (characterTokenizer.hasMoreTokens()) {
				characterToken = characterTokenizer.nextToken();
			}
			if (characterSectionStarted) {
				String description = concatenateTokens(characterTokenizer);
				this.characters.add(new Character(characterToken, description));
			}
			
			if (characterToken.equals(CHARACTER_TOKEN)) {
				characterSectionStarted = true;
			}
			parseCursor ++;
		}
	}
	
	private void parseBodyLines() {
		for (int i = parseCursor; i < rawLines.size(); i++) {
			StringTokenizer bodyTokenizer = new StringTokenizer(rawLines.get(i));
			this.quotes = new ArrayList<Quote>();
		}
	}

	public void constructFeatures() {
		try {
			rawLines = pconf.returnLines();
		} catch(Exception e) {
			System.out.println("Error with the reading of the fileName: " + pconf.get("fileName"));
		}
		
		parseTitle();
		parseCharacters();
		parseBodyLines();
	}

	public ArrayList<Character> returnCharacters(Unit time) {
		// Returns the characters at the defined unit of time
		return new ArrayList<Character>();
	}
	
	public ArrayList<Quote> returnQuotesForCharacter(Character character) {
		// processing quotes
		return new ArrayList<Quote>();
	}
}