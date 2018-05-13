package test;

import static org.junit.Assert.*;

import org.junit.Test;

import model.data_structures.HashTable;
import model.data_structures.HashTableLP;
import model.data_structures.IHashTable;
import model.data_structures.IHashTableLP;

public class HashTableLPTest {

	@Test
	public void testPut() {
		HashTableLP<Integer,String> hashTable = new HashTableLP<Integer,String>();
		hashTable.put(1, "S");
		hashTable.put(2, "X");
		hashTable.put(7, "C");
		int result = hashTable.size();
		assertEquals(3,result);
	}
	

	@Test
	public void testGet() {
		IHashTableLP<Integer,String> hashTable = new HashTableLP<Integer,String>();
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
		HashTableLP<Integer,String> hashTable = new HashTableLP<Integer,String>();
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
		IHashTableLP<Integer,String> hashTable = new HashTableLP<Integer,String>();
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
		HashTableLP<Integer,String> hashTable = new HashTableLP<Integer,String>();
		assertEquals(4,hashTable.getM());
		hashTable.put(1,"A");
		hashTable.put(2,"B");
		//float factorCarga = 2/4;
		float factorCarga = (float)hashTable.getN()/(float)hashTable.getM();
		System.out.println("M: "+hashTable.getM());
		System.out.println("N: "+hashTable.getN());
		System.out.println("Factor de carga: "+factorCarga);
		System.out.println();
		assertEquals(4,hashTable.getM());
		hashTable.put(3,"C");
		factorCarga = (float)hashTable.getN()/(float)hashTable.getM();
		System.out.println("M: "+hashTable.getM());
		System.out.println("N: "+hashTable.getN());
		System.out.println("Factor de carga: "+factorCarga);
		System.out.println();
		hashTable.put(4, "D");
		factorCarga = (float)hashTable.getN()/(float)hashTable.getM();
		System.out.println("M: "+hashTable.getM());
		System.out.println("N: "+hashTable.getN());
		System.out.println("Factor de carga: "+factorCarga);
		System.out.println();
		assertEquals(8,hashTable.getM());
		hashTable.put(5, "E");
		factorCarga = (float)hashTable.getN()/(float)hashTable.getM();
		System.out.println("M: "+hashTable.getM());
		System.out.println("N: "+hashTable.getN());
		System.out.println("Factor de carga: "+factorCarga);
		System.out.println();
		assertEquals(8,hashTable.getM());
		hashTable.put(6, "F");
		factorCarga = (float)hashTable.getN()/(float)hashTable.getM();
		System.out.println("M: "+hashTable.getM());
		System.out.println("N: "+hashTable.getN());
		System.out.println("Factor de carga: "+factorCarga);
		assertEquals(16,hashTable.getM());
		
	}

}
