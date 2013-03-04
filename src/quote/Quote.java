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
	
	private String parsedQuote() {
		return null;
	}
	
	private Character parsedSpeaker() {
		return new Character(null, null); 
	}
	
	private ArrayList<Character> parsedMentionChars() {
		return new ArrayList<Character>();
	}
	
	private ArrayList<Action> parsedActions() {
		return new ArrayList<Action>();
	}
	
	private ArrayList<Action> getActions() {
		return containedActions;
	}
	
	public Quote(String rawQuote) {
		this.rawQuote = parsedQuote();
		this.speaker = parsedSpeaker();
		this.mentionedCharacters = parsedMentionChars();
		this.containedActions = parsedActions();
	}
}
