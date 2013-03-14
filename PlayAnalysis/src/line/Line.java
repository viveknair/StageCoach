package line;

import time.TimeUnit; 
import gll.GlobalLineNode; 
import java.util.HashMap; 
import java.util.StringTokenizer;

public abstract class Line {
	// Define some shared methods between the Quote, StageDirection, MetaInformation classes.
	protected String description = null;
	private LineType type;
	private GlobalLineNode node;
	protected HashMap <String, Integer> wordCounts ;
	protected int totalTokens = 0;
	
	public int getTotalTokens() {
		return totalTokens;
	}
	
	protected void tokenizeElements() {
		StringTokenizer tokenizer = new StringTokenizer(description); 
		while (tokenizer.hasMoreTokens()) {
			String nextToken = tokenizer.nextToken();
			Integer latestCount = wordCounts.get(nextToken);
			if (latestCount != null) {
				wordCounts.put(nextToken, new Integer(latestCount.intValue() + 1));
			} else {
				wordCounts.put(nextToken, new Integer(1));
			}
			totalTokens ++;
		}
	}
	
	public LineType getType() {
		return type; 
	}
	
	public GlobalLineNode getGllNode() {
		return this.node; 
	}
	
	public void setGllNode(GlobalLineNode node) {
		this.node = node; 
	}

	public String toString() {
		return description;
	}	
	
	public Line(LineType type) {
		this.type = type; 
		wordCounts = new HashMap <String, Integer>();
	}
}
