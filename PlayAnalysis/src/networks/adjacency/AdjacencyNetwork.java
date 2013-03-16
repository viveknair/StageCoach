package networks.adjacency;

import gll.GlobalLineNode;
import gll.LineNodeHeader;

import java.util.ArrayList;
import java.util.HashMap;

import networks.Network;

import line.Line;
import line.LineType;
import line.quote.Quote;
import play.StageCoach;
import character.Character;
import configuration.PlayConfig;

public class AdjacencyNetwork extends Network {
	private StageCoach currentPlay; 
	private PlayConfig playConfig;
	//Represents the character edges
	private HashMap<String, Character> characters;
	private HashMap <Character, HashMap<Character, Integer>> edgeWeights;

	public AdjacencyNetwork(StageCoach currentPlay) {
		this.currentPlay = currentPlay; 
		characters = new HashMap<String, Character>();
		edgeWeights = new HashMap <Character, HashMap<Character, Integer>>();
	}
	
	private void previousQuoteAnalysis(Character currentCharacter, GlobalLineNode prevNodeQuote, Quote previousQuote, int totalTokenCount) {
		if (prevNodeQuote != null) { 
			Line previousLine = prevNodeQuote.getLine();
			if (previousLine.getType() == LineType.QUOTE) {
				previousQuote = (Quote) previousLine; 
			}
			if (previousQuote != null) {
				// System.out.println("Previous node is not null");
				// System.out.println("The total token count is " + totalTokenCount);
				HashMap<Character, Integer> characterMap = edgeWeights.get(currentCharacter);
				if (characterMap == null) {
					characterMap = new HashMap<Character, Integer>();
				}
				Integer characterCount = characterMap.get(previousQuote.getCharacter());
				if (characterCount != null) {
					characterMap.put(previousQuote.getCharacter(), characterCount.intValue() + totalTokenCount);
				} else {
					characterMap.put(previousQuote.getCharacter(), totalTokenCount);
				}
				edgeWeights.put(currentCharacter, characterMap);
			}
		}
	}
	
	private void nextQuoteAnalysis(Character currentCharacter, GlobalLineNode nextNodeQuote, Quote nextQuote, Quote previousQuote, int totalTokenCount) {
		if (nextNodeQuote != null) {
			Line nextLine = nextNodeQuote.getLine();
			if (nextLine.getType() == LineType.QUOTE) {
				nextQuote = (Quote) nextLine; 
			}
			if ((nextQuote != null && previousQuote != null && nextQuote.getCharacter() != previousQuote.getCharacter()) 
				 || (nextQuote != null)) {
				HashMap<Character, Integer> characterMap = edgeWeights.get(currentCharacter);
				if (characterMap == null) {
					characterMap = new HashMap<Character, Integer>();
				}
				Integer characterCount = characterMap.get(nextQuote.getCharacter());
				if (characterCount != null) {
					characterMap.put(nextQuote.getCharacter(), characterCount.intValue() + totalTokenCount);
				} else {
					characterMap.put(nextQuote.getCharacter(), totalTokenCount);
				}
				edgeWeights.put(currentCharacter, characterMap);
			}
		}
	}
	
	public void constructAdjacency() {
		characters = currentPlay.returnCharacters(null, null);
		for (String charName : characters.keySet()) {
			Character currentCharacter = characters.get(charName);
			ArrayList<Quote> quotes = currentCharacter.getQuotes();
			for (Quote quote : quotes) {
				int totalTokenCount = quote.getTotalTokens();
				
				// The fact that the GlobalLineNode is actually exposed to the client
				// is troubling. We have to refactor this design substantially.
				GlobalLineNode nodeQuote = quote.getGllNode();
				GlobalLineNode prevNodeQuote = nodeQuote.getPreviousNode(LineNodeHeader.DEFAULT);
				GlobalLineNode nextNodeQuote = nodeQuote.getNextNode(LineNodeHeader.DEFAULT);
				
				Quote previousQuote = null;	
				Quote nextQuote = null;
				
				previousQuoteAnalysis(currentCharacter, prevNodeQuote, previousQuote, totalTokenCount);
				nextQuoteAnalysis(currentCharacter, nextNodeQuote, nextQuote, previousQuote, totalTokenCount);
			}
		}
	}
	
	public HashMap <Character, HashMap<Character, Integer>> getEdgeWeights() {
		return edgeWeights; 
	}
	
	public String toString() {
		StringBuffer graphString = new StringBuffer("");
		for (Character character : edgeWeights.keySet()) {
			HashMap<Character, Integer> hashedCharacterMap = edgeWeights.get(character);
			graphString.append("Character: " + character.getName() + "\n");
			for (Character targetCharacter : hashedCharacterMap.keySet()) {
				graphString.append("   Target Character: " + targetCharacter.getName() + ", Value: " + hashedCharacterMap.get(targetCharacter) + "\n");
			}
		}
		return graphString.toString();
	}
}