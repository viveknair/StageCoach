package line;

import time.TimeUnit; 

public abstract class Line {
	// Define some shared methods between the Quote and StageDirection classes.
	protected String description = null;
	private LineType type = LineType.QUOTE; 

	public String toString() {
		return description;
	}	
	
	public Line(LineType type) {
		this.type = type; 
	}
}
