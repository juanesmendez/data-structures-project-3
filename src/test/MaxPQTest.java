package test;

import static org.junit.Assert.*;

import java.util.Iterator;

import org.junit.Test;

import model.data_structures.IMaxPQ;
import model.data_structures.MaxPQ;

public class MaxPQTest {

	@Test
	public void testIsEmpty() {
		IMaxPQ<String> maxPQ = new MaxPQ<String>();
		boolean result = maxPQ.isEmpty();
		assertEquals(true, result);
		maxPQ.insert("K");
		maxPQ.insert("L");
		maxPQ.insert("X");
		maxPQ.insert("A");
		maxPQ.insert("T");
		result = maxPQ.isEmpty();
		assertEquals(false, result);
	}

	@Test
	public void testSize() {
		IMaxPQ<String> maxPQ = new MaxPQ<String>();
		int result = maxPQ.size();
		assertEquals(0, result);
		maxPQ.insert("K");
		maxPQ.insert("L");
		maxPQ.insert("X");
		maxPQ.insert("A");
		maxPQ.insert("T");
		result = maxPQ.size();
		assertEquals(5, result);
	}

	@Test
	public void testMax() {
		IMaxPQ<String> maxPQ = new MaxPQ<String>();
		maxPQ.insert("B");
		maxPQ.insert("D");
		String result = maxPQ.max();
		assertEquals("D", result);
		maxPQ.insert("K");
		maxPQ.insert("L");
		maxPQ.insert("X");
		maxPQ.insert("A");
		maxPQ.insert("T");
		result = maxPQ.max();
		assertEquals("X", result);
		
		
	}

	@Test
	public void testInsert() {
		
		IMaxPQ<String> maxPQ = new MaxPQ<String>();
		int result;
		maxPQ.insert("K");
		result = maxPQ.size();
		assertEquals(1, result);
		maxPQ.insert("L");
		result = maxPQ.size();
		assertEquals(2, result);
		maxPQ.insert("X");
		result = maxPQ.size();
		assertEquals(3, result);
		maxPQ.insert("A");
		result = maxPQ.size();
		assertEquals(4, result);
		maxPQ.insert("T");
		result = maxPQ.size();
		assertEquals(5, result);
		
		
		
	}

	@Test
	public void testDelMin() {
		
		IMaxPQ<String> maxPQ = new MaxPQ<String>();
		String result;
		maxPQ.insert("B");
		maxPQ.insert("D");
		maxPQ.insert("K");
		result = maxPQ.delMax();
		assertEquals("K", result);
		result = maxPQ.delMax();
		assertEquals("D", result);
		result = maxPQ.delMax();
		assertEquals("B", result);
	}

}
