package examples;

import play.StageCoach; 
import configuration.PlayConfig;
import configuration.PlayType;
import character.Character; 
import networks.adjacency.AdjacencyNetwork;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;

public class SingleCharacterCentrality {
	
	private static StageCoach currentPlay;
	private static AdjacencyNetwork network;
	private static double totalNetworkInteraction; 
	private static double THRESHOLD = 0.25; 
	private static ArrayList<Character> characters;
	
	private static Character maxCharacter;
	private static Character maxNextCharacter;
	
	private static Character findCharacterRank(int rank) {
		return characters.get(rank);
	}
	
	private static double calculateWeightedDegree(Character character) {
		double edgeValues = 0;
		HashMap<Character, Integer> relCharacters = network.getCharacterRelations(character);
		for(Character relchar: relCharacters.keySet()) {
			edgeValues += relCharacters.get(relchar);
		}
		return edgeValues;
	}
	
	private static double constructDomain() {
		double domain = 0;
		for(Character character : characters) {
			domain += calculateWeightedDegree(character);
		}
		return domain; 
	}
	
	private static void sortCharacters() {
		Collections.sort(
		 characters, new Comparator<Character>() { 
		    public int compare(Character first, Character second) {
		      HashMap<Character, Integer> firstRel = network.getCharacterRelations(first);
		      int firstDomain = 0;
		      int secondDomain = 0; 
			  for(Character relchar: firstRel.keySet()) {
			    firstDomain += firstRel.get(relchar);
			  }
			  
		      HashMap<Character, Integer> secondRel = network.getCharacterRelations(second);
			  for(Character relchar: secondRel.keySet()) {
			    secondDomain += secondRel.get(relchar);
			  }
		      return secondDomain - firstDomain;
		    }
		  }
		);
	}
	
	private static double characterCentrality(double threshold) {
		sortCharacters();
		double totalNetworkInteraction = constructDomain();
		maxCharacter = findCharacterRank(0);
		maxNextCharacter = findCharacterRank(1);
		
		return (calculateWeightedDegree(maxCharacter) - calculateWeightedDegree(maxNextCharacter)) / totalNetworkInteraction; 
	}

	public static void main(String [] args) {
		PlayConfig config = new PlayConfig(PlayType.DEFAULT);
		config.set("fileName", args[0]);
		
		currentPlay = new StageCoach(config);
		currentPlay.instantiatePlay();
		
		network = new AdjacencyNetwork(currentPlay);
		network.constructAdjacency();
		
		characters = network.getCharacters();
		
		double centrality = characterCentrality(THRESHOLD);
		
		System.out.println("Single character centrality is " + centrality + " for character, " + maxCharacter.getName());
	}
}