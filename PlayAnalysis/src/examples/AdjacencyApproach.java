package examples;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
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
		File folder = new File(args[0]);
		File [] listOfFiles = folder.listFiles();
		for (File file : listOfFiles) {
			PlayConfig config = new PlayConfig(PlayType.DEFAULT);
			config.set("fileName", file.getAbsolutePath());
		
			currentPlay = new StageCoach(config);
			currentPlay.instantiatePlay();
			
			System.out.println("The number of lines is " + config.getParsedLines().size());
			
			AdjacencyNetwork network = new AdjacencyNetwork(currentPlay);
			network.constructAdjacency();
			System.out.println(network.toString());
		    try{
		      // Create file 
			  FileWriter fstream = new FileWriter("/Users/viveknair/Downloads/narrative_analysis/output/test_results/" + file.getName());
			  BufferedWriter out = new BufferedWriter(fstream);
			  out.write(network.toString());
			  out.close();
			} catch (Exception e) {
			  System.err.println("Error: " + e.getMessage());
	        }
		}
	}
}
