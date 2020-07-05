package com.alejandro.ana.pojos;

import java.util.ArrayList;
import java.util.List;

public class EntidadesPojo {

	
	private Boolean isEntity;
	private String nombreClase;
	private String nombreTabla;
	private String paquete;
	private List<AtributoPojo> atributos = new ArrayList<>();
    private List<RelacionPojo> relaciones = new ArrayList<>();
    
   
	public EntidadesPojo() {}


	public Boolean getIsEntity() {
		return isEntity;
	}


	public void setIsEntity(Boolean isEntity) {
		this.isEntity = isEntity;
	}


	public String getNombreClase() {
		return nombreClase;
	}


	public void setNombreClase(String nombreClase) {
		this.nombreClase = nombreClase;
	}


	public String getNombreTabla() {
		return nombreTabla;
	}


	public void setNombreTabla(String nombreTabla) {
		this.nombreTabla = nombreTabla;
	}


	public String getPaquete() {
		return paquete;
	}


	public void setPaquete(String paquete) {
		this.paquete = paquete;
	}


	public List<AtributoPojo> getAtributos() {
		return atributos;
	}


	public void setAtributos(List<AtributoPojo> atributos) {
		this.atributos = atributos;
	}


	public List<RelacionPojo> getRelaciones() {
		return relaciones;
	}


	public void setRelaciones(List<RelacionPojo> relaciones) {
		this.relaciones = relaciones;
	}


	@Override
	public String toString() {
		return "EntidadesPojo [isEntity=" + isEntity + ", nombreClase=" + nombreClase + ", nombreTabla=" + nombreTabla
				+ ", paquete=" + paquete + ", atributos=" + atributos + ", relaciones=" + relaciones + "]";
	}





}
