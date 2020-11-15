package com.alejandro.ana.services;

import com.alejandro.ana.pojos.ArchivoBaseDatosPojo;

public interface GenerarInstanciasService {

	public boolean ejecutaBase(ArchivoBaseDatosPojo archivo) throws Exception;
	
	public void generarBse07(ArchivoBaseDatosPojo archivoBaseDatosPojo);
	
	
}


/*
* NOTA: estaria bien tener metodos por arquitecturas y no por clases de java.
*
* */