package quote;

import java.util.*;
import character.Character;
import action.Action;

public class Quote {
	private static String rawQuote;
	private static Character speaker;
	private static ArrayList<Action> containedActions = new ArrayList<Action>();
	private static ArrayList<Character> mentionedCharacters = new ArrayList<Character>();
	
	// Tracks the token location in the quote up to which we
	// have parsed
	private static int quoteCursor = 0;
	
	/*
	 * Public API: 
	 * 	getSpeaker
	 * 	getRawQuote
	 * 	getMentionedCharacters
	 * 	getActions
	 */
	
	public Character getSpeaker() {
		return speaker; 
	}
	
	public String getRawQuote() {
		return rawQuote;
	}
	
	
	public ArrayList<Character> getMentionedCharacters() {
		return mentionedCharacters; 
	}
	
	public ArrayList<Action> getActions() {
		return containedActions;
	}
	
	/*
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
	}
}
