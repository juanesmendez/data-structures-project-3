package test;

import static org.junit.Assert.*;

import org.junit.Test;

import model.data_structures.IMaxPQ;
import model.data_structures.IMinPQ;
import model.data_structures.MaxPQ;
import model.data_structures.MinPQ;

public class MinPQTest {

	@Test
	public void testIsEmpty() {
		IMinPQ<Integer> minPQ = new MinPQ<Integer>();
		boolean res = minPQ.isEmpty();
		assertEquals(true, res);
		minPQ.insert(4);
		minPQ.insert(5);
		res = minPQ.isEmpty();
		assertEquals(false, res);
	}

	@Test
	public void testSize() {
		IMinPQ<Integer> minPQ = new MinPQ<Integer>();
		int size = minPQ.size();
		assertEquals(0, size);
		minPQ.insert(4);
		minPQ.insert(5);
		size = minPQ.size();
		assertEquals(2, size);
	}

	@Test
	public void testMin() {
		IMinPQ<Integer> minPQ = new MinPQ<Integer>();
		minPQ.insert(4);
		minPQ.insert(5);
		minPQ.insert(7);
		minPQ.insert(2);
		minPQ.insert(3);
		int res = minPQ.min();
		assertEquals(2, res);
		minPQ.insert(1);
		res = minPQ.min();
		assertEquals(1, res);
	}

	@Test
	public void testInsert() {
		
		IMinPQ<Integer> minPQ = new MinPQ<Integer>();
		minPQ.insert(4);
		int size = minPQ.size();
		assertEquals(1, size);
		minPQ.insert(5);
		size = minPQ.size();
		assertEquals(2, size);
		minPQ.insert(7);
		size = minPQ.size();
		assertEquals(3, size);
		minPQ.insert(2);
		size = minPQ.size();
		assertEquals(4, size);
	}

	@Test
	public void testDelMin() {
		IMinPQ<String> minPQ = new MinPQ<String>();
		String result;
		minPQ.insert("B");
		minPQ.insert("D");
		minPQ.insert("K");
		result = minPQ.delMin();
		assertEquals("B", result);
		result = minPQ.delMin();
		assertEquals("D", result);
		result = minPQ.delMin();
		assertEquals("K", result);
		
		
		
	}

}
