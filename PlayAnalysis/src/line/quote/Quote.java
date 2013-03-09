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
	
	private Character character = null; 
	private ArrayList<Action> containedActions = new ArrayList<Action>();
	private ArrayList<Character> mentionedCharacters = new ArrayList<Character>();
	
	/*
	 * Public API: 
	 * 	getCharacter
	 * 	getRawQuote
	 * 	getMentionedCharacters
	 * 	getActions
	 */
	
	public String getRawQuote() {
		return description;
	}
	
	public ArrayList<Character> getMentionedCharacters() {
		return mentionedCharacters; 
	}
	
	public ArrayList<Action> getActions() {
		return containedActions;
	}

	
	/*
	 * Can only be called once the associateSpeakerToCharacter 
	 * has been called by the client.
	 */
	public Character getCharacter() {
		return character;
	}
	
	/* 
	 * Returns the actions from the corresponding Quote
	 */
	private void parseActions() {
		Matcher bracketsMatch = bracketsPattern.matcher(description);
		while (bracketsMatch.find()) {
			String relation = bracketsMatch.group(1);
			containedActions.add( new Action(character, relation));
		}
	}

	public Quote(Character character, String rawQuote) {
		super(LineType.QUOTE);
		this.description = rawQuote;
		this.character = character;
		
		parseActions();
	}
}