package examples;

import line.Line;
import play.StageCoach; 
import configuration.PlayConfig;
import configuration.PlayType;
import java.util.HashMap;
import character.Character; 
import line.quote.Quote;
import gll.GlobalLineNode;
import gll.LineNodeHeader;
import line.LineType;

public class SingleCharacterCentrality {
	
	private static StageCoach currentPlay;
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
		
		constructCharacterCentrality(THRESHOLD);
		
		System.out.println("The number of lines is " + config.getParsedLines().size());
	}
}