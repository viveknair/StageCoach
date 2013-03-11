package configuration;

import gll.LineNodeHeader;

import java.io.*;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import action.Action;

import line.Line;
import line.meta_information.MetaInformation;
import line.quote.Quote;
import line.stage_direction.StageDirection;

import character.Character;


public class PlayConfig {
	private static final String TITLE_TOKEN = "TITLE";
	private static final String CHARACTER_TOKEN = "CHARACTERS";
	private static final char STAGE_DIRECTION_ITEM = '[';
	private static final String FILE_NAME_KEY = "fileName";
	
	private static Pattern p = Pattern.compile("\\[(.*?)\\]");
	
	private PlayType type;
	private ArrayList<String> lines = null;
	private HashMap<String, String> conf = new HashMap<String, String>();

	HashMap<String, Character> characters = new HashMap<String, Character>();
	private ArrayList<StageDirection> stageDirections = new ArrayList<StageDirection>();
	private ArrayList<Quote> quotes = new ArrayList<Quote>();
	
	private List<Pattern> metaPatterns = new ArrayList<Pattern>();
	private ArrayList<Line> parsedLines = new ArrayList<Line>();
	
	private class LineHeaderTuple {
		private Line line; 
		private LineNodeHeader header; 
		
		public Line getLine() {
			return line; 
		}
		
		public LineNodeHeader getHeader() {
			return header; 
		}
	}
	
	// Index into the ArrayList of raw lines, 
	// describing up to what file the lines have 
	// been parsed
	private static int parseCursor = 0;

	public PlayConfig(PlayType type) {
		this.type = type;
		
		if (metaPatterns.isEmpty()) {
			metaPatterns.add(Pattern.compile("Act [0-9]+"));
			metaPatterns.add(Pattern.compile("Act [0-9]+ Scene [0-9]+"));
		}
	}
	
	public void set(String key, String value) {
		conf.put(key, value);
	}
	
	public String get(String key) {
		return conf.get(key);
	}
	
	public HashMap<String, Character> getCharacters() {
		return characters; 
	}
	
	public ArrayList<Quote> getQuotes() {
		return quotes;
	}
	
	public ArrayList<StageDirection> getStageDirections() {
		return stageDirections; 
	}
	
	public ArrayList<Line> getParsedLines() {
		return parsedLines;
	}
	
	public void constructFeatures() {
		try {
			System.out.println("About to read from the file: " + conf.get(FILE_NAME_KEY));
			lines = returnLines();
		} catch(IOException e) {
			System.out.println("Error with the reading of the fileName: " + conf.get(FILE_NAME_KEY));
		}
		
		parseTitle();
		parseCharacters();
		parseBodyLines();
	}
	
	private String parseTitle() {
		for (int i = parseCursor; i < lines.size(); i++) {
			StringTokenizer titleTokenizer = new StringTokenizer(lines.get(i), " ,:");
			String titleToken = null;
			String titleIdentifier = null;
			if (titleTokenizer.hasMoreTokens()) {
				titleIdentifier = titleTokenizer.nextToken();
				titleToken = concatenateTokens(titleTokenizer);
				System.out.println(titleToken);
			}
			if (titleToken != null && titleIdentifier != null && titleIdentifier.equals(TITLE_TOKEN)) {
				parseCursor ++;
				return titleToken; 
			}
			parseCursor ++;
		}
		return null; 
	}
	
	private void parseCharacters() {
		boolean characterSectionStarted = false;
		for (int i = parseCursor; i < lines.size(); i++) {
			if (characterSectionStarted && lines.get(i).trim().length() == 0) {
				break;
			}
			StringTokenizer characterTokenizer = new StringTokenizer(lines.get(i), ",");
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
	
	private void parseBodyLines() {
		for (int i = parseCursor; i < lines.size(); i++) {
			if (lines.get(i).trim().length() == 0)
				continue; 
			
			MetaInformation meta = parseMetaInformation(lines.get(i));
			if	(meta != null) {
				parsedLines.add(meta);
			}
			
			/*
			 * Stage Direction: Parsing out the single-line stage directions
			 */
			StageDirection sdir = parseStageDirection(lines.get(i));
			if (sdir != null) {
				stageDirections.add(sdir);
				parsedLines.add(sdir);
				continue;
			}
			
			/*
			 * Character line: Parsing out the single-line character dialogue line.
			 */
			Quote diagQuote = parseQuote(lines.get(i));
			if (diagQuote != null) {
				parsedLines.add(diagQuote);
				quotes.add(diagQuote);
			}
		}
	}
	
	
	private MetaInformation parseMetaInformation(String rawLine) {
		for (Pattern p : metaPatterns) {
			Matcher m = p.matcher(rawLine);
			if (m.find()) {
				// We can use m.group(1) to specifically refer to extracted values.
				System.out.println(rawLine);
				MetaInformation newMeta = new MetaInformation(rawLine);
				return newMeta;
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
			ArrayList<String> charNames = new ArrayList<String>(characters.keySet());
			Character foundCharacter = null;
			for (String charName : charNames) {
				if (charName.indexOf(potentialCharacter) != -1) {
					foundCharacter = characters.get(charName);
				}
			}
			if (foundCharacter != null) {
				String restQuote = concatenateTokens(quoteTokenizer);
				if (restQuote != null) {
					System.out.println(restQuote);
					Quote newQuote = new Quote(foundCharacter, restQuote);
					foundCharacter.addQuote(newQuote);
					for (Action action : newQuote.getActions()) {
						System.out.println("	Action: " + action.getRelation());
					}
					return newQuote;
				}
			}
		}
		return null; 
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
				// System.out.println("The stage direction is " + mainDirection);
				return new StageDirection(mainDirection);
			}
		}
		return null; 
	}
	
	public ArrayList<String> returnLines() throws IOException {
		if (lines != null) {
			return lines;
		}
		lines = new ArrayList<String>();
		
		FileInputStream fstream = new FileInputStream(conf.get("fileName"));
        DataInputStream in = new DataInputStream(fstream);
        BufferedReader br = new BufferedReader(new InputStreamReader(in));
        String currentLine;
        while ((currentLine = br.readLine()) != null) {
        	lines.add(currentLine);
        }
		return lines;
	}
	
	private String concatenateTokens(StringTokenizer tokenizer) {
		StringBuffer totalString = new StringBuffer("");
		while (tokenizer.hasMoreTokens()) {
			totalString.append(tokenizer.nextToken() + " ");
		}
		return totalString.toString();
	}
}