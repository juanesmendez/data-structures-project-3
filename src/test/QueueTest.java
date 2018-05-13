package test;

import static org.junit.Assert.*;

import java.util.Iterator;

import org.junit.Test;

import model.data_structures.IQueue;
import model.data_structures.Queue;

public class QueueTest {

	@Test
	public void testEnqueue() {
		IQueue <Integer> queue= new Queue<Integer>();
		queue.enqueue(1);
		queue.enqueue(2);
		queue.enqueue(3);
		Iterator<Integer> it = queue.iterator();
		int i = 1;
		int result;
		while(it.hasNext()) {
			result = it.next();
			assertEquals(i,result);
			i++;
		}
	}

	@Test
	public void testDequeue() {
		IQueue <Integer> queue= new Queue<Integer>();
		queue.enqueue(1);
		queue.enqueue(2);
		queue.enqueue(3);
		int result;
		result = queue.dequeue();
		assertEquals(1,result);
		result = queue.dequeue();
		assertEquals(2,result);
		result = queue.dequeue();
		assertEquals(3,result);

	}

	@Test
	public void testIsEmpty() {

		IQueue <Integer> queue= new Queue<Integer>();
		boolean result = queue.isEmpty();
		assertEquals(true,result);
		
		queue.enqueue(1);
		result = queue.isEmpty();

		assertEquals(false,result);
		
	}

	@Test
	public void testSize() {
		IQueue <Integer> queue= new Queue<Integer>();
		int result = queue.size();
		assertEquals(0,result);
		queue.enqueue(1);
		queue.enqueue(2);
		queue.enqueue(3);
		result=queue.size();
		assertEquals(3,result);
		
	}

}
