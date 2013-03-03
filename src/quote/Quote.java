package quote;

import java.util.*;
import character.Character;

public class Quote {
	private static String rawQuote;
	private static Character speaker;
	private static ArrayList<Character> mentionedCharacters; 
	
	private String parsedQuote() {
		return null;
	}
	
	private Character parsedSpeaker() {
		return new Character(null, null); 
	}
	
	private ArrayList<Character> parsedMentionChars() {
		return new ArrayList<Character>();
	}
	
	public Quote(String rawQuote) {
		this.rawQuote = parsedQuote();
		this.speaker = parsedSpeaker();
		this.mentionedCharacters = parsedMentionChars();
	}
}
