package gll;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;

import line.Line;
import line.meta_information.MetaInformation;
import gll.LineNodeHeader;

public class GlobalLineList implements List<GlobalLineNode>{
	// Instantiate the line linked list
	GlobalLineNode rootNodeGll; 
	GlobalLineNode tailLineGll; 
	
	// Instantiate the scene linked list
	GlobalLineNode tailSceneGll;
	
	// Instantiate the act linked list
	GlobalLineNode tailActGll;
	
	ArrayList<GlobalLineNode> transitionNodes; 
	
	private static final String ROOT_NODE_DESCRIPTION = "Root node:";
	
	public GlobalLineList() {
		Line rootLineGll = new MetaInformation(ROOT_NODE_DESCRIPTION);
		rootNodeGll = new GlobalLineNode(null, LineNodeHeader.START, rootLineGll);
		
		// Three different linked lists for line, scene, and act.
		tailLineGll = rootNodeGll;
		tailSceneGll = rootNodeGll;
		tailActGll = rootNodeGll; 
		
		transitionNodes = new ArrayList<GlobalLineNode>();
		
		transitionNodes.add(tailLineGll);
		transitionNodes.add(tailSceneGll);
		transitionNodes.add(tailActGll);
		transitionNodes.add(rootNodeGll);
	}
		
	public void addLine(Line nextLine, LineNodeHeader header) {
		GlobalLineNode nextNode = new GlobalLineNode(transitionNodes, header, nextLine);
		
		if (nextNode.getHeader().equals(LineNodeHeader.ACT)) {
			tailActGll = nextNode;
		}
		
		if (nextNode.getHeader().equals(LineNodeHeader.SCENE)) {
			tailSceneGll = nextNode; 	
		}
	}
	
	// Write out the line, act, and scene linked list.
	@Override
	public String toString() {
		StringBuffer lineList = new StringBuffer("");
		GlobalLineNode currentNode = rootNodeGll;
		
		// Currently this will only work for one node since 
		// flags for next are 'null'.
		while (currentNode != null) {
			Line currentLine = currentNode.getLine();
			lineList.append(currentLine.toString() + "\n");
			
			currentNode = currentNode.getNextNode(LineNodeHeader.DEFAULT);
		}
		return lineList.toString(); 
	}

	@Override
	public boolean add(GlobalLineNode e) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void add(int index, GlobalLineNode element) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public boolean addAll(Collection<? extends GlobalLineNode> c) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean addAll(int index, Collection<? extends GlobalLineNode> c) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void clear() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public boolean contains(Object o) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean containsAll(Collection<?> c) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public GlobalLineNode get(int index) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int indexOf(Object o) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public boolean isEmpty() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public Iterator<GlobalLineNode> iterator() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int lastIndexOf(Object o) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public ListIterator<GlobalLineNode> listIterator() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ListIterator<GlobalLineNode> listIterator(int index) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean remove(Object o) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public GlobalLineNode remove(int index) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean removeAll(Collection<?> c) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean retainAll(Collection<?> c) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public GlobalLineNode set(int index, GlobalLineNode element) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int size() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public List<GlobalLineNode> subList(int fromIndex, int toIndex) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Object[] toArray() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public <T> T[] toArray(T[] a) {
		// TODO Auto-generated method stub
		return null;
	}
}