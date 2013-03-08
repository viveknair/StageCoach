package gll;

import line.LineType;
import line.Line; 

public class GlobalLineNode {
	private GlobalLineNode prev;
	private GlobalLineNode next; 
	
	private LineNodeHeader header;
	private Line internalLine = null; 
	private int gllIndex;
	
	public void setLine(Line line) {
		this.internalLine = line; 
	}
	
	public Line getLine() {
		return internalLine;
	}
	
	public void setHeader(LineNodeHeader header) {
		this.header = header; 
	}
	
	public LineNodeHeader getHeader() {
		return header;
	}
	
	public int getGllIndex() {
		return gllIndex; 
	}
	
	public void setNext(GlobalLineNode next) {
		this.next = next; 
	}
	
	public void setPrevious(GlobalLineNode prev) {
		this.prev = prev; 
	}
	
	public GlobalLineNode getNext() {
		return next; 
	}
	
	public GlobalLineNode getPrevious() {
		return prev; 
	}
	
	public GlobalLineNode(GlobalLineNode prev, LineNodeHeader header, Line line) {
		this.header = header; 
		
		setLine(line);
		setHeader(header); 
		setPrevious(prev);
	}
}