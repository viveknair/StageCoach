package character;

import java.util.*;
import line.quote.Quote;

public class Character {
	private String name = null;
	private String description = null;
	private ArrayList<Quote> quotes = new ArrayList<Quote>();
	
	public ArrayList<Quote> getQuotes() {
		return quotes;
	}
	
	public void addQuote(Quote quote) {
		quotes.add(quote);
	}
	
	public String getName() {
		return name;
	}
	
	public String getDescription() {
		return description;
	}

	public Character(String name, String description) {
		this.name = name;
		this.description = description;
	}
}