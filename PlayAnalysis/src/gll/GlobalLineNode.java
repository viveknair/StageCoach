package gll;

import java.util.ArrayList;
import java.util.HashMap;

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
	
	// Just initializes three empty transitions
	
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
	
	public GlobalLineNode getNextNode(LineNodeHeader header) {
		NodeTransition transition = transitions.get(header);
		return transition.getNext();
	}
	
	public void setNextNode(LineNodeHeader header, GlobalLineNode nextNode) {
		NodeTransition transition = transitions.get(header);
		transition.setNext(nextNode);
		transitions.put(header, transition);
	}
	
	private void setTransitions(ArrayList<GlobalLineNode> headerNodes) {
		for(int i = 0; i < headerNodes.size(); i++) {
			GlobalLineNode headerNode = headerNodes.get(i);
			NodeTransition transition = transitions.get(headerNode.getHeader());
			transition.setPrevious(headerNode);
			
			// Super hacky but I'll refactor later
			if (i == 0 && header == LineNodeHeader.DEFAULT) {
				headerNode.setNextNode(LineNodeHeader.DEFAULT, this);
			}
			
			if (i == 1 && header == LineNodeHeader.SCENE) {
				headerNode.setNextNode(LineNodeHeader.SCENE, this);
			}
			
			if (i == 2 && header == LineNodeHeader.ACT) {
				headerNode.setNextNode(LineNodeHeader.ACT, this);
			}
			
			transitions.put(headerNode.getHeader(), transition);
		}
		
	}
	
	public void intializeTransitions() {
		for (LineNodeHeader header : LineNodeHeader.values()) {
			transitions.put(header, new NodeTransition());
		}
	}

	public GlobalLineNode(ArrayList<GlobalLineNode> headerNodes, LineNodeHeader header, Line line) {
		this.header = header; 
	
		setLine(line);
		setHeader(header);
		
		intializeTransitions();
		if (headerNodes != null) {
			setTransitions(headerNodes); 
		}
	}
}
