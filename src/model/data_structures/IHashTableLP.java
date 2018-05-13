package model.data_structures;

public interface IHashTableLP<Key,Value> extends Iterable<Key> {
	public void put(Key key, Value val);
	public Value get(Key key);
	public void delete(Key key);
	public Iterable<Key> keys();
}
