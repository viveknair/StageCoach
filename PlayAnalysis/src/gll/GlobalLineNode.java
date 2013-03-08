package gll;

import java.util.ArrayList;
import java.util.HashMap;

import line.LineType;
import line.Line; 

public class GlobalLineNode {
	
	private class NodeTransition {
		private GlobalLineNode prev = null;
		private GlobalLineNode next = null; 
		
		public void setPrevious(GlobalLineNode prev) {
			this.prev = prev; 
		}
		
		public void setNext(GlobalLineNode next) {
			this.next = next; 
		}
		
		public GlobalLineNode getPrevious() {
			return prev; 
		}
		
		public GlobalLineNode getNext() {
			return next; 
		}
	}
	
	HashMap<LineNodeHeader, NodeTransition> transitions = new HashMap<LineNodeHeader, NodeTransition> (); 
	
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
	
	private void setTransitions(ArrayList<GlobalLineNode> headerNodes) {
		for(GlobalLineNode headerNode : headerNodes) {
			NodeTransition transition = new NodeTransition(); 
			transition.setPrevious(headerNode);
			transition.setNext(null);
			transitions.put(headerNode.getHeader(), transition);
		}
	}

	public GlobalLineNode(ArrayList<GlobalLineNode> headerNodes, LineNodeHeader header, Line line) {
		this.header = header; 
		
		setLine(line);
		setHeader(header);
		setTransitions(headerNodes); 
	}
}