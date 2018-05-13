package test;

import static org.junit.Assert.*;

import org.junit.Test;

import model.data_structures.IStack;
import model.data_structures.Stack;

public class StackTest {

	@Test
	public void testPop() {
		IStack<Integer> stack = new Stack<Integer>();
		stack.push(1);
		stack.push(2);
		stack.push(3);
		int res1 = stack.pop();
		int res2= stack.pop();
		assertEquals(3,res1);
		assertEquals(2,res2);
	}

	@Test
	public void testIsEmpty() {
		IStack<Integer> stack = new Stack<Integer>();
		boolean result = stack.isEmpty();
		assertEquals(true, result);
		stack.push(1);
		result = stack.isEmpty();
		assertEquals(false,result);
	}

	@Test
	public void testPush() {
		IStack<Integer> stack = new Stack<Integer>();
		stack.push(1);
		stack.push(2);
		stack.push(3);
		int n=3;
		int result;
		for(Integer i:stack) {
			result = i;
			assertEquals(n,result);
			n--;
		}
	}

	@Test
	public void testSize() {
		IStack<Integer> stack = new Stack<Integer>();
		int result = stack.size();
		assertEquals(0, result);
		stack.push(1);
		stack.push(2);
		result = stack.size();
		assertEquals(2,result);
	}

}
