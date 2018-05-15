package model.vo;

public class Component implements Comparable<Component>{
	
	private int color;
	private int numVertices;
	
	public Component(int color) {
		this.color = color;
		this.numVertices = 0;
	}
	public int getColor() {
		return color;
	}
	public void setColor(int color) {
		this.color = color;
	}
	public int getNumVertices() {
		return numVertices;
	}
	public void setNumVertices(int numVertices) {
		this.numVertices = numVertices;
	}
	@Override
	public int compareTo(Component o) {
		// TODO Auto-generated method stub
		if(this.color < o.color) {
			return -1;
		}else if(this.color > o.getColor()) {
			return 1;
		}
		return 0;
	}
	
	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return "Color: "+this.color+"\nNumero de vertices: "+this.numVertices;
	}
	
	public void agregarVertice() {
		this.numVertices++;
	}
	
	
	
}
