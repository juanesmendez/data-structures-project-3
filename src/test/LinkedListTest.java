package test;

import static org.junit.Assert.*;

import java.util.Iterator;

import org.junit.Test;

import model.data_structures.LinkedList;
import model.data_structures.List;

public class LinkedListTest {

	@Test
	public void testAdd() {
		LinkedList<Integer> list = new List<Integer>();
		list.add(1);
		list.add(2);
		list.add(3);
		list.add(4);
		Iterator<Integer> it =list.iterator();
		int result;
		int i=1;
		while(it.hasNext()) {
			result = it.next();
			assertEquals(i,result);
			i++;
		}
	}

	@Test
	public void testDelete() {
		LinkedList<Integer> list = new List<Integer>();
		list.add(1);
		list.add(2);
		list.add(3);
		list.add(4);
		Iterator<Integer> it =list.iterator();
		int result;
		int i=1;
		list.delete(3);
		while(it.hasNext()) {
			result = it.next();
			
			if(i==3) {
				assertEquals(4,result);
				i++;
				continue;
			}
			
			assertEquals(i,result);
			i++;
		}
	}

	@Test
	public void testGetT() {
		LinkedList<String> list = new List<String>();
		list.add("A");
		list.add("B");
		list.add("C");
		list.add("D");
		
		String result = list.get("C");
		assertEquals("C", result);
		result = list.get("A");
		assertEquals("A", result);
	}

	@Test
	public void testSize() {
		LinkedList<String> list = new List<String>();
		int result = list.size();
		assertEquals(0,result);
		list.add("A");
		list.add("B");
		list.add("C");
		list.add("D");
		result = list.size();
		assertEquals(4,result);
	}

	@Test
	public void testGetInt() {
		LinkedList<String> list = new List<String>();
		list.add("A");
		list.add("B");
		list.add("C");
		list.add("D");
		
		String result = list.get(1);
		assertEquals("B", result);
		result = list.get(3);
		assertEquals("D", result);
	}

	@Test
	public void testAddInOrder() {
		LinkedList<Integer> list = new List<Integer>();
		list.addInOrder(3);
		list.addInOrder(1);
		list.addInOrder(4);
		list.addInOrder(2);
		
		Iterator<Integer> it =list.iterator();
		int result;
		int i=1;
		while(it.hasNext()) {
			result = it.next();
			assertEquals(i,result);
			i++;
		}
	}

}
