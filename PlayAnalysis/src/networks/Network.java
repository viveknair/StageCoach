package networks;

import java.util.HashMap;
import java.util.ArrayList;

import character.Character;

public abstract class Network {
	protected HashMap <Character, HashMap<Character, Integer>> edgeWeights;
	protected HashMap<String, Character> characters;
	// We'll add to this as we develop more network criteria
	
	public HashMap<Character, Integer> getCharacterRelations(Character character) {
		return edgeWeights.get(character);
	}
	
	public ArrayList<Character> getCharacters() {
		return new ArrayList<Character>(edgeWeights.keySet());
	}
	
	public ArrayList<Relation> getRelations() {
		ArrayList<Relation> results = new ArrayList<Relation>(); 
		for (Character character : characters.values()) {
			HashMap<Character, Integer> characterRelations = edgeWeights.get(character);
			System.out.println("characterRelations is " + characterRelations);
			if (characterRelations != null) {
				for (Character relchar : characterRelations.keySet()) {
					results.add(new Relation(character, relchar));
				}
			}
		}
		return results; 
	}
}