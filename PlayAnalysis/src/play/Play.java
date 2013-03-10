package play;

import java.util.*;

import time.*;
import line.quote.*;
import configuration.*;
import character.Character;
import gll.GlobalLineList;
import line.stage_direction.StageDirection;

public class Play {
	private String title = null;
	private GlobalLineList gll; 
	private HashMap<String, Character> characters = new HashMap<String, Character>();
	private ArrayList<Quote> quotes = new ArrayList<Quote>();
	private ArrayList<StageDirection> stageDirections = new ArrayList<StageDirection>();
	private PlayConfig pconf = null;

	public Play(PlayConfig pconf) {
		this.pconf = pconf;
	}
	
	public String getTitle() {
		return title;
	}
	
	public String configGet(String key) {
		return pconf.get(key);
	}
	
	public void instantiatePlay() {
		pconf.constructFeatures();
		characters = pconf.getCharacters();
		quotes = pconf.getQuotes(); 
		stageDirections = pconf.getStageDirections();
		
		// Should this be in PlayConfig?
		constructLineList();
	}
	
	// Creating the boilerplate for constructing the line list
	private void constructLineList() {
		gll = new GlobalLineList();
	}
	
	/*
	 * Specify a start unit of time to an end unit of time 
	 * to collect information.
	 * 
	 * TODO: Rewrite so we can access stage directions for a given act or scene
	 */
	public ArrayList<StageDirection> returnStageDirections(TimeUnit stime, TimeUnit etime) {
		return stageDirections;
	}
	
	/*
	 * Specify a start unit of time to an end unit of time 
	 * to collect information.
	 * 
	 * TODO: Rewrite so we can access stage directions for a given act or scene
	 */
	public ArrayList<Quote> returnQuotes(TimeUnit stime, TimeUnit etime) {
		return quotes;
	}

	/*
	 * Specify a start unit of time to an end unit of time 
	 * to collect information.
	 * 
	 * TODO: Rewrite so we can access characters for a given act or scene
	 */
	public HashMap<String, Character> returnCharacters(TimeUnit stime, TimeUnit etime) {
		// Traversal of the linked list to gather all of the characters
		// Returns the characters at the defined unit of time
		// We DON't want to edge case in here for values of stime and etime but
		// for now we'll concentrate on it returning the proper characters.
		//TODO: Add time component
		if (!characters.isEmpty()) {
			return characters;
		}
		return new HashMap<String, Character>();
	}
}
