package line.stage_direction;

import line.Line;
import line.LineType;

import java.util.*;
import character.Character;

public class StageDirection extends Line {
	private String description;
	
	public String getDescription() {
		return description;
	}
	
	public ArrayList<Character> getCharacterMentions(ArrayList<Character> allCharacters) {
		return allCharacters;
	}

	public String getLocation() {
	  // Will return a string for now representing the
	  // raw location parameters (if applicable)
	  // Probably will be encapsulated as an object 'Location'
	  // later.
	  return null; 
	}
  
	public StageDirection(LineType type, String description) {
	  super(type);
	  this.description = description;
	}
}
