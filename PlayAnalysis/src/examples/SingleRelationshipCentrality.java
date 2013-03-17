package examples;

import play.StageCoach; 
import configuration.PlayConfig;
import configuration.PlayType;
import character.Character; 
import networks.adjacency.AdjacencyNetwork;
import networks.Relation; 
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.lang.Math; 

public class SingleRelationshipCentrality {
	
	private static StageCoach currentPlay;
	private static AdjacencyNetwork network;
	private static double totalNetworkInteraction; 
	private static double THRESHOLD = 0.25; 
	private static ArrayList<Character> characters;
	private static ArrayList<Relation> edges;
	
	private static Relation maxEdge;
	private static Relation maxNextEdge;
	
	private static Relation findEdgeRank(int rank) {
		return edges.get(rank);
	}
	
	private static double constructDomain() {
		for (Relation edge : edges) {
			totalNetworkInteraction += edge.getWeight();
		}
		return totalNetworkInteraction;
	}
	
	private static void sortEdges() {
		Collections.sort(
		 edges, new Comparator<Relation>() { 
		    public int compare(Relation first, Relation second) {
		    	return (int) (second.getWeight() -  first.getWeight());
		    }
		  }
		);
	}
	
	private static double relationshipCentrality(double threshold) {
		sortEdges();
		double totalNetworkInteraction = constructDomain();
		maxEdge = findEdgeRank(0);
		maxNextEdge = findEdgeRank(1);
		
		return (maxEdge.getWeight() - maxNextEdge.getWeight()) / totalNetworkInteraction; 
	}

	public static void main(String [] args) {
		PlayConfig config = new PlayConfig(PlayType.DEFAULT);
		config.set("fileName", args[0]);
		
		currentPlay = new StageCoach(config);
		currentPlay.instantiatePlay();
		
		network = new AdjacencyNetwork(currentPlay);
		network.constructAdjacency();
		
		characters = network.getCharacters();
		edges = network.getRelations();
		
		double centrality = relationshipCentrality(THRESHOLD);
		
		System.out.println("Single relationship centrality is " + centrality + " for characters, " + maxEdge.getObject().getName() + " " + maxNextEdge.getObject().getName());
	}
}