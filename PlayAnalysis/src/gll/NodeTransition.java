package gll;

public class NodeTransition {
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