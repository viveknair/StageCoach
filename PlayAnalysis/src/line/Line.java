package line;

import time.TimeUnit; 

public abstract class Line {
	// Define some shared methods between the Quote and StageDirection classes.
	private String description = null;
	private LineType type = LineType.QUOTE; 

	// Is this necessary?
	public String getDescription() {
		return description; 
	}	
	
	public Line(LineType type) {
		this.type = type; 
	}
}
