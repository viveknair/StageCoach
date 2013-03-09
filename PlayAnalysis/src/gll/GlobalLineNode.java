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
	
	private void setTransitions(ArrayList<GlobalLineNode> headerNodes) {
		System.out.println(headerNodes);
		for(GlobalLineNode headerNode : headerNodes) {
			NodeTransition transition = transitions.get(headerNode.getHeader());
			transition.setPrevious(headerNode);
			
			transitions.put(headerNode.getHeader(), transition);
		}
	}
	
	public void intializeTransitions() {
		NodeTransition[] emptyTransitions = { new NodeTransition(), new NodeTransition(), 
											  new NodeTransition(), new NodeTransition() };
		LineNodeHeader[] headerTypes = { LineNodeHeader.ACT, LineNodeHeader.SCENE, 
										 LineNodeHeader.DEFAULT, LineNodeHeader.START };
		for (int i= 0; i < emptyTransitions.length; i ++) {
			transitions.put(headerTypes[i], emptyTransitions[i]);
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