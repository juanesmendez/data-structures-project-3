package model.vo;

public class InfoEdge {
	
	private float distancia;  //Distancia en metros del servicio (Si hay mas de un servicio entonces es el promedio de distancias)
	private float valor; //Valor del servicio $. (Si hay mas de un servicio entonces es el promedio de valores)
	private int segundos;
	private int contPeaje; //Numero de servicios en el arco que pagaron peaje

	
	public InfoEdge(float distancia, float valor, int segundos, boolean peaje) {
		this.distancia = distancia;
		this.valor = valor;
		this.segundos = segundos;
		if(peaje == false) {
			this.contPeaje = 0;
		}else {
			this.contPeaje = 1;
		}
		
	}
	
	public InfoEdge(float distancia, float valor, int segundos, int peaje) {
		this.distancia = distancia;
		this.valor = valor;
		this.segundos = segundos;
		this.contPeaje = peaje;
		
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

	public int getSegundos() {
		return segundos;
	}

	public void setSegundos(int segundos) {
		this.segundos = segundos;
	}
	
	public int getContPeaje() {
		return contPeaje;
	}

	public void setContPeaje(int contPeaje) {
		this.contPeaje = contPeaje;
	}

	public void promedio(float nuevaDistancia,float nuevovalor, int nuevosSegundos, boolean nuevoPeaje) {
		this.distancia = (distancia + nuevaDistancia)/2;
		this.valor = (valor+nuevovalor)/2;
		this.segundos = (segundos+nuevosSegundos)/2;
		if(nuevoPeaje) {
			contPeaje++;
		}
	}
	
	
}
