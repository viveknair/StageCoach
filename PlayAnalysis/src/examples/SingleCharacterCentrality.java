package examples;

import play.StageCoach; 
import configuration.PlayConfig;
import configuration.PlayType;
import character.Character; 
import networks.adjacency.AdjacencyNetwork;

public class SingleCharacterCentrality {
	
	private static StageCoach currentPlay;
	private static AdjacencyNetwork network; 
	private static double THRESHOLD = 0.25; 
	
	private static Character calculateNextMaxCharacter() {
	
		return null; 
	}
	
	private static Character calculateMaxCharacter() {
		
		return null; 
	}
	
	private static void constructCharacterCentrality(double threshold) {
		Character maxCharacter = calculateMaxCharacter(); 
		Character maxNextCharacter = calculateNextMaxCharacter(); 
		// calculate the max weighted edge 
	}

	public static void main(String [] args) {
		PlayConfig config = new PlayConfig(PlayType.DEFAULT);
		config.set("fileName", args[0]);
		
		currentPlay = new StageCoach(config);
		currentPlay.instantiatePlay();
		
		network = new AdjacencyNetwork(currentPlay);
		network.constructAdjacency();
		
		constructCharacterCentrality(THRESHOLD);
		
		System.out.println("The n=umber of lines is " + config.getParsedLines().size());
	}
}