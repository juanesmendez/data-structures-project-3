package model.data_structures;


//Elaborado con la ayuda de https://algs4.cs.princeton.edu/code/edu/princeton/cs/algs4/SequentialSearchST.java.html

//Class that illustrates a symbol table as a linked list of nodes with a Key and a Value
public class SequentialSearchST<Key extends Comparable<Key>,Value> {
	private int n;           // number of key-value pairs
    private Node first;      // the linked list of key-value pairs

    // a helper linked list data type
    private class Node {
        private Key key;
        private Value val;
        private Node next;

        public Node(Key key, Value val, Node next)  {
            this.key  = key;
            this.val  = val;
            this.next = next;
        }
    }
    
    public SequentialSearchST() {
    }
    
    public int size() {
        return n;
    }
    
    public boolean isEmpty() {
    		if(size() == 0) {
    			return true;
    		}else {
    			return false;
    		}
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
    
    public Value get(Key key) {
        if (key == null) {
        		throw new IllegalArgumentException("argument to get() is null"); 
        }
        for (Node x = first; x != null; x = x.next) {
            if (key.equals(x.key))
                return x.val;
        }
        return null;
    }

    //Inserts a Key-Value pair, if the key value is null it will cause an error to pop up. The SequentialSearch symbol table only admits Key-value pairs without null values
    //If the val received as a parameter is null, the method proceeds to delete the key-value pair with the key received as a parameter
    public void put(Key key, Value val) {
        if (key == null) {
        		throw new IllegalArgumentException("first argument to put() is null"); 
        }
        if (val == null) {
            delete(key);
            return;
        }
        
        Node node = first;
        
        while(node!=null) {
        		if(key.equals(node.key)) {
        			node.val = val;
        			return;
        		}
        		node = node.next;
        }
        first = new Node(key, val, first); //If the key doesn't exists, the new node is added at the beginning of the list
        n++; //Increments the size of the symbol table
    }
    
    public void delete(Key key) {
        if (key == null) {
        		throw new IllegalArgumentException("argument to delete() is null"); 
        }
        first = delete(first, key);
    }

    // delete key in linked list beginning at Node x
    //Recursive function that deletes a node from the symbol table.
    private Node delete(Node x, Key key) {
        if (x == null) {
        		return null;
        }
        if (key.equals(x.key)) {
            n--;
            return x.next;
        }
        x.next = delete(x.next, key);
        return x;
    }
    //returns the keys of the symbol table as an Iterable interface
    public Iterable<Key> keys()  {
        IQueue<Key> queue = new Queue<Key>();
        Node x = first;
        while(x !=null) {
        		queue.enqueue(x.key);
        		x = x.next;
        }
        return queue;
    }

}
