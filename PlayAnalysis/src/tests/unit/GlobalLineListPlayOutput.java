package tests.unit;

import static org.junit.Assert.*;

import java.util.ArrayList;

import line.Line; 
import org.junit.Before;
import org.junit.Test;

import configuration.PlayConfig;
import configuration.PlayType;
import gll.GlobalLineList; 
import gll.LineNodeHeader;

public class GlobalLineListPlayOutput {
	
	// Obviously terrible but I have no idea how to pass functions into a jUnit test. 
	private static final String LOCATION = "/Users/viveknair/Downloads/narrative_analysis/input/parsed_chekhov_plays";
	private ArrayList<Line> parsedLines = null; 
	private GlobalLineList gll; 
	
	@Before
	public void instantiatePlay() {
		gll = new GlobalLineList();
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
		for (Line nextLine : parsedLines) {
			gll.addLine(nextLine, LineNodeHeader.DEFAULT);
		}
		System.out.println(gll.toString());
		assertTrue(true);
	}
}