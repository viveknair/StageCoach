package character;

import java.util.*;
import quote.Quote;

public class Character {
	private static String name;
	private static String description;
	private static ArrayList<Quote> quotes = new ArrayList<Quote>();
	
	public ArrayList<Quote> getQuotes() {
		return quotes;
	}
	
	public void addQuote(Quote quote) {
		quotes.add(quote);
	}

	public Character(String name, String description) {
		this.name = name;
		this.description = description;
	}
}