package com.alejandro.ana.services;

import com.alejandro.ana.pojos.ArchivoBaseDatosPojo;

public interface GenerarInstanciasService {

	public boolean ejecutaBase(ArchivoBaseDatosPojo archivo) throws Exception;
	
	public void generarArchivos(ArchivoBaseDatosPojo archivoBaseDatosPojo);
	
	
}
