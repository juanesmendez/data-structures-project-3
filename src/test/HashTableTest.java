package test;

import static org.junit.Assert.*;

import org.junit.Test;
import model.data_structures.HashTable;
import model.data_structures.IHashTable;

public class HashTableTest {

	@Test
	public void testPut() {
		HashTable<Integer,String> hashTable = new HashTable<Integer,String>();
		hashTable.put(1, "S");
		hashTable.put(2, "X");
		hashTable.put(7, "C");
		int result = hashTable.size();
		assertEquals(3,result);
	}
	

	@Test
	public void testGet() {
		IHashTable<Integer,String> hashTable = new HashTable<Integer,String>();
		hashTable.put(1, "S");
		hashTable.put(2, "X");
		hashTable.put(7, "C");
		String result = hashTable.get(2);
		assertEquals("X",result);
		result = hashTable.get(7);
		assertEquals("C",result);
		result = hashTable.get(1);
		assertEquals("S",result);
	}
	
	@Test
	public void testDelete() {
		HashTable<Integer,String> hashTable = new HashTable<Integer,String>();
		hashTable.put(1, "S");
		hashTable.put(2, "X");
		hashTable.put(7, "C");
		hashTable.delete(2);
		int result = hashTable.size();
		assertEquals(2,result);
		hashTable.delete(7);
		result = hashTable.size();
		assertEquals(1,result);
	}
	
	@Test
	public void testKeys() {
		IHashTable<Integer,String> hashTable = new HashTable<Integer,String>();
		hashTable.put(1, "S");
		hashTable.put(2, "X");
		hashTable.put(7, "C");
		Iterable<Integer> keys = hashTable.keys();
		int count = 0;
		for(Integer i:keys) {
			count++;
		}
		assertEquals(3, count);
	}
	
	@Test
	public void testResize() {
		HashTable<Integer,String> hashTable = new HashTable<Integer,String>();
		assertEquals(4,hashTable.getM());
		hashTable.put(1,"A");
		hashTable.put(2,"B");
		hashTable.put(3,"C");
		hashTable.put(4,"D");
		hashTable.put(5,"E");
		hashTable.put(6, "F");
		hashTable.put(7, "G");
		hashTable.put(8, "H");
		hashTable.put(9, "I");
		hashTable.put(10, "J");
		hashTable.put(11, "K");
		hashTable.put(12, "L");
		hashTable.put(13, "M");
		hashTable.put(14, "N");
		hashTable.put(15, "O");
		hashTable.put(16, "P");
		hashTable.put(17, "Q");
		hashTable.put(18, "R");
		hashTable.put(19, "S");
		hashTable.put(20, "T");
		hashTable.put(21, "U");
		hashTable.put(22, "V");
		hashTable.put(23, "W");
		hashTable.put(24, "X");
		hashTable.put(25, "Y");
		hashTable.put(26, "Z");
		hashTable.put(27, "AB");
		hashTable.put(28, "AC");
		System.out.println("M: "+hashTable.getM());
		System.out.println("N: "+hashTable.getN());
		System.out.println("Factor de carga: "+hashTable.getN()/hashTable.getM());
		System.out.println();
		hashTable.put(29, "AD");
		System.out.println("M: "+hashTable.getM());
		System.out.println("N: "+hashTable.getN());
		System.out.println("Factor de carga: "+hashTable.getN()/hashTable.getM());
		assertEquals(8,hashTable.getM());
		
	}
	

}
