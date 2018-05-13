package model.data_structures;

import java.util.Iterator;

//Linear Probing HashTable (Only allows one key-value node per array entry)

public class HashTableLP<Key,Value> implements IHashTableLP<Key,Value>,Iterable<Key>{
	private static final int INIT_CAPACITY = 4;

    private int n;           // number of key-value pairs in the symbol table
    private int m;           // size of linear probing table
    private Key[] keys;      // the keys
    private Value[] vals;    // the values
    private float chargeFactor;
    
    public HashTableLP() {
        this(INIT_CAPACITY);
    }
    
    public HashTableLP(int capacity) {
	    	this.m = capacity;
	    	this.n = 0;
	    	keys = (Key[])   new Object[m];
	    	vals = (Value[]) new Object[m];
    }
    
    
    public int getN() {
		return n;
	}

	public int getM() {
		return m;
	}

	public int size() {
        return n;
    }
    
    public boolean isEmpty() {
        return size() == 0;
    }
    
    public float calculateChargeFactor() {
    		float chargeFactor;
    		chargeFactor = (float)n/(float)m;
    		return chargeFactor;
    }
    
    public boolean contains(Key key) {
    		boolean answer = false;
        if (key == null) {
        		throw new IllegalArgumentException("argument to contains() is null");
        }
        if(get(key) != null) {
        		answer = true;
        }
        return answer;
    }
    
 // hash function for keys - returns value between 0 and M-1
    private int hash(Key key) {
        return (key.hashCode() & 0x7fffffff) % m;
    }
    
 // resizes the hash table to the given capacity by re-hashing all of the keys
    private void resize(int capacity) {
        HashTableLP<Key, Value> temp = new HashTableLP<Key, Value>(capacity);
        for (int i = 0; i < m; i++) {
            if (keys[i] != null) {
                temp.put(keys[i], vals[i]);
            }
        }
        keys = temp.keys;
        vals = temp.vals;
        m    = temp.m;
    }
    
    public void put(Key key, Value val) {
        if (key == null) {
        		throw new IllegalArgumentException("first argument to put() is null");
        }

        if (val == null) {
            delete(key);
            return;
        }

        // double table size if 50% full
        if(calculateChargeFactor() > 0.5) {
        		resize(2*m);
        }
        
        int i;
        for (i = hash(key); keys[i] != null; i = (i + 1) % m) {
            if (keys[i].equals(key)) {
                vals[i] = val;
                return;
            }
        }
        keys[i] = key;
        vals[i] = val;
        n++;
    }
    
    public Value get(Key key) {
        if (key == null) {
        		throw new IllegalArgumentException("argument to get() is null");
        }
        for (int i = hash(key); keys[i] != null; i = (i + 1) % m)
            if (keys[i].equals(key)) {
            		return vals[i];
            }
                
        return null;
    }
    
    public void delete(Key key) {
        if (key == null) {
        		throw new IllegalArgumentException("argument to delete() is null");
        }
        if (!contains(key)) {
        		return;
        }

        // find position i of key
        int i = hash(key);
        while (!key.equals(keys[i])) {
            i = (i + 1) % m; // %m is just to make sure indez stays in bounds (i < m)
        }

        // delete key and associated value
        keys[i] = null;
        vals[i] = null;

        // rehash all keys in same cluster to be sure no null spaces that will affect efficiency later
        i = (i + 1) % m;
        while (keys[i] != null) {
            // delete keys[i] an vals[i] and reinsert
            Key   keyToRehash = keys[i];
            Value valToRehash = vals[i];
            keys[i] = null;
            vals[i] = null;
            n--;
            put(keyToRehash, valToRehash);
            i = (i + 1) % m;
        }

        n--;

        // halves size of array if it's 12.5% full or less
        if (n > 0 && n <= m/8) {
        		resize(m/2);
        }

    }
    
    public Iterable<Key> keys() {
        Queue<Key> queue = new Queue<Key>();
        for (int i = 0; i < m; i++) {
        		if (keys[i] != null) {
        			queue.enqueue(keys[i]);
        		}
        }
        return queue;
    }

	@Override
	public Iterator<Key> iterator() {
		// TODO Auto-generated method stub
		return keys().iterator();
	}
	
	

}
