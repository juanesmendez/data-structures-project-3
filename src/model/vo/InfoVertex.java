package model.vo;

import model.data_structures.LinkedList;
import model.data_structures.List;

public class InfoVertex implements Comparable<InfoVertex>{

	
	private double latitudReferencia;
	private double longitudReferencia;
	private LinkedList<String> listaServicios; //Va a guardar los ids de servicios que entran en el disco
	
	public InfoVertex(double latitudReferencia,double longitudReferencia) {
		this.latitudReferencia = latitudReferencia;
		this.longitudReferencia = longitudReferencia;
		this.listaServicios = new List<>();
	}

	public double getLatitudReferencia() {
		return latitudReferencia;
	}

	public void setLatitudReferencia(double latitudReferencia) {
		this.latitudReferencia = latitudReferencia;
	}

	public double getLongitudReferencia() {
		return longitudReferencia;
	}

	public void setLongitudReferencia(double longitudReferencia) {
		this.longitudReferencia = longitudReferencia;
	}

	public LinkedList<String> getListaServicios() {
		return listaServicios;
	}

	public void setListaServicios(LinkedList<String> listaServicios) {
		this.listaServicios = listaServicios;
	}

	@Override
	public int compareTo(InfoVertex o) {
		// TODO Auto-generated method stub
		if(latitudReferencia == o.getLatitudReferencia() && longitudReferencia == o.getLongitudReferencia()) {
			return 0;
		}else if(latitudReferencia < o.getLatitudReferencia() && longitudReferencia < o.getLongitudReferencia()){
			return -1;
		}else if(latitudReferencia > o.getLatitudReferencia() && longitudReferencia > o.getLongitudReferencia()) {
			return 1;
		}else if(latitudReferencia < o.getLatitudReferencia() && longitudReferencia > o.getLongitudReferencia()) {
			return -1;
		}else if(latitudReferencia > o.getLatitudReferencia() && longitudReferencia < o.getLongitudReferencia()) {
			return 1;
		}
		
		
		return 0;
	}
	
	public void addService(String idService) {
		this.listaServicios.add(idService);
	}

	
	
	
}
