package tests.unit;

import static org.junit.Assert.*;

import java.util.ArrayList;

import org.junit.Before;
import org.junit.Test;
import character.Character;
import line.quote.Quote; 

public class CharacterUnit {

	private Character character; 
	private Quote testQuote; 
	
	@Before
	public void setup() {
		character = new Character("Vivek Nair", "Some description"); 

		testQuote = new Quote(character, "Aren't I cool?");
		character.addQuote(testQuote);
	}
	
	// Basic assertions
	@Test
	public void basicAssertions() {
		assertTrue(character.getName().equals("Vivek Nair"));
		assertTrue(character.getDescription().equals("Some description"));
	}
	
	@Test
	public void basicQuotePossesion() {
		ArrayList<Quote> quotes = character.getQuotes();
		
		assertTrue(testQuote.getCharacter() == character);
		assertTrue(quotes.contains(testQuote));
	}
}
