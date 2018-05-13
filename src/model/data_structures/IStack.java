package model.data_structures;

public interface IStack<T extends Comparable<T>> extends Iterable<T> {

	/** Push a new element at the top of the stack */
	public void push (T item);
	
	/** Pop the element at the top of the stack 
	 * @return the top element or null if it doesn't exist
	 * */
	public T pop();
	
	/** Evaluate if the stack is empty
	 * @return true if the stack is empty. false in other case. 
	 */
	public boolean isEmpty();
	
	public int size();

}
