package line;

import time.TimeUnit; 

public abstract class Line {
	// Define some shared methods between the Quote and StageDirection classes.
	private String description = null;

	public String getDescription() {
		return description; 
	}
	
	// This logic is not specific to either Quote or
	// StageDirection
	public TimeUnit returnLocation() {
		return null; 
	}
	
	public Line(String description) {
		this.description = description; 
	}
}
