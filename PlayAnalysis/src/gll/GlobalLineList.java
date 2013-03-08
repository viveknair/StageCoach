package gll;

import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;

import line.Line;
import gll.LineNodeHeader;

public class GlobalLineList implements List<GlobalLineNode>{
	GlobalLineNode rootGll = new GlobalLineNode(null, LineNodeHeader.START, null);
	GlobalLineNode tailGll = rootGll; 
	
	public void addLine(Line nextLine, LineNodeHeader header) {
		GlobalLineNode nextNode = new GlobalLineNode(tailGll, header, nextLine);
		tailGll.setNext(nextNode);
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