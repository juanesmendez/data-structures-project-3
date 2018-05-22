package model.vo;
import model.data_structures.*;

public class Path implements Comparable<Path>{
	private Stack<Edge<InfoEdge>> edges; //Se utiliza para Dijkstra
	private LinkedList<Edge<InfoEdge>> edgesList; //Se utiliza para encontrar todos los caminos
	private int segundos;
	private float distancia;
	private float valor;
	private boolean peaje;
	
	public Path(Iterable<Edge<InfoEdge>> edges) {
		this.edges = (Stack<Edge<InfoEdge>>) edges;
		this.segundos = 0;
		this.distancia = 0;
		this.valor = 0;
		this.peaje = false;
		this.calculate();
	}
	
	public Path(LinkedList<Edge<InfoEdge>> edges ) {
		this.edgesList = edges;
		this.segundos = 0;
		this.distancia = 0;
		this.valor = 0;
		this.peaje=false;
		this.calculateInfo();
	}
	
	private void calculateInfo() {
		// TODO Auto-generated method stub
		for(Edge<InfoEdge> e:this.edgesList) {
			segundos += e.getWeight().getSegundos();
			distancia += e.getWeight().getDistancia();
			valor += e.getWeight().getValor();
			if(e.getPeaje().getContPeaje()!=0)
			{
				peaje = true;
			}
		}
	}

	private void addEdge(Edge<InfoEdge> e) {
		this.edgesList.add(e);
		this.segundos += e.getWeight().getSegundos();
		this.distancia += e.getWeight().getDistancia();
		this.valor += e.getWeight().getValor();
		if(e.getPeaje().getContPeaje()!=0)
		{
			this.peaje = true;
		}
	}
	
	private void calculate() {
		// TODO Auto-generated method stub
		for(Edge<InfoEdge> e:this.edges) {
			segundos += e.getWeight().getSegundos();
			distancia += e.getWeight().getDistancia();
			valor += e.getWeight().getValor();
			if (e.getPeaje().getContPeaje()!=0)
			{
				this.peaje=true;
			}
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
	
	public boolean getPeaje()
	{
		return peaje;
	}
	
	public void setPeaje(boolean peaje)
	{
		this.peaje = peaje;
	}

	public LinkedList<Edge<InfoEdge>> getEdgesList() {
		return edgesList;
	}

	public void setEdgesList(LinkedList<Edge<InfoEdge>> edgesList) {
		this.edgesList = edgesList;
	}

	@Override
	public int compareTo(Path o) {
		// TODO Auto-generated method stub
		if(this.segundos < o.segundos) {
			return -1;
		}else if(this.segundos > o.segundos) {
			return 1;
		}else if(this.valor < o.valor) { // Alrevez para que los ordene de mayor a menor por valor del viaje
			return 1;
		}else if(this.valor > o.valor) {
			return -1;
		}
		
		return 0;
	}
	
	
	
}
