package model.vo;
import model.data_structures.*;

public class Path {
	private Stack<Edge<InfoEdge>> edges;
	private int segundos;
	private float distancia;
	private float valor;
	
	public Path(Iterable<Edge<InfoEdge>> edges) {
		this.edges = (Stack<Edge<InfoEdge>>) edges;
		this.segundos = 0;
		this.distancia = 0;
		this.valor = 0;
		this.calculate();
	}

	private void calculate() {
		// TODO Auto-generated method stub
		for(Edge<InfoEdge> e:this.edges) {
			segundos += e.getWeight().getSegundos();
			distancia += e.getWeight().getDistancia();
			valor += e.getWeight().getValor();
		}
	}

	public Stack<Edge<InfoEdge>> getEdges() {
		return edges;
	}

	public void setEdges(Stack<Edge<InfoEdge>> edges) {
		this.edges = edges;
	}

	public int getSegundos() {
		return segundos;
	}

	public void setSegundos(int segundos) {
		this.segundos = segundos;
	}

	public float getDistancia() {
		return distancia;
	}

	public void setDistancia(float distancia) {
		this.distancia = distancia;
	}

	public float getValor() {
		return valor;
	}

	public void setValor(float valor) {
		this.valor = valor;
	}
	
}
