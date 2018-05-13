package model.data_structures;

import java.util.Iterator;

//Elaborado con la ayuda de https://algs4.cs.princeton.edu/code/edu/princeton/cs/algs4/SeparateChainingHashST.java.html 

//Separate chaining HashTable (Allows more than one key-value node per array entry

public class HashTable<Key extends Comparable<Key>,Value> implements IHashTable<Key,Value>,Iterable<Key>{
    private static final int INIT_CAPACITY = 4;
    
    private int n;                                // number of key-value pairs
    private int m;                                // hash table size
    private SequentialSearchST<Key, Value>[] st;  // array of linked-list symbol tables
    private float chargeFactor;					 //Percentage that determines how many entries of the HashTable are occupied.
    
    public HashTable() {
        this(INIT_CAPACITY);
    } 
    
    public HashTable(int m) {
        this.m = m;
        st = (SequentialSearchST<Key, Value>[]) new SequentialSearchST[m];
        for (int i = 0; i < m; i++) {
            st[i] = new SequentialSearchST<Key, Value>(); //Initialize each of the array entries as a SequentialSearch symbol table
        }
        chargeFactor = 0;
    }
    
    public int getN() {
		return n;
	}

	public int getM() {
		return m;
	}

	// resize the hash table to have the given number of chains,
    // rehashing all of the keys
    private void resize(int numberOfEntries) {
        HashTable<Key, Value> temp = new HashTable<Key, Value>(numberOfEntries);
        for (int i = 0; i < m; i++) {
            for (Key key : st[i].keys()) {
                temp.put(key, st[i].get(key)); //Insets into de HashTable each of the entries the pevious o
            }
        }
        this.m  = temp.m;
        this.n  = temp.n;
        this.st = temp.st;
    }
    
    // hash value between 0 and m-1
    private int hash(Key key) {
    		int arrayEntry = (key.hashCode() & 0x7fffffff) % m;
        return arrayEntry; //creates value for storing the key-value pair in the hash value entry created
    } 
    
    
    public int size() {
        return n; //Return the total number of key-value nodes in the HashTable
    } 
    
    public boolean isEmpty() {
    		if(this.size() == 0) {
    			return true;
    		}else {
    			return false;
    		}
    }

    public float calculateChargeFactor() {
    		float chargeFactor;
    		chargeFactor = n/m;
    		this.chargeFactor = chargeFactor;
    		return chargeFactor;

    }
    //Auxiliary method for knowing if the key exists in the HashTable
    public boolean contains(Key key) {
        if (key == null) {
        		throw new IllegalArgumentException("argument to contains() is null");
        }
        return get(key) != null;
    } 
    //Returns the value of the key received as a parameter
    public Value get(Key key) {
        if (key == null) {
        		throw new IllegalArgumentException("argument to get() is null"); //The key received as a parameter can't be null
        }
        int i = hash(key); //Returns the entry at which the key can be find in the array of the HashTable
        return this.st[i].get(key);
    }
    
    public void put(Key key, Value val) {
        if (key == null) {
        		throw new IllegalArgumentException("first argument to put() is null"); //Key can't be null
        }
        if (val == null) {
            delete(key); //If the value received as a parameter is null-> delete de given key-value pair from the symbol table
            return;
        }

        // double table size if charge factor is greater than 6
        if(calculateChargeFactor() > 6) {
        		resize(2*m); // Double the table size
        }
        
        int i = hash(key);
        if (!st[i].contains(key)) {
        		n++;
        }
        st[i].put(key, val);
    } 
  
    //Deletes the specified key and the value associated to it from the hash table
    public void delete(Key key) {
        if (key == null) {
        		throw new IllegalArgumentException("argument to delete() is null");
        }

        int i = hash(key);
        if (st[i].contains(key)) {
        		n--;
        }
        st[i].delete(key);

        // Reduces table size to half if the average length of each entry list is <=2. Same as n/m <= 2
        if (m > INIT_CAPACITY && n <= 2*m) {
        		resize(m/2);
        }
    }
    
    @Override
    public Iterator<Key> iterator() {
    	// TODO Auto-generated method stub
    		Iterable<Key> iterable = this.keys();
    		
    		return iterable.iterator();
    }
    
 // return keys in symbol table as an Iterable
    public Iterable<Key> keys() {
        IQueue<Key> queue = new Queue<Key>();
        for (int i = 0; i < m; i++) {
            for (Key key : st[i].keys()) {
            		queue.enqueue(key);
            }
                
        }
        return queue;
    }


    
}
