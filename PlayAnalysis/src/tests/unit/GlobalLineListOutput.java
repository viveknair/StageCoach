package tests.unit;

import static org.junit.Assert.*;

import character.Character;
import line.Line; 
import line.LineType;
import line.meta_information.MetaInformation;
import line.quote.Quote;
import line.stage_direction.StageDirection;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;
import gll.GlobalLineList;
import gll.LineNodeHeader;

public class GlobalLineListOutput {

	private GlobalLineList gll = null;
	
	@Before
	public void instantiateGll() {
		Character character = new Character("Vivek Nair", "Some description");
		
		gll = new GlobalLineList();
		Line nextLine3 = new MetaInformation("LineType 3");
		Line nextLine2 = new MetaInformation("LineType 2");
		Line nextLine1 = new MetaInformation("LineType 1");
		Line nextQuote1 = new Quote(character, "Some raw quote [Gestures action]");
		Line nextSD1 = new StageDirection("Some stage direction.");
		Line nextEnd = new MetaInformation("End");
		
		gll.addLine(nextLine3,  LineNodeHeader.SCENE);
		gll.addLine(nextLine1,  LineNodeHeader.DEFAULT);
		gll.addLine(nextLine2,  LineNodeHeader.SCENE);
		gll.addLine(nextQuote1, LineNodeHeader.DEFAULT);
		gll.addLine(nextSD1,    LineNodeHeader.ACT);
		gll.addLine(nextEnd,    LineNodeHeader.END);
	}
	
	@Test
	public void mainTest() {
		String gllOutput = gll.toString();
		System.out.println(gllOutput);
		String expectedOutput = "Root node:\nLineType 3\nLineType 1\nLineType 2\n" 
								+ "Some raw quote [Gestures action]\nSome stage direction.\nEnd\n";
		assertTrue(gllOutput.equals(expectedOutput));
	}
	
	@Test
	public void sceneTest() {
		String gllOutput = gll.toStringHeader(LineNodeHeader.SCENE);
		System.out.println(gllOutput);
		String expectedOutput = "Root node:\nLineType 3\nLineType 2\nSome stage direction.\nEnd\n";
		assertTrue(gllOutput.equals(expectedOutput));
	}

	@Test
	public void actTest() {
		String gllOutput = gll.toStringHeader(LineNodeHeader.ACT);
		System.out.println(gllOutput);
		String expectedOutput = "Root node:\nSome stage direction.\nEnd\n";
		assertTrue(gllOutput.equals(expectedOutput));
	}
}