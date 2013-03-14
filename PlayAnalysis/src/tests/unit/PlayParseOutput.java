package tests.unit;

import static org.junit.Assert.*;

import java.util.ArrayList;

import line.Line; 
import org.junit.Before;
import org.junit.Test;

import configuration.PlayConfig;
import configuration.PlayType;

public class PlayParseOutput {
	
	// Obviously terrible but I have no idea how to pass functions into a jUnit test. 
	private static final String LOCATION = "/Users/viveknair/Downloads/narrative_analysis/input/parsed_chekhov_plays";
	private ArrayList<Line> parsedLines = null; 
	
	@Before
	public void instantiatePlay() {
		PlayConfig config = new PlayConfig(PlayType.DEFAULT);
		config.set("fileName", LOCATION);
		config.constructFeatures();
		parsedLines = config.getParsedLines();
		for(Line line : parsedLines) {
			System.out.println(line.toString());
		}
	}
	
	@Test
	public void testPlayConfig() {
		assertTrue(parsedLines.get(0).toString().equals("Act 1 Scene 1"));
	}
}
