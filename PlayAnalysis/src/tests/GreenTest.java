package tests;
import java.io.IOException;

import play.StageCoach;
import line.quote.Quote;
import configuration.PlayConfig;
import configuration.PlayType;

// Green Test: Basically to see if the code is running correctly or not.
public class GreenTest {
	
	public static void main(String [] args) throws IOException {
		PlayConfig config = new PlayConfig(PlayType.DEFAULT);
		if (args[0].length() == 0) {
			return;
		}
		config.set("fileName", args[0]);
		StageCoach play = new StageCoach(config);
		
		// Construct the features
		play.instantiatePlay();
		System.out.println("-----");
		
		charPrintTest(play);
		quoteAttrTest(play);
		
	}

	private static void quoteAttrTest(StageCoach play) {
		// TODO Auto-generated method stub
	}

	private static void charPrintTest(StageCoach play) {
		for (character.Character dude : play.returnCharacters(null, null).values()) {
			System.out.println("Name: " + dude.getName());
			System.out.println("Description: " + dude.getDescription());
			for (Quote q : dude.getQuotes()) {
				System.out.println(q.getRawQuote());
			}
		}
	}
}