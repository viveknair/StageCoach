package examples;

import java.util.ArrayList;

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
import networks.adjacency.AdjacencyNetwork;

public class AdjacencyApproach {
	private static StageCoach currentPlay; 
	
	public static void main(String args[]) {
		PlayConfig config = new PlayConfig(PlayType.DEFAULT);
		config.set("fileName", args[0]);
		
		currentPlay = new StageCoach(config);
		currentPlay.instantiatePlay();
		
		System.out.println("The number of lines is " + config.getParsedLines().size());
		
		AdjacencyNetwork network = new AdjacencyNetwork(currentPlay);
		network.constructAdjacency();
		System.out.println(network.toString());
	}
}
