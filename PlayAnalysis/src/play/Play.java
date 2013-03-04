package play;

<<<<<<< HEAD
import java.io.IOException;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

=======
import java.util.*;
>>>>>>> c0299da0c1ba1a8aee90661a3c0bbd0725d5a626
import unit.*;
import quote.*;
import configuration.*;
import character.Character;
<<<<<<< HEAD
import action.Action;
import stage_direction.StageDirection;

public class Play {
	private static final String FILE_NAME_KEY = "fileName";
	private static final String CHARACTER_TOKEN = "CHARACTERS";
	private static final String TITLE_TOKEN = "TITLE";
	private static final char STAGE_DIRECTION_ITEM = '[';
	
	private Pattern p = Pattern.compile("\\[(.*?)\\]");

	
	private String title = null;
	private ArrayList<Character> characters = new ArrayList<Character>();
	private ArrayList<Quote> quotes = new ArrayList<Quote>();
	private ArrayList<StageDirection> stageDirections = new ArrayList<StageDirection>();
	private ArrayList<String> rawLines = null;
	private PlayConfig pconf = null;
=======
import stage_direction.StageDirection;

public class Play {

	private static final String CHARACTER_TOKEN = "CHARACTER";
	private static final String TITLE_TOKEN = "TITLE";
	
	private static String fileName = null;
	private static String title = null;
	private static ArrayList<Character> characters = new ArrayList<Character>();
	private static ArrayList<Quote> quotes = new ArrayList<Quote>();
	private static ArrayList<StageDirection> stageDirection = new ArrayList<StageDirection>();
	private static ArrayList<String> rawLines = null;
	private static PlayConfig pconf = null;
>>>>>>> c0299da0c1ba1a8aee90661a3c0bbd0725d5a626
	
	// Index into the ArrayList of raw lines, 
	// describing up to what file the lines have 
	// been parsed
	private static int parseCursor = 0;

	public Play(PlayConfig pconf) {
		this.pconf = pconf;
	}
	
<<<<<<< HEAD
	public String getTitle() {
		return title;
	}
	
	public String getFileName() {
		return pconf.get(FILE_NAME_KEY);
	}
	
=======
>>>>>>> c0299da0c1ba1a8aee90661a3c0bbd0725d5a626
	private void parseTitle() {
		for (int i = parseCursor; i < rawLines.size(); i++) {
			StringTokenizer titleTokenizer = new StringTokenizer(rawLines.get(i), " ,:");
			String titleToken = null;
<<<<<<< HEAD
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
=======
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
>>>>>>> c0299da0c1ba1a8aee90661a3c0bbd0725d5a626
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
<<<<<<< HEAD
			}			
			if (characterSectionStarted) {
				String description = concatenateTokens(characterTokenizer);
				characters.add(new Character(characterToken, description));
			}
			
			if (characterToken != null && characterToken.equals(CHARACTER_TOKEN)) {
=======
			}
			if (characterSectionStarted) {
				String description = concatenateTokens(characterTokenizer);
				this.characters.add(new Character(characterToken, description));
			}
			
			if (characterToken.equals(CHARACTER_TOKEN)) {
>>>>>>> c0299da0c1ba1a8aee90661a3c0bbd0725d5a626
				characterSectionStarted = true;
			}
			parseCursor ++;
		}
	}
	
<<<<<<< HEAD
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
=======
	private void parseBodyLines() {
		for (int i = parseCursor; i < rawLines.size(); i++) {
			StringTokenizer bodyTokenizer = new StringTokenizer(rawLines.get(i));
			if (!bodyTokenizer.hasMoreTokens()) {
				continue;
			}
>>>>>>> c0299da0c1ba1a8aee90661a3c0bbd0725d5a626
		}
	}

	public void constructFeatures() {
		try {
<<<<<<< HEAD
			System.out.println("The file name is: " + pconf.get("fileName"));
			rawLines = pconf.returnLines();
		} catch(IOException e) {
=======
			rawLines = pconf.returnLines();
		} catch(Exception e) {
>>>>>>> c0299da0c1ba1a8aee90661a3c0bbd0725d5a626
			System.out.println("Error with the reading of the fileName: " + pconf.get("fileName"));
		}
		
		parseTitle();
		parseCharacters();
		parseBodyLines();
	}
	
	private String concatenateTokens(StringTokenizer tokenizer) {
		StringBuffer totalString = new StringBuffer("");
		while (tokenizer.hasMoreTokens()) {
<<<<<<< HEAD
			totalString.append(tokenizer.nextToken() + " ");
=======
			totalString.append(tokenizer.nextToken());
>>>>>>> c0299da0c1ba1a8aee90661a3c0bbd0725d5a626
		}
		return totalString.toString();
	}

	/*
	 * Specify a start unit of time to an end unit of time 
	 * to collect information.
	 */
	public ArrayList<Character> returnCharacters(Unit stime, Unit etime) {
<<<<<<< HEAD
		// Traversal of the linked list to gather all of the characters		
=======
>>>>>>> c0299da0c1ba1a8aee90661a3c0bbd0725d5a626
		// Returns the characters at the defined unit of time
		return new ArrayList<Character>();
	}
}