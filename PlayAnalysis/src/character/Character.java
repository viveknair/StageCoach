package character;

import java.util.*;
import quote.Quote;

public class Character {
<<<<<<< HEAD
	private String name = null;
	private String description = null;
	private ArrayList<Quote> quotes = new ArrayList<Quote>();
=======
	private static String name;
	private static String description;
	private static ArrayList<Quote> quotes = new ArrayList<Quote>();
>>>>>>> c0299da0c1ba1a8aee90661a3c0bbd0725d5a626
	
	public ArrayList<Quote> getQuotes() {
		return quotes;
	}
	
	public void addQuote(Quote quote) {
		quotes.add(quote);
	}
<<<<<<< HEAD
	
	public String getName() {
		return name;
	}
	
	public String getDescription() {
		return description;
	}
=======
>>>>>>> c0299da0c1ba1a8aee90661a3c0bbd0725d5a626

	public Character(String name, String description) {
		this.name = name;
		this.description = description;
	}
}