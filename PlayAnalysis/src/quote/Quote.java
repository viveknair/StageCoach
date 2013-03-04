package quote;

import java.util.*;
<<<<<<< HEAD
import java.util.regex.Matcher;
import java.util.regex.Pattern;

=======
>>>>>>> c0299da0c1ba1a8aee90661a3c0bbd0725d5a626
import character.Character;
import action.Action;

public class Quote {
<<<<<<< HEAD

	private static final Pattern bracketsPattern = Pattern.compile("\\[(.*?)\\]");
	
	private String rawQuote;
	private String speaker;
	private Character character = null; 
	private ArrayList<Action> containedActions = new ArrayList<Action>();
	private ArrayList<Character> mentionedCharacters = new ArrayList<Character>();
	
	// Tracks the token location in the quote up to which we
	// have parsed
	private int quoteCursor = 0;
=======
	private static String rawQuote;
	private static Character speaker;
	private static ArrayList<Action> containedActions = new ArrayList<Action>();
	private static ArrayList<Character> mentionedCharacters = new ArrayList<Character>();
	
	// Tracks the token location in the quote up to which we
	// have parsed
	private static int quoteCursor = 0;
>>>>>>> c0299da0c1ba1a8aee90661a3c0bbd0725d5a626
	
	/*
	 * Public API: 
	 * 	getSpeaker
	 * 	getRawQuote
	 * 	getMentionedCharacters
	 * 	getActions
	 */
	
<<<<<<< HEAD
	public String getSpeaker() {
=======
	public Character getSpeaker() {
>>>>>>> c0299da0c1ba1a8aee90661a3c0bbd0725d5a626
		return speaker; 
	}
	
	public String getRawQuote() {
		return rawQuote;
	}
	
<<<<<<< HEAD
=======
	
>>>>>>> c0299da0c1ba1a8aee90661a3c0bbd0725d5a626
	public ArrayList<Character> getMentionedCharacters() {
		return mentionedCharacters; 
	}
	
	public ArrayList<Action> getActions() {
		return containedActions;
	}
	
	/*
<<<<<<< HEAD
	 * The speaker is given as a quote to the 
	 * new Quote object. So we have to associate this 
	 * speaker to one of the available characters.
	 */
	public void associateSpeakerToCharacter(ArrayList<Character> characters) {
		character = characters.get(0);
	}
	
	/*
	 * Can only be called once the associateSpeakerToCharacter 
	 * has been called by the client.
	 */
	public Character returnSpeakingCharacter() {
		return character;
	}
	
	/* 
	 * Returns the actions from the corresponding Quote
	 */
	private void parseActions() {
		Matcher bracketsMatch = bracketsPattern.matcher(rawQuote);
		while (bracketsMatch.find()) {
			String relation = bracketsMatch.group(1);
			containedActions.add( new Action(speaker, relation));
		}
	}

	public Quote(String speaker, String rawQuote) {
		this.rawQuote = rawQuote;
		this.speaker = speaker;
		
		parseActions();
=======
	 * Internal parse of the different sections of the
	 * play (e.g. the speaker, characters, actions)
	 */
	
	private Character parsedSpeaker() {
		return new Character(null, null); 
	}
	
	private ArrayList<Character> parsedMentionChars() {
		return new ArrayList<Character>();
	}
	
	private ArrayList<Action> parsedActions() {
		return new ArrayList<Action>();
	}

	
	public Quote(String rawQuote) {
		this.rawQuote = rawQuote;
		this.speaker = parsedSpeaker();
		this.mentionedCharacters = parsedMentionChars();
		this.containedActions = parsedActions();
>>>>>>> c0299da0c1ba1a8aee90661a3c0bbd0725d5a626
	}
}
