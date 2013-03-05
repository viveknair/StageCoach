package play;

import java.io.IOException;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import unit.*;
import quote.*;
import configuration.*;
import character.Character;
import action.Action;
import stage_direction.StageDirection;

public class Play {
	private static final String FILE_NAME_KEY = "fileName";
	private static final String CHARACTER_TOKEN = "CHARACTERS";
	private static final String TITLE_TOKEN = "TITLE";
	private static final char STAGE_DIRECTION_ITEM = '[';
	
	private Pattern p = Pattern.compile("\\[(.*?)\\]");

	
	private String title = null;
	private HashMap<String, Character> characters = new HashMap<String, Character>();
	private ArrayList<Quote> quotes = new ArrayList<Quote>();
	private ArrayList<StageDirection> stageDirections = new ArrayList<StageDirection>();
	private ArrayList<String> rawLines = null;
	private PlayConfig pconf = null;
	
	// Index into the ArrayList of raw lines, 
	// describing up to what file the lines have 
	// been parsed
	private static int parseCursor = 0;

	public Play(PlayConfig pconf) {
		this.pconf = pconf;
	}
	
	public String getTitle() {
		return title;
	}
	
	public String getFileName() {
		return pconf.get(FILE_NAME_KEY);
	}
	
	private void parseTitle() {
		for (int i = parseCursor; i < rawLines.size(); i++) {
			StringTokenizer titleTokenizer = new StringTokenizer(rawLines.get(i), " ,:");
			String titleToken = null;
			String titleIdentifier = null;
			if (titleTokenizer.hasMoreTokens()) {
				titleIdentifier = titleTokenizer.nextToken();
				titleToken = concatenateTokens(titleTokenizer);
				System.out.println(titleToken);
			}
			if (titleToken != null && titleIdentifier != null && titleIdentifier.equals(TITLE_TOKEN)) {
				title = titleToken;
				parseCursor ++;
				return;
			}
			parseCursor ++;
		}
		title = null;
	}
	
	private void parseCharacters() {
		boolean characterSectionStarted = false;
		for (int i = parseCursor; i < rawLines.size(); i++) {
			if (characterSectionStarted && rawLines.get(i).trim().length() == 0) {
				break;
			}
			StringTokenizer characterTokenizer = new StringTokenizer(rawLines.get(i), ",");
			String characterToken = null;
			if (characterTokenizer.hasMoreTokens()) {
				characterToken = characterTokenizer.nextToken();
			}			
			if (characterSectionStarted) {
				String description = concatenateTokens(characterTokenizer);
				characters.put(characterToken, new Character(characterToken, description));
				System.out.println("Added character: " + characterToken);
			}
			
			if (characterToken != null && characterToken.equals(CHARACTER_TOKEN)) {
				characterSectionStarted = true;
			}
			parseCursor ++;
		}
	}
	
	private StageDirection parseStageDirection(String line) {
		char identifyingToken = line.trim().charAt(0);
		if (identifyingToken == STAGE_DIRECTION_ITEM) {
			Matcher extractStageDirection = p.matcher(line);
			String mainDirection  = null; 
			if (extractStageDirection.find()) {
				mainDirection = extractStageDirection.group(1);
			}
			if (mainDirection != null) {
				System.out.println("The stage direction is " + mainDirection);
				return new StageDirection(mainDirection);
			}
		}
		return null; 
	}
	
	private Quote parseQuote(String line) {
		StringTokenizer quoteTokenizer = new StringTokenizer(line, ".");
		if (!quoteTokenizer.hasMoreTokens()) {
			return null; 
		}
		String potentialCharacter = quoteTokenizer.nextToken();
		
		// Matches the capitalize character names at the beginning of
		// the play. This matches the Project Gutenberg convention
		// for denoting characters.
		if (potentialCharacter.equals(potentialCharacter.toUpperCase())) {
			String restQuote = concatenateTokens(quoteTokenizer);
			if (restQuote != null) {
				System.out.println(restQuote);
				Quote newQuote = new Quote(potentialCharacter, restQuote);
				for( Action action : newQuote.getActions()) {
					System.out.println("	action: " + action.getRelation());
				}
				return newQuote;
			}
		}
		return null; 
	}
	
	private void parseBodyLines() {
		for (int i = parseCursor; i < rawLines.size(); i++) {
			if (rawLines.get(i).trim().length() == 0)
				continue; 
			
			/*
			 * Stage Direction: Parsing out the single-line stage directions
			 */
			StageDirection sdir = parseStageDirection(rawLines.get(i));
			if (sdir != null) {
				stageDirections.add(sdir);
				continue;
			}
			
			/*
			 * Character line: Parsing out the single-line character dialogue line.
			 */
			Quote diagQuote = parseQuote(rawLines.get(i));
			if (diagQuote != null) {
				quotes.add(diagQuote);
			}
		}
	}

	public void constructFeatures() {
		try {
			System.out.println("The file name is: " + pconf.get("fileName"));
			rawLines = pconf.returnLines();
		} catch(IOException e) {
			System.out.println("Error with the reading of the fileName: " + pconf.get("fileName"));
		}
		
		parseTitle();
		parseCharacters();
		parseBodyLines();
	}
	
	private String concatenateTokens(StringTokenizer tokenizer) {
		StringBuffer totalString = new StringBuffer("");
		while (tokenizer.hasMoreTokens()) {
			totalString.append(tokenizer.nextToken() + " ");
		}
		return totalString.toString();
	}

	/*
	 * Specify a start unit of time to an end unit of time 
	 * to collect information.
	 * 
	 * TODO: Rewrite so we can access characters for a given act or scene
	 */
	public HashMap<String, Character> returnCharacters(Unit stime, Unit etime) {
		// Traversal of the linked list to gather all of the characters	
		// Returns the characters at the defined unit of time
		//TODO: Add time component
		if (!characters.isEmpty()) {
			return characters;
		}
		return new HashMap<String, Character>();
	}
}