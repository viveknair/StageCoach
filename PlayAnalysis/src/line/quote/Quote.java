package line.quote;

import line.Line;
import line.LineType;

import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import character.Character;
import action.Action;

public class Quote extends Line {

	private static final Pattern bracketsPattern = Pattern.compile("\\[(.*?)\\]");
	
	private String rawQuote;
	private String speaker;
	private Character character = null; 
	private ArrayList<Action> containedActions = new ArrayList<Action>();
	private ArrayList<Character> mentionedCharacters = new ArrayList<Character>();
	
	// Tracks the token location in the quote up to which we
	// have parsed
	private int quoteCursor = 0;
	
	/*
	 * Public API: 
	 * 	getSpeaker
	 * 	getRawQuote
	 * 	getMentionedCharacters
	 * 	getActions
	 */
	
	public String getSpeaker() {
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

	public Quote(LineType type, String speaker, String rawQuote) {
		super(type);
		this.rawQuote = rawQuote;
		this.speaker = speaker;
		
		parseActions();
	}
}