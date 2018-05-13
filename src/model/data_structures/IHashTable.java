package model.data_structures;

//Interface of a separate chaining HashTable

public interface IHashTable<Key extends Comparable<Key>,Value> extends Iterable<Key>{

	public void put(Key key, Value val);
	public Value get(Key key);
	public void delete(Key key);
	public Iterable<Key> keys();
}
